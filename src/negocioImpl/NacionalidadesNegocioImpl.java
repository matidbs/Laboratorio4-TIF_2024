package negocioImpl;

import java.util.List;

import dao.nacionalidadesDao;
import daoImpl.nacionalidadesDaoImpl;
import entidad.Nacionalidad;
import negocio.NacionalidadesNegocio;

public class NacionalidadesNegocioImpl implements NacionalidadesNegocio{

	nacionalidadesDao nDao = new nacionalidadesDaoImpl();
	
	@Override
	public List<Nacionalidad> listar() {
		// TODO Auto-generated method stub
		return nDao.listar();
	}

	@Override
	public List<Nacionalidad> BuscarNacionalidadCompleta(int codNac) {
		return nDao.BuscarNacionalidadCompleta(codNac);
	}

}
