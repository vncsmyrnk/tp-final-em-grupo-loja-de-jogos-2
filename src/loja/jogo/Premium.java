package loja.jogo;

import loja.Jogo;

public class Premium extends Jogo {
    public static final Double PREMIUM_MODIFICADOR_PRECO = 0d;
    public static final String DESCRICAO = "Premium";

    public Premium(String nome, String descricao, Double precoOriginal) {
        super(nome, descricao, precoOriginal, PREMIUM_MODIFICADOR_PRECO);
    }

    public String descricaoCategoria() {
        return DESCRICAO;
    }
}
