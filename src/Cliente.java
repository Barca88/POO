
public class Cliente extends Utilizador{
    
    // construtor
    public Cliente(int type, double gpsX, double gpsY, String fname, 
        String lname,String email, String username, String password, 
        String morada, String dataNasc){
        super(type, gpsX, gpsY, fname, lname, email, username, password, 
            morada, dataNasc);
        this.setType(1);
    }
    
    public Cliente(){
    
    }
    
 //   public Cliente(){
        
        
}
