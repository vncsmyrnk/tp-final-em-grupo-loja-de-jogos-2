package loja.compra.regras;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import loja.Jogo;
import loja.compra.RegraDescontoCompra;
import loja.jogo.Lancamento;

public class RegraLancamentosGrupo2 extends RegraDescontoCompra {

    public RegraLancamentosGrupo2(LinkedList<Jogo> jogos) {
        super(jogos);
    }

    public boolean regraEhAplicavel() {
        List<Jogo> jogosAplicaveis = this.jogos
                .stream()
                .filter((jogo) -> jogo instanceof Lancamento)
                .collect(Collectors.toList());
        return jogosAplicaveis.size() >= 2;
    }

    public Double valorDesconto() {
        return RegraDescontoCompra.DESCONTO_GRUPO_2;
    }
}
