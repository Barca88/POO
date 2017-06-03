import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class Umer implements Serializable {
    private HashMap<String,Utilizador> users;
    private HashMap<String,Taxi> taxis;
    private Utilizador utilizador; // utilizador registado no momento

    public Umer(){
        this.users = new HashMap<String,Utilizador>();
        this.taxis = new HashMap<String,Taxi>();
        this.utilizador = null;
    }
    public Umer (HashMap<String,Utilizador> users, HashMap<String,Taxi> taxis,
        Utilizador utilizador) {
        this.users = users;
        this.taxis = taxis;
        this.utilizador = utilizador;
    }
    public Umer (Umer e){
        this.users = (HashMap<String,Utilizador>) e.getUsers();
        this.taxis = (HashMap<String,Taxi>) e.getTaxis();
        this.utilizador = e.getUtilizador();
    }
    public Umer clone(){
        return new Umer(this);
    }

    //Getters

    public Map<String,Utilizador> getUsers(){
        return this.users.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue()));
    }
    public Map<String,Taxi> getTaxis(){
        return this.taxis.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue()));
    }

    public Utilizador getUtilizador(){
        return utilizador;
    }

    //Setters

    public void setUsers (HashMap<String,Utilizador> users){
        this.users.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue()));
    }

    public void setTaxis (HashMap<String,Taxi> taxis){
        this.taxis.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue()));
    }

    public void setUtilizador (Utilizador utilizador){
        this.utilizador = utilizador;
    }


    /**
     * Gravar o estado da aplicação num determinado ficheiro.
     */

    public void gravaObj() throws IOException {
        ObjectOutputStream sv = new ObjectOutputStream(new FileOutputStream("umer_estado"));
        sv.writeObject(this);
        sv.flush();
        sv.close();
    }

    /**
     * Iniciar a aplicação com o estado guardado num determinado ficheiro.
     */
     public static Umer leObj() throws IOException, ClassNotFoundException {
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream("umer_estado"));

        Umer um = (Umer) oi.readObject();

        oi.close();
        return um;
    }
    /**
     * Fazer um ficheiro de texto log com toda a informação na aplicacao no momento em que é fechada.
     */
    public void log(String f, boolean ap) throws IOException {
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n========================= LOG ==========================\n");
        fw.write(this.toString());
        fw.write("\n========================= LOG ==========================\n");
        fw.flush();
        fw.close();
    }
    /**
     * Regista utilizador no catalogo.
     */
    public void registarUtilizador (Utilizador utilizador) throws UtilizadorExistenteException{

        if(this.users.containsKey(utilizador.getEmail())){
            throw new UtilizadorExistenteException ("Ja existe este Utilizador");
        }else this.users.put(utilizador.getEmail(),utilizador);
    }
    /**
     * Regista um taxi no catalogo.
     */
    public void registarViatura (Taxi viatura) throws ViaturaExistenteException{

        if(this.taxis.containsKey(viatura.getMatricula())){
            throw new ViaturaExistenteException ("Viatura ja registada");
        }else this.taxis.put(viatura.getMatricula(),viatura);
    }
    /**
     * Inicia sessao com email e password.
     */
    public void iniciaSessao(String email, String password) throws SemAutorizacaoException {

        if(this.utilizador == null){

            if(users.containsKey(email)){
                Utilizador user = users.get(email);
                if (password.equals(user.getPassword())){
                    utilizador = user;
                }else throw new SemAutorizacaoException("Credenciais Erradas");
            }else throw new SemAutorizacaoException("Credenciais Erradas");
        }else throw new SemAutorizacaoException("Ja tem sessão iniciada");
    }
    /**
     * Solicita uma viagem ao Taxi mais proximo.
     */
    public void solicitarViagem(Localizacao lDest) {
        Taxi taxi = this.compLocalizacao();
        Motorista mot = (Motorista) this.users.get(taxi.getMotorista().getEmail());
        Cliente cli = (Cliente) this.users.get(this.utilizador.getEmail());
        double random = ThreadLocalRandom.current().nextDouble(1.0,2.0+1.0);
        taxi.setFiab(random);
        Viagem viagem = criaViagem(lDest,cli.getLocal(),taxi);
        mot.setDisponibilidade(false);
        mot.insereViagem(viagem);
        cli.insereViagem(viagem);
    }
    /**
     * Solicita uma viagem ao Taxi de uma respetiva matricula.
     */
    public void solicitarViagem(Localizacao lDest, String matricula) throws NaoExisteTaxiException, MotoristaNaoDispException{
        if (!this.taxis.containsKey(matricula)) throw new NaoExisteTaxiException("Viatura nao registada");
        Taxi taxi = this.taxis.get(matricula).clone();
        Motorista mot = (Motorista) this.users.get(taxi.getMotorista().getEmail());
        Cliente cli = (Cliente) this.users.get(this.utilizador.getEmail());
        if (!mot.getDisponibilidade()) throw new MotoristaNaoDispException("Motorista nao disponivel");
        double random = ThreadLocalRandom.current().nextDouble(1.0,2.0+1.0);
        taxi.setFiab(random);
        Viagem viagem = criaViagem(lDest,cli.getLocal(),taxi);
        mot.setDisponibilidade(false);
        mot.insereViagem(viagem);
        cli.insereViagem(viagem);
    }
    /**
     * Cria uma viagem consoante as localizacoes do destino,taxi e cliente.
     */
    private static Viagem criaViagem(Localizacao lDest, Localizacao cDest, Taxi taxi){
        Viagem viagem = new Viagem();
        viagem.setLiCliente(cDest);
        viagem.setLiTaxi(taxi.getLocal());
        viagem.setLiDestino(lDest);
        viagem.setTaxi(taxi);
        viagem.setPreco(viagem.precoViagem());
        viagem.setDia(LocalDate.now());
        return viagem;
    }
    /*
    * Devolve o taxi mais proximo do cliente registado.
    */
    private Taxi compLocalizacao(){
        Cliente c = (Cliente) this.users.get(this.utilizador.getEmail());
        double dist = 10000000000000000000.0;
        Taxi taxi = null;
        for(Taxi t : this.taxis.values()){
            if ((Localizacao.distancia(c.getLocal(),t.getLocal())) < dist) {
                taxi = t;
                dist = Localizacao.distancia(c.getLocal(),t.getLocal());
            }
        }
        return taxi;
    }
    /**
     * Funcao que classifica o motorista e atualiza apos viagem.
     */
    public void classificaMotorista(int aval){
        Cliente cli = (Cliente) this.users.get(this.utilizador.getEmail());
        Viagem vig = cli.getViagens().get(cli.getViagens().size());
        Motorista mot = (Motorista) this.users.get(vig.getTaxi().getMotorista().getEmail());
        Taxi taxi = this.taxis.get(vig.getTaxi().getMatricula());
        mot.insereClassificacao(aval);
        mot.setClassiFinal(mot.mediaClassificacoes());
        mot.setDisponibilidade(true);
        taxi.setLocal(vig.getLiDestino());
        taxi.setTotalFaturado(taxi.getTotalFaturado() + vig.getPreco());
        cli.setTotalGasto(cli.getTotalGasto() + vig.getPreco());
        mot.setTotalKms(mot.getTotalKms() + vig.distanciaViagem());
    }
    /**
     *  Atualiza posicao do cliente.
     */
    public void setNovaPos(Localizacao nova){
        Cliente cli = (Cliente) this.users.get(this.utilizador.getEmail());
        cli.setLocal(nova);
    }
    /**
     * Funcao que coleciona os 10 clientes que mais gastam por ordem.
     */
    public ArrayList<Utilizador> top10Gastos (){
        return this.users.values().stream().filter(t->t instanceof Cliente)
        .sorted(new ComparadorCustos()).limit(10)
        .map(t->t.clone())
        .collect(Collectors.toCollection(ArrayList :: new));
    }
    /**
     * Funcao que coleciona os 5 motoristas que apresentam maior desvio entre valores previstos e faturados.
     */
    public ArrayList<Utilizador> top5EstimadoFinal(){
         return this.users.values().stream().filter(t->t instanceof Motorista)
        .sorted(new ComparadorDiferencaCustos()).limit(5)
        .map(m->m.clone())
        .collect(Collectors.toCollection(ArrayList :: new));
    }
    /**
     * Funcao que lista viagens efetuadas entre datas de cliente ou motorista.
     */
        public ArrayList<Viagem> getViagensData(LocalDate d1, LocalDate d2){
        Utilizador user = this.users.get(this.utilizador.getEmail()).clone();
        if(user instanceof Cliente){
        Cliente cli = (Cliente) user;
        return cli.getViagens().stream()
        .filter(v->v.getDia().isBefore(d2) && v.getDia().isAfter(d1))
        .map(c->c.clone())
        .collect(Collectors.toCollection(ArrayList :: new));
        }
        else{
        Motorista mot = (Motorista) user;
        return mot.getViagens().stream()
        .filter(v->v.getDia().isBefore(d2) && v.getDia().isAfter(d1))
        .map(v->v.clone())
        .collect(Collectors.toCollection(ArrayList :: new));
        }
    }
    /**
     * Funcao que altera disponibilidade do motorista.
     */
    public void disponibilidade(boolean b){
        Motorista mot = (Motorista) this.users.get(utilizador.getEmail());
        mot.setDisponibilidade(b);
    }
    /**
     * Funcao que retorna numero total de kms de um dado motorista.
     */
    public double numeroDeKmsRealizados(){
        Motorista mot = (Motorista) this.users.get(utilizador.getEmail()).clone();
        return mot.getTotalKms();
    }
    /**
     * Funcao que retorna classificacao de um dado motorista.
     */
    public int motoristaClassificacao(){
        Motorista mot = (Motorista) this.users.get(utilizador.getEmail()).clone();
        return mot.getclassiFinal();
    }
    /**
     * Funcao que retorna o total faturado nas viagens de um motorista.
     */
    public double totalFaturado(){
        Motorista mot = (Motorista) this.users.get(utilizador.getEmail()).clone();
        Taxi taxi = this.taxis.values().stream().filter(t->t.getMotorista().equals(mot)).findFirst().get().clone();
        return taxi.getTotalFaturado();
    }
    /**
     * Funcao que retorna o total gasto nas viagens de um cliente.
     */
    public double totalGasto(){
        Cliente cli = (Cliente) this.users.get(utilizador.getEmail()).clone();
        return cli.getTotalGasto();
    }
    /**
     * Funcao que termina a sessao do utilizador logado.
     */
    public void terminaSessao(){
        this.utilizador = null;
    }
    /**
      *
      * Funcao que retorna motorista do catalogo.
      */		
    public Motorista getMotorista(){
         return (Motorista) this.users.get(this.utilizador.getEmail());
    }
    /**
     * Funcao que verifica se o taxi do motorista existe no catalogo.
     */
    public boolean existeTaxi(){
         return this.taxis.values().stream().anyMatch(t->t.getMotorista().equals(this.users.get(utilizador.getEmail())));
    }
}
