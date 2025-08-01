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
 * Servlet implementation class ServletModificarCuenta
 */
@WebServlet("/ServletModificarCuenta")
public class ServletModificarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cuenta cuenta;
	private cuentasNegocio cuNeg = new cuentasNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarCuenta() {
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
		/// BUSCAR CUENTA POR NROCUENTA
		if(request.getParameter("btnSeleccionar") != null) {
			cuenta = new Cuenta();
			int nroCuenta = 0;
			try{
				 nroCuenta = Integer.parseInt(request.getParameter("txtBuscarNroCuenta"));
			}catch(Exception e) {
				request.setAttribute("txtVacio", true);
				RequestDispatcher rd=request.getRequestDispatcher("/ModificarCuenta.jsp");
				rd.forward(request, response);
			}
			
			cuenta.setNroCuenta_CU(nroCuenta);
			if(cuNeg.existeCuenta(cuenta)) {
				listaCuentas = cuNeg.listarCuentaNroCuenta(cuenta);
				cuenta = listaCuentas.get(0);
				
				//genero una session para guardar el numero de cuenta, me servirá para actualizarla cuenta luego de modificarla.
				request.getSession().setAttribute("numeroDeCuenta",nroCuenta);
				
				ArrayList<tipoDeCuenta> lista;
				tipoDeCuentaNegocio tCN = new tipoDeCuentaNegocioImpl();
				lista = (ArrayList<tipoDeCuenta>) tCN.BuscarTipoDeCuentaCompleta(cuenta.getTipoCuenta_CU().getCodTipoCuenta_Tcu());
				
				request.setAttribute("TipoDeCuenta", lista);
				request.setAttribute("cuentaPorNro", listaCuentas);
				RequestDispatcher rd=request.getRequestDispatcher("/ModificarCuenta.jsp");
				rd.forward(request, response);
			}
			request.setAttribute("existeCuenta", false);
			RequestDispatcher rd=request.getRequestDispatcher("/ModificarCuenta.jsp");
			rd.forward(request, response);
		}
		
		
		
		/// MODIFICAR CUENTA EN LA BD
		if(request.getParameter("btnAceptar") != null) {
			//me fijo que el dni que ingresaron exista en la db
			clientesNegocio cNeg = new clientesNegocioImpl();
			Cliente cliente = new Cliente();
			cliente.setDni_Cl(request.getParameter("txtDni"));
			int noExisteDni=0;
			boolean seModifico = false;
			int modificado = 0;
			
			if(cNeg.existeCliente(cliente))
			{
				
				Cuenta cuentaConDatosViejos = new Cuenta();
				Cuenta cuentaConDatosNuevos = new Cuenta();
				
				//uso la session creada arriba para traer los datos de la db.
				if(request.getSession().getAttribute("numeroDeCuenta")!=null) 
				{
					int nroCuenta = Integer.parseInt(request.getSession().getAttribute("numeroDeCuenta").toString());
				
					cuentaConDatosViejos.setNroCuenta_CU(nroCuenta);
					listaCuentas = cuNeg.listarCuentaNroCuenta(cuentaConDatosViejos);
					cuentaConDatosViejos = listaCuentas.get(0);
					
					cuentaConDatosNuevos.setNroCuenta_CU(nroCuenta);
					cuentaConDatosNuevos.setDniCliente_CU(request.getParameter("txtDni"));
					cuentaConDatosNuevos.setFechaCreacion_CU(cuentaConDatosViejos.getFechaCreacion_CU());
					
					tipoDeCuenta tipodecuenta = new tipoDeCuenta();
					String tipoDeCuentaSeleccionada = request.getParameter("tipoCuenta").toString();				
					if( tipoDeCuentaSeleccionada.contains("Corriente")) tipodecuenta.setCodTipoCuenta_Tcu(1);
					else tipodecuenta.setCodTipoCuenta_Tcu(2);
					
					cuentaConDatosNuevos.setTipoCuenta_CU(tipodecuenta);
					
					cuentaConDatosNuevos.setCBU_CU(cuentaConDatosViejos.getCBU_CU());
					cuentaConDatosNuevos.setSaldo_CU(Float.parseFloat(request.getParameter("txtSaldo")));
				
					String estadoSeleccionado = request.getParameter("estado").toString();
					if(estadoSeleccionado.contains("Activar")) { cuentaConDatosNuevos.setEstado_CU(true);}
					else {cuentaConDatosNuevos.setEstado_CU(false);}				
					
					seModifico = cuNeg.Modificar(cuentaConDatosNuevos);	
					if(seModifico) modificado = 1;
				}		
				else 
				{
					noExisteDni = 1;
				}
			}
			request.getSession().removeAttribute("numeroDeCuenta");
			request.setAttribute("existeDni", noExisteDni);
			request.setAttribute("modificacion", modificado);
			RequestDispatcher rd=request.getRequestDispatcher("/ModificarCuenta.jsp");
			rd.forward(request, response);

		}
	}

}
