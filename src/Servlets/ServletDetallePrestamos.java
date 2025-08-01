package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.DetallePrestamo;
import exceptions.ValorMinimo;
import negocio.detallePrestamosNegocio;
import negocioImpl.detallePrestamosNegocioImpl;

/**
 * Servlet implementation class ServletDetallePrestamos
 */
@WebServlet("/ServletDetallePrestamos")
public class ServletDetallePrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	detallePrestamosNegocio dtoNeg = new detallePrestamosNegocioImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDetallePrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CARGAR TABLA CON TODOS LOS PRESTAMOS AUTORIZADOS (PrestamosAutorizados.jsp)
				if(request.getParameter("autorizados") != null) {
					ArrayList<DetallePrestamo> lista = new ArrayList<DetallePrestamo>();
					lista = dtoNeg.listaCuotas();
					
					request.setAttribute("listaAutorizadosSinFiltros", lista);
					RequestDispatcher rd =request.getRequestDispatcher("/PrestamosAutorizados.jsp");
					rd.forward(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnFiltrarDni") != null) {
			ArrayList<DetallePrestamo> lista = new ArrayList<DetallePrestamo>();
			lista = dtoNeg.listaCuotas_DNI(request.getParameter("txtFiltrarDni"));
			
			
			request.setAttribute("listaAutorizadosFiltrado", lista);
			RequestDispatcher rd =request.getRequestDispatcher("/PrestamosAutorizados.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarNombreyApellido") != null) {
			
			String nombre = request.getParameter("txtFiltrarNombre").toString();
			String apellido = request.getParameter("txtFiltrarApellido").toString();
			
			String nombreCompleto =  nombre.trim()+ " " +apellido.trim();			
			DetallePrestamo detallePrestamo = new DetallePrestamo();
			detallePrestamo.setNombreCliente(nombreCompleto);
			ArrayList<DetallePrestamo> lista = new ArrayList<DetallePrestamo>();
			lista = dtoNeg.listaCuotas_NOMBREyAPELLIDO(detallePrestamo);
			
			
			request.setAttribute("listaAutorizadosFiltrado", lista);
			RequestDispatcher rd =request.getRequestDispatcher("/PrestamosAutorizados.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarMonto") != null) {
			ArrayList<DetallePrestamo> lista = new ArrayList<DetallePrestamo>();
			float min =  Float.parseFloat(request.getParameter("txtFiltrarMontoMIN"));
			float max = Float.parseFloat(request.getParameter("txtFiltrarMontoMAX"));
			String mensajeError=null;
			
			try {
				DetallePrestamo.verificarValorMontos(min, max);				
				lista = dtoNeg.listaCuotas_MONTOCUOTA(min, max);
			} catch (ValorMinimo e) {
				mensajeError = e.getMessage();
			}
			
						
			request.setAttribute("listaAutorizadosFiltrado", lista);
			request.setAttribute("errorFiltradoMontos", mensajeError);
			RequestDispatcher rd =request.getRequestDispatcher("/PrestamosAutorizados.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarfECHA") != null) {
			//puedo hacer un trycatch para ver que min sea menor que max siempre
			
			String min =  (request.getParameter("txtFiltroFechaMin").toString());
			String max = (request.getParameter("txtFiltroFechaMax").toString());
			
			ArrayList<DetallePrestamo> lista = new ArrayList<DetallePrestamo>();
			lista = dtoNeg.listaCuotas_FECHA(min, max);
			
			
			request.setAttribute("listaAutorizadosFiltrado", lista);
			RequestDispatcher rd =request.getRequestDispatcher("/PrestamosAutorizados.jsp");
			rd.forward(request, response);
		}
		
		
		if(request.getParameter("btnMostrarTodos") != null) {
			ArrayList<DetallePrestamo> lista = new ArrayList<DetallePrestamo>();
			lista = dtoNeg.listaCuotas();
			
			
			request.setAttribute("listaAutorizadosSinFiltros", lista);
			RequestDispatcher rd =request.getRequestDispatcher("/PrestamosAutorizados.jsp");
			rd.forward(request, response);
		}
	}

}
