package entidad;

public class Transferencia {
	private int CuentaOrigen;
	private int cbuOrigen;
	private int CuentaDestino;
	private int cbuDestino;
	private float monto;
	private String detalle;
	
	
	
	public Transferencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transferencia(int cuentaOrigen, int cbuOrigen, int cuentaDestino, int cbuDestino, float monto,
			String detalle) {
		super();
		CuentaOrigen = cuentaOrigen;
		this.cbuOrigen = cbuOrigen;
		CuentaDestino = cuentaDestino;
		this.cbuDestino = cbuDestino;
		this.monto = monto;
		this.detalle = detalle;
	}

	public int getCuentaOrigen() {
		return CuentaOrigen;
	}

	public void setCuentaOrigen(int cuentaOrigen) {
		CuentaOrigen = cuentaOrigen;
	}

	public int getCbuOrigen() {
		return cbuOrigen;
	}

	public void setCbuOrigen(int cbuOrigen) {
		this.cbuOrigen = cbuOrigen;
	}

	public int getCuentaDestino() {
		return CuentaDestino;
	}

	public void setCuentaDestino(int cuentaDestino) {
		CuentaDestino = cuentaDestino;
	}

	public int getCbuDestino() {
		return cbuDestino;
	}

	public void setCbuDestino(int cbuDestino) {
		this.cbuDestino = cbuDestino;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Transferencia [CuentaOrigen=" + CuentaOrigen + ", cbuOrigen=" + cbuOrigen + ", CuentaDestino="
				+ CuentaDestino + ", cbuDestino=" + cbuDestino + ", monto=" + monto + ", detalle=" + detalle + "]";
	}
	
	
	
}
