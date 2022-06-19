package testes;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.LinkedList;

import org.junit.Test;

import loja.Compra;
import loja.Jogo;
import loja.Loja;
import loja.cliente.Cadastrado;
import loja.cliente.assinante.Empolgado;
import loja.jogo.*;

public class LojaTest {
    @Test
    public void testLojaValorMensal() {
        // Cliente 1
        Empolgado c1 = new Empolgado("Cliente 1", "c1", "1");
        Lancamento j1 = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Lancamento j2 = new Lancamento("Jogo 2", "Outro jogo qualquer", 120d);
        Premium j3 = new Premium("Jogo 3", "Mais um jogo qualquer", 150d);
        Compra comp1 = new Compra(LocalDate.now());

        comp1.adicionarJogo(j1);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j3);

        // Valor final da compra: ((100+10% + 120+10% + 150) - 20%) - 10%
        // 392 - 20% - 10% = 313,6 - 10% = 282,24

        // Cliente 2
        Empolgado c2 = new Empolgado("Cliente 2", "c2", "2");
        Lancamento j4 = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Premium j5 = new Premium("Jogo 3", "Mais um jogo qualquer", 150d);
        Compra comp2 = new Compra(LocalDate.now());

        comp2.adicionarJogo(j4);
        comp2.adicionarJogo(j5);

        // Valor final da compra: (100+10% + 150) - 10%
        // 260 - 10% = 234

        c1.adicionarCompra(comp1);
        c2.adicionarCompra(comp2);

        Loja j = new Loja();
        j.cadastraCliente(c1);
        j.cadastraCliente(c2);

        // Soma das compras: 516,24

