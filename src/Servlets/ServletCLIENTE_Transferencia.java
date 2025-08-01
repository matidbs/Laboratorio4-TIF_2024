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
import entidad.Transferencia;
import negocio.clientesNegocio;
import negocio.cuentasNegocio;
import negocio.movimientosNegocio;
import negocioImpl.clientesNegocioImpl;
import negocioImpl.cuentasNegocioImpl;
import negocioImpl.movimientosNegocioImpl;

/**
 * Servlet implementation class ServletCLIENTE_Transferencia
 */
@WebServlet("/ServletCLIENTE_Transferencia")
public class ServletCLIENTE_Transferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCLIENTE_Transferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param") != null)
		{
			cuentasNegocio cneg = new cuentasNegocioImpl();
			ArrayList<Cuenta> lista;
			
			//uso el sesionDni 
			Cuenta x = new Cuenta();
			x.setDniCliente_CU(request.getSession().getAttribute("sesionDNI").toString());
			lista = cneg.listarCuentasDni(x);
					
			request.setAttribute("ListaCuentas", lista);
			RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_Transferencia.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnTransferir") != null) 
		{
			//Verifico si seleccionó una cuenta a debitar
			if(request.getParameter("cuentasCliente") == null || request.getParameter("cuentasCliente").equals("disabled")) {
				request.setAttribute("SeleccioneCuenta", true);
				RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_Transferencia.jsp");
				rd.forward(request, response);
			}
			
			boolean existeCbu = false;
			boolean transferenciaExitosa = false;
			cuentasNegocio cuentasneg = new cuentasNegocioImpl();
			clientesNegocio clientenegocio = new clientesNegocioImpl();
			
			Cuenta destino = new Cuenta(); //lo uso para cargar el cbu ingresado
			Cuenta origen = new Cuenta(); //lo uso para cargar el numero de cuenta seleccionado
			
			ArrayList<Cuenta> registroDestino = null; //aca voy a guardar el registro competo de la cuenta destino
			ArrayList<Cuenta> registroOrigen = null; //aca voy a guardar el registro de la cuenta origen
			
			//verifico que la cuenta y cliente destino esten activas
			destino.setCBU_CU(Integer.parseInt(request.getParameter("txtCbu"))); //me traigo el cbu que ingresaron
						
			registroDestino  = cuentasneg.listarCuentaCBU(destino);//cargo la cuenta a la que le voy a trasnferir con todos los datos
			boolean estadoCliente = clientenegocio.clienteActivoPorCBUCuenta(destino.getCBU_CU());	
			
			if(estadoCliente == false) //el cliente está inactivo
			{
				request.setAttribute("verificarCliente", estadoCliente);
				RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_Transferencia.jsp");
				rd.forward(request, response);
			}else //el cliente está activo
			{
				if(registroDestino.size() != 0) //si la lista no está vacía es porque existe el cbu y/o está activo
				{
					existeCbu = true;
					
					movimientosNegocio mNeg = new movimientosNegocioImpl();
					Transferencia t = new Transferencia();
					float monto = Float.parseFloat(request.getParameter("txtImporte"));
					
					//cargo la cuenta de destino por completo
					destino = registroDestino.get(0);
					
					//obtengo registro completo de la cuenta de origen
					origen.setNroCuenta_CU(Integer.parseInt(request.getParameter("cuentasCliente")));
					registroOrigen = cuentasneg.listarCuentaNroCuenta(origen);
					//cargo la cuenta de origen por completo
					origen = registroOrigen.get(0);
					
					//cargo objeto transferencia
					t.setCuentaOrigen(origen.getNroCuenta_CU());
					t.setCuentaDestino(destino.getNroCuenta_CU());
					t.setCbuOrigen(origen.getCBU_CU());
					t.setCbuDestino(destino.getCBU_CU());
					t.setMonto(monto);
					t.setDetalle(request.getParameter("txtMotivo"));
					transferenciaExitosa = mNeg.AgregarTransferencia(t);
				}			
				
				request.setAttribute("transferencia", transferenciaExitosa);
				request.setAttribute("verificarCbu", existeCbu);
				RequestDispatcher rd=request.getRequestDispatcher("/CLIENTE_Transferencia.jsp");
				rd.forward(request, response);
			}
		}
	}
}
