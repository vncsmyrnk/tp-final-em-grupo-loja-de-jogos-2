import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import javax.naming.NameNotFoundException;

import loja.Cliente;
import loja.Compra;
import loja.Jogo;
import loja.Loja;
import loja.jogo.Premium;

public class App {

    static String arqDados;
    static Scanner teclado;
    static Loja loja;


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
        limparTela();
        System.out.println("XULAMBGAMES");
        System.out.println("==========================");
        System.out.println("Informe a opção desejada");
        System.out.println("1 - Cadastro de jogos");
        System.out.println("2 - Cadastro de clientes");
        System.out.println("3 - Cadastro de compras");
        System.out.println("4 - Listar jogos");
        System.out.println("5 - Listar clientes");
        System.out.println("0 - Sair");

        // ToDo
        // Revisar cadastros
        // Cadastrar compra
        // Opção de historico do cliente
        // Valor de uma compra
        // Valor mensal (total)
        // Valor médio
        // Jogos extremos

        int opcao = teclado.nextInt();
        teclado.nextLine();
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
            categoria = Integer.parseInt(teclado.nextLine());

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
                    System.out.println("Digite a porcentagem entre 70% e 100%:");
                    modificador = teclado.nextDouble();
                    loja.cadastrarRegular(nome, descricao, preco, modificador);
                    break;
                case 4:
                    System.out.println("Categoria PROMOÇÃO");
                    System.out.println("Digite a porcentagem entre 30% e 50%:");
                    modificador = teclado.nextDouble();
                    loja.cadastrarPromocao(nome, descricao, preco, modificador);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void menuCompraJogo() {
        int categoria = 0;
        String nome = "";
        String descricao = "";
        Double preco = 0.0;

        try {
            System.out.println("Deseja buscar o jogo por?");
            System.out.println("1 - Nome");
            System.out.println("2 - Descrição");
            System.out.println("3 - Preço");
            System.out.println("4 - Categoria");

            int opc = Integer.parseInt(teclado.nextLine());
            switch (opc) {
                case 1:
                    System.out.println("Digite o nome do jogo desejado:");
                    nome = teclado.nextLine();
                    break;
                case 2:
                    System.out.println("Digite a descrição do jogo desejado:");
                    descricao = teclado.nextLine();

                    break;
                case 3:
                    System.out.println("Digite o preço do jogo desejado:");
                    preco = teclado.nextDouble();

                    break;
                case 4:
                    System.out.println("Digite a categoria de jogos desejada :");
                    categoria = teclado.nextInt();

                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void menuCompraJogo2() {
        Cliente c = cadastroClienteCompra();
        LinkedList<Jogo> jCompra = cadastroJogosCompra();
        c.adicionarCompra(new Compra(jCompra));
    }

    public static Cliente cadastroClienteCompra() {
        try {
            boolean finalizarSelecaoCliente = false;
            Cliente c = null;
            while (!finalizarSelecaoCliente) {
                System.out.println("Nome cliente: ");
                String nome = teclado.nextLine();

                try {
                    c = loja.listarClientes()
                            .stream()
                            .filter((cliente) -> cliente.getNome() == nome)
                            .findFirst()
                            .orElseThrow(() -> new NameNotFoundException());    
                } catch (NameNotFoundException e) {
                    System.out.println("Cliente não encontrado...");
                    continue;
                }

                System.out.println("Cliente encontrado: ");
                System.out.println(c);
                teclado.nextLine();
                System.out.println("Confirma seleção do cliente? (s/n)");
                String opc = teclado.nextLine();
                switch (opc) {
                    case "n":
                    case "N":
                        finalizarSelecaoCliente = true;
                }
            }
            return c;
        } catch (Exception e) {

        }
        return null;
    }

    public static LinkedList<Jogo> cadastroJogosCompra() {
        try {
            boolean finalizarSelecaoJogo = false;
            LinkedList<Jogo> jogosCompra = new LinkedList<>();
            while (!finalizarSelecaoJogo) {
                System.out.println("Nome jogo: ");
                String nome = teclado.nextLine();
                Jogo j = loja.listarJogos()
                        .stream()
                        .filter((jogo) -> jogo.getNome() == nome)
                        .findFirst()
                        .orElseThrow(() -> new NameNotFoundException());
                System.out.println("Jogo encontrado: ");
                System.out.println(j);
                jogosCompra.add(j);
                teclado.nextLine();
                System.out.println("Adicionar mais à compra? (s/n)");
                String opc = teclado.nextLine();
                switch (opc) {
                    case "n":
                    case "N":
                        finalizarSelecaoJogo = true;
                }
            }
            return jogosCompra;
        } catch (NameNotFoundException e) {
            System.out.println("Jogo não encontrado...");
        } catch (Exception e) {

        }
        return new LinkedList<>();
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
            categoria = Integer.parseInt(teclado.nextLine());

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
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        int opcao = -1;
        teclado = new Scanner(System.in);
        loja = new Loja();
        loja.leDados();
        // Jogo jogoCadastrado = null;
        // Cliente clienteCadastrado = null;
        // Compra compraCadastrada = null;

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
                    menuCompraJogo2();
                case 4:
                    listarJogos();
                case 5:
                    listarClientes();

            }
            pausa(teclado);
        } while (opcao != 0);
        teclado.close();
    }

    public static void listarJogos() {
        System.out.println(loja.listarJogos());
    }

    public static void listarClientes() {
        System.out.println(loja.listarClientes());
    }

}