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

@WebServlet("/ServletDarBajaCuenta")
public class ServletDarBajaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private cuentasNegocio cuNeg = new cuentasNegocioImpl();
    private Cuenta cuenta;

    public ServletDarBajaCuenta() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(request.getParameter("btnBuscarDni") != null) {
			cuenta = new Cuenta();
			ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
			request.setAttribute("listaCuentas", listaCuentas);
			cuenta.setDniCliente_CU(request.getParameter("txtDni"));	
			listaCuentas = cuNeg.listarCuentasDni(cuenta);
			request.setAttribute("listaCuentasDNI", listaCuentas);
			
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCuenta.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnBuscarNumeroCuenta") != null) {
			cuenta = new Cuenta();
			ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
			int nroCuenta = Integer.parseInt(request.getParameter("txtNumeroCuenta"));			
			cuenta.setNroCuenta_CU(nroCuenta);
			listaCuentas = cuNeg.listarCuentaNroCuenta(cuenta);
			request.setAttribute("listaCuentasNroCliente", listaCuentas);
			
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCuenta.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnDarBaja") != null) {
			
			RequestDispatcher rd=request.getRequestDispatcher("/ConfirmarBajaCuenta.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnConfirmar") != null) {
			cuenta = new Cuenta();
			
			int nroCuenta = Integer.parseInt(request.getSession().getAttribute("Cuenta").toString());
			cuenta.setNroCuenta_CU(nroCuenta);
			request.getSession().removeAttribute("Cuenta");
			boolean elimino = cuNeg.Eliminar(cuenta);
			request.setAttribute("Elimino", elimino);
			
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCuenta.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("btnCancelar") != null) {
			
			RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCuenta.jsp");
			rd.forward(request, response);
			
		}
		
		doGet(request, response);
	}

}
