package fp.nobel;

import java.util.Objects;

import fp.utiles.Checkers;

public record Premio(Integer anyo, String categoria, String nombre, String apellidos, Genero genero,Integer anyoNacimiento ) {
	
	public Premio (Integer anyo, String categoria, String nombre, String apellidos, Genero genero, Integer anyoNacimiento ){
		Checkers.checkCondicion(anyo>anyoNacimiento);
		this.anyo = anyo;
		this.categoria = categoria;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.anyoNacimiento = anyoNacimiento;
		
	}

	public Integer edadPremiado() {
		return anyo()-anyoNacimiento() ;
	}

	
	public int hashCode() {
		return Objects.hash(apellidos, anyo, categoria, nombre);
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Premio other = (Premio) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(anyo, other.anyo)
				&& Objects.equals(categoria, other.categoria) && Objects.equals(nombre, other.nombre);
	}

}


