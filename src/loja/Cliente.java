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

    public abstract Double valorFinalCompra(Compra c);

    public void adicionarCompra(Compra c) {
        this.compras.add(c);
    }

    public Double valorTotal() {
        return this.compras
                .stream()
                .mapToDouble((compra) -> this.valorFinalCompra(compra))
                .sum();
    }

    public int quantidadeCompras() {
        return this.compras.size();
    }

    public Double valorMedio() {
        return valorTotal() / this.quantidadeCompras();
    }

    public String dados() {
        return this.dados(this.compras);
    }

    public String dados(LocalDate data) {
        return this.dados(this.historico(data));
    }

    public String dados(String descricaoCategoria) {
        return this.dados(this.historico(descricaoCategoria));
    }

    private String dados(LinkedList<Compra> comprasCliente) {
        StringBuilder relat = new StringBuilder();
        relat.append("> " + this.toString() + " (" + this.descricao() + ")\n");
        for (Compra compra : comprasCliente) {
            relat.append("\t> Compra " + compra.hashCode() + "\n");
            relat.append(compra.dados() + "\n");
            relat.append("\t< Valor a ser pago: R$ " + this.valorFinalCompra(compra) + "\n");
            relat.append("\t< " + compra.hashCode() + "\n");
        }
        relat.append("< Total gasto: R$ " + this.valorTotal() + "\n");
        return relat.toString();
    }

    public LinkedList<Compra> historico() {
        return this.compras;
    }

    public LinkedList<Compra> historico(LocalDate data) {
        return this.compras
                .stream()
                .filter((compra) -> compra.getData().equals(data))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<Compra> historico(String descricaoCategoria) {
        return this.compras
                .stream()
                .filter((compra) -> compra.hasCategoriaJogo(descricaoCategoria))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public int vezesJogoComprado(Jogo jogo) {
        return this.compras
                .stream()
                .mapToInt((compra) -> compra.vezesJogoComprado(jogo))
                .reduce(0, (subtotal, vezesComprado) -> subtotal + vezesComprado);
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

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "; Usuário: " + this.nomeUsuario;
    }
}
