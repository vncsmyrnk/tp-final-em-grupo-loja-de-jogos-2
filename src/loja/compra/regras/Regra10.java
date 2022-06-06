package loja.compra.regras;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import loja.Jogo;
import loja.compra.Regra;
import loja.jogo.*;

public class Regra10 extends Regra {
    public static final Double VALOR_DESCONTO = 0.1;

    public boolean regraEhAplicavel(LinkedList<Jogo> jogos) {
        return this.regraPremiumEhAplicavel(jogos) || this.regraRegularEhAplicavel(jogos);
    }

    public Double valorDesconto() {
        return VALOR_DESCONTO;
    }

    public boolean regraPremiumEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Premium)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() == 2;
    }

    public boolean regraRegularEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Regular)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() == 4;
    }
}
