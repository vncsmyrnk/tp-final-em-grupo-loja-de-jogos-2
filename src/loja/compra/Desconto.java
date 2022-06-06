package loja.compra;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

import loja.Jogo;
import loja.compra.regras.*;

public class Desconto implements Serializable {
    private LinkedList<Jogo> jogos;
    private LinkedList<Regra> regras;

    public Desconto(LinkedList<Jogo> jogos) {
        this.jogos = jogos;
        this.carregaRegrasDesconto();
    }

    public Double calculaDesconto() {
        Collections.sort(this.regras, Collections.reverseOrder());
        for (Regra regra : this.regras) {
            if (regra.regraEhAplicavel(this.jogos)) {
                return regra.valorDesconto();
            }
        }
        return 0d;
    }

    public void carregaRegrasDesconto() {
        this.regras = new LinkedList<>();
        this.regras.push(new Regra10());
        this.regras.push(new Regra20());
    }
}
