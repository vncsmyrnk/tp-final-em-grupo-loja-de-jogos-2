package loja.cliente;

import loja.Cliente;
import loja.Compra;

public abstract class ClienteAssinante extends Cliente {
    protected Double valorMensal;
    protected Double valorDesconto;

    public void setValorMensal(Double valorMensal) {
        this.valorMensal = valorMensal;
    }

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

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getValorMensal() {
        return valorMensal;
    }

}
