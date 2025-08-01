package entidad;

public class Reporte {

	private int numeroDeCuenta;
	private String dniDelCliente;
	private String apellidoYNombre;
	private String provincia;
	private String localidad;
	private String fechaDeCreacion;
	private String tipoDeCuenta;
	private int cbu;
	private float saldo;
	private boolean estado;
	
	
	
	public Reporte() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Reporte(int numeroDeCuenta, String dniDelCliente, String apellidoYNombre, String fechaDeCreacion,
			String tipoDeCuenta, int cbu, float saldo, boolean estado) {
		super();
		this.numeroDeCuenta = numeroDeCuenta;
		this.dniDelCliente = dniDelCliente;
		this.apellidoYNombre = apellidoYNombre;
		this.fechaDeCreacion = fechaDeCreacion;
		this.tipoDeCuenta = tipoDeCuenta;
		this.cbu = cbu;
		this.saldo = saldo;
		this.estado = estado;
	}



	public int getNumeroDeCuenta() {
		return numeroDeCuenta;
	}



	public void setNumeroDeCuenta(int numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}



	public String getDniDelCliente() {
		return dniDelCliente;
	}



	public void setDniDelCliente(String dniDelCliente) {
		this.dniDelCliente = dniDelCliente;
	}



	public String getApellidoYNombre() {
		return apellidoYNombre;
	}



	public void setApellidoYNombre(String apellidoYNombre) {
		this.apellidoYNombre = apellidoYNombre;
	}



	public String getFechaDeCreacion() {
		return fechaDeCreacion;
	}



	public void setFechaDeCreacion(String fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}



	public String getTipoDeCuenta() {
		return tipoDeCuenta;
	}



	public void setTipoDeCuenta(String tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}



	public int getCbu() {
		return cbu;
	}



	public void setCbu(int cbu) {
		this.cbu = cbu;
	}



	public float getSaldo() {
		return saldo;
	}



	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}



	public boolean isEstado() {
		return estado;
	}



	public void setEstado(boolean estado) {
		this.estado = estado;
	}



	public String getProvincia() {
		return provincia;
	}



	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}



	public String getLocalidad() {
		return localidad;
	}



	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	
	
	
}
