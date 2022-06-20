package loja;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.naming.NameNotFoundException;

import loja.cliente.Cadastrado;
import loja.cliente.assinante.Empolgado;
import loja.cliente.assinante.Fanatico;
import loja.jogo.*;

/**
 * Responsavel por gerenciar uma loja de jogos considerando clientes e jogos
 */
public class Loja {
    private LinkedList<Jogo> jogos;
    private LinkedList<Cliente> clientes;
    private static final String arquivoClientes = "./dados/clientes.bin";
    private static final String arquivojogos = "./dados/jogos.bin";

    public Loja() {
        this.jogos = new LinkedList<>();
        this.clientes = new LinkedList<>();

    }

    /**
     * Adiciona um cliente a loja
     * 
     * @param Cliente cliente
     */
    public void cadastraCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    /**
     * Adiciona um jogo a loja
     * 
     * @param Jogo jogo
     */
    public void cadastraJogo(Jogo jogo) {
        this.jogos.add(jogo);
    }

    /**
     * Adiciona um jogo Lancamento a loja
     * 
     * @param String nome
     * @param String descricao
     * @param Double preco
     */
    public void cadastrarLancamento(String nome, String descricao, double preco) {
        this.cadastraJogo(new Lancamento(nome, descricao, preco));
    }

    /**
     * Adiciona um jogo Premium a loja
     * 
     * @param String nome
     * @param String descricao
     * @param Double preco
     */
    public void cadastrarPremium(String nome, String descricao, double preco) {
        this.cadastraJogo(new Premium(nome, descricao, preco));
    }

    /**
     * Adiciona um jogo Regular a loja
     * 
     * @param String nome
     * @param String descricao
     * @param Double preco
     * @param Double modificador
     */
    public void cadastrarRegular(String nome, String descricao, double preco, double modificador) {
        this.cadastraJogo(new Regular(nome, descricao, preco, modificador));
    }

    /**
     * Adiciona um jogo Promocao a loja
     * 
     * @param String nome
     * @param String descricao
     * @param Double preco
     * @param Double modificador
     */
    public void cadastrarPromocao(String nome, String descricao, double preco, double modificador) {
        this.cadastraJogo(new Promocao(nome, descricao, preco, modificador));
    }

    /**
     * Adiciona um cliente Cadastrado a loja
     * 
     * @param String nome
     * @param int    categoria
     * @param String nomeUsuario
     * @param String senha
     * @param String email
     */
    public void clienteCadastrado(String nome, int categoria, String nomeUsuario, String senha, String email) {
        this.cadastraCliente(new Cadastrado(nome, nomeUsuario, senha, email));
    }

    /**
     * Adiciona um cliente Fanatico a loja
     * 
     * @param String nome
     * @param int    categoria
     * @param String nomeUsuario
     * @param String senha
     */
    public void clienteFanatico(String nome, int categoria, String nomeUsuario, String senha) {
        this.cadastraCliente(new Fanatico(nome, nomeUsuario, senha));
    }

    /**
     * Adiciona um cliente Empolgado a loja
     * 
     * @param String nome
     * @param int    categoria
     * @param String nomeUsuario
     * @param String senha
     */
    public void clienteEmpolgado(String nome, int categoria, String nomeUsuario, String senha) {
        this.cadastraCliente(new Empolgado(nome, nomeUsuario, senha));
    }

