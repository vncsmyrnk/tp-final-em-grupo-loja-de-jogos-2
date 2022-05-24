package loja.compra;

import java.util.LinkedList;

import loja.Jogo;
import loja.compra.regras.*;

public class DescontoCompra {
    private LinkedList<Jogo> jogos;
    private LinkedList<RegraDescontoCompra> regrasDescontoCompra;
    private LinkedList<RegraDescontoCompra> regrasDescontoAplicaveis;

    public DescontoCompra(LinkedList<Jogo> jogos) {
        this.jogos = jogos;
        this.carregaRegrasDesconto();
    }

    public Double calculaDesconto() {
        for (RegraDescontoCompra regra : this.regrasDescontoCompra) {
            if (regra.regraEhAplicavel(this.jogos)) {
                this.regrasDescontoAplicaveis.add(regra);
            }
        }
        return this.regrasDescontoAplicaveis
                .stream()
                .mapToDouble((regra) -> regra.valorDesconto())
                .max()
                .orElse(0d);
    }

    public void carregaRegrasDesconto() {
        this.regrasDescontoCompra = new LinkedList<>();
        this.regrasDescontoAplicaveis = new LinkedList<>();
        this.regrasDescontoCompra.add(new RegraLancamentosGrupo2());
        this.regrasDescontoCompra.add(new RegraPremiumGrupo1());
        this.regrasDescontoCompra.add(new RegraPremiumGrupo2());
        this.regrasDescontoCompra.add(new RegraRegularGrupo2());
        this.regrasDescontoCompra.add(new RegraPremiumMaisOutroGrupo2());
        this.regrasDescontoCompra.add(new RegraRegularMaisOutroGrupo2());
        this.regrasDescontoCompra.add(new RegraRegularGrupo1());
    }
}
