package loja.cliente;

import loja.Cliente;
import loja.Compra;

public abstract class ClienteAssinante extends Cliente implements ClienteCalculavelValorFinal {
    protected Double valorMensal;
    protected Double valorDesconto;

    public ClienteAssinante(String nome, String nomeUsuario, String senha, Double valorMensal, Double valorDesconto) {
        super(nome, nomeUsuario, senha);
        this.valorMensal = valorMensal;
        this.valorDesconto = valorDesconto;
    }

    public final Double valorDescontoCompra(Compra c) {
        return c.valor() * this.valorDesconto;
    }

    public Double valorFinalCompra(Compra c) {
        return c.valor() - this.valorDescontoCompra(c);
    }
}
