package fp.tipos.futbol.test;

import java.time.LocalDateTime;

import fp.tipos.futbol.Partido;

public class TestPartido {

	public static void main(String[] args) {
		LocalDateTime fecha = LocalDateTime.of(
				2016, 9, 24, 21, 30);
		Partido p = new Partido(fecha,
				"Sporting Gijón", "Barcelona", 0, 5);
		System.out.println(p.cadenaConFormato());
	}

}
