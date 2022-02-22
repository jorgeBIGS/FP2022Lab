package fp.tipos.futbol;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static fp.tipos.futbol.ResultadoQuiniela.*;

public record Partido(LocalDateTime fecha, String local, String visitante, Integer golesLocal, Integer golesVisitante) {

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
		DateTimeFormatter formater = 
				DateTimeFormatter.ofPattern("dd-MM-yy");
		String formatoFecha = fecha().format(formater);
		return formatoFecha + "-> " + local() + " vs " + visitante() + ": " + golesLocal() + "-" + golesVisitante()
				+ " (" + valor + ")";

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
