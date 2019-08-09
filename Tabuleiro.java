package IMD.LP;

import java.util.Arrays;

public class Tabuleiro {
    private char matrizTabuleiro[][];

    public Tabuleiro() {
        matrizTabuleiro = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizTabuleiro[i][j] = 'X';
            }
        }
    }

    public void zerarTabuleiro() {
        matrizTabuleiro = new char[3][3];
        Arrays.fill(matrizTabuleiro, ' ');
    }

    public boolean tabuleiroCompleto() {
        int cont = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(matrizTabuleiro[i][j] == 'X' || matrizTabuleiro[i][j] == 'O'){
                    cont++;
                }
            }
        }

        if(cont == 9){
            return true;
        } else {
            return false;
        }
    }

    public boolean verificarCasa(int pos1, int pos2) {
        if(matrizTabuleiro[pos1][pos2] == ' ') {
            return true;
        }
        return false;
    }

    public void preencherTabuleiro(int pos1, int pos2, Jogador comVez) {
        if(verificarCasa(pos1, pos2)) {
            matrizTabuleiro[pos1][pos2] = comVez.getTipoCasa();
            imprimirTabuleiro();
        } else {
            comVez.fezJogadaErrada();
        }
    }

    public void imprimirTabuleiro() {
        for (int i = 0, pos1 = 0; i < 5; i++) {
            for (int j = 0, pos2 = 0; j < 5; j++) {
                if(i % 2 == 0 && j % 2 == 0) {
                    System.out.print("" + matrizTabuleiro[pos1][pos2]);
                    pos2++;
                } else if (i % 2 == 0 && j % 2 != 0) {
                    System.out.print(" ║ ");
                } else if (i % 2 != 0 && j % 2 == 0){
                    System.out.print("═");
                } else {
                    System.out.print("═╬═");
                }
            }
            System.out.println("");
            if(i % 2 != 0) {
                pos1++;
            }
        }
    }
}
