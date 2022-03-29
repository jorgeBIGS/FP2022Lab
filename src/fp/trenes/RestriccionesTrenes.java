package fp.trenes;

public class RestriccionesTrenes {
//	R1: el c�digo de un tren debe estar formado por 5 d�gitos.
//	� R2: la hora de salida de la primera estaci�n no puede ser nula.
//	� R3: la hora de llegada a la �ltima estaci�n no puede ser nula.
//	� R4: la hora de salida de la primera estaci�n debe ser anterior a la hora de llegada a la �ltima estaci�n.


	public static Boolean esCodigoValido(String codigo) {
		
		Boolean res = codigo.length() == 5 
				&& Character.isDigit(codigo.charAt(0))
				&& Character.isDigit(codigo.charAt(1))
				&& Character.isDigit(codigo.charAt(2))
				&& Character.isDigit(codigo.charAt(3))
				&& Character.isDigit(codigo.charAt(4));
		
		return res;
	}


}
