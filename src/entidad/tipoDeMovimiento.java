package entidad;

public class tipoDeMovimiento {
	private int codTipoMovimiento_TM;
	private String descripcion_TM;
	
	public tipoDeMovimiento() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tipoDeMovimiento(int codTipoMovimiento_TM, String descripcion_TM) {
		super();
		this.codTipoMovimiento_TM = codTipoMovimiento_TM;
		this.descripcion_TM = descripcion_TM;
	}
	
	public int getCodTipoMovimiento_TM() {
		return codTipoMovimiento_TM;
	}
	public void setCodTipoMovimiento_TM(int codTipoMovimiento_TM) {
		this.codTipoMovimiento_TM = codTipoMovimiento_TM;
	}
	public String getDescripcion_TM() {
		return descripcion_TM;
	}
	public void setDescripcion_TM(String descripcion_TM) {
		this.descripcion_TM = descripcion_TM;
	}
	@Override
	public String toString() {
		return  descripcion_TM ;
	}
	
	
}
