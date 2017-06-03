import java.util.Comparator;
import java.util.Map;

public class ComparadorCustos implements Comparator<Utilizador>{


	public int compare (Utilizador p1, Utilizador p2){
		Cliente c1 = (Cliente) p1;
		Cliente c2 = (Cliente) p2;
		double p = (c1.getTotalGasto() - c2.getTotalGasto());
		if (p>0) return -1;
		if (p<0) return 1;
		else return 0;
	}
}