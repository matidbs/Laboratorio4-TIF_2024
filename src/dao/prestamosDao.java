package dao;

import java.util.ArrayList;

import entidad.Prestamo;

public interface prestamosDao {
	public ArrayList<Prestamo> listarPrestamos();
	public ArrayList<Prestamo> listarPrestamosDni(Prestamo prestamo);
	public ArrayList<Prestamo> listarPrestamosCuenta(int nroCuenta);
	public Prestamo listarPrestamoNumero (Prestamo p);
	public Prestamo agregarPrestamo(Prestamo p);
	public boolean modificarEstado(Prestamo p);
	public ArrayList<Prestamo> listarSolicitudes();
	public ArrayList<Prestamo> listarSolicitudesRechazadas();
	public ArrayList<Prestamo> listarPrestamosMonto(float superior, float inferior);
	public boolean actualizarPrestamo(Prestamo p);
	public boolean rechazarPrestamo(Prestamo p);
}
