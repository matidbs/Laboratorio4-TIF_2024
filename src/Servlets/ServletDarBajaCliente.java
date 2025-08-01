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
import entidad.Cuenta;
import negocio.clientesNegocio;
import negocio.cuentasNegocio;
import negocioImpl.clientesNegocioImpl;
import negocioImpl.cuentasNegocioImpl;


@WebServlet("/ServletDarBajaCliente")
public class ServletDarBajaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	clientesNegocio neg = new clientesNegocioImpl();
	cuentasNegocio cNeg = new cuentasNegocioImpl();
	Cliente c;
	Cuenta cuenta;
	
    public ServletDarBajaCliente() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param") != null) {
			
			ArrayList<Cliente> listaC = neg.darBajaCliente();
			
			request.setAttribute("listaClientes", listaC);
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCliente.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnBuscarDni") != null) {
			
			c = new Cliente();
			c.setDni_Cl(request.getParameter("txtDni"));
			ArrayList<Cliente> listaC = neg.filtrarClienteXdni(c);
			
			request.setAttribute("desplegableDni", listaC);
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCliente.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnDesplegableDni") != null) {
			
			c = new Cliente();
			c.setDni_Cl(request.getParameter("resultadosDNI"));
			ArrayList<Cliente> listaC = neg.filtrarClienteXdni(c);
			
			request.setAttribute("seleccionadoDNI", listaC);
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCliente.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnDarBaja") != null) {
			
			RequestDispatcher rd=request.getRequestDispatcher("/ConfirmarBajaCliente.jsp");
			rd.forward(request, response);
			String dni = request.getParameter("hiddenDniC");
			request.getSession().setAttribute("DNI", dni);
			
		}
		
		if(request.getParameter("btnConfirmar") != null) {
			
			c = new Cliente();
			String dni = request.getSession().getAttribute("DNI").toString();
			c.setDni_Cl(dni);
			
			try { //POR SI EL CLIENTE DADO DE BAJA NO TIENE CUENTAS
				cuenta = new Cuenta();
				cuenta.setDniCliente_CU(dni);
				cNeg.darBajaCuentasCliente(cuenta);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			boolean estado = neg.Eliminar(c);
			request.getSession().removeAttribute("DNI");

			request.setAttribute("dadoBaja", estado);
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCliente.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnCancelar") != null) {
		
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCliente.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnBuscarNombreApellido") != null) {
			
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
			
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnDesplegableNomApe") != null) {
			
			c = new Cliente();
			c.setDni_Cl(request.getParameter("resultadosNombre"));
			ArrayList<Cliente> listaC = neg.filtrarClienteXdni(c);
			
			request.setAttribute("seleccionadoDNI", listaC);
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCliente.jsp");
			rd.forward(request, response);
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		
	}

}
