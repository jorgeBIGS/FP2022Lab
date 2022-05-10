package fp.tipos.vinos.test;

import fp.tipos.vino.FactoriaVinos;
import fp.tipos.vino.Vinos;


public class TestFactoriaVinos {
	
	public static void main(String[] args) {
		testCreacionVinos("data/wine_reviews.csv");
	}

	private static void testCreacionVinos(String filename) {
		System.out.println("\nTEST de la creacion de vinos");
		try {
			Vinos vinos = FactoriaVinos.leerVinos(filename);
			System.out.println("   VINOS: "+ vinos);
		} catch(Exception e) {
			System.out.println("Excepción inesperada capturada:\n   " + e);	
		}
	}
}
