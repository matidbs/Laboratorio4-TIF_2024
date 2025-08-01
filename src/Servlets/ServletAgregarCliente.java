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
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Usuario;
import negocio.LocalidadesNegocio;
import negocio.NacionalidadesNegocio;
import negocio.ProvinciasNegocio;
import negocio.clientesNegocio;
import negocioImpl.LocalidadesNegocioImpl;
import negocioImpl.NacionalidadesNegocioImpl;
import negocioImpl.ProvinciasNegocioImpl;
import negocioImpl.clientesNegocioImpl;


@WebServlet("/ServletAgregarCliente")
public class ServletAgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private clientesNegocio cNeg = new clientesNegocioImpl();
	
	
    public ServletAgregarCliente() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param") != null) {
			//Provincia
			ArrayList<Provincia> listaProvincias;
			ProvinciasNegocio pNeg = new ProvinciasNegocioImpl();	
			listaProvincias = (ArrayList<Provincia>) pNeg.listar();
			
			//Nacionalidades
			ArrayList<Nacionalidad> listaNacionalidades;
			NacionalidadesNegocio nNeg = new NacionalidadesNegocioImpl();
			listaNacionalidades = (ArrayList<Nacionalidad>) nNeg.listar();
			
			request.setAttribute("Provincias", listaProvincias);
			request.setAttribute("Nacionalidades", listaNacionalidades);
			RequestDispatcher rd=request.getRequestDispatcher("/AgregarCliente.jsp");
			rd.forward(request, response);
				
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("btnFiltrarProvincia") != null) {
			int codProv= Integer.parseInt(request.getParameter("provincia"));
			
			//cargo el ddl de localidades segun provincia seleccionada
			ArrayList<Localidad> listaLocalidades;
			LocalidadesNegocio lneg = new LocalidadesNegocioImpl();	
			listaLocalidades = (ArrayList<Localidad>) lneg.listarPorProvincia(codProv);
			
			//vuelvo a cargar el dd provincias pero unicamente con la prov seleccionada
			ArrayList<Provincia> listaProvincias;
			ProvinciasNegocio pNeg = new ProvinciasNegocioImpl();	
			listaProvincias = (ArrayList<Provincia>) pNeg.BuscarProvinciaCompleta(codProv);
			
			//vuelvo a cargar el ddl nacionalidades 
			//Nacionalidades
			ArrayList<Nacionalidad> listaNacionalidades;
			NacionalidadesNegocio nNeg = new NacionalidadesNegocioImpl();
			listaNacionalidades = (ArrayList<Nacionalidad>) nNeg.listar();
			
			request.setAttribute("Nacionalidades", listaNacionalidades);
			request.setAttribute("Localidades", listaLocalidades);
			request.setAttribute("Provincias", listaProvincias);
			RequestDispatcher rd=request.getRequestDispatcher("/AgregarCliente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnCargarInfo") != null) 
		{
			int codProv= Integer.parseInt(request.getParameter("provincia"));
			int codLoc= Integer.parseInt(request.getParameter("localidad"));
			int codNac= Integer.parseInt(request.getParameter("nacionalidad"));
			
			request.getSession().setAttribute("ProvSeleccionada",codProv);
			request.getSession().setAttribute("LocSeleccionada",codLoc);
			request.getSession().setAttribute("NacSeleccionada",codNac);
			//cargo el ddl de localidades pero unicamente con la loc seleccionada
			ArrayList<Localidad> listaLocalidades;
			LocalidadesNegocio lneg = new LocalidadesNegocioImpl();	
			listaLocalidades = (ArrayList<Localidad>) lneg.BuscarLocalidadCompleta(codLoc);
			
			//vuelvo a cargar el dd provincias pero unicamente con la prov seleccionada
			ArrayList<Provincia> listaProvincias;
			ProvinciasNegocio pNeg = new ProvinciasNegocioImpl();	
			listaProvincias = (ArrayList<Provincia>) pNeg.BuscarProvinciaCompleta(codProv);
			
			//vuelvo a cargar el ddl nacionalidades pero que guarde la que seleccionó el usuario
			//Nacionalidades
			ArrayList<Nacionalidad> listaNacionalidades;
			NacionalidadesNegocio nNeg = new NacionalidadesNegocioImpl();
			listaNacionalidades = (ArrayList<Nacionalidad>) nNeg.BuscarNacionalidadCompleta(codNac);
			
			boolean cargado = true;
			
			request.setAttribute("Nacionalidades", listaNacionalidades);
			request.setAttribute("Localidades", listaLocalidades);
			request.setAttribute("Provincias", listaProvincias);
			request.setAttribute("infoCargada", cargado);
			
			RequestDispatcher rd=request.getRequestDispatcher("/AgregarCliente.jsp");
			rd.forward(request, response);
		}
		
		
		if(request.getParameter("btnAceptar") != null) {
			
			Usuario usuario;
			Nacionalidad nacionalidad;
			Provincia provincia;
			Localidad localidad;
			
			Cliente cliente = new Cliente();
			cliente.setDni_Cl(request.getParameter("txtDni"));
			cliente.setCuil_Cl(request.getParameter("txtCuil"));
			cliente.setNombre_Cl(request.getParameter("txtNombre").trim());
			cliente.setApellido_Cl(request.getParameter("txtApellido").trim());
			cliente.setSexo_Cl(request.getParameter("sexo"));
			
			//traigo las sessiones creadas de loc,prov,nac
			if(request.getSession().getAttribute("LocSeleccionada")!=null ||
				request.getSession().getAttribute("ProvSeleccionada")!= null ||
				request.getSession().getAttribute("NacSeleccionada") !=null)
			{
				int codLoc = Integer.parseInt(request.getSession().getAttribute("LocSeleccionada").toString());
				int codProv = Integer.parseInt(request.getSession().getAttribute("ProvSeleccionada").toString());
				int codNac = Integer.parseInt(request.getSession().getAttribute("NacSeleccionada").toString());
				
				//Nacionalidad
				nacionalidad = new Nacionalidad();
				nacionalidad.setCodNacionalidad_N(codNac);
				cliente.setCodNacionalidad_Cl(nacionalidad);
		
				//Provincia
				provincia = new Provincia();
				provincia.setCodProv_P(codProv);
				cliente.setCodProv_Cl(provincia);
				
				//Localidad
				localidad = new Localidad();
				localidad.setCodLoc_L(codLoc);		
				cliente.setCodLoc_Cl(localidad);
			
			}
			
			
			
			cliente.setDireccion_Cl(request.getParameter("txtDireccion"));
			cliente.setCorreoElectronico_Cl(request.getParameter("txtEmail"));
			cliente.setTelefono_Cl(request.getParameter("txtTel"));
			cliente.setFechaNacimiento_Cl(request.getParameter("fechaNac").toString());
			
			//Usuario
			usuario = new Usuario();
			usuario.setNombreUser_U(request.getParameter("txtUsuario"));
			usuario.setContra_U(request.getParameter("txtContrasena"));
						
			cliente.setUser_contra_Cl(usuario);
			
			int agregado=0;
			
			if(cNeg.todosLosCamposCompletos(cliente))
			{
				if(!request.getParameter("txtContrasena").toString().equals(request.getParameter("txtRepetirContrasena").toString()))
				{
					agregado = -3;
				}else 
				{	
					if(cNeg.existeCliente(cliente)) 
					{
						agregado = 0; //ya existe el cliente
					}else 
					{
						if(cNeg.existeClienteUsuario(cliente))
						{
							agregado = -1; //ya existe el usuario 
						}
						else 
						{
							boolean verificar = cNeg.Agregar(cliente);
							if(verificar) agregado = 1;
						
						}
					}					
				}
			}
			else 
			{
				agregado = -2;
			}
			
			//borro las sessiones
			request.getSession().removeAttribute("LocSeleccionada");
			request.getSession().removeAttribute("ProvSeleccionada");
			request.getSession().removeAttribute("NacSeleccionada");
			
			
			request.setAttribute("agregado", agregado);
			RequestDispatcher rd=request.getRequestDispatcher("/AgregarCliente.jsp");
			rd.forward(request, response);
		}
		
	}
	

}
