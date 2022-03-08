package fp.tipos.musica;

import java.util.ArrayList;
import java.util.List;

import static fp.utiles.Checkers.*;

public class Album {
	/*
	 * nombre, de tipo String, consultable. El nombre del álbum • id, de tipo
	 * String, consultable. Un identificador unívoco del álbum. • popularidad, de
	 * tipo Integer, consultable. Un entero que representa la popularidad del álbum.
	 * • año de publicación, de tipo Integer, consultable. Año de publicación del
	 * álbum. • tipo del álbum, consultable. Podrá tener uno de los siguientes
	 * valores: ALBUM, SINGLE, COMPILATION. • canciones, de tipo List<Cancion>,
	 * consultable. Es una lista de las canciones que forman el álbum. • imagenes,
	 * de tipo List<String>, consultable. Es una lista que contendrá las rutas de
	 * las imágenes relacionadas con el álbum.
	 */

	private String nombre, id;
	private Integer popularidad;
	private Integer anyoPublicacion;
	private TipoAlbum tipoAlbum;
	private List<Cancion> canciones;
	private List<String> imagenes;

	public Album(String nombre, String id, Integer popularidad, Integer anyoPublicacion, TipoAlbum tipoAlbum) {
		checkID(id);
		checkPopularidad(popularidad);
		this.nombre = nombre;
		this.id = id;
		this.popularidad = popularidad;
		this.anyoPublicacion = anyoPublicacion;
		this.tipoAlbum = tipoAlbum;
		canciones = new ArrayList<>();
		imagenes = new ArrayList<>();
	}

	private void checkPopularidad(Integer popularidad2) {
		if (popularidad2 < 0 || popularidad2 > 100) {
			throw new IllegalArgumentException();
		}

	}

	private void checkID(String id2) {
		if (id2.length() != 22) {
			throw new IllegalArgumentException();
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getId() {
		return id;
	}

	public Integer getPopularidad() {
		return popularidad;
	}

	public Integer getAnyoPublicacion() {
		return anyoPublicacion;
	}

	public TipoAlbum getTipoAlbum() {
		return tipoAlbum;
	}

	public List<Cancion> getCanciones() {
		return new ArrayList<>(canciones);
	}

	public List<String> getImagenes() {
		return new ArrayList<>(imagenes);
	}

	public String toString() {
		return "Album [nombre=" + nombre + ", id=" + id + ", popularidad=" + popularidad + ", anyoPublicacion="
				+ anyoPublicacion + ", tipoAlbum=" + tipoAlbum + "]";
	}
	
	public void incorporaImagen(String ruta) {
		checkURLImagen(ruta);
		imagenes.add(ruta);
	}
	
	public void eliminaImagen(int posicion) {
		checkCondicion(posicion < 0 || posicion >= imagenes.size());
		imagenes.remove(posicion);
	}

	private void checkURLImagen(String ruta) {
		if(!ruta.startsWith("http")) {
			throw new IllegalArgumentException();
		}
		
	}

	public void incorporaCancion(Cancion c) {
		incorporaCancion(c, canciones.size());
	}

	public void incorporaCancion(Cancion c, int posicion) {
		checkCondicion(posicion < 0 || posicion > canciones.size());
		if (!canciones.contains(c)) {
			canciones.add(posicion, c);
		}
	}

	public void eliminaCancion(Cancion c) {
		int posicion = canciones.indexOf(c);
		if (posicion != -1) {
			eliminaCancion(posicion);
		}
	}

	public void eliminaCancion(int posicion) {
		checkCondicion(posicion < 0 || posicion >= canciones.size());
		canciones.remove(posicion);
	}
}
