package loja;

import java.io.Serializable;

public abstract class Jogo implements Serializable {
    protected String nome;
    protected String descricao;
    protected Double precoOriginal;
    protected Double modificadorPreco;

    public Jogo(String nome, String descricao, Double precoOriginal, Double modificadorPreco) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoOriginal = precoOriginal;
        this.modificadorPreco = modificadorPreco;
    }

    public abstract String descricao();

    public final Double valorFinalJogo() {
        return this.precoOriginal + (this.precoOriginal * this.modificadorPreco);
    }

    public String getNome() {
        return this.nome;
    }

    public String dados() {
        return "\t\t\t- [" + this.descricao() + "] Nome: " + this.nome + "; Valor: R$ " + this.valorFinalJogo()
                + "; Descrição: "
                + this.descricao;
    }

    @Override
    public String toString() {
        return "[" + this.descricao() + "] Nome do jogo: " + this.nome + "; Valor: R$ " + this.valorFinalJogo()
                + "; Descrição: "
                + this.descricao;
    }
}
