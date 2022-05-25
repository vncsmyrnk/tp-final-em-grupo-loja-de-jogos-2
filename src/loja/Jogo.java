package loja;

import java.io.Serializable;

public abstract class Jogo implements Serializable {
    protected String nome;
    protected String descricao;
    protected Double precoOriginal;
    protected Double modificadorPreco;
    protected String categoria;

    public Jogo(String nome, String descricao, Double precoOriginal, Double modificadorPreco) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoOriginal = precoOriginal;
        this.modificadorPreco = modificadorPreco;
    }

    public final Double valorFinalJogo() {
        return this.precoOriginal + (this.precoOriginal * this.modificadorPreco);
    }

    public String getNome() {
        return this.nome;
    }

    public String dados() {
        return "\t\tNome do jogo: " + this.nome + "; Valor: " + this.valorFinalJogo() + "; Descrição: "
                + this.descricao;
    }

    @Override
    public String toString() {
        return "Nome do jogo: " + this.nome + "; Valor: " + this.valorFinalJogo() + "; Descrição: "
                + this.descricao;
    }
}
