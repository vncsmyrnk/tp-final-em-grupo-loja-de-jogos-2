package testes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.LinkedList;

import org.junit.Test;

import loja.Compra;
import loja.cliente.assinante.Empolgado;
import loja.jogo.Lancamento;
import loja.jogo.Premium;
import loja.jogo.Promocao;

public class HistoricoClienteTest {
    @Test
    public void testHistoricoPorData() {
        LocalDate data1 = LocalDate.of(2022, 1, 1);
        LocalDate data2 = LocalDate.of(2022, 1, 2);

        Empolgado clientEmpolgado = new Empolgado("Cliente 1", "c1", "1");
        Lancamento jogoLancamento = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Lancamento jogoLancamento2 = new Lancamento("Jogo 2", "Outro jogo qualquer", 120d);
        Premium jogoPremium = new Premium("Jogo 3", "Mais um jogo qualquer", 150d);

        Compra compra = new Compra(data1);
        compra.adicionarJogo(jogoLancamento);
        compra.adicionarJogo(jogoLancamento2);

        Compra compra2 = new Compra(data2);
        compra2.adicionarJogo(jogoPremium);

        clientEmpolgado.adicionarCompra(compra);
        clientEmpolgado.adicionarCompra(compra2);

        LinkedList<Compra> comprasData1 = clientEmpolgado.historico(data1);
        LinkedList<Compra> comprasData1Comparacao = new LinkedList<>();
        comprasData1Comparacao.add(compra);

        assertTrue(comprasData1.equals(comprasData1Comparacao));
    }

    @Test
    public void testHistoricoPorData2() {
        LocalDate data1 = LocalDate.of(2022, 1, 1);
        LocalDate data2 = LocalDate.of(2022, 1, 2);

        Empolgado clientEmpolgado = new Empolgado("Cliente 1", "c1", "1");
        Lancamento jogoLancamento = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Lancamento jogoLancamento2 = new Lancamento("Jogo 2", "Outro jogo qualquer", 120d);
        Premium jogoPremium = new Premium("Jogo 3", "Mais um jogo qualquer", 150d);

        Compra compra = new Compra(data1);
        compra.adicionarJogo(jogoLancamento);

        Compra compra2 = new Compra(data2);
        compra2.adicionarJogo(jogoPremium);

        Compra compra3 = new Compra(data2);
        compra3.adicionarJogo(jogoLancamento2);

        clientEmpolgado.adicionarCompra(compra);
        clientEmpolgado.adicionarCompra(compra2);
        clientEmpolgado.adicionarCompra(compra3);

        LinkedList<Compra> comprasData2 = clientEmpolgado.historico(data2);
        LinkedList<Compra> comprasData2Comparacao = new LinkedList<>();
        comprasData2Comparacao.add(compra2);
        comprasData2Comparacao.add(compra3);

        assertTrue(comprasData2.equals(comprasData2Comparacao));
    }

    @Test
    public void testHistoricoHasJogo() {
        Empolgado clientEmpolgado = new Empolgado("Cliente 1", "c1", "1");
        Lancamento jogoLancamento = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Lancamento jogoLancamento2 = new Lancamento("Jogo 2", "Outro jogo qualquer", 120d);
        Premium jogoPremium = new Premium("Jogo 3", "Mais um jogo qualquer", 150d);

        Compra compra = new Compra(LocalDate.of(2022, 1, 1));
        compra.adicionarJogo(jogoLancamento);
        compra.adicionarJogo(jogoLancamento2);
        compra.adicionarJogo(jogoPremium);

        clientEmpolgado.adicionarCompra(compra);

        LinkedList<Compra> comprasDescricao1 = clientEmpolgado.historico(Lancamento.DESCRICAO);
        LinkedList<Compra> comprasDescricao1Comparacao = new LinkedList<>();
        comprasDescricao1Comparacao.add(compra);

        assertTrue(comprasDescricao1.equals(comprasDescricao1Comparacao));
    }

    @Test
    public void testHistoricoHasJogo2() {
        Empolgado clientEmpolgado = new Empolgado("Cliente 1", "c1", "1");
        Lancamento jogoLancamento = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Lancamento jogoLancamento2 = new Lancamento("Jogo 2", "Outro jogo qualquer", 120d);
        Premium jogoPremium = new Premium("Jogo 3", "Mais um jogo qualquer", 150d);

        Compra compra = new Compra(LocalDate.of(2022, 1, 1));
        compra.adicionarJogo(jogoLancamento);
        compra.adicionarJogo(jogoLancamento2);
        compra.adicionarJogo(jogoPremium);

        clientEmpolgado.adicionarCompra(compra);

        LinkedList<Compra> comprasDescricao1 = clientEmpolgado.historico(Promocao.DESCRICAO);
        LinkedList<Compra> comprasDescricao1Comparacao = new LinkedList<>(); // Lista de comparacao vazia - nao devem
                                                                             // existir jogos

        assertTrue(comprasDescricao1.equals(comprasDescricao1Comparacao));
    }
}
