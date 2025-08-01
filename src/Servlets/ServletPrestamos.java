package Servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.glass.ui.Pixels.Format;


import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import negocio.clientesNegocio;
import negocio.cuentasNegocio;
import negocio.prestamosNegocio;
import negocioImpl.clientesNegocioImpl;
import negocioImpl.cuentasNegocioImpl;
import negocioImpl.prestamosNegocioImpl;

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	prestamosNegocio pNeg = new prestamosNegocioImpl();
	cuentasNegocio cNeg = new cuentasNegocioImpl();
	clientesNegocio clNeg = new clientesNegocioImpl();
       
    public ServletPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CARGAR TABLA CON TODAS LAS SOLICITUDES
		if(request.getParameter("Param") != null) {
			ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
			lista = pNeg.listarSolicitudes();
			
			request.setAttribute("listaSolicitudes", lista);
			RequestDispatcher rd =request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnMostrarTodos") != null) {
			ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
			lista = pNeg.listarSolicitudes();
			
			request.setAttribute("listaSolicitudes", lista);
			RequestDispatcher rd =request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
			rd.forward(request, response);
		}
		
		
		// CARGAR SELECT CUENTAS		
		if(request.getParameter("btnCargarCuentas") != null) {
			ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();

			Cliente cliente = new Cliente();
			cliente.setDni_Cl(request.getParameter("txtFiltrarCuentasPrestamos"));
			
			boolean resultado = false;
			if(clNeg.existeCliente(cliente)) {
				Cuenta cuenta = new Cuenta();
				cuenta.setDniCliente_CU(request.getParameter("txtFiltrarCuentasPrestamos"));
				listaCuentas = cNeg.listarCuentasDni(cuenta);
				resultado = true;
			}
			
			request.setAttribute("listaCuentas", listaCuentas);
			request.setAttribute("existeDni", resultado);
			RequestDispatcher rd =request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
			rd.forward(request, response);
		}
		
		// FILTRAR SOLICITUDES POR CUENTA SELECCIONADA
		if(request.getParameter("btnFiltrarCuentas") != null) {
			// Si no se seleccionó una cuenta
			if(request.getParameter("filtrarCuentas") == null || request.getParameter("filtrarCuentas").equals("disabled")) {
				request.setAttribute("cuentaSeleccionada", false);
				RequestDispatcher rd =request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
				rd.forward(request, response);
			}
			ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
			int nroCuenta = Integer.parseInt(request.getParameter("filtrarCuentas"));
			lista = pNeg.listarPrestamosFiltradosCuenta(nroCuenta);
							
			request.setAttribute("listaSolicitudes", lista);
			RequestDispatcher rd =request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
			rd.forward(request, response);
		}
		
		// FILTRAR POR RANGO DE MONTO
		if(request.getParameter("btnFiltrarMontos") != null) {
			ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
			float superior = Float.parseFloat(request.getParameter("montoSuperior"));
			float inferior = Float.parseFloat(request.getParameter("montoInferior"));
			if(superior > inferior) {
				request.setAttribute("filtrarMonto", false);
				RequestDispatcher rd =request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
				rd.forward(request, response);
			}else {
				lista = pNeg.listarPrestamosMonto(superior, inferior);
			
				request.setAttribute("listaSolicitudes", lista);
				RequestDispatcher rd =request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ACEPTAR PRESTAMO
		if(request.getParameter("btnAceptar") != null) {
			String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			
			Prestamo prestamo = new Prestamo();
			int nroPrestamo = Integer.parseInt(request.getParameter("nroPrestamo").toString());
			prestamo.setNroPrestamo_P(nroPrestamo);
			prestamo.setFechaAltaPrestamo_P(fechaActual);
			prestamo.setNroCuentaSolicitante_P(Integer.parseInt(request.getParameter("nroCuentaSolicitante")));
			prestamo.setCantidadCuotas_P(Integer.parseInt(request.getParameter("cantidadCuotas")));
			prestamo.setMontoSolicitado_P(Float.parseFloat(request.getParameter("montoSolicitado")));
			prestamo.setMontoTotalAPagar_P(Float.parseFloat(request.getParameter("montoTotalAPagar")));
									
			boolean resultado = false;
			if(pNeg.aceptarPrestamo(prestamo)) {resultado=true;}
			
			if(resultado) {
				// VUELVO A CARGAR LA TABLA
				ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
				lista = pNeg.listarSolicitudes();
				
				request.setAttribute("listaSolicitudes", lista);
			}
			request.setAttribute("prestamoAceptado", resultado);
			RequestDispatcher rd =request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
			rd.forward(request, response);
		}
		
		// RECHAZAR PRESTAMO
		if(request.getParameter("btnRechazar") != null) {
			String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			Prestamo prestamo = new Prestamo();
			int nroPrestamo = Integer.parseInt(request.getParameter("nroPrestamo").toString());
			prestamo.setNroPrestamo_P(nroPrestamo);
			prestamo.setFechaAltaPrestamo_P(fechaActual);
			
			boolean resultado = false;
			if(pNeg.rechazarPrestamo(prestamo)) {resultado=true;}
			
			if(resultado) {
				// VUELVO A CARGAR LA TABLA
				ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
				lista = pNeg.listarSolicitudes();
				
				request.setAttribute("listaSolicitudes", lista);
			}
			request.setAttribute("prestamoRechazado", resultado);
			RequestDispatcher rd =request.getRequestDispatcher("/SolicitudesPrestamos.jsp");
			rd.forward(request, response);
		}
	}

}
