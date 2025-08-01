package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.tipoDeCuenta;

public interface tipoDeCuentaNegocio {
	public ArrayList<tipoDeCuenta> listar();
	public List<tipoDeCuenta> BuscarTipoDeCuentaCompleta(int codTipoCuenta);
}
