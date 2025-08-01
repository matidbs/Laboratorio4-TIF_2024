package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.nacionalidadesDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Reporte;
import entidad.Usuario;
import entidad.Localidad;
import negocio.LocalidadesNegocio;
import negocio.NacionalidadesNegocio;
import negocio.ProvinciasNegocio;
import negocio.clientesNegocio;
import negocio.cuentasNegocio;
import negocioImpl.LocalidadesNegocioImpl;
import negocioImpl.NacionalidadesNegocioImpl;
import negocioImpl.ProvinciasNegocioImpl;
import negocioImpl.clientesNegocioImpl;
import negocioImpl.cuentasNegocioImpl;


@WebServlet("/ServletModificarCliente")
public class ServletModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    clientesNegocio neg = new clientesNegocioImpl();
    cuentasNegocio cNeg = new cuentasNegocioImpl();
    ProvinciasNegocio nProv = new ProvinciasNegocioImpl();
    LocalidadesNegocio nLoc = new LocalidadesNegocioImpl();
    NacionalidadesNegocio nNac = new NacionalidadesNegocioImpl();
    Cliente c;
    Nacionalidad n;
	Localidad l;
	Provincia p;
	Usuario u;
	Cuenta cuenta;

    public ServletModificarCliente() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			if(request.getParameter("btnSeleccionar") != null) {
			
				c = new Cliente();
				c.setDni_Cl(request.getParameter("txtBuscarDni"));
			
			if(neg.existeCliente(c)) {
				
				ArrayList<Cliente> clienteFiltradoXdni = (ArrayList<Cliente>)neg.filtrarClienteXdniModificar(c);
				request.setAttribute("clienteFiltradoXdni", clienteFiltradoXdni);
				
				ArrayList<Provincia> listaProvincias;
				listaProvincias = (ArrayList<Provincia>) nProv.listar();
				request.setAttribute("Provincias", listaProvincias);
				
				ArrayList<Nacionalidad> listaNacionalidades;
			    listaNacionalidades = (ArrayList<Nacionalidad>) nNac.listar();
			    request.setAttribute("Nacionalidades", listaNacionalidades);
			    
			    ArrayList<Localidad> listaLocalidades;
			    listaLocalidades = (ArrayList<Localidad>) nLoc.listar();
			    request.setAttribute("Localidades", listaLocalidades);
			
			}else {
				request.setAttribute("noExisteDni", false);
			}
				
		}
			
			if(request.getParameter("btnFiltrarProvincia") != null) {
				
				c = new Cliente();
				c.setDni_Cl(request.getSession().getAttribute("DNI").toString());
				
			if(neg.existeCliente(c)) {
					
				c = new Cliente();
				n = new Nacionalidad();
				l = new Localidad();
				p = new Provincia();
				u = new Usuario();
				c.setDni_Cl(request.getParameter("txtDni"));
				c.setCuil_Cl(request.getParameter("txtCuil"));
				c.setNombre_Cl(request.getParameter("txtNombre"));
				c.setApellido_Cl(request.getParameter("txtApellido"));
				c.setSexo_Cl(request.getParameter("sexo"));
				n.setCodNacionalidad_N(Integer.parseInt(request.getParameter("nacionalidad").toString()));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(request.getParameter("txtDireccion"));
				l.setCodLoc_L(Integer.parseInt(request.getParameter("localidad").toString()));
				c.setCodLoc_Cl(l);
				p.setCodProv_P(Integer.parseInt(request.getParameter("provincia").toString()));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(request.getParameter("txtEmail"));
				c.setTelefono_Cl(request.getParameter("txtTel"));
				u.setNombreUser_U(request.getParameter("nombreUser"));
				u.setContra_U(request.getParameter("txtContra"));
				c.setUser_contra_Cl(u);
				c.setFechaNacimiento_Cl(request.getParameter("fechaNac").toString());
					
				String estadoDesplegable = request.getParameter("estado").toString();
				boolean valor = false;
				if(estadoDesplegable.equals("1")) { valor = true;}
				else {valor = false;}				
					
				c.setEstado_Cl(valor);
				
				ArrayList<Cliente> clienteFiltradoXdni = new ArrayList<Cliente>();
				clienteFiltradoXdni.add(c);
				request.setAttribute("clienteFiltradoXdni", clienteFiltradoXdni);
				

				ArrayList<Provincia> listaProvincias;
				listaProvincias = (ArrayList<Provincia>) nProv.listar();
				request.setAttribute("Provincias", listaProvincias);
				
				ArrayList<Nacionalidad> listaNacionalidades;
			    listaNacionalidades = (ArrayList<Nacionalidad>) nNac.listar();
			    request.setAttribute("Nacionalidades", listaNacionalidades);
			    
		    	 
		    	int codProv= Integer.parseInt(request.getParameter("provincia").toString());
					
				ArrayList<Localidad> listaLocalidadesF;
				LocalidadesNegocio lneg = new LocalidadesNegocioImpl();	
				listaLocalidadesF = (ArrayList<Localidad>) lneg.listarPorProvincia(codProv);
				    
				request.setAttribute("FiltroLocalidades", listaLocalidadesF);
				
			}else {
				request.setAttribute("noExisteDni", false);
			}
				
			
	    }
			
			if(request.getParameter("btnAceptar") != null) {
				c = new Cliente();
				c.setDni_Cl(request.getParameter("txtDni"));
				String estadoDesplegable = request.getParameter("estado").toString();
				boolean valor = false;
				if(estadoDesplegable.equals("1")) { valor = true;}
				else {valor = false;}				
				ArrayList<Cliente> clienteFiltradoXdni = (ArrayList<Cliente>)neg.filtrarClienteXdniModificar(c);
				c = clienteFiltradoXdni.get(0);
				String cuil = c.getCuil_Cl().toString();
				
				if(c.isEstado_Cl() == true && valor == false) { //SI SE QUIERE DAR DE BAJA ESTANDO ACTIVO, LO ENVIO AL FORM. DAR DE BAJA
				request.setAttribute("DniDarBaja", c);
				RequestDispatcher rd=request.getRequestDispatcher("/DarBajaCliente.jsp");
				rd.forward(request, response);
				}else {
				
				c = new Cliente();
				n = new Nacionalidad();
				l = new Localidad();
				p = new Provincia();
				u = new Usuario();
				c.setCuil_Cl(request.getParameter("txtCuil"));
					
				if(!cuil.equals(c.getCuil_Cl())) { //SI EL CLIENTE ESTA MODIFICANDO SU CUIL --->
					if(!neg.existeCuilCliente(c)) { // REVISO QUE EL "NUEVO" CUIL NO EXISTA, SI NO EXISTE (NUEVO CUIL), ACTUALIZO EL CLIENTE
					
				c.setDni_Cl(request.getParameter("txtDni"));
				c.setNombre_Cl(request.getParameter("txtNombre"));
				c.setApellido_Cl(request.getParameter("txtApellido"));
				c.setSexo_Cl(request.getParameter("sexo"));
				n.setCodNacionalidad_N(Integer.parseInt(request.getParameter("nacionalidad").toString()));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(request.getParameter("txtDireccion"));
				l.setCodLoc_L(Integer.parseInt(request.getParameter("localidad").toString()));
				c.setCodLoc_Cl(l);
				p.setCodProv_P(Integer.parseInt(request.getParameter("provincia").toString()));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(request.getParameter("txtEmail"));
				c.setTelefono_Cl(request.getParameter("txtTel"));
				u.setContra_U(request.getParameter("txtContra"));
				c.setUser_contra_Cl(u);
				c.setFechaNacimiento_Cl(request.getParameter("fechaNac"));
				if(estadoDesplegable.equals("1")) { c.setEstado_Cl(true);}
				else {c.setEstado_Cl(false);}		
				
				try { //POR SI EL CLIENTE NO TIENE CUENTAS, AL MODIFICAR NO GENERE PROBLEMAS
					cuenta = new Cuenta();
					cuenta.setDniCliente_CU(request.getParameter("txtDni"));
					cNeg.activarCuentasCliente(cuenta);
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				boolean estado = neg.Modificar(c);
				request.setAttribute("Modificado", estado);
			}else { //SI EL CUIL NUEVO EXISTE, NO ACTUALIZO EL REGISTRO, PERO NO SE PERDERa LO INGRESADO EN EL FORMULARIO
					//LOS CONTROLES NO PIERDEN LO QUE SE INGRESa“
				c = new Cliente();
				n = new Nacionalidad();
				l = new Localidad();
				p = new Provincia();
				u = new Usuario();
				c.setCuil_Cl(request.getParameter("txtCuil"));
				c.setDni_Cl(request.getParameter("txtDni"));
				c.setNombre_Cl(request.getParameter("txtNombre"));
				c.setApellido_Cl(request.getParameter("txtApellido"));
				c.setSexo_Cl(request.getParameter("sexo"));
				n.setCodNacionalidad_N(Integer.parseInt(request.getParameter("nacionalidad").toString()));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(request.getParameter("txtDireccion"));
				l.setCodLoc_L(Integer.parseInt(request.getParameter("localidad").toString()));
				c.setCodLoc_Cl(l);
				p.setCodProv_P(Integer.parseInt(request.getParameter("provincia").toString()));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(request.getParameter("txtEmail"));
				c.setTelefono_Cl(request.getParameter("txtTel"));
				u.setContra_U(request.getParameter("txtContra"));
				
				//TRAEMOS EL USUARIO DEL CLIENTE PARA MOSTRARLO EN EL CARTEL DEL MODIFICAR CONTRASEna
				ArrayList<Cliente> usuarioCliente = (ArrayList<Cliente>)neg.filtrarClienteXdniModificar(c);
				Cliente cl = new Cliente();
				cl = usuarioCliente.get(0);
				u.setNombreUser_U(cl.getUser_contra_Cl().getNombreUser_U());
				
				c.setUser_contra_Cl(u);
				c.setFechaNacimiento_Cl(request.getParameter("fechaNac"));
				if(estadoDesplegable.equals("1")) { c.setEstado_Cl(true);}
				else {c.setEstado_Cl(false);}
					
				ArrayList<Provincia> listaProvincias;
				listaProvincias = (ArrayList<Provincia>) nProv.listar();
				request.setAttribute("Provincias", listaProvincias);
					
				ArrayList<Nacionalidad> listaNacionalidades;
				listaNacionalidades = (ArrayList<Nacionalidad>) nNac.listar();
				request.setAttribute("Nacionalidades", listaNacionalidades);
			    
				//COMO LOS CONTROLES NO PERDERan SU INFORMACIon, VUELVO A CARGAR LOS DESPLEGABLES
			    int codProv= Integer.parseInt(request.getParameter("provincia").toString());
						
				ArrayList<Localidad> listaLocalidadesF;
				LocalidadesNegocio lneg = new LocalidadesNegocioImpl();	
				listaLocalidadesF = (ArrayList<Localidad>) lneg.listarPorProvincia(codProv);
				    
				request.setAttribute("FiltroLocalidades", listaLocalidadesF);
					
				ArrayList<Cliente> clienteFiltrado = new ArrayList<Cliente>();
				clienteFiltrado.add(c);
				request.setAttribute("clienteFiltradoXdni", clienteFiltrado);
				request.setAttribute("CUIL", false);
				}
					
			}else{ //SI EL CLIENTE NO ESTA MODIFICANDO SU CUIL, ACTUALIZO EL REGISTRO PERO NO BUSCO SI EXISTE EL CUIL
				c = new Cliente();
				n = new Nacionalidad();
				l = new Localidad();
				p = new Provincia();
				u = new Usuario();
				c.setDni_Cl(request.getParameter("txtDni"));
				c.setNombre_Cl(request.getParameter("txtNombre"));
				c.setApellido_Cl(request.getParameter("txtApellido"));
				c.setCuil_Cl(request.getParameter("txtCuil"));
				c.setSexo_Cl(request.getParameter("sexo"));
				n.setCodNacionalidad_N(Integer.parseInt(request.getParameter("nacionalidad").toString()));
				c.setCodNacionalidad_Cl(n);
				c.setDireccion_Cl(request.getParameter("txtDireccion"));
				l.setCodLoc_L(Integer.parseInt(request.getParameter("localidad").toString()));
				c.setCodLoc_Cl(l);
				p.setCodProv_P(Integer.parseInt(request.getParameter("provincia").toString()));
				c.setCodProv_Cl(p);
				c.setCorreoElectronico_Cl(request.getParameter("txtEmail"));
				c.setTelefono_Cl(request.getParameter("txtTel"));
				u.setContra_U(request.getParameter("txtContra"));
				c.setUser_contra_Cl(u);
				c.setFechaNacimiento_Cl(request.getParameter("fechaNac"));
				if(estadoDesplegable.equals("1")) { c.setEstado_Cl(true);}
				else {c.setEstado_Cl(false);}		
				
				try { //POR SI EL CLIENTE NO TIENE CUENTAS, AL MODIFICAR NO GENERE PROBLEMAS
				cuenta = new Cuenta();
				cuenta.setDniCliente_CU(request.getParameter("txtDni"));
				cNeg.activarCuentasCliente(cuenta);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				boolean estado = neg.Modificar(c);
				request.setAttribute("Modificado", estado);
				}
			}
				
		}
			
			RequestDispatcher rd=request.getRequestDispatcher("/ModificarCliente.jsp");
			rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
	}

}
