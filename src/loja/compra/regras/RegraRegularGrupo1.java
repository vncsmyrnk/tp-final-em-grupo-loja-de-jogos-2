package loja.compra.regras;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import loja.Jogo;
import loja.compra.RegraDescontoCompra;
import loja.jogo.Regular;

public class RegraRegularGrupo1 extends RegraDescontoCompra {

    public boolean regraEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Regular)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() == 4;
    }

    public Double valorDesconto() {
        return RegraDescontoCompra.DESCONTO_GRUPO_1;
    }
}
