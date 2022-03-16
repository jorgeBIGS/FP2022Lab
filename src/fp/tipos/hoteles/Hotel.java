package fp.tipos.hoteles;

public record Hotel(String nombre, String direccion, String ciudad, String telefono, String cadenaHostelera,
		String descripcion, CategoriaHotel categoria, TipoAlojamiento tipo, CategoriaPrecio c_precio, Float puntuacion,
		Integer n_comentarios, Boolean mascotas, Boolean adaptado, Coordenada coordenada) {

	public Hotel{
		checkPuntuacion(puntuacion);
		checkNumComentarios(n_comentarios);
	}
	
	public Hotel(String nombre, String cadenaHostelera, CategoriaHotel categoria) {
		this(nombre, null, null, null, cadenaHostelera, null, categoria, null, CategoriaPrecio.MEDIA, null, 0, false,
				false, null);

	}

	public Hotel(String nombre, String direccion, String ciudad, String telefono, String cadenaHostelera,
			CategoriaHotel categoria, CategoriaPrecio c_precio, Coordenada coordenada) {
		this(nombre, direccion, ciudad, telefono, cadenaHostelera, null, categoria, null, c_precio, null, 0, null, null,
				coordenada);

	}
	
	private void checkNumComentarios(Integer n_come) {
		if(!(n_come==null || n_come>=0)) {
			throw new IllegalArgumentException();
		}
	}

	private void checkPuntuacion(Float f) {
		if(f!=null && f<0.0) {
			throw new IllegalArgumentException();
		}
	}

	

	public String getCadenaConFormato() {
		return nombre() + "(" + generaAsteriscos() + ")";
	}

	private String generaAsteriscos() {
		String result;
		switch (categoria()) {
		case UNA:
			result = "*";
			break;
		case DOS:
			result = "**";
			break;
		case TRES:
			result = "***";
			break;
		case CUATRO:
			result = "****";
			break;
		case CINCO:
			result = "*****";
			break;
		default:
			result = "N/A";
		}
		return result;
	}

}
