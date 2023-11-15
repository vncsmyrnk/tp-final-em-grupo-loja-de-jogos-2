package com.lojaJogos.cliente.assinante;

import com.lojaJogos.cliente.ClienteAssinante;

/**
 * Representa um cliente Fanatico
 */
public class Fanatico extends ClienteAssinante {
    public static final Double MENSALIDADE = 25d;
    public static final Double DESCONTO = 0.3;
    public static final String DESCRICAO = "Fanático";

    public Fanatico(String nome, String nomeUsuario, String senha) {
        super(nome, nomeUsuario, senha, MENSALIDADE, DESCONTO);
    }

    /**
     * Descreve o cliente Fanatico
     */
    public String descricao() {
        return DESCRICAO;
    }
}
