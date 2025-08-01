package negocio;

import java.util.List;

import entidad.Provincia;

public interface ProvinciasNegocio {
	public List<Provincia> listar();
	public List<Provincia> BuscarProvinciaCompleta(int codProv);
}
