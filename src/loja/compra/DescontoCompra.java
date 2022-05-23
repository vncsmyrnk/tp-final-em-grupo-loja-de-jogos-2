package loja.compra;

import java.util.LinkedList;

import loja.Compra;
import loja.Jogo;
import loja.compra.regras.*;

public class DescontoCompra {
    private Compra compra;
    private LinkedList<RegraDescontoCompra> regrasDescontoCompra;
    private LinkedList<RegraDescontoCompra> regrasDescontoAplicaveis;

    public DescontoCompra(Compra compra) {
        this.compra = compra;
        this.carregaRegrasDesconto();
    }

    public Double calculaDesconto() {
        for (RegraDescontoCompra regra : this.regrasDescontoCompra) {
            if (regra.regraEhAplicavel()) {
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
        LinkedList<Jogo> jogosCompra = this.jogosCompra();
        this.regrasDescontoCompra.add(new RegraLancamentosGrupo2(jogosCompra));
        this.regrasDescontoCompra.add(new RegraPremiumGrupo1(jogosCompra));
        this.regrasDescontoCompra.add(new RegraPremiumGrupo2(jogosCompra));
        this.regrasDescontoCompra.add(new RegraRegularGrupo2(jogosCompra));
        this.regrasDescontoCompra.add(new RegraPremiumMaisOutroGrupo2(jogosCompra));
        this.regrasDescontoCompra.add(new RegraRegularMaisOutroGrupo2(jogosCompra));
        this.regrasDescontoCompra.add(new RegraRegularGrupo1(jogosCompra));
    }

    private LinkedList<Jogo> jogosCompra() {
        return this.compra.getJogos();
    }
}
