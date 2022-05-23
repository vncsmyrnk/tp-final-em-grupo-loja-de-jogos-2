package testes;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import loja.Compra;
import loja.cliente.Cadastrado;
import loja.cliente.assinante.Empolgado;
import loja.cliente.assinante.Fanatico;
import loja.jogo.Lancamento;
import loja.jogo.Premium;
import loja.jogo.Regular;

public class CompraTest {
    @Test
    public void testValorCompra1() {
        Empolgado clientEmpolgado = new Empolgado("Cliente 1", "c1", "1");
        Lancamento jogoLancamento = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Lancamento jogoLancamento2 = new Lancamento("Jogo 2", "Outro jogo qualquer", 120d);
        Premium jogoPremium = new Premium("Jogo 3", "Mais um jogo qualquer", 150d);
        Compra compra = new Compra(LocalDate.now());

        compra.adicionarJogo(jogoLancamento);
        compra.adicionarJogo(jogoLancamento2);
        compra.adicionarJogo(jogoPremium);

        // Valor final da compra: ((100+10% + 120+10% + 150) - 20%) - 10%
        // 392 - 20% - 10% = 313,6 - 10% = 282,24

        Double valorFinalEsperado = 282.24;
        assertEquals(valorFinalEsperado, clientEmpolgado.valorFinalCompra(compra));
    }

    @Test
    public void testValorCompra2() {
        Empolgado clientEmpolgado = new Empolgado("Cliente 1", "c1", "1");
        Lancamento jogoLancamento = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Premium jogoPremium = new Premium("Jogo 3", "Mais um jogo qualquer", 150d);
        Compra compra = new Compra(LocalDate.now());

        compra.adicionarJogo(jogoLancamento);
        compra.adicionarJogo(jogoPremium);

        // Valor final da compra: (100+10% + 150) - 10%
        // 260 - 10% = 234

        Double valorFinalEsperado = 234d;
        assertEquals(valorFinalEsperado, clientEmpolgado.valorFinalCompra(compra));
    }

    @Test
    public void testValorCompra3() {
        Empolgado clientEmpolgado = new Empolgado("Cliente 1", "c1", "1");
        Premium jogoPremium = new Premium("Jogo 1", "Mais um jogo qualquer", 150d);
        Premium jogoPremium2 = new Premium("Jogo 2", "Outro jogo qualquer", 130d);
        Compra compra = new Compra(LocalDate.now());

        compra.adicionarJogo(jogoPremium);
        compra.adicionarJogo(jogoPremium2);

        // Valor final da compra: (130 + 150) - 10% - 10%
        // 280 - 10% = 225 - 10% = 226.8

        Double valorFinalEsperado = 226.8;
        assertEquals(valorFinalEsperado, clientEmpolgado.valorFinalCompra(compra));
    }

    @Test
    public void testValorCompra4() {
        Empolgado clientEmpolgado = new Empolgado("Cliente 1", "c1", "1");
        Premium jogoPremium = new Premium("Jogo 1", "Mais um jogo qualquer", 150d);
        Compra compra = new Compra(LocalDate.now());

        compra.adicionarJogo(jogoPremium);

        // Valor final da compra: 150 - 10% = 135

        Double valorFinalEsperado = 135d;
        assertEquals(valorFinalEsperado, clientEmpolgado.valorFinalCompra(compra));
    }

    @Test
    public void testValorCompra5() {
        Cadastrado clientCadastrado = new Cadastrado("Cliente 1", "c1", "1", "email@exemplo.com");
        Premium jogoPremium = new Premium("Jogo 1", "Mais um jogo qualquer", 150d);
        Premium jogoPremium2 = new Premium("Jogo 2", "Outro jogo qualquer", 130d);
        Premium jogoPremium3 = new Premium("Jogo 3", "Mais um jogo qualquer", 120d);
        Compra compra = new Compra(LocalDate.now());

        compra.adicionarJogo(jogoPremium);
        compra.adicionarJogo(jogoPremium2);
        compra.adicionarJogo(jogoPremium3);

        // Valor final da compra: (150 + 130 + 120) - 20%
        // 400 - 20% = 320

        Double valorFinalEsperado = 320d;
        assertEquals(valorFinalEsperado, clientCadastrado.valorFinalCompra(compra));
    }

