package fp.nobel;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Premios {
	
	void anyadirPremio(Premio p);
	
	Set<Premio> getPremios();
	
	List<Premio> obtenerPremiosDeGenero(Genero genero);
	
	Long calcularNumeroPremiadosMasJovenesDe(Integer edad);
	
	Map<Genero, Integer> calcularNumeroPremiosPorGenero();
	
	Map<Integer, List<Premio>> calcularPremiosPorEdad();
	
	Map<String, Double> calcularMediaEdadPorCategoria();
}
