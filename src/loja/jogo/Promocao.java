package loja.jogo;

import loja.Jogo;

/**
 * Representa um jogo Promocao
 */
public class Promocao extends Jogo {
    public static final String DESCRICAO = "Promoção";

    public Promocao(String nome, String descricao, Double precoOriginal, Double modificadorPreco) {
        super(nome, descricao, precoOriginal, modificadorPreco);
    }

    /**
     * Descreve a categoria do jogo Promocao
     * 
     * @return String
     */
    public String descricaoCategoria() {
        return DESCRICAO;
    }
}
