package loja.jogo;

import loja.Jogo;

public class Regular extends Jogo {
    public static final String DESCRICAO = "Regular";

    public Regular(String nome, String descricao, Double precoOriginal, Double modificadorPreco) {
        super(nome, descricao, precoOriginal, modificadorPreco);
    }

    public String descricaoCategoria() {
        return DESCRICAO;
    }
}
