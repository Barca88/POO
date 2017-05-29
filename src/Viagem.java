public class Viagem{

    private double preco;
    //duracao
    //Inicio da viagem
    //fim de viagem
    private Motorista motorista;
    private Cliente cliente;
    private Motos moto;
    private Carrinhas carrinha;
    private Carros carro;
    //data

    public Viagem(){
    	this.preco=0.0;
    	//
    	//
    	//
    	//
    	//
    }

    public Viagem(double preco){
    	this.preco=preco;
    	//
    	//
    	//
    }

    public Viagem(Viagem v){
    	this.preco=v.getPreco();
    	this.motorista=v.getMotorista();
    	this.cliente=v.getCliente();
    	//
    	//
    }

    //Metodos

    public double getPreco(){
    	return preco;
    }

    public Motorista getMotorista(){
    	return motorista;
    }

    public Cliente getCliente(){
    	return cliente;
    }

    public void setPreco(double preco){
    	this.preco=preco;
    }

    public void setMotorista(Motorista motorista){
    	this.motorista=motorista;
    }

    public void setCliente(Cliente cliente){
    	this.cliente=cliente;
    }





    


}