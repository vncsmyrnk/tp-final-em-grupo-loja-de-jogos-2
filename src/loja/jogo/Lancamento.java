package loja.jogo;

import loja.Jogo;

/**
 * Representa um jogo Lancamento
 */
public class Lancamento extends Jogo {
    public static final Double LANCAMENTO_MODIFICADOR_PRECO = 0.1d;
    public static final String DESCRICAO = "Lançamento";

    public Lancamento(String nome, String descricao, Double precoOriginal) {
        super(nome, descricao, precoOriginal, LANCAMENTO_MODIFICADOR_PRECO);
    }

    /**
     * Descreve a categoria do jogo Lancamento
     * 
     * @return String
     */
    public String descricaoCategoria() {
        return DESCRICAO;
    }
}
