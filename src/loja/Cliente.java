package loja;

import java.time.LocalDate;
import java.util.LinkedList;

public abstract class Cliente {
    protected String nome;
    private String nomeUsuario;
    private String senha;
    private LinkedList<Compra> compras;

    public void adicionarCompra(Compra c) {

    }

    public String historico() {

        return "HISTORICO DO CLIENTE\n" + this.toString();
    }

    public String historico(LocalDate data) {



        return "return "HISTORICO DO CLIENTE\n" + this.toString();";
    }

    public String historico(String categoria) {


        return "return "HISTORICO DO CLIENTE\n" + this.toString();";
    }

    public Double valorTotal() {
        return 0d;
    }

    @Override
    public String toString(){
        StringBuilder relat = new StringBuilder();
        relat.append("=====================\n");
        relat.append("Compra== "+this.idCompra+" - "+this.dataCompra.formatarData()+"\n");
        for(Compra compra : this.compras) {
            relat.append(compra.toString()+"\n");
        }
        relat.append("=====================\n");
        relat.append("TOTAL DA COMPRA: R$"+this.valorTotal());
        return relat.toString();
    }
}



