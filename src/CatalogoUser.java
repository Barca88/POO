import java.util.TreeMap;
import java.util.stream.Collectors;

import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.Map;

public class CatalogoUser {
    private TreeMap<String,Utilizador> users;

    public CatalogoUser(){ // construtor vazio
        users = new TreeMap<String,Utilizador>();        
    }
    public CatalogoUser(TreeMap<String,Utilizador> users){ //constrotor com pareametros
        this.users = new TreeMap<>();
        this.users = users;
    }
    public CatalogoUser(CatalogoUser c){ //construtor de cópia
        this.users = (TreeMap<String,Utilizador>)c.getCatalog(); 
    } 
    public boolean checkUser(String email){
        return users.containsKey(email);
    }
    //Getters
    public Utilizador getUser(String email){
        return users.get(email);
    }
    public Map<String,Utilizador> getCatalog(){
        return this.users.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue())); 
    }
    //Setters
    public void setCatalog(TreeMap<String,Utilizador> users){
        this.users.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue()));
    }
    public boolean isCliente(Utilizador user){
        if(user.getType() == 1) return true;
        return false;
    }

}
