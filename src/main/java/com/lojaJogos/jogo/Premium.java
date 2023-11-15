package com.lojaJogos.jogo;

import com.lojaJogos.Jogo;


/**
 * Representa um jogo Premium
 */
public class Premium extends Jogo {
    public static final Double PREMIUM_MODIFICADOR_PRECO = 0d;
    public static final String DESCRICAO = "Premium";

    public Premium(String nome, String descricao, Double precoOriginal) {
        super(nome, descricao, precoOriginal, PREMIUM_MODIFICADOR_PRECO);
    }

    /**
     * Descreve a categoria do jogo Premium
     * 
     * @return String
     */
    public String descricaoCategoria() {
        return DESCRICAO;
    }
}
