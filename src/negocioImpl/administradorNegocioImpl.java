package negocioImpl;

import dao.administradorDao;
import daoImpl.administradorDaoImpl;
import entidad.Usuario;
import negocio.administradorNegocio;

public class administradorNegocioImpl implements administradorNegocio{

	administradorDao dao = new administradorDaoImpl();
	@Override
	public boolean existeAdministradorUsuario(Usuario usuario) {
		return dao.existeAdministradorUsuario(usuario);
	}

}
