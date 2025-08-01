package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.reportesDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Reporte;
import entidad.Usuario;

public class reportesDaoImpl implements reportesDao{

	private static final String listarReportes_SinCondicion = "SELECT nroCuenta_CU as 'Número de Cuenta', dniCliente_CU as 'DNI del cliente', CONCAT(clientes.apellido_Cl,', ', clientes.nombre_Cl) as 'Apellido y Nombre',  provincias.descripcion_P as 'Provincia', localidades.descripcion_L as 'Localidad', fechaCreacion_CU as 'Fecha de Creación', tiposdecuenta.descripcion_Tcu as 'Tipo de Cuenta', CBU_CU as CBU,  Saldo_CU as Saldo ,cuentas.Estado_CU as Estado  FROM cuentas inner join clientes on cuentas.dniCliente_CU = clientes.dni_Cl  INNER JOIN tiposdecuenta on cuentas.tipoCuenta_CU = tiposdecuenta.codTipoCuenta_Tcu  INNER JOIN provincias on clientes.codProv_Cl = provincias.codProv_P INNER JOIN localidades on clientes.codLoc_Cl = localidades.codLoc_L ";
	private static final String condicion_Provincia = "where clientes.codProv_Cl = ?"; //pCodProv_Cl
	private static final String condicion_ProvinciaLocalidad = "where clientes.codLoc_Cl = ?"; // pCodLoc_Cl
	private static final String condicion_Anio = "WHERE YEAR(fechaCreacion_CU) = ?"; //pAnio
	private static final String condicion_AnioMes = "WHERE YEAR(fechaCreacion_CU) = ? and MONTH(fechaCreacion_CU) = ?"; //pAnio y pmes
	private static final String condicion_SaldoIgual = " where Saldo_CU = ?";
	private static final String condicion_SaldoMayor = " where Saldo_CU > ?";
	private static final String condicion_SaldoMenor = " where Saldo_CU < ?";
	
	public reportesDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Reporte> listarReportes() {
		ArrayList<Reporte> listaC = new ArrayList<Reporte>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarReportes_SinCondicion);
			rs = pst.executeQuery();
			while(rs.next()) {
				
				//cuenta, dni, apellido y nom,prov,loc, fecha, tipo cuenta, cbu, saldo , estado
				Reporte c = new Reporte();
				c.setNumeroDeCuenta(rs.getInt(1));
				c.setDniDelCliente(rs.getString(2));
				c.setApellidoYNombre(rs.getString(3));
				c.setProvincia(rs.getString(4));
				c.setLocalidad(rs.getString(5));
				c.setFechaDeCreacion(rs.getString(6));
				c.setTipoDeCuenta(rs.getString(7));
				c.setCbu(rs.getInt(8));
				c.setSaldo(rs.getFloat(9));
				c.setEstado(rs.getBoolean(10));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;
	}
	

	@Override
	public ArrayList<Reporte> listarReportes_Provincia(int codProv) {
		ArrayList<Reporte> listaC = new ArrayList<Reporte>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarReportes_SinCondicion + condicion_Provincia);
			pst.setInt(1, codProv);
			rs = pst.executeQuery();
			while(rs.next()) {
				
				//cuenta, dni, apellido y nom,prov,loc, fecha, tipo cuenta, cbu, saldo , estado
				Reporte c = new Reporte();
				c.setNumeroDeCuenta(rs.getInt(1));
				c.setDniDelCliente(rs.getString(2));
				c.setApellidoYNombre(rs.getString(3));
				c.setProvincia(rs.getString(4));
				c.setLocalidad(rs.getString(5));
				c.setFechaDeCreacion(rs.getString(6));
				c.setTipoDeCuenta(rs.getString(7));
				c.setCbu(rs.getInt(8));
				c.setSaldo(rs.getFloat(9));
				c.setEstado(rs.getBoolean(10));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;
	}

