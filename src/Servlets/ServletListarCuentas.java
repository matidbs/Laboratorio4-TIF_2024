package Servlets;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ServletListarCuentas
 */
@WebServlet("/ServletListarCuentas")
public class ServletListarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cuenta cuenta;
	private cuentasNegocio cuNeg = new cuentasNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		
		if(request.getParameter("btnBuscarCuenta") != null) {
			cuenta = new Cuenta();
			int nroCuenta = Integer.parseInt(request.getParameter("txtNroCuenta"));
			cuenta.setNroCuenta_CU(nroCuenta);
			
			listaCuentas = cuNeg.listarCuentaNroCuenta(cuenta);
			
			request.setAttribute("cuentaPorNro", listaCuentas);
		}
		
		if(request.getParameter("btnBuscarDni") != null) {
			cuenta = new Cuenta();
			cuenta.setDniCliente_CU(request.getParameter("txtBuscarDni"));
			
			listaCuentas = cuNeg.listarCuentasDni(cuenta);
			request.setAttribute("cuentaPorDni", listaCuentas);
		}
		
		if(request.getParameter("btnMostrarTodos") != null) {
			listaCuentas = cuNeg.listarCuentasActivas();
			
			request.setAttribute("listaCuentas", listaCuentas);
		}
		
		if(request.getParameter("btnMostrarTodosInactivos") != null) {
			listaCuentas = cuNeg.listarCuentasInactivas();
			
			request.setAttribute("listaCuentas", listaCuentas);
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("/ListarCuentas.jsp");
		rd.forward(request, response);
		doGet(request, response);
	}

}
