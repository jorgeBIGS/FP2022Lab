package fp.trenes.test;

import java.time.LocalTime;

import fp.trenes.TipoTren;
import fp.trenes.TrayectoTren;
import fp.trenes.TrayectoTrenImpl2;

public class TestTrayectoTren {

	public static void main(String[] args) {

		TrayectoTren t1 = new TrayectoTrenImpl2("01234", "SEVILLA-MADRID", TipoTren.AV_CITY,
				"SANTA JUSTA", "PUERTA DE ATOCHA", LocalTime.of(10,2), LocalTime.of(12,2));
		mostrar(t1);
		
		t1.anadirEstacionIntermedia(1, "PUERTOLLANO", LocalTime.of(11,3), LocalTime.of(11,15));
		mostrar(t1);
	}

	private static void mostrar(TrayectoTren t1) {
		System.out.println(t1);
	}

}
