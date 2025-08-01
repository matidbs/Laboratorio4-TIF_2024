package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.nacionalidadesDao;
import entidad.Nacionalidad;



public class nacionalidadesDaoImpl implements nacionalidadesDao{

	public static final String listarNacionalidades = "SELECT * FROM nacionalidades";
	public static final String NacionalidadEspecifica = "select  * from nacionalidades where codNacionalidad_N = ?";
	
	public nacionalidadesDaoImpl() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Nacionalidad> listar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Nacionalidad> lista = new ArrayList<Nacionalidad>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarNacionalidades);
			rs = pst.executeQuery();
			while(rs.next()) {
				Nacionalidad p = new Nacionalidad();
				
				p.setCodNacionalidad_N(rs.getInt(1));
				p.setDescripcion_N(rs.getString(2));	
				lista.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Nacionalidad> BuscarNacionalidadCompleta(int codNac) {
		ArrayList<Nacionalidad> lista = new ArrayList<Nacionalidad>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(NacionalidadEspecifica);
			pst.setInt(1, codNac);
			rs = pst.executeQuery();
			while(rs.next()) {
				Nacionalidad p = new Nacionalidad();
				
				p.setCodNacionalidad_N(rs.getInt(1));
				p.setDescripcion_N(rs.getString(2));	
				lista.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}	

}
