package loja;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

import loja.compra.Desconto;

public class Compra implements Serializable {
    private Desconto desconto;
    private LinkedList<Jogo> jogosSelecionados;
    private LocalDate data;

    public Compra(LocalDate data, LinkedList<Jogo> jogosSelecionados) {
        this.data = data;
        this.jogosSelecionados = jogosSelecionados;
        this.desconto = new Desconto(jogosSelecionados);
    }

    public Compra(LinkedList<Jogo> jogosSelecionados) {
        this.data = LocalDate.now();
        this.jogosSelecionados = jogosSelecionados;
        this.desconto = new Desconto(jogosSelecionados);
    }

    public Compra(LocalDate data) {
        this.data = data;
        this.jogosSelecionados = new LinkedList<>();
        this.desconto = new Desconto(jogosSelecionados);
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
        relat.append("\t\t+ Data: " + Util.formatarData(this.data) + "\n");
        for (Jogo jogo : this.jogosSelecionados) {
            relat.append(jogo.dados() + "\n");
        }
        relat.append("\t\t+ Total compra: R$ " + this.valor());
        return relat.toString();
    }

    public boolean hasCategoriaJogo(String descricaoCategoria) {
        return this.jogosSelecionados
                .stream()
                .map(Jogo::descricaoCategoria)
                .anyMatch((d) -> d.equals(descricaoCategoria));
    }

    public int vezesJogoComprado(Jogo jogo) {
        long count = this.jogosSelecionados
                .stream()
                .filter((j) -> j.equals(jogo))
                .count();
        return (int) count;
    }

    @Override
    public String toString() {
        return "Data: " + Util.formatarData(this.data) + "; Valor compra: " + this.valor();
    }
}
