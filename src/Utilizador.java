import java.io.Serializable;

public abstract class Utilizador implements Serializable{
    // var. de instancia
 
    private String fname;
    private String lname;
    private String email;
    private String username;
    private String password;
    private String morada;
    private String dataNasc;
   
    // Construtor
    public Utilizador(){
        fname = null;
        lname = null;
        email = null;
        username = null;
        password = null;
        morada = null;
        dataNasc = null;
    }
    
    public Utilizador ( String fname, String lname, String email,
        String username, String password, String morada, String dataNasc){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.morada = morada;
        this.dataNasc = dataNasc;
    }
    
    public Utilizador (Utilizador u){
        this.fname = u.getFname();
        this.lname = u.getLname();
        this.email = u.getEmail();
        this.username = u.getUsername();
        this.password = u.getPass();
        this.morada = u.getMorada();
        this.dataNasc = u.getData();
    }
    
    //Getters
    public String getFname(){
        return fname;
    }
    
     public String getLname(){
        return lname;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPass(){
        return password;
    }

    public String getMorada(){
        return morada;
    }
    
    public String getData(){
        return dataNasc;
    }
    
    //Setters
     public void setFname(String fname){
        this.fname = fname;
    }
    
     public void setLname(String lname){
        this.lname = lname;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPass(String password){
        this.password = password;
    }
    
    public void setMorada(String morada){
        this.morada = morada;
    }
    
    public void setData(String dataNasc){
        this.dataNasc = dataNasc;
    }
    
    
    //id generator
    public String idGen(String lastId, int tipo){
        long id;
        lastId = lastId.substring(1);
        id = Long.parseLong(lastId) + 1;
        if (tipo == 0){
            lastId = "0"+id;
        }
        else{
            lastId = "1"+id;
        }
        return lastId;
    }
    
       
    public boolean equals(Utilizador a){
        if(a != null){
            if (this.username.equals(a.getUsername()) && this.email.equals(a.getEmail())) return true;
        }
        return false;
    }
}    