package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private Connection conector; 
	public static Conexion i; 

	private Conexion()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			this.conector = DriverManager.getConnection(/* Cadena de conexión */);
			this.conector.setAutoCommit(false); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static Conexion getConexion()   
	{								
		if(i == null)
		{
			i = new Conexion(); 
		}
		return i;
	}

	public Connection getSQLConexion() 
	{
		return this.conector; 
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.conector.close(); 
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		i = null; 
	}

}

