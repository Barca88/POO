import java.io.Serializable;
import java.util.HashMap;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Umer implements Serializable{
    private HashMap<String,Utilizador> users;
    private HashMap<String,Taxi> taxis;
    private Utilizador utilizador; // utilizador registado no momento

    public Umer(){
        this.users = new HashMap<String,Utilizador>();
        this.taxis = new HashMap<String,Taxi>();
        this.utilizador = null;
    }
    public Umer (HashMap<String,Utilizador> users, HashMap<String,Taxi> taxis, Utilizador utilizador) {
        this.users = users;
        this.taxis = taxis;
        this.utilizador = utilizador;
    }
    public Umer (Umer e){
        this.users = e.getUsers();
        this.taxis = e.getTaxis();
        this.utilizador = e.getUtilizador();
    }
    public Umer clone(){
        return new Umer(this);
    }

    //Getters
    public HashMap<String,Utilizador> getUsers(){
        return users;
    }
    public HashMap<String,Taxi> getTaxis(){
        return taxis;
    }
    public Utilizador getUtilizador(){
        return utilizador;
    }

    //Setters
    public void setUsers (HashMap<String,Utilizador> users){
        this.users = users;
    }
    public void setTaxis (HashMap<String,Taxi> taxis){
        this.taxis = taxis;
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
        fw.write("\n========================== LOG ==========================\n");
        fw.write(this.toString());
        fw.write("\n========================== LOG ==========================\n");
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
    

    
    public void terminaSessao(){
        this.utilizador = null;
    } 
}
