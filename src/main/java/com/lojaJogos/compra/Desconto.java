package com.lojaJogos.compra;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

import com.lojaJogos.Jogo;
import com.lojaJogos.compra.regras.*;

/**
 * Responsavel por calcular o deconto a ser aplicado em uma determinada compra
 */
public class Desconto implements Serializable {
    private LinkedList<Jogo> jogos;
    private LinkedList<Regra> regras;

    public Desconto(LinkedList<Jogo> jogos) {
        this.jogos = jogos;
        this.carregaRegrasDesconto();
    }

    /**
     * Calcula o desconto a ser aplicado em uma compra considerando o maior desconto
     * possivel
     * 
     * @return Double
     */
    public Double calculaDesconto() {
        Collections.sort(this.regras, Collections.reverseOrder());
        for (Regra regra : this.regras) {
            if (regra.regraEhAplicavel(this.jogos)) {
                return regra.valorDesconto();
            }
        }
        return 0d;
    }

    /**
     * Indica as regras a serem consideradas no calculo
     */
    public void carregaRegrasDesconto() {
        this.regras = new LinkedList<>();
        this.regras.push(new Regra10());
        this.regras.push(new Regra20());
    }
}
