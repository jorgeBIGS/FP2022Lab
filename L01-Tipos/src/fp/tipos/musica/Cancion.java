package fp.tipos.musica;

import java.time.Duration;
import java.time.LocalDate;

public class Cancion {
	/*
	 * titulo, de tipo String, consultable y modificable. • artista, de tipo String,
	 * consultable y modificable. Representa al intérprete de la canción. •
	 * duracion, de tipo Duration, consultable y modificable. • fechaLanzamiento, de
	 * tipo LocalDate, consultable y modificable. • genero, de tipo Genero,
	 * consultable y modificable. Puede tomar los valores: POP, ROCK, FOLK. •
	 * formatoCorto, de tipo String, consultable. Cadena que representa una canción
	 * con el siguiente formato: el título de la canción, seguido del artista entre
	 * paréntesis y la duración, por ejemplo, “Whole Lotta Love (Led Zeppelin) 3:20”
	 */
	private String titulo;
	private String artista;
	private Duration duracion;
	private LocalDate fechaLanzamiento;
	private Genero genero;

	public Cancion(String titulo, String artista) {
		this(titulo, artista, Duration.ZERO, LocalDate.MIN, Genero.NOT_VALID);
	}

	public Cancion(String titulo, String artista, Duration duracion, LocalDate fechaLanzamiento, Genero genero) {
		this.titulo = titulo;
		this.artista = artista;
		this.duracion = duracion;
		this.fechaLanzamiento = fechaLanzamiento;
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public Duration getDuracion() {
		return duracion;
	}

	public void setDuracion(Duration duracion) {
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
		return this.getTitulo() 
				+ " (" + getArtista() + ") " 
				+ minutes + ":" + (seconds%60);
	}

	public String toString() {
		return "Cancion [titulo=" + titulo + ", artista=" + artista + ", duracion=" + duracion + ", fechaLanzamiento="
				+ fechaLanzamiento + ", genero=" + genero + "]";
	}

}
