package loja.compra.regras;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import loja.Jogo;
import loja.compra.Regra;
import loja.jogo.*;

/**
 * Representa uma regra que garante 20% de desconto caso aplicavel
 */
public class Regra20 extends Regra {
    public static final Double VALOR_DESCONTO = 0.2;

    /**
     * Determina se a regra e aplicavel considerando uma lista de jogos de uma
     * determinada compra
     * 
     * @param LinkedList<Jogo> jogos
     * @return boolean
     */
    public boolean regraEhAplicavel(LinkedList<Jogo> jogos) {
        return this.regraLancamentosEhAplicavel(jogos) ||
                this.regraPremiumEhAplicavel(jogos) ||
                this.regraPremimMaisOutroEhAplicavel(jogos) ||
                this.regraRegularEhAplicavel(jogos) ||
                this.regraRegularMaisOutroEhAplicavel(jogos);
    }

    /**
     * Obtem o valor a ser descontado
     * 
     * @return Double
     */
    public Double valorDesconto() {
        return VALOR_DESCONTO;
    }

    /**
     * Informa se a regra relativa aos jogos lancamento e aplicavel. A regra e
     * aplicavel caso existam 2 ou mais jogos lancamento
     * 
     * @param LinkedList<Jogo> jogos
     * @return boolean
     */
    public boolean regraLancamentosEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Lancamento)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() >= 2;
    }

    /**
     * Informa se a regra relativa aos jogos premium e aplicavel. A regra e
     * aplicavel caso existam 3 ou mais jogos premium
     * 
     * @param LinkedList<Jogo> jogos
     * @return boolean
     */
    public boolean regraPremiumEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Premium)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() >= 3;
    }

    /**
     * Informa se a regra relativa aos jogos premium e aplicavel. A regra e
     * aplicavel caso existam 2 ou mais jogos premium e outro qualquer
     * 
     * @param LinkedList<Jogo> jogos
     * @return boolean
     */
    public boolean regraPremimMaisOutroEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Premium)
                .collect(Collectors.toList());

        return jogosAplicaveis.size() >= 2 && jogos.size() >= 3;
    }

    /**
     * Informa se a regra relativa aos jogos regular e aplicavel. A regra e
     * aplicavel caso existam 5 ou mais jogos regular
     * 
     * @param LinkedList<Jogo> jogos
     * @return boolean
     */
    public boolean regraRegularEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Regular)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() >= 5;
    }

    /**
     * Informa se a regra relativa aos jogos regular e aplicavel. A regra e
     * aplicavel caso existam 3 jogos regular e outro qualquer
     * 
     * @param LinkedList<Jogo> jogos
     * @return boolean
     */
    public boolean regraRegularMaisOutroEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Regular)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() == 3 && jogos.size() >= 4;
    }
}
