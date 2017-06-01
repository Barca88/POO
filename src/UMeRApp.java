import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;

public class UMeRApp{
    private static Umer umer;
    private static Menu menuPrincipal, menuRegistar, menuMotoristas,
                   menuClientes, menuLogado;



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
        System.out.println("Volte sempre!");
    }

    /**
     * Apresenta o menu principal.
     */
    private static void apresentarMenu(){
        int running = 1;

        do {
            if(umer.getUser() != null){
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

        if(umer.getUser() == null)
            runMenu();
        else{
            Utilizador util = umer.getUser();
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
        String [] menu3 = {"Disponibilidade",
                           "Histórico de Viagens",
                           "Total Faturado na Viatura",
                           "Associar-me a Viatura"};
        String [] menu4 = {"Solicitar Viagem",
                           "Total Gasto em Viagens",
                           "Histórico de Viagens"};

        menuLogado = new Menu(menu0);
        menuPrincipal = new Menu(menu1);
        menuRegistar = new Menu(menu2);
        menuMotoristas = new Menu(menu3);
        menuCliente = new Menu(menu4);
    }


    /**
     * Carrega o estado da aplicação da última vez que esta foi fechada.
     * @param fich
     */
    private static void carregaEstado(String fich){
        try {
            umer = Umer.leObj(fich);
        }
        catch (IOException e) {
            umer = new Umer();
            System.out.println("Error 404!\nErro de leitura do ficheiro.");
        }
        catch (ClassNotFoundException e) {
            umer = new Umer();
            System.out.println("Error 404!\nFicheiro com formato desconhecido do ficheiro.");
        }
        catch (ClassCastException e) {
            umer = new Umer();
            System.out.println("Error 404!\nErro de formato do ficheiro.");
        }
    }
    /**
     * Registo na UMeRApp.
     */
    private static void registo(){
        Utilizador user;
        Scanner pt = new Scanner(System.in);

        menuRegistar.executa();
        if(menuRegistar.getOpcao() !=0){
            String nome,email,password,morada,dataNasc,info;
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
                case 1: user = new Motorista(null,nome,email,password,morada,data,0,0,0,false);
                        break;
                case 2: user = new Cliente(null,nome,email,password,morada,data,0.0);
                        break;
                default: user = new Utilizador();
            }
            try{
                umer.registarUtilizador(user);
            }
            catch(UtilizadorExistenteException e){
                System.out.println("Este utizador já existe!");
            }
        }
        else System.out.println("Registo cancelado!");
        pt.close();
    }
    /**
     * Inicio de sessão na ImobiliariaApp.
     */
    private static void iniciarSessao(){
        Scanner pt = new Scanner(System.in);
        String email,password;
        System.out.print("E-mail: ");
        email = pt.nextLine();
        System.out.print("Password: ");
        password = pt.nextLine();

        try{
            umer.iniciaSessao(email,password);
        }
        catch(SemAutorizacaoException e){
            System.out.println(e.getMessage());
        }

        pt.close();
    }

    /**
     * Fechar sessão na ImobiliariaApp.
     */
    private static void terminarSessao(){
        umer.terminaSessao();
    }
//====================================================
    /**
     * Executar o menu para utilizadores não registados na ImobiliariaApp.
     */
     private static void running_menu_comprador(){
        do{
            menuCliente.executa();
            switch(menuCliente.getOpcao()){
                case 1: consultarImoveisTipo();
                        break;
                case 2: habitaveisPreco();
                        break;
                case 3: imoveisVendedores();
                        break;
            }
        }while(menuCliente.getOpcao()!=0);
    }

    /**
     * Executar menu para vendedores.
     */
    private static void running_menu_vendedor(){
        do{
            menuMotoristas.executa();
            switch(menuMotoristas.getOpcao()){
                case 1: adicionaImovel();
                        break;
                case 2: consultarImoveis();
                        break;
                case 3: alterarEstado();
                        break;
                case 4: topConsultados();
                        break;
                case 5: consultarImoveisTipo();
                        break;
                case 6: habitaveisPreco();
                        break;
                case 7: imoveisVendedores();
                        break;
                case 8: running_menu_leilao_vendedor();
                        break;
            }
        }while(menuMotoristas.getOpcao()!=0);
    }

    /**
     * Executar menu para compradores registados na ImobiliariaApp.
     */
    private static void running_menu_comprador_registado(){
        do{
            menu_comprador_registado.executa();
            switch(menu_comprador_registado.getOpcao()){
                case 1: consultarImoveisTipo();
                        break;
                case 2: habitaveisPreco();
                        break;
                case 3: imoveisVendedores();
                        break;
                case 4: favoritoImovel();
                        break;
                case 5: consultarFavoritos();
                        break;
            }
        }while(menu_comprador_registado.getOpcao()!=0);
    }

    /**
     * Executar menu dos leilões.
     */
    private static void running_menu_leilao_vendedor(){
        do{
            menu_leilao_vendedor.executa();
            switch(menu_leilao_vendedor.getOpcao()){
                case 1: iniciar_leilao();
                        break;
            }

        }while(menu_leilao_vendedor.getOpcao()!=0);
    }
    /**
     * Consultar favoritos de um comprador.
     */
    private static void consultarFavoritos(){

        Map<String,Imovel> favoritos = new HashMap<String,Imovel>();
        Comprador utilizador = (Comprador) umer.getUser();
        favoritos = utilizador.getFavoritos();
        for(Imovel i : favoritos.values())
            System.out.println(i);

    }

    /**
     * Definir um Imóvel como favorito de um comprador.
     */
    private static void favoritoImovel(){
        Scanner pt = new Scanner(System.in);
        String idImovel;
        System.out.print("ID Imóvel: ");
        idImovel = pt.nextLine();
        try{
            umer.setFavorito(idImovel);
        }
        catch(SemAutorizacaoException | ImovelInexistenteException e){
            System.out.println(e.getMessage());
        }
        pt.close();
    }




    /**
     * Alterar o estado de um determinado Imóvel existente.
     */
    private static void alterarEstado(){
        String id,estado;
        Scanner pt = new Scanner(System.in);
        System.out.print("ID do Imóvel: ");
        id = pt.nextLine();
        System.out.print("Estado: ");
        estado = pt.nextLine();
        try{
            umer.setEstado(id,estado);
        }
        catch(ImovelInexistenteException | SemAutorizacaoException |
        EstadoInvalidoException e){
            System.out.println(e.getMessage());
        }
        pt.close();
    }

    /**
     * Adicionar um Imóvel à Imobiliária.
     */
    private static void adicionaImovel(){
        Imovel imovel = criaImovel();
        if(imovel!=null){
            try{
                umer.registaImovel(imovel);
            }
            catch(ImovelExisteException | SemAutorizacaoException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Criar um Imóvel para ser adicionado à Imobiliária.
     * @return
     
    private static Imovel criaImovel(){
        Imovel imovel = null;
        Scanner pt = new Scanner(System.in);

        menu_cria_imovel.executa();
        if(menu_cria_imovel.getOpcao() !=0){
            String rua,estado;
            Double preco, preco_Minimo; String id;
            System.out.print("Rua: ");
            rua = pt.nextLine();
            preco = inputPreco();
            preco_Minimo = inputPrecoMinimo();
            estado = "em venda";
            id = "ID_" + umer.getId();

            switch(menu_cria_imovel.getOpcao()){
                case 1: double area_loja; boolean wc_loja; String tipo_Negocio,wc_string;
                        int numero_loja;
                        area_loja = inputArea();
                        wc_loja = inputWC();
                        tipo_Negocio = inputTipoNegocio();
                        numero_loja = inputNumero();
                        imovel = new Loja(id,rua,preco,preco_Minimo,estado,null,
                        area_loja,wc_loja,tipo_Negocio,numero_loja);
                        break;

                case 2: double area_loja_hab; boolean wc_loja_hab,garagem_loja_hab;
                        String tipo_negocio_loja_hab, tipo_loja_hab;
                        int numero_loja_hab, quartos_loja_hab, casa_banho_loja_hab,
                        andar_loja_hab;
                        area_loja_hab = inputArea();
                        wc_loja_hab = inputWC();
                        tipo_negocio_loja_hab = inputTipoNegocio();
                        numero_loja_hab = inputNumero();
                        tipo_loja_hab = inputTipo();
                        quartos_loja_hab = inputQuartos();
                        casa_banho_loja_hab = inputCasasBanho();
                        andar_loja_hab = inputAndar();
                        garagem_loja_hab = inputGaragem();
                        imovel = new LojaHabitavel(id,rua,preco,preco_Minimo,estado,
                        null,area_loja_hab,wc_loja_hab,tipo_negocio_loja_hab,numero_loja_hab,
                        tipo_loja_hab,quartos_loja_hab,casa_banho_loja_hab,andar_loja_hab,
                        garagem_loja_hab);
                        break;

                case 3: String tipo_apart; double area_apart; int quartos_apart,
                        casa_banho_apart,andar_apart, numero_apart; boolean garagem_apart;
                        tipo_apart = inputTipo();
                        area_apart = inputArea();
                        quartos_apart = inputQuartos();
                        casa_banho_apart = inputCasasBanho();
                        numero_apart = inputNumero();
                        andar_apart = inputAndar();
                        garagem_apart = inputGaragem();
                        imovel = new Apartamento(id,rua,preco,preco_Minimo,estado,null,
                        tipo_apart,area_apart,quartos_apart,casa_banho_apart,numero_apart,andar_apart,
                        garagem_apart);
                        break;

                case 4: String tipo_moradia; double area_moradia,area_coberta_moradia,
                        area_terreno_moradia; int quartos_moradia,casas_banho_moradia,
                        numero_moradia;
                        tipo_moradia = inputTipo();
                        area_moradia = inputArea();
                        area_coberta_moradia = inputAreaCoberta();
                        area_terreno_moradia = inputAreaTerreno();
                        quartos_moradia = inputQuartos();
                        casas_banho_moradia = inputCasasBanho();
                        numero_moradia = inputNumero();
                        imovel = new Moradia(id,rua,preco,preco_Minimo,estado,null,
                        tipo_moradia,area_moradia,area_coberta_moradia,area_terreno_moradia,
                        quartos_moradia,casas_banho_moradia,numero_moradia);
                        break;

                case 5: String tipo_terreno; int area_terreno;
                        float diametro_canalizacoes,carga_eletrica;
                        boolean saneamento;
                        tipo_terreno = inputTipo();
                        area_terreno = (int) inputArea();
                        diametro_canalizacoes = inputCanalizacoes();
                        carga_eletrica = inputCargaEletrica();
                        saneamento = inputSaneamento();
                        imovel = new Terreno(id,rua,preco,preco_Minimo,estado,null,tipo_terreno,
                        diametro_canalizacoes,carga_eletrica,saneamento);
                        break;
            }

        pt.close();
       }
       return imovel;
    }*/
    /**
     * Input de informação para um preço.
     * @return
     */
    private static double inputPreco(){
        double preco;
        System.out.print("Preço: ");
        Scanner pt = new Scanner(System.in);
        try{
            preco = pt.nextDouble();
        }
        catch(InputMismatchException e){
            System.out.println("Preço inválido!");
            preco = inputPreco();
        }
        pt.close();
        return preco;
    }

    /**
     * Input de informação para um preço minimo.
     * @return
     */
     private static double inputPrecoMinimo(){
        double preco;
        System.out.print("Preço Mínimo: ");
        Scanner pt = new Scanner(System.in);
        try{
            preco = pt.nextDouble();
        }
        catch(InputMismatchException e){
            System.out.println("Preço inválido!");
            preco = inputPrecoMinimo();
        } ;
        pt.close();
        return preco;
    }
    /**
     * Input de informação para um número.
     * @return
     */
    private static int inputNumero(){
        int numero;
        System.out.print("Número: ");
        Scanner pt = new Scanner(System.in);
        try{
            numero = pt.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Número inválido!");
            numero = inputNumero();
        }
        pt.close();
        return numero;
    }
   /** private static void runMenu(){
        do{
            menuPrincipal.executa();
            switch(menuPrincipal.getOpcao()){

            }
        }
    }**/
}
