import java.lang.Math;
import java.io.Serializable;
public class Localizacao implements Serializable{
    // instance variables - replace the example below with your own
    private double x;
    private double y;

    //Construtores
    public Localizacao(){
        this.x = 0.0;
        this.y = 0.0;
    }
    
    public Localizacao(Localizacao l){
        this.x = l.getX();
        this.y = l.getY();
    }
    
    public Localizacao(double x,double y){
        this.x = x;
        this.y = y;
    }

    //Getters
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    //Setters
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    //Métodos
    public static double distancia(Localizacao a, Localizacao b){
        double dist = Math.sqrt(Math.pow((b.getX() - a.getX()),2) + Math.pow((b.getY() - b.getY()),2));
        return dist;
    }

    public static double TempoDesloca(double VelMedia,
                Localizacao a, Localizacao b){
        double dist = distancia(a,b);
        double tempo = dist * VelMedia;
        return tempo;
    }
    
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(super.toString());
        str.append("Localizacao: \n");
        str.append("X: ");
        str.append(this.x+"\n");
        str.append("Y: ");
        str.append(this.y+"\n");
        return str.toString();
    }
    
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null || obj.getClass() != this.getClass())
            return false;
        Localizacao l = (Localizacao) obj;
        return l.getX() == this.getX() && l.getY() == this.getY();
    }
    
    public Localizacao clone(){
        return new Localizacao (this);
    }
    
}
