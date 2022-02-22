package fp.tipos.musica.test;

import java.time.Duration;
import java.time.LocalDate;

import fp.tipos.musica.Cancion;
import fp.tipos.musica.Genero;

public class TestCancion {

	public static void main(String[] args) {
		Duration duracion = Duration.ofSeconds(200);
		LocalDate fecha = LocalDate.of(2008, 4, 20);
		
		Cancion cancion1 = new Cancion("Whole Lotta Love"
				, "Led Zeppelin", 
				duracion, fecha, Genero.ROCK);
		
		System.out.println(cancion1.getFormatoCorto());
	}

}
