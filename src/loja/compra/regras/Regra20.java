package loja.compra.regras;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import loja.Jogo;
import loja.compra.Regra;
import loja.jogo.*;

public class Regra20 extends Regra {
    public static final Double VALOR_DESCONTO = 0.2;

    public boolean regraEhAplicavel(LinkedList<Jogo> jogos) {
        return this.regraLancamentosEhAplicavel(jogos) ||
                this.regraPremiumEhAplicavel(jogos) ||
                this.regraPremimMaisOutroEhAplicavel(jogos) ||
                this.regraRegularEhAplicavel(jogos) ||
                this.regraRegularMaisOutroEhAplicavel(jogos);
    }

    public Double valorDesconto() {
        return VALOR_DESCONTO;
    }

    public boolean regraLancamentosEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Lancamento)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() >= 2;
    }

    public boolean regraPremiumEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Premium)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() >= 3;
    }

    public boolean regraPremimMaisOutroEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Premium)
                .collect(Collectors.toList());

        return jogosAplicaveis.size() >= 2 && jogos.size() >= 3;
    }

    public boolean regraRegularEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Regular)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() >= 5;
    }

    public boolean regraRegularMaisOutroEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Regular)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() == 3 && jogos.size() >= 4;
    }
}
