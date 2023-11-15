package com.lojaJogos.cliente;

import com.lojaJogos.Cliente;
import com.lojaJogos.Compra;

/**
 * Representa uma cliente Assinante
 */
public abstract class ClienteAssinante extends Cliente {
    protected Double valorMensal;
    protected Double valorDesconto;

    public ClienteAssinante(String nome, String nomeUsuario, String senha, Double valorMensal, Double valorDesconto) {
        super(nome, nomeUsuario, senha);
        this.valorMensal = valorMensal;
        this.valorDesconto = valorDesconto;
    }

    /**
     * Calcula o valor de deconto em uma compra para um cliente Assinante
     * 
     * @param Compra c
     * @return Double
     */
    public final Double valorDescontoCompra(Compra c) {
        return c.valor() * this.valorDesconto;
    }

    /**
     * Calcula o valor a ser pago em uma determinada compra para um cliente
     * Assinante
     * 
     * @param Compra c
     * @return Double
     */
    public Double valorFinalCompra(Compra c) {
        return c.valor() - this.valorDescontoCompra(c);
    }
}
