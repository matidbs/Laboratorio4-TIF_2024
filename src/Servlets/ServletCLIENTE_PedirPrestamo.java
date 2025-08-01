package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Array;

import dao.prestamosDao;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.cuentasNegocio;
import negocio.prestamosNegocio;
import negocioImpl.cuentasNegocioImpl;
import negocioImpl.prestamosNegocioImpl;

/**
 * Servlet implementation class ServletCLIENTE_PedirPrestamo
 */
@WebServlet("/ServletCLIENTE_PedirPrestamo")
public class ServletCLIENTE_PedirPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private cuentasNegocio cuNeg = new cuentasNegocioImpl();
	private Cuenta cuenta;
       

    public ServletCLIENTE_PedirPrestamo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Cargo las cuentas al descolgable
		if(request.getParameter("Param") != null) {

			//Cargar cuentas
			ArrayList<Cuenta> listaCuentas; 
			cuenta = new Cuenta();
			cuenta.setDniCliente_CU(request.getSession().getAttribute("sesionDNI").toString());
			listaCuentas = cuNeg.listarCuentasDni(cuenta);
			

			request.setAttribute("cuentasCliente", listaCuentas);
		}

		RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_PedirPrestamo.jsp");
		rd.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnSolicitar") != null) {
			//Verifica si seleccionó una opción 
			if(request.getParameter("cuotasPrestamo") != null && request.getParameter("cuentasCliente") != null) {
				Prestamo prestamo = new Prestamo();
				prestamosNegocio pNeg = new prestamosNegocioImpl();
				
				//Casteo de parametros
				int nroCuentaS = Integer.parseInt(request.getParameter("cuentasCliente"));
				int cantCuotas = Integer.parseInt(request.getParameter("cuotasPrestamo"));
				float montoSolicitado = Float.parseFloat(request.getParameter("txtImporte"));
			
				//Seteo
				prestamo.setDniCliente_P(request.getSession().getAttribute("sesionDNI").toString());
				prestamo.setNroCuentaSolicitante_P(nroCuentaS);
				prestamo.setCantidadCuotas_P(cantCuotas);
				prestamo.setMontoSolicitado_P(montoSolicitado);
				
				/*El monto a pagar se calcula directamente desde el SP en MySQL. 
				Se calcula con: montoSolicitado * 2 
				*/
				
				Prestamo p = pNeg.agregarPrestamo(prestamo);
				/* 'p' trae el nroPrestamo para listar */
				prestamo = pNeg.listarPrestamoNumero(p);
								
				request.setAttribute("Prestamo", prestamo);
			} else {
				request.setAttribute("NoCompleto", false);
			}
			
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_PedirPrestamo.jsp");
		rd.forward(request, response);
		
		//doGet(request, response);
	}

}
