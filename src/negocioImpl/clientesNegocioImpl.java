package negocioImpl;

import java.util.ArrayList;

import dao.clientesDao;
import daoImpl.clientesDaoImpl;
import entidad.Cliente;
import entidad.Usuario;
import negocio.clientesNegocio;

public class clientesNegocioImpl implements clientesNegocio {

	clientesDao dao = new clientesDaoImpl();
	
	@Override
	public boolean Agregar(Cliente cliente) {
		return dao.Agregar(cliente);
	}

	@Override
	public boolean Modificar(Cliente cliente) {
		return dao.Modificar(cliente);
	}

	@Override
	public boolean Eliminar(Cliente cliente) {
		return dao.Eliminar(cliente);
	}
	
	@Override
	public ArrayList<Cliente> listarClientes() {
		return (ArrayList<Cliente>) dao.listarClientes();
	}

	@Override
	public ArrayList<Cliente> filtrarClienteXdni(Cliente cliente) {
		return (ArrayList<Cliente>)dao.filtrarClienteXdni(cliente);
	}

	@Override
	public boolean existeCliente(Cliente cliente) {
		return dao.existeCliente(cliente);
	}

	@Override
	public boolean existeClienteUsuario(Cliente cliente) {
		return dao.existeClienteUsuario(cliente);
	}

	@Override
	public Cliente existeClienteUsuarioYContra(Usuario usuario) {
		return dao.existeClienteUsuarioYContra(usuario);
	}

	@Override
	public ArrayList<Cliente> filtrarClientesXedad(String edad) {
		return (ArrayList<Cliente>)dao.filtrarClientesXedad(edad);
	}

	@Override
	public ArrayList<Cliente> filtrarClientesXnombre(Cliente cliente) {
		return (ArrayList<Cliente>) dao.filtrarClientesXnombre(cliente);
	}

	@Override
	public ArrayList<Cliente> filtrarClientesXapellido(Cliente cliente) {
		return (ArrayList<Cliente>) dao.filtrarClientesXapellido(cliente);
	}

	@Override
	public ArrayList<Cliente> filtrarClientesXnombre_y_apellido(Cliente cliente) {
		return (ArrayList<Cliente>) dao.filtrarClientesXnombre_y_apellido(cliente);
	}

	@Override
	public ArrayList<Cliente> darBajaCliente() {
		return (ArrayList<Cliente>) dao.darBajaCliente();
	}

	@Override
	public boolean todosLosCamposCompletos(Cliente cliente) {
		boolean completo = false;

		if(cliente.getDni_Cl() != "" && cliente.getCuil_Cl() != ""
				&& cliente.getNombre_Cl() != "" && cliente.getApellido_Cl() != ""
				&& cliente.getSexo_Cl() != "" && cliente.getCodNacionalidad_Cl() != null 
				&& cliente.getCodProv_Cl() != null && cliente.getCodLoc_Cl() != null 
				&& cliente.getDireccion_Cl() != "" && cliente.getCorreoElectronico_Cl() != ""
				&& cliente.getTelefono_Cl() != "" && cliente.getFechaNacimiento_Cl() != ""
				&& cliente.getUser_contra_Cl().getNombreUser_U() != ""
				&& cliente.getUser_contra_Cl().getContra_U() != "") completo = true;
			
		return completo;
	}

	@Override
	public boolean clienteActivo(Cliente cliente) {
		return dao.clienteActivo(cliente);
	}

	@Override
	public ArrayList<Cliente> filtrarClienteXdniModificar(Cliente cliente) {
		return dao.filtrarClienteXdniModificar(cliente);
	}

	@Override
	public boolean clienteActivoPorCBUCuenta(int cbu) {
		return dao.clienteActivoPorCBUCuenta(cbu);
	}

	@Override
	public boolean existeCuilCliente(Cliente cliente) {
		return dao.existeCuilCliente(cliente);
	}


}
