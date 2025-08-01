package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Prestamo;
import negocio.prestamosNegocio;
import negocioImpl.prestamosNegocioImpl;

/**
 * Servlet implementation class ServletCLIENTE_MisPrestamos
 */
@WebServlet("/ServletCLIENTE_MisPrestamos")
public class ServletCLIENTE_MisPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Prestamo prestamo;
    private prestamosNegocio pNeg = new prestamosNegocioImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCLIENTE_MisPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param") != null) {
			ArrayList<Prestamo> listaP = new ArrayList<Prestamo>();
			
			//Seteo el DNI 
			prestamo = new Prestamo();
			prestamo.setDniCliente_P(request.getSession().getAttribute("sesionDNI").toString());
			
			//Traigo los prestamos del cliente
			listaP = pNeg.listarPrestamosDni(prestamo);
			
			//Seteo el attribute
			request.setAttribute("ListaPrestamos", listaP);
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_MisPrestamos.jsp");
		rd.forward(request, response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
