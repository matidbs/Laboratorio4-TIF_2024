package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Localidad;
import entidad.Mes;
import entidad.Provincia;
import entidad.Reporte;
import negocio.LocalidadesNegocio;
import negocio.ProvinciasNegocio;
import negocio.mesesNegocio;
import negocio.reportesNegocio;
import negocioImpl.LocalidadesNegocioImpl;
import negocioImpl.ProvinciasNegocioImpl;
import negocioImpl.mesesNegocioImpl;
import negocioImpl.reportesNegocioImpl;

/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("Param") != null)
		{
			//Traigo provincias, meses y tabla sin filtros
			ArrayList<Provincia> listaProvincias;
			ProvinciasNegocio pNeg = new ProvinciasNegocioImpl();	
			listaProvincias = (ArrayList<Provincia>) pNeg.listar();
			
			ArrayList<Reporte> listaReportes;
			reportesNegocio rNeg = new reportesNegocioImpl();	
			listaReportes = (ArrayList<Reporte>) rNeg.listarReportes();
			
			int cantRegistros = listaReportes.size();
			
			ArrayList<Mes> listaMeses;
			mesesNegocio mNeg = new mesesNegocioImpl();	
			listaMeses = (ArrayList<Mes>) mNeg.listar();
			
			request.setAttribute("ReportesSinFiltro", listaReportes);
			request.setAttribute("Provincias", listaProvincias);
			request.setAttribute("CantidadRegistros", cantRegistros);
			request.setAttribute("Meses", listaMeses);
			RequestDispatcher rd=request.getRequestDispatcher("/ReporteCuentas.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnFiltrarProvincia") != null)
		{
			//Traigo cuentas existentes por provincia seleccionada			
			ArrayList<Reporte> listaReportes;
			reportesNegocio rNeg = new reportesNegocioImpl();
			int codProv= Integer.parseInt(request.getParameter("provincias"));
			listaReportes = (ArrayList<Reporte>) rNeg.listarReportes_Provincia(codProv);
			
			int cantRegistros = listaReportes.size();
			
			//depaso cargo el ddl de localidades
			ArrayList<Localidad> listaLocalidades;
			LocalidadesNegocio lneg = new LocalidadesNegocioImpl();	
			listaLocalidades = (ArrayList<Localidad>) lneg.listarPorProvincia(codProv);
			
			//vuelvo a cargar el dd provincias pero unicamente con la prov seleccionada
			ArrayList<Provincia> listaProvincias;
			ProvinciasNegocio pNeg = new ProvinciasNegocioImpl();	
			listaProvincias = (ArrayList<Provincia>) pNeg.BuscarProvinciaCompleta(codProv);
			
			//cargo el ddl de meses porque sinó se borra
			ArrayList<Mes> listaMeses;
			mesesNegocio mNeg = new mesesNegocioImpl();	
			listaMeses = (ArrayList<Mes>) mNeg.listar();
			
			request.setAttribute("ReportesFiltroProvincia", listaReportes);
			request.setAttribute("CantidadRegistros", cantRegistros);
			request.setAttribute("FiltroLocalidades", listaLocalidades);
			request.setAttribute("Provincias", listaProvincias);
			request.setAttribute("Meses", listaMeses);
			RequestDispatcher rd=request.getRequestDispatcher("/ReporteCuentas.jsp");
			rd.forward(request, response);
		}
		
		
		if(request.getParameter("btnFiltrarLocalidad") != null)
		{
			//Traigo cuentas existentes por Localidad seleccionada			
			ArrayList<Reporte> listaReportes;
			reportesNegocio rNeg = new reportesNegocioImpl();
			int codLoc = Integer.parseInt(request.getParameter("localidades"));
			listaReportes = (ArrayList<Reporte>) rNeg.listarReportes_ProvinciaLocalidad( codLoc);
			
			int cantRegistros = listaReportes.size();
			
			//vuelvo a cargar la provincia seleccionada, para eso me fijo a que prov corresponde la localidad seleccionada
			ArrayList<Localidad> listaLocalidades;
			LocalidadesNegocio lneg = new LocalidadesNegocioImpl();	
			listaLocalidades = (ArrayList<Localidad>) lneg.BuscarLocalidadCompleta(codLoc); //y depaso cargo la localidad seleccionada nuevamente
			Localidad l = listaLocalidades.get(0);
			
					
			ArrayList<Provincia> listaProvincias;
			ProvinciasNegocio pNeg = new ProvinciasNegocioImpl();	
			listaProvincias = (ArrayList<Provincia>) pNeg.BuscarProvinciaCompleta(l.getCodProv_L());
			
			
			ArrayList<Mes> listaMeses;
			mesesNegocio mNeg = new mesesNegocioImpl();	
			listaMeses = (ArrayList<Mes>) mNeg.listar();
			
			
			request.setAttribute("ReportesFiltroProvinciaLocalidad", listaReportes);
			request.setAttribute("CantidadRegistros", cantRegistros);
			request.setAttribute("Provincias", listaProvincias);
			request.setAttribute("FiltroLocalidades", listaLocalidades);
			request.setAttribute("Meses", listaMeses);
			RequestDispatcher rd=request.getRequestDispatcher("/ReporteCuentas.jsp");
			rd.forward(request, response);
		}
		
		
		if(request.getParameter("btnFiltrarAnio") != null)
		{
			//Traigo cuentas existentes por año ingresado			
			ArrayList<Reporte> listaReportes;
			reportesNegocio rNeg = new reportesNegocioImpl();
			String anio= request.getParameter("txtFiltrarAnio");
			listaReportes = (ArrayList<Reporte>) rNeg.listarReportes_Anio(anio);
			
			int cantRegistros = listaReportes.size();
			
			//vuelvo a cargar las provincias y meses, sino se borra
			ArrayList<Provincia> listaProvincias;
			ProvinciasNegocio pNeg = new ProvinciasNegocioImpl();	
			listaProvincias = (ArrayList<Provincia>) pNeg.listar();
			
			ArrayList<Mes> listaMeses;
			mesesNegocio mNeg = new mesesNegocioImpl();	
			listaMeses = (ArrayList<Mes>) mNeg.listar();
			
			//devuelvo el año ingresado asi queda en el txtFiltrarAnio	
			request.setAttribute("AnioFiltrado", anio);
			request.setAttribute("ReportesFiltroAnio", listaReportes);
			request.setAttribute("CantidadRegistros", cantRegistros);
			request.setAttribute("Provincias", listaProvincias);
			request.setAttribute("Meses", listaMeses);
			RequestDispatcher rd=request.getRequestDispatcher("/ReporteCuentas.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarMes") != null)
		{
			//Traigo cuentas existentes por año y mes seleccionado			
			ArrayList<Reporte> listaReportes;
			reportesNegocio rNeg = new reportesNegocioImpl();
			String anio= request.getParameter("txtFiltrarAnio");
			String mes= request.getParameter("meses");
			listaReportes = (ArrayList<Reporte>) rNeg.listarReportes_AnioMes(anio, mes);
			
			int cantRegistros = listaReportes.size();
			
			//vuelvo a cargar las provincias  sino se borra
			ArrayList<Provincia> listaProvincias;
			ProvinciasNegocio pNeg = new ProvinciasNegocioImpl();	
			listaProvincias = (ArrayList<Provincia>) pNeg.listar();
			
			//cargo unicamente el mes seleccionado
			ArrayList<Mes> listaMeses;
			mesesNegocio mNeg = new mesesNegocioImpl();	
			listaMeses = (ArrayList<Mes>) mNeg.listar();
			Mes mesSeleccionado = new Mes();
			
			for(Mes x : listaMeses)
			{
				if(x.getCodMes_M() == Integer.parseInt(mes))
				{
					mesSeleccionado = x;
				}
			}
			
			//limpio y vuelvo a cargar la lista
			listaMeses = null;
			listaMeses = new ArrayList<Mes>();
			listaMeses.add(mesSeleccionado);
			
			//devuelvo el año y mes ingresado asi queda en el txtFiltrarAnio y el ddl de meses	
			request.setAttribute("AnioFiltrado", anio);
			request.setAttribute("ReportesFiltroAnioMes", listaReportes);
			request.setAttribute("CantidadRegistros", cantRegistros);
			request.setAttribute("Provincias", listaProvincias);
			request.setAttribute("Meses", listaMeses);
			RequestDispatcher rd=request.getRequestDispatcher("/ReporteCuentas.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarSaldo") != null)
		{
			//Cargo lo mismo que el doget
			ArrayList<Provincia> listaProvincias;
			ProvinciasNegocio pNeg = new ProvinciasNegocioImpl();	
			listaProvincias = (ArrayList<Provincia>) pNeg.listar();
			
			ArrayList<Reporte> listaReportes;
			reportesNegocio rNeg = new reportesNegocioImpl();	
			String operador= request.getParameter("operador");
			String saldo= request.getParameter("txtSaldo");
			listaReportes = (ArrayList<Reporte>) rNeg.listarReportes_Saldo(operador, saldo);
			
			int cantRegistros = listaReportes.size();
			
			ArrayList<Mes> listaMeses;
			mesesNegocio mNeg = new mesesNegocioImpl();	
			listaMeses = (ArrayList<Mes>) mNeg.listar();
			
			request.setAttribute("ReportesFiltroSaldo", listaReportes);
			request.setAttribute("Provincias", listaProvincias);
			request.setAttribute("CantidadRegistros", cantRegistros);
			request.setAttribute("Meses", listaMeses);
			RequestDispatcher rd=request.getRequestDispatcher("/ReporteCuentas.jsp");
			rd.forward(request, response);
			
			
		}
		
		if(request.getParameter("btnMostrarTodos") != null)
		{
			//Cargo lo mismo que el doget
			ArrayList<Provincia> listaProvincias;
			ProvinciasNegocio pNeg = new ProvinciasNegocioImpl();	
			listaProvincias = (ArrayList<Provincia>) pNeg.listar();
			
			ArrayList<Reporte> listaReportes;
			reportesNegocio rNeg = new reportesNegocioImpl();	
			listaReportes = (ArrayList<Reporte>) rNeg.listarReportes();
			
			int cantRegistros = listaReportes.size();
			
			ArrayList<Mes> listaMeses;
			mesesNegocio mNeg = new mesesNegocioImpl();	
			listaMeses = (ArrayList<Mes>) mNeg.listar();
			
			request.setAttribute("ReportesSinFiltro", listaReportes);
			request.setAttribute("Provincias", listaProvincias);
			request.setAttribute("CantidadRegistros", cantRegistros);
			request.setAttribute("Meses", listaMeses);
			RequestDispatcher rd=request.getRequestDispatcher("/ReporteCuentas.jsp");
			rd.forward(request, response);
			
			
		}
		
	}

}
