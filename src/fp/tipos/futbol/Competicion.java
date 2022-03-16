package fp.tipos.futbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static fp.utiles.Checkers.*;

public class Competicion {
	private String nombre;
	private Integer anyo;
	private List<Partido> partidos;

	public void incorporaPartido(Partido p) {
		if(!partidos.contains(p)) {
			partidos.add(p);
		}
	}

	public void incorporaPartidos(List<Partido> lista) {
		for(Partido partido: lista) {
			incorporaPartido(partido);
		}
	}

	public Competicion(String nombre, Integer anyo) {
		checkNotNull(nombre, anyo);
		this.nombre = nombre;
		this.anyo = anyo;
		this.partidos = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getAnyo() {
		return anyo;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public Integer getNumeroPartidos() {
		return getPartidos().size();
	}

	public String toString() {
		return "Competicion [nombre=" + nombre + ", anyo=" + anyo + ", getNumeroPartidos()=" + getNumeroPartidos()
				+ "]";
	}

	public int hashCode() {
		return Objects.hash(anyo, nombre);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competicion other = (Competicion) obj;
		return Objects.equals(anyo, other.anyo) && Objects.equals(nombre, other.nombre);
	}

}