    /**
     * Realiza a gravacao dos dados da loja em um determinado arquivo
     */
    public void gravaDados() {
        try {
            this.gravaDadosCliente();
            this.gravaDadosJogos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Realiza a gravacao dos dados de clientes da loja em um determinado arquivo
     * 
     * @throws IOException
     */
    public void gravaDadosCliente() throws IOException {
        FileOutputStream fout = new FileOutputStream(arquivoClientes);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        for (Cliente c : this.clientes) {
            oos.writeObject(c);
        }
        oos.close();
    }

    /**
     * Realiza a gravacao dos dados de jogos da loja em um determinado arquivo
     * 
     * @throws IOException
     */
    public void gravaDadosJogos() throws IOException {
        FileOutputStream fout = new FileOutputStream(arquivojogos);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        for (Jogo j : this.jogos) {
            oos.writeObject(j);
        }
        oos.close();
    }

    /**
     * Realiza a leitura das informacoes armazenadas previamente da loja em um
     * determiado arquivo
     */
    public void leDados() {
        try {
            this.jogos = this.leDadosJogos();
            this.clientes = this.leDadosClientes();
        } catch (Exception e) {
            System.out.println("Não foi possível carregar os dados.");
        }
    }

    /**
     * Realiza a leitura dos dados de clientes previamente armazenados em um
     * determinado arquivo
     * 
     * @return LinkedList<Cliente>
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public LinkedList<Cliente> leDadosClientes() throws IOException, ClassNotFoundException {
        LinkedList<Cliente> clientes = new LinkedList<>();
        FileInputStream dados = new FileInputStream(arquivoClientes);
        ObjectInputStream obj = new ObjectInputStream(dados);
        while (dados.available() != 0) {
            Cliente novo = (Cliente) obj.readObject();
            clientes.add(novo);
        }
        obj.close();
        return clientes;
    }

    /**
     * Realiza a leitura dos dados de jogos previamente armazenados em um
     * determinado arquivo
     * 
     * @return LinkedList<Jogo>
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public LinkedList<Jogo> leDadosJogos() throws IOException, ClassNotFoundException {
        LinkedList<Jogo> jogos = new LinkedList<>();
        FileInputStream dados = new FileInputStream(arquivojogos);
        ObjectInputStream obj = new ObjectInputStream(dados);
        while (dados.available() != 0) {
            Jogo novo = (Jogo) obj.readObject();
            jogos.add(novo);
        }
        obj.close();
        return jogos;
    }

    /**
     * Obtem os jogos da loja
     * 
     * @return LinkedList<Jogo>
     */
    public LinkedList<Jogo> listarJogos() {
        return this.jogos;
    }

    /**
     * Obtem os clientes da loja
     * 
     * @return LinkedList<Cliente>
     */
    public LinkedList<Cliente> listarClientes() {
        return this.clientes;
    }

    /**
     * Retorna um relatorio com o historico de compras dos clientes da loja
     * 
     * @return String
     */
    public String historicoClientes() {
        return this.clientes
                .stream()
                .map(Cliente::dados)
                .reduce("", (subtotal, dados) -> subtotal + dados);
    }

    /**
     * Busca por um determinado jogo pelo seu nome
     * 
     * @param String nome
     * @return Jogo
     * @throws NameNotFoundException
     */
    public Jogo buscaApenasUmJogoPorNome(String nome) throws NameNotFoundException {
        return this.jogos
                .stream()
                .filter((jogo) -> jogo.getNome().equals(nome))
                .findFirst()
                .orElseThrow(() -> new NameNotFoundException());
    }

    /**
     * Busca por um determinado cliente por nome
     * 
     * @param String nome
     * @return Cliente
     * @throws NameNotFoundException
     */
    public Cliente buscaApenasUmClientePorNome(String nome) throws NameNotFoundException {
        return this.clientes
                .stream()
                .filter((cliente) -> cliente.getNome().equals(nome))
                .findFirst()
                .orElseThrow(() -> new NameNotFoundException());
    }

    /**
     * Calcula o valor total gasto pelos clientes nas compras
     * 
     * @return Double
     */
    public Double valorTotalVendido() {
        return this.clientes
                .stream()
                .mapToDouble((cliente) -> cliente.valorTotal())
                .sum();
    }

    /**
     * Calcula o total de compras realizadas na loja
     * 
     * @return int
     */
    public int quantidadeCompras() {
        return this.clientes
                .stream()
                .mapToInt((cliente) -> cliente.quantidadeCompras())
                .sum();
    }

    /**
     * Calcula o valor medio gasto nas compras da loja
     * 
     * @return Double
     */
    public Double valorMedioCompras() {
        if (this.quantidadeCompras() == 0) {
            return 0d;
        }
        return this.valorTotalVendido() / this.quantidadeCompras();
    }

    /**
     * Obtem o jogo mais vendido da loja
     * 
     * @return Jogo
     */
    public Jogo jogoMaisVendido() {
        return this.jogos
                .stream()
                .max((jogo1, jogo2) -> this.vezesJogoComprado(jogo1) - this.vezesJogoComprado(jogo2))
                .orElse(null);
    }

    /**
     * Obtem os jogos mais vendidos da loja caso existam varios jogos com a maior
     * quantidade
     * 
     * @return LinkedList<Jogo>
     */
    public LinkedList<Jogo> jogosMaisVendidos() {
        return this.jogos
                .stream()
                .filter((jogo) -> this.vezesJogoComprado(jogo) == this.vezesJogoComprado(this.jogoMaisVendido()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Retorna um relatorio com os jogos mais vendidos
     * 
     * @return String
     */
    public String relatorioJogosMaisVendidos() {
        return this.jogosMaisVendidos()
                .stream()
                .map((jogo) -> jogo.toString() + " (Comprado " + this.vezesJogoComprado(jogo) + " vezes)")
                .reduce("", (subtotal, relatorioJogo) -> subtotal + "\n" + relatorioJogo);
    }

    /**
     * Obtem o jogo menos vendido da loja
     * 
     * @return Jogo
     */
    public Jogo jogoMenosVendido() {
        return this.jogos
                .stream()
                .min((jogo1, jogo2) -> this.vezesJogoComprado(jogo1) - this.vezesJogoComprado(jogo2))
                .get();
    }

    /**
     * Obtem os jogos menos vendidos da loja caso existam varios jogos com a menor
     * quantidade
     * 
     * @return LinkedList<Jogo>
     */
    public LinkedList<Jogo> jogosMenosVendidos() {
        return this.jogos
                .stream()
                .filter((jogo) -> this.vezesJogoComprado(jogo) == this.vezesJogoComprado(this.jogoMenosVendido()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Retorna um relatorio com os jogos menos vendidos
     * 
     * @return String
     */
    public String relatorioJogosMenosVendidos() {
        return this.jogosMenosVendidos()
                .stream()
                .map((jogo) -> jogo.toString() + " (Comprado " + this.vezesJogoComprado(jogo) + " vezes)")
                .reduce("", (subtotal, relatorioJogo) -> subtotal + "\n" + relatorioJogo);
    }

    /**
     * Retorna o numero de vezes que um determinado foi comprado na loja
     * 
     * @param Jogo jogo
     * @return int
     */
    public int vezesJogoComprado(Jogo jogo) {
        return this.clientes
                .stream()
                .mapToInt((cliente) -> cliente.vezesJogoComprado(jogo))
                .reduce(0, (subtotal, vezesComprado) -> subtotal + vezesComprado);
    }
}
