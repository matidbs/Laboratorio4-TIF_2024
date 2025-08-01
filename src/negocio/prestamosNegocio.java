package negocio;

import java.util.ArrayList;

import entidad.Prestamo;

public interface prestamosNegocio {
	public ArrayList<Prestamo> listarPrestamos();
	public ArrayList<Prestamo> listarPrestamosFiltradosCuenta(int nroCuenta);
	public ArrayList<Prestamo> listarSolicitudes();
	public ArrayList<Prestamo> listarSolicitudesRechazadas();
	public boolean modificarEstado(Prestamo p);
	public ArrayList<Prestamo> listarPrestamosMonto(float superior, float inferior);
	public boolean aceptarPrestamo(Prestamo p);
	public boolean rechazarPrestamo(Prestamo p);
	public Prestamo agregarPrestamo(Prestamo p);
	public Prestamo listarPrestamoNumero(Prestamo p);
	public ArrayList<Prestamo> listarPrestamosDni(Prestamo p);
}
