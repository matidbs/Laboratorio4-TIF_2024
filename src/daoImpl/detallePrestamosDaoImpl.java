package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.detallePrestamosDao;
import entidad.DetallePrestamo;

public class detallePrestamosDaoImpl implements detallePrestamosDao{
	
	public static final String listarCuotas = "SELECT nroPrestamo_DP,nroCuota_DP,montoCuota_DP,nroCuentaPago_DP,  fechaPagoCuota_DP,estado_DP,concat(clientes.nombre_Cl,', ',clientes.apellido_Cl) as nombre, prestamos.nroCuentaSolicitante_P FROM detallePrestamos left join prestamos on nroPrestamo_P = nroPrestamo_DP left join cuentas on nroCuenta_CU = prestamos.nroCuentaSolicitante_P  left join clientes on dniCliente_CU = dni_Cl"; 
	public static final String condicion_dni = " where dniCliente_CU = ?";
	public static final String condicion_nombreYapellido = " where concat(clientes.nombre_Cl,' ',clientes.apellido_Cl)  = ?";
	public static final String condicion_montoCuota =" where montoCuota_DP > ? and montoCuota_DP < ?";
	public static final String condicion_fecha =" where fechaPagoCuota_DP > ? and fechaPagoCuota_DP < ?";
	
	// estos son condiciones para que el cliente vea los detalles se las cuotas que tiene que pagar
	public static final String condicion_dniLadoCliente = " where prestamos.dniCliente_P = ?";
	
