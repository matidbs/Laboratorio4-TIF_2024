package negocioImpl;

import java.util.ArrayList;

import dao.tipoDeMovimientoDao;
import daoImpl.tipoDeMovimientoDaoImpl;
import entidad.tipoDeMovimiento;
import negocio.tipoDeMovimientoNegocio;

public class tipoDeMovimientoNegocioImpl implements tipoDeMovimientoNegocio{

	tipoDeMovimientoDao dao = new tipoDeMovimientoDaoImpl();
	@Override
	public ArrayList<tipoDeMovimiento> listar() {
		return dao.listar();
	}

}
