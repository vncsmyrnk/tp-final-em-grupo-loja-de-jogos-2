package loja;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

import loja.compra.Desconto;

/**
 * Responsavel por controlar administrar e calcular valores de jogos
 */
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

    /**
     * Adiciona um jogo a compra
     * 
     * @param Jogo jogo
     */
    public void adicionarJogo(Jogo jogo) {
        this.jogosSelecionados.add(jogo);
    }

    /**
     * Calcula o valor final de uma compra considerando descontos
     * 
     * @return Double
     */
    public Double valor() {
        Double valorBruto = this.valorBruto();
        return valorBruto - (valorBruto * this.valorDesconto());
    }

    /**
     * Calcula o valor bruto de uma compra
     * 
     * @return Double
     */
    public Double valorBruto() {
        return this.jogosSelecionados
                .stream()
                .map((jogo) -> jogo.valorFinalJogo())
                .reduce(0d, Double::sum);
    }

    /**
     * Calcula o valor do desconto a ser aplicado em uma compra
     * 
     * @return Double
     */
    public Double valorDesconto() {
        return this.desconto.calculaDesconto();
    }

    /**
     * Retorna um relatorio dos valores de uma compra e dos jogos adquiridos
     * 
     * @return String
     */
    public String dados() {
        StringBuilder relat = new StringBuilder();
        relat.append("\t\t+ Data: " + Util.formatarData(this.data) + "\n");
        for (Jogo jogo : this.jogosSelecionados) {
            relat.append(jogo.dados() + "\n");
        }
        relat.append("\t\t+ Total compra: R$ " + this.valor());
        return relat.toString();
    }

    /**
     * Informa se existem jogos de uma determinada categoria na compra
     * 
     * @param String descricaoCategoria
     * @return boolean
     */
    public boolean hasCategoriaJogo(String descricaoCategoria) {
        return this.jogosSelecionados
                .stream()
                .map(Jogo::descricaoCategoria)
                .anyMatch((d) -> d.equals(descricaoCategoria));
    }

    /**
     * Calcula quantas vezes um deteminado jogo foi adquirido na compra
     * 
     * @param Jogo jogo
     * @return int
     */
    public int vezesJogoComprado(Jogo jogo) {
        long count = this.jogosSelecionados
                .stream()
                .filter((j) -> j.equals(jogo))
                .count();
        return (int) count;
    }

    /**
     * Obtem os jogos comprados
     * 
     * @return LinkedList<Jogo>
     */
    public LinkedList<Jogo> getJogos() {
        return this.jogosSelecionados;
    }

    /**
     * Obtem a data da compra
     * 
     * @return LocalDate
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Define a data da compra
     * 
     * @param LocalDate data
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Sobrescreve o metodo "toString" informando detalhes de uma compra
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Data: " + Util.formatarData(this.data) + "; Valor compra: " + this.valor();
    }
}
