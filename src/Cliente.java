import java.util.List;
import java.util.ArrayList;

public class Cliente extends Utilizador{
    
    private double totalGasto;
    private List<Viagem> viagens; 

    public Cliente (){
        super();
        this.totalGasto = 0.0;
        this.viagens = new ArrayList<Viagem>();
    }

    public Cliente (Localizacao gps, String nome, String email,
        String password,String morada,String dataNasc, double totalGasto, List<Viagem> viagens){
        super(gps, nome, email, password, morada, dataNasc);
        this.totalGasto = totalGasto;
        this.viagens = viagens;       
    }
    public Cliente (Cliente c){
        super(c);
        this.totalGasto = c.getTotalGasto();
        this.viagens = c.getViagens();
    }
    //Getters
    public double getTotalGasto(){
        return this.totalGasto;
    }
    
    public List<Viagem> getViagens(){
        return this.viagens;
    }

    public Cliente clone (){
        return new Cliente(this);
    }
    // se adicionar historico mudar aqui
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Cliente o = (Cliente) obj;
        return o.getEmail().equals(this.getEmail()) && o.getNome().equals(this.getNome()) &&
            o.getPassword().equals(this.getPassword()) && o.getMorada().equals(this.getMorada())
            && o.getData().equals(this.getData()) && o.getTotalGasto() == this.getTotalGasto()
            && o.getViagens() == this.viagens;
    }        
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: \n");
        sb.append(this.getNome()+"\n");
        sb.append("Email: \n");
        sb.append(this.getEmail()+"\n");
        sb.append("Localizacao: \n");
        sb.append(this.getLocal()+"\n");
        sb.append("Morada: \n");
        sb.append(this.getMorada()+"\n");
        sb.append("Data de Nascimento: \n");
        sb.append(this.getData()+"\n");
        sb.append("Total Gasto: \n");
        sb.append(this.getTotalGasto()+"\n");
        return sb.toString();
    }



}