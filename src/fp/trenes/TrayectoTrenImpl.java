package fp.trenes;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class TrayectoTrenImpl implements Comparable<TrayectoTren>, TrayectoTren{

	
	
	private String codigoTren;
	private String nombreTrayecto;
	private TipoTren tipoTren;
	private List<String> estaciones;
	private List<LocalTime> horasSalida;
	private List<LocalTime> horasLlegada;
	
	@Override
	public String getCodigoTren() {
		return codigoTren;
	}
	@Override
	public String getNombreTrayecto() {
		return nombreTrayecto;
	}
	@Override
	public TipoTren getTipoTren() {
		return tipoTren;
	}
	@Override
	public List<String> getEstaciones() {
		return estaciones;
	}
	@Override
	public List<LocalTime> getHorasSalida() {
		return horasSalida;
	}
	@Override
	public List<LocalTime> getHorasLlegada() {
		return horasLlegada;
	}
//	
	public TrayectoTrenImpl(String codigoTren, 
			String nombreTrayecto, TipoTren tipoTren, 
			String origen,
			String llegada, LocalTime horaSalida, 
			LocalTime horaLlegada) {
		
		Checkers.checkCondicion(!RestriccionesTrenes
				.esCodigoValido(codigoTren));
		Checkers.checkNoNull(horaSalida, horaLlegada);
		Checkers.checkCondicion(!horaSalida.isBefore(horaLlegada));
		
		this.codigoTren = codigoTren;
		this.nombreTrayecto = nombreTrayecto;
		this.tipoTren = tipoTren;
		
		this.estaciones = new ArrayList<>();
		this.horasSalida = new ArrayList<>();
		this.horasLlegada = new ArrayList<>();
		
		this.estaciones.add(origen);
		this.estaciones.add(llegada);
		
		this.horasSalida.add(horaSalida);
		this.horasSalida.add(null);
		
		this.horasLlegada.add(null);
		this.horasLlegada.add(horaLlegada);

	}

	@Override
	public LocalTime getHoraSalida() {
		return horasSalida.get(0);
	}
	
	@Override
	public LocalTime getHoraLlegada() {
		return horasLlegada.get(horasLlegada.size()-1);
	}
	
	@Override
	public Duration getDuracionTrayecto() {
		return Duration.between(getHoraSalida(), getHoraLlegada());
	}



	
	@Override
	public LocalTime getHoraSalida(String estacion) {
		int indice = estaciones.indexOf(estacion);
		LocalTime res = null;
		
		if(!(indice == -1 || indice == estaciones.size()-1)) {
			res = horasSalida.get(indice);
		}
		
		return res;
	}
	
	@Override
	public LocalTime getHoraLlegada(String estacion) {
		int indice = estaciones.indexOf(estacion);
		LocalTime res = null;
		
		if(!(indice == -1 || indice == 0)) {
			res = horasLlegada.get(indice);
		}
		
		return res;
	}
	
	@Override
	public void anadirEstacionIntermedia(int posicion, 
			String estacion, LocalTime horaLlegada, 
			LocalTime horaSalida) {

		Checkers.checkCondicion(!(posicion >= 1 && posicion < estaciones.size()));
		Checkers.checkCondicion(horaSalida.isBefore(horaLlegada));
		
		LocalTime horaSalidaAnterior = horasSalida.get(posicion-1);
		Checkers.checkCondicion(horaLlegada.isBefore(horaSalidaAnterior));
		
		LocalTime horaLlegadaSiguiente = horasLlegada.get(posicion);
		Checkers.checkCondicion(horaSalida.isAfter(horaLlegadaSiguiente));
		
		estaciones.add(posicion,estacion);
		horasLlegada.add(posicion, horaLlegada);
		horasSalida.add(posicion, horaSalida);			
	}
//	• void eliminarEstacionIntermedia(String estacion): elimina del trayecto la 
//	estación dada como 
//	parámetro. Eleva IllegalArgumentException si la estación a 
//	eliminar es la primera o la última del trayecto 
//	o si no está en el trayecto
	
	@Override
	public void eliminarEstacionIntermedia(String estacion) {
		
		int indice = estaciones.indexOf(estacion);
		Checkers.checkCondicion(indice == 0 || indice == estaciones.size()-1);
		
		estaciones.remove(indice);
		horasSalida.remove(indice);
		horasLlegada.remove(indice);
	}
	
////	 el nombre del trayecto, seguido de un guion, el tipo de tren,
////	seguido del código 
////	 del tren entre paréntesis, y después, por cada estación, 
//	el nombre de la estación, un tabulador, la hora de 
////	 llegada a la estación, un tabulador y la hora de salida; 
//	para la de origen la hora de llegada se dejará en blanco, 
////	 así como la hora de salida en la de destino. Por ejemplo:
	
	public String toString() {
		
		String mensaje = "";
		
		for(int i =0; i <estaciones.size(); i++) {
			if(i == 0) {
				
				mensaje += estaciones.get(i) + "\t" + " " + "\t"+
						horasSalida.get(i) + "\n";
			}
			else if(i == estaciones.size()-1) {
				
				mensaje += estaciones.get(i) + "\t" + horasLlegada.get(i) + "\t"+
						" " + "\n";
			}
			else {
				mensaje += estaciones.get(i) + "\t" + horasLlegada.get(i) + "\t"+
						horasSalida.get(i) + "\n";
			}
		}
		
		return nombreTrayecto + "-" + tipoTren + "(" + codigoTren + ") \n"+ mensaje;
	}
	
	public int hashCode() {
		return Objects.hash(codigoTren, getHoraSalida(), tipoTren);
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof TrayectoTren) {
			TrayectoTren other = (TrayectoTren) obj;
			result = Objects.equals(codigoTren, other.getCodigoTren()) && Objects.equals(getHoraSalida(), other.getHoraSalida())
					&& tipoTren == other.getTipoTren();
		}
		return result;
	}
	
	public int compareTo(TrayectoTren o) {

		 int res = nombreTrayecto.compareTo(o.getNombreTrayecto());
		 if(res == 0) {
			 res = getHoraSalida().compareTo(o.getHoraSalida());
		 }
		 if(res == 0) {
			 res = codigoTren.compareTo(o.getCodigoTren());
		 }
		 
		 return res;
	}
	
}
