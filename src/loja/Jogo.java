package loja;

public abstract class Jogo {
    protected String nome;
    protected String descricao;
    protected Double precoOriginal;
    protected Double modificadorPreco;
    protected String descricaoCategoria;

    public Jogo(String nome, String descricao, Double precoOriginal, Double modificadorPreco) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoOriginal = precoOriginal;
        this.modificadorPreco = modificadorPreco;
    }

    public final Double valorFinalJogo() {
        return this.precoOriginal + (this.precoOriginal * this.modificadorPreco);
    }

    @Override
    public String toString() {
        StringBuilder relat = new StringBuilder();
        relat.append("Nome do jogo== " + this.descricaoCategoria + " - " + this.valorFinalJogo() + "\n");
        return relat.toString();
    }
}
