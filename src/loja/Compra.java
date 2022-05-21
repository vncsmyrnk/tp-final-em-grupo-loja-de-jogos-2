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
}
