package loja;

import java.time.LocalDate;
import java.util.LinkedList;

import loja.compra.DescontoCompra;

public class Compra {
    private LinkedList<Jogo> jogosSelecionados;
    private LocalDate data;

    public Compra(LocalDate data, LinkedList<Jogo> jogosSelecionados) {
        this.data = data;
        this.jogosSelecionados = jogosSelecionados;
    }

    public Compra(LocalDate data) {
        this.data = data;
        this.jogosSelecionados = new LinkedList<>();
    }

    public void adicionarJogo(Jogo jogo) {
        this.jogosSelecionados.add(jogo);
    }

    public Double valor() {
        Double valorBruto = this.valorBruto();
        return valorBruto - (valorBruto * this.valorDesconto());
    }

    public Double valorBruto() {
        return this.jogosSelecionados
                .stream()
                .map((jogo) -> jogo.valorFinalJogo())
                .reduce(0d, Double::sum);
    }

    public Double valorDesconto() {
        DescontoCompra desconto = new DescontoCompra(this);
        return desconto.calculaDesconto();
    }

    public LinkedList<Jogo> filtrarJogos() {
        return new LinkedList<>();
    }

    public LinkedList<Jogo> getJogos() {
        return this.jogosSelecionados;
    }
}
