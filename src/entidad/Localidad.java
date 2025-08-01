package entidad;

public class Localidad {
	private int codLoc_L;
	private int codProv_L;
	private String descripcion_L;
	
	public Localidad() {
	}
	
	public Localidad(int codLoc_L, int codProv_L, String descripcion_L) {
		super();
		this.codLoc_L = codLoc_L;
		this.codProv_L = codProv_L;
		this.descripcion_L = descripcion_L;
	}

	public int getCodLoc_L() {
		return codLoc_L;
	}

	public void setCodLoc_L(int codLoc_L) {
		this.codLoc_L = codLoc_L;
	}

	public int getCodProv_L() {
		return codProv_L;
	}

	public void setCodProv_L(int codProv_L) {
		this.codProv_L = codProv_L;
	}

	public String getDescripcion_L() {
		return descripcion_L;
	}

	public void setDescripcion_L(String descripcion_L) {
		this.descripcion_L = descripcion_L;
	}

	@Override
	public String toString() {
		return  descripcion_L;
	}
	
	
	
	
}
