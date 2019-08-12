package IMD.LP;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Jogo {
    private Jogador[] jogadores;
    private int vencedor;
    private int rodadas;
    private Tabuleiro tabJogo;
    private Scanner leitor;

    Jogo() {
        this.jogadores = new Jogador[2];
        this.vencedor = -1;
        this.rodadas = 0;
        this.leitor = new Scanner(System.in);
        this.tabJogo = new Tabuleiro();
        System.out.println("Bem-vindos ao Jogo da Velha\n");
        this.iniciarJogo();
    }

    private void iniciarJogo() {
        for (int i = 0; i < this.jogadores.length; i++) {
            String auxNome;
            char tipoJog;
            boolean condicao = false;

            do {
                System.out.print("Insira o Nome do Jogador(a): ");
                auxNome = this.leitor.nextLine();
                condicao = false;
                if(auxNome.length() <= 3) {
                    System.out.println("O Comprimento do Nome do Jogador Precisa Ser Maior que Dois Caracteres");
                    condicao = true;
                }
            } while(condicao);

            if(i == 0) {
                do {
                    System.out.print("Com Qual Identificador Você Quer Jogar? ");
                    tipoJog = this.leitor.nextLine().charAt(0);
                    condicao = false;
                    if(tipoJog != 'X' && tipoJog != 'O') {
                        System.out.println("O Identificador Precisa Ser \"X\" ou \"O\"");
                        condicao = true;
                    }
                } while(condicao);
            } else if (this.jogadores[0].getTipoCasa() == 'X'){
                tipoJog = 'O';
            } else {
                tipoJog = 'X';
            }

            this.jogadores[i] = new Jogador(auxNome, tipoJog);
        }

        this.administrarRodadas();
    }

    private void administrarRodadas() {
        boolean temGanhador = true;

        System.out.println("Será Decidido na Sorte o Primeiro a Jogar!");
        double sorteComecar = Math.random();
        System.out.print("E Quem Começa a Jogar é... ");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(sorteComecar < 0.5) {
            System.out.println(this.jogadores[0].getNome());
            this.jogadores[0].setTemVez(true);
        } else {
            System.out.println(this.jogadores[1].getNome());
            this.jogadores[1].setTemVez(true);
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
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