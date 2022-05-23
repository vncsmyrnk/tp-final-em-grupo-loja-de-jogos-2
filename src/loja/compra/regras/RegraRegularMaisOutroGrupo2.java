package loja.compra.regras;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import loja.Jogo;
import loja.compra.RegraDescontoCompra;
import loja.jogo.Regular;

public class RegraRegularMaisOutroGrupo2 extends RegraDescontoCompra {
    public RegraRegularMaisOutroGrupo2(LinkedList<Jogo> jogos) {
        super(jogos);
    }

    public boolean regraEhAplicavel() {
        List<Jogo> jogosAplicaveis = this.jogos
                .stream()
                .filter((jogo) -> jogo instanceof Regular)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() == 3 && this.jogos.size() >= 4;
    }

    public Double valorDesconto() {
        return RegraDescontoCompra.DESCONTO_GRUPO_2;
    }
}
