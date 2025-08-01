package entidad;

import exceptions.ValorMinimo;

public class DetallePrestamo {

	private int nroPrestamo_DP;
	private int nroCuota_DP;
	private float montoCuota_DP;
	private int nroCuentaPago_DP;
	private String fechaPagoCuota_DP;
	private boolean estado_DP;
	
	//no es propiedad de la tablas pero lo necesito para los inner joins de busqueda
	private String nombreCliente;
	private int nrocuentaSolicitante;
	
	public DetallePrestamo() {

	}

	public DetallePrestamo(int nroPrestamo_DP, int nroCuota_DP, float montoCuota_DP, int nroCuentaPago_DP,
			String fechaPagoCuota_DP,  boolean estado_DP) {
		super();
		this.nroPrestamo_DP = nroPrestamo_DP;
		this.nroCuota_DP = nroCuota_DP;
		this.montoCuota_DP = montoCuota_DP;
		this.nroCuentaPago_DP = nroCuentaPago_DP;
		this.fechaPagoCuota_DP = fechaPagoCuota_DP;
		this.estado_DP = estado_DP;
	}

	public int getNroPrestamo_DP() {
		return nroPrestamo_DP;
	}

	public void setNroPrestamo_DP(int nroPrestamo_DP) {
		this.nroPrestamo_DP = nroPrestamo_DP;
	}

	public int getNroCuota_DP() {
		return nroCuota_DP;
	}

	public void setNroCuota_DP(int nroCuota_DP) {
		this.nroCuota_DP = nroCuota_DP;
	}

	public float getMontoCuota_DP() {
		return montoCuota_DP;
	}

	public void setMontoCuota_DP(float montoCuota_DP) {
		this.montoCuota_DP = montoCuota_DP;
	}

	public int getNroCuentaPago_DP() {
		return nroCuentaPago_DP;
	}

	public void setNroCuentaPago_DP(int nroCuentaPago_DP) {
		this.nroCuentaPago_DP = nroCuentaPago_DP;
	}

	public String getFechaPagoCuota_DP() {
		return fechaPagoCuota_DP;
	}

	public void setFechaPagoCuota_DP(String fechaPagoCuota_DP) {
		this.fechaPagoCuota_DP = fechaPagoCuota_DP;
	}

	public boolean isEstado_DP() {
		return estado_DP;
	}

	public void setEstado_DP(boolean estado_DP) {
		this.estado_DP = estado_DP;
	}

	
	
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getNrocuentaSolicitante() {
		return nrocuentaSolicitante;
	}

	public void setNrocuentaSolicitante(int nrocuentaSolicitante) {
		this.nrocuentaSolicitante = nrocuentaSolicitante;
	}

	@Override
	public String toString() {
		return "DetallePrestamo [nroPrestamo_DP=" + nroPrestamo_DP + ", nroCuota_DP=" + nroCuota_DP + ", montoCuota_DP="
				+ montoCuota_DP + ", nroCuentaPago_DP=" + nroCuentaPago_DP + ", fechaPagoCuota_DP=" + fechaPagoCuota_DP
				+ ", estado_DP=" + estado_DP + ", nombreCliente=" + nombreCliente + "]";
	}

	public static boolean verificarValorMontos(float min, float max) throws ValorMinimo
	{
		if(min>max) throw new ValorMinimo();
		return true;		
	}
	
	
	
	
	
}
