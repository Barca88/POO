public class Motorista extends Utilizador{
    //var. de instancia

    private int grauCump;
    private int classificacao;
    private int totalKms;
    private boolean disponibilidade;

    //Construtor
    public Motorista (){
        super();
        this.grauCump = 0;
        this.classificacao = 0;
        this.totalKms = 0;
        this.disponibilidade = false;
    }
    public Motorista (Localizacao gps, String nome,
        String email, String password, String morada,
        String dataNasc, int grauCump, int classificacao,
        int totalKms, boolean disponibilidade){

        super(gps, nome, email, password, morada, dataNasc);
        this.grauCump = grauCump;
        this.classificacao = classificacao;
        this.totalKms = totalKms;
        this.disponibilidade = disponibilidade;
    }
    public Motorista (Motorista m){
        super(m);
        this.grauCump = m.getGrauCump();
        this.classificacao = m.getClassificacao();
        this.totalKms = m.getTotalKms();
        this.disponibilidade = m.getDisponibilidade();
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
    public boolean equals (Object obj){
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Motorista m = (Motorista) obj;
        return o.getEmail().equals(this.getEmail()) &&
               o.getNome().equals(this.getNome()) &&
               o.getPassword().equals(this.getPassword()) &&
               o.getMorada().equals(this.getMorada()) &&
               o.getData().equals(this.getDataNasc()) &&
               o.getGrauCump().equals(this.getGrauCump()) &&
               o.getClassificacao().equals(this.getClassificacao()) &&
               o.getTotalKms().equals(this.getTotalKms()) &&
               o.getDisponibilidade().equals(this.getDisponibilidade());
    }
    */

    public String toString() {
        StringBuilder str;
        str = new StringBuilder();
        str.append("Nome: \n");
        str.append(this.nome+"\n");
        str.append("Email: \n");
        str.append(this.email+"\n");
        str.append("Localizacao: \n");
        str.append(this.gps+"\n");
        str.append("Morada: \n");
        str.append(this.morada+"\n");
        str.append("Data de Nascimento: \n");
        str.append(this.dataNasc+"\n");

        return str.toString();
    }
}
