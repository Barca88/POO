import java.util.Comparator;
import java.util.Map;

public class ComparadorDiferencaCustos implements Comparator<Utilizador>{


	public int compare (Utilizador p1, Utilizador p2){
		Motorista m1 = (Motorista) p1;
		Motorista m2 = (Motorista) p2;
		double p = (m1.totalDiferenca() - m2.totalDiferenca());
		if (p>0) return -1;
		if (p<0) return 1;
		else return 0;
	}
}