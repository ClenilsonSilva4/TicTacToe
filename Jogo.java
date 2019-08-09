package IMD.LP;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Jogo {
    private Jogador[] jogadores;
    private int vencedor;
    private int rodadas;
    private Jogador temVez;
    Scanner leitor;

    public Jogo() {
        jogadores = new Jogador[2];
        vencedor = -1;
        rodadas = 0;
        leitor = new Scanner(System.in);
        iniciarJogo();
    }

    private void iniciarJogo() {
        for (int i = 0; i < jogadores.length; i++) {
            String auxNome;
            char tipoJog;
            boolean condicao = false;

            do {
                System.out.println("Insira o Nome do Primeiro Jogador: ");
                auxNome = leitor.nextLine();
                if(auxNome.length() <= 3) {
                    System.out.println("O Comprimento do Nome do Jogador Precisa Ser Maior que Dois Caracteres");
                    condicao = true;
                }
            } while(condicao);
            condicao = false;

            if(i == 0) {
                do {
                    System.out.println("Com Qual Identificador Você Quer Jogar? ");
                    tipoJog = leitor.nextLine().charAt(0);
                    if(tipoJog != 'X' || tipoJog != 'O') {
                        System.out.println("O Identificador Precisa Ser \"X\" ou \"O\"");
                        condicao = true;
                    }
                } while(condicao);
            } else if (jogadores[0].getTipoCasa() == 'X'){
                tipoJog = 'O';
            } else {
                tipoJog = 'X';
            }

            jogadores[i] = new Jogador(auxNome, tipoJog);
        }
        administrarRodadas();
    }

    private void administrarRodadas() throws InterruptedException {
        System.out.println("Será Decidido na Sorte o Primeiro a Jogar...");
        double sorteComecar = Math.random();
        System.out.println("E Quem Começa a Jogar é... ");
        TimeUnit.SECONDS.sleep(1);
        if(sorteComecar < 0.5) {
            System.out.println(jogadores[0].getNome());
        } else {
            System.out.println(jogadores[1].getNome());
        }
    }
}