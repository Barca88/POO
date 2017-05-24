import java.lang.Math;


public class Localizacao{
    // instance variables - replace the example below with your own
    private int x;
    private int y;

    //Construtores
    public Localizacao(int x,int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y=y;
    }
    
    public static double Distancia(Localizacao a, Localizacao b){
        double dist = Math.sqrt((b.x - a.x)^2 + (b.y - b.y)^2)*10000;
        return dist;
    }
    
    public static double TempoDesloca( double VelMedia, Localizacao a, Localizacao b){
        double dist = Distancia(a,b);
        double tempo = dist * VelMedia;
        return tempo;
    }
        
}