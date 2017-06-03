import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Motorista extends Utilizador{
    //var. de instancia

    private int grauCump;
    private int classiFinal;
    private int totalKms;
    private boolean disponibilidade;
    private List<Integer> classificacoes;
    private List<Viagem> viagens;


    //Construtor
    public Motorista (){
        super("","","","","");
        this.grauCump = 0;
        this.classiFinal = 0;
        this.totalKms = 0;
        this.disponibilidade = false;
        this.classificacoes = new ArrayList<Integer>();
        this.viagens = new ArrayList<Viagem>();
    }
    public Motorista (String nome,
        String email, String password, String morada,
        String dataNasc, int grauCump, int classiFinal,
        int totalKms, boolean disponibilidade, ArrayList<Integer> classificacoes, ArrayList<Viagem> viagens){

        super(nome, email, password, morada, dataNasc);
        this.grauCump = grauCump;
        this.classiFinal = classiFinal;
        this.totalKms = totalKms;
        this.disponibilidade = disponibilidade;
        this.classificacoes = classificacoes;
        this.viagens = viagens;
    }
    public Motorista (Motorista m){
        super(m);
        this.grauCump = m.getGrauCump();
        this.classiFinal = m.getclassiFinal();
        this.totalKms = m.getTotalKms();
        this.disponibilidade = m.getDisponibilidade();
        this.classificacoes = m.getClassificacoes();
        this.viagens = m.getViagens();
    }

    //Getters
    public int getGrauCump(){
        return grauCump;
    }
    public int getclassiFinal(){
        return classiFinal;
    }
    public int getTotalKms(){
        return totalKms;
    }
    public boolean getDisponibilidade(){
        return disponibilidade;
    }
    public List<Integer> getClassificacoes(){
        return this.classificacoes.stream().collect(Collectors.toCollection(ArrayList::new));
    }
    public List<Viagem> getViagens(){
        return this.viagens.stream().collect(Collectors.toCollection(ArrayList::new));
    }

    //Setters
    public void setGrauCump(int grauCump){
        this.grauCump = grauCump;
    }
    public void setClassiFinal(int classiFinal){
        this.classiFinal = classiFinal;
    }
    public void setTotalKms(int totalKms){
        this.totalKms = totalKms;
    }
    public void setDisponibilidade(boolean disponibilidade){
        this.disponibilidade = disponibilidade;
    }
    public void setClassificacao (List<Integer> classificacoes){
        this.classificacoes.stream().collect(Collectors.toCollection(ArrayList::new));
    }
    public void setViagens (List<Viagem> viagens){
        this.viagens.stream().collect(Collectors.toCollection(ArrayList::new));
    }

    //Métodos
    public void insereViagem (Viagem viagem){
        this.viagens.add(viagem);
    }

    public void insereClassificacao (int aval) {
        this.classificacoes.add(aval);
    }
    public int mediaClassificacoes () {
        return (this.classificacoes.stream().mapToInt(Integer::intValue).sum() / this.classificacoes.size());
    }
    public double totalPreco(){
        return this.viagens.stream().mapToDouble(t->t.getPreco()).sum();
    }

    public Motorista clone(){
        return new Motorista (this);
    }
    public boolean equals (Object obj){
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Motorista o = (Motorista) obj;
        return o.getGrauCump() == this.getGrauCump() &&
               o.getclassiFinal() == this.getclassiFinal() &&
               o.getTotalKms() == this.getTotalKms() &&
               o.getDisponibilidade() == this.getDisponibilidade();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Grau de Cumprimento:(0-100) ").append(grauCump).append("\n");
        sb.append("Classificacão:(0-100) ").append(classiFinal).append("\n");
        sb.append("Total de kms: ").append(totalKms).append("\n");
        sb.append("Disponibilidade: ").append(disponibilidade).append("\n");
        return sb.toString();
    }
}
