package com.lojaJogos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import com.lojaJogos.jogo.Lancamento;
import com.lojaJogos.jogo.Premium;
import com.lojaJogos.jogo.Promocao;
import com.lojaJogos.jogo.Regular;

public class AppTest {
    @Test
    public void testAppMenu() {
        TestsUtil.suppressOutput();

        App.teclado = new Scanner(TestsUtil.provideInput("1\n"));
        int opcao = App.menu(App.teclado);
        assertEquals(1, opcao);
    }

    @Test
    public void testAppMenuCadastraJogoLancamento() {
        TestsUtil.suppressOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("1\nJogoLancamento\nDesc\n100\n0\n"));

        App.menuCadastraJogo();
        LinkedList<Jogo> jogosEsperados = new LinkedList<>(Arrays.asList(new Lancamento("JogoLancamento", "Desc", 100d)));
        LinkedList<Jogo> jogosLoja = App.loja.listarJogos();
        assertEquals(jogosEsperados, jogosLoja);
    }

    @Test
    public void testAppMenuCadastraJogoPremium() {
        TestsUtil.suppressOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("2\nJogoPremium\nDesc\n100\n0\n"));

        App.menuCadastraJogo();
        LinkedList<Jogo> jogosEsperados = new LinkedList<>(Arrays.asList(new Premium("JogoPremium", "Desc", 100d)));
        LinkedList<Jogo> jogosLoja = App.loja.listarJogos();
        assertEquals(jogosEsperados, jogosLoja);
    }

    @Test
    public void testAppMenuCadastraJogoRegular() {
        TestsUtil.suppressOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("3\nJogoRegular\nDesc\n100\n0\n100\n80\n\n0\n"));

        App.menuCadastraJogo();
        LinkedList<Jogo> jogosEsperados = new LinkedList<>(Arrays.asList(new Regular("JogoRegular", "Desc", 100d, 80d)));
        LinkedList<Jogo> jogosLoja = App.loja.listarJogos();
        assertEquals(jogosEsperados, jogosLoja);
    }

    @Test
    public void testAppMenuCadastraJogoPromocao() {
        TestsUtil.suppressOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("4\nJogoPromocao\nDesc\n100\n0\n40\n40\n\n0\n"));

        App.menuCadastraJogo();
        LinkedList<Jogo> jogosEsperados = new LinkedList<>(Arrays.asList(new Promocao("JogoPromocao", "Desc", 100d, 40d)));
        LinkedList<Jogo> jogosLoja = App.loja.listarJogos();
        assertEquals(jogosEsperados, jogosLoja);
    }

    @Test
    public void testAppMenuCadastraJogoOpCancelada() {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("0\n\n"));

        App.menuCadastraJogo();
        String outputEsperado = "Operação cancelada";
        assertTrue(output.toString().contains(outputEsperado));
    }
}
