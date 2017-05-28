import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.Map;

public class CatalogoUser {
    private TreeMap<String,Utilizador> users;

    public CatalogoUser(){
        users = new TreeMap<String,Utilizador>();        
    }
    
    public CatalogoUser(TreeMap<String,Utilizador> users){
        this.users = new TreeMap<>();
        this.users = users;
    }
    
    public CatalogoUser(CatalogoUser c){
        this.users = (TreeMap<String,Utilizador>)c.getCatalog();
        
    }
        
    public boolean checkUser(String email){
        return users.containsKey(email);
    }
    
    public Utilizador getUser(String email){
        return users.get(email);
    }
    
    public Map<String,Utilizador> getCatalog(){
        return this.users.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue())); 
    }
        
    public boolean isCliente(Utilizador user){
        if(user.getType() == 1) return true;
        
        return false;
    }
}
