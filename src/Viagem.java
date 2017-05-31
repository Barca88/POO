public class Viagem{

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
        this.taxi = new taxi();
        this.motorista = new Motorista();
    }
    
    public Viagem(double preco, Localizacao pICliente, Localizacao pITaxi,
                  Localizacao pDestino, Cliente cliente, 
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
    public void setLITaxi(Localizacao liTaxi){
        this.liTaxi = liTaxi;
    }
    public void setLDestino(Localizacao lDestino){
        this.lDestino = lDestino;
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
    public double precoViagem(Taxi taxi, Cliente cliente,
                                            Localizacao destino){
        double custo;
        custo = Distancia(taxi.getLocal(),cliente.getLocal());
        custo = custo + Distancia(cliente.getLocal(),destino);
        custo = custo * taxi.getPreco();
        return custo;
    }
    public double tempoViagem(Taxi taxi, Cliente cliente,
                                            Localizacao destino){
        double tempo = TempoDesloca
            (taxi.getVelMedia(),taxi.getLocal,cliente.getLocal());
        tempo = tempo + TempoDesloca(taxi.getVelMedia(),
                                    cliente.getLocal(),destino);
        return tempo;
    }
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Viagem o = (Viagem) obj;
        
        return super.equals(o) && o.getPreco() == this.getPreco() && 
        o.getPreco() == this.getPreco() && o.getLICliente() == this.getLICliente() &&
        o.getLTaxi() == this.getLTaxi() && o.getLDestino() == this.getLDestino() && 
        o.getCliente() == this.getCliente() && o.getTaxi() == this.getTaxi() &&
        o.getMotorista() == this.getMotorista();
    }
    public Viagem clone(){
        return new Viagem(this);
    }
    public String toString(){
        StringBuilder str;
        str = new StringBuilder();
        
        
    private double preco;
    private Localizacao pICliente;
    private Localizacao pITaxi;
    private Localizacao pDestino;
    private Cliente cliente;
    private Taxi taxi;
    private Motorista motorista;
        
    }
}