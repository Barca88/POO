import java.io.Serializable;

public abstract class Utilizador implements Serializable{
    // var. de instancia
    private Localizacao gps;
    private String nome;
    private String email;
    private String password;
    private String morada;
    private String dataNasc;

    // Construtores
    public Utilizador(){
        this.gps = new Localizacao();
        this.nome = "null";
        this.email = "null";
        this.password = "null";
        this.morada = "null";
        this.dataNasc = "null";
    }
    public Utilizador (Localizacao gps, String nome, String email, String password,
        String morada, String dataNasc){
        this.gps = gps;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.morada = morada;
        this.dataNasc = dataNasc;
    }
    public Utilizador (Utilizador u){
        this.gps = u.getLocal();
        this.nome = u.getNome();
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.morada = u.getMorada();
        this.dataNasc = u.getData();
    }

    //Getters
    public Localizacao getLocal(){
        return gps;
    }
    public String getNome(){
        return nome;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getMorada(){
        return morada;
    }
    public String getData(){
        return dataNasc;
    }

    //Setters
    public void setLocal(Localizacao gps){
        this.gps = gps;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setMorada(String morada){
        this.morada = morada;
    }
    public void setData(String dataNasc){
        this.dataNasc = dataNasc;
    }

    //Metodos
    public abstract Utilizador clone();

    public boolean equals(Object obj){
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Utilizador u = (Utilizador) obj;
        return u.getEmail().equals(email)
        && u.getNome().equals(nome)
        && u.getPassword().equals(password)
        && u.getMorada().equals(morada)
        && u.getData().equals(dataNasc);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(email).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Password: ").append(password).append("\n");
        sb.append("Morada: ").append(morada).append("\n");
        sb.append("Data de nascimento: ").append(dataNasc).append("\n");
        return sb.toString();
    }
}
