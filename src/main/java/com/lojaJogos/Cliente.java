package com.lojaJogos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Responsavel por controlar, administrar e calcular valores de compras
 */
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

    /**
     * Responsavel por descrever um cliente ou o seu tipo
     * 
     * @return String
     */
    public abstract String descricao();

    /**
     * Calcula o valor final a ser pago por uma compra considerando particularidades
     * de um cliente
     * 
     * @param Compra c
     * @return Double
     */
    public abstract Double valorFinalCompra(Compra c);

    /**
     * Adiciona uma compra ao cliente
     * 
     * @param Compra c
     */
    public void adicionarCompra(Compra c) {
        this.compras.add(c);
    }

    /**
     * Calcula o valor total gasto em todas as compras realizadas
     * 
     * @return
     */
    public Double valorTotal() {
        return this.compras
                .stream()
                .mapToDouble((compra) -> this.valorFinalCompra(compra))
                .sum();
    }

    /**
     * Calcula o total de compras realizadas
     * 
     * @return int
     */
    public int quantidadeCompras() {
        return this.compras.size();
    }

    /**
     * Calcula o valor medio gasto nas compras
     * 
     * @return Double
     */
    public Double valorMedio() {
        return valorTotal() / this.quantidadeCompras();
    }

    /**
     * Retorna um relatorio das compras realizadas
     * 
     * @return String
     */
    public String dados() {
        return this.dados(this.compras);
    }

    /**
     * Retorna um relatorio das compras realizadas filtrando por data
     * 
     * @return String
     */
    public String dados(LocalDate data) {
        return this.dados(this.historico(data));
    }

    /**
     * Retorna um relatorio das compras realizadas filtrando por categoria de jogo
     * 
     * @return String
     */
    public String dados(String descricaoCategoria) {
        return this.dados(this.historico(descricaoCategoria));
    }

    /**
     * Retorna um relatorio a partir das compras informadas
     * 
     * @param LinkedList<Compra> comprasCliente
     * @return String
     */
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

    /**
     * Obtem uma lista das compras realizadas
     * 
     * @return LinkedList<Compra>
     */
    public LinkedList<Compra> historico() {
        return this.compras;
    }

    /**
     * Obtem uma lista das compras realizadas em uma determinada data
     * 
     * @return LinkedList<Compra>
     */
    public LinkedList<Compra> historico(LocalDate data) {
        return this.compras
                .stream()
                .filter((compra) -> compra.getData().equals(data))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Obtem uma lista das compras realizadas que possuem uma determinada categoria
     * de jogo
     * 
     * @return LinkedList<Compra>
     */
    public LinkedList<Compra> historico(String descricaoCategoria) {
        return this.compras
                .stream()
                .filter((compra) -> compra.hasCategoriaJogo(descricaoCategoria))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Calcula quantas vezes um determinado jogo foi comprado considerando todas as
     * compras
     * 
     * @param Jogo jogo
     * @return
     */
    public int vezesJogoComprado(Jogo jogo) {
        return this.compras
                .stream()
                .mapToInt((compra) -> compra.vezesJogoComprado(jogo))
                .reduce(0, (subtotal, vezesComprado) -> subtotal + vezesComprado);
    }

    /**
     * Obtem o nome do cliente
     * 
     * @return String
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Obtem as compras realizadas por um cliente
     * 
     * @return LinkedList<Compra>
     */
    public LinkedList<Compra> getCompras() {
        return compras;
    }

    /**
     * Define as compras que foram realizadas
     * 
     * @param LinkedList<Compra> compras
     */
    public void setCompras(LinkedList<Compra> compras) {
        this.compras = compras;
    }

    /**
     * Obtem a senha do cliente
     * 
     * @return String
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Obtem o nome de usuario do cliente
     * 
     * @return String
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * Sobrescreve o metodo "toString" informando detalhes de um cliente
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Nome: " + this.nome + "; Usuário: " + this.nomeUsuario;
    }
}
