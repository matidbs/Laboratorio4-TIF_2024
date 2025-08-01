package negocioImpl;

import java.util.ArrayList;

import dao.prestamosDao;
import daoImpl.prestamosDaoImpl;
import entidad.Prestamo;
import negocio.prestamosNegocio;

public class prestamosNegocioImpl implements prestamosNegocio {
	prestamosDao pDao = new prestamosDaoImpl();
	
	@Override
	public ArrayList<Prestamo> listarPrestamos() {
		return pDao.listarPrestamos();
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosFiltradosCuenta(int nroCuenta) {
		return pDao.listarPrestamosCuenta(nroCuenta);
	}

	@Override
	public ArrayList<Prestamo> listarSolicitudes() {
		return pDao.listarSolicitudes();
	}
	
	@Override
	public ArrayList<Prestamo> listarSolicitudesRechazadas() {
		return pDao.listarSolicitudesRechazadas();
	}

	@Override
	public boolean modificarEstado(Prestamo p) {
		return pDao.modificarEstado(p);
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosMonto(float superior,
			float inferior) {
		return pDao.listarPrestamosMonto(superior, inferior);
	}

	@Override
	public boolean aceptarPrestamo(Prestamo p) {
		return pDao.actualizarPrestamo(p);
	}

	@Override
	public boolean rechazarPrestamo(Prestamo p) {
		return pDao.rechazarPrestamo(p);
	}

	@Override
	public Prestamo agregarPrestamo(Prestamo p) {
		return pDao.agregarPrestamo(p);
	}

	@Override
	public Prestamo listarPrestamoNumero(Prestamo p) {
		return pDao.listarPrestamoNumero(p);
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosDni(Prestamo p) {
		return pDao.listarPrestamosDni(p);
	}

	
}
