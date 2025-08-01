package entidad;

public class Mes {
	private int codMes_M;
	private String descripcion_M;
	
	public Mes() {
	
	}
	
	public Mes(int codMes_M, String descripcion_M) {
		super();
		this.codMes_M = codMes_M;
		this.descripcion_M = descripcion_M;
	}

	public int getCodMes_M() {
		return codMes_M;
	}

	public void setCodMes_M(int codMes_M) {
		this.codMes_M = codMes_M;
	}

	public String getDescripcion_M() {
		return descripcion_M;
	}

	public void setDescripcion_M(String descripcion_M) {
		this.descripcion_M = descripcion_M;
	}

	@Override
	public String toString() {
		return descripcion_M;
	} 
	
	
	
}
