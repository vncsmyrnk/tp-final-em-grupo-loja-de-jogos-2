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

public class Loja {

    private LinkedList<Jogo> jogos;
    private LinkedList<Cliente> clientes;
    private static final String arquivoClientes = "./dados/clientes.bin";
    private static final String arquivojogos = "./dados/jogos.bin";

    public Loja() {
        this.jogos = new LinkedList<>();
        this.clientes = new LinkedList<>();

    }

    public void cadastraCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void cadastraJogo(Jogo jogo) {
        this.jogos.add(jogo);
    }

    public void cadastrarLancamento(String nome, String descricao, double preco) {
        this.cadastraJogo(new Lancamento(nome, descricao, preco));
    }

    public void cadastrarPremium(String nome, String descricao, double preco) {
        this.cadastraJogo(new Premium(nome, descricao, preco));
    }

    public void cadastrarRegular(String nome, String descricao, double preco, double modificador) {
        this.cadastraJogo(new Regular(nome, descricao, preco, modificador));
    }

    public void cadastrarPromocao(String nome, String descricao, double preco, double modificador) {
        this.cadastraJogo(new Promocao(nome, descricao, preco, modificador));
    }

    public void clienteCadastrado(String nome, int categoria, String nomeUsuario, String senha, String email) {
        this.cadastraCliente(new Cadastrado(nome, nomeUsuario, senha, email));
    }

    public void clienteFanatico(String nome, int categoria, String nomeUsuario, String senha) {
        this.cadastraCliente(new Fanatico(nome, nomeUsuario, senha));
    }

    public void clienteEmpolgado(String nome, int categoria, String nomeUsuario, String senha) {
        this.cadastraCliente(new Empolgado(nome, nomeUsuario, senha));
    }

    public void gravaDados() {
        try {
            this.gravaDadosCliente();
            this.gravaDadosJogos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void gravaDadosCliente() throws IOException {
        FileOutputStream fout = new FileOutputStream(arquivoClientes);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        for (Cliente c : this.clientes) {
            oos.writeObject(c);
        }
        oos.close();
    }

    public void gravaDadosJogos() throws IOException {
        FileOutputStream fout = new FileOutputStream(arquivojogos);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        for (Jogo j : this.jogos) {
            oos.writeObject(j);
        }
        oos.close();
    }

    public void leDados() {
        try {
            this.jogos = this.leDadosJogos();
            this.clientes = this.leDadosClientes();
        } catch (Exception e) {
            System.out.println("Não foi possível carregar os dados.");
        }
    }

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

    public LinkedList<Jogo> listarJogos() {
        return this.jogos;
    }

    public LinkedList<Cliente> listarClientes() {
        return this.clientes;
    }

    public String historicoClientes() {
        return this.clientes
                .stream()
                .map(Cliente::dados)
                .reduce("", (subtotal, dados) -> subtotal + dados);
    }

    public Jogo buscaApenasUmJogoPorNome(String nome) throws NameNotFoundException {
        return this.jogos
                .stream()
                .filter((jogo) -> jogo.getNome().equals(nome))
                .findFirst()
                .orElseThrow(() -> new NameNotFoundException());
    }

    public Cliente buscaApenasUmClientePorNome(String nome) throws NameNotFoundException {
        return this.clientes
                .stream()
                .filter((cliente) -> cliente.getNome().equals(nome))
                .findFirst()
                .orElseThrow(() -> new NameNotFoundException());
    }

    public Double valorTotalVendido() {
        return this.clientes
                .stream()
                .mapToDouble((cliente) -> cliente.valorTotal())
                .sum();
    }

    public int quantidadeCompras() {
        return this.clientes
                .stream()
                .mapToInt((cliente) -> cliente.quantidadeCompras())
                .sum();
    }

    public Double valorMedioCompras() {
        if (this.quantidadeCompras() == 0) {
            return 0d;
        }
        return this.valorTotalVendido() / this.quantidadeCompras();
    }

    public Jogo jogoMaisVendido() {
        return this.jogos
                .stream()
                .max((jogo1, jogo2) -> this.vezesJogoComprado(jogo1) - this.vezesJogoComprado(jogo2))
                .orElse(null);
    }

    public LinkedList<Jogo> jogosMaisVendidos() {
        return this.jogos
                .stream()
                .filter((jogo) -> this.vezesJogoComprado(jogo) == this.vezesJogoComprado(this.jogoMaisVendido()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public String relatorioJogosMaisVendidos() {
        return this.jogosMaisVendidos()
                .stream()
                .map((jogo) -> jogo.toString() + " (Comprado " + this.vezesJogoComprado(jogo) + " vezes)")
                .reduce("", (subtotal, relatorioJogo) -> subtotal + "\n" + relatorioJogo);
    }

    public LinkedList<Jogo> jogosMenosVendidos() {
        return this.jogos
                .stream()
                .filter((jogo) -> this.vezesJogoComprado(jogo) == this.vezesJogoComprado(this.jogoMenosVendido()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public String relatorioJogosMenosVendidos() {
        return this.jogosMenosVendidos()
                .stream()
                .map((jogo) -> jogo.toString() + " (Comprado " + this.vezesJogoComprado(jogo) + " vezes)")
                .reduce("", (subtotal, relatorioJogo) -> subtotal + "\n" + relatorioJogo);
    }

    public Jogo jogoMenosVendido() {
        return this.jogos
                .stream()
                .min((jogo1, jogo2) -> this.vezesJogoComprado(jogo1) - this.vezesJogoComprado(jogo2))
                .get();
    }

    public int vezesJogoComprado(Jogo jogo) {
        return this.clientes
                .stream()
                .mapToInt((cliente) -> cliente.vezesJogoComprado(jogo))
                .reduce(0, (subtotal, vezesComprado) -> subtotal + vezesComprado);
    }
}
