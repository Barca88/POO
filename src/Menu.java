import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu
{
    // variáveis de instância 
    private List<String> opcoes;
    private int op;

    /**
     * Construtor para objetos da classe Menu
     */
    public Menu(String[] opcoes){
        this.opcoes = new ArrayList<String>();
        for(String op: opcoes)
            this.opcoes.add(op);
        this.op = 0;
    }

    /**
     * Função para executar o menu.
     */
    public void executa(){
        do {
            showMenu();
            this.op = lerOpcao();
        }
        while(this.op == -1);
    }
    
    /**
     * Função para mostrar o menu.
     */
    private void showMenu() {
        System.out.println("\n******************* Menu *******************");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print("   "+(i+1));
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("   0 - Sair");
        System.out.println("*********************************************");
    }
    
    /**
     * Função ler uma opção do menu.
     */
    private int lerOpcao() {
        int op; 
        Scanner is = new Scanner(System.in);
        
        System.out.print("Opção: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }
    
    /**
     * Obter opção selecionada.
     * @return 
     */
    public int getOpcao() {
        return this.op;
    }
}

/**
public class Menu {
    // variáveis de instância
    private List<String> opcoes;
    private int op;

    /**
     * Constructor for objects of class Menu
     
    public Menu(String[] opcoes){
        this.opcoes = new ArrayList<String>();
        for (String op : opcoes)
            this.opcoes.add(op);
        this.op = 1;
    }
    /**
     * Método para apresentar o menu e ler uma opção.
     *
     
    public void executa(){
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    /** Apresentar o menu 
    private void showMenu() {
        System.out.println("\n|============= UMeRApp =============| ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }

    /** Ler uma opção válida 
    private int lerOpcao() {
        int op;
        Scanner pt = new Scanner(System.in);

        System.out.print("Opção: ");
        try {op = pt.nextInt();
        }
        catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }

    /**
     * Método para obter a última opção lida
     
    public int getOpcao() {
        return this.op;
    }
}
**/