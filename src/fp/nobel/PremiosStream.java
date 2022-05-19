package fp.nobel;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PremiosStream implements Premios {
	private Set<Premio> premios;

	public PremiosStream() {
		premios = new HashSet<>();
	}

	public PremiosStream(Stream<Premio> premios) {
		this.premios = new HashSet<>(premios.collect(Collectors.toSet()));
	}

//	public PremiosStream(Set<Premio> premios) {
//		this.premios = new HashSet<Premio>(premios);
//	}

	public Set<Premio> getPremios() {
		return Collections.unmodifiableSet(premios);
	}

	public String toString() {
		return "PremiosStream [getPremios()=" + premios + "]";
	}

	public int hashCode() {
		return Objects.hash(premios);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PremiosStream other = (PremiosStream) obj;
		return Objects.equals(premios, other.premios);
	}

	public void anyadirPremio(Premio p) {
		premios.add(p);

	}

	public List<Premio> obtenerPremiosDeGenero(Genero genero) {
		return getPremios().stream().
				filter((Premio p) -> p.genero().equals(genero)).
				toList();
	}

	public Map<Genero, Integer> calcularNumeroPremiosPorGenero() {
		return getPremios().stream().collect(
				Collectors.groupingBy(Premio::genero,
				Collectors.collectingAndThen(
						Collectors.counting(), Long::intValue)));
	}

	public Map<Integer, List<Premio>> calcularPremiosPorEdad() {
		return getPremios().stream().collect(
				Collectors.groupingBy(Premio::edadPremiado));
	}

	public Map<String, Double> calcularMediaEdadPorCategoria() {
		return getPremios().stream()
				.collect(Collectors.groupingBy(
						Premio::categoria, 
						Collectors.averagingInt(Premio::edadPremiado)));
	}

	public Long calcularNumeroPremiadosMasJovenesDe(Integer edad) {
		return getPremios().stream().
				filter((Premio p) -> p.edadPremiado() < edad)
				.count();

	}

}
