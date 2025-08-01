package entidad;

public class tipoDeCuenta {
	private int codTipoCuenta_Tcu;
	private String descripcion_Tcu;	
	
	public tipoDeCuenta() {
	
	}

	public tipoDeCuenta(int codTipoCuenta_Tcu, String descripcion_Tcu) {
		super();
		this.codTipoCuenta_Tcu = codTipoCuenta_Tcu;
		this.descripcion_Tcu = descripcion_Tcu;
	}

	public int getCodTipoCuenta_Tcu() {
		return codTipoCuenta_Tcu;
	}

	public void setCodTipoCuenta_Tcu(int codTipoCuenta_Tcu) {
		this.codTipoCuenta_Tcu = codTipoCuenta_Tcu;
	}

	public String getDescripcion_Tcu() {
		return descripcion_Tcu;
	}

	public void setDescripcion_Tcu(String descripcion_Tcu) {
		this.descripcion_Tcu = descripcion_Tcu;
	}

	@Override
	public String toString() {
		return descripcion_Tcu;
	}
	
	
	
	
	
	

}
