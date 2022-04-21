package fp.tipos.futbol;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaCompeticiones {
	public static Partido createPartido(String s) {
		String[] splits = s.split(";");
		Checkers.checkCondicion(splits.length != 5);
		DateTimeFormatter formateador=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha=LocalDate.parse(splits[0].trim(), formateador);
		String local=splits[1].trim();
		String visitante= splits[2].trim();
		Integer golesLocal=Integer.valueOf(splits[3].trim()); 
		Integer golesVisitante=Integer.valueOf(splits[4].trim()); 
		
		return new Partido(
				LocalDateTime.of(fecha, 
						LocalTime.of(0, 0)), 
				local, visitante, golesLocal, golesVisitante);
	}
	
	public static Competicion createCompeticion(String ruta, String nombre, Integer anyo ) {
		Competicion result=new Competicion(nombre, anyo);
		List<String> lineas = Ficheros.leeLineas(ruta, true);
		for (String linea:lineas) {
			Partido p = createPartido(linea);
			result.incorporaPartido(p);
		}
		
		return result;
	}
}
