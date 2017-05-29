public class Cliente extends Utilizador{
    



    public Cliente (){
        super(0.0,0.0,"n/a","n/a","n/a","n/a","n/a");
    }

    public Cliente (double gpsX, double gpsY, String nome,
        String email, String password, 
        String morada, String dataNasc){
        super(gpsX, gpsY, nome, email, password, morada, dataNasc);
    }

    public Cliente (Cliente c){
        super(c);
    }
    
    public Cliente clone (){
        return new Cliente(this);
    }

    /* ***falta o return***
    public boolean equals (Object obj){
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Cliente c = (Cliente) obj;
        return;
    }  
    */

    public String toString (){
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

        
        
}
