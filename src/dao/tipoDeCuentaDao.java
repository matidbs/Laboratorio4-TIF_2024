package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.tipoDeCuenta;

public interface tipoDeCuentaDao {
	public ArrayList<tipoDeCuenta> listar();
	
	public List<tipoDeCuenta> BuscarTipoDeCuentaCompleta(int codTipoCuenta);
}
