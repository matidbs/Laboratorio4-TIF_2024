package exceptions;

import java.io.IOException;

public class ValorNull extends IOException {
	public ValorNull() {}

	@Override
	public String getMessage() {
		return "La base de datos devolvi� un valor null";
	}
}
