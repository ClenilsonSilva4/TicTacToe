package IMD.LP;

public class Jogador {
    private String nome;
    private char tipoCasa;
    private boolean casaErrada;

    public Jogador(String nomeJog, char tipoJog) {
        this.nome = nomeJog;
        this.tipoCasa = tipoJog;
        this.casaErrada = false;
    }

    public char getTipoCasa() {
        return this.tipoCasa;
    }

    public void fezJogadaErrada() {
        this.casaErrada = true;
    }

    public boolean getJogadaErrada(){
        return this.casaErrada;
    }

    public String getNome() {
        return this.nome;
    }
}
