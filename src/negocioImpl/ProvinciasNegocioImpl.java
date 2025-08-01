package negocioImpl;

import java.util.List;

import dao.provinciasDao;
import daoImpl.provinciasDaoImpl;
import entidad.Localidad;
import entidad.Provincia;
import negocio.ProvinciasNegocio;

public class ProvinciasNegocioImpl implements ProvinciasNegocio{

	provinciasDao pdao = new provinciasDaoImpl(); 
	
	@Override
	public List<Provincia> listar() {
		return pdao.listar();
	}

	@Override
	public List<Provincia> BuscarProvinciaCompleta(int codProv) {
		return pdao.BuscarProvinciaCompleta(codProv);
	}

}
