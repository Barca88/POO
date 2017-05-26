import java.util.HashMap;
/**
 * Write a description of class Interface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Interface{
    private CatalogoUser catalogo;
    private int type = 0;

   public Interface(){
     
   }
   // login
   
   // return 2 password errada return 1 erro do utilizador
   public int login(CatalogoUser data, String username, String pwd){
       Utilizador cliente = new Cliente();
       Utilizador motorista = new Motorista();
       
       if (data.checkUser(username)){
           
           if (data.isCliente(data.getUser(username))){
               cliente = data.getUser(username);
               type = 1;
           } else{
               motorista = data.getUser(username);
               type = 2;
           }
           
           if (type == 1 && pwd != cliente.getPassword()) return 2;
           if (type == 2 && pwd != motorista.getPassword()) return 2;
       }
       return 0;
    }

   // açoe
   /**
   private static startAsCliente(Utilizador a){
       newJurney(a.
    }
   
   private static startAsMototrista(Utilizador a){
    
   }
   */
}