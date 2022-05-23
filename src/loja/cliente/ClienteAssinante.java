package loja.cliente;

import loja.Compra;

public abstract class ClienteAssinante {
    private Double valorMensal;
    private Double valorDesconto;

    public abstract Double valorFinalCompra(Compra c);

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(Double valorMensal) {
        this.valorMensal = valorMensal;
    }
}
