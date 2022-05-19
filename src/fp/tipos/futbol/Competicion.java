package fp.tipos.futbol;
import static fp.utiles.Checkers.*;
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
import java.util.SortedMap;
import java.util.TreeMap;

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
		checkNoNull(nombre, anyo);
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

	public Map<ResultadoQuiniela, Integer> contarNumPartidosPorResultado() {
		Map<ResultadoQuiniela, Integer> result = new HashMap<>();

		for (Partido p : partidos) {
			ResultadoQuiniela clave = p.resultado();
			if (!result.containsKey(clave)) {
				result.put(clave, 1);
			} else {
				Integer valor = result.get(clave);
				valor++;
				result.put(clave, valor);
			}
		}

		return result;
	}

//	• Map <String, Integer> getClasificacionFinal(): crea un Map que relaciona cada nombre de un equipo con 
//	el total de puntos obtenidos en la competición por ese equipo.
	public Map<String, Integer> getClasificacionFinal() {
		Map<String, Integer> result = new HashMap<>();

		for (Partido p : getPartidos()) {
			String keyLocal = p.local();
			String keyVisitante = p.visitante();
			
			ResultadoQuiniela quiniela = p.resultado();
			Integer valorLocal = 0;
			Integer valorVisitante = 0;
			if (quiniela.equals(ResultadoQuiniela.UNO)) {
				valorLocal = 3;
			} else if (quiniela.equals(ResultadoQuiniela.EQUIS)) {
				valorLocal = 1;
				valorVisitante = 1;
			}else {
				valorVisitante = 3;
			}

			if (result.containsKey(keyLocal)) {
				result.put(keyLocal, result.get(keyLocal) + valorLocal);
			} else {
				result.put(keyLocal, valorLocal);
			}
			
			if (result.containsKey(keyVisitante)) {
				result.put(keyVisitante, result.get(keyVisitante) + valorVisitante);
			} else {
				result.put(keyVisitante, valorVisitante);
			}
		}
		

		return result;
	}
	
	public Map<String, Integer> contarPartidosGanadosPorEquipo(){
		Map<String, Integer> result = new HashMap<>();
		
		for(Partido p: getPartidos()) {
			String keyLocal = p.local();
			String keyVisitante = p.visitante();
			
			Integer contadorLocal = 0;
			Integer contadorVisitante = 0;
			
			if(p.resultado().equals(ResultadoQuiniela.UNO)) {
				contadorLocal = 1;
			}else if(p.resultado().equals(ResultadoQuiniela.DOS)){
				contadorVisitante = 1;
			}
			
			if (result.containsKey(keyLocal)) {
				result.put(keyLocal, result.get(keyLocal) + contadorLocal);
			} else {
				result.put(keyLocal, contadorLocal);
			}
			
			if (result.containsKey(keyVisitante)) {
				result.put(keyVisitante, result.get(keyVisitante) + contadorVisitante);
			} else {
				result.put(keyVisitante, contadorVisitante);
			}
		}
		
		return result;
	}
	
	public SortedMap<LocalDate, List<Partido>> getPartidosPorFecha (){
		SortedMap<LocalDate, List<Partido>> result = new TreeMap<>();
		for(Partido p:getPartidos()) {
			LocalDate key = p.fecha().toLocalDate();
			if(result.containsKey(key)) {
				result.get(key).add(p);
			}else {
				List<Partido> aux = new ArrayList<>();
				aux.add(p);
				result.put(key, aux);
			}
			
		}
		return result;
	}


	public SortedMap<Integer, Set<String>> equiposPorNumeroPartidosGanados(){
		Map<String, Integer> aux = contarPartidosGanadosPorEquipo();
		
		SortedMap<Integer, Set<String>> result = new TreeMap<>();
		
		for(String equipo: aux.keySet()) {
			Integer key = aux.get(equipo);
			
			if(result.containsKey(key)) {
				result.get(key).add(equipo);
			}else {
				Set<String> setAux = new HashSet<>();
				setAux.add(equipo);
				result.put(key, setAux);
			}
		}
		
		
		return result;
		
		
	}


	public String getCampeon() {
		Map<String, Integer> clasificacionFinal = getClasificacionFinal();
		Comparator<String> cmp = Comparator.comparing(nombre->clasificacionFinal.get(nombre));
		return Collections.max(clasificacionFinal.keySet(), cmp);
	}
}
