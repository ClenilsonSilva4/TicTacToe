package IMD.LP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

class Jogo extends JFrame{
    private Jogador[] jogadores;
    private int vencedor;
    private int rodadas;
    private Tabuleiro tabJogo;
    private Container gameLayout;

    public Jogo() {
        super("Jogo da Velha");
        this.jogadores = new Jogador[2];
        this.vencedor = -1;
        this.rodadas = 1;
        this.tabJogo = new Tabuleiro();
        iniciarJogo();
    }

    private void iniciarJogo() {
        String[] identifierOptions = {"X", "O"};
        JComboBox<String> selectIdentifierP1 = new JComboBox<>(identifierOptions);
        JComboBox<String> selectIdentifierP2 = new JComboBox<>(identifierOptions);

        JLabel inicialTitle = new JLabel("Bem-vindos ao Jogo da Velha");
        inicialTitle.setFont(new Font("TimesRoman", Font.BOLD, 14));

        JPanel playerOnePanel = new JPanel(new FlowLayout());
        JTextField nameP1Box = new JTextField(20);
        JLabel descriptionP1 = new JLabel("Nome do Primeiro Jogador: ");

        descriptionP1.setFont(new Font("TimesRoman", Font.PLAIN + Font.ITALIC, 14));
        nameP1Box.setToolTipText("Insira o Nome do Primeiro Jogador");
        selectIdentifierP1.setToolTipText("Escolha o Identificador que o Primeiro Jogador Usará");
        selectIdentifierP1.setSelectedIndex(-1);
        selectIdentifierP1.addItemListener(event -> {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    if(selectIdentifierP1.getSelectedItem() == "X") {
                        selectIdentifierP2.setSelectedIndex(1);
                    } else {
                        selectIdentifierP2.setSelectedIndex(0);
                    }
                }
        });
        playerOnePanel.add(descriptionP1);
        playerOnePanel.add(nameP1Box);
        playerOnePanel.add(selectIdentifierP1);


        JPanel playerTwoPanel = new JPanel(new FlowLayout());
        JTextField nameP2Box = new JTextField(20);
        JLabel descriptionP2 = new JLabel("Nome do Segundo Jogador:  ");

        descriptionP2.setFont(new Font("TimesRoman", Font.PLAIN + Font.ITALIC, 14));
        nameP2Box.setToolTipText("Insira o Nome do Segundo Jogador");
        selectIdentifierP2.setToolTipText("Escolha o Identificador que o Segundo Jogador Usará");
        selectIdentifierP2.setSelectedIndex(-1);
        selectIdentifierP2.addItemListener(event -> {
            if(event.getStateChange() == ItemEvent.SELECTED) {
                if(selectIdentifierP2.getSelectedItem() == "X") {
                    selectIdentifierP1.setSelectedIndex(1);
                } else {
                    selectIdentifierP1.setSelectedIndex(0);
                }
            }
        });
        playerTwoPanel.add(descriptionP2);
        playerTwoPanel.add(nameP2Box);
        playerTwoPanel.add(selectIdentifierP2);

        JButton startGame = new JButton("Iniciar Jogo");
        startGame.setFont(new Font("TimesRoman", Font.BOLD, 14));

        gameLayout = getContentPane();
        gameLayout.setLayout(new FlowLayout());
        gameLayout.add(inicialTitle);
        gameLayout.add(playerOnePanel);
        gameLayout.add(playerTwoPanel);
        gameLayout.add(startGame);

