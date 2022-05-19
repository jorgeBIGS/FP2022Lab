package fp.nobel.test;

import fp.nobel.FactoriaNobel;
import fp.nobel.Genero;
import fp.nobel.Premios;

public class PremiosTest {
	public static void main(String[] args) {
		FactoriaNobel.setTipoPremios(true);
		Premios stream = FactoriaNobel.leePremios("data/nobel_prizes2.csv");
		stream.getPremios().forEach(p->System.out.println(p));
//		System.out.println(stream.obtenerPremiosDeGenero(Genero.MALE));
//		System.out.println(stream.calcularNumeroPremiadosMasJovenesDe(45));
//		System.out.println(stream.calcularNumeroPremiosPorGenero());
//		System.out.println(stream.calcularPremiosPorEdad());
//		System.out.println(stream.calcularMediaEdadPorCategoria());
		
		//------------------------------------------------------------------------------------------------------
		System.out.println("------------------------------------------------------------------");
		
		FactoriaNobel.setTipoPremios(false);
		Premios bucle = FactoriaNobel.leePremios("data/nobel_prizes2.csv");
//		System.out.println(bucle.obtenerPremiosDeGenero(Genero.MALE));
//		System.out.println(bucle.calcularNumeroPremiadosMasJovenesDe(45));
//		System.out.println(bucle.calcularNumeroPremiosPorGenero());
//		System.out.println(bucle.calcularPremiosPorEdad());
//		System.out.println(bucle.calcularMediaEdadPorCategoria());
		

	}
	
}
