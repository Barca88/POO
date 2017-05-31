import java.io.Serializable;

public class Taxi implements Serializable{
    // var. instancia
    private long id;
    private double velMedia;
    private double preco;
    private double fiabilidade;
    private Localizacao local;

    //construtores
    public Taxi(){
        this.id = 0;
        this.velMedia = 0.0;
        this.preco = 0.0;
        this.fiabilidade = 0.0;
        this.local = new Localizacao();
    }
    public Taxi(long id, double velMedia,double preco, double fiabilidade){
        this.id = id;
        this.velMedia = velMedia;
        this.preco = preco;
        this.fiabilidade = fiabilidade;
        this.local = null;
    }
    public Taxi(Taxi t){
       this.id = t.getId();
       this.velMedia = t.getVelMedia();
       this.preco = t.getPreco();
       this.fiabilidade = t.getFiab();
       this.local = t.getLocal();
    }

    //Getters
    public long getId(){
        return id;
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
    public Localizacao getLocal() throws NullPointerException{
        if(this.local == null)
            throw new NullPointerException("No location is defined");
        return this.local;
    }

    //Setters
    
    public void setId(){
        this.id = id;
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

    public void setLocal(Localizacao local){
        this.local = local;
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
        return o.getId() == this.getId()
        && o.getVelMedia() == this.getVelMedia()
        && o.getPreco() == this.getPreco()
        && o.getFiab() == this.getFiab()
        && o.getLocal() == this.getLocal();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id do taxi: ").append(id).append("\n");
        sb.append("Velocidade média: ").append(velMedia).append("\n");
        sb.append("Preço base por km: ").append(preco).append("\n");
        return sb.toString();
    }
}