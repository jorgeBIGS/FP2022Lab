package fp.tipos.futbol.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fp.tipos.futbol.Competicion;
import fp.tipos.futbol.Partido;

public class TestCompeticion {

	public static void main(String[] args) {
		Competicion liga = new Competicion("La Liga", 2021);
		Partido p = new Partido(LocalDateTime.of(LocalDate.of(2021, 3, 13), 
				LocalTime.of(16, 15)), "Real Betis Balompié", "Athletic Club Bilbao", 1,
				0);
		Partido p2 = new Partido(LocalDateTime.of(LocalDate.of(2021, 3, 13), 
				LocalTime.of(16, 15)), "Rayo Vallecano", "Sevilla F.C.", 1,
				1);
		List<Partido> lista = new ArrayList<>();
		lista.add(p);
		lista.add(p2);
		lista.add(p2);
		
		liga.incorporaPartidos(lista);
		System.out.println(liga);
	}

}
