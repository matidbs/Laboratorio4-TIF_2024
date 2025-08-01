package exceptions;

import java.io.IOException;

public class ValorMinimo extends IOException{

	@Override
	public String getMessage() {
		return "El valor minimo no puede ser mayor al valor maximo";
	}

}