        Double valorFinalEsperado = 516.24;
        assertEquals(valorFinalEsperado, j.valorTotalVendido());
    }

    @Test
    public void testLojaValorMedio() {
        // Cliente 1
        Cadastrado c1 = new Cadastrado("Cliente 1", "c1", "1", "email@exemlo.com");
        Premium j1 = new Premium("Jogo 1", "Outro jogo qualquer", 110d);
        Regular j2 = new Regular("Jogo 2", "Um jogo qualquer", 100d, 0d);
        Regular j3 = new Regular("Jogo 3", "Outro jogo qualquer", 105d, -0.15);
        Regular j4 = new Regular("Jogo 4", "Mais um jogo qualquer", 120d, -0.2);
        Premium j5 = new Premium("Jogo 5", "Outro jogo qualquer", 110d);
        Regular j6 = new Regular("Jogo 6", "Um jogo qualquer", 100d, 0d);
        Regular j7 = new Regular("Jogo 7", "Outro jogo qualquer", 105d, -0.15);

        Compra comp1 = new Compra(LocalDate.now());
        comp1.adicionarJogo(j1);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j3);
        comp1.adicionarJogo(j4);
        // Valor final da compra: (110 + 100 + 89,25 + 96) = 395,25 - 20% = 316,2

        Compra comp2 = new Compra(LocalDate.now());
        comp2.adicionarJogo(j5);
        comp2.adicionarJogo(j6);
        comp2.adicionarJogo(j7);
        // Valor final da compra: (110 + 100 + 89,25) = 299,25

        c1.adicionarCompra(comp1);
        c1.adicionarCompra(comp2);

        // Cliente 2
        Cadastrado c2 = new Cadastrado("Cliente 2", "c2", "2", "email@exemplo.com");
        Regular j8 = new Regular("Jogo 8", "Um jogo qualquer", 150d, 0d);
        Regular j9 = new Regular("Jogo 9", "Outro jogo qualquer", 130d, -0.1);
        Regular j10 = new Regular("Jogo 10", "Mais um jogo qualquer 1", 110d, -0.3);
        Regular j11 = new Regular("Jogo 11", "Mais um jogo qualquer 2", 160d, -0.1);
        Compra comp3 = new Compra(LocalDate.now());

        comp3.adicionarJogo(j8);
        comp3.adicionarJogo(j9);
        comp3.adicionarJogo(j10);
        comp3.adicionarJogo(j11);

        c2.adicionarCompra(comp3);

        Loja j = new Loja();
        j.cadastraCliente(c1);
        j.cadastraCliente(c2);

        // Valor final da compra: (150 + 117 + 77 + 144) = 488 - 10% = 439,2

        // Total das compras: 1054,65; Medio: 351,55

        Double valorFinalEsperado = 351.55;
        assertEquals(valorFinalEsperado, j.valorMedioCompras());
    }

    @Test
    public void testLojaJogoMaisVendido() {
        Premium j1 = new Premium("Jogo 1", "Outro jogo qualquer", 110d);
        Regular j2 = new Regular("Jogo 2", "Um jogo qualquer", 100d, 0d);
        Regular j3 = new Regular("Jogo 3", "Outro jogo qualquer", 105d, -0.15);
        Regular j4 = new Regular("Jogo 4", "Mais um jogo qualquer", 120d, -0.2);
        Premium j5 = new Premium("Jogo 5", "Outro jogo qualquer", 110d);

        Cadastrado c1 = new Cadastrado("Cliente 1", "c1", "1", "email@exemlo.com");
        Compra comp1 = new Compra(LocalDate.now());
        comp1.adicionarJogo(j1);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j4);

        Compra comp2 = new Compra(LocalDate.now());
        comp2.adicionarJogo(j5);
        comp2.adicionarJogo(j2);
        comp2.adicionarJogo(j1);

        c1.adicionarCompra(comp1);
        c1.adicionarCompra(comp2);

        Cadastrado c2 = new Cadastrado("Cliente 2", "c2", "2", "email@exemplo.com");
        Compra comp3 = new Compra(LocalDate.now());

        comp3.adicionarJogo(j4);
        comp3.adicionarJogo(j3);
        comp3.adicionarJogo(j3);
        comp3.adicionarJogo(j2);

        c2.adicionarCompra(comp3);

        Loja j = new Loja();
        j.cadastraCliente(c1);
        j.cadastraCliente(c2);
        j.cadastraJogo(j1);
        j.cadastraJogo(j2);
        j.cadastraJogo(j3);
        j.cadastraJogo(j4);
        j.cadastraJogo(j5);

        Jogo jogoMaisVendido = j2;
        assertEquals(jogoMaisVendido, j.jogoMaisVendido());
    }

    @Test
    public void testLojaJogoMenosVendido() {
        Premium j1 = new Premium("Jogo 1", "Outro jogo qualquer", 110d);
        Regular j2 = new Regular("Jogo 2", "Um jogo qualquer", 100d, 0d);
        Regular j3 = new Regular("Jogo 3", "Outro jogo qualquer", 105d, -0.15);
        Regular j4 = new Regular("Jogo 4", "Mais um jogo qualquer", 120d, -0.2);
        Premium j5 = new Premium("Jogo 5", "Outro jogo qualquer", 110d);

        Cadastrado c1 = new Cadastrado("Cliente 1", "c1", "1", "email@exemlo.com");
        Compra comp1 = new Compra(LocalDate.now());
        comp1.adicionarJogo(j1);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j4);

        Compra comp2 = new Compra(LocalDate.now());
        comp2.adicionarJogo(j5);
        comp2.adicionarJogo(j2);
        comp2.adicionarJogo(j1);

        c1.adicionarCompra(comp1);
        c1.adicionarCompra(comp2);

        Cadastrado c2 = new Cadastrado("Cliente 2", "c2", "2", "email@exemplo.com");
        Compra comp3 = new Compra(LocalDate.now());

        comp3.adicionarJogo(j4);
        comp3.adicionarJogo(j3);
        comp3.adicionarJogo(j3);
        comp3.adicionarJogo(j2);

        c2.adicionarCompra(comp3);

        Loja j = new Loja();
        j.cadastraCliente(c1);
        j.cadastraCliente(c2);
        j.cadastraJogo(j1);
        j.cadastraJogo(j2);
        j.cadastraJogo(j3);
        j.cadastraJogo(j4);
        j.cadastraJogo(j5);

        Jogo jogoMenosVendido = j5;
        assertEquals(jogoMenosVendido, j.jogoMenosVendido());
    }

    @Test
    public void testLojaJogoVezesComprado() {
        Premium j1 = new Premium("Jogo 1", "Outro jogo qualquer", 110d);
        Regular j2 = new Regular("Jogo 2", "Um jogo qualquer", 100d, 0d);
        Regular j3 = new Regular("Jogo 3", "Outro jogo qualquer", 105d, -0.15);
        Regular j4 = new Regular("Jogo 4", "Mais um jogo qualquer", 120d, -0.2);
        Premium j5 = new Premium("Jogo 5", "Outro jogo qualquer", 110d);

        Cadastrado c1 = new Cadastrado("Cliente 1", "c1", "1", "email@exemlo.com");
        Compra comp1 = new Compra(LocalDate.now());
        comp1.adicionarJogo(j1);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j4);

        Compra comp2 = new Compra(LocalDate.now());
        comp2.adicionarJogo(j2);
        comp2.adicionarJogo(j2);
        comp2.adicionarJogo(j1);

        c1.adicionarCompra(comp1);
        c1.adicionarCompra(comp2);

        Cadastrado c2 = new Cadastrado("Cliente 2", "c2", "2", "email@exemplo.com");
        Compra comp3 = new Compra(LocalDate.now());

        comp3.adicionarJogo(j4);
        comp3.adicionarJogo(j4);
        comp3.adicionarJogo(j3);
        comp3.adicionarJogo(j2);

        c2.adicionarCompra(comp3);

        Loja j = new Loja();
        j.cadastraCliente(c1);
        j.cadastraCliente(c2);
        j.cadastraJogo(j1);
        j.cadastraJogo(j2);
        j.cadastraJogo(j3);
        j.cadastraJogo(j4);
        j.cadastraJogo(j5);

        assertEquals(2, j.vezesJogoComprado(j1));
        assertEquals(5, j.vezesJogoComprado(j2));
        assertEquals(1, j.vezesJogoComprado(j3));
        assertEquals(3, j.vezesJogoComprado(j4));
        assertEquals(0, j.vezesJogoComprado(j5));
    }

    @Test
    public void testLojaJogosMaisVendidos() {
        Premium j1 = new Premium("Jogo 1", "Outro jogo qualquer", 110d);
        Regular j2 = new Regular("Jogo 2", "Um jogo qualquer", 100d, 0d);
        Regular j3 = new Regular("Jogo 3", "Outro jogo qualquer", 105d, -0.15);
        Regular j4 = new Regular("Jogo 4", "Mais um jogo qualquer", 120d, -0.2);
        Premium j5 = new Premium("Jogo 5", "Outro jogo qualquer", 110d);

        Cadastrado c1 = new Cadastrado("Cliente 1", "c1", "1", "email@exemlo.com");
        Compra comp1 = new Compra(LocalDate.now());
        comp1.adicionarJogo(j1);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j4);

        Compra comp2 = new Compra(LocalDate.now());
        comp2.adicionarJogo(j2);
        comp2.adicionarJogo(j4);
        comp2.adicionarJogo(j1);

        c1.adicionarCompra(comp1);
        c1.adicionarCompra(comp2);

        Cadastrado c2 = new Cadastrado("Cliente 2", "c2", "2", "email@exemplo.com");
        Compra comp3 = new Compra(LocalDate.now());

        comp3.adicionarJogo(j4);
        comp3.adicionarJogo(j4);
        comp3.adicionarJogo(j3);
        comp3.adicionarJogo(j2);

        c2.adicionarCompra(comp3);

        Loja j = new Loja();
        j.cadastraCliente(c1);
        j.cadastraCliente(c2);
        j.cadastraJogo(j1);
        j.cadastraJogo(j2);
        j.cadastraJogo(j3);
        j.cadastraJogo(j4);
        j.cadastraJogo(j5);

        LinkedList<Jogo> jogosMaisVendidos = new LinkedList<>();
        jogosMaisVendidos.add(j2);
        jogosMaisVendidos.add(j4);

        assertEquals(jogosMaisVendidos, j.jogosMaisVendidos());
    }

    @Test
    public void testLojaJogosMenosVendidos() {
        Premium j1 = new Premium("Jogo 1", "Outro jogo qualquer", 110d);
        Regular j2 = new Regular("Jogo 2", "Um jogo qualquer", 100d, 0d);
        Regular j3 = new Regular("Jogo 3", "Outro jogo qualquer", 105d, -0.15);
        Regular j4 = new Regular("Jogo 4", "Mais um jogo qualquer", 120d, -0.2);
        Premium j5 = new Premium("Jogo 5", "Outro jogo qualquer", 110d);

        Cadastrado c1 = new Cadastrado("Cliente 1", "c1", "1", "email@exemlo.com");
        Compra comp1 = new Compra(LocalDate.now());
        comp1.adicionarJogo(j1);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j2);
        comp1.adicionarJogo(j4);

        Compra comp2 = new Compra(LocalDate.now());
        comp2.adicionarJogo(j2);
        comp2.adicionarJogo(j4);
        comp2.adicionarJogo(j1);

        c1.adicionarCompra(comp1);
        c1.adicionarCompra(comp2);

        Cadastrado c2 = new Cadastrado("Cliente 2", "c2", "2", "email@exemplo.com");
        Compra comp3 = new Compra(LocalDate.now());

        comp3.adicionarJogo(j5);
        comp3.adicionarJogo(j4);
        comp3.adicionarJogo(j3);
        comp3.adicionarJogo(j2);

        c2.adicionarCompra(comp3);

        Loja j = new Loja();
        j.cadastraCliente(c1);
        j.cadastraCliente(c2);
        j.cadastraJogo(j1);
        j.cadastraJogo(j2);
        j.cadastraJogo(j3);
        j.cadastraJogo(j4);
        j.cadastraJogo(j5);

        LinkedList<Jogo> jogosMenosVendidos = new LinkedList<>();
        jogosMenosVendidos.add(j3);
        jogosMenosVendidos.add(j5);

        assertEquals(jogosMenosVendidos, j.jogosMenosVendidos());
    }
}
