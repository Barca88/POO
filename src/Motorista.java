

public class Motorista extends Utilizador{
    //var. de instancia

    private int grauCump;
    private int classificacao;
    private int totalKms;
    private boolean disponibilidade;

    //Construtor
    public Motorista(int type, double gpsX, double gpsY, String fname, 
        String lname, String email, String username, String password, 
        String morada, String dataNasc, int grauCump, int classificacao,
        int totalKms, boolean disponibilidade){
        
        super(type, gpsX, gpsY, fname, lname, email, username, password, 
            morada, dataNasc);
        this.setType(2);
    }
    
    public Motorista(){
    }
    //Metodos
    public int getGrauCump(){
        return grauCump;
    }
    
    public int getClassificacao(){
        return classificacao;
    }
    
    public int getTotalKms(){
        return totalKms;
    }
    
    public boolean getDisponibilidade(){
        return disponibilidade;
    }
    
    public void setGrauCump(int grauCump){
        this.grauCump = grauCump;
    }
    
    public void setClassificacao(int classificacao){
        this.classificacao = classificacao;
    }
    
    public void setTotalKms(int totalKms){
        this.totalKms = totalKms;
    }
    
    public void setDisponibilidade(boolean disponibilidade){
        this.disponibilidade = disponibilidade;
    }
}
