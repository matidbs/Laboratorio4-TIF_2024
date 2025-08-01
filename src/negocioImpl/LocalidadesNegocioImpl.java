package negocioImpl;

import java.util.List;

import dao.localidadesDao;
import daoImpl.localidadesDaoImpl;
import entidad.Localidad;
import negocio.LocalidadesNegocio;

public class LocalidadesNegocioImpl implements LocalidadesNegocio{

	localidadesDao ldao = new localidadesDaoImpl();
	
	@Override
	public List<Localidad> listar() {
		// TODO Auto-generated method stub
		return ldao.listar();
	}

	@Override
	public List<Localidad> listarPorProvincia(int codProv) {
		return ldao.listarPorProvincia(codProv);
	}

	@Override
	public List<Localidad> BuscarLocalidadCompleta(int codLoc) {
		return ldao.BuscarLocalidadCompleta(codLoc);
			
		
	}

}
