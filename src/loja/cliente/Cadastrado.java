package loja.cliente;

import loja.Cliente;
import loja.Compra;

public class Cadastrado extends Cliente implements ClienteCalculavelValorFinal {
    private String email;

    public Cadastrado(String nome, String nomeUsuario, String senha, String email) {
        super(nome, nomeUsuario, senha);
        this.email = email;
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
