package loja;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

import loja.compra.DescontoCompra;

public class Compra implements Serializable {
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

    public String dados() {
        StringBuilder relat = new StringBuilder();
        relat.append("\tData: " + Util.formatarData(this.data) + "\n");
        for (Jogo jogo : this.jogosSelecionados) {
            relat.append(jogo.dados() + "\n");
        }
        relat.append("\tTotal compra: R$" + this.valor());
        return relat.toString();
    }

    @Override
    public String toString() {
        return "Data: " + Util.formatarData(this.data) + "; Valor compra: " + this.valor();
    }
}
