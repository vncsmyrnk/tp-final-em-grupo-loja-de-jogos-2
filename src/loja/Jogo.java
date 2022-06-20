package loja;

import java.io.Serializable;

/**
 * Responsavel por representar um jogo e seus valores
 */
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

    /**
     * Descreve a categoria de um jogo
     * 
     * @return String
     */
    public abstract String descricaoCategoria();

    /**
     * Calcula o valor final de um jgo considerando seu preco original e
     * modificadores
     * 
     * @return Double
     */
    public final Double valorFinalJogo() {
        return this.precoOriginal + (this.precoOriginal * this.modificadorPreco);
    }

    /**
     * Retorna um relatorio com as principais informacoes de um jogo
     * 
     * @return String
     */
    public String dados() {
        return "\t\t\t- [" + this.descricaoCategoria() + "] Nome: " + this.nome + "; Preço original: "
                + this.precoOriginal + "; Valor: R$ " + this.valorFinalJogo()
                + "; Descrição: "
                + this.descricao;
    }

    /**
     * Compra se um determinado jogo possui a mesma categoria que o atual
     * 
     * @param Jogo jogo
     * @return boolean
     */
    public boolean isMesmaCategoria(Jogo jogo) {
        return this.descricaoCategoria().equals(jogo.descricaoCategoria());
    }

    /**
     * Obtem o nome de um jogo
     * 
     * @return String
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Sobrescreve o metodo "toString" informando detalhes de um jogo
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.descricaoCategoria() + "] Nome do jogo: " + this.nome + "; Preço original: "
                + this.precoOriginal + "; Valor: R$ " + this.valorFinalJogo()
                + "; Descrição: "
                + this.descricao;
    }

    /**
     * Sobrescreve o metodo "equals" comparando jogos por nome
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        Jogo jogo = (Jogo) o;
        if (jogo == null) {
            return false;
        }
        return this.nome.equals(jogo.getNome());
    }
}
