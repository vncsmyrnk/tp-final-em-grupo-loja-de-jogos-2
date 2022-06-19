package loja.jogo;

import loja.Jogo;

public class Promocao extends Jogo {
    public static final String DESCRICAO = "Promoção";

    public Promocao(String nome, String descricao, Double precoOriginal, Double modificadorPreco) {
        super(nome, descricao, precoOriginal, modificadorPreco);
    }

    public String descricaoCategoria() {
        return DESCRICAO;
    }
}
