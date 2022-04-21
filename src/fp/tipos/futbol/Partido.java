package fp.tipos.futbol;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static fp.tipos.futbol.ResultadoQuiniela.*;
import static fp.utiles.Checkers.*;

public record Partido(LocalDateTime fecha, String local, String visitante, Integer golesLocal, Integer golesVisitante)
		implements Comparable<Partido> {
	public Partido(LocalDateTime fecha, String local, String visitante, Integer golesLocal, Integer golesVisitante) {
		checkNotNull(local, visitante, golesLocal, golesVisitante);
		checkGoles(golesLocal);
		checkGoles(golesVisitante);
		checkNombre(local);
		checkNombre(visitante);
		this.visitante = visitante;
		this.local = local;
		this.golesVisitante = golesVisitante;
		this.golesLocal = golesLocal;
		this.fecha = fecha;
	}

	private void checkNombre(String local) {
		if (local.equals("")) {
			throw new IllegalArgumentException("formato de cadena no valido.");
		}
	}

	private void checkGoles(Integer golesLocal) {
		if (golesLocal < 0) {
			throw new IllegalArgumentException("formato de entero no valido.");
		}
	}

	public Boolean getGanaPartido(String equipo) {
		Boolean result = false;

		if (equipo.equals(this.local) && golesLocal > golesVisitante
				|| 
			equipo.equals(this.visitante) && golesLocal < golesVisitante) {
			result = true;
		}
		return result;
	}

	public ResultadoQuiniela resultado() {
		ResultadoQuiniela result;

		if (golesLocal > golesVisitante) {
			result = UNO;
		} else if (golesLocal < golesVisitante) {
			result = DOS;
		} else {
			result = EQUIS;
		}

		return result;
	}

	public String cadenaConFormato() {
		String valor;
		if (resultado().equals(ResultadoQuiniela.EQUIS)) {
			valor = "X";
		} else if (resultado().equals(ResultadoQuiniela.UNO)) {
			valor = "1";
		} else {
			valor = "2";
		}
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yy");
		String formatoFecha = fecha().format(formater);
		return formatoFecha + "-> " + local() + " vs " + visitante() + ": " + golesLocal() + "-" + golesVisitante()
				+ " (" + valor + ")";

	}

	public int compareTo(Partido o) {
		int res;
		res = fecha.compareTo(o.fecha);
		if (res == 0) {
			res = local.compareTo(o.local);
		}
		if (res == 0) {
			res = visitante.compareTo(o.visitante);
		}
		return res;
	}

	public int hashCode() {
		return Objects.hash(fecha, local, visitante);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(local, other.local)
				&& Objects.equals(visitante, other.visitante);
	}

	/*
	 * 
	 * • cadena con formato, de tipo String. Devuelve una cadena con la fecha del
	 * partido, seguido del equipo local, el equipo visitante, los goles del equipo
	 * local, los goles del equipo visitante, y entre paréntesis el resultado de la
	 * quiniela, tal y como se muestra en el siguiente ejemplo: “24-09-16-> Sporting
	 * Gijón vs Barcelona: 0-5 (2)”. Tenga en cuenta que el resultado de la quiniela
	 * se debe representar con los caracteres 1, X ó 2.
	 * 
	 */

}
