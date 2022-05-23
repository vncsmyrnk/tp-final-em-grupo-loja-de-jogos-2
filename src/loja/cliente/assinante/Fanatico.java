package loja.cliente.assinante;

import loja.cliente.ClienteAssinante;

public class Fanatico extends ClienteAssinante {

    public static final Double MENSALIDADE = 25d;
    public static final Double DESCONTO = 0.3;

    public Fanatico(String nome, String nomeUsuario, String senha) {
        super(nome, nomeUsuario, senha, MENSALIDADE, DESCONTO);
    }
}
