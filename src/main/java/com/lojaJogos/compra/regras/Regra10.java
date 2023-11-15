package com.lojaJogos.compra.regras;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.lojaJogos.Jogo;
import com.lojaJogos.compra.Regra;
import com.lojaJogos.jogo.*;

/**
 * Representa uma regra que garante 10% de desconto caso aplicavel
 */
public class Regra10 extends Regra {
    public static final Double VALOR_DESCONTO = 0.1;

    /**
     * Determina se a regra e aplicavel considerando uma lista de jogos de uma
     * determinada compra
     * 
     * @param LinkedList<Jogo> jogos
     * @return boolean
     */
    public boolean regraEhAplicavel(LinkedList<Jogo> jogos) {
        return this.regraPremiumEhAplicavel(jogos) || this.regraRegularEhAplicavel(jogos);
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
     * Informa se a regra relativa aos jogos premium e aplicavel. A regra e
     * aplicavel caso existam 2 jogos premium
     * 
     * @param LinkedList<Jogo> jogos
     * @return boolean
     */
    public boolean regraPremiumEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Premium)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() == 2;
    }

    /**
     * Informa se a regra relativa aos jogos regular e aplicavel. A regra e
     * aplicavel caso existam 4 jogos regular
     * 
     * @param LinkedList<Jogo> jogos
     * @return boolean
     */
    public boolean regraRegularEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Regular)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() == 4;
    }
}
