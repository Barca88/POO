import java.io.Serializable;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.lang.ClassNotFoundException;
import java.lang.IllegalStateException;
import java.time.LocalDate;
import java.util.regex.MatchResult;
import java.io.Console;

public class UMeRApp implements Serializable{
    private static Umer umer;
    private static Menu menuLogado, menuPrincipal, menuRegistar,
            menuMotoristas, menuCliente, menuSolicitaViagem;

    /**
     * Função que faz executar toda a aplicação UMeRApp.
     */
    public static void main(String[] args) {
        String file_name = "umer_estado";
        carregaMenus();
        carregaEstado(file_name);
        apresentarMenu();
        try {
            umer.gravaObj(file_name);
            umer.log("log.txt", true);
        }
        catch (IOException e) {
            System.out.println("Não consegui gravar os dados!");
        }
        System.out.println("Até á proxima!");
    }
    /**
     * Apresenta o menu principal.
     */
    private static void apresentarMenu(){
        int running = 1;

        do {
            if(umer.getUtilizador() != null){
                menuLogado.executa();
                switch(menuLogado.getOpcao()){
                    case 1: menu();
                            break;
                    case 2: terminarSessao();
                            break;
                    case 0: running = 0;
                }
            }
            else{
                menuPrincipal.executa();
                switch (menuPrincipal.getOpcao()) {
                    case 1: registo();
                            break;
                    case 2: iniciarSessao();
                            if((umer.getUtilizador() instanceof Motorista) && !(umer.existeTaxi())) associarMotoristaViatura();
                            break;
                    case 3: menu();
                            break;
                    case 0: running = 0;
                }
            }
        } while (running!=0);
    }
    /**
     * Apresenta o Menu consoante o tipo de utilizador com sessão iniciada.
     */
    private static void menu(){
        if(umer.getUtilizador() != null){
            Utilizador util = umer.getUtilizador();
            if(util.getClass().getSimpleName().equals("Cliente"))
                runMenuCliente();
            else runMenuMotorista();
        }
    }

    /**
     * Carrega todos os menus para apresentar.
     */
    private static void carregaMenus() {
        String [] menu0 = {"Menu",
                           "Terminar sessão"};
        String [] menu1 = {"Registar Utilizador",
                           "Iniciar sessão",
                           "Menu"};
        String [] menu2 = {"Motorista",
                           "Cliente"};
        String [] menu3 = {"Disponivel",
                           "Não Disponivel",
                           "Histórico de Viagens",
                           "Numero de Kms realizados",
                           "Classificação",
                           "Total Faturado na Viatura"
                           };
        String [] menu4 = {"Solicitar Viagem",
                           "Total Gasto em Viagens",
                           "Histórico de Viagens"};
        String [] menu5 = {"Taxi Mais Próximo",
                           "Pedir Um Taxi"};

        menuLogado = new Menu(menu0);
        menuPrincipal = new Menu(menu1);
        menuRegistar = new Menu(menu2);
        menuMotoristas = new Menu(menu3);
        menuCliente = new Menu(menu4);
        menuSolicitaViagem = new Menu(menu5);
    }
    /**
     * Executar o menu para utilizadores Clientes
     */
    private static void runMenuCliente(){
        do{
            menuCliente.executa();
            switch(menuCliente.getOpcao()){
                case 1: runMenuSolicitaViagem();
                        break;
                case 2: clienteTotalGasto();
                        break;
                case 3: runMenuHistorico();
                        break;
            }
        }while(menuCliente.getOpcao()!=0);
    }
    /*
     * Executar menu para Motoristas
     */
    private static void runMenuMotorista(){
        do{
            menuMotoristas.executa();
            switch(menuMotoristas.getOpcao()){
                case 1: umer.disponibilidade(true);
                        System.out.println("Disponivel");
                        break;
                case 2: umer.disponibilidade(false);
                        System.out.println("Indisponivel");
                        break;
                case 3: runMenuHistorico();
                        break;
                case 4: numeroDeKmsRealizados();
                        break;
                case 5: motoristaClassificacao();
                        break;
                case 6: totalFaturadoNaViatura();
                        break;
                }
            }while(menuMotoristas.getOpcao()!=0);
    }
    private static void numeroDeKmsRealizados(){
        System.out.println("Numero de Kilometros efectuado: " + umer.numeroDeKmsRealizados() + "Kms");
    }
    private static void motoristaClassificacao(){
        System.out.println("A sua Classificação e: " + umer.motoristaClassificacao());
    }
    private static void clienteTotalGasto(){
        System.out.println("Total gasto em viagens: " + umer.totalGasto());
    }
    private static void totalFaturadoNaViatura(){
        System.out.println("Total Faturado: " + umer.totalFaturado());
    }
    /**
     * Mostra o historico entre 2 datas
     */
    private static void runMenuHistorico(){
        LocalDate inicio, fim;
        System.out.println("Insira a Data mais antiga:\n");
        inicio = pedirData();
        System.out.println("Insira a Data mais recente\n");
        fim = pedirData();
        for(Viagem v : umer.getViagensData(inicio,fim)){
            System.out.println(v.toString());
        }

    }
    /**
    * Funçao para pedir um LocalDateTime
    */
    private static LocalDate pedirData(){
        String str;
        Scanner pt = new Scanner(System.in);
        System.out.println("Formato:\nAAAA-MM-DD");
        str = pt.nextLine();
        LocalDate dt = null;
        try{
            dt = LocalDate.parse(str);
            System.out.println(dt);
        } catch(IllegalStateException e){
            System.err.println("Invalid date-time format.");
        }
        return dt;
    }

