package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import entidad.Cliente;
import negocio.clientesNegocio;
import negocioImpl.clientesNegocioImpl;

/**
 * Servlet implementation class ServletConfirmarBajaCliente
 */
@WebServlet("/ServletConfirmarBajaCliente")
public class ServletConfirmarBajaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	clientesNegocio neg = new clientesNegocioImpl();
	Cliente c;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConfirmarBajaCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnConfirmar") != null) {
			
			c = new Cliente();
			String dni = request.getSession().getAttribute("DNI").toString();
			c.setDni_Cl(dni);
			
			boolean estado = neg.Eliminar(c);
			request.setAttribute("dadoBaja", estado);
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCliente.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnCancelar") != null) {
		
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCliente.jsp");
			rd.forward(request, response);
			
		}
	}

}
