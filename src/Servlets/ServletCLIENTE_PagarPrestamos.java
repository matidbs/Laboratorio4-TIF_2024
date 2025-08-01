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
import entidad.DetallePrestamo;
import negocio.cuentasNegocio;
import negocio.detallePrestamosNegocio;
import negocioImpl.cuentasNegocioImpl;
import negocioImpl.detallePrestamosNegocioImpl;

/**
 * Servlet implementation class ServletCLIENTE_PagarPrestamos
 */
@WebServlet("/ServletCLIENTE_PagarPrestamos")
public class ServletCLIENTE_PagarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cuenta cuenta;
	private cuentasNegocio cuNeg = new cuentasNegocioImpl();
	private detallePrestamosNegocio dpNeg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCLIENTE_PagarPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param") != null) {
			ArrayList<Cuenta> listaCuentas; 
			cuenta = new Cuenta();
			cuenta.setDniCliente_CU(request.getSession().getAttribute("sesionDNI").toString());
			listaCuentas = cuNeg.listarCuentasDni(cuenta);
			
			ArrayList<DetallePrestamo> listaDtoPrestamos; 
			dpNeg = new detallePrestamosNegocioImpl();
			listaDtoPrestamos = dpNeg.listaCuotas_DniLadoCliente(request.getSession().getAttribute("sesionDNI").toString());
			
			
			request.setAttribute("cuentasCliente", listaCuentas);
			request.setAttribute("dtoPrestamosCliente", listaDtoPrestamos);
			RequestDispatcher rd =request.getRequestDispatcher("/CLIENTE_PagarPrestamos.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnPagar") != null) {
			dpNeg = new detallePrestamosNegocioImpl();
			
			// VERIFICA QUE SE HAYA SELECCIONADO UNA CUENTA
			if(request.getParameter("seleccionarCuenta") == null || request.getParameter("seleccionarCuenta").equals("disabled")) {
				request.setAttribute("cuentaSeleccionada", false);
				RequestDispatcher rd =request.getRequestDispatcher("/CLIENTE_PagarPrestamos.jsp");
				rd.forward(request, response);
			}
			
			cuenta.setNroCuenta_CU(Integer.parseInt(request.getParameter("seleccionarCuenta")));
			ArrayList<Cuenta> listaCuentas = cuNeg.listarCuentaNroCuenta(cuenta);
			
			// VERIFICA SI TENGO SALDO PARA PAGAR LA CUOTA
			float montoCuota = Float.parseFloat(request.getParameter("montoCuota"));
			if(listaCuentas.get(0).getSaldo_CU() < montoCuota) {
				request.setAttribute("saldoInsuficiente", false);
				RequestDispatcher rd =request.getRequestDispatcher("/CLIENTE_PagarPrestamos.jsp");
				rd.forward(request, response);
			}else {
			//in pnroCuentaPago int, in pnroPrestamo int,in pnroCuota int, in pmontoCuota float
			//TENGO SALGO, GENERO EL PAGO DE LA CUOTA
			DetallePrestamo dp = new DetallePrestamo();
			dp.setNroCuentaPago_DP(Integer.parseInt(request.getParameter("seleccionarCuenta")));
			dp.setNroPrestamo_DP(Integer.parseInt(request.getParameter("nroPrestamo")));
			dp.setNroCuota_DP(Integer.parseInt(request.getParameter("nroCuota")));
			dp.setMontoCuota_DP(montoCuota);
			boolean exito = false;
			exito = dpNeg.pagarCuotaPrestamo(dp);
			
			request.setAttribute("pagoExitoso", exito);
			RequestDispatcher rd =request.getRequestDispatcher("/CLIENTE_PagarPrestamos.jsp");
			rd.forward(request, response);}
		}
	}

}
