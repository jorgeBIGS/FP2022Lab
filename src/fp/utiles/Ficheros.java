package fp.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ficheros {
	public static List<String> leeLineas(String path, Boolean eliminaCab) {
		List<String> listaLineas = new ArrayList<>();
		try {
			listaLineas.addAll(Files.readAllLines(Paths.get(path)));
			if(eliminaCab) {
				listaLineas.remove(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listaLineas;
	}
}
