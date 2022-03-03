package fp.tipos.hoteles;

public record Coordenada(Double latitud, Double longitud) {
	
	public static final  Double R_TIERRA = 6371.0;
	
	public Double getDistancia(Coordenada c){
		Double aux1 = Math.pow(c.latitud() - this.latitud(), 2);
		Double aux2 = Math.pow(c.longitud() - this.longitud(), 2);
		return Math.sqrt(aux1 + aux2);
		
	}
	
	public Double getDistanciaHarvesine(Coordenada c) {
		Double diferencia_latitud = Math.toRadians(c.latitud()	- this.latitud());
		Double diferencia_longitud = Math.toRadians(c.longitud() - this.longitud());
		Double a = Math.pow(Math.sin(diferencia_latitud/2), 2) + Math.cos(this.latitud())*
				Math.cos(c.latitud()) * Math.pow(Math.sin(diferencia_longitud/2), 2);
		Double c_prima = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return R_TIERRA * c_prima;		
		
	}

}
