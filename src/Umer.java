import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

public class Umer implements Serializable{
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


    // GRAVAR
    /**
     * Gravar o estado da aplicação num determinado ficheiro.
     * @param fich
     */

    public void gravaObj(String fich) throws IOException {
        ObjectOutputStream sv = new ObjectOutputStream(new FileOutputStream(fich));
        sv.writeObject(this);
        sv.flush();
        sv.close();
    }

    /**
     * Iniciar a aplicação com o estado guardado num determinado ficheiro.
     * @param fich
     * @return
     */
    /*
     public static Umer leObj(String fich) throws IOException, ClassNotFoundException {
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(fich));

        Umer um = (Umer) oi.readObject();

        ois.close();
        return um;
    }

    /**
     * Fazer um ficheiro de texto log com toda a informação na Imobiliária no momento em que é fechada.
     * @param f
     * @param ap
     */
    /*
    public void log(String f, boolean ap) throws IOException {
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n========================= LOG ==========================\n");
        fw.write(this.toString());
        fw.write("\n========================= LOG ==========================\n");
        fw.flush();
        fw.close();
    } */


    public void registarUtilizador (Utilizador utilizador) throws UtilizadorExistenteException{

        if(this.users.containsKey(utilizador.getEmail())){
            throw new UtilizadorExistenteException ("Ja existe este Utilizador");
        }
        else {
            this.users.put(utilizador.getEmail(),utilizador);
        }
    }

    public void insereViatura (Taxi viatura) throws ViaturaExistenteException{

        if(this.taxis.containsKey(viatura.getMatricula())){
            throw new ViaturaExistenteException ("Viatura ja registada");
        }
        else{
            this.taxis.put(viatura.getMatricula(),viatura);
        }
    }




    public void iniciaSessao(String email, String password) throws SemAutorizacaoException {

        if(this.utilizador == null){

            if(users.containsKey(email)){
                Utilizador user = users.get(email);
                if (password.equals(user.getPassword())){
                    utilizador = user;
                }
                else {
                    throw new SemAutorizacaoException("Credenciais Erradas");
                }
            }else
                throw new SemAutorizacaoException("Credenciais Erradas");
        }else {
            throw new SemAutorizacaoException("Ja tem sessão iniciada");
        }
    }
    public static void solicitarViagem() {}  // --TODO
    public static Viagem criaViagem(Localizacao lDest, Taxi taxi, Cliente clie){
        Viagem viagem = new Viagem();
        viagem.setLiCliente(clie.getLocal());
        viagem.setLiTaxi(taxi.getLocal());
        viagem.setLiDestino(lDest);
        viagem.setTaxi(taxi);
        viagem.setCliente(clie);
        viagem.setMotorista(taxi.getMotorista());

    }
    /*
    * Devolve o taxi mais proximo do cliente registado
    */
     private String compLoaclizacao(){
        double dist = 10000000000000000000.0;
        String matricula = null;
        Cliente c = (Cliente) this.utilizador;
        this.taxis.values().stream().filter(t->t.getMotorista().getDisponibilidade()).forEach(t->{
            if ((Localizacao.distancia(c.getLocal(),t.getLocal())) < dist){
                matricula = t.getMatricula();
                dist = Localizacao.distancia(c.getLocal(),t.getLocal());
            }
        });
        return matricula;
    }





    public void terminaSessao(){
        this.utilizador = null;
    }
}

