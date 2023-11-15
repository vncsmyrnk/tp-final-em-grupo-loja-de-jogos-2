package com.lojaJogos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lojaJogos.cliente.Cadastrado;
import com.lojaJogos.jogo.Lancamento;
import com.lojaJogos.jogo.Regular;

@DisplayName("Testes de cliente")
public class ClienteTest {

    @Test
    @DisplayName("Custom test name containing spaces")
    public void testClienteCadastradoValorCompraRegular() {
        Cadastrado c = new Cadastrado("Usuario1", "u1", "1", "u1@exemplo.com");
        LinkedList<Jogo> jogosCompra = new LinkedList<>(Arrays.asList(new Regular("Jogo1", "desc", 100.0, 0.0)));
        Double actual = c.valorFinalCompra(new Compra(LocalDate.now(), jogosCompra));
        Double expected = 100.0;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Custom test name containing spaces")
    public void testClienteCadastradoValorCompraLancamento() {
        Cadastrado c = new Cadastrado("Usuario1", "u1", "1", "u1@exemplo.com");
        LinkedList<Jogo> jogosCompra = new LinkedList<>(Arrays.asList(new Lancamento("Jogo1", "desc", 100.0)));
        Double actual = c.valorFinalCompra(new Compra(LocalDate.now(), jogosCompra));
        Double expected = 110.0;
        assertEquals(expected, actual);
    }
}
