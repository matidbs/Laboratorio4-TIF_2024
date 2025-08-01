package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.provinciasDao;
import entidad.Provincia;

public class provinciasDaoImpl implements provinciasDao{

	public static final String listarProvincias = "SELECT * FROM provincias";
	public static final String ProvinciaEspecifica = "select * from provincias where codProv_P = ?";
	

	
	public provinciasDaoImpl() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Provincia> listar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Provincia> lista = new ArrayList<Provincia>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarProvincias);
			rs = pst.executeQuery();
			while(rs.next()) {
				Provincia p = new Provincia();
				
				p.setCodProv_P(rs.getInt(1));
				p.setDescripcion_P(rs.getString(2));	
				lista.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Provincia> BuscarProvinciaCompleta(int codProv) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Provincia> lista = new ArrayList<Provincia>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(ProvinciaEspecifica);
			pst.setInt(1, codProv);
			rs = pst.executeQuery();
			while(rs.next()) {
				Provincia p = new Provincia();
				
				p.setCodProv_P(rs.getInt(1));
				p.setDescripcion_P(rs.getString(2));	
				lista.add(p);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	

}
