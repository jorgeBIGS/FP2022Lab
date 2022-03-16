package fp.tipos.musica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class ListReproduccion {

	private String nombre;
	private List<Cancion> canciones;
	
	public ListReproduccion(String nombre) {
		this.nombre = nombre;
		this.canciones = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}
	
	public Integer getNumeroCanciones() {
		return canciones.size();
	}
	
	public void aleatoriza() {
		Collections.shuffle(canciones);
	}
	
	public void incorporaCancion(Cancion c) {
		canciones.add(c);
		
	}
	
	public void incorporaCanciones(List<Cancion> l) {
		canciones.addAll(l);
	}
	
	public void eliminaPrimera(Cancion c) {
		canciones.remove(c);
	}
	
	public void eliminaUltima(Cancion c) {
		int i = canciones.lastIndexOf(c);
		if(i!=-1) {
			canciones.remove(i);
		}
	}
	
	public void eliminaTrozo(Integer inicio, Integer fin) {
		Checkers.checkCondicion(inicio>=0 && fin<getNumeroCanciones() && inicio<=fin);
		List<Cancion> sublista = canciones.subList(inicio, fin+1);
		canciones.removeAll(sublista);
	}
	


	public int hashCode() {
		return Objects.hash(canciones, nombre);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListReproduccion other = (ListReproduccion) obj;
		return Objects.equals(canciones, other.canciones) && Objects.equals(nombre, other.nombre);
	}

	public String toString() {
		return nombre + "("+ getNumeroCanciones() +")";
	}
	
	
}
