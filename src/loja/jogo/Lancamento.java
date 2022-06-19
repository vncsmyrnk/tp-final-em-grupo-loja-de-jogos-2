package loja.jogo;

import loja.Jogo;

public class Lancamento extends Jogo {
    public static final Double LANCAMENTO_MODIFICADOR_PRECO = 0.1d;
    public static final String DESCRICAO = "Lançamento";

    public Lancamento(String nome, String descricao, Double precoOriginal) {
        super(nome, descricao, precoOriginal, LANCAMENTO_MODIFICADOR_PRECO);
    }

    public String descricaoCategoria() {
        return DESCRICAO;
    }
}
