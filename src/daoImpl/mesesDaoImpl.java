package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.mesesDao;
import entidad.Mes;
import entidad.Nacionalidad;

public class mesesDaoImpl implements mesesDao{

	public static final String listarMeses = "select * from meses";
	
	public mesesDaoImpl() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Mes> listar() {
		ArrayList<Mes> lista = new ArrayList<Mes>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarMeses);
			rs = pst.executeQuery();
			while(rs.next()) {
				Mes p = new Mes();
				
				p.setCodMes_M(rs.getInt(1));
				p.setDescripcion_M(rs.getString(2));	
				lista.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