	public detallePrestamosDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas() {
		ArrayList<DetallePrestamo> listaDP = new ArrayList<DetallePrestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarCuotas);
			rs = pst.executeQuery();
			while (rs.next()) {
				DetallePrestamo dp = new DetallePrestamo();
				dp.setNroPrestamo_DP(rs.getInt("nroPrestamo_DP"));
				dp.setNroCuota_DP(rs.getInt("nroCuota_DP"));
				dp.setMontoCuota_DP(rs.getFloat("montoCuota_DP"));
				dp.setNroCuentaPago_DP(rs.getInt("nroCuentaPago_DP"));
				dp.setFechaPagoCuota_DP(rs.getString("fechaPagoCuota_DP"));
				dp.setEstado_DP(rs.getBoolean("estado_DP"));
				dp.setNombreCliente(rs.getString("nombre"));
				dp.setNrocuentaSolicitante(rs.getInt("nroCuentaSolicitante_P"));
				listaDP.add(dp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDP;
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas_DNI(String dni) {
		ArrayList<DetallePrestamo> listaDP = new ArrayList<DetallePrestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarCuotas+condicion_dni);
			pst.setString(1, dni);
			rs = pst.executeQuery();			
			while (rs.next()) {
				DetallePrestamo dp = new DetallePrestamo();
				dp.setNroPrestamo_DP(rs.getInt("nroPrestamo_DP"));
				dp.setNroCuota_DP(rs.getInt("nroCuota_DP"));
				dp.setMontoCuota_DP(rs.getFloat("montoCuota_DP"));
				dp.setNroCuentaPago_DP(rs.getInt("nroCuentaPago_DP"));
				dp.setFechaPagoCuota_DP(rs.getString("fechaPagoCuota_DP"));
				dp.setEstado_DP(rs.getBoolean("estado_DP"));
				dp.setNombreCliente(rs.getString("nombre"));
				listaDP.add(dp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDP;
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas_NOMBREyAPELLIDO(DetallePrestamo detallePrestamo) {
		ArrayList<DetallePrestamo> listaDP = new ArrayList<DetallePrestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarCuotas+condicion_nombreYapellido);
			pst.setString(1, detallePrestamo.getNombreCliente());
			rs = pst.executeQuery();			
			while (rs.next()) {
				DetallePrestamo dp = new DetallePrestamo();
				dp.setNroPrestamo_DP(rs.getInt("nroPrestamo_DP"));
				dp.setNroCuota_DP(rs.getInt("nroCuota_DP"));
				dp.setMontoCuota_DP(rs.getFloat("montoCuota_DP"));
				dp.setNroCuentaPago_DP(rs.getInt("nroCuentaPago_DP"));
				dp.setFechaPagoCuota_DP(rs.getString("fechaPagoCuota_DP"));
				dp.setEstado_DP(rs.getBoolean("estado_DP"));
				dp.setNombreCliente(rs.getString("nombre"));
				listaDP.add(dp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDP;
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas_MONTOCUOTA(float min, float max) {
		ArrayList<DetallePrestamo> listaDP = new ArrayList<DetallePrestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarCuotas+condicion_montoCuota);
			pst.setFloat(1, min);
			pst.setFloat(2, max);
			rs = pst.executeQuery();			
			while (rs.next()) {
				DetallePrestamo dp = new DetallePrestamo();
				dp.setNroPrestamo_DP(rs.getInt("nroPrestamo_DP"));
				dp.setNroCuota_DP(rs.getInt("nroCuota_DP"));
				dp.setMontoCuota_DP(rs.getFloat("montoCuota_DP"));
				dp.setNroCuentaPago_DP(rs.getInt("nroCuentaPago_DP"));
				dp.setFechaPagoCuota_DP(rs.getString("fechaPagoCuota_DP"));
				dp.setEstado_DP(rs.getBoolean("estado_DP"));
				dp.setNombreCliente(rs.getString("nombre"));
				listaDP.add(dp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDP;
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas_FECHA(String min, String max) {
		ArrayList<DetallePrestamo> listaDP = new ArrayList<DetallePrestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarCuotas+condicion_fecha);
			pst.setString(1, min);
			pst.setString(2, max);
			rs = pst.executeQuery();			
			while (rs.next()) {
				DetallePrestamo dp = new DetallePrestamo();
				dp.setNroPrestamo_DP(rs.getInt("nroPrestamo_DP"));
				dp.setNroCuota_DP(rs.getInt("nroCuota_DP"));
				dp.setMontoCuota_DP(rs.getFloat("montoCuota_DP"));
				dp.setNroCuentaPago_DP(rs.getInt("nroCuentaPago_DP"));
				dp.setFechaPagoCuota_DP(rs.getString("fechaPagoCuota_DP"));
				dp.setEstado_DP(rs.getBoolean("estado_DP"));
				dp.setNombreCliente(rs.getString("nombre"));
				listaDP.add(dp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDP;
	}

	@Override
	public ArrayList<DetallePrestamo> listaCuotas_DniLadoCliente(String dni) {
		ArrayList<DetallePrestamo> listaDP = new ArrayList<DetallePrestamo>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarCuotas+condicion_dniLadoCliente);
			pst.setString(1,dni);
			rs = pst.executeQuery();			
			while (rs.next()) {
				DetallePrestamo dp = new DetallePrestamo();
				dp.setNroPrestamo_DP(rs.getInt("nroPrestamo_DP"));
				dp.setNroCuota_DP(rs.getInt("nroCuota_DP"));
				dp.setMontoCuota_DP(rs.getFloat("montoCuota_DP"));
				dp.setNroCuentaPago_DP(rs.getInt("nroCuentaPago_DP"));
				dp.setFechaPagoCuota_DP(rs.getString("fechaPagoCuota_DP"));
				dp.setEstado_DP(rs.getBoolean("estado_DP"));
				dp.setNombreCliente(rs.getString("nombre"));
				dp.setNrocuentaSolicitante(rs.getInt("nroCuentaSolicitante_P"));
				listaDP.add(dp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDP;
	}

	@Override
	public boolean pagarCuotaPrestamo(DetallePrestamo detallePrestamo) {
		boolean filas = false;

		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			CallableStatement cs = cn.prepareCall("call sp_PagarCuota(?,?,?,?)");
			cs.setInt(1, detallePrestamo.getNroCuentaPago_DP());
			cs.setInt(2, detallePrestamo.getNroPrestamo_DP());
			cs.setInt(3, detallePrestamo.getNroCuota_DP());
			cs.setFloat(4, detallePrestamo.getMontoCuota_DP());
			 cs.execute();
			 filas = true;
			cn.commit();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}

}
