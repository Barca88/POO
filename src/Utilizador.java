import java.io.Serializable;

public abstract class Utilizador implements Serializable{
    // var. de instancia
    private String email;
    private String nome;
    private String password;
    private String morada;
    private String dataNasc;
    // Construtor
    public Utilizador(){
        email = null;
        nome = null;
        password = null;
        morada = null;
        dataNasc = null;
    }
    
    public Utilizador (String email, String nome, String password, String morada, String dataNasc){
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNasc = dataNasc;
    }
    
    public Utilizador (Utilizador u){
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.password = u.getPass();
        this.morada = u.getMorada();
        this.dataNasc = u.getData();
    }
    //Métodos
    public String getEmail(){
        return email;
    }
    
    public String getNome(){
        return nome;
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
    
    public void setEmail(String email){
        this.email=email;
    }
    
    public void setNome(String nome){
        this.nome=nome;
    }
    
    public void setPass(String password){
        this.password=password;
    }
    
    public void setMorada(String morada){
        this.morada=morada;
    }
    
    public void setData(String dataNasc){
        this.dataNasc=dataNasc;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
    