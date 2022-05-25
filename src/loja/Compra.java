package loja;

import java.time.LocalDate;
import java.util.LinkedList;

import loja.compra.DescontoCompra;

public class Compra {
    private DescontoCompra desconto;
    private LinkedList<Jogo> jogosSelecionados;
    private LocalDate data;

    public Compra(LocalDate data, LinkedList<Jogo> jogosSelecionados) {
        this.data = data;
        this.jogosSelecionados = jogosSelecionados;
    }

    public Compra(LinkedList<Jogo> jogosSelecionados) {
        this.data = LocalDate.now();
        this.jogosSelecionados = jogosSelecionados;
    }

    public Compra(LocalDate data) {
        this.data = data;
        this.jogosSelecionados = new LinkedList<>();
    }

    public void adicionarJogo(Jogo jogo) {
        this.jogosSelecionados.add(jogo);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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
        this.desconto = new DescontoCompra(this.jogosSelecionados);
        return this.desconto.calculaDesconto();
    }

    public LinkedList<Jogo> filtrarJogos() {
        return new LinkedList<>();
    }

    public LinkedList<Jogo> getJogos() {
        return this.jogosSelecionados;
    }

    @Override
    public String toString() {
        StringBuilder relat = new StringBuilder();
        relat.append("=====================\n");
        relat.append("Compra== - " + Util.formatarData(this.data) + "\n");
        for (Jogo jogo : this.jogosSelecionados) {
            relat.append(jogo.toString() + "\n");
        }
        relat.append("=====================\n");
        relat.append("TOTAL DA COMPRA: R$" + this.valor());
        return relat.toString();
    }
}
