package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import negocio.clientesNegocio;
import negocioImpl.clientesNegocioImpl;

@WebServlet("/ServletListarClientes")
public class ServletListarClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	clientesNegocio neg = new clientesNegocioImpl();

    public ServletListarClientes() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("Param") != null) { //BOTÓN 'Cargar tabla'
			
			ArrayList<Cliente> listaClientes = neg.listarClientes();
			
			request.setAttribute("listaClientes", listaClientes);
			RequestDispatcher rd=request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnDni") != null) {
			
			Cliente c = new Cliente();
			c.setDni_Cl(request.getParameter("txtFiltrarDni"));
			
			if(neg.existeCliente(c)) {
				
			ArrayList<Cliente> clienteFiltradoXdni = (ArrayList<Cliente>)neg.filtrarClienteXdni(c);
			request.setAttribute("clienteFiltradoXdni", clienteFiltradoXdni);
			
			}else {
				request.setAttribute("noExisteDni", false);
			}
			
			RequestDispatcher rd=request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnEdad") != null) {
			
			String edad = request.getParameter("txtEdad");
			ArrayList<Cliente> clientesFiltradosXedad = neg.filtrarClientesXedad(edad);
			
			request.setAttribute("clientesFiltradosXedad", clientesFiltradosXedad);
			RequestDispatcher rd=request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
		}
	
		if(request.getParameter("btnBuscar") != null) {
			
			Cliente c = new Cliente();
			String nombre = request.getParameter("txtNombre");
			String apellido = request.getParameter("txtApellido");
			
			if(nombre != null && !nombre.trim().isEmpty() && apellido != null && !apellido.trim().isEmpty()) {
				
				c.setNombre_Cl(nombre);
				c.setApellido_Cl(apellido);
				ArrayList<Cliente> clienteFiltradoXnombre_y_apellido = neg.filtrarClientesXnombre_y_apellido(c);
				request.setAttribute("clienteFiltradoXnombre_y_apellido", clienteFiltradoXnombre_y_apellido);

			} else if(nombre != null && !nombre.trim().isEmpty()) {
				
				c.setNombre_Cl(nombre);
				ArrayList<Cliente> clientesFiltradosXnombre = neg.filtrarClientesXnombre(c);
				request.setAttribute("clientesFiltradosXnombre", clientesFiltradosXnombre);
				
			} else if(apellido != null && !apellido.trim().isEmpty()){
				
				c.setApellido_Cl(apellido);
				ArrayList<Cliente> clientesFiltradosXapellido = neg.filtrarClientesXapellido(c);
				request.setAttribute("clientesFiltradosXapellido", clientesFiltradosXapellido);
				
			}
			
			RequestDispatcher rd=request.getRequestDispatcher("/ListarClientes.jsp");
			rd.forward(request, response);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		
	}
	
	
	}
