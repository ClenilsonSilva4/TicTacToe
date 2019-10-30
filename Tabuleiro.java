package IMD.LP;

import java.util.Scanner;

class Tabuleiro{
    private char[][] matrizTabuleiro;
    private Scanner getCasas;

    Tabuleiro() {
        this.getCasas = new Scanner(System.in);
        this.matrizTabuleiro = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizTabuleiro[i][j] = ' ';
            }
        }
    }

    void zerarTabuleiro() {
        this.matrizTabuleiro = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.matrizTabuleiro[i][j] = ' ';
            }
        }
    }

    char tabuleiroCompleto() {
        int checkDiagX = 0, checkDiagO = 0, checkHorX = 0, checkHorO = 0, checkVerX = 0, checkVerO = 0, cont = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == j){
                    if(this.matrizTabuleiro[i][j] == 'X'){
                        checkDiagX++;
                    } else if(this.matrizTabuleiro[i][j] == 'O') {
                        checkDiagO++;
                    }
                }

                if(this.matrizTabuleiro[i][j] == 'X') {
                    checkHorX++;
                } else if (this.matrizTabuleiro[i][j] == 'O') {
                    checkHorO++;
                }

                if(this.matrizTabuleiro[j][i] == 'X') {
                    checkVerX++;
                } else if (this.matrizTabuleiro[j][i] == 'O') {
                    checkVerO++;
                }
                if(this.matrizTabuleiro[i][j] != ' ') {
                    cont++;
                }
            }
            if(checkHorO == 3 || checkVerO == 3) {
                return 'O';
            } else if (checkHorX == 3 || checkVerX == 3) {
                return 'X';
            }
            checkHorO = checkVerO = checkVerX = checkHorX = 0;
        }
        if(checkDiagO == 3) {
            return 'O';
        } else if(checkDiagX == 3) {
            return 'X';
        } else if(cont == 9) {
            return 'D';
        }
        return ' ';
    }

    private boolean verificarCasa(int pos1, int pos2) {
        return this.matrizTabuleiro[pos1][pos2] == ' ';
    }

    void preencherTabuleiro(Jogador comVez) {
        int pos1, pos2;

        System.out.println("\nA Vez é do(a) Jogador(a) " + comVez.getNome() +
                           " Com o Identificador \"" + comVez.getTipoCasa() + "\"");

        System.out.println("Insira a Primeira Coordenada da Sua Jogada.");
        pos2 = getCasas.nextInt() - 1;
        System.out.println("Insira a Primeira Coordenada da Sua Jogada.");
        pos1 = getCasas.nextInt() - 1;

        if(pos1 >= 0 && pos1 <= 2 && pos2 >= 0 && pos2 <= 2) {
            if(this.verificarCasa(pos1, pos2)) {
                this.matrizTabuleiro[pos1][pos2] = comVez.getTipoCasa();
            } else {
                comVez.fezJogadaErrada();
                System.out.println("As Coordenadas Já Estão Ocupadas! A Vez Será Passada.");
            }
        } else {
            comVez.fezJogadaErrada();
            System.out.println("As Coordenadas Não Estão Corretas! A Vez Será Passada.");
        }
        comVez.setTemVez(false);
    }

    void imprimirTabuleiro() {
        System.out.print(" 1   2   3\n");
        int coord = 1;
        for (int i = 0, pos1 = 0; i < 5; i++) {
            for (int j = 0, pos2 = 0; j < 5; j++) {
                if(i % 2 == 0 && j % 2 == 0) {
                    System.out.print(" " + this.matrizTabuleiro[pos1][pos2]);
                    pos2++;
                } else if (i % 2 == 0) {
                    System.out.print(" ║");
                } else if (j % 2 == 0){
                    System.out.print("═══");
                } else {
                    System.out.print("╬");
                }
            }
            if(i % 2 == 0) {
                System.out.print("  " + coord);
                coord++;
            }
            if(i % 2 != 0) {
                pos1++;
            }
            System.out.print("\n");
        }
    }
}