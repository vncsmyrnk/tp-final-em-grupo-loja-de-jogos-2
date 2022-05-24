package loja.compra.regras;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import loja.Jogo;
import loja.compra.RegraDescontoCompra;
import loja.jogo.Premium;

public class RegraPremiumGrupo2 extends RegraDescontoCompra {

    public boolean regraEhAplicavel(LinkedList<Jogo> jogos) {
        List<Jogo> jogosAplicaveis = jogos
                .stream()
                .filter((jogo) -> jogo instanceof Premium)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() >= 3;
    }

    public Double valorDesconto() {
        return RegraDescontoCompra.DESCONTO_GRUPO_2;
    }
}
