package Excepciones;

public class CampoNuloException extends NullPointerException {
	public CampoNuloException() {}

	@Override
	public String getMessage() {
		return "La base de datos devolvió un campo nulo";
	}
}
