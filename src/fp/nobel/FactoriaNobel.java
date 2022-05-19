package fp.nobel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class FactoriaNobel {

	private static Boolean conStream = false;

	public static void setTipoPremios(Boolean stream) {
		conStream = stream;
	}

	private static Premio parsearPremio(String s) {
		String[] split = s.split(",");
		Checkers.checkCondicion(split.length == 6);

		Integer anyo = Integer.valueOf(split[0].trim());
		String categoria = split[1].trim();
		String nombre = split[2].trim();
		String apellidos = split[3].trim();
		Genero genero = Genero.valueOf(split[4].trim().toUpperCase());
		Integer anyoNacimiento = Integer.valueOf(split[5].trim());

		return new Premio(anyo, categoria, nombre, apellidos, genero, anyoNacimiento);
	}

//	public static PremiosStream leePremios(String nombreFichero, Boolean cabecera) {
//		Set<Premio> res = new HashSet<>();
//		List<String> lineas = Ficheros.leeLineas(nombreFichero, cabecera);
//		
//		for (String l : lineas) {
//			res.add(parsearPremio(l));
//		}
//
//		return new PremiosStream(res);
//
//	}

	public static Premios leePremios(String s) {
		Premios res = null;

		try {
			Stream<Premio> aux = Files.lines(Paths.get(s)).skip(1).map(FactoriaNobel::parsearPremio);

			if (conStream) {
				res = new PremiosStream(aux);
			} else {
				res = new PremiosBucle(aux);
			}

		} catch (IOException e) {
			System.out.println("Fichero no encontrado" + s);
			e.printStackTrace();
		}
		return res;

	}

}
