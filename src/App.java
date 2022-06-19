import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import javax.naming.NameNotFoundException;

import loja.Cliente;
import loja.Compra;
import loja.Jogo;
import loja.Loja;

public class App {

    static String arqDados;
    static Scanner teclado;
    static Loja loja;

    public static void main(String[] args) throws Exception {
        int opcao = -1;
        teclado = new Scanner(System.in);
        loja = new Loja();
        loja.leDados();

        do {
            opcao = menu(teclado);
            limparTela();
            switch (opcao) {
                case 0:
                    loja.gravaDados();
                    return;
                case 1:
                    menuCadastraJogo();
                    break;
                case 2:
                    menuCadastraCliente();
                    break;
                case 3:
                    menuCompraJogo();
                    break;
                case 4:
                    listarJogos();
                    break;
                case 5:
                    listarClientesHistorico();
                    break;
                case 6:
                    listarEstatisticasLoja();
                    break;
            }
            pausa(teclado);
        } while (opcao != 0);
        teclado.close();
    }

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Menu para a Loja de Jogos
     * 
     * @param teclado Scanner de leitura
     * @return Opção do usuário (int)
     */
    public static int menu(Scanner teclado) {
        int opcao = 0;
        while (true) {
            try {
                limparTela();
                System.out.println("XULAMBGAMES");
                System.out.println("==========================");
                System.out.println("Informe a opção desejada");
                System.out.println("1 - Cadastro de jogos");
                System.out.println("2 - Cadastro de clientes");
                System.out.println("3 - Cadastro de compras");
                System.out.println("4 - Listar jogos");
                System.out.println("5 - Histórico compras");
                System.out.println("6 - Estatísticas da loja");
                System.out.println("0 - Sair");

                opcao = teclado.nextInt();
                teclado.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Tente novamente com valores válidos");
                exigirInteracao();
                teclado.nextLine();
                continue;
            }
        }
        return opcao;
    }

