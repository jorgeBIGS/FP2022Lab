package fp.nobel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PremiosBucle implements Premios {
	private Set<Premio> premios;

	public PremiosBucle() {
		premios = new HashSet<>(premios);
	}

	public PremiosBucle(Stream<Premio> premios) {
		this.premios = new HashSet<>(premios.collect(Collectors.toSet()));
	}

//	public PremiosBucle(Set<Premio> premios) {
//		this.premios = new HashSet<>(premios);
//	}

	public Set<Premio> getPremios() {
		return Collections.unmodifiableSet(premios);
	}

	public void anyadirPremio(Premio p) {
		premios.add(p);
	}

	public List<Premio> obtenerPremiosDeGenero(Genero genero) {
		List<Premio> res = new ArrayList<>();
		for (Premio p : premios) {
			if (p.genero().equals(genero)) {
				res.add(p);
			}

		}

		return res;
	}

	public Long calcularNumeroPremiadosMasJovenesDe(Integer edad) {
		Long res = 0l;
		for (Premio p : premios) {
			if (p.edadPremiado() < edad) {
				res++;

			}

		}

		return res;
	}

	public Map<Genero, Integer> calcularNumeroPremiosPorGenero() {
		Map<Genero, Integer> res = new HashMap<>();

		for (Premio p : premios) {
			Genero clave = p.genero();

			if (res.containsKey(clave)) {
				res.put(clave, res.get(clave) + 1);
			} else {
				res.put(clave, 1);
			}

		}
		return res;
	}

	public Map<Integer, List<Premio>> calcularPremiosPorEdad() {
		Map<Integer, List<Premio>> res = new HashMap<>();
		for (Premio p : premios) {
			Integer clave = p.edadPremiado();
			
			if (!res.containsKey(clave)) {
				List<Premio> aux = new ArrayList<>();
				aux.add(p);
				res.put(clave, aux);
			}else{
				res.get(clave).add(p);
			}

		}

		return res;
	}

//	public Map<String, Double> calcularMediaEdadPorCategoria() {
//		Map<String, Double> res = new HashMap<>();
//		Map<String, Integer> contador = new HashMap<>();
//		
//		for (Premio p : premios) {
//			String clave = p.categoria();
//			if (res.containsKey(clave)) {
//				Double mediaAnterior = res.get(clave);
//				Double media =  (mediaAnterior * contador.get(clave)
//						+ p.edadPremiado()) / (contador.get(clave) + 1) ;
//				
//				res.put(clave,media);
//				contador.put(clave, contador.get(clave) + 1);
//
//			} else {
//				res.put(clave, p.edadPremiado() * 1.);
//				contador.put(clave, 1);
//
//			}
//
//		}
//
//		return res;
//	}

	private Map<String, Integer> contadorPersonasPorCategoria(){
		Map<String, Integer> result = new HashMap<>();
		
		for (Premio p : premios) {
			String clave = p.categoria();
			if (!result.containsKey(clave)) {
				result.put(clave, 1);				
			} else {
				result.put(clave, result.get(clave) + 1);
			}
		}
		return result;
	}

	public Map<String, Double> calcularMediaEdadPorCategoria() {
		Map<String, Double> res = new HashMap<>();
		Map<String, Integer> contador = contadorPersonasPorCategoria();
		
		for (Premio p : premios) {
			String clave = p.categoria();
			if (res.containsKey(clave)) {
				res.put(clave, res.get(clave) + 
						(p.edadPremiado() *1.0)/contador.get(clave));
			} else {
				res.put(clave, p.edadPremiado() * 1./contador.get(clave));
			}

		}

		return res;
	}
}
