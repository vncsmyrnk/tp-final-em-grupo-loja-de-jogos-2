package loja.cliente;

import loja.Compra;

public abstract class ClienteAssinante {
    private Double valorMensal;
    private Double valorDesconto;

    public abstract Double valorFinalCompra(Compra c);
}
