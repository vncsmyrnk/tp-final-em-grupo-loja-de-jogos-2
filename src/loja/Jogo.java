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

    public abstract String descricaoCategoria();

    public final Double valorFinalJogo() {
        return this.precoOriginal + (this.precoOriginal * this.modificadorPreco);
    }

    public String getNome() {
        return this.nome;
    }

    public String dados() {
        return "\t\t\t- [" + this.descricaoCategoria() + "] Nome: " + this.nome + "; Preço original: "
                + this.precoOriginal + "; Valor: R$ " + this.valorFinalJogo()
                + "; Descrição: "
                + this.descricao;
    }

    public boolean isMesmaCategoria(Jogo jogo) {
        return this.descricaoCategoria().equals(jogo.descricaoCategoria());
    }

    @Override
    public String toString() {
        return "[" + this.descricaoCategoria() + "] Nome do jogo: " + this.nome + "; Preço original: "
                + this.precoOriginal + "; Valor: R$ " + this.valorFinalJogo()
                + "; Descrição: "
                + this.descricao;
    }

    @Override
    public boolean equals(Object o) {
        Jogo jogo = (Jogo) o;
        if (jogo == null) {
            return false;
        }
        return this.nome.equals(jogo.getNome());
    }
}
