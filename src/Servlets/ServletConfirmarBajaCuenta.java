package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import negocio.cuentasNegocio;
import negocioImpl.cuentasNegocioImpl;

/**
 * Servlet implementation class ServletConfirmarBajaCuenta
 */
@WebServlet("/ServletConfirmarBajaCuenta")
public class ServletConfirmarBajaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private cuentasNegocio cuNeg = new cuentasNegocioImpl();
    private Cuenta cuenta;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConfirmarBajaCuenta() {
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
			cuenta = new Cuenta();
			int nroCuenta = Integer.parseInt(request.getSession().getAttribute("Cuenta").toString());
			cuenta.setNroCuenta_CU(nroCuenta);
			boolean elimino = cuNeg.Eliminar(cuenta);
			request.setAttribute("Elimino", elimino);
			
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCuenta.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnCancelar") != null) {
			
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCuenta.jsp");
			rd.forward(request, response);
			
		}
		
	}

}
