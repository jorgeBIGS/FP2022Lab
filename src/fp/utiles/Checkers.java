package fp.utiles;

public class Checkers {

	private static final String MENSAJE_NULO = "Parámetro null no soportado";

	public static void checkNotNull(Object... array) {
		for (Object o : array) {
			if (o == null) {
				throw new IllegalArgumentException(MENSAJE_NULO);
			}
		}
	}

	public static void checkCondicion(Boolean condicion) {
		if(condicion) {
			throw new IllegalArgumentException();
		}
		
	}

}
