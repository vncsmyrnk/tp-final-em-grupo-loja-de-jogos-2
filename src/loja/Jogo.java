package loja;

public abstract class Jogo {
    protected double precoOriginal;
    protected double modificadorPreco;
    protected String descricaoCategoria;

    public abstract double valorFinalJogo();
}
