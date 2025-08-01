package negocioImpl;

import java.util.ArrayList;

import dao.detallePrestamosDao;
import daoImpl.detallePrestamosDaoImpl;
import entidad.DetallePrestamo;
import negocio.detallePrestamosNegocio;

public class detallePrestamosNegocioImpl implements detallePrestamosNegocio {

	detallePrestamosDao dao = new detallePrestamosDaoImpl();
	
	@Override
	public ArrayList<DetallePrestamo> listaCuotas() {
		return (ArrayList<DetallePrestamo>) dao.listaCuotas();
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas_DNI(String dni) {
		return dao.listaCuotas_DNI(dni);
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas_NOMBREyAPELLIDO(DetallePrestamo detallePrestamo) {
		return dao.listaCuotas_NOMBREyAPELLIDO(detallePrestamo);
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas_MONTOCUOTA(float min, float max) {
		return dao.listaCuotas_MONTOCUOTA(min, max);
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas_FECHA(String min, String max) {
		return dao.listaCuotas_FECHA(min, max);
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas_DniLadoCliente(String dni) {
		return dao.listaCuotas_DniLadoCliente(dni);
	}

	@Override
	public boolean pagarCuotaPrestamo(DetallePrestamo detallePrestamo) {
		return dao.pagarCuotaPrestamo(detallePrestamo);
	}

	

	
}
