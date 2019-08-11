package IMD.LP;

class Jogador {
    private String nome;
    private char tipoCasa;
    private boolean casaErrada;
    private boolean temVez;

    Jogador(String nomeJog, char tipoJog) {
        this.nome = nomeJog;
        this.tipoCasa = tipoJog;
        this.casaErrada = false;
        this.temVez = false;
    }

    char getTipoCasa() {
        return this.tipoCasa;
    }

    void fezJogadaErrada() {
        this.casaErrada = true;
    }

    void setTemVez(boolean cond) {
        this.temVez = cond;
    }

    boolean getTemVez() {
        return this.temVez;
    }

    boolean getJogadaErrada(){
        return this.casaErrada;
    }

    String getNome() {
        return this.nome;
    }
}
