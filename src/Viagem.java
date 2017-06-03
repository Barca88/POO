import java.io.Serializable;
import java.time.LocalDateTime;

public class Viagem implements Serializable {

    private double preco;
    private Localizacao liCliente;
    private Localizacao liTaxi;
    private Localizacao liDestino;
    private Taxi taxi;
    private LocalDateTime dia;

    public Viagem(){
        this.preco = 0.0;
        this.liCliente = new Localizacao();
        this.liTaxi = new Localizacao();
        this.liDestino = new Localizacao();
        this.taxi = new Taxi();
        //falta data
    }
    public Viagem(double preco, Localizacao liCliente, Localizacao liTaxi,
                  Localizacao liDestino, Taxi taxi){
        this.preco = preco;
        this.liCliente = liCliente;
        this.liTaxi = liTaxi;
        this.liDestino = liDestino;
        this.taxi = taxi;
        //data
    }
    public Viagem(Viagem v){
    	this.preco = v.getPreco();
    	this.liCliente = v.getLiCliente();
        this.liTaxi = v.getLiTaxi();
        this.liDestino = v.getLiDestino();
        this.taxi = v.getTaxi();
        //data
    }

    //Getters
    public double getPreco(){
    	return preco;
    }
    public Localizacao getLiCliente(){
    	return liCliente;
    }
    public Localizacao getLiTaxi(){
    	return liTaxi;
    }
    public Localizacao getLiDestino(){
    	return liDestino;
    }
    public Taxi getTaxi(){
        return taxi;
    }
    public LocalDateTime getDia(){
        return dia;
    }

    //Setters
    public void setPreco(double preco){
        this.preco = preco;
    }
    public void setLiCliente(Localizacao liCLiente){
        this.liCliente = liCliente;
    }
    public void setLiTaxi(Localizacao liTaxi){
        this.liTaxi = liTaxi;
    }
    public void setLiDestino(Localizacao liDestino){
        this.liDestino = liDestino;
    }
    public void setTaxi(Taxi taxi){
        this.taxi = taxi;
    }
    public void setDia(LocalDateTime dia){
        this.dia = dia;
    }

    //Metodos
    public double precoViagem(){
        double custo;
        custo = Localizacao.distancia(liTaxi,liCliente);
        custo += Localizacao.distancia(liCliente,liDestino);
        custo = custo * this.taxi.getPreco();
        return custo;
    }
    
    /*public double tempoViagem(Taxi taxi, Cliente cliente, Localizacao destino){
        double tempo = 0;
            (taxi.getVelMedia(),taxi.getLocal,cliente.getLocal());
        tempo = tempo + TempoDesloca(taxi.getVelMedia(),
                                    cliente.getLocal(),destino);
        return tempo;
    }*/

    public Viagem clone(){
        return new Viagem(this);
    }
    public boolean equals(Object obj){
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Viagem o = (Viagem) obj;
        return o.getPreco() == this.getPreco() &&
        o.getPreco() == this.getPreco() &&
        o.getLiCliente() == this.getLiCliente() &&
        o.getLiTaxi() == this.getLiTaxi() &&
        o.getLiDestino() == this.getLiDestino() &&
        o.getTaxi() == this.getTaxi();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Preço da Viagem: ").append(preco).append("\n");
        sb.append(liDestino.toString());
        return sb.toString();
    }
}
