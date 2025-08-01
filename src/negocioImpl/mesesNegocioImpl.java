package negocioImpl;

import java.util.List;

import dao.mesesDao;
import daoImpl.mesesDaoImpl;
import entidad.Mes;
import negocio.mesesNegocio;

public class mesesNegocioImpl implements mesesNegocio{

	mesesDao dao = new mesesDaoImpl();
	
	@Override
	public List<Mes> listar() {
		return dao.listar();
	}

}
