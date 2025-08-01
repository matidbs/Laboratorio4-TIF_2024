package entidad;

public class Nacionalidad {
	private int codNacionalidad_N;
	private String descripcion_N;
	
	public Nacionalidad() {
		
	}
	
	public Nacionalidad(int codNacionalidad_N, String descripcion_N) {
		super();
		this.codNacionalidad_N = codNacionalidad_N;
		this.descripcion_N = descripcion_N;
	}

	public int getCodNacionalidad_N() {
		return codNacionalidad_N;
	}

	public void setCodNacionalidad_N(int codNacionalidad_N) {
		this.codNacionalidad_N = codNacionalidad_N;
	}

	public String getDescripcion_N() {
		return descripcion_N;
	}

	public void setDescripcion_N(String descripcion_N) {
		this.descripcion_N = descripcion_N;
	}

	@Override
	public String toString() {
		return descripcion_N;
	}
	
	
	
	
}
