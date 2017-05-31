public class Cliente extends Utilizador{
    private doube totalGasto;
    public Cliente (){
        super(0.0,0.0,"n/a","n/a","n/a","n/a","n/a",0.0);
    }

    public Cliente (Localizacao gps, String nome, String email,
        String password,String morada,String dataNasc, double totalGasto){
        super(gps, nome, email, password, morada, dataNasc, totalGasto);
    }
    public Cliente (Cliente c){
        super(c);
        this.totalGasto = c.getTotalGasto();
    }
    //Getters
    public double getTotalGasto{
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
            && o.getData().equals(this.getDataNasc()) &&
            o.getTotalGasto().equals(this.getTotalGasto());
    }
    public String toString() {
        StringBuilder str;
        str = new StringBuilder();
        str.append("Nome: \n");
        str.append(this.nome+"\n");
        str.append("Email: \n");
        str.append(this.email+"\n");
        str.append("Localizacao: \n");
        str.append(this.gps+"\n");
        str.append("Morada: \n");
        str.append(this.morada+"\n");
        str.append("Data de Nascimento: \n");
        str.append(this.dataNasc+"\n");
        str.append("Total Gasto: \n");
        str.append(this.totalGasto+"\n");
        return str.toString();
    }



}
