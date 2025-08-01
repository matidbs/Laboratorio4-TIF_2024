package negocio;

import java.util.List;

import entidad.Nacionalidad;

public interface NacionalidadesNegocio {
	public List<Nacionalidad> listar();
	public List<Nacionalidad> BuscarNacionalidadCompleta(int codNac);
}
