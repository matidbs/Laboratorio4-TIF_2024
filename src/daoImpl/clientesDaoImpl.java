package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.clientesDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Usuario;

public class clientesDaoImpl implements clientesDao{

	private static final String listarClientes = "SELECT * FROM listarClientes WHERE estado = 1"; //View para menu admin - listar clientes
	private static final String darBajaCliente = "SELECT dni_Cl AS DNI, cuil_Cl AS CUIL, nombre_Cl AS NOMBRE, apellido_Cl AS APELLIDO, usuario_Cl as USUARIO FROM clientes WHERE estado_Cl = 1";
	private static final String filtrarClienteXdni = "SELECT * FROM listarClientes WHERE dni = ? AND estado = 1";
	private static final String filtrarClienteXdniModificar = "SELECT * FROM clientes WHERE dni_Cl = ?";
	private static final String filtrarClientesXedad = "SELECT * FROM listarClientes WHERE TIMESTAMPDIFF(YEAR, fechaNac, CURDATE()) = ? AND estado = 1";
	private static final String filtrarClientesXnombre = "SELECT * FROM listarClientes WHERE nombre LIKE ? AND estado = 1";
	private static final String filtrarClientesXapellido = "SELECT * FROM listarClientes WHERE apellido LIKE ? AND estado = 1";
	private static final String filtrarClientesXnombre_y_apellido = "SELECT * FROM listarClientes WHERE nombre LIKE ? AND apellido LIKE ? AND estado = 1";
	public static final String buscarClienteDNI = "SELECT COUNT(*) FROM clientes WHERE dni_Cl = ? ";
	public static final String dadoBaja = "SELECT COUNT(*) FROM clientes WHERE dni_Cl = ? AND estado_Cl = 1";
	public static final String buscarClienteUsuario = "SELECT COUNT(*) FROM clientes WHERE usuario_Cl = ?";
	public static final String buscarClienteXCBUCuenta = "select count(*) from clientes  inner join cuentas on clientes.dni_Cl = cuentas.dniCliente_CU where cbu_cu=? and estado_Cl = 1";
	public static final String buscarClienteUsuarioYContra = "SELECT * FROM clientes WHERE usuario_Cl = ? and contra_Cl = ?";
	public static final String buscarCuilCliente = "SELECT * FROM clientes WHERE cuil_Cl = ?";
	
