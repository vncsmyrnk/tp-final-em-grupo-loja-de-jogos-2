package loja.cliente.assinante;

import loja.cliente.ClienteAssinante;

public class Empolgado extends ClienteAssinante {

    public static final Double MENSALIDADE = 10d;
    public static final Double DESCONTO = 0.1;
    public static final String DESCRICAO = "Empolgado";

    public Empolgado(String nome, String nomeUsuario, String senha) {
        super(nome, nomeUsuario, senha, MENSALIDADE, DESCONTO);
    }

    public String descricao() {
        return DESCRICAO;
    }
}
