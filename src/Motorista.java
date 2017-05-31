public class Motorista extends Utilizador{
    //var. de instancia

    private int grauCump;
    private int classificacao;
    private int totalKms;
    private boolean disponibilidade;

    //Construtor
    public Motorista (){
        super(0.0,0.0,"n/a","n/a","n/a","n/a","n/a");
    }
    public Motorista (Localizacao gps, String nome,
        String email, String password, String morada,
        String dataNasc, int grauCump, int classificacao,
        int totalKms, boolean disponibilidade){

        super(gps, nome, email, password, morada, dataNasc);
    }
    public Motorista (Motorista m){
        super(m);
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

    //Métodos
    public Motorista clone (){
        return new Motorista (this);
    }

    /*  **falta o return**
    public boolean equals (Object obj){
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Motorista m = (Motorista) obj;
        return;
    }
    */

    // falta fazer
    public String toString (){
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
