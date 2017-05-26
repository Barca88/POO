import java.util.HashMap;

public class CatalogoUser {
    private HashMap<String,Utilizador> users;

    public CatalogoUser(){
        users = new HashMap<>();        
    }
 
    public boolean checkUser(String username){
        return users.containsKey(username);
    }
    
    public Utilizador getUser(String username){
        return users.get(username);
    }
    
    public boolean isCliente(Utilizador user){
        if(user.getType() == 1) return true;
        
        return false;
    }
}
