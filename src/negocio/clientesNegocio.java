package negocio;

import java.lang.reflect.Array;
import java.util.ArrayList;

import entidad.Cliente;
import entidad.Usuario;

public interface clientesNegocio {

	public boolean Agregar(Cliente cliente);
	public boolean Modificar(Cliente cliente);
	public boolean Eliminar(Cliente cliente);	
	public ArrayList<Cliente> listarClientes();
	public ArrayList<Cliente> darBajaCliente();
	public ArrayList<Cliente> filtrarClienteXdni(Cliente cliente);
	public ArrayList<Cliente> filtrarClienteXdniModificar(Cliente cliente);
	public ArrayList<Cliente> filtrarClientesXedad(String edad);
	public ArrayList<Cliente> filtrarClientesXnombre(Cliente cliente);
	public ArrayList<Cliente> filtrarClientesXapellido(Cliente cliente);
	public ArrayList<Cliente> filtrarClientesXnombre_y_apellido(Cliente cliente);
	public boolean existeCliente(Cliente cliente); //Busca por DNI
	public boolean clienteActivo(Cliente cliente);
	public boolean clienteActivoPorCBUCuenta(int cbu);
	public boolean existeClienteUsuario(Cliente cliente);
	public Cliente existeClienteUsuarioYContra(Usuario usuario); //Busca si existe el nombre de usuario y contrase�
	public boolean existeCuilCliente(Cliente cliente);

	
	public boolean todosLosCamposCompletos(Cliente cliente);
}