package fp.trenes;

import java.time.LocalTime;

public record Parada(String estacion, LocalTime horaLlegada, LocalTime horaSalida) {

}
