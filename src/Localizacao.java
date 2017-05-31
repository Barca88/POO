import java.lang.Math;

public class Localizacao{
    // instance variables - replace the example below with your own
    private double x;
    private double y;

    //Construtores
    public Localizacao(){
        this.x = 0.0;
        this.y = 0.0;
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
    public static double Distancia(Localizacao a, Localizacao b){
        double dist = Math.sqrt((b.x - a.x)^2 + (b.y - b.y)^2);
        return dist;
    }
    public static double TempoDesloca(double VelMedia,
                Localizacao a, Localizacao b){
        double dist = Distancia(a,b);
        double tempo = dist * VelMedia;
        return tempo;
    }

}
