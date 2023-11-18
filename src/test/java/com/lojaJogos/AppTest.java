package com.lojaJogos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Executable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import javax.naming.NameNotFoundException;

import org.junit.jupiter.api.Test;

import com.lojaJogos.cliente.Cadastrado;
import com.lojaJogos.cliente.assinante.Empolgado;
import com.lojaJogos.cliente.assinante.Fanatico;
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
    public void testAppMenuOpcaoInvalida() {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        App.teclado = new Scanner(TestsUtil.provideInput("a\n\n0\n"));
        App.menu(App.teclado);
        String outputEsperado = "Tente novamente com valores válidos";
        assertTrue(output.toString().contains(outputEsperado));
    }

    @Test
    public void testAppClientesHistoricoTodos() {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        Fanatico c1 = new Fanatico("Cliente1", "c1", "senha");

        App.loja = new Loja();
        App.loja.cadastraCliente(c1);

        Lancamento jogoLancamento = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Compra compra = new Compra(LocalDate.of(2023, 1, 1));
        compra.adicionarJogo(jogoLancamento);
        c1.adicionarCompra(compra);

        App.teclado = new Scanner(TestsUtil.provideInput("t\n\n0\n"));
        App.listarClientesHistorico();

        String outputEsperado = "> Nome: Cliente1; Usuário: c1 (Fanático)\n" + //
                "\t> Compra " + compra.hashCode() + "\n" + //
                "\t\t+ Data: 01/01/2023\n" + //
                "\t\t\t- [Lançamento] Nome: Jogo 1; Preço original: 100.0; Valor: R$ 110.0; Descrição: Um jogo qualquer\n" + //
                "\t\t+ Total compra: R$ 110.0\n" + //
                "\t< Valor a ser pago: R$ 77.0\n" + //
                "\t< " + compra.hashCode() + "\n" + //
                "< Total gasto: R$ 77.0";
        assertTrue(output.toString().contains(outputEsperado));
    }

    @Test
    public void testAppClientesHistoricoPorClienteECategoria() {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        Fanatico c1 = new Fanatico("Cliente1", "c1", "senha");

        App.loja = new Loja();
        App.loja.cadastraCliente(c1);

        Lancamento jogoLancamento = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Compra compra1 = new Compra(LocalDate.of(2023, 1, 1));
        compra1.adicionarJogo(jogoLancamento);
        c1.adicionarCompra(compra1); // Valor compra: 77.0

        Premium jogoPremium = new Premium("Jogo 2", "Um outro jogo qualquer", 120d);
        Compra compra2 = new Compra(LocalDate.of(2023, 1, 2));
        compra2.adicionarJogo(jogoPremium);
        c1.adicionarCompra(compra2); // Valor compra: 84.0

        App.teclado = new Scanner(TestsUtil.provideInput("c\nCliente1\ns\nLançamento\n\n"));
        App.listarClientesHistorico();

        String outputEsperado = "> Nome: Cliente1; Usuário: c1 (Fanático)\n" + //
                "\t> Compra " + compra1.hashCode() + "\n" + //
                "\t\t+ Data: 01/01/2023\n" + //
                "\t\t\t- [Lançamento] Nome: Jogo 1; Preço original: 100.0; Valor: R$ 110.0; Descrição: Um jogo qualquer\n" + //
                "\t\t+ Total compra: R$ 110.0\n" + //
                "\t< Valor a ser pago: R$ 77.0\n" + //
                "\t< " + compra1.hashCode() + "\n" + //
                "< Total gasto: R$ 161.0"; // Total das duas compras (77+84)
        assertTrue(output.toString().contains(outputEsperado));
    }

    @Test
    public void testAppClientesHistoricoPorClienteEData() {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        Fanatico c1 = new Fanatico("Cliente1", "c1", "senha");

        App.loja = new Loja();
        App.loja.cadastraCliente(c1);

        Lancamento jogoLancamento = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Compra compra1 = new Compra(LocalDate.of(2023, 1, 1));
        compra1.adicionarJogo(jogoLancamento);
        c1.adicionarCompra(compra1); // Valor compra: 77.0

        Premium jogoPremium = new Premium("Jogo 2", "Um outro jogo qualquer", 120d);
        Compra compra2 = new Compra(LocalDate.of(2023, 1, 2));
        compra2.adicionarJogo(jogoPremium);
        c1.adicionarCompra(compra2); // Valor compra: 84.0

        App.teclado = new Scanner(TestsUtil.provideInput("d\nCliente1\ns\n\n02/01/2023\n0\n"));
        App.listarClientesHistorico();

        String outputEsperado = "> Nome: Cliente1; Usuário: c1 (Fanático)\n" + //
                "\t> Compra " + compra2.hashCode() + "\n" + //
                "\t\t+ Data: 02/01/2023\n" + //
                "\t\t\t- [Premium] Nome: Jogo 2; Preço original: 120.0; Valor: R$ 120.0; Descrição: Um outro jogo qualquer\n" + //
                "\t\t+ Total compra: R$ 120.0\n" + //
                "\t< Valor a ser pago: R$ 84.0\n" + //
                "\t< " + compra2.hashCode() + "\n" + //
                "< Total gasto: R$ 161.0"; // Total das duas compras (77+84)
        assertTrue(output.toString().contains(outputEsperado));
    }

    @Test
    public void testAppClientesHistoricoOpcaoInvalida() {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        App.loja = new Loja();
        
        App.teclado = new Scanner(TestsUtil.provideInput("f\n\n"));
        App.listarClientesHistorico();

        String outputEsperado = "Opção inválida.";
        assertTrue(output.toString().contains(outputEsperado));
    }

    @Test
    public void testAppListarEstatisticasLoja() {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        Fanatico c1 = new Fanatico("Cliente1", "c1", "senha");

        App.loja = new Loja();
        App.loja.cadastraCliente(c1);

        Lancamento jogoLancamento = new Lancamento("Jogo 1", "Um jogo qualquer", 100d);
        Compra compra1 = new Compra(LocalDate.of(2023, 1, 1));
        compra1.adicionarJogo(jogoLancamento);
        c1.adicionarCompra(compra1); // Valor compra: 77.0

        Premium jogoPremium = new Premium("Jogo 2", "Um outro jogo qualquer", 120d);
        Compra compra2 = new Compra(LocalDate.of(2023, 1, 2));
        compra2.adicionarJogo(jogoPremium);
        c1.adicionarCompra(compra2); // Valor compra: 84.0

        App.loja.cadastraJogo(jogoLancamento);
        App.loja.cadastraJogo(jogoPremium);
        App.loja.cadastraJogo(new Regular("Jogo 3", "Desc", 100d, 1d));

        App.listarEstatisticasLoja();

        String outputEsperado = "Valor total gasto em compras: R$ 161.0\n" + //
                "Valor médio gasto em compras: R$ 80.5\n" + //
                "\n" + //
                "Jogos mais vendidos: \n" + //
                "[Lançamento] Nome do jogo: Jogo 1; Preço original: 100.0; Valor: R$ 110.0; Descrição: Um jogo qualquer (Comprado 1 vezes)\n" + //
                "[Premium] Nome do jogo: Jogo 2; Preço original: 120.0; Valor: R$ 120.0; Descrição: Um outro jogo qualquer (Comprado 1 vezes)\n" + //
                "\n" + //
                "Jogos menos vendidos: \n" + //
                "[Regular] Nome do jogo: Jogo 3; Preço original: 100.0; Valor: R$ 200.0; Descrição: Desc (Comprado 0 vezes)";
        assertTrue(output.toString().contains(outputEsperado));
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

    @Test
    public void testAppMenuCadastraJogoOpcaoInvalida() {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("20\n"));

        App.menuCadastraJogo();
        String outputEsperado = "Opção inválida.";
        assertTrue(output.toString().contains(outputEsperado));
    }

    @Test
    public void testAppMenuCadastraClienteEmpolgado() {
        TestsUtil.suppressOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("1\nCliente1\nc1\nsenha\n"));

        App.menuCadastraCliente();
        LinkedList<Cliente> clientesEsperados = new LinkedList<>(Arrays.asList(new Empolgado("Cliente1", "c1", "senha")));
        LinkedList<Cliente> clientesLoja = App.loja.listarClientes();
        assertEquals(clientesEsperados, clientesLoja);
    }

    @Test
    public void testAppMenuCadastraClienteFanatico() {
        TestsUtil.suppressOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("2\nCliente3\nc1\nsenha\n"));

        App.menuCadastraCliente();
        LinkedList<Cliente> clientesEsperados = new LinkedList<>(Arrays.asList(new Fanatico("Cliente3", "c1", "senha")));
        LinkedList<Cliente> clientesLoja = App.loja.listarClientes();
        assertEquals(clientesEsperados, clientesLoja);
    }

    @Test
    public void testAppMenuCadastraClienteCadastrado() {
        TestsUtil.suppressOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("3\nCliente3\nc1\nsenha\nemail@exemplo.com\n"));

        App.menuCadastraCliente();
        LinkedList<Cliente> clientesEsperados = new LinkedList<>(Arrays.asList(new Cadastrado("Cliente3", "c1", "senha", "email@exemplo.com")));
        LinkedList<Cliente> clientesLoja = App.loja.listarClientes();
        assertEquals(clientesEsperados, clientesLoja);
    }

    @Test
    public void testAppMenuCadastraClienteOpCancelada() {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("0\n\n"));

        App.menuCadastraCliente();
        String outputEsperado = "Operação cancelada";
        assertTrue(output.toString().contains(outputEsperado));
    }

    @Test
    public void testAppMenuCadastraClienteOpcaoInvalida() {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("18\n"));

        App.menuCadastraCliente();
        String outputEsperado = "Opção inválida.";
        assertTrue(output.toString().contains(outputEsperado));
    }

    @Test
    public void testAppInterrogaUsuarioSeNao() throws Exception {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("n"));

        App.interrogaUsuarioSeNao("Mensagem");
        String outputEsperado = "Mensagem" + " (s/n)\n";
        assertTrue(output.toString().contains(outputEsperado));
    }

    @Test
    public void testAppInterrogaUsuarioSeNaoCancelado() throws Exception {
        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("s"));

        assertThrows(Exception.class, () -> App.interrogaUsuarioSeNao("Mensagem"));
    }

    @Test
    public void testAppInterrogaUsuarioSeSim() throws Exception {
        ByteArrayOutputStream output = TestsUtil.listenToOutput();

        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("s"));

        App.interrogaUsuarioSeSim("Mensagem");
        String outputEsperado = "Mensagem" + " (s/n)\n";
        assertTrue(output.toString().contains(outputEsperado));
    }

    @Test
    public void testAppInterrogaUsuarioSeSimCancelado() throws Exception {
        App.loja = new Loja();
        App.teclado = new Scanner(TestsUtil.provideInput("n"));

        assertThrows(Exception.class, () -> App.interrogaUsuarioSeSim("Mensagem"));
    }
}
