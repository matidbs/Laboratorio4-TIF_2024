package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.prestamosDao;
import entidad.Prestamo;

public class prestamosDaoImpl implements prestamosDao{

	private static final String listarPrestamos = "SELECT * FROM Prestamos"; 	
	private static final String listarPrestamosCuenta = "SELECT * FROM prestamos WHERE nroCuentaSolicitante_P = ? AND estado_P is null AND fechaAltaPrestamo_p IS NULL";
	private static final String listarSolicitudes = "SELECT * FROM prestamos WHERE estado_P is null AND fechaAltaPrestamo_p IS NULL";
	private static final String listarSolicitudesRechazadas = "SELECT * FROM prestamos WHERE estado_P = 0 AND fechaAltaPrestamo_p IS NULL AND fechaFinalizacion_P is not null";
	private static final String listarSolicitudesMonto = "SELECT * FROM prestamos WHERE estado_P is null AND fechaAltaPrestamo_p IS NULL AND montoTotalAPagar_P > ? AND montoTotalAPagar_P < ?;";
	private static final String listarPrestamosNumero = "Select * From prestamos Where nroPrestamo_P  = ?";
	private static final String listarPrestamosDNI= "Select * From Prestamos Where DniCliente_P = ?";
	
	public prestamosDaoImpl() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	//Para SolicitudesPrestamos - Admin
	@Override
	public ArrayList<Prestamo> listarPrestamos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		
		try {
			pst = cn.prepareStatement(listarPrestamos);
			rs = pst.executeQuery();
			while(rs.next()) {
			Prestamo p = new Prestamo();
			p.setNroPrestamo_P(rs.getInt(1));
			p.setDniCliente_P(rs.getString(2));
			p.setNroCuentaSolicitante_P(rs.getInt(3));
			p.setCantidadCuotas_P(rs.getInt(4));
			p.setCuotasPagas_P(rs.getInt(5));
			p.setMontoTotalAPagar_P(rs.getFloat(6));
			p.setMontoSolicitado_P(rs.getFloat(7));
			p.setEstado_P(rs.getBoolean(8));
			p.setFechaAltaPrestamo_P(rs.getDate(9).toString());
			p.setFechaFinalizacion_P(rs.getDate(10).toString());
			lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	//Vista MisPrestamos - Cliente
	@Override
	public ArrayList<Prestamo> listarPrestamosDni(Prestamo prestamo) {		
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;		
		try {
			pst = cn.prepareStatement(listarPrestamosDNI);
			pst.setString(1, prestamo.getDniCliente_P());
			rs = pst.executeQuery();
			while(rs.next()) {
				Prestamo p = new Prestamo();
				p.setNroPrestamo_P(rs.getInt(1));
				p.setDniCliente_P(rs.getString(2));
				p.setNroCuentaSolicitante_P(rs.getInt(3));
				p.setCantidadCuotas_P(rs.getInt(4));
				p.setCuotasPagas_P(rs.getInt(5));
				p.setMontoTotalAPagar_P(rs.getFloat(6));
				p.setMontoSolicitado_P(rs.getFloat(7));
				p.setEstado_P(rs.getBoolean(8));
				try {
					p.setFechaAltaPrestamo_P(rs.getDate(9).toString());
				}catch (Exception e) {
					p.setFechaAltaPrestamo_P("-");
				}
				try {
					p.setFechaFinalizacion_P(rs.getDate(10).toString());
				}catch(Exception e) {
					p.setFechaFinalizacion_P("-");
				}
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lista;		
	}
	
	// Solicitudes de Prestamos - Admin
	@Override
	public Prestamo agregarPrestamo(Prestamo p) {
		Prestamo prestamo = new Prestamo();
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			CallableStatement cs = cn.prepareCall("call sp_AgregarPrestamo (?,?,?,?,?)");
			cs.setString(1, p.getDniCliente_P());
			cs.setInt(2, p.getNroCuentaSolicitante_P());
			cs.setInt(3, p.getCantidadCuotas_P());
			cs.setFloat(4, p.getMontoSolicitado_P());
			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			
			cs.execute();
			cn.commit();
			
			int nro = cs.getInt(5);
			prestamo.setNroPrestamo_P(nro);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return prestamo;
	}


	@Override
	public boolean modificarEstado(Prestamo p) {
		boolean filas = false;
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			CallableStatement cs = cn.prepareCall("sp_ModificarEstadoPrestamo (?,?)");
			cs.setInt(1, p.getNroPrestamo_P());
			cs.setBoolean(2, p.isEstado_P());
			filas = cs.execute();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosCuenta(int nroCuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarPrestamosCuenta);
			pst.setInt(1, nroCuenta);
			rs = pst.executeQuery();
			while(rs.next()) {
				Prestamo p = new Prestamo();
				p.setNroPrestamo_P(rs.getInt(1));
				p.setDniCliente_P(rs.getString(2));
				p.setNroCuentaSolicitante_P(rs.getInt(3));
				p.setCantidadCuotas_P(rs.getInt(4));
				p.setCuotasPagas_P(rs.getInt(5));
				p.setMontoTotalAPagar_P(rs.getFloat(6));
				p.setMontoSolicitado_P(rs.getFloat(7));
				p.setEstado_P(rs.getBoolean(8));
				try {
					p.setFechaAltaPrestamo_P(rs.getDate(9).toString());
				}catch (Exception e) {
					p.setFechaAltaPrestamo_P("-");
				}
				try {
					p.setFechaFinalizacion_P(rs.getDate(10).toString());
				}catch(Exception e) {
					p.setFechaFinalizacion_P("-");
				}
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<Prestamo> listarSolicitudes() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		
		try {
			pst = cn.prepareStatement(listarSolicitudes);
			rs = pst.executeQuery();
			while(rs.next()) {
				Prestamo p = new Prestamo();
				p.setNroPrestamo_P(rs.getInt(1));
				p.setDniCliente_P(rs.getString(2));
				p.setNroCuentaSolicitante_P(rs.getInt(3));
				p.setCantidadCuotas_P(rs.getInt(4));
				p.setCuotasPagas_P(rs.getInt(5));
				p.setMontoTotalAPagar_P(rs.getFloat(6));
				p.setMontoSolicitado_P(rs.getFloat(7));
				p.setEstado_P(rs.getBoolean(8));
				try {
					p.setFechaAltaPrestamo_P(rs.getDate(9).toString());
				}catch (Exception e) {
					p.setFechaAltaPrestamo_P("-");
				}
				try {
					p.setFechaFinalizacion_P(rs.getDate(10).toString());
				}catch(Exception e) {
					p.setFechaFinalizacion_P("-");
				}
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public ArrayList<Prestamo> listarSolicitudesRechazadas() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		
		try {
			pst = cn.prepareStatement(listarSolicitudesRechazadas);
			rs = pst.executeQuery();
			while(rs.next()) {
				Prestamo p = new Prestamo();
				p.setNroPrestamo_P(rs.getInt(1));
				p.setDniCliente_P(rs.getString(2));
				p.setNroCuentaSolicitante_P(rs.getInt(3));
				p.setCantidadCuotas_P(rs.getInt(4));
				p.setCuotasPagas_P(rs.getInt(5));
				p.setMontoTotalAPagar_P(rs.getFloat(6));
				p.setMontoSolicitado_P(rs.getFloat(7));
				p.setEstado_P(rs.getBoolean(8));
				try {
					p.setFechaAltaPrestamo_P(rs.getDate(9).toString());
				}catch (Exception e) {
					p.setFechaAltaPrestamo_P("-");
				}
				try {
					p.setFechaFinalizacion_P(rs.getDate(10).toString());
				}catch(Exception e) {
					p.setFechaFinalizacion_P("-");
				}
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<Prestamo> listarPrestamosMonto(float superior, float inferior) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		
		try {
			pst = cn.prepareStatement(listarSolicitudesMonto);
			pst.setFloat(1, superior);
			pst.setFloat(2, inferior);
			rs = pst.executeQuery();
			while(rs.next()) {
				Prestamo p = new Prestamo();
				p.setNroPrestamo_P(rs.getInt(1));
				p.setDniCliente_P(rs.getString(2));
				p.setNroCuentaSolicitante_P(rs.getInt(3));
				p.setCantidadCuotas_P(rs.getInt(4));
				p.setCuotasPagas_P(rs.getInt(5));
				p.setMontoTotalAPagar_P(rs.getFloat(6));
				p.setMontoSolicitado_P(rs.getFloat(7));
				p.setEstado_P(rs.getBoolean(8));
				try {
					p.setFechaAltaPrestamo_P(rs.getDate(9).toString());
				}catch (Exception e) {
					p.setFechaAltaPrestamo_P("-");
				}
				try {
					p.setFechaFinalizacion_P(rs.getDate(10).toString());
				}catch(Exception e) {
					p.setFechaFinalizacion_P("-");
				}
				lista.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public boolean actualizarPrestamo(Prestamo p) {
		boolean actualizado = false;
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			
			CallableStatement cs = cn.prepareCall("call sp_AceptarSolicitudPrestamo (?,?,?,?,?,?)");
			cs.setString(1, p.getFechaAltaPrestamo_P());
			cs.setInt(2, p.getNroPrestamo_P());
			cs.setInt(3, p.getNroCuentaSolicitante_P());
			cs.setInt(4, p.getCantidadCuotas_P());
			cs.setFloat(5, p.getMontoSolicitado_P());
			cs.setFloat(6, p.getMontoTotalAPagar_P());
			cs.execute();
			actualizado = true;
			cn.commit();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return actualizado;
	}

	@Override
	public boolean rechazarPrestamo(Prestamo p) {
		boolean actualizado = false;
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			CallableStatement cs = cn.prepareCall("call sp_RechazarSolicitudPrestamo (?,?,?)");
			cs.setString(1, p.getFechaAltaPrestamo_P());
			cs.setString(2, p.getFechaAltaPrestamo_P());
			cs.setInt(3, p.getNroPrestamo_P());
			actualizado = true;
			cs.execute();
			cn.commit();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return actualizado;
	}

	@Override
	public Prestamo listarPrestamoNumero(Prestamo p) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		Prestamo prestamo = new Prestamo();
		try {
			pst = cn.prepareStatement(listarPrestamosNumero);
			pst.setInt(1, p.getNroPrestamo_P());
			rs = pst.executeQuery();
			while(rs.next()) {
				prestamo.setNroPrestamo_P(rs.getInt(1));
				prestamo.setDniCliente_P(rs.getString(2));
				prestamo.setNroCuentaSolicitante_P(rs.getInt(3));
				prestamo.setCantidadCuotas_P(rs.getInt(4));
				prestamo.setCuotasPagas_P(rs.getInt(5));
				prestamo.setMontoTotalAPagar_P(rs.getFloat(6));
				prestamo.setMontoSolicitado_P(rs.getFloat(7));
				prestamo.setEstado_P(rs.getBoolean(8));
				try {
					prestamo.setFechaAltaPrestamo_P(rs.getDate(9).toString());
				}catch (Exception e) {
					prestamo.setFechaAltaPrestamo_P("-");
				}
				try {
					prestamo.setFechaFinalizacion_P(rs.getDate(10).toString());
				}catch(Exception e) {
					prestamo.setFechaFinalizacion_P("-");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return prestamo;
		
	}
}
