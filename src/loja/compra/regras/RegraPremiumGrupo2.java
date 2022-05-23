package loja.compra.regras;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import loja.Jogo;
import loja.compra.RegraDescontoCompra;
import loja.jogo.Premium;

public class RegraPremiumGrupo2 extends RegraDescontoCompra {

    public RegraPremiumGrupo2(LinkedList<Jogo> jogos) {
        super(jogos);
    }

    public boolean regraEhAplicavel() {
        List<Jogo> jogosAplicaveis = this.jogos
                .stream()
                .filter((jogo) -> jogo instanceof Premium)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() >= 3;
    }

    public Double valorDesconto() {
        return RegraDescontoCompra.DESCONTO_GRUPO_2;
    }
}
