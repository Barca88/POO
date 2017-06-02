import java.util.Comparator;

public class ComparadorDistancias implements Comparator<Taxi>{
	private Localizacao luser;

	public ComparadorDistancias(Localizacao l){
		this.luser = l;
	}

	public int compare (Taxi t1, Taxi t2){
		double r = (Localizacao.distancia(luser,t1.getLocal()) - Localizacao.distancia(luser,t1.getLocal()));
		if (r>0) return 1;
		if (r<0) return -1;
		else return 0;
	}
}