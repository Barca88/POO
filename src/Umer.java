import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl.NamespaceWildcardIterator;
import java.io.ObjectOutputStream;
import java.io.FileWriter;
import java.util.*;


public /**
 * Empresa
 */
public class Umer {
    private CatalogoUser cUser;
    private CatalogoTaxi cTaxi;
    private Utilizador user; // utilizador registado no momento

    public Umer(){
        this.cUser = new CatalogoUser();
        this.cTaxi = new CatalogoTaxi();
        this.user = new Utilizador();
    }
    public Umer (CatalogUser cUser, CatalogoTaxi cTaxi, Utilizador user) {
        this.cUser = cUser;
        this.cTaxi = cTaxi;
        this.user = user;
    }
    public Umer (Umer e){ // copia
        this.cUser = e.getCUser();
        this.cTaxi = e.getCTaxi();
        this.user = e.getUser();
    }
    public Umer clone(){
        return new Umer(this);
    }

    //Getters
    public CatalogoUser getCUser(){
        return new CatalogUser(this.cUser);
    }
    public CatalogoTaxi getCTaxi(){
        return new CatalogoTaxi(this.cTaxi);
    }
    public Utilizador getUser(){
        return user;
    }

    //Setters
    public void setCUser(CatalogoUser cUser){
        this.cUser.setCatalog(cUser.getCatalog());
    }
    public void setCTaxi(CatalogoTaxi cTaxi){
        this.cTaxi.setCatalog(cTaxi.getCatalog());
    }
    public void setUser(Utilizador user){
        this.user = user;
    }

    // GRAVAR
    /**
     * Gravar o estado da aplicação num determinado ficheiro.
     * @param fich
     */
    public void gravaObj(String fich) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
        oos.writeObject(this);

        oos.flush();
        oos.close();
    }

    /**
     * Iniciar a aplicação com o estado guardado num determinado ficheiro.
     * @param fich
     * @return
     */
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
    public void log(String f, boolean ap) throws IOException {
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n========================== LOG ==========================\n");
        fw.write(this.toString());
        fw.write("\n========================== LOG ==========================\n");
        fw.flush();
        fw.close();
    }
}
