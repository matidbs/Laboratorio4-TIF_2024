package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.localidadesDao;
import entidad.Localidad;

public class localidadesDaoImpl implements localidadesDao {

	public static final String listarLocalidades = "SELECT * FROM localidades";
	public static final String LocalidadEspecifica = "select * from localidades where codLoc_L  = ?";
	public static final String listarLocalidadesPORProvincia = "select * from localidades where codProv_L = ?";
	public localidadesDaoImpl() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Localidad> listar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Localidad> lista = new ArrayList<Localidad>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarLocalidades);
			rs = pst.executeQuery();
			while(rs.next()) {
				Localidad l = new Localidad();
				l.setCodLoc_L(rs.getInt(1));
				l.setCodProv_L(rs.getInt(2));
				l.setDescripcion_L(rs.getString(3));		
				lista.add(l);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Localidad> listarPorProvincia(int codProv) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Localidad> lista = new ArrayList<Localidad>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(listarLocalidadesPORProvincia);
			pst.setInt(1, codProv);
			rs = pst.executeQuery();
			while(rs.next()) {
				Localidad l = new Localidad();
				l.setCodLoc_L(rs.getInt(1));
				l.setCodProv_L(rs.getInt(2));
				l.setDescripcion_L(rs.getString(3));		
				lista.add(l);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Localidad> BuscarLocalidadCompleta(int codLoc) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<Localidad> lista = new ArrayList<Localidad>();
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(LocalidadEspecifica);
			pst.setInt(1, codLoc);
			rs = pst.executeQuery();
			while(rs.next()) {
				Localidad l = new Localidad();
				l.setCodLoc_L(rs.getInt(1));
				l.setCodProv_L(rs.getInt(2));
				l.setDescripcion_L(rs.getString(3));		
				lista.add(l);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
