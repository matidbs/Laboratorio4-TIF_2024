package entidad;



public class Prestamo {

	private int nroPrestamo_P;
	private String dniCliente_P;
	private int nroCuentaSolicitante_P;
	private int cantidadCuotas_P;
	private int cuotasPagas_P;
	private float montoTotalAPagar_P;
	private float montoSolicitado_P;
	private boolean estado_P;	
	private String fechaAltaPrestamo_P;
	private String fechaFinalizacion_P;
	
	public Prestamo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Prestamo(int nroPrestamo_P, String dniCliente_P, int nroCuentaSolicitante_P, int cantidadCuotas_P,
			int cuotasPagas_P, float montoTotalAPagar_P, float montoSolicitado_P, boolean estado_P,
			String fechaAltaPrestamo_P, String fechaFinalizacion_P) {
		super();
		this.nroPrestamo_P = nroPrestamo_P;
		this.dniCliente_P = dniCliente_P;
		this.nroCuentaSolicitante_P = nroCuentaSolicitante_P;
		this.cantidadCuotas_P = cantidadCuotas_P;
		this.cuotasPagas_P = cuotasPagas_P;
		this.montoTotalAPagar_P = montoTotalAPagar_P;
		this.montoSolicitado_P = montoSolicitado_P;
		this.estado_P = estado_P;
		this.fechaAltaPrestamo_P = fechaAltaPrestamo_P;
		this.fechaFinalizacion_P = fechaFinalizacion_P;
	}
	public int getNroPrestamo_P() {
		return nroPrestamo_P;
	}
	public void setNroPrestamo_P(int nroPrestamo_P) {
		this.nroPrestamo_P = nroPrestamo_P;
	}
	public String getDniCliente_P() {
		return dniCliente_P;
	}
	public void setDniCliente_P(String dniCliente_P) {
		this.dniCliente_P = dniCliente_P;
	}
	public int getNroCuentaSolicitante_P() {
		return nroCuentaSolicitante_P;
	}
	public void setNroCuentaSolicitante_P(int nroCuentaSolicitante_P) {
		this.nroCuentaSolicitante_P = nroCuentaSolicitante_P;
	}
	public int getCantidadCuotas_P() {
		return cantidadCuotas_P;
	}
	public void setCantidadCuotas_P(int cantidadCuotas_P) {
		this.cantidadCuotas_P = cantidadCuotas_P;
	}
	public int getCuotasPagas_P() {
		return cuotasPagas_P;
	}
	public void setCuotasPagas_P(int cuotasPagas_P) {
		this.cuotasPagas_P = cuotasPagas_P;
	}
	public float getMontoTotalAPagar_P() {
		return montoTotalAPagar_P;
	}
	public void setMontoTotalAPagar_P(float montoTotalAPagar_P) {
		this.montoTotalAPagar_P = montoTotalAPagar_P;
	}
	public float getMontoSolicitado_P() {
		return montoSolicitado_P;
	}
	public void setMontoSolicitado_P(float montoSolicitado_P) {
		this.montoSolicitado_P = montoSolicitado_P;
	}
	public boolean isEstado_P() {
		return estado_P;
	}
	public void setEstado_P(boolean estado_P) {
		this.estado_P = estado_P;
	}
	public String getFechaAltaPrestamo_P() {
		return fechaAltaPrestamo_P;
	}
	public void setFechaAltaPrestamo_P(String fechaAltaPrestamo_P) {
		this.fechaAltaPrestamo_P = fechaAltaPrestamo_P;
	}
	public String getFechaFinalizacion_P() {
		return fechaFinalizacion_P;
	}
	public void setFechaFinalizacion_P(String fechaFinalizacion_P) {
		this.fechaFinalizacion_P = fechaFinalizacion_P;
	}
	
	@Override
	public String toString() {
		return "Prestamo [nroPrestamo_P=" + nroPrestamo_P + ", dniCliente_P=" + dniCliente_P
				+ ", nroCuentaSolicitante_P=" + nroCuentaSolicitante_P + ", cantidadCuotas_P=" + cantidadCuotas_P
				+ ", cuotasPagas_P=" + cuotasPagas_P + ", montoTotalAPagar_P=" + montoTotalAPagar_P
				+ ", montoSolicitado_P=" + montoSolicitado_P + ", estado_P=" + estado_P + ", fechaAltaPrestamo_P="
				+ fechaAltaPrestamo_P + ", fechaFinalizacion_P=" + fechaFinalizacion_P + "]";
	}
	
	
}
