package loja;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public abstract class Cliente implements Serializable {
    protected String nome;
    private String nomeUsuario;
    private String senha;
    private LinkedList<Compra> compras;

    public Cliente(String nome, String nomeUsuario, String senha) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.compras = new LinkedList<>();
    }

    public void adicionarCompra(Compra c) {
        this.compras.add(c);
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
        return this.compras
                .stream()
                .mapToDouble((compra) -> compra.valor())
                .sum();
    }

    public String getNome() {
        return this.nome;
    }

    public String dados() {
        StringBuilder relat = new StringBuilder();
        relat.append("Nome cliente: " + this.nome + "\n");
        for (Compra compra : this.compras) {
            relat.append(compra.dados() + "\n");
        }
        relat.append("Total gasto: R$" + this.valorTotal() + "\n");
        return relat.toString();
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "; Usuário: " + this.nomeUsuario;
    }
}
