package entidad;

public class Provincia {
	private int codProv_P;
	private String descripcion_P;
	
	public Provincia() {
	
	}
	
	public Provincia(int codProv_P, String descripcion_P) {
		super();
		this.codProv_P = codProv_P;
		this.descripcion_P = descripcion_P;
	}

	public int getCodProv_P() {
		return codProv_P;
	}

	public void setCodProv_P(int codProv_P) {
		this.codProv_P = codProv_P;
	}

	public String getDescripcion_P() {
		return descripcion_P;
	}

	public void setDescripcion_P(String descripcion_P) {
		this.descripcion_P = descripcion_P;
	}

	@Override
	public String toString() {
		return descripcion_P;
	}
	
	
	
	

}
