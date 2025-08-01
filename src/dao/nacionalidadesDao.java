package dao;

import java.util.List;

import entidad.Nacionalidad;


public interface nacionalidadesDao {
	public List<Nacionalidad> listar();
	
	public List<Nacionalidad> BuscarNacionalidadCompleta(int codNac);
}
