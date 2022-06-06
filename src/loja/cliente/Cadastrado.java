package loja.cliente;

import loja.Cliente;
import loja.Compra;

public class Cadastrado extends Cliente {
    private String email;
    public static final String DESCRICAO = "Cadastrado";

    public Cadastrado(String nome, String nomeUsuario, String senha, String email) {
        super(nome, nomeUsuario, senha);
        this.email = email;
    }

    public String descricao() {
        return DESCRICAO;
    }

    public void enviaAvisos() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double valorFinalCompra(Compra c) {
        return c.valor();
    }
}