	@Override
	public ArrayList<Reporte> listarReportes_ProvinciaLocalidad(int codLoc) {
		ArrayList<Reporte> listaC = new ArrayList<Reporte>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarReportes_SinCondicion + condicion_ProvinciaLocalidad);
			pst.setInt(1, codLoc);
			rs = pst.executeQuery();
			while(rs.next()) {
				
				//cuenta, dni, apellido y nom,prov,loc, fecha, tipo cuenta, cbu, saldo , estado
				Reporte c = new Reporte();
				c.setNumeroDeCuenta(rs.getInt(1));
				c.setDniDelCliente(rs.getString(2));
				c.setApellidoYNombre(rs.getString(3));
				c.setProvincia(rs.getString(4));
				c.setLocalidad(rs.getString(5));
				c.setFechaDeCreacion(rs.getString(6));
				c.setTipoDeCuenta(rs.getString(7));
				c.setCbu(rs.getInt(8));
				c.setSaldo(rs.getFloat(9));
				c.setEstado(rs.getBoolean(10));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;
	}

	@Override
	public ArrayList<Reporte> listarReportes_Anio(String anio) {
		ArrayList<Reporte> listaC = new ArrayList<Reporte>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarReportes_SinCondicion + condicion_Anio);
			pst.setString(1, anio);
			rs = pst.executeQuery();
			while(rs.next()) {
				
				//cuenta, dni, apellido y nom,prov,loc, fecha, tipo cuenta, cbu, saldo , estado
				Reporte c = new Reporte();
				c.setNumeroDeCuenta(rs.getInt(1));
				c.setDniDelCliente(rs.getString(2));
				c.setApellidoYNombre(rs.getString(3));
				c.setProvincia(rs.getString(4));
				c.setLocalidad(rs.getString(5));
				c.setFechaDeCreacion(rs.getString(6));
				c.setTipoDeCuenta(rs.getString(7));
				c.setCbu(rs.getInt(8));
				c.setSaldo(rs.getFloat(9));
				c.setEstado(rs.getBoolean(10));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;
	}

	@Override
	public ArrayList<Reporte> listarReportes_AnioMes(String anio, String mes) {
		ArrayList<Reporte> listaC = new ArrayList<Reporte>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarReportes_SinCondicion + condicion_AnioMes);
			pst.setString(1, anio);
			pst.setString(2, mes);
			rs = pst.executeQuery();
			while(rs.next()) {
				
				//cuenta, dni, apellido y nom,prov,loc, fecha, tipo cuenta, cbu, saldo , estado
				Reporte c = new Reporte();
				c.setNumeroDeCuenta(rs.getInt(1));
				c.setDniDelCliente(rs.getString(2));
				c.setApellidoYNombre(rs.getString(3));
				c.setProvincia(rs.getString(4));
				c.setLocalidad(rs.getString(5));
				c.setFechaDeCreacion(rs.getString(6));
				c.setTipoDeCuenta(rs.getString(7));
				c.setCbu(rs.getInt(8));
				c.setSaldo(rs.getFloat(9));
				c.setEstado(rs.getBoolean(10));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;
	}

	@Override
	public ArrayList<Reporte> listarReportes_Saldo(String operador, String saldo) {
		ArrayList<Reporte> listaC = new ArrayList<Reporte>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		String consulta = listarReportes_SinCondicion;
		try {
			
			if(operador.equals("=")) consulta += condicion_SaldoIgual;
			else 
			{
				if(operador.equals(">")) consulta += condicion_SaldoMayor;
				else consulta += condicion_SaldoMenor;
			}
			
			pst = cn.prepareStatement(consulta);
			pst.setFloat(1, Float.parseFloat(saldo));
			rs = pst.executeQuery();
			while(rs.next()) {
				
				//cuenta, dni, apellido y nom,prov,loc, fecha, tipo cuenta, cbu, saldo , estado
				Reporte c = new Reporte();
				c.setNumeroDeCuenta(rs.getInt(1));
				c.setDniDelCliente(rs.getString(2));
				c.setApellidoYNombre(rs.getString(3));
				c.setProvincia(rs.getString(4));
				c.setLocalidad(rs.getString(5));
				c.setFechaDeCreacion(rs.getString(6));
				c.setTipoDeCuenta(rs.getString(7));
				c.setCbu(rs.getInt(8));
				c.setSaldo(rs.getFloat(9));
				c.setEstado(rs.getBoolean(10));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;
	}	
}