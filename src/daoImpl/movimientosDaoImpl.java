package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.movimientosDao;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Transferencia;
import entidad.tipoDeMovimiento;

public class movimientosDaoImpl implements movimientosDao {
	private static final String listarMovimientos = "SELECT * FROM movimientos WHERE nroCuenta_M = ?";
	private static final String filtrarMovimientosCBU = "SELECT nroMovimiento_M, nroCuenta_M, codTipoMovimiento_M, detalle_M, importe_M, fecha_M, CBU_CU FROM bancodb.movimientos INNER JOIN cuentas ON nroCuenta_CU = nroCuenta_M WHERE nroCuenta_M = ? AND CBU_CU = ?";
	private static final String filtrarMovimientosTipoMov = "SELECT nroMovimiento_M, nroCuenta_M, codTipoMovimiento_M, detalle_M, importe_M, fecha_M FROM movimientos WHERE codTipoMovimiento_M = ? AND nroCuenta_M = ?";

	public movimientosDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Movimiento> listarMovimientos(Cuenta cuenta) {
		ArrayList<Movimiento> listaMov = new ArrayList<Movimiento>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarMovimientos);
			pst.setInt(1, cuenta.getNroCuenta_CU());
			rs = pst.executeQuery();
			while(rs.next()) {
				Movimiento mov = new Movimiento();
				mov.setNroMovimiento_M(rs.getInt(1));
				
				Cuenta c = new Cuenta();
				c.setNroCuenta_CU(rs.getInt(2));
				
				mov.setCuenta_M(c);
				
				tipoDeMovimiento tm = new tipoDeMovimiento();
				tm.setCodTipoMovimiento_TM(rs.getInt(3));
				
				mov.setTipoMovimiento_M(tm);
				mov.setDetalle_M(rs.getString(4));
				mov.setImporte_M(rs.getFloat(5));
				mov.setFecha_M(rs.getString(6));
				listaMov.add(mov);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMov;
	}
	
	@Override
	public ArrayList<Movimiento> filtrarMovimientosCBU(Cuenta cuenta, String cbu) {
		ArrayList<Movimiento> listaMov = new ArrayList<Movimiento>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(filtrarMovimientosCBU);
			pst.setInt(1, cuenta.getNroCuenta_CU());
			pst.setInt(2, Integer.parseInt(cbu));
			rs = pst.executeQuery();
			while(rs.next()) {
				Movimiento mov = new Movimiento();
				mov.setNroMovimiento_M(rs.getInt(1));
				Cuenta c = new Cuenta();
				c.setNroCuenta_CU(rs.getInt(2));
				
				mov.setCuenta_M(c);
				
				tipoDeMovimiento tm = new tipoDeMovimiento();
				tm.setCodTipoMovimiento_TM(rs.getInt(3));
				
				mov.setTipoMovimiento_M(tm);
				mov.setDetalle_M(rs.getString(4));
				mov.setImporte_M(rs.getFloat(5));
				mov.setFecha_M(rs.getString(6));
				listaMov.add(mov);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMov;
	}

	@Override
	public boolean AgregarTransferencia(Transferencia transferencia) {
		boolean filas = false;
		int resultadoOperacion = 0;
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			
			CallableStatement cs = cn.prepareCall("call sp_Transferencia(?,?,?,?,?,?,?)");
			cs.setInt(1, transferencia.getCuentaOrigen());
			cs.setInt(2,transferencia.getCuentaDestino());
			cs.setInt(3,transferencia.getCbuOrigen());
			cs.setInt(4,transferencia.getCbuDestino());
			cs.setFloat(5,transferencia.getMonto());
			cs.setString(6,transferencia.getDetalle());
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			
			cs.execute();
			cn.commit();
			
			resultadoOperacion = cs.getInt(7);
			
			if(resultadoOperacion == 1) filas = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}
	
	@Override
	public ArrayList<Movimiento> filtrarMovimientosTipoMov(int codTipo,
			Cuenta cuenta) {
		ArrayList<Movimiento> listaMov = new ArrayList<Movimiento>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(filtrarMovimientosTipoMov);
			pst.setInt(1, codTipo);
			pst.setInt(2, cuenta.getNroCuenta_CU());
			rs = pst.executeQuery();
			while(rs.next()) {
				Movimiento mov = new Movimiento();
				mov.setNroMovimiento_M(rs.getInt(1));
				
				Cuenta c = new Cuenta();
				c.setNroCuenta_CU(rs.getInt(2));
				
				mov.setCuenta_M(c);
				
				tipoDeMovimiento tm = new tipoDeMovimiento();
				tm.setCodTipoMovimiento_TM(rs.getInt(3));
				
				mov.setTipoMovimiento_M(tm);
				mov.setDetalle_M(rs.getString(4));
				mov.setImporte_M(rs.getFloat(5));
				mov.setFecha_M(rs.getString(6));
				listaMov.add(mov);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMov;
	}
}
