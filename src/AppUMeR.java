import java.io.IOException;);
import java.util.scanner;

public class AppUMeR {

	private static Empresa emp;
	private static Menu menuPrincipal;

	public static void main(String[] args){
		carregarMenus();
		carregarDados();
		imprimeMenuPrincipal();
		try {
			emp.gravar();
		}
		catch(IOException e){
			System.out.println("Não consegui gravar os dados!");
		}
	}

	private static void carregarMenus(){
		String [] principal = {
								"Iniciar Sessão",
								"Registar Utilizador",
								
		};
		String [] motoristas = {

		};
		String [] clientes = {

		};
		String [] registar = {
								"Registar Motorista",
								"Registar Cliente"
		};
		menuMain = new Menu(principal);
		menuMotoristas = new Menu(motoristas);
		menuClientes = new Menu(clientes);
		menuRegistar = new Menu(registar); 
	}

	private static void executaMenuPrincipal() {
		do {
			menuMain.executa();
			switch(menuMain.getOpcao()){
				case 1: sessao();
						break;
				case 2: regiUti();
						break;
			}
		} while (menumain.getOpcao()!= 0);
	}

	private static void executaMenuMotoristas() {

	}

	private static void executaMenuClientes() {

	}

	private static void executaMenuRegistar() {

	}

	private static void carregarDados(){
		try {
			emp = Empresa.initApp();
		}
		catch (IOException e){
			emp = new Empresa();
			System.out.println("Não foi possivel ler os dados!\nErro de leitura!");
		}
		catch (ClassNotFoundException e){
			emp = new Empresa();
			System.out.println("Não foi possivel ler os dados!\nFicheiro com formato desconhecido!");
		}
		catch (ClassCastException e){
			emp = new Empresa();
			System.out.println("Não foi possivel ler os dados!\nErro de formato!");
		}
	}

	private static void sessao(){
		Scanner input = new Scanner(System.in);
		String email,password;


		System.out.println("Email: ");
		email = input.nextLine();

		System.out.println("Password: ");
		password = input.nextLine();

		try{
			emp.iniciaSessao(email,password);
		}
		catch (SemAutorizacaoException e){
			System.out.println(e.getMessage());
		}
		finally{
			input.close();
		}
		switch(emp.getTipoUtilizador()){
			case 1: imprimeMenuMotoristas(); break;
			case 2: imprimeMenuClientes();
		}
	}

	private static void regiUti(){
		String email,nome,password,morada,dataNasc;
		Scanner input = new Scanner (System.in);
		Utilizador u = null;
		menuRegistar.executa();
		if(menuRegistar.getOpcao()==0){
			System.out.println("Registo Cancelado");
			return;
		}
		System.out.println("Insira o email: ");
		email = input.nextLine();

		System.out.println("Insira o nome: ");
		nome = input.nextLine();

		System.out.println("Insira a password: ");
		password = input.nextLine();

		System.out.println("Insira a morada: ");
		morada = input.nextLine();

		System.out.println("Insira a data de nascimento no formato (dd-MM-yyyy): ");
		dataNasc = input.nextLine();

		switch(menuRegistar.getOpcao()){
			case 0: input.close(); return;
			case 1: u = new Motorista(); break;
			case 2: u = new Cliente(); break;
		}

		u.setEmail(email);
		u.setNome(nome);
		u.setPassword(password);
		u.setMorada(morada);
		u.setData(dataNasc);

		try{
			emp.registarUtilizador(u);
		}
		catch (UtilizadorExistenteException e){
			System.out.println(e.getMessage());
		}
		finally{
			input.close();
		}
	}

	


















}