package IMD.LP;

import java.util.Arrays;

public class Tabuleiro {
    private char matrizTabuleiro[][];

    public Tabuleiro() {
        matrizTabuleiro = new char[3][3];
        Arrays.fill(matrizTabuleiro, ' ');
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
        }

        return false;
    }

    public void preencherTabuleiro(char movimento)
}
