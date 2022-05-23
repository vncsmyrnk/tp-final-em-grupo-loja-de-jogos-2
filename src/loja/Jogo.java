package loja;

public abstract class Jogo {
    protected double precoOriginal;
    protected double modificadorPreco;
    protected String descricaoCategoria;

    public abstract double valorFinalJogo();


   

    @Override
    public String toString(){
        StringBuilder relat = new StringBuilder();
        relat.append("Nome do jogo== "+this.descricaoCategoria+" - "+this.valorFinalJogo()+"\n");
        return relat.toString();
    }


    
}
