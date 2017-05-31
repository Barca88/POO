public class Cliente extends Utilizador{
    private double totalGasto;
    public Cliente (){
        super();
        this.totalGasto = 0.0;
    }

    public Cliente (Localizacao gps, String nome, String email,
        String password,String morada,String dataNasc, double totalGasto){
        super(gps, nome, email, password, morada, dataNasc);
        this.totalGasto = totalGasto;
    }
    public Cliente (Cliente c){
        super(c);
        this.totalGasto = c.getTotalGasto();
    }
    //Getters
    public double getTotalGasto(){
        return this.totalGasto;
    }
    //adiciona valor gasto em viagem
    public void addViagem(double x){
        this.totalGasto += x;
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
            && o.getData().equals(this.getData()) &&
            o.getTotalGasto() == this.getTotalGasto();
    }
    public String toString() {
        StringBuilder str;
        str = new StringBuilder();
        str.append("Nome: \n");
        str.append(this.getNome()+"\n");
        str.append("Email: \n");
        str.append(this.getEmail()+"\n");
        str.append("Localizacao: \n");
        str.append(this.getLocal()+"\n");
        str.append("Morada: \n");
        str.append(this.getMorada()+"\n");
        str.append("Data de Nascimento: \n");
        str.append(this.getData()+"\n");
        str.append("Total Gasto: \n");
        str.append(this.getTotalGasto()+"\n");
        return str.toString();
    }



}
