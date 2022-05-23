package loja;

import java.time.LocalDate;
import java.util.LinkedList;

public abstract class Cliente {
    protected String nome;
    private String nomeUsuario;
    private String senha;
    private LinkedList<Compra> compras;

    public Cliente(String nome, String nomeUsuario, String senha) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public void adicionarCompra(Compra c) {

    }

    public String historico() {
        return "";
    }

    public String historico(LocalDate data) {
        return "";
    }

    public String historico(String categoria) {
        return "";
    }

    public Double valorTotal() {
        return 0d;
    }
}
