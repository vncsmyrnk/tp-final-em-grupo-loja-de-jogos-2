package loja.compra;

import java.util.LinkedList;

import loja.Jogo;

public abstract class RegraDescontoCompra {
    public static final Double DESCONTO_GRUPO_1 = 0.1;
    public static final Double DESCONTO_GRUPO_2 = 0.2;

    public abstract boolean regraEhAplicavel(LinkedList<Jogo> jogos);

    public abstract Double valorDesconto();
}