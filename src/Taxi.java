import java.io.Serializable;

public abstract class Taxi implements Serializable{
    // var. instancia
    private long id;
    private double velMedia;
    private double preco;
    private double fiabilidade;
    private Localizacao local;

    //construtores
    public Taxi(long id){
        this.id = id;
        this.velMedia = 0.0;
        this.preco = 0.0;
        this.fiabilidade = 0.0;
        this.local = null;
    }
    public Taxi(long id, double velMedia,double preco,
                                            double fiabilidade){
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
}
