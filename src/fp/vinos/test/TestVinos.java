package fp.vinos.test;

/**
 * @author José A. Troyano
 * @reviewed Toñi Reina
 * 
 * @revieweddate 2022-04-22
 */

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fp.tipos.vino.FactoriaVinos;
import fp.tipos.vino.Vino;
import fp.tipos.vino.Vinos;

public class TestVinos {

	private static <T> void mostrarMapConColeccion(Map<T, List<Vino>> m, Integer n) {
		for(Map.Entry<T, List<Vino>> entry: m.entrySet()) {
			System.out.println(entry.getKey());
			List<Vino> lv = entry.getValue();
			if (n!= -1 && lv.size() >n){
				lv = lv.subList(0, n);
			}
			mostrarColeccion(lv);
		}	
	}

	private static void mostrarColeccion(Collection<Vino> vinos) {
		vinos.stream()
			.forEach(v->System.out.println("\t"+v));
	}
	
	public static void main(String[] args) {
		Vino vino1 = new Vino("US", "California", 96, 235.0, "Cabernet Sauvignon");
		Vino vino2 = new Vino("Spain", "Northern Spain", 96, 110.0, "Tinta de Toro");
		Vino vino3 = new Vino("US", "California", 96, 90.0, "Sauvignon Blanc");
		Vino vino4 = new Vino("US", "Oregon", 96, 65.0, "Pinot Noir");
		Vino vino5 = new Vino("France", "Provence", 95, 66.0, "Provence red blend");
		Vino vino6 = new Vino("Spain", "Northern Spain", 95, 73.0, "Tinta de Toro");
		Vino vino7 = new Vino("Spain", "Northern Spain", 95, 65.0, "Tinta de Toro");
		Vino vino8 = new Vino("Spain", "Northern Spain", 95, 110.0, "Tinta de Toro");
		Vino vino9 = new Vino("US", "Oregon", 95, 65.0, "Pinot Noir");
		Vino vino10 = new Vino("US", "California", 95, 60.0, "Pinot Noir");
		List<Vino> lista_vinos = List.of(vino1, vino2, vino3, vino4, vino5, vino6, vino7, vino8, vino9, vino10);
		Vinos vinos1 = new Vinos(lista_vinos.stream());
		Vinos vinos2 = FactoriaVinos.leerVinos("data/wine_reviews.csv");

		System.out.println("\nCasos de prueba para el constructor");
		System.out.println("===================================");
		testConstructor(lista_vinos);

		System.out.println("\nCasos de prueba para calcularNumeroVinosDePais");
		System.out.println("================================================");
		testCalcularNumeroVinosPais(vinos1, "Spain");
		testCalcularNumeroVinosPais(vinos2, "Spain");

		System.out.println("\nCasos de prueba para calcularNumeroVinosDePaisConPuntuacionSuperior");
		System.out.println("=====================================================================");
		testCalcularNumeroVinosDePaisConPuntuacionSuperior(vinos1, "Spain", 90);
		testCalcularNumeroVinosDePaisConPuntuacionSuperior(vinos2, "Spain", 90);
	
		System.out.println("Casos de prueba para calcularUvasPorRegion");
		System.out.println("===========================================");
		testCalcularUvasDeRegion(vinos1, "California");
		testCalcularUvasDeRegion(vinos2, "California");
		
		System.out.println("Casos de prueba para obtenerVinosRangoPuntos");
		System.out.println("============================================");
		testObtenerVinosRangoPuntos(vinos1, 90, 95);
		testObtenerVinosRangoPuntos(vinos2, 90, 95);
		// Caso de prueba para ver si se gestionan bien los parámetros
		testObtenerVinosRangoPuntos(vinos1, 95, 90);
		
		System.out.println("\nCasos de prueba para obtenerVinosBaratos");
		System.out.println("==========================================");
		testObtenerVinosBaratos(vinos1, 10.0);
		testObtenerVinosBaratos(vinos2, 10.0);
		
		System.out.println("\nCasos de prueba para existeVinoDeUvaEnRegion");
		System.out.println("==============================================");
		testExisteVinoDeUvaEnRegion(vinos2, "Chardonnay", "California");
		testExisteVinoDeUvaEnRegion(vinos2, "Portuguese White","California");
		
		System.out.println("\nCasos de prueba para calcularTotalPuntosVinosDeRegion");
		System.out.println("==============================================");
		testCalcularTotalPuntosVinosDeRegion(vinos1, "California");
		testCalcularTotalPuntosVinosDeRegion(vinos2, "California");

		
		System.out.println("\nCasos de prueba para calcularTotalPuntosVinosDeRegion");
		System.out.println("==============================================");
		testCalcularMediaPuntosVinosDeUva(vinos1, "Chardonnay");
		testCalcularMediaPuntosVinosDeUva(vinos2, "Chardonnay");

		System.out.println("\nCasos de prueba para obtenerVinoMejorPuntuado");
		System.out.println("===============================================");
		testObtenerVinoMejorPuntuado(vinos1);
		testObtenerVinoMejorPuntuado(vinos2);

		System.out.println("\nCasos de prueba para obtenerVinoMejorPuntuadoDePais");
		System.out.println("=====================================================");
		testObtenerVinoMejorPuntuadoDePais(vinos1, "Spain");
		testObtenerVinoMejorPuntuadoDePais(vinos2, "Spain");
	
		System.out.println("\nCasos de prueba para obtenerNVinosRegionOrdenadosPrecio");
		System.out.println("=====================================================");
		testObtenerNVinosRegionOrdenadosPrecio(vinos1,"California", 3);
		testObtenerNVinosRegionOrdenadosPrecio(vinos2,"California", 10);
		//
//		System.out.println("\nCasos de prueba para agrupaVinosPorPais");
//		System.out.println("=========================================");
//		testCalcularVinosPorPais(vinos1);
//		testCalcularVinosPorPais(vinos2);
//		
//		System.out.println("Casos de prueba para calcularUvasPais");
//		System.out.println("=====================================");
//		testAgruparUvasPorPais(vinos1);
//		testAgruparUvasPorPais(vinos2);
//		System.out.println("Casos de prueba para calcularCalidadPrecioPorRegionMayorDe");
//		System.out.println("==========================================================");		
//		testCalcularCalidadPrecioPorRegionMayorDe(vinos1, 1.2);
//		testCalcularCalidadPrecioPorRegionMayorDe(vinos2, 1.2);
	}



