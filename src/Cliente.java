import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Cliente extends Utilizador{
    
    private Localizacao gps;
    private double totalGasto;
    private List<Viagem> viagens; 

    public Cliente (){
        super("","","","","");
        this.gps = gps;
        this.totalGasto = 0.0;
        this.viagens = new ArrayList<Viagem>();
    }

    public Cliente (Localizacao gps, String nome, String email,
        String password,String morada,String dataNasc, double totalGasto, List<Viagem> viagens){
        super(nome, email, password, morada, dataNasc);
        this.gps = gps;
        this.totalGasto = totalGasto;
        this.viagens = viagens;       
    }
    public Cliente (Cliente c){
        super(c);
        this.gps = c.getLocal();
        this.totalGasto = c.getTotalGasto();
        this.viagens = c.getViagens();
    }
    
    //Getters

    public Localizacao getLocal() throws NullPointerException{
        if (this.gps == null)
            throw new NullPointerException("No location is defined");
        return this.gps;
    }

    public double getTotalGasto(){
        return this.totalGasto;
    }

    public List<Viagem> getViagens(){
        return this.viagens.stream().collect(Collectors.toCollection(ArrayList::new));
    }

    public void setLocalizacao(){
        this.gps = gps;
    }

    public void setTotalGasto(){
        this.totalGasto = totalGasto;
    }

    public void setViagens (List<Viagem> viagens){
        this.viagens.stream().collect(Collectors.toCollection(ArrayList::new));
    }
    
    public Cliente clone (){
        return new Cliente(this);
    }
    
    public boolean equals (Object obj) {
        if(this == obj)
            return true;
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        Cliente c = (Cliente) obj;
        return super.equals(c);
    }     
}