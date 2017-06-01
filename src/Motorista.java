import java.util.List;
import java.util.ArrayList;

public class Motorista extends Utilizador{
    //var. de instancia

    private int grauCump;
    private int classificacao;
    private int totalKms;
    private boolean disponibilidade;
    private List<Integer> classificacoes;

    //Construtor
    public Motorista (){
        super();
        this.grauCump = 0;
        this.classificacao = 0;
        this.totalKms = 0;
        this.disponibilidade = false;
        this.classificacoes = new ArrayList<Integer>();
    }
    public Motorista (String nome,
        String email, String password, String morada,
        String dataNasc, int grauCump, int classificacao,
        int totalKms, boolean disponibilidade, ArrayList<Integer> classificacoes){

        super(nome, email, password, morada, dataNasc);
        this.grauCump = grauCump;
        this.classificacao = classificacao;
        this.totalKms = totalKms;
        this.disponibilidade = disponibilidade;
        this.classificacoes = classificacoes;
    }
    public Motorista (Motorista m){
        super(m);
        this.grauCump = m.getGrauCump();
        this.classificacao = m.getClassificacao();
        this.totalKms = m.getTotalKms();
        this.disponibilidade = m.getDisponibilidade();
        this.classificacoes = m.getClassificacoes();
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

    public List<Integer> getClassificacoes(){
        return classificacoes;
    }

    //Setters
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

    public void setClassificacao (List<Integer> classificacoes){
        this.classificacoes = classificacoes;
    }

    
    public Motorista clone (){
        return new Motorista (this);
    }
    public boolean equals (Object obj){
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Motorista o = (Motorista) obj;
        return o.getEmail().equals(this.getEmail()) &&
               o.getNome().equals(this.getNome()) &&
               o.getPassword().equals(this.getPassword()) &&
               o.getMorada().equals(this.getMorada()) &&
               o.getData().equals(this.getData()) &&
               o.getGrauCump() == this.getGrauCump() &&
               o.getClassificacao() == this.getClassificacao() &&
               o.getTotalKms() == this.getTotalKms() &&
               o.getDisponibilidade() == this.getDisponibilidade();
    }
}
