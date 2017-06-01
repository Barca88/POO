import java.util.TreeMap;

public class CatalogoTaxi {
    private TreeMap<Long,Taxi> taxis;

    public CatalogoTaxi(){
        taxis = new TreeMap<Long,Taxi>();        
    }
 
    // public CatalogUser(CatalogoTaxi c){}
    
    public boolean checkTaxi(long id){
        return taxis.containsKey(id);
    }
    
    public Taxi getTaxi(long id){
        return taxis.get(id);
    }
    
}