    @Test
    public void testValorCompra6() {
        Fanatico clientFanatico = new Fanatico("Cliente 1", "c1", "1");
        Regular jogoRegular = new Regular("Jogo 1", "Um jogo qualquer", 150d, 0d);
        Regular jogoRegular2 = new Regular("Jogo 2", "Outro jogo qualquer", 130d, -0.1);
        Regular jogoRegular3 = new Regular("Jogo 4", "Mais um jogo qualquer 1", 110d, -0.3);
        Regular jogoRegular4 = new Regular("Jogo 5", "Mais um jogo qualquer 2", 160d, -0.1);
        Regular jogoRegular5 = new Regular("Jogo 6", "Mais um jogo qualquer 3", 145d, -0.2);
        Compra compra = new Compra(LocalDate.now());

        compra.adicionarJogo(jogoRegular);
        compra.adicionarJogo(jogoRegular2);
        compra.adicionarJogo(jogoRegular3);
        compra.adicionarJogo(jogoRegular4);
        compra.adicionarJogo(jogoRegular5);

        // Valor final da compra: (150 + 117 + 77 + 144 + 116) = 604 - 20% - 30% =
        // 338,24

        Double valorFinalEsperado = 338.24;
        assertEquals(valorFinalEsperado, clientFanatico.valorFinalCompra(compra));
    }

    @Test
    public void testValorCompra7() {
        Cadastrado clientCadastrado = new Cadastrado("Cliente 1", "c1", "1", "email@exemlo.com");
        Regular jogoRegular = new Regular("Jogo 1", "Um um jogo qualquer", 150d, 0d);
        Premium jogoPremium = new Premium("Jogo 2", "Outro jogo qualquer", 120d);
        Premium jogoPremium2 = new Premium("Jogo 3", "Mais um jogo qualquer", 130d);
        Compra compra = new Compra(LocalDate.now());

        compra.adicionarJogo(jogoRegular);
        compra.adicionarJogo(jogoPremium);
        compra.adicionarJogo(jogoPremium2);

        // Valor final da compra: (150 + 120 + 130) = 400 - 20% = 320

        Double valorFinalEsperado = 320d;
        assertEquals(valorFinalEsperado, clientCadastrado.valorFinalCompra(compra));
    }

    @Test
    public void testValorCompra8() {
        Cadastrado clientCadastrado = new Cadastrado("Cliente 1", "c1", "1", "email@exemlo.com");
        Premium jogoPremium = new Premium("Jogo 1", "Outro jogo qualquer", 110d);
        Regular jogoRegular = new Regular("Jogo 2", "Um jogo qualquer", 100d, 0d);
        Regular jogoRegular2 = new Regular("Jogo 3", "Outro jogo qualquer", 105d, -0.15);
        Regular jogoRegular3 = new Regular("Jogo 4", "Mais um jogo qualquer", 120d, -0.2);
        Compra compra = new Compra(LocalDate.now());

        compra.adicionarJogo(jogoPremium);
        compra.adicionarJogo(jogoRegular);
        compra.adicionarJogo(jogoRegular2);
        compra.adicionarJogo(jogoRegular3);

        // Valor final da compra: (110 + 100 + 89,25 + 96) = 395,25 - 20% = 316,2

        Double valorFinalEsperado = 316.2;
        assertEquals(valorFinalEsperado, clientCadastrado.valorFinalCompra(compra));
    }

    @Test
    public void testValorCompra9() {
        Cadastrado clientCadastrado = new Cadastrado("Cliente 1", "c1", "1", "email@exemlo.com");
        Premium jogoPremium = new Premium("Jogo 1", "Outro jogo qualquer", 110d);
        Regular jogoRegular = new Regular("Jogo 2", "Um jogo qualquer", 100d, 0d);
        Regular jogoRegular2 = new Regular("Jogo 3", "Outro jogo qualquer", 105d, -0.15);
        Compra compra = new Compra(LocalDate.now());

        compra.adicionarJogo(jogoPremium);
        compra.adicionarJogo(jogoRegular);
        compra.adicionarJogo(jogoRegular2);

        // Valor final da compra: (110 + 100 + 89,25) = 299,25

        Double valorFinalEsperado = 299.25;
        assertEquals(valorFinalEsperado, clientCadastrado.valorFinalCompra(compra));
    }

    @Test
    public void testValorCompra10() {
        Cadastrado clientCadastrado = new Cadastrado("Cliente 1", "c1", "1", "email@exemplo.com");
        Regular jogoRegular = new Regular("Jogo 1", "Um jogo qualquer", 150d, 0d);
        Regular jogoRegular2 = new Regular("Jogo 2", "Outro jogo qualquer", 130d, -0.1);
        Regular jogoRegular3 = new Regular("Jogo 4", "Mais um jogo qualquer 1", 110d, -0.3);
        Regular jogoRegular4 = new Regular("Jogo 5", "Mais um jogo qualquer 2", 160d, -0.1);
        Compra compra = new Compra(LocalDate.now());

        compra.adicionarJogo(jogoRegular);
        compra.adicionarJogo(jogoRegular2);
        compra.adicionarJogo(jogoRegular3);
        compra.adicionarJogo(jogoRegular4);

        // Valor final da compra: (150 + 117 + 77 + 144) = 488 - 10% = 439,2

        Double valorFinalEsperado = 439.2;
        assertEquals(valorFinalEsperado, clientCadastrado.valorFinalCompra(compra));
    }
}
