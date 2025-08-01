package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.tipoDeMovimientoDao;
import entidad.tipoDeMovimiento;

public class tipoDeMovimientoDaoImpl implements tipoDeMovimientoDao{

	private static final String listar = "Select * From TiposDeMovimientos";
	
	public tipoDeMovimientoDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<tipoDeMovimiento> listar() {
		ArrayList<tipoDeMovimiento> lista = new ArrayList<tipoDeMovimiento>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listar);
			rs = pst.executeQuery();
			while(rs.next()) {
				tipoDeMovimiento tc = new tipoDeMovimiento();
				tc.setCodTipoMovimiento_TM(rs.getInt(1));
				tc.setDescripcion_TM(rs.getString(2));
				lista.add(tc);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
