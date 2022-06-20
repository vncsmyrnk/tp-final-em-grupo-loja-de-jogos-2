package loja.compra;

import java.io.Serializable;
import java.util.LinkedList;

import loja.Jogo;

/**
 * Representa uma regra de desconto a ser aplicada em uma compra
 */
public abstract class Regra implements Comparable<Regra>, Serializable {
    /**
     * Determina se a regra e aplicavel considerando uma lista de jogos de uma
     * determinada compra
     * 
     * @param LinkedList<Jogo> jogos
     * @return boolean
     */
    public abstract boolean regraEhAplicavel(LinkedList<Jogo> jogos);

    /**
     * Obtem o valor a ser descontado
     * 
     * @return Double
     */
    public abstract Double valorDesconto();

    /**
     * Compara uma regra a outra a partir dos valores de desconto
     * 
     * @param Regra r
     * @return int
     */
    public int compareTo(Regra r) {
        return (int) (this.valorDesconto() * 100 - r.valorDesconto() * 100);
    }
}
