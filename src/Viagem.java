import java.io.Serializable;
import java.time.LocalDateTime;

public class Viagem implements Serializable {

    private double preco;  // custo da viagem
    private double diferenca;  // diferença entre gasto reais e estimados
    private Localizacao liCliente;
    private Localizacao liTaxi;
    private Localizacao liDestino;
    private Taxi taxi;
    private LocalDateTime dia;

    public Viagem(){
        this.preco = 0.0;
        this.diferenca = 0.0;
        this.liCliente = new Localizacao();
        this.liTaxi = new Localizacao();
        this.liDestino = new Localizacao();
        this.taxi = new Taxi();
        //falta data
    }
    public Viagem(double preco, Localizacao liCliente, Localizacao liTaxi,
                  Localizacao liDestino, Taxi taxi, double diferenca){
        this.preco = preco;
        this.diferenca = diferenca;
        this.liCliente = liCliente;
        this.liTaxi = liTaxi;
        this.liDestino = liDestino;
        this.taxi = taxi;
        //data
    }
    public Viagem(Viagem v){
        this.preco = v.getPreco();
        this.diferenca = v.getDiferenca();
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
    public double getDiferenca(){
        return diferenca;
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
    public void setDiferenca(double diferenca){
        this.diferenca = diferenca;
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
        double custo = distanciaViagem();
        custo = custo * this.taxi.getPreco();
        if(this.taxi.getFiab() <= 1.25 ){
            this.diferenca = - (custo * this.taxi.getFiab());
            custo = custo * this.taxi.getFiab();
        }
        else 
            this.diferenca = (custo * this.taxi.getFiab()) - custo;
        return custo;
    }

    public double distanciaViagem(){
        return Localizacao.distancia(liTaxi,liCliente) + Localizacao.distancia(liCliente,liDestino);
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
        sb.append("Destino, ").append(liDestino.toString()).append("Taxi, ");
        sb.append(liTaxi.toString()).append("A Minha, ").append(liCliente.toString());
        sb.append("Preço da Viagem: ").append(preco).append("\n");
        sb.append("Diferença de preço entre o preço real e o estimado: ").append(diferenca);
        sb.append(liDestino.toString());
        return sb.toString();
    }
}