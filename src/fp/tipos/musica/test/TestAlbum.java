package fp.tipos.musica.test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import fp.tipos.musica.Album;
import fp.tipos.musica.Cancion;
import fp.tipos.musica.Genero;
import fp.tipos.musica.TipoAlbum;

public class TestAlbum {

	public static void main(String[] args) {
		Album a = new Album("Freed from Desire", "0123456789012345678901", 100, 1999, TipoAlbum.ALBUM);
		Duration duracion = Duration.ofSeconds(200);
		LocalDate fecha = LocalDate.of(1999, 4, 20);
		
		Cancion cancion1 = new Cancion("Freed from Desire"
				, "Gala", 
				duracion, fecha, Genero.POP);
		a.incorporaCancion(cancion1);
		
		System.out.println(a);
		
		System.out.println(a.getCanciones());

	}

}
