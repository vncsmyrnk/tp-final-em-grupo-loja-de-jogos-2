package com.lojaJogos.cliente;

import com.lojaJogos.Cliente;
import com.lojaJogos.Compra;

/**
 * Representa um cliente Cadastrado
 */
public class Cadastrado extends Cliente {
    private String email;
    public static final String DESCRICAO = "Cadastrado";

    public Cadastrado(String nome, String nomeUsuario, String senha, String email) {
        super(nome, nomeUsuario, senha);
        this.email = email;
    }

    /**
     * Calcula o valor final de uma compra para o cliente Cadastrado
     * 
     * @param Compra c
     * @return Double
     */
    public Double valorFinalCompra(Compra c) {
        return c.valor();
    }

    /**
     * Descreve o cliente cadastrado
     * 
     * @return String
     */
    public String descricao() {
        return DESCRICAO;
    }

    /**
     * Realiza o envio de avisos por e-mail (nao implementado)
     */
    public void enviaAvisos() {

    }

    /**
     * Obtem o e-mail do cliente
     * 
     * @return String
     */
    public String getEmail() {
        return email;
    }
}
