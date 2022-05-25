package loja;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

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

    public void cadastrarLancamento(String nome, String descricao, double preco) {
        this.jogos.add(new Lancamento(nome, descricao, preco));
    }

    public void cadastrarPremium(String nome, String descricao, double preco) {
        this.jogos.add(new Premium(nome, descricao, preco));
    }

    public void cadastrarRegular(String nome, String descricao, double preco, double modificador) {
        this.jogos.add(new Regular(nome, descricao, preco, modificador));
    }

    public void cadastrarPromocao(String nome, String descricao, double preco, double modificador) {
        this.jogos.add(new Promocao(nome, descricao, preco, modificador));
    }

    public void clienteCadastrado(String nome, int categoria, String nomeUsuario, String senha, String email) {
        this.clientes.add(new Cadastrado(nome, nomeUsuario, senha, email));
    }

    public void clienteFanatico(String nome, int categoria, String nomeUsuario, String senha) {
        this.clientes.add(new Fanatico(nome, nomeUsuario, senha));
    }

    public void clienteEmpolgado(String nome, int categoria, String nomeUsuario, String senha) {
        this.clientes.add(new Empolgado(nome, nomeUsuario, senha));
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
}
