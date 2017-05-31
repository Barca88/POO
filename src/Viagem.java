public class Viagem{

    private double preco;
    private Localizacao pICliente;
    private Localizacao pITaxi;
    private Localizacao pDestino;
    private Cliente cliente;
    private Taxi taxi;
    private Motorista motorista;
    //data

    public Viagem(){
    	this.preco = 0.0;
        this.pICliente = new Localizacao();
        this.pITaxi = new Localizacao();
        this.pDestino = new Localizacao();
        this.cliente = new Cliente();
        this.taxi = new taxi();
        this.motorista = new Motorista();
    }

    public Viagem(double preco){
    	this.preco = preco;
        //
    }

    public Viagem(Viagem v){
    	this.preco = v.getPreco();
    	this.lICliente = v.getLICliente();
        this.lITaxi = v.getLITaxi();
        this.lDestino = v.getLDestino();
        this.cliente = v.getCliente();
        this.taxi = v.getTaxi();
        this.motorista = v.getMotorista();
    }
    //Getters
    public double getPreco(){
    	return preco;
    }
    public Localizacao getLICliente(){
    	return lICliente;
    }
    public Localizacao getLITaxi(){
    	return lITaxi;
    }
    public Localizacao getLDestino(){
    	return lDestino;
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
    public void setLICliente(Localizacao lICLiente){
        this.lICliente = lICliente;
    }
    public void setLITaxi(Localizacao lITaxi){
        this.lITaxi = lITaxi;
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









}
