package IMD.LP;

public class Jogador {
    private String nome;
    private char tipoCasa;
    private boolean casaErrada;
    private boolean temVez;

    public Jogador(String nomeJog, char tipoJog) {
        this.nome = nomeJog;
        this.tipoCasa = tipoJog;
        this.casaErrada = false;
        this.temVez = false;
    }

    public String getNome() {
        return nome;
    }

    public char getTipoCasa() {
        return tipoCasa;
    }

    public void setTemVez(boolean temVez) {
        this.temVez = temVez;
    }

    public boolean getJogadaErrada() {
        return casaErrada;
    }

    public void fezJogadaErrada() {
        this.casaErrada = true;
    }

    public boolean getTemVez() {
        return temVez;
    }
}
