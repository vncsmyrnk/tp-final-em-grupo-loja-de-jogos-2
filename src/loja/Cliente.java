package loja;

import java.time.LocalDate;
import java.util.LinkedList;

public abstract class Cliente {
    protected String nome;
    protected int categoria;
    private String nomeUsuario;
    private String senha;
    private LinkedList<Compra> compras;

    public Cliente(String nome, int categoria, String nomeUsuario, String senha) {
        this.nome = nome;
        this.categoria = categoria;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public void adicionarCompra(Compra c) {

    }

    public LinkedList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(LinkedList<Compra> compras) {
        this.compras = compras;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String historico() {

        return "HISTORICO DO CLIENTE\n" + this.toString();
    }

    public String historico(LocalDate data) {

        return "HISTORICO DO CLIENTE\n" + this.toString();
    }

    public String historico(String categoria) {

        return "HISTORICO DO CLIENTE\n" + this.toString();
    }

    public Double valorTotal() {
        return 0d;
    }

    @Override
    public String toString() {
        StringBuilder relat = new StringBuilder();
        relat.append("=====================\n");
        relat.append("Cliente==" + this.nome + "\n");
        for (Compra compra : this.compras) {
            relat.append(compra.toString() + "\n");
        }
        relat.append("=====================\n");
        relat.append("TOTAL DA COMPRA: R$" + this.valorTotal());
        return relat.toString();
    }
}
