import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl.NamespaceWildcardIterator;

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
}