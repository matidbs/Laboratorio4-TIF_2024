package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import dao.tipoDeCuentaDao;
import daoImpl.tipoDeCuentaDaoImpl;
import entidad.tipoDeCuenta;
import negocio.tipoDeCuentaNegocio;

public class tipoDeCuentaNegocioImpl implements tipoDeCuentaNegocio {

	private tipoDeCuentaDao tcDao = new tipoDeCuentaDaoImpl();
	
	@Override
	public ArrayList<tipoDeCuenta> listar() {
		return tcDao.listar();
	}

	@Override
	public List<tipoDeCuenta> BuscarTipoDeCuentaCompleta(int codTipoCuenta) {
		return tcDao.BuscarTipoDeCuentaCompleta(codTipoCuenta);
	}
	
}
