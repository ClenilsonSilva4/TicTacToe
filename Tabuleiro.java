package IMD.LP;

public class Tabuleiro{
    private char[][] matrizTabuleiro;

    public Tabuleiro() {
        this.matrizTabuleiro = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizTabuleiro[i][j] = ' ';
            }
        }
    }

    public void zerarTabuleiro() {
        this.matrizTabuleiro = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.matrizTabuleiro[i][j] = ' ';
            }
        }
    }

    public char tabuleiroCompleto() {
        int checkDiagPriX = 0, checkDiagPriO = 0, checkHorX = 0, checkHorO = 0, checkVerX = 0, checkVerO = 0, cont = 0,
            checkDiagSecX = 0, checkDiagSecO = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == j){
                    if(this.matrizTabuleiro[i][j] == 'X'){
                        checkDiagPriX++;
                    } else if(this.matrizTabuleiro[i][j] == 'O') {
                        checkDiagPriO++;
                    }
                }

                if(i == 0 && j == 2 || i == 2 && j == 0 || i == 1 && j == 1){
                    if(this.matrizTabuleiro[i][j] == 'X'){
                        checkDiagSecX++;
                    } else if(this.matrizTabuleiro[i][j] == 'O') {
                        checkDiagSecO++;
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
        if(checkDiagPriO == 3 || checkDiagSecO == 3) {
            return 'O';
        } else if(checkDiagPriX == 3 || checkDiagSecX == 3) {
            return 'X';
        } else if(cont == 9) {
            return 'D';
        }
        return ' ';
    }

    private boolean verificarCasa(int pos1, int pos2) {
        return this.matrizTabuleiro[pos1][pos2] == ' ';
    }

    public void preencherTabuleiro(Jogador comVez, int pos1, int pos2) {
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
}