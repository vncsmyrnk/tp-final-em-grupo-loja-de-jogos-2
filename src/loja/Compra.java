package loja;

import java.time.LocalDate;
import java.util.LinkedList;

public class Compra {
    private LinkedList<Jogo> jogosSelecionados;
    private LocalDate Data;

    public Double valor() {
        return 0d;
    }

    public Double valorDesconto() {
        return 0d;
    }

    public LinkedList<Jogo> filtrarJogos() {
        return new LinkedList<>();
    }


    @Override
    public String toString(){
        StringBuilder relat = new StringBuilder();
        relat.append("=====================\n");
        relat.append("Compra== "+this.idCompra+" - "+this.dataCompra.formatarData()+"\n");
        for(Jogo jogo : this.jogosSelecionados) {
            relat.append(jogo.toString()+"\n");
        }
        relat.append("=====================\n");
        relat.append("TOTAL DA COMPRA: R$"+this.valorTotal());
        return relat.toString();
    }
}
