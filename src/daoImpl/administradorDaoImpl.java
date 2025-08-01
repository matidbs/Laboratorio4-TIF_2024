package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.administradorDao;
import entidad.Usuario;

public class administradorDaoImpl implements administradorDao {

	public static final String buscarAdministradorUsuarioYContra = "SELECT count(*) FROM usuarios WHERE nombreUser_U = ? and contra_U = ?";
	
	@Override
	public boolean existeAdministradorUsuario(Usuario usuario) {
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
			pst = cn.prepareStatement(buscarAdministradorUsuarioYContra);
			pst.setString(1, usuario.getNombreUser_U());
			pst.setString(2, usuario.getContra_U());
			rs = pst.executeQuery();
			if(rs.next()) {
				if (rs.getInt(1) > 0) resultado = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
