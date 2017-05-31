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

    private Umer() {}

    /**
     * Função que faz executar toda a aplicação UMeRApp.
     */
    public static void main(String[] args) {
        String file_name = "umer_estado";
        carregarMenus();
        initApp(file_name);
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
            if(umer.getUtilizador() != null){
                menuLogado.executa();
                switch(menuLogado.getOpcao()){
                    case 1: menu();
                            break;
                    case 2: fecharSessao();
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

        if(umer.getUtilizador() == null)
            running_menu_comprador();
        else{
            Utilizador util = umer.getUtilizador();
            if(util.getClass().getSimpleName().equals("Vendedor"))
                running_menu_vendedor();
            else running_menu_comprador_registado();
        }
    }

    /**
     * Carrega todos os menus para apresentar.
     */
    private static void carregarMenus() {
        String [] menu0 = {"Menu",
                           "Terminar sessão"};
        String [] menu1 = {"Registar Utilizador",
                           "Iniciar sessão",
                           "Menu"};
        String [] menu2 = {"Motorista",
                           "Cliente"};
        String [] menu3 = {"Disponibilidade",
                           "Histórico de Viagens",
                           "Total Faturado na Viatura"
                           "Associar-me a Viatura"};
        String [] menu4 = {"Solicitar Viagem",
                           "Total Gasto em Viagens",
                           "Histórico de Viagens"};

        menuLogado = new Menu(menu0);
        menuPrincipal = new Menu(menu1);
        menuRegistar = new Menu(menu2);
        menuMotoristas = new Menu(menu3);
        menuCliente = new Menu(menu4);


    /**
     * Carrega o estado da aplicação da última vez que esta foi fechada.
     * @param fich
     */
    private static void initApp(String fich){
        try {
            umer = Imoobiliaria.leObj(fich);
        }
        catch (IOException e) {
            umer = new Imoobiliaria();
            System.out.println("Não consegui ler os dados!\nErro de leitura.");
        }
        catch (ClassNotFoundException e) {
            umer = new Imoobiliaria();
            System.out.println("Não consegui ler os dados!\nFicheiro com formato desconhecido.");
        }
        catch (ClassCastException e) {
            umer = new Imoobiliaria();
            System.out.println("Não consegui ler os dados!\nErro de formato.");
        }
    }

    /**
     * Registo na ImobiliáriaApp.
     */
    private static void registo(){
        Utilizador user;
        Scanner is = new Scanner(System.in);

        menuRegistar.executa();
        if(menuRegistar.getOpcao() !=0){
            String nome,email,password,morada,dataNasc,info;
            System.out.print("Nome: ");
            nome = is.nextLine();
            System.out.print("Email: ");
            email = is.nextLine();
            System.out.print("Password: ");
            password = is.nextLine();
            System.out.print("Morada: ");
            morada = is.nextLine();
            System.out.print("Data de nascimento: ");
            dataNasc = is.nextLine();

            switch(menuRegistar.getOpcao()){
                case 1: user = new Vendedor(email,nome,password,morada,data,null,null);
                        break;
                case 2: user = new Comprador(email,nome,password,morada,data,null);
                        break;
                default: user = new Comprador();
            }
            try{
                umer.registarUtilizador(user);
            }
            catch(UtilizadorExistenteException e){
                System.out.println("Este utizador já existe!");
            }
        }
        else System.out.println("Registo cancelado!");
        is.close();
    }

    /**
     * Inicio de sessão na ImobiliariaApp.
     */
    private static void iniciarSessao(){
        Scanner is = new Scanner(System.in);
        String email,password;
        System.out.print("E-mail: ");
        email = is.nextLine();
        System.out.print("Password: ");
        password = is.nextLine();

        try{
            umer.iniciaSessao(email,password);
        }
        catch(SemAutorizacaoException e){
            System.out.println(e.getMessage());
        }

        is.close();
    }

    /**
     * Fechar sessão na ImobiliariaApp.
     */
    private static void fecharSessao(){
        umer.fechaSessao();
    }

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
     * Iniciar e simular um leilão.
     */
    private static void iniciar_leilao(){
        Scanner is = new Scanner(System.in);
        int tempo;
        Imovel imovel = inputID();
        System.out.print("Tempo (segundos): ");
        tempo = is.nextInt();

        try{
            umer.iniciaLeilao(imovel,tempo);
        }
        catch(SemAutorizacaoException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Consultar favoritos de um comprador.
     */
    private static void consultarFavoritos(){

        Map<String,Imovel> favoritos = new HashMap<String,Imovel>();
        Comprador utilizador = (Comprador) umer.getUtilizador();
        favoritos = utilizador.getFavoritos();
        for(Imovel i : favoritos.values())
            System.out.println(i);

    }

    /**
     * Definir um Imóvel como favorito de um comprador.
     */
    private static void favoritoImovel(){
        Scanner is = new Scanner(System.in);
        String idImovel;
        System.out.print("ID Imóvel: ");
        idImovel = is.nextLine();
        try{
            umer.setFavorito(idImovel);
        }
        catch(SemAutorizacaoException | ImovelInexistenteException e){
            System.out.println(e.getMessage());
        }
        is.close();
    }

    /**
     * Fazer mapeamento de todos os Imóveis e respectivos vendedores.
     */
    private static void imoveisVendedores(){
        Map<Imovel,Vendedor> imoveisVendedores = new TreeMap<Imovel,Vendedor>();
        imoveisVendedores = umer.getMapeamentoImoveis();
        for(Map.Entry<Imovel,Vendedor> entry : imoveisVendedores.entrySet()){
            Imovel i = entry.getKey();
            Vendedor v = entry.getValue();
            System.out.println("\n******************* Vendedor *******************");
            System.out.println(v);
            System.out.println(i);
            System.out.println("************************************************");
        }
    }

    /**
     * Listagem de Imóveis Habitáveis até um determinado preço.
     */
    private static void habitaveisPreco(){
        Scanner is = new Scanner(System.in);
        List<Habitavel> lista = new ArrayList<Habitavel>();
        int preco;
        preco = (int) inputPreco();


        lista = umer.getHabitaveis(preco);
        for(Habitavel i: lista)
            System.out.println(i);

        is.close();
    }

    /**
     * Consultar todos os Imóveis de um determinado tipo.
     */
    private static void consultarImoveisTipo(){
        Scanner is = new Scanner(System.in);
        List<Imovel> lista = new ArrayList<Imovel>();
        String tipo; int preco;
        tipo = inputTipo();
        preco = (int) inputPreco();

        lista = umer.getImovel(tipo,preco);
        for(Imovel i: lista)
            System.out.println(i);

        is.close();
    }

    /**
     * Listagem das consultas feitas aos Imóveis de um determinado vendedor.
     */
    private static void consultarImoveis(){
       List<Consulta> lista = new ArrayList<Consulta>();
       try{
            lista = umer.getConsultas();
        }
        catch(SemAutorizacaoException e){
            System.out.println(e.getMessage());
       }

       for(Consulta c: lista){
            String x = c.toString();
            System.out.println(x);
       }

    }

    /**
     * Listagem de Imóveis com consultas superiores a um determinado número.
     */
    private static void topConsultados(){
        Scanner is = new Scanner(System.in);
        int numero;
        Set<String> lista = new HashSet<String>();
        numero = inputConsultas();
        lista = umer.getTopImoveis(numero);
        for(String i:lista){
            System.out.println(i);
        }
    }

    /**
     * Alterar o estado de um determinado Imóvel existente.
     */
    private static void alterarEstado(){
        String id,estado;
        Scanner is = new Scanner(System.in);
        System.out.print("ID do Imóvel: ");
        id = is.nextLine();
        System.out.print("Estado: ");
        estado = is.nextLine();
        try{
            umer.setEstado(id,estado);
        }
        catch(ImovelInexistenteException | SemAutorizacaoException |
        EstadoInvalidoException e){
            System.out.println(e.getMessage());
        }
        is.close();
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
     */
    private static Imovel criaImovel(){
        Imovel imovel = null;
        Scanner is = new Scanner(System.in);

        menu_cria_imovel.executa();
        if(menu_cria_imovel.getOpcao() !=0){
            String rua,estado;
            Double preco, preco_Minimo; String id;
            System.out.print("Rua: ");
            rua = is.nextLine();
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

        is.close();
       }
       return imovel;
    }

    /**
     * Input de informação para um WC.
     * @return
     */
    private static boolean inputWC(){
        String wc_string; boolean wc;
        System.out.print("Wc (S/N): ");
        Scanner is = new Scanner(System.in);
        wc_string = is.nextLine();
        if(wc_string.equals("S")|| wc_string.equals("s")) wc = true;
        else wc = false;
        is.close();
        return wc;
    }

    /**
     * Input de informação para uma garagem.
     * @return
     */
    private static boolean inputGaragem(){
        String garagem_string; boolean garagem;
        System.out.print("Garagem (S/N): ");
        Scanner is = new Scanner(System.in);
        garagem_string = is.nextLine();
        if(garagem_string.equals("S")|| garagem_string.equals("s")) garagem = true;
        else garagem = false;
        is.close();
        return garagem;
    }

    /**
     * Input de informação para um preço.
     * @return
     */
    private static double inputPreco(){
        double preco;
        System.out.print("Preço: ");
        Scanner is = new Scanner(System.in);
        try{
            preco = is.nextDouble();
        }
        catch(InputMismatchException e){
            System.out.println("Preço inválido!");
            preco = inputPreco();
        }
        is.close();
        return preco;
    }

    /**
     * Input de informação para um preço minimo.
     * @return
     */
     private static double inputPrecoMinimo(){
        double preco;
        System.out.print("Preço Mínimo: ");
        Scanner is = new Scanner(System.in);
        try{
            preco = is.nextDouble();
        }
        catch(InputMismatchException e){
            System.out.println("Preço inválido!");
            preco = inputPrecoMinimo();
        } ;
        is.close();
        return preco;
    }

    /**
     * Input de informação para uma área.
     * @return
     */
    private static double inputArea(){
        double area;
        System.out.print("Área: ");
        Scanner is = new Scanner(System.in);
        try{
            area = is.nextDouble();
        }
        catch(InputMismatchException e){
            System.out.println("Área inválida!");
            area = inputArea();
        }
        is.close();
        return area;
    }

    /**
     * Input de informação para uma área coberta.
     * @return
     */
    private static double inputAreaCoberta(){
        double area;
        System.out.print("Área Coberta: ");
        Scanner is = new Scanner(System.in);
        try{
            area = is.nextDouble();
        }
        catch(InputMismatchException e){
            System.out.println("Área coberta inválida");
            area = inputAreaCoberta();
        }
        is.close();
        return area;
    }

    /**
     * Input de informação para uma área de um terreno.
     * @return
     */
    private static double inputAreaTerreno(){
        double area;
        System.out.print("Área Terreno: ");
        Scanner is = new Scanner(System.in);
        try{
            area = is.nextDouble();
        }
        catch(InputMismatchException e){
            System.out.println("Área do terreno inválida!");
            area = inputAreaTerreno();
        } ;
        is.close();
        return area;
    }

    /**
     * Input de informação para um número.
     * @return
     */
    private static int inputNumero(){
        int numero;
        System.out.print("Número: ");
        Scanner is = new Scanner(System.in);
        try{
            numero = is.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Número inválido!");
            numero = inputNumero();
        }
        is.close();
        return numero;
    }

    /**
     * Input de informação para um número de quartos.
     * @return
     */
    private static int inputQuartos(){
        int quartos;
        System.out.print("Quartos: ");
        Scanner is = new Scanner(System.in);
        try{
            quartos = is.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Número de quartos inválido!");
            quartos = inputQuartos();
        }
        is.close();
        return quartos;
    }

    /**
     * Input de informação para um andar.
     * @return
     */
    private static int inputAndar(){
        int andar;
        System.out.print("Andar: ");
        Scanner is = new Scanner(System.in);
        try{
            andar = is.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Andar inválido!");
            andar = inputAndar();
        }
        is.close();
        return andar;
    }

    /**
     * Input de informação para um número de consultas.
     * @return
     */
    private static int inputConsultas(){
        int consultas;
        System.out.print("Número de consultas: ");
        Scanner is = new Scanner(System.in);
        try{
            consultas = is.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Número de consultas inválido!");
            consultas = inputConsultas();
        }
        is.close();
        return consultas;
    }

    /**
     * Input de informação para um número de casas de banho.
     * @return
     */
    private static int inputCasasBanho(){
        int casas_banho;
        System.out.print("Casas de banho: ");
        Scanner is = new Scanner(System.in);
        try{
            casas_banho = is.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Número de casas de banho inválido!");
            casas_banho = inputCasasBanho();
        }
        is.close();
        return casas_banho;
    }

    /**
     * Input de informação para um tipo de negócio.
     * @return
     */
    private static String inputTipoNegocio(){
        String tipo_negocio;
        System.out.print("Tipo de negócio: ");
        Scanner is = new Scanner(System.in);
        tipo_negocio = is.nextLine();
        is.close();
        return tipo_negocio;
    }

    /**
     * Input de informação para um tipo.
     * @return
     */
    private static String inputTipo(){
        String tipo;
        System.out.print("Tipo: ");
        Scanner is = new Scanner(System.in);
        tipo = is.nextLine();
        is.close();
        return tipo;
    }

    /**
     * Input de informação para o número de canalizações.
     * @return
     */
    private static float inputCanalizacoes(){
        float total;
        System.out.print("Canalizações: ");
        Scanner is = new Scanner(System.in);
        try{
            total = is.nextFloat();
        }
        catch(InputMismatchException e){
            System.out.println("Valor inválido!");
            total = inputCanalizacoes();
        }
        is.close();
        return total;
    }

    /**
     * Input de informação para o número da carga elétrica.
     * @return
     */
    private static float inputCargaEletrica(){
        float total;
        System.out.print("Carga Elétrica: ");
        Scanner is = new Scanner(System.in);
        try{
            total = is.nextFloat();
        }
        catch(InputMismatchException e){
            System.out.println("Valor inválido!");
            total = inputCargaEletrica();
        }
        is.close();
        return total;
    }

    /**
     * Input de informação para o número do saneamento.
     * @return
     */
    private static boolean inputSaneamento(){
        String saneamento_string; boolean saneamento;
        System.out.print("Saneamento (S/N): ");
        Scanner is = new Scanner(System.in);
        saneamento_string = is.nextLine();
        if(saneamento_string.equals("S")|| saneamento_string.equals("s")) saneamento = true;
        else saneamento = false;
        is.close();
        return saneamento;
    }

    /**
     * Input de informação para um id_imóvel.
     * @return
     */
    private static Imovel inputID(){
        Scanner is = new Scanner(System.in);
        String id;
        System.out.print("ID_Imóvel: ");
        id = is.nextLine();
        Imovel imovel;
        try{
            imovel = umer.getImovelLeilao(id);
        }
        catch(ImovelInexistenteException e){
            System.out.println(e.getMessage());
            imovel = inputID();
        }
        return imovel;
    }
}
