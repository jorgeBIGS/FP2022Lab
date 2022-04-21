package fp.tipos.futbol;

import static fp.utiles.Checkers.checkNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Competicion {
	private String nombre;
	private Integer anyo;
	private List<Partido> partidos;

	public void incorporaPartido(Partido p) {
		if (!partidos.contains(p)) {
			partidos.add(p);
		}
	}

	public void incorporaPartidos(List<Partido> lista) {
		for (Partido partido : lista) {
			incorporaPartido(partido);
		}
	}

	public Competicion(String nombre, Integer anyo) {
		checkNotNull(nombre, anyo);
		this.nombre = nombre;
		this.anyo = anyo;
		this.partidos = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getAnyo() {
		return anyo;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public Integer getNumeroPartidos() {
		return getPartidos().size();
	}

	public String toString() {
		return "Competicion [nombre=" + nombre + ", anyo=" + anyo + ", getNumeroPartidos()=" + getNumeroPartidos()
				+ "]";
	}

	public int hashCode() {
		return Objects.hash(anyo, nombre);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competicion other = (Competicion) obj;
		return Objects.equals(anyo, other.anyo) && Objects.equals(nombre, other.nombre);
	}

//	Integer getVictoriasVisitantes(): obtiene el número total de victorias obtenidas por los equipos visitantes.
	public Integer getVictoriasVisitantes() {
		Integer res = 0;
		for (Partido p : partidos) {
			if (p.golesVisitante() > p.golesLocal()) {
				res++;
			}
		}
		return res;
	}

//	• Integer getTotalGolesMarcados(String equipo): obtiene el número total de goles marcados por el equipo
//	dado como parámetro.
	public Integer getTotalGolesMarcados(String equipo) {
		Integer res = 0;
		for (Partido p : partidos) {
			if (p.local().equals(equipo)) {
				res += p.golesLocal();
			}
			if (p.visitante().equals(equipo)) {
				res += p.golesVisitante();
			}
		}
		return res;
	}

//	• Set<String> getEquipos(): obtiene un conjunto con los nombres de todos los equipos que participan en 
//	la competición.
	public Set<String> getEquipos() {
		Set<String> equipos = new HashSet<>();
		for (Partido p : partidos) {
			equipos.add(p.local());
			equipos.add(p.visitante());
		}
		return equipos;
	}

//	• PartidoFutbol getPartidoMasGoles(): obtiene el partido en el que se han marcado más goles.
	public Partido getPartidoMasGoles() {
		Comparator<Partido> comparador = Comparator.comparing(x -> x.golesLocal() + x.golesVisitante());
		return Collections.max(partidos, comparador);
	}

	public Partido getPartidoMasGoles2() {
		Partido result = null;

		for (Partido p : partidos) {
			if (result == null || result.golesLocal() + result.golesVisitante() < p.golesLocal() + p.golesVisitante()) {
				result = p;
			}
		}

		return result;
	}

//	• Integer getPuntosEquipoFecha(String equipo, LocalDate fecha): obtiene el número total de puntos 
//	obtenidos por el equipo dado como parámetro hasta la fecha dada como parámetro. Se consideran los 
//	partidos anteriores a esa fecha.

	public Integer getPuntosEquipoFecha(String equipo, LocalDate fecha) {

		Integer result = 0;

		for (Partido p : getPartidosAnterioresA(equipo, fecha)) {
			if (p.golesLocal().equals(p.golesVisitante())) {
				result += 1;
			} else {
				if (p.getGanaPartido(equipo)) {
					result += 3;
				}
			}

		}

		return result;
	}

	public Set<Partido> getPartidosAnterioresA(String equipo, LocalDate f) {
		Set<Partido> result = new HashSet<>();
		for (Partido p : partidos) {
			if ((p.local().equals(equipo) || p.visitante().equals(equipo))
					&& (p.fecha().toLocalDate().isBefore(f) || p.fecha().toLocalDate().equals(f))) {
				result.add(p);
			}
		}
		return result;
	}
	
	

//	• : crea un Map que relaciona cada resultado 
//	con el número de partidos en los que se ha dado ese resultado.
	
	public Map<ResultadoQuiniela, Integer> contarNumPartidosPorResultado(){
		Map<ResultadoQuiniela, Integer> result = new HashMap<>();
		
		for(Partido p: partidos) {
			ResultadoQuiniela clave = p.resultado();
			if(!result.containsKey(clave))
			{
				result.put(clave, 1);
			}else {
				Integer valor = result.get(clave);
				valor++;
				result.put(clave, valor);
			}
		}
		
		return result;
	}
	
//	• Map <String, Integer> getClasificacionFinal(): crea un Map que relaciona cada nombre de un equipo con 
//	el total de puntos obtenidos en la competición por ese equipo.
//	• Map<String, Integer> contarPartidosGanadosPorEquipo(): crea un Map que relaciona cada equipo con el 
//	número de partidos ganados por ese equipo.
//	• SortedMap<LocalDate, List<PartidoFutbol>> getPartidosPorFecha (): crea un SortedMap que relaciona 
//	cada fecha con los partidos que se disputaron en esa fecha.
//	• SortedMap<Integer, Set<String>> equiposPorNumeroPartidosGanados(): crea un SortedMap que 
//	relaciona cada número con el conjunto de los equipos que han ganado ese número de partidos.
//	• String getCampeon(): obtiene el nombre del equipo ganador de la competición

}
