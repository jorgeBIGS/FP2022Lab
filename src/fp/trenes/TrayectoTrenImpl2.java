package fp.trenes;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class TrayectoTrenImpl2 implements Comparable<TrayectoTren>, TrayectoTren{

	
	
	private String codigoTren;
	private String nombreTrayecto;
	private TipoTren tipoTren;
	private List<Parada> paradas;
	
	public TrayectoTrenImpl2(String codigoTren, 
			String nombreTrayecto, TipoTren tipoTren, 
			String origen,
			String llegada, LocalTime horaSalida, 
			LocalTime horaLlegada) {
		
		Checkers.checkCondicion(!RestriccionesTrenes
				.esCodigoValido(codigoTren));
		Checkers.checkNotNull(horaSalida, horaLlegada);
		Checkers.checkCondicion(!horaSalida.isBefore(horaLlegada));
		
		this.codigoTren = codigoTren;
		this.nombreTrayecto = nombreTrayecto;
		this.tipoTren = tipoTren;
		
		this.paradas = new ArrayList<>();
		
		Parada inicio = new Parada(origen, null, horaSalida);
		Parada fin = new Parada(llegada, horaLlegada, null);
		
		this.paradas.add(inicio);
		this.paradas.add(fin);

	}
	
	public String getCodigoTren() {
		return codigoTren;
	}
	
	public String getNombreTrayecto() {
		return nombreTrayecto;
	}
	
	public TipoTren getTipoTren() {
		return tipoTren;
	}
	
	public List<String> getEstaciones() {
		List<String> result = new ArrayList<>();
		for(Parada p: paradas) {
			result.add(p.estacion());
		}
		return result;
	}
	
	public List<LocalTime> getHorasSalida() {
		List<LocalTime> result = new ArrayList<>();
		for(Parada p: paradas) {
			result.add(p.horaSalida());
		}
		return result;
	}
	
	public List<LocalTime> getHorasLlegada() {
		List<LocalTime> result = new ArrayList<>();
		for(Parada p: paradas) {
			result.add(p.horaLlegada());
		}
		return result;
	}
	
	
	public LocalTime getHoraSalida() {
		return paradas.get(0).horaSalida();
	}
	
	public LocalTime getHoraLlegada() {
		return paradas.get(paradas.size()-1).horaLlegada();
	}
	
	public Duration getDuracionTrayecto() {
		return Duration.between(getHoraSalida(), getHoraLlegada());
	}

	public LocalTime getHoraSalida(String estacion) {
		int indice = getEstaciones().indexOf(estacion);
		LocalTime res = null;
		
		if(!(indice == -1 || indice == paradas.size()-1)) {
			res = paradas.get(indice).horaSalida();
		}
		
		return res;
	}
	
	public LocalTime getHoraLlegada(String estacion) {
		int indice = getEstaciones().indexOf(estacion);
		LocalTime res = null;
		
		if(!(indice == -1 || indice == 0)) {
			res = paradas.get(indice).horaLlegada();
		}
		
		return res;
	}
	
	public void anadirEstacionIntermedia(int posicion, 
			String estacion, LocalTime horaLlegada, 
			LocalTime horaSalida) {

		Checkers.checkCondicion(!(posicion >= 1 && posicion < paradas.size()));
		Checkers.checkCondicion(horaSalida.isBefore(horaLlegada));
		
		LocalTime horaSalidaAnterior = paradas.get(posicion-1).horaSalida();
		Checkers.checkCondicion(horaLlegada.isBefore(horaSalidaAnterior));
		
		LocalTime horaLlegadaSiguiente = paradas.get(posicion).horaLlegada();
		Checkers.checkCondicion(horaSalida.isAfter(horaLlegadaSiguiente));
		
		paradas.add(posicion,new Parada(estacion, horaLlegada, horaSalida));
	}

	public void eliminarEstacionIntermedia(String estacion) {
		
		int indice = getEstaciones().indexOf(estacion);
		Checkers.checkCondicion(indice == 0 || indice == paradas.size()-1);
		
		paradas.remove(indice);
	}
	
	
	public String toString() {
		
		String mensaje = "";
		
		for(int i =0; i < paradas.size(); i++) {
			Parada p = paradas.get(i);
			if(i == 0) {
				
				mensaje += p.estacion() + "\t" + " " + "\t"+
						p.horaSalida() + "\n";
			}
			else if(i == paradas.size()-1) {
				
				mensaje += p.estacion() + "\t" + p.horaLlegada() + "\t"+
						" " + "\n";
			}
			else {
				mensaje += p.estacion() + "\t" + p.horaLlegada() + "\t"+
						p.horaSalida() + "\n";
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
