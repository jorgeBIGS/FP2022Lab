package fp.tipos.musica;

import java.time.Duration;
import java.time.LocalDate;

import static fp.utiles.Checkers.*;

public class Cancion implements Comparable<Cancion> {
	/*
	 * titulo, de tipo String, consultable y modificable. • artista, de tipo String,
	 * consultable y modificable. Representa al intérprete de la canción. •
	 * duracion, de tipo Duration, consultable y modificable. • fechaLanzamiento, de
	 * tipo LocalDate, consultable y modificable. • genero, de tipo Genero,
	 * consultable y modificable. Puede tomar los valores: POP, ROCK, FOLK. •
	 * formatoCorto, de tipo String, consultable. Cadena que representa una canción
	 * con el siguiente formato: el título de la canción, seguido del artista entre
	 * paréntesis y la duración, por ejemplo, “Whole Lotta Love (Led Zeppelin) 3:20”
	 * 
	 * 1. Cancion Restricciones: 
	 * R0: Título, artista y duracion no pueden ser null.
	 * R1: el valor en segundos de la duración de una
	 * canción siempre es mayor o igual que cero. 2. Criterio de igualdad: dos
	 * canciones son iguales si lo son sus títulos y sus artistas. 3. Criterio de
	 * ordenación: las canciones se ordenan por artista y título.
	 */
	
	private static final String MENSAJE_DURACION = "Duración negativa no válida";

	public int hashCode() {
		return getArtista().hashCode() + getTitulo().hashCode() * 31;
	}

	public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof Cancion) {
			Cancion c = (Cancion) o;
			result = getArtista().equals(c.getArtista()) 
					&& getTitulo().equals(c.getTitulo());
		}
		return result;
	}

	public int compareTo(Cancion o) {
		int result = getArtista().compareTo(o.getArtista());
		if (result == 0) {
			result = getTitulo().compareTo(o.getTitulo());
		}
		return result;
	}

	private String titulo;
	private String artista;
	private Duration duracion;
	private LocalDate fechaLanzamiento;
	private Genero genero;

	/** Este es el comentario javadoc del constructor */
	public Cancion(String titulo, String artista) {
		this(titulo, artista, Duration.ZERO, LocalDate.MIN, Genero.NOT_VALID);
	}

	public Cancion(String titulo, String artista, Duration duracion, LocalDate fechaLanzamiento, Genero genero) {
		checkNoNull(titulo, artista, duracion);
		checkDuracion(duracion);
		
		this.titulo = titulo;
		this.artista = artista;
		this.duracion = duracion;
		this.fechaLanzamiento = fechaLanzamiento;
		this.genero = genero;
	}

	private void checkDuracion(Duration duracion) {
		if (duracion.toSeconds()<0) {
			throw new IllegalArgumentException(MENSAJE_DURACION);
		}
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		checkNoNull(titulo);
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		checkNoNull(artista);
		this.artista = artista;
	}

	public Duration getDuracion() {
		return duracion;
	}

	public void setDuracion(Duration duracion) {
		checkNoNull(duracion);
		checkDuracion(duracion);
		this.duracion = duracion;
	}

	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getFormatoCorto() {
		// “Whole Lotta Love (Led Zeppelin) 3:20”
		Long seconds = getDuracion().toSeconds();
		Long minutes = seconds / 60;
		return this.getTitulo() + " (" + getArtista() + ") " + minutes + ":" + (seconds % 60);
	}

	public String toString() {
		return "Cancion [titulo=" + titulo + ", artista=" + artista + ", duracion=" + duracion + ", fechaLanzamiento="
				+ fechaLanzamiento + ", genero=" + genero + "]";
	}

}
