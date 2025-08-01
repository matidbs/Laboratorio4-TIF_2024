package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.tipoDeCuentaDao;
import entidad.Provincia;
import entidad.tipoDeCuenta;

public class tipoDeCuentaDaoImpl implements tipoDeCuentaDao {
	private static final String listar = "Select * From TiposDeCuenta";
	public static final String TipoCuentaEspecifica = "select * from tiposDeCuenta where codTipoCuenta_Tcu = ?";
	
	
	public tipoDeCuentaDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<tipoDeCuenta> listar() {
		ArrayList<tipoDeCuenta> lista = new ArrayList<tipoDeCuenta>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listar);
			rs = pst.executeQuery();
			while(rs.next()) {
				tipoDeCuenta tc = new tipoDeCuenta();
				tc.setCodTipoCuenta_Tcu(rs.getInt(1));
				tc.setDescripcion_Tcu(rs.getString(2));
				lista.add(tc);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<tipoDeCuenta> BuscarTipoDeCuentaCompleta(int codTipoCuenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<tipoDeCuenta> lista = new ArrayList<tipoDeCuenta>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(TipoCuentaEspecifica);
			pst.setInt(1, codTipoCuenta);
			rs = pst.executeQuery();
			while(rs.next()) {
				tipoDeCuenta p = new tipoDeCuenta();
				
				p.setCodTipoCuenta_Tcu(rs.getInt(1));
				p.setDescripcion_Tcu(rs.getString(2));	
				lista.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}


}
