package loja;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.stream.Collectors;

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

    public abstract String descricao();

    public void adicionarCompra(Compra c) {
        this.compras.add(c);
    }

    public Double valorTotal() {
        return this.compras
                .stream()
                .mapToDouble((compra) -> compra.valor())
                .sum();
    }

    public String dados() {
        return this.dados(this.compras);
    }

    public String dados(LocalDate data) {
        LinkedList<Compra> comprasFiltrado = this.compras
                .stream()
                .filter((compra) -> compra.getData().equals(data))
                .collect(Collectors.toCollection(LinkedList::new));

        return this.dados(comprasFiltrado);
    }

    private String dados(LinkedList<Compra> comprasCliente) {
        StringBuilder relat = new StringBuilder();
        relat.append("> " + this.toString() + " ( " + this.descricao() + "\n");
        for (Compra compra : comprasCliente) {
            relat.append("\t-- Compra " + compra.hashCode() + " --\n");
            relat.append(compra.dados() + "\n");
            relat.append("\t-- " + compra.hashCode() + " --\n");
        }
        relat.append("< Total gasto: R$" + this.valorTotal() + "\n");
        return relat.toString();
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

    public String getNome() {
        return this.nome;
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

    @Override
    public String toString() {
        return "Nome: " + this.nome + "; Usuário: " + this.nomeUsuario;
    }
}
