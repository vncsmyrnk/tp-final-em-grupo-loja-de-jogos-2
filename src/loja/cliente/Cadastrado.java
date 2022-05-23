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

    public Double valorFinalCompra(Compra c) {
        return c.valor();
    }
}