    /**
    * Executar o menu para utilizadores Clientes
    */
    private static void runMenuSolicitaViagem(){
        Scanner pt = new Scanner(System.in);
        double x,y;
        String matricula;

        System.out.println("Insira as Coordenadas do Destino");
        System.out.println("Insira o X.X: ");
        x = pt.nextDouble();
        System.out.println("Insira o Y.Y: ");
        y = pt.nextDouble();
        Localizacao local = new Localizacao(x,y);
        do{
            menuSolicitaViagem.executa();
            switch(menuSolicitaViagem.getOpcao()){
                case 1: umer.solicitarViagem(local);             
                        break;
                case 2: System.out.println("Insira a Matricula do Taxi: ");
                        matricula = pt.nextLine();
                        try{
                        umer.solicitarViagem(local, matricula);
                        }
                        catch (NaoExisteTaxiException | MotoristaNaoDispException e){
                            System.out.println("Taxi Inexistente/Motorista Indesponivel de Momento");
                        }
                        break;
                    }
        }while(menuSolicitaViagem.getOpcao()!=0);
    }
    /**
     * Carrega o estado da aplicação da última vez que esta foi fechada.
     */
    private static void carregaEstado(String fich){
        try {
            umer = Umer.leObj(fich);
        }
        catch (IOException e){
            umer = new Umer();
            System.out.println("Error 404!\nErro de leitura do ficheiro.");
        }
        catch (ClassNotFoundException e){
            umer = new Umer();
            System.out.println("Error 404!\nFicheiro com formato desconhecido do ficheiro.");
        }
        catch (ClassCastException e){
            umer = new Umer();
            System.out.println("Error 404!\nErro de formato do ficheiro.");
        }
    }
    /**
     * Registo na UMeRApp.
     */
    private static void registo(){
        Utilizador user = null;
        Scanner pt = new Scanner(System.in);

        menuRegistar.executa();
        if(menuRegistar.getOpcao() !=0){
            String nome, email, password, morada, dataNasc;

            System.out.print("Nome: ");
            nome = pt.nextLine();
            System.out.print("Email: ");
            email = pt.nextLine();
            System.out.print("Password: ");
            password = pt.nextLine();
            System.out.print("Morada: ");
            morada = pt.nextLine();
            System.out.print("Data de nascimento: ");
            dataNasc = pt.nextLine();

            switch(menuRegistar.getOpcao()){
                case 1: user = new Motorista(nome,email,password,morada,dataNasc,0,0,0,true);
                        break;
                case 2: user = new Cliente(null,nome,email,password,morada,dataNasc,0.0);
                        break;
            }
            try{ umer.registarUtilizador(user);
            } catch(UtilizadorExistenteException e){
                System.out.println("Este utizador já existe!");
            }
        }else System.out.println("Registo cancelado!");
        pt.close();
    }
    /**
     * Funcao que associa motorista a viatura.
     */
    public static void associarMotoristaViatura(){
        Scanner pt = new Scanner(System.in);
        String matricula;
        double x,y;
        System.out.println("Matricula do Veiculo: ");
        matricula = pt.nextLine();
        System.out.println("Insira a posiçao da sua Viatura");
        System.out.println("Insira X.X: ");
        x = pt.nextDouble();
        System.out.println("Insira Y.Y: " );
        y = pt.nextDouble();
        Localizacao gps = new Localizacao(x,y);
        Taxi taxi = new Taxi(matricula,(Motorista) umer.getMotorista(),70,4.5,0,gps);
        try{
            umer.registarViatura(taxi);
        }
        catch(ViaturaExistenteException e){
        System.out.println("Viatura ja registada");
        }   
    }
    /**
     * Inicio de sessão na UMeRApp.
     */
    private static void iniciarSessao(){
        Scanner pt = new Scanner(System.in);
        Console console = System.console();
        String email, password;

        System.out.print("E-mail: ");
        email = pt.nextLine();
    
        System.out.print("Password: ");
        password = console.readPassword().toString();
    

        try{ umer.iniciaSessao(email,password);
        }catch(SemAutorizacaoException e){
            System.out.println("Credenciais Incorretas, tente outra vez");
        }

        pt.close();
    }
    /**
     * Fechar sessão na Aplicaçao.
     */
    private static void terminarSessao(){
        umer.terminaSessao();
    }
}
