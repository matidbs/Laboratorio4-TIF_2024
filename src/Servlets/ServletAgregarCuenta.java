package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.tipoDeCuenta;
import negocio.clientesNegocio;
import negocio.cuentasNegocio;
import negocio.tipoDeCuentaNegocio;
import negocioImpl.clientesNegocioImpl;
import negocioImpl.cuentasNegocioImpl;
import negocioImpl.tipoDeCuentaNegocioImpl;

/**
 * Servlet implementation class ServletAgregarCuenta
 */
@WebServlet("/ServletAgregarCuenta")
public class ServletAgregarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cuenta cuenta;
	private cuentasNegocio cuNeg = new cuentasNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAgregarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param") != null) {			
			//TipoDeCuenta
			ArrayList<tipoDeCuenta> lista;
			tipoDeCuentaNegocio tCN = new tipoDeCuentaNegocioImpl();
			lista = (ArrayList<tipoDeCuenta>) tCN.listar();
			
			request.setAttribute("TipoDeCuenta", lista);
			
		}
				
		RequestDispatcher rd=request.getRequestDispatcher("/AgregarCuenta.jsp");
		rd.forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAgregar") != null) {
			clientesNegocio cNeg = new clientesNegocioImpl();
			Cliente cl = new Cliente();
            cl.setDni_Cl(request.getParameter("txtDni"));
            cuenta = new Cuenta();

            // Preguntar si el DNI ingresado exist
            if (cNeg.existeCliente(cl)) {
            	            	
                cuenta.setDniCliente_CU(cl.getDni_Cl());

                int codNacionalidad = Integer.parseInt(request.getParameter("tipoCuenta"));               
                
                tipoDeCuenta tc = new tipoDeCuenta();
                tc.setCodTipoCuenta_Tcu(codNacionalidad);
                
                cuenta.setTipoCuenta_CU(tc);    

                Cuenta cuentaNueva = new Cuenta();
                cuentaNueva = cuNeg.Agregar(cuenta);

                if(cuentaNueva.getDniCliente_CU() != "-1")
                {
                	//busco los datos de esta cuentanueva generada y lo guardo en cuenta               	
                	cuenta = cuNeg.listarCuentaCBU(cuentaNueva).get(0);
                }
                else 
                {
                	//se le pone -1 porque el cliente existe pero ya tiene 3 cuentas asignadas
                	cuenta.setDniCliente_CU("-1");
                }
               
                request.setAttribute("datoscuenta", cuenta);
            } 
            else 
            { 
              request.setAttribute("NoExiste", 1);
            }
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("/AgregarCuenta.jsp");
		rd.forward(request, response);

	}

}