        setSize(500, 180);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        startGame.addActionListener(actionEvent -> {
            if(nameP1Box.getText().isEmpty() && nameP2Box.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Os nomes dos jogadores estão vazios");
            } else if(nameP1Box.getText().length() < 2) {
                JOptionPane.showMessageDialog(null, "O nome inserido para o primeiro jogador é inválido");
            } else if(nameP2Box.getText().length() < 2) {
                JOptionPane.showMessageDialog(null, "O nome inserido para o segundo jogador é inválido");
            } else if(selectIdentifierP1.getSelectedItem() == null || selectIdentifierP2.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Os identificadores dos jogadores não foram selecionados");
            } else {
                int confirmation = JOptionPane.showConfirmDialog(null, "Tem certeza que quer começar o jogo?");
                if(confirmation == JOptionPane.YES_OPTION) {
                    jogadores[0] = new Jogador(nameP1Box.getText(), selectIdentifierP1.getSelectedItem().toString().charAt(0));
                    jogadores[1] = new Jogador(nameP2Box.getText(), selectIdentifierP2.getSelectedItem().toString().charAt(0));
                    gameLayout.removeAll();
                    gameLayout.revalidate();
                    this.administrarRodadas();
                }
            }
        });
    }

    private void administrarRodadas() {
        gameLayout = getContentPane();
        gameLayout.setLayout(new BorderLayout());

        JLabel gameRounds = new JLabel("Rodada " + rodadas);
        gameRounds.setHorizontalAlignment(SwingConstants.CENTER);
        gameRounds.setFont(new Font("TimesRoman", Font.BOLD, 20));

        JPanel boardLayout = new JPanel(new GridLayout(3, 3));

        JLabel playerDecision = new JLabel("", SwingConstants.LEFT);
        playerDecision.setFont(new Font("TimesRoman", Font.ITALIC, 14));

        JButton restartButton = new JButton("Reiniciar  Jogo");
        restartButton.setBackground(Color.white);
        restartButton.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 16));
        restartButton.addActionListener(event -> {
            gameLayout.removeAll();
            gameLayout.revalidate();
            gameLayout = getContentPane();
            gameLayout.setLayout(new FlowLayout());
            setSize(500, 80);
            rodadas = 1;

            JButton newGame, newSession;
            newGame = new JButton("Cadastrar Novos Jogadores");
            newGame.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 14));
            newGame.addActionListener(newGameEvent -> {
                gameLayout.removeAll();
                gameLayout.revalidate();
                tabJogo.zerarTabuleiro();
                iniciarJogo();
            });

            newSession = new JButton("Reiniciar Partida");
            newSession.setFont(new Font("TimesRoman", Font.BOLD + Font.ITALIC, 14));
            newSession.addActionListener(newSessionEvent -> {
                gameLayout.removeAll();
                gameLayout.revalidate();
                tabJogo.zerarTabuleiro();
                administrarRodadas();
            });

            gameLayout.add(newGame);
            gameLayout.add(newSession);
        });

        JPanel southBorder = new JPanel(new BorderLayout());
        southBorder.add(playerDecision, BorderLayout.NORTH);
        southBorder.add(restartButton, BorderLayout.SOUTH);

        gameLayout.add(gameRounds, BorderLayout.NORTH);
        gameLayout.add(boardLayout, BorderLayout.CENTER);
        gameLayout.add(southBorder, BorderLayout.SOUTH);

        double sorteComecar = Math.random();

        if(sorteComecar < 0.5) {
            playerDecision.setText("<html><br/>Quem Começa a Jogar é " + jogadores[0].getNome() +
                                   " com o identificador " + jogadores[0].getTipoCasa());
            this.jogadores[0].setTemVez(true);
        } else {
            playerDecision.setText("<html><br/>Quem Começa a Jogar é " + this.jogadores[1].getNome() +
                                   " com o identificador " + jogadores[1].getTipoCasa());
            this.jogadores[1].setTemVez(true);
        }

        setSize(500, 600);
        imprimirTabuleiro(boardLayout, playerDecision, gameRounds);
    }

    private void checarVencedor(JLabel playerDecision, JPanel boardLayout) {
        char identVenc = tabJogo.tabuleiroCompleto();
        if (identVenc == 'X' || identVenc == 'O') {
            for (int i = 0; i < 2; i++) {
                if(this.jogadores[i].getTipoCasa() == identVenc) {
                    this.vencedor = i;
                }
            }
            apresentarVencedor(playerDecision, boardLayout);
        } else if(identVenc == 'D') {
            this.vencedor = 2;
            apresentarVencedor(playerDecision, boardLayout);
        }
    }

    private void apresentarVencedor(JLabel playerDecision, JPanel boardLayout) {
        for (int i = 0; i < 9; i++) {
            JButton disable = (JButton) boardLayout.getComponent(i);
            disable.setEnabled(false);
        }

        if(this.vencedor >= 0 && this.vencedor < 2) {
            playerDecision.setText("<html><br/>O vencedor do jogo é " + this.jogadores[this.vencedor].getNome());
        } else {
            playerDecision.setText("<html><br/>Deu Velha! A partida terminou em empate.");
        }
    }

    private void imprimirTabuleiro(JPanel boardLayout, JLabel moveMessage, JLabel gameRounds) {
        JButton[] playersMove = new JButton[9];

        for (int i = 0; i < 9; i++) {
            playersMove[i] = new JButton();
            playersMove[i].setName(String.valueOf(i));
            playersMove[i].setFont(new Font("TimesRoman", Font.BOLD, 40));
            playersMove[i].setBackground(Color.white);
            playersMove[i].addActionListener(actionEvent -> {
                JButton clickedButton = (JButton) actionEvent.getSource();
                if(jogadores[0].getTemVez()) {
                    playersMove[Integer.parseInt(clickedButton.getName())].setText(Character.toString(
                                                                                   jogadores[0].getTipoCasa()));
                    tabJogo.preencherTabuleiro(jogadores[0], Integer.parseInt(clickedButton.getName()) / 3,
                                         Integer.parseInt(clickedButton.getName()) % 3);
                    moveMessage.setText("<html><br/>A vez de jogar é do(a) " + jogadores[1].getNome() +
                                        " com o identificador " + jogadores[1].getTipoCasa());
                    jogadores[1].setTemVez(true);
                } else {
                    playersMove[Integer.parseInt(clickedButton.getName())].setText(Character.toString(
                                                                                   jogadores[1].getTipoCasa()));
                    tabJogo.preencherTabuleiro(jogadores[1], Integer.parseInt(clickedButton.getName()) / 3,
                                         Integer.parseInt(clickedButton.getName()) % 3);
                    moveMessage.setText("<html><br/>A vez de jogar é do(a) " + jogadores[0].getNome() +
                            " com o identificador " + jogadores[0].getTipoCasa());
                    jogadores[0].setTemVez(true);
                }
                playersMove[Integer.parseInt(clickedButton.getName())].setEnabled(false);
                rodadas++;
                gameRounds.setText("Rodada " + rodadas);
                checarVencedor(moveMessage, boardLayout);
            });
            boardLayout.add(playersMove[i]);
        }
    }
}