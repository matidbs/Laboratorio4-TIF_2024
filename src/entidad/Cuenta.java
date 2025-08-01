package entidad;

public class Cuenta {

	private int nroCuenta_CU;
	private String dniCliente_CU;
	private String fechaCreacion_CU;
	private tipoDeCuenta tipoCuenta_CU;
	private int CBU_CU;
	private float Saldo_CU;
	private boolean Estado_CU;
	
	public Cuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cuenta(int nroCuenta_CU, String dniCliente_CU, String fechaCreacion_CU, tipoDeCuenta tipoCuenta_CU, int cBU_CU,
			float saldo_CU, boolean estado_CU) {
		super();
		this.nroCuenta_CU = nroCuenta_CU;
		this.dniCliente_CU = dniCliente_CU;
		this.fechaCreacion_CU = fechaCreacion_CU;
		this.tipoCuenta_CU = tipoCuenta_CU;
		CBU_CU = cBU_CU;
		Saldo_CU = saldo_CU;
		Estado_CU = estado_CU;
	}

	public int getNroCuenta_CU() {
		return nroCuenta_CU;
	}

	public void setNroCuenta_CU(int nroCuenta_CU) {
		this.nroCuenta_CU = nroCuenta_CU;
	}

	public String getDniCliente_CU() {
		return dniCliente_CU;
	}

	public void setDniCliente_CU(String dniCliente_CU) {
		this.dniCliente_CU = dniCliente_CU;
	}

	public String getFechaCreacion_CU() {
		return fechaCreacion_CU;
	}

	public void setFechaCreacion_CU(String fechaCreacion_CU) {
		this.fechaCreacion_CU = fechaCreacion_CU;
	}

	public tipoDeCuenta getTipoCuenta_CU() {
		return tipoCuenta_CU;
	}

	public void setTipoCuenta_CU(tipoDeCuenta tipoCuenta_CU) {
		this.tipoCuenta_CU = tipoCuenta_CU;
	}

	public int getCBU_CU() {
		return CBU_CU;
	}

	public void setCBU_CU(int cBU_CU) {
		CBU_CU = cBU_CU;
	}

	public float getSaldo_CU() {
		return Saldo_CU;
	}

	public void setSaldo_CU(float saldo_CU) {
		Saldo_CU = saldo_CU;
	}

	public boolean isEstado_CU() {
		return Estado_CU;
	}

	public void setEstado_CU(boolean estado_CU) {
		Estado_CU = estado_CU;
	}

	@Override
	public String toString() {
		return "Cuenta [nroCuenta_CU=" + nroCuenta_CU + ", dniCliente_CU=" + dniCliente_CU + ", fechaCreacion_CU="
				+ fechaCreacion_CU + ", tipoCuenta_CU=" + tipoCuenta_CU.getCodTipoCuenta_Tcu() + ", CBU_CU=" + CBU_CU + ", Saldo_CU="
				+ Saldo_CU + ", Estado_CU=" + Estado_CU + "]";
	}	
}
