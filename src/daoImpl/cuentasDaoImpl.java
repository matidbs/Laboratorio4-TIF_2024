package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.cuentasDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.tipoDeCuenta;
import negocio.tipoDeCuentaNegocio;
import negocioImpl.tipoDeCuentaNegocioImpl;

public class cuentasDaoImpl implements cuentasDao {
	public static final String listarCuentas = "SELECT * FROM cuentas";
	public static final String listarCuentasActivas = "SELECT * FROM cuentas_activas";
	public static final String listarCuentasInactivas = "SELECT * FROM cuentas_inactivas";
	public static final String listarCuentasDNI = "SELECT * FROM cuentas_activas WHERE dniCliente_Cu = ?";
	public static final String listarCuentasCBU = "SELECT * FROM cuentas_activas where CBU_CU = ?";
	public static final String listarCuentasNumeroCuenta = "SELECT * FROM cuentas where nroCuenta_Cu = ?";
	public static final String darBajaCuentas = "Update cuentas set estado_CU = 0 where dniCliente_CU = ?";
	public static final String activarCuentas = "Update cuentas set estado_CU = 1 where dniCliente_CU = ?";
	public static final String existeCuenta = "SELECT COUNT(*) FROM cuentas WHERE nroCuenta_CU = ?";
	
	
	
	public cuentasDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Cuenta Agregar(Cuenta cuenta){
		
		Cuenta cuentaNueva = new Cuenta();
		int cantidadCuentas = 0;
		int cbu = 0;
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			CallableStatement cs = cn.prepareCall("call sp_AgregarCuenta(?,?,?,?)");
			cs.setString(1,cuenta.getDniCliente_CU());
			cs.setInt(2, cuenta.getTipoCuenta_CU().getCodTipoCuenta_Tcu());
			cs.registerOutParameter(3, java.sql.Types.INTEGER); //cantidad de cuentas que tiene el cliente
			cs.registerOutParameter(4, java.sql.Types.INTEGER); //el numero de cbu generado automaticamente

			cs.execute();
			cn.commit();
			
			
			cantidadCuentas = cs.getInt(3);
			cbu = cs.getInt(4);

				if(cantidadCuentas != 3) 
				{
					//se gener� la cuenta para ese cliente porque tiene menos de tres cuentas asignadas
					//le cargo el cbu para despues buscarlo
					cuentaNueva.setDniCliente_CU(cuenta.getDniCliente_CU());
					cuentaNueva.setCBU_CU(cbu);
				}
				else 
				{
					//no se genera la cuenta para el cliente  porque ya tiene tres cuentas
					cuentaNueva.setDniCliente_CU("-1");
				}
				
			
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return cuentaNueva;
	}

	@Override
	public boolean Modificar(Cuenta cuenta) {
		int resultado = 0;
		boolean seModifico = true;
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			CallableStatement cs = cn.prepareCall("call sp_ModificarCuenta(?,?,?,?,?,?,?)");
			cs.setInt(1,cuenta.getNroCuenta_CU());
			cs.setString(2, cuenta.getDniCliente_CU());
			cs.setInt(3,cuenta.getTipoCuenta_CU().getCodTipoCuenta_Tcu());
			cs.setInt(4,cuenta.getCBU_CU());
			cs.setFloat(5, cuenta.getSaldo_CU());
			cs.setBoolean(6,cuenta.isEstado_CU());
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			
			cs.executeUpdate();
			cn.commit();
			
			resultado = cs.getInt(7);

			if(resultado == 0) seModifico =  false; //no lo modifico porque el cliente ya tiene 3 cuentas asignadas  
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return seModifico;
	}

