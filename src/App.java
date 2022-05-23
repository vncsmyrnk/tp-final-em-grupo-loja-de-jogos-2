import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Set;
import loja.Cliente;
import loja.Compra;
import loja.Jogo;

public class App {

    static String arqDados;
    
    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    /**
     * Menu para a Loja de Jogos
     * @param teclado Scanner de leitura
     * @return Opção do usuário (int)
     */    
    public static int menu(Scanner teclado){
        limparTela();
        System.out.println("XULAMBGAMES");
        System.out.println("==========================");
        System.out.println("Informe a opção desejada");
        System.out.println("1 - Cadastro de jogos");
        System.out.println("2 - Cadastro de clientes");
        System.out.println("3 - Cadastro de compras");
        System.out.println("0 - Sair");

        int opcao = teclado.nextInt();
        teclado.nextLine();
        return opcao;
    }

    /**
     * Pausa para leitura de mensagens em console
     * @param teclado Scanner de leitura
     */
    static void pausa(Scanner teclado){
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    /**
     * Gravação serializada do conjunto de clientes
     * @param clientes Conjunto de clientes a salvar
     * @throws IOException Em caso de erro na escrita ou abertura do arquivo (propagação de exceção)
     */
    public static void gravarClientes(Set<Cliente> clientes) throws IOException{
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arqDados));
        for (Cliente cliente : clientes) {
            obj.writeObject(cliente);
        }
        obj.close();
    }

    /**
     * Gravação serializada do conjunto de compras
     * @param compras Conjunto de compras a salvar
     * @throws IOException Em caso de erro na escrita ou abertura do arquivo (propagação de exceção)
     */
    public static void gravarCompras(Set<Compra> compras) throws IOException{
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arqDados));
        for (Compra compra : compras) {
            obj.writeObject(compra);
        }
        obj.close();
    }

        /**
     * Gravação serializada do conjunto de compras
     * @param compras Conjunto de compras a salvar
     * @throws IOException Em caso de erro na escrita ou abertura do arquivo (propagação de exceção)
     */
    public static void gravarJogos(Set<Jogo> jogos) throws IOException{
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(arqDados));
        for (Jogo jogo : jogos) {
            obj.writeObject(jogo);
        }
        obj.close();
    }

    public static void main(String[] args) throws Exception {
        int opcao=-1;                               
        Scanner teclado = new Scanner(System.in);
        // Jogo jogoCadastrado = null;
        // Cliente clienteCadastrado = null;                  
        // Compra compraCadastrada = null; 
        
        do{
            opcao = menu(teclado);
            limparTela();
            switch(opcao){
                case 1: System.out.println("Qual a categoria do jogo a ser informado?");
                        System.out.println("1 - Lançamento");
                        System.out.println("2 - Premium");
                        System.out.println("3 - Promoção");
                        System.out.println("3 - Regular");
                    break;
                case 2: System.out.println("Qual a categoria do cliente a ser informado?");
                        System.out.println("1 - Empolgado");
                        System.out.println("2 - Fanático");
                        System.out.println("3 - Cadastrado");
                    break;    
                case 3: 
            }
            pausa(teclado);
        }while(opcao!=0);
    }

}