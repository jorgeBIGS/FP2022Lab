package fp.tipos.vino;

import static fp.utiles.Checkers.*;

public record Vino(String pais, String region, Integer puntos, Double precio, String uva) {
	
	private static final String delim = ",";
	
	public Vino{
		checkCondicion(puntos>=0 && puntos<=100);
		checkCondicion(precio>0);
	}

	
	public static Vino createVino(String s) {
		String [] splits = s.split(delim);
		
		checkCondicion(splits.length==5);
		
		String pais = splits[0].trim();
		
		String region = s.split(delim)[1].trim();
		Integer puntos = Integer.valueOf(s.split(delim)[2].trim());
		Double precio = Double.valueOf(s.split(delim)[3].trim());
		String uva = s.split(delim)[4].trim();
		
		return new Vino(pais, region, puntos, precio, uva);
	}

	
	public Double getCalidadPrecio() {
		return puntos/precio;
	}
}