	private static void testCalcularMediaPuntosVinosDeUva(Vinos vinos, String uva) {
		System.out.println("\nTEST de calcularMediaPuntosVinosDeUva");
		try {
			String msg = String.format("La media de puntos de los vinos de la uva %s es %.2f",
					 uva, vinos.calcularMediaPuntosVinosDeUva(uva));
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}

	private static void testCalcularTotalPuntosVinosDeRegion(Vinos vinos, String region) {
		System.out.println("\nTEST de calcularTotalPuntosVinosDeRegion");
		try {
			String msg = String.format("El total de puntos de la región %s es %.2f",
					 region, vinos.calcularTotalPuntosVinosDeRegion(region));
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}

	private static void testExisteVinoDeUvaEnRegion(Vinos vinos, String uva, String region) {
		System.out.println("\nTEST de existeVinoDeUvaEnRegion");
		try {
			String msg = String.format("¿Hay algún vino de la uva %s en la región %s? %s",
					uva, region, vinos.existeVinoDeUvaEnRegion(uva, region));
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}

	private static void testObtenerVinosBaratos(Vinos vinos, Double precio) {
		System.out.println("\nTEST de obtenerVinosBaratos");
		try {
			String msg = String.format("Los vinos del con precio inferior a %.2fson", precio);
			System.out.println(msg);
			mostrarColeccion(vinos.obtenerVinosBaratos(precio));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}

	public static void testConstructor(Collection<Vino> coleccion_vinos) {
		System.out.println("\nTEST del Constructor");
		try {
			Vinos vinos = new Vinos(coleccion_vinos.stream());
			System.out.println("  VINOS: " + vinos + "\n");
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	public static void testCalcularNumeroVinosPais(Vinos vinos, String pais) {
		System.out.println("\nTEST de calcularNumeroVinosDePais");
		try {
			System.out.println(String.format("  Hay %d  vinos de %s: ",
					                 vinos.calcularNumeroVinosPais(pais), pais));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	public static void testObtenerVinosRangoPuntos(Vinos vinos, Integer inf, Integer sup) {
		System.out.println("\nTEST de obtenerVinosRangoPuntos");
		try {
			String msg = String.format("Los vinos del rango [%d,%d] son", inf, sup);
			System.out.println(msg);
			mostrarColeccion(vinos.obtenerVinosRangoPuntos(inf, sup));
		} catch (IllegalArgumentException iae) {
			System.out.println("Excepción capturada: " + iae);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}
	

	private static void testObtenerNVinosRegionOrdenadosPrecio(Vinos vinos, String region, Integer n) {
		System.out.println("\nTEST de obtenerNVinosRegionOrdenadosPrecio");
		try {
			String msg = String.format("Los %d vinos más caros de la región %s son", n, region);
			System.out.println(msg);
			mostrarColeccion(vinos.obtenerNVinosRegionOrdenadosPrecio(region, n));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}

	
	public static void testCalcularNumeroVinosDePaisConPuntuacionSuperior (Vinos vinos, String pais, Integer umbralPuntuacion) {
		System.out.println("\nTEST de calcularNumeroVinosDePaisConPuntuacionSuperior");
		try {
			System.out.println(String.format("  Hay %d  vinos del pais %s con puntuación superior a %d: ",
					                 vinos.calcularNumeroVinosDePaisConPuntuacionSuperior(pais, umbralPuntuacion), 
					                 pais, umbralPuntuacion));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}
	public static void testObtenerVinoMejorPuntuado(Vinos vinos) {
		System.out.println("\nTEST de obtenerVinoMejorPuntuado");
		try {
			System.out.println("  El vino mejor puntuado es: " + vinos.obtenerVinoMejorPuntuado());
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	
	private static void testObtenerVinoMejorPuntuadoDePais(Vinos vinos, String pais) {
		System.out.println("\nTEST de obtenerVinoMejorPuntuadoDePais");
		try {
			System.out.println(String.format("El vino mejor puntuado del pais %s es: %s",
					pais, vinos.obtenerVinoMejorPuntuadoDePais(pais)));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
		
	}
	public static void testCalcularVinosPorPais(Vinos vinos) {
		System.out.println("\nTEST de agruparVinosPorPais");
		try {
			System.out.println("  Vinos por pais: " );
			mostrarMapConColeccion(vinos.agruparVinosPorPais(), 10);
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	public static void testCalcularUvasDeRegion(Vinos vinos, String region) {
		System.out.println("\nTEST de obtenerUvasDeRegion");
		try {
			Set<String> uvas = vinos.obtenerUvasDeRegion(region);
			String msg = String.format("Hay %d uvas de la región %s y son",
					uvas.size(), region );
			System.out.println(msg);
			uvas.stream()
				.forEach(uva->System.out.println("\t"+ uva));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}



	public static void testAgruparUvasPorPais(Vinos vinos) {
		System.out.println("\nTEST de agruparUvasPorPais");
		try {
			System.out.println("Las uvas de cada país son: ");
			Map<String, Set<String>> m= vinos.agruparUvasPorPais();
			m.entrySet().stream()
				.forEach(e->System.out.println("\t"+e));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}

	public static void testCalcularCalidadPrecioPorRegionMayorDe(Vinos vinos, Double umbral) {
		System.out.println("\nTEST de calcularCalidadPrecioPorRegionMayorDe");
		try {
			System.out.println(String.format("  Total vinos de calidad/precio mayor de %.1f: \n", umbral)
					+ vinos.calcularCalidadPrecioPorRegionMayorDe(umbral));
		} catch (Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);
		}
	}
}
