import java.util.Comparator;
import java.util.Map;

public class ComparadorCustos implements Comparator<Map.Entry<String,Utilizador>>{


	public int compare (Map.Entry<String,Utilizador> p1, Map.Entry<String,Utilizador> p2){
		Cliente c1 = (Cliente) p1.getValue();
		Cliente c2 = (Cliente) p2.getValue();
		double p = (c1.totalPreco() - c2.totalPreco());
		if (p>0) return -1;
		if (p<0) return 1;
		else return 0;
	}
}