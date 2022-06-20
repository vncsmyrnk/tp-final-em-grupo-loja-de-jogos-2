package loja.jogo;

import loja.Jogo;

/**
 * Representa um jogo Regular
 */
public class Regular extends Jogo {
    public static final String DESCRICAO = "Regular";

    public Regular(String nome, String descricao, Double precoOriginal, Double modificadorPreco) {
        super(nome, descricao, precoOriginal, modificadorPreco);
    }

    /**
     * Descreve a categoria do jogo Regular
     * 
     * @return String
     */
    public String descricaoCategoria() {
        return DESCRICAO;
    }
}
