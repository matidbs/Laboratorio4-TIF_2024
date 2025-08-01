package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Usuario;
import negocio.administradorNegocio;
import negocio.clientesNegocio;
import negocioImpl.administradorNegocioImpl;
import negocioImpl.clientesNegocioImpl;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnIngresar") != null) {
			
			Usuario u = new Usuario();
			u.setNombreUser_U(request.getParameter("txtUsuario"));
			u.setContra_U(request.getParameter("txtContrasena"));
			
			
			clientesNegocio cNeg = new clientesNegocioImpl();
			administradorNegocio aNeg = new administradorNegocioImpl();
			Cliente x = new Cliente();
			x = cNeg.existeClienteUsuarioYContra(u);
			
			if(x.getDni_Cl() != "")  //si el dni no está vacío es porque existe el cliente
			{
				boolean estadoDelCliente = x.isEstado_Cl();
				if(estadoDelCliente) 
				{
					//crea session cliente
					String nombreYapellido = x.getNombre_Cl() + ", " + x.getApellido_Cl();
					request.getSession().setAttribute("sesion",nombreYapellido);				
					
					//creo otra session para guardarme el dni del cliente
					request.getSession().setAttribute("sesionDNI",x.getDni_Cl());

					RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_MenuCliente.jsp");
					rd.forward(request, response);
				}
				else 
				{	
					request.setAttribute("inactivo", true);
					RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
					rd.forward(request, response);
					
				}			
			}else //si da false es porque el usuario de tipo cliente no existe, se fija si existe el admin
			{
				if(aNeg.existeAdministradorUsuario(u)) 
				{
					//crea session admin
					request.getSession().setAttribute("sesion",u.getNombreUser_U());
					RequestDispatcher rd=request.getRequestDispatcher("/MenuAdmin.jsp");
					rd.forward(request, response);
					
				}else 
				{	
					request.setAttribute("verificar", false);
					RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
					rd.forward(request, response);
					
				}
								
			}
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnSalir") != null) 
		{
			if(request.getSession().getAttribute("sesionDNI") !=null)
			{
				request.getSession().removeAttribute("sesionDNI");
			}
			
			request.getSession().removeAttribute("sesion");
			RequestDispatcher rd=request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}		
		
	}
}