	public clientesDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean Agregar(Cliente cliente) {
		boolean filas = false;
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			CallableStatement cs = cn.prepareCall("call sp_AgregarCliente (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			cs.setString(1,cliente.getDni_Cl());
			cs.setString(2,cliente.getCuil_Cl());
			cs.setString(3,cliente.getNombre_Cl());
			cs.setString(4,cliente.getApellido_Cl());
			cs.setString(5, cliente.getSexo_Cl());
			//del la propiedad Nacionalidad del cliente (CodNacionalidad_Cl) trae el objeto Nacionalidad y busco el codigo 
			cs.setInt(6, cliente.getCodNacionalidad_Cl().getCodNacionalidad_N());
			cs.setString(7, cliente.getDireccion_Cl());
			cs.setInt(8, cliente.getCodLoc_Cl().getCodLoc_L());
			cs.setInt(9, cliente.getCodProv_Cl().getCodProv_P());
			cs.setString(10, cliente.getCorreoElectronico_Cl());
			cs.setString(11, cliente.getTelefono_Cl());
			cs.setString(12, cliente.getUser_contra_Cl().getNombreUser_U());
			cs.setString(13, cliente.getUser_contra_Cl().getContra_U());
			cs.setString(14, cliente.getFechaNacimiento_Cl());

			 cs.execute();
			 filas = true;
			cn.commit();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}

	@Override
	public boolean Modificar(Cliente cliente) {
		boolean estado = false;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			CallableStatement cs = cn.prepareCall("call sp_ModificarCliente (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			cs.setString(1,cliente.getDni_Cl());
			cs.setString(2,cliente.getCuil_Cl());
			cs.setString(3,cliente.getNombre_Cl());
			cs.setString(4,cliente.getApellido_Cl());
			cs.setString(5, cliente.getSexo_Cl());
			cs.setInt(6, cliente.getCodNacionalidad_Cl().getCodNacionalidad_N());
			cs.setString(7, cliente.getDireccion_Cl());
			cs.setInt(8, cliente.getCodLoc_Cl().getCodLoc_L());
			cs.setInt(9, cliente.getCodProv_Cl().getCodProv_P());
			cs.setString(10, cliente.getCorreoElectronico_Cl());
			cs.setString(11, cliente.getTelefono_Cl());
			//el nombre de usuario no se modifica
			cs.setString(12, cliente.getUser_contra_Cl().getContra_U());
			cs.setString(13, cliente.getFechaNacimiento_Cl());
			cs.setBoolean(14, cliente.isEstado_Cl());
			int filas = cs.executeUpdate();
			cn.commit();
			if(filas > 0) estado = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return estado;
	}

	@Override
	public boolean Eliminar(Cliente cliente) {
		boolean estado = false;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			CallableStatement cs = cn.prepareCall("call sp_EliminarCliente(?)");
			cs.setString(1,cliente.getDni_Cl());

			int filas = cs.executeUpdate();
			cn.commit();
			if(filas > 0) estado = true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return estado;
	}
	
	@Override
	public ArrayList<Cliente> listarClientes() {
		ArrayList<Cliente> listaC = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(listarClientes);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				Nacionalidad n = new Nacionalidad();
				Provincia p = new Provincia();
				Localidad l = new Localidad();
				Usuario u = new Usuario();
				c.setDni_Cl(rs.getString("dni"));
				c.setCuil_Cl(rs.getString("cuil"));
				c.setNombre_Cl(rs.getString("nombre"));
				c.setApellido_Cl(rs.getString("apellido"));
				c.setSexo_Cl(rs.getString("sexo"));
				n.setDescripcion_N(rs.getString("nacionalidad"));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(rs.getString("direccion"));
				l.setDescripcion_L(rs.getString("localidad"));
				c.setCodLoc_Cl(l);
				p.setDescripcion_P(rs.getString("provincia"));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(rs.getString("correo"));
				c.setTelefono_Cl(rs.getString("telefono"));
				u.setNombreUser_U(rs.getString("usuario"));
				u.setContra_U(rs.getString(14));
				c.setUser_contra_Cl(u);
				c.setFechaNacimiento_Cl(rs.getString("fechaNac"));
				c.setEstado_Cl(rs.getBoolean("estado"));
				
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;	
	}

	@Override
	public boolean existeCliente(Cliente cliente) {
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
			pst = cn.prepareStatement(buscarClienteDNI);
			pst.setString(1, cliente.getDni_Cl());
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
	public ArrayList<Cliente> filtrarClienteXdni(Cliente cliente) {
		ArrayList<Cliente> listaC = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(filtrarClienteXdni);
			pst.setString(1, cliente.getDni_Cl());
			rs = pst.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				Nacionalidad n = new Nacionalidad();
				Provincia p = new Provincia();
				Localidad l = new Localidad();
				Usuario u = new Usuario();
				c.setDni_Cl(rs.getString("dni"));
				c.setCuil_Cl(rs.getString("cuil"));
				c.setNombre_Cl(rs.getString("nombre"));
				c.setApellido_Cl(rs.getString("apellido"));
				c.setSexo_Cl(rs.getString("sexo"));
				n.setDescripcion_N(rs.getString("nacionalidad"));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(rs.getString("direccion"));
				l.setDescripcion_L(rs.getString("localidad"));
				c.setCodLoc_Cl(l);
				p.setDescripcion_P(rs.getString("provincia"));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(rs.getString("correo"));
				c.setTelefono_Cl(rs.getString("telefono"));
				u.setNombreUser_U(rs.getString("usuario"));
				u.setContra_U(rs.getString(14));
				c.setUser_contra_Cl(u);
				c.setFechaNacimiento_Cl(rs.getString("fechaNac"));
				c.setEstado_Cl(rs.getBoolean("estado"));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;	
	}

	@Override
	public boolean existeClienteUsuario(Cliente cliente) {
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
			pst = cn.prepareStatement(buscarClienteUsuario);
			pst.setString(1, cliente.getUser_contra_Cl().getNombreUser_U());
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
	public Cliente existeClienteUsuarioYContra(Usuario usuario) {
		Cliente c = new Cliente();
		c.setDni_Cl("");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement pst;
		ResultSet rs;
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			pst = cn.prepareStatement(buscarClienteUsuarioYContra);
			pst.setString(1, usuario.getNombreUser_U());
			pst.setString(2, usuario.getContra_U());
			rs = pst.executeQuery();
			if(rs.next()) {
				if (rs.getInt(1) > 0) 
				{
					Nacionalidad n = new Nacionalidad();
					Provincia p = new Provincia();
					Localidad l = new Localidad();
					Usuario u = new Usuario();
					c.setDni_Cl(rs.getString(1));
					c.setCuil_Cl(rs.getString(2));
					c.setNombre_Cl(rs.getString(3));
					c.setApellido_Cl(rs.getString(4));
					c.setSexo_Cl(rs.getString(5));
					n.setDescripcion_N(rs.getString(6));
					c.setCodNacionalidad_Cl(n);
					c.setDireccion_Cl(rs.getString(7));
					l.setDescripcion_L(rs.getString(8));
					c.setCodLoc_Cl(l);
					p.setDescripcion_P(rs.getString(9));
					c.setCodProv_Cl(p);
					c.setCorreoElectronico_Cl(rs.getString(10));
					c.setTelefono_Cl(rs.getString(11));
					u.setNombreUser_U(rs.getString(12));
					u.setContra_U(rs.getString(13));
					c.setUser_contra_Cl(u);
					c.setFechaNacimiento_Cl(rs.getString(14));
					c.setEstado_Cl(rs.getBoolean(15));
						
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public ArrayList<Cliente> filtrarClientesXedad(String edad) {
		ArrayList<Cliente> listaC = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(filtrarClientesXedad);
			pst.setString(1, edad);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				Nacionalidad n = new Nacionalidad();
				Provincia p = new Provincia();
				Localidad l = new Localidad();
				Usuario u = new Usuario();
				c.setDni_Cl(rs.getString("dni"));
				c.setCuil_Cl(rs.getString("cuil"));
				c.setNombre_Cl(rs.getString("nombre"));
				c.setApellido_Cl(rs.getString("apellido"));
				c.setSexo_Cl(rs.getString("sexo"));
				n.setDescripcion_N(rs.getString("nacionalidad"));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(rs.getString("direccion"));
				l.setDescripcion_L(rs.getString("localidad"));
				c.setCodLoc_Cl(l);
				p.setDescripcion_P(rs.getString("provincia"));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(rs.getString("correo"));
				c.setTelefono_Cl(rs.getString("telefono"));
				u.setNombreUser_U(rs.getString("usuario"));
				u.setContra_U(rs.getString(14));
				c.setUser_contra_Cl(u);
				c.setFechaNacimiento_Cl(rs.getString("fechaNac"));
				c.setEstado_Cl(rs.getBoolean("estado"));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;	
	}

	@Override
	public ArrayList<Cliente> filtrarClientesXnombre(Cliente cliente) {
		ArrayList<Cliente> listaC = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(filtrarClientesXnombre);
			pst.setString(1, cliente.getNombre_Cl());
			rs = pst.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				Nacionalidad n = new Nacionalidad();
				Provincia p = new Provincia();
				Localidad l = new Localidad();
				Usuario u = new Usuario();
				c.setDni_Cl(rs.getString("dni"));
				c.setCuil_Cl(rs.getString("cuil"));
				c.setNombre_Cl(rs.getString("nombre"));
				c.setApellido_Cl(rs.getString("apellido"));
				c.setSexo_Cl(rs.getString("sexo"));
				n.setDescripcion_N(rs.getString("nacionalidad"));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(rs.getString("direccion"));
				l.setDescripcion_L(rs.getString("localidad"));
				c.setCodLoc_Cl(l);
				p.setDescripcion_P(rs.getString("provincia"));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(rs.getString("correo"));
				c.setTelefono_Cl(rs.getString("telefono"));
				u.setNombreUser_U(rs.getString("usuario"));
				u.setContra_U(rs.getString(13));
				c.setUser_contra_Cl(u);
				c.setFechaNacimiento_Cl(rs.getString("fechaNac"));
				c.setEstado_Cl(rs.getBoolean("estado"));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;	
	}

	@Override
	public ArrayList<Cliente> filtrarClientesXapellido(Cliente cliente) {
		ArrayList<Cliente> listaC = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(filtrarClientesXapellido);
			pst.setString(1, cliente.getApellido_Cl());
			rs = pst.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				Nacionalidad n = new Nacionalidad();
				Provincia p = new Provincia();
				Localidad l = new Localidad();
				Usuario u = new Usuario();
				c.setDni_Cl(rs.getString("dni"));
				c.setCuil_Cl(rs.getString("cuil"));
				c.setNombre_Cl(rs.getString("nombre"));
				c.setApellido_Cl(rs.getString("apellido"));
				c.setSexo_Cl(rs.getString("sexo"));
				n.setDescripcion_N(rs.getString("nacionalidad"));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(rs.getString("direccion"));
				l.setDescripcion_L(rs.getString("localidad"));
				c.setCodLoc_Cl(l);
				p.setDescripcion_P(rs.getString("provincia"));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(rs.getString("correo"));
				c.setTelefono_Cl(rs.getString("telefono"));
				u.setNombreUser_U(rs.getString("usuario"));
				u.setContra_U(rs.getString(13));
				c.setUser_contra_Cl(u);
				c.setFechaNacimiento_Cl(rs.getString("fechaNac"));
				c.setEstado_Cl(rs.getBoolean("estado"));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;	
	}

	@Override
	public ArrayList<Cliente> filtrarClientesXnombre_y_apellido(Cliente cliente) {
		ArrayList<Cliente> listaC = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(filtrarClientesXnombre_y_apellido);
			pst.setString(1, cliente.getNombre_Cl());
			pst.setString(2, cliente.getApellido_Cl());
			rs = pst.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				Nacionalidad n = new Nacionalidad();
				Provincia p = new Provincia();
				Localidad l = new Localidad();
				Usuario u = new Usuario();
				c.setDni_Cl(rs.getString("dni"));
				c.setCuil_Cl(rs.getString("cuil"));
				c.setNombre_Cl(rs.getString("nombre"));
				c.setApellido_Cl(rs.getString("apellido"));
				c.setSexo_Cl(rs.getString("sexo"));
				n.setDescripcion_N(rs.getString("nacionalidad"));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(rs.getString("direccion"));
				l.setDescripcion_L(rs.getString("localidad"));
				c.setCodLoc_Cl(l);
				p.setDescripcion_P(rs.getString("provincia"));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(rs.getString("correo"));
				c.setTelefono_Cl(rs.getString("telefono"));
				u.setNombreUser_U(rs.getString("usuario"));
				u.setContra_U(rs.getString(14));
				c.setUser_contra_Cl(u);
				c.setFechaNacimiento_Cl(rs.getString("fechaNac"));
				c.setEstado_Cl(rs.getBoolean("estado"));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;	
	}

	@Override
	public ArrayList<Cliente> darBajaCliente() {
		ArrayList<Cliente> listaC = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(darBajaCliente);
			rs = pst.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setDni_Cl(rs.getString("DNI"));
				c.setCuil_Cl(rs.getString("CUIL"));
				c.setNombre_Cl(rs.getString("NOMBRE"));
				c.setApellido_Cl(rs.getString("APELLIDO"));
				Usuario u = new Usuario();
				u.setNombreUser_U(rs.getString("USUARIO"));
				c.setUser_contra_Cl(u);
				listaC.add(c);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listaC;
	}

	@Override
	public boolean clienteActivo(Cliente cliente) {
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
			pst = cn.prepareStatement(dadoBaja);
			pst.setString(1, cliente.getDni_Cl());
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
	public ArrayList<Cliente> filtrarClienteXdniModificar(Cliente cliente) {
		ArrayList<Cliente> listaC = new ArrayList<Cliente>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(filtrarClienteXdniModificar);
			pst.setString(1, cliente.getDni_Cl());
			rs = pst.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				Nacionalidad n = new Nacionalidad();
				Provincia p = new Provincia();
				Localidad l = new Localidad();
				Usuario u = new Usuario();
				c.setDni_Cl(rs.getString("dni_Cl"));
				c.setCuil_Cl(rs.getString("cuil_Cl"));
				c.setNombre_Cl(rs.getString("nombre_Cl"));
				c.setApellido_Cl(rs.getString("apellido_Cl"));
				c.setSexo_Cl(rs.getString("sexo_Cl"));
				n.setCodNacionalidad_N(rs.getInt("codNacionalidad_Cl"));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(rs.getString("direccion_Cl"));
				l.setCodLoc_L(rs.getInt("codLoc_Cl"));
				c.setCodLoc_Cl(l);
				p.setCodProv_P(rs.getInt("codProv_Cl"));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(rs.getString("correoElectronico_Cl"));
				c.setTelefono_Cl(rs.getString("telefono_Cl"));
				u.setNombreUser_U(rs.getString("usuario_Cl"));
				u.setContra_U(rs.getString("contra_Cl"));
				c.setUser_contra_Cl(u);
				//c.setFechaNacimiento_Cl(rs.getString("fechaNacimiento_Cl"));
				
				Timestamp fecha = rs.getTimestamp("fechaNacimiento_Cl");
				LocalDate fechaConvertida = fecha.toLocalDateTime().toLocalDate(); //SOLO QUEREMOS LA FECHA SIN LA HORA
                String fechaHTML = fechaConvertida.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); //EN EL FORMATO AÑO - MES- DIA
                c.setFechaNacimiento_Cl(fechaHTML);
                
				c.setEstado_Cl(rs.getBoolean("estado_Cl"));
				listaC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listaC;	
	}

	@Override
	public boolean clienteActivoPorCBUCuenta(int cbu) {
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
			pst = cn.prepareStatement(buscarClienteXCBUCuenta);
			pst.setInt(1, cbu);
			rs = pst.executeQuery();
			if(rs.next()) {
				if (rs.getInt(1) > 0) {
					System.out.println(rs.getInt(1));
					resultado = true;
					}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean existeCuilCliente(Cliente cliente) {
		boolean estado = false;
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = cn.prepareStatement(buscarCuilCliente);
			pst.setString(1, cliente.getCuil_Cl());
			rs = pst.executeQuery();
			if(rs.isBeforeFirst()) estado = true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return estado;
	}
	

}