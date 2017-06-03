import java.io.Serializable;

public class Taxi implements Serializable{
    // var. instancia
    private String matricula;
    private Motorista motorista;
    private double velMedia;
    private double preco;
    private double fiabilidade;
    private Localizacao gps;
    private double totalFaturado;

    //construtores
    public Taxi(){
        this.matricula ="";
        this.motorista = new Motorista();
        this.velMedia = 0.0;
        this.preco = 0.0;
        this.fiabilidade = 0.0;
        this.gps = new Localizacao();
        this.totalFaturado = 0.0;
    }
    public Taxi(String matricula, Motorista motorista, double velMedia,
            double preco, double fiabilidade, Localizacao gps){
        this.matricula = matricula;
        this.motorista = motorista;
        this.velMedia = velMedia;
        this.preco = preco;
        this.fiabilidade = fiabilidade;
        this.gps = gps;
        this.totalFaturado = totalFaturado;
    }
    public Taxi(Taxi t){
       this.matricula = t.getMatricula();
       this.motorista = t.getMotorista();
       this.velMedia = t.getVelMedia();
       this.preco = t.getPreco();
       this.fiabilidade = t.getFiab();
       this.gps = t.getLocal();
       this.totalFaturado = t.getTotalFaturado();
    }

    //Getters
    public String getMatricula(){
        return matricula;
    }
    public Motorista getMotorista(){
        return motorista.clone();
    }
    public double getVelMedia(){
        return velMedia;
    }
    public double getPreco(){
        return preco;
    }
    public double getFiab(){
        return fiabilidade;
    }
    public Localizacao getLocal(){
        return this.gps;
    }
    public double getTotalFaturado(){
        return this.totalFaturado;
    }

    //Setters
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    public void setMotorista(Motorista motorista){
        this.motorista = motorista;
    }
    public void setVelMedia(double velMedia){
        this.velMedia = velMedia;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
    public void setFiab(double fiabilidade){
        this.fiabilidade = fiabilidade;
    }
    public void setLocal(Localizacao gps){
        this.gps = gps;
    }
    public void setTotalFaturado(double total){
        this.totalFaturado = total;

    }
    public Taxi clone(){
        return new Taxi(this);
    }
    public boolean equals(Object obj){
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Taxi o = (Taxi) obj;
        return o.getMatricula() == this.getMatricula()
        && o.getVelMedia() == this.getVelMedia()
        && o.getPreco() == this.getPreco()
        && o.getFiab() == this.getFiab()
        && o.getLocal() == this.getLocal();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Taxi:\n");
        sb.append("Matricula do taxi: ").append(matricula).append("\n");
        sb.append("Velocidade média: ").append(velMedia).append("\n");
        sb.append("Preço base por km: ").append(preco).append("\n");
        return sb.toString();
    }
}
