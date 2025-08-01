package negocio;

import java.util.List;

import entidad.Localidad;


public interface LocalidadesNegocio {
	public List<Localidad> listar();
	public List<Localidad> listarPorProvincia(int codProv);
	public List<Localidad> BuscarLocalidadCompleta(int codLoc);

}
