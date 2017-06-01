import java.io.Serializable;

public class Viagem implements Serializable {

    private double preco;
    private Localizacao liCliente;
    private Localizacao liTaxi;
    private Localizacao liDestino;
    private Cliente cliente;
    private Taxi taxi;
    private Motorista motorista;
    //data

    public Viagem(){
        this.preco = 0.0;
        this.liCliente = new Localizacao();
        this.liTaxi = new Localizacao();
        this.liDestino = new Localizacao();
        this.cliente = new Cliente();
        this.taxi = new Taxi();
        this.motorista = new Motorista();
    }
    
    public Viagem(double preco, Localizacao liCliente, Localizacao liTaxi,
                  Localizacao liDestino, Cliente cliente, 
                  Taxi taxi, Motorista motorista){
        this.preco = preco;
        this.liCliente = liCliente;
        this.liTaxi = liTaxi;
        this.liDestino = liDestino;
        this.cliente = cliente;
        this.taxi = taxi;
        this.motorista = motorista;
        
    }
    public Viagem(Viagem v){
    	this.preco = v.getPreco();
    	this.liCliente = v.getLiCliente();
        this.liTaxi = v.getLiTaxi();
        this.liDestino = v.getLiDestino();
        this.cliente = v.getCliente();
        this.taxi = v.getTaxi();
        this.motorista = v.getMotorista();
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
    
    public Cliente getCliente(){
        return cliente;
    }
    
    public Taxi getTaxi(){
        return taxi;
    }
    
    public Motorista getMotorista(){
    	return motorista;
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
    public void setCliente(Cliente cliente){
    	this.cliente = cliente;
    }
    public void setMotorista(Motorista motorista){
    	this.motorista = motorista;
    }

    //Metodos

    /*
    public double precoViagem(Taxi taxi, Cliente cliente, Localizacao destino){
        double custo;
        custo = Distancia(taxi.getLocal(),cliente.getLocal());
        custo += Distancia(cliente.getLocal(),destino);
        custo = custo * taxi.getPreco();
        return custo;
    } */

    /*
    public double tempoViagem(Taxi taxi, Cliente cliente, Localizacao destino){
        double tempo = 0;
            (taxi.getVelMedia(),taxi.getLocal,cliente.getLocal());
        tempo = tempo + TempoDesloca(taxi.getVelMedia(),
                                    cliente.getLocal(),destino);
        return tempo;
    }*/

    public Viagem clone(){
        return new Viagem(this);
    }

    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Viagem o = (Viagem) obj;
        
        return o.getPreco() == this.getPreco() && 
        o.getPreco() == this.getPreco() && o.getLiCliente() == this.getLiCliente() &&
        o.getLiTaxi() == this.getLiTaxi() && o.getLiDestino() == this.getLiDestino() && 
        o.getCliente() == this.getCliente() && o.getTaxi() == this.getTaxi() &&
        o.getMotorista() == this.getMotorista();
    }
    

    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Preço da Viagem: ").append(preco).append("\n");
        sb.append("Cliente: ").toString(cliente);
        sb.append("Taxi: ").toString(taxi);
        sb.append("Motorista: ").toString(motorista);
        return sb.toString();
    }
}