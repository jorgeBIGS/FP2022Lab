package fp.tipos.vino;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FactoriaVinos {
	public static Vino parsearVino(String s) {
		return Vino.createVino(s);
	}
	
	public static Vinos leerVinos(String ruta) {
		Vinos vinos = null;
		try {
			vinos = new Vinos (
					Files.readAllLines(Paths.get(ruta)).stream().skip(1).map(s -> parsearVino(s)));
		} catch (IOException e) {
			System.err.println("Error en la lectura del fichero '" + ruta + "'");
		}
		return vinos;
	}
}
