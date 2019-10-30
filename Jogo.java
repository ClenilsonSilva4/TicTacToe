package IMD.LP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Scanner;

class Jogo extends JFrame {
    private Jogador[] jogadores;
    private int vencedor;
    private int rodadas;
    private Tabuleiro tabJogo;
    private Scanner leitor;

    Jogo() {
        super("Jogo da Velha");
        this.jogadores = new Jogador[2];
        this.vencedor = -1;
        this.rodadas = 0;
        this.leitor = new Scanner(System.in);
        this.tabJogo = new Tabuleiro();
        this.iniciarJogo();
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

        descriptionP1.setFont(new Font("TimesRoman", Font.PLAIN + Font.ITALIC, 12));
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
        JLabel descriptionP2 = new JLabel("Nome do Segundo Jogador: ");

        descriptionP2.setFont(new Font("TimesRoman", Font.PLAIN + Font.ITALIC, 12));
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
        startGame.setFont(new Font("TimesRoman", Font.BOLD, 12));

        Container initialLayout = getContentPane();
        initialLayout.setLayout(new FlowLayout());
        initialLayout.add(inicialTitle);
        initialLayout.add(playerOnePanel);
        initialLayout.add(playerTwoPanel);
        initialLayout.add(startGame);

        setSize(500, 180);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        startGame.addActionListener(actionEvent -> {
            if(nameP1Box.getText().isEmpty() && nameP2Box.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Os Nomes dos Jogadores Estão Vazios");
            } else if(nameP1Box.getText().length() < 2) {
                JOptionPane.showMessageDialog(null, "O Nome Inserido para o Primeiro Jogador é Inválido");
            } else if(nameP2Box.getText().length() < 2) {
                JOptionPane.showMessageDialog(null, "O Nome Inserido para o Segundo Jogador é Inválido");
            } else if(selectIdentifierP1.getSelectedItem() == null || selectIdentifierP2.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Os Identificadores dos Jogadores não Foram Selecionados");
            } else {
                int confirmation = JOptionPane.showConfirmDialog(null, "Tem Certeza que Quer Começar o Jogo?");
                if(confirmation == JOptionPane.YES_OPTION) {
                    jogadores[0] = new Jogador(nameP1Box.getText(), selectIdentifierP1.getSelectedItem().toString().charAt(0));
                    jogadores[1] = new Jogador(nameP2Box.getText(), selectIdentifierP2.getSelectedItem().toString().charAt(0));
                    getContentPane().removeAll();
                    this.administrarRodadas();
                }
            }
        });
    }

    private void administrarRodadas() {
        boolean temGanhador = true;
        JOptionPane.showMessageDialog(null, "Será Decidido na Sorte o Primeiro a Jogar!\nE Quem Começa a Jogar é...");
        double sorteComecar = Math.random();

        if(sorteComecar < 0.5) {
            JOptionPane.showMessageDialog(null, this.jogadores[0].getNome());
            this.jogadores[0].setTemVez(true);
        } else {
            JOptionPane.showMessageDialog(null, this.jogadores[1].getNome());
            this.jogadores[1].setTemVez(true);
        }
        this.tabJogo.imprimirTabuleiro();

        while(temGanhador) {
            rodadas++;
            if(this.jogadores[0].getTemVez()) {
                this.tabJogo.preencherTabuleiro(this.jogadores[0]);
                this.jogadores[1].setTemVez(true);
            } else {
                this.tabJogo.preencherTabuleiro(this.jogadores[1]);
                this.jogadores[0].setTemVez(true);
            }
            System.out.println("Rodada " + this.rodadas);
            this.tabJogo.imprimirTabuleiro();

            char identVenc = this.tabJogo.tabuleiroCompleto();
            if (identVenc == 'X' || identVenc == 'O') {
                for (int i = 0; i < 2; i++) {
                    if(this.jogadores[i].getTipoCasa() == identVenc) {
                        this.vencedor = i;
                    }
                }
                temGanhador = false;
            } else if(identVenc == 'D') {
                this.vencedor = 2;
                temGanhador = false;
            }
        }
        this.apresentarVencedor();
    }

    private void apresentarVencedor() {
        System.out.println();
        if(this.vencedor >= 0 && this.vencedor < 2) {
            System.out.println("O Vencedor do Jogo é: " + this.jogadores[this.vencedor].getNome());
            if(this.jogadores[this.vencedor].getJogadaErrada()) {
                System.out.println("Mesmo Tendo Feito Jogadas Erradas.");
            }
        } else {
            System.out.println("Deu Velha! A Partida Terminou em Empate.");
        }

        this.tabJogo.zerarTabuleiro();
        System.out.println("Será Iniciada uma Nova Partida");
        System.out.println("Digite \"Y\" Se Quiser Manter os Mesmos Jogadores");
        if(leitor.next().charAt(0) == 'Y') {
            this.administrarRodadas();
        } else {
            leitor = new Scanner(System.in);
            this.iniciarJogo();
        }
    }
}