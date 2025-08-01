package entidad;

public class Movimiento {
	private int nroMovimiento_M;
	private Cuenta Cuenta_M;
	private tipoDeMovimiento TipoMovimiento_M;
	private String detalle_M;
	private float importe_M;
	private String fecha_M;
	
	public Movimiento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movimiento(int nroMovimiento_M, Cuenta Cuenta_M, tipoDeMovimiento TipoMovimiento_M, String detalle_M, float importe_M,
			String fecha_M) {
		super();
		this.nroMovimiento_M = nroMovimiento_M;
		this.Cuenta_M = Cuenta_M;
		this.TipoMovimiento_M = TipoMovimiento_M;
		this.detalle_M = detalle_M;
		this.importe_M = importe_M;
		this.fecha_M = fecha_M;
	}

	public int getNroMovimiento_M() {
		return nroMovimiento_M;
	}

	public void setNroMovimiento_M(int nroMovimiento_M) {
		this.nroMovimiento_M = nroMovimiento_M;
	}

	public Cuenta getCuenta_M() {
		return Cuenta_M;
	}

	public void setCuenta_M(Cuenta nroCuenta_M) {
		this.Cuenta_M = nroCuenta_M;
	}

	public tipoDeMovimiento getTipoMovimiento_M() {
		return TipoMovimiento_M;
	}

	public void setTipoMovimiento_M(tipoDeMovimiento codTipoMovimiento_M) {
		this.TipoMovimiento_M = codTipoMovimiento_M;
	}

	public String getDetalle_M() {
		return detalle_M;
	}

	public void setDetalle_M(String detalle_M) {
		this.detalle_M = detalle_M;
	}

	public float getImporte_M() {
		return importe_M;
	}

	public void setImporte_M(float importe_M) {
		this.importe_M = importe_M;
	}

	public String getFecha_M() {
		return fecha_M;
	}

	public void setFecha_M(String fecha_M) {
		this.fecha_M = fecha_M;
	}

	@Override
	public String toString() {
		return "Movimiento [nroMovimiento_M=" + nroMovimiento_M + ", nroCuenta_M=" + Cuenta_M.getNroCuenta_CU()
				+ ", codTipoMovimiento_M=" + TipoMovimiento_M.getCodTipoMovimiento_TM() + ", detalle_M=" + detalle_M + ", importe_M="
				+ importe_M + ", fecha_M=" + fecha_M + "]";
	}
	
}
