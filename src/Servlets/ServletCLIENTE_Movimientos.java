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
import entidad.Movimiento;
import entidad.tipoDeMovimiento;
import negocio.movimientosNegocio;
import negocio.tipoDeMovimientoNegocio;
import negocioImpl.movimientosNegocioImpl;
import negocioImpl.tipoDeCuentaNegocioImpl;
import negocioImpl.tipoDeMovimientoNegocioImpl;

/**
 * Servlet implementation class ServletCLIENTE_Movimientos
 */
@WebServlet("/ServletCLIENTE_Movimientos")
public class ServletCLIENTE_Movimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	movimientosNegocio mNeg;
	Cuenta cuenta;
	ArrayList<Movimiento> lista = new ArrayList<Movimiento>();
	ArrayList<tipoDeMovimiento> listaTipo = new ArrayList<tipoDeMovimiento>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCLIENTE_Movimientos() {
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
		
		if(request.getParameter("btnVerMovimientos") != null)
		{
			cuenta = new Cuenta();
			int numeroCuenta = Integer.parseInt(request.getParameter("nroCuenta"));
			
			cuenta.setNroCuenta_CU(numeroCuenta);
			mNeg = new movimientosNegocioImpl();
			
			lista = mNeg.listarMovimientos(cuenta);
			
			tipoDeMovimientoNegocio tmNeg = new tipoDeMovimientoNegocioImpl();
			
			listaTipo = tmNeg.listar();
			
			request.setAttribute("ListaTipos", listaTipo);
			request.setAttribute("Movimientos", lista);
			request.setAttribute("numCuenta", numeroCuenta);
			RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_Movimientos.jsp");
			rd.forward(request, response);
		}
		// FILTRAR POR TIPO DE MOVIMIENTO
		if(request.getParameter("btnFiltrar") != null) {
			int numeroCuenta = Integer.parseInt(request.getParameter("numCuenta"));
			
			cuenta = new Cuenta();
			cuenta.setNroCuenta_CU(numeroCuenta);
			mNeg = new movimientosNegocioImpl();
			
			int codTipo = Integer.parseInt(request.getParameter("seleccionarTipo"));
			lista = mNeg.filtrarMovimientosTipoMov(codTipo, cuenta);
			
			tipoDeMovimientoNegocio tmNeg = new tipoDeMovimientoNegocioImpl();
			listaTipo = tmNeg.listar();
			
			request.setAttribute("ListaTipos", listaTipo);
			request.setAttribute("Movimientos", lista);
			request.setAttribute("numCuenta", numeroCuenta);
			RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_Movimientos.jsp");
			rd.forward(request, response);
		}
		
		// MOSTRAR TODOS LOS MOVIMIENTOS
		if(request.getParameter("btnMostrarTodos") != null) {
			int numeroCuenta = Integer.parseInt(request.getParameter("numCuenta"));

			cuenta = new Cuenta();
			cuenta.setNroCuenta_CU(numeroCuenta);
			
			mNeg = new movimientosNegocioImpl();
			lista = mNeg.listarMovimientos(cuenta);
			
			tipoDeMovimientoNegocio tmNeg = new tipoDeMovimientoNegocioImpl();
			
			listaTipo = tmNeg.listar();
			
			request.setAttribute("ListaTipos", listaTipo);
			request.setAttribute("Movimientos", lista);
			request.setAttribute("numCuenta", numeroCuenta);
			RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_Movimientos.jsp");
			rd.forward(request, response);
		}
	}

}
