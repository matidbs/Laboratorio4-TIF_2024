package dao;

import java.util.List;

import entidad.Localidad;

public interface localidadesDao {
	public List<Localidad> listar();
	
	public List<Localidad> listarPorProvincia(int codProv);
	
	public List<Localidad> BuscarLocalidadCompleta(int codLoc);
}
