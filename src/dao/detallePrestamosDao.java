package dao;

import java.util.ArrayList;

import entidad.DetallePrestamo;

public interface detallePrestamosDao {

	public ArrayList<DetallePrestamo> listaCuotas();
	public ArrayList<DetallePrestamo> listaCuotas_DNI(String dni);
	public ArrayList<DetallePrestamo> listaCuotas_NOMBREyAPELLIDO(DetallePrestamo detallePrestamo);
	public ArrayList<DetallePrestamo> listaCuotas_MONTOCUOTA(float min, float max);
	public ArrayList<DetallePrestamo> listaCuotas_FECHA(String min, String max);
	
	public ArrayList<DetallePrestamo> listaCuotas_DniLadoCliente(String dni);
	public boolean pagarCuotaPrestamo(DetallePrestamo detallePrestamo);
}
