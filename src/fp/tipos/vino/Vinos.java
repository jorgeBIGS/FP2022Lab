package fp.tipos.vino;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class Vinos {
	private Set<Vino> setVinos;

	public Vinos(Stream<Vino> streamVinos) {
		this.setVinos = streamVinos.collect(Collectors.toSet());
	}
	
	public Vinos() {
		this.setVinos = new HashSet<Vino>();
	}

	public String toString() {
		return "Vinos [numeroVinos=" + setVinos.size() + "]";
	}
	
	public void agregarVino(Vino v) {
		setVinos.add(v);
	}
	
	public void eliminarVino(Vino v) {
		if (!setVinos.contains(v)) {
			throw new IllegalArgumentException("El vino no está contenido");
		}
		else {
			setVinos.remove(v);
		}
	}
	
	public Integer obtenerNumeroVinos() {
		return setVinos.size();
	}
	
	public Boolean contieneVino(Vino v) {
		return setVinos.contains(v);
	}
	
	public void agregarVinos(Collection<Vino> coleccionVinos) {
		setVinos.addAll(coleccionVinos);
	}
	
	public Boolean contieneVinos(Collection<Vino> coleccionVinos) {
		return setVinos.containsAll(coleccionVinos);
	}
	
	public Long calcularNumeroVinosPais(String pais) {
		return setVinos.stream().filter(x -> x.pais().equals(pais)).count();
	}
	
	
	public List<Vino> obtenerVinosRangoPuntos(Integer inf, Integer sup) {
		
		Checkers.checkCondicion(sup>inf);
		return setVinos.stream().filter(x -> x.puntos() >= inf && x.puntos() <= sup).
				toList();
	}
	
	public Long calcularNumeroVinosDePaisConPuntuacionSuperior(String pais, 
			Integer umbralPuntuacion) {
		Predicate<Vino> pred1 = x -> x.pais().equals(pais) ;
		Predicate<Vino> pred2 = x -> x.puntos() > umbralPuntuacion;
		return setVinos.stream().filter(pred1.and(pred2)).count();
	}

	public List<Vino> obtenerVinosBaratos(Double precio) {
		return setVinos.stream().filter(x -> x.precio() < precio).collect(Collectors.toList());
	}
	
	public Boolean existeVinoDeUvaEnRegion(String uva, String region) {
		return setVinos.stream().anyMatch(x -> x.region().equals(region) && x.uva().equals(uva));
	}
	
	
	//Hasta aquí 10/05
	public Double calcularMediaPuntosVinosDeUva(String uva) {
		return setVinos.stream().filter(x -> x.uva().equals(uva)).mapToInt(x -> x.puntos()).average().getAsDouble();
	}

	public Integer calcularTotalPuntosVinosDeRegion(String region) {
		return setVinos.stream().filter(x -> x.region().equals(region)).mapToInt(x -> x.puntos()).sum();
	}

	

	

	

	public List<Vino> obtenerNVinosRegionOrdenadosPrecio(String region, Integer n) {
		return setVinos.stream().filter(x -> x.region().equals(region)).sorted(Comparator.comparing(Vino :: precio).reversed()).limit(n).collect(Collectors.toList());
	}

	

	public Vino obtenerVinoMejorPuntuado() {
		return setVinos.stream().max(Comparator.comparing(Vino :: puntos)).get();
	}

	public Vino obtenerVinoMejorPuntuadoDePais(String pais) {
		return setVinos.stream().filter(x -> x.pais().equals(pais)).max(Comparator.comparing(Vino :: puntos)).get();
	}

	public Map<String, List<Vino>> agruparVinosPorPais() {
		return setVinos.stream().collect(Collectors.groupingBy(Vino :: region));
	}

	public Set<String> obtenerUvasDeRegion(String region) {
		return setVinos.stream().filter(x -> x.region().equals(region)).map(x -> x.uva()).collect(Collectors.toSet());
	}

	public Map<String, Long> calcularCalidadPrecioPorRegionMayorDe(Double umbral) {
		return setVinos.stream().filter(x -> x.getCalidadPrecio() > umbral).collect(Collectors.groupingBy(x -> x.region(), Collectors.counting()));
	}

	public Map<String, Set<String>> agruparUvasPorPais() {
		return setVinos.stream().collect(Collectors.groupingBy(x -> x.pais(), Collectors.mapping(x -> x.uva(), Collectors.toSet())));
	}	
}
