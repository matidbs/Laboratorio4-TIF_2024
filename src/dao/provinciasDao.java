package dao;

import java.util.List;

import entidad.Provincia;

public interface provinciasDao {

	public List<Provincia> listar();
	
	public List<Provincia> BuscarProvinciaCompleta(int codProv);
}
