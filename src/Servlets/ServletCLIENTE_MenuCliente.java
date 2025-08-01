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

/**
 * Servlet implementation class ServletCLIENTE_MenuCliente
 */
@WebServlet("/ServletCLIENTE_MenuCliente")
public class ServletCLIENTE_MenuCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCLIENTE_MenuCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param") != null)
		{
			cuentasNegocio cneg = new cuentasNegocioImpl();
			ArrayList<Cuenta> lista;
			
			//uso el sesionDni 
			Cuenta x = new Cuenta();
			x.setDniCliente_CU(request.getSession().getAttribute("sesionDNI").toString());
			lista = cneg.listarCuentasDni(x);
			
			request.setAttribute("ListaCuentas", lista);
			RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_MenuCliente.jsp");
			rd.forward(request, response);
		}
		
		
		if(request.getParameter("Datos") != null) 
		{
			//traigo los datos del cliente
			clientesNegocio clNeg = new clientesNegocioImpl();
			Cliente cliente = new Cliente();
			cliente.setDni_Cl(request.getSession().getAttribute("sesionDNI").toString());
					
			ArrayList<Cliente> lista;
			lista = clNeg.filtrarClienteXdni(cliente);
			
			//me fijo cuantas cuentas tiene este cliente
			cuentasNegocio cuNeg = new cuentasNegocioImpl();
			Cuenta x = new Cuenta();
			x.setDniCliente_CU(cliente.getDni_Cl());
			int cantidad = cuNeg.ContarCuentasDni(x);
			
			request.setAttribute("CantidadCuentas", cantidad);
			request.setAttribute("ListaDatos", lista);
			RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_DatosCliente.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