    /**
     * Pausa para leitura de mensagens em console
     * 
     * @param teclado Scanner de leitura
     */
    static void pausa(Scanner teclado) {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    /**
     * Gravação serializada do conjunto de clientes
     * 
     * @param clientes Conjunto de clientes a salvar
     * @throws IOException Em caso de erro na escrita ou abertura do arquivo
     *                     (propagação de exceção)
     */
    public static void gravarClientes(Set<Cliente> clientes) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arqDados));
        for (Cliente cliente : clientes) {
            obj.writeObject(cliente);
        }
        obj.close();
    }

    /**
     * Gravação serializada do conjunto de compras
     * 
     * @param compras Conjunto de compras a salvar
     * @throws IOException Em caso de erro na escrita ou abertura do arquivo
     *                     (propagação de exceção)
     */
    public static void gravarCompras(Set<Compra> compras) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arqDados));
        for (Compra compra : compras) {
            obj.writeObject(compra);
        }
        obj.close();
    }

    /**
     * Gravação serializada do conjunto de compras
     * 
     * @param compras Conjunto de compras a salvar
     * @throws IOException Em caso de erro na escrita ou abertura do arquivo
     *                     (propagação de exceção)
     */
    public static void gravarJogos(Set<Jogo> jogos) throws IOException {
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arqDados));
        for (Jogo jogo : jogos) {
            obj.writeObject(jogo);
        }
        obj.close();
    }

    public static void menuCadastraJogo() {

        int categoria = 0;
        String nome = "";
        String descricao = "";
        Double preco = 0.0;
        double modificador = 0;

        try {
            System.out.println("Qual a categoria do jogo a ser informado?");
            System.out.println("1 - Lançamento");
            System.out.println("2 - Premium");
            System.out.println("3 - Regular");
            System.out.println("4 - Promoção");
            categoria = Integer.parseInt(obterString("0 - Cancelar", "0"));

            System.out.println("Digite o nome do jogo:");
            nome = teclado.nextLine();

            System.out.println("Digite a descrição do jogo:");
            descricao = teclado.nextLine();

            System.out.println("Digite o preço original do jogo:");
            preco = teclado.nextDouble();

            System.out.println("Digite o modificador de preço do jogo:");
            modificador = 0;

            switch (categoria) {
                case 1:
                    loja.cadastrarLancamento(nome, descricao, preco);
                    break;
                case 2:
                    loja.cadastrarPremium(nome, descricao, preco);
                    break;
                case 3:
                    System.out.println("Categoria REGULAR");
                    modificador = obterValorDouble("Digite a porcentagem entre 70% e 100%:", 70d, 100d);
                    loja.cadastrarRegular(nome, descricao, preco, modificador / 100);
                    break;
                case 4:
                    System.out.println("Categoria PROMOÇÃO");
                    modificador = obterValorDouble("Digite a porcentagem entre 30% e 50%:", 30d, 50d);
                    loja.cadastrarPromocao(nome, descricao, preco, modificador / 100);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuCompraJogo() {
        try {
            Cliente c = interrogaUsuarioPorCliente();
            LinkedList<Jogo> jCompra = cadastroJogosCompra();
            c.adicionarCompra(new Compra(jCompra));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Cliente interrogaUsuarioPorCliente() throws Exception {
        Cliente c = null;

        while (true) {
            String nome = obterString("Nome cliente (0 p/ sair): ", "0");
            try {
                c = loja.buscaApenasUmClientePorNome(nome);
                System.out.println("Cliente encontrado. " + c);
                interrogaUsuarioSeSim("Confirma seleção do cliente?");
                break;
            } catch (NameNotFoundException e) {
                System.out.println("Cliente não encontrado...");
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

        }
        return c;
    }

    public static LinkedList<Jogo> cadastroJogosCompra() throws Exception {
        LinkedList<Jogo> jogos = new LinkedList<>();
        Jogo j;

        while (true) {
            String nome = obterString("Nome jogo (0 p/ sair): ", "0");
            try {
                j = loja.buscaApenasUmJogoPorNome(nome);
                System.out.println("Jogo encontrado. " + j);
                interrogaUsuarioSeSim("Confirma seleção do jogo?");
                jogos.add(j);
                interrogaUsuarioSeNao("Adicionar mais jogos?");
                break;
            } catch (NameNotFoundException e) {
                System.out.println("jogo não encontrado...");
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
        return jogos;
    }

    public static void menuCadastraCliente() {
        int categoria = 0;
        String nome = "";
        String usuario = "";
        String senha = "";
        String email = "";

        try {
            System.out.println("Qual a categoria do cliente a ser informado?");
            System.out.println("1 - Empolgado");
            System.out.println("2 - Fanático");
            System.out.println("3 - Cadastrado");
            categoria = Integer.parseInt(obterString("0 - Cancelar", "0"));

            System.out.println("Digite o nome do Cliente:");
            nome = teclado.nextLine();

            System.out.println("Digite nome usuário do Cliente:");
            usuario = teclado.nextLine();

            System.out.println("Digite a senha do do Cliente:");
            senha = teclado.nextLine();

            switch (categoria) {
                case 1:
                    loja.clienteEmpolgado(nome, categoria, usuario, senha);
                    break;
                case 2:
                    loja.clienteFanatico(nome, categoria, usuario, senha);
                    break;
                case 3:
                    System.out.println("Informe qual será o e-mail do cliente");
                    email = teclado.nextLine();
                    loja.clienteCadastrado(nome, categoria, usuario, senha, email);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void interrogaUsuarioSeSim(String mensagem) throws Exception {
        System.out.println(mensagem + " (s/n)");
        String opc = teclado.nextLine();

        switch (opc) {
            case "n":
            case "N":
                throw new Exception("Operação cancelada.");
        }
    }

    public static void interrogaUsuarioSeNao(String mensagem) throws Exception {
        System.out.println(mensagem + " (s/n)");
        String opc = teclado.nextLine();

        switch (opc) {
            case "s":
            case "S":
            case " ":
                throw new Exception("Operação cancelada.");
        }
    }

    public static String obterString(String mensagem) {
        System.out.println(mensagem);
        return teclado.nextLine();
    }

    public static String obterString(String mensagem, String exceptValue) throws Exception {
        String value = obterString(mensagem);
        if (value.equals(exceptValue)) {
            throw new Exception("Operação cancelada");
        }
        return value;
    }

    public static Double obterValorDouble(String mensagem) {
        System.out.println(mensagem);
        return Double.parseDouble(teclado.nextLine());
    }

    public static Double obterValorDouble(String mensagem, Double minValue, Double maxValue) {
        while (true) {
            try {
                teclado.nextLine();
                Double value = obterValorDouble(mensagem);
                if (value > maxValue || value < minValue)
                    throw new Exception("Valor inválido. Pressione <enter> e tente novamente.");
                return value;
            } catch (Exception e) {
                System.out.println("Problema ao ler o valor. " + e.getMessage());
                continue;
            }
        }
    }

    public static LocalDate obterData(String mensagem) {
        while (true) {
            try {
                exigirInteracao();
                System.out.println(mensagem + " (formato: dd/mm/yyyy)");
                String dataParts[] = teclado.nextLine().split("/");
                return LocalDate.of(Integer.parseInt(dataParts[2]), Integer.parseInt(dataParts[1]),
                        Integer.parseInt(dataParts[0]));
            } catch (Exception e) {
                System.out.println("Problema ao regsitrar a data. " + e.getMessage());
                continue;
            }
        }
    }

    public static void exigirInteracao() {
        System.out.println("Preesione <enter> para continuar.");
        teclado.nextLine();
    }

    public static int obterValorInteger(String mensagem) {
        System.out.println(mensagem);
        return Integer.parseInt(teclado.nextLine());
    }

    public static void listarJogos() {
        loja.listarJogos()
                .stream()
                .forEach((jogo) -> System.out.println(jogo.toString()));
    }

    public static void listarClientesHistorico() {
        try {
            Cliente c;
            String opc = obterString(
                    "Buscar por todos (t) ou por cliente, filtrando por data da compra (d) ou categoria dos jogos da compra (c)? (0 p/ sair)",
                    "0");
            switch (opc) {
                case "t":
                case "T":
                    System.out.println(loja.historicoClientes());
                    break;
                case "d":
                case "D":
                    c = interrogaUsuarioPorCliente();
                    LocalDate data = obterData("Insira a data para filtragem");
                    System.out.println(c.dados(data));
                    break;
                case "c":
                case "C":
                    c = interrogaUsuarioPorCliente();
                    String descricaoCategoria = obterString("Insira a categoria para filtragem");
                    System.out.println(c.dados(descricaoCategoria));
                    break;
                default:
                    throw new Exception("Opção inválida.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listarEstatisticasLoja() {
        System.out.println("Valor total gasto em compras: R$ " + loja.valorTotalVendido());
        System.out.println("Valor médio gasto em compras: R$ " + loja.valorMedioCompras());
        System.out.println("\nJogos mais vendidos: " + loja.relatorioJogosMaisVendidos());
        System.out.println("\nJogos menos vendidos: " + loja.relatorioJogosMenosVendidos());
    }

}