	@Override
	public boolean Eliminar(Cuenta cuenta) {
boolean filas = false;
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			CallableStatement cs = cn.prepareCall("call sp_EliminarCuenta(?)");
			cs.setInt(1,cuenta.getNroCuenta_CU());

			filas = cs.execute();
			cn.commit();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}
	


	
	@Override
	public ArrayList<Cuenta> listarCuentas() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarCuentas);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setNroCuenta_CU(rs.getInt(1));
				cuenta.setDniCliente_CU(rs.getString(2));
				cuenta.setFechaCreacion_CU(rs.getString(3));
				
				tipoDeCuenta tc = new tipoDeCuenta();
				tc.setCodTipoCuenta_Tcu(rs.getInt(4));
				
				cuenta.setTipoCuenta_CU(tc);
				cuenta.setCBU_CU(rs.getInt(5));
				cuenta.setSaldo_CU(rs.getFloat(6));
				cuenta.setEstado_CU(rs.getBoolean(7));
				lista.add(cuenta);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<Cuenta> listarCuentasActivas() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarCuentasActivas);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setNroCuenta_CU(rs.getInt(1));
				cuenta.setDniCliente_CU(rs.getString(2));
				cuenta.setFechaCreacion_CU(rs.getString(3));
				
				tipoDeCuenta tc = new tipoDeCuenta();
				tc.setCodTipoCuenta_Tcu(rs.getInt(4));
				tc.setDescripcion_Tcu(rs.getString(5));
				
				cuenta.setTipoCuenta_CU(tc);
				cuenta.setCBU_CU(rs.getInt(6));
				cuenta.setSaldo_CU(rs.getFloat(7));
				lista.add(cuenta);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<Cuenta> listarCuentasInactivas() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarCuentasInactivas);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setNroCuenta_CU(rs.getInt(1));
				cuenta.setDniCliente_CU(rs.getString(2));
				cuenta.setFechaCreacion_CU(rs.getString(3));
				
				tipoDeCuenta tc = new tipoDeCuenta();
				tc.setCodTipoCuenta_Tcu(rs.getInt(4));
				tc.setDescripcion_Tcu(rs.getString(5));
				
				cuenta.setTipoCuenta_CU(tc);
				cuenta.setCBU_CU(rs.getInt(6));
				cuenta.setSaldo_CU(rs.getFloat(7));
				lista.add(cuenta);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<Cuenta> listarCuentasDNI(Cuenta cuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarCuentasDNI);
			pst.setString(1, cuenta.getDniCliente_CU());
			rs = pst.executeQuery();
			while(rs.next()) {
				Cuenta x = new Cuenta();
				x.setNroCuenta_CU(rs.getInt(1));
				x.setDniCliente_CU(rs.getString(2));
				x.setFechaCreacion_CU(rs.getString(3));
				
				tipoDeCuenta tc = new tipoDeCuenta();
				tc.setCodTipoCuenta_Tcu(rs.getInt(4));
				tc.setDescripcion_Tcu(rs.getString(5));
				
				x.setTipoCuenta_CU(tc);
				x.setCBU_CU(rs.getInt(6));
				x.setSaldo_CU(rs.getFloat(7));
				lista.add(x);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ArrayList<Cuenta> listarCuentaCBU(Cuenta cuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarCuentasCBU);
			pst.setInt(1, cuenta.getCBU_CU());
			rs = pst.executeQuery();
			while(rs.next()) {
				Cuenta x = new Cuenta();
				x.setNroCuenta_CU(rs.getInt(1));
				x.setDniCliente_CU(rs.getString(2));
				x.setFechaCreacion_CU(rs.getString(3));
				
				tipoDeCuenta tc = new tipoDeCuenta();
				tc.setCodTipoCuenta_Tcu(rs.getInt(4));
				tc.setDescripcion_Tcu(rs.getString(5));
				
				x.setTipoCuenta_CU(tc);
				x.setCBU_CU(rs.getInt(6));
				x.setSaldo_CU(rs.getFloat(7));
				lista.add(x);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public ArrayList<Cuenta> listarCuentaNroCuenta(Cuenta cuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarCuentasNumeroCuenta);
			pst.setInt(1, cuenta.getNroCuenta_CU());
			rs = pst.executeQuery();
			while(rs.next()) {
				Cuenta x = new Cuenta();
				x.setNroCuenta_CU(rs.getInt(1));
				x.setDniCliente_CU(rs.getString(2));
				x.setFechaCreacion_CU(rs.getString(3));
				
				tipoDeCuenta tc = new tipoDeCuenta();
				tc.setCodTipoCuenta_Tcu(rs.getInt(4));
				
				tipoDeCuentaNegocio tipoNegocio = new tipoDeCuentaNegocioImpl();		
				ArrayList<tipoDeCuenta> tcCompleta=  (ArrayList<tipoDeCuenta>) tipoNegocio.BuscarTipoDeCuentaCompleta(tc.getCodTipoCuenta_Tcu());
				tc = tcCompleta.get(0);
				
				x.setTipoCuenta_CU(tc);
				x.setCBU_CU(rs.getInt(5));
				x.setSaldo_CU(rs.getFloat(6));
				
				int valorTinyint = rs.getInt(7);
				x.setEstado_CU(valorTinyint != 0);
				lista.add(x);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	
	@Override
	public boolean existeCuenta(Cuenta cuenta) {
		boolean resultado = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(existeCuenta);
			pst.setInt(1, cuenta.getNroCuenta_CU());
			rs = pst.executeQuery();
			if(rs.next()) {
				if (rs.getInt(1) > 0) resultado = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean activarCuentasCliente(Cuenta cuenta) {
		boolean estado = false;
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		try {
			pst = cn.prepareStatement(activarCuentas);
			pst.setString(1, cuenta.getDniCliente_CU());
			int filas = pst.executeUpdate();
			if(filas > 0) estado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public boolean darBajaCuentasCliente(Cuenta cuenta) {
		boolean estado = false;
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		try {
			pst = cn.prepareStatement(darBajaCuentas);
			pst.setString(1, cuenta.getDniCliente_CU());
			int filas = pst.executeUpdate();
			if(filas > 0) estado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estado;
	}
	
}
