<%@page import="entidad.Localidad"%>
<%@page import="negocioImpl.LocalidadesNegocioImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Nacionalidad"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar Cliente</title>

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
	<jsp:include page="/css/StyleSheet.css"></jsp:include>
</style>

</head>
<body>

<div class = "encabezado"> <h4 class = "text-center text-white"> <u>AGREGAR CLIENTE</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<div class = "parteDerecha font-weight-bold">

<a href="ServletAgregarCliente?Param=1">Cargar Descolgables</a>

<%

ArrayList<Provincia> listaProvincias = null;
ArrayList<Nacionalidad> listaNacionalidades = null;
ArrayList<Localidad> listaLocalidades = null;

if(request.getAttribute("Provincias") != null){
	listaProvincias = (ArrayList <Provincia>)request.getAttribute("Provincias");
}

if(request.getAttribute("Nacionalidades") != null){
	listaNacionalidades = (ArrayList <Nacionalidad>)request.getAttribute("Nacionalidades");
}

if(request.getAttribute("Localidades") != null){
	listaLocalidades = (ArrayList <Localidad>)request.getAttribute("Localidades");
}
%>



<h4 class = "text-center"><u>DATOS DEL CLIENTE</u></h4>


<form method = "post" action ="ServletAgregarCliente">
	<table>
	<tr>	
		<td>PROVINCIA:</td><td><select name = "provincia" required>
		<% 
		  					if(listaProvincias != null)
		  					for(Provincia x : listaProvincias)  {
		  				%>		
		  						<option value="<%= x.getCodProv_P() %>"><%= x.getDescripcion_P() %></option>
						<% } %>
		</select> </td> 
		
		<td>LOCALIDAD:</td><td><select name = "localidad" >
		<% 		
		  					if(listaLocalidades != null)
		  					for(Localidad x : listaLocalidades)  {
		  				%>		
		  						<option value="<%= x.getCodLoc_L() %>"><%= x.getDescripcion_L() %></option>
						<% } %>
		</select> </td>
		<td><input type="submit" name="btnFiltrarProvincia" value="Cargar Localidades"></td>
		<td>NACIONALIDAD:</td><td><select name = "nacionalidad" required>
					<% 
	  					if(listaNacionalidades != null)
	  					for(Nacionalidad x : listaNacionalidades)  {					
	  				%>		
	  						<option value="<%= x.getCodNacionalidad_N() %>"><%= x.getDescripcion_N() %></option>
					<% } %>
				</select> 
		</td>
		
	</tr>
	<tr>
	
	
			<%if(request.getAttribute("infoCargada") != null){%>
					<div class="alert alert-success" role="alert"><p class = "font-weight-bold text-center"> Esta informacion fue cargada con exíto, continúe con el formulario</p></div>
			<%}%>
	
	</tr>
	</table>	
	
	<p style = "text-align: center;"> 
		<input type="submit" name="btnCargarInfo" value="Cargar Esta informacion">
	</p>
	
</form>

<form method = "post" action ="ServletAgregarCliente">
<table>

<tr>
<td>INGRESE DNI:</td><td><input type = "text" pattern="^\d{8}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');" title ="DNI: 8 dígitos sin espacios ni puntos." name = "txtDni"  placeholder = "Inserte DNI de 8 dígitos" maxlength = "8"></td>
<td>INGRESE CUIL:</td><td> <input type = "text" pattern="^\d{11}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');" title ="CUIL: 11 dígitos si espacios ni guiones" name = "txtCuil"  placeholder = "Inserte CUIL de 11 dígitos" maxlength = "11"></td>
</tr>

<tr>
<td>INGRESE NOMBRE:</td><td><input type = "text" name = "txtNombre"  maxlength = "15" placeholder = "Inserte nombre"></td>
<td>INGRESE APELLIDO:</td><td><input type = "text" name = "txtApellido"  maxlength = "15" placeholder = "Inserte apellido"></td>
<td>SEXO:</td><td><select name = "sexo">
<option value = "M">Masculino</option>
<option value = "F">Femenino</option>
</select></td>
</tr>

<tr>
<td>DIRECCION:</td><td><input type = "text" name = "txtDireccion"  placeholder = "Inserte dirección"></td>
<td>EMAIL:</td><td><input type = "email" name = "txtEmail"  placeholder = "Inserte Email"></td>
</tr>

<tr>
<td>TELEFONO:</td><td><input type = "tel" name = "txtTel"  placeholder = "Inserte teléfono" maxlength = "10" pattern="^\d{10}$" title = "Teléfono: número de 10 dígitos." oninput="this.value = this.value.replace(/[^0-9]/g, '');"></td>
<td>FECHA DE NACIMIENTO:</td><td><input type = "date" name = "fechaNac" pattern="\d{4}-\d{2}-\d{2}" placeholder = "AAAA-mm-dd" title = "AAAA-mm-dd"></td>
</tr>

</table>

<h4 class = "text-center"><u>GENERACIÓN DE USUARIO</u></h4>
<table >
<tr>
<!-- Hay que verificar que el nombre de user no existe en la tabla Clientes ni en Usuario (admin) -->
<td>USUARIO:</td><td><input type = "text" name = "txtUsuario"  maxlength = "15"></td>
</tr>
<tr>
<td>CONTRASEÑA:</td><td><input type = "password" name = "txtContrasena"  maxlength = "15"></td>
<td>REPETIR CONTRASEÑA:</td><td><input type = "password" name = "txtRepetirContrasena"  maxlength = "15"></td>
</tr>
</table>

<p style = "text-align: center;"> 
<input type="submit" name="btnAceptar" value="Aceptar">
</p>
</form>

<% 
if(request.getAttribute("agregado") != null){
	
	int rta = (int)request.getAttribute("agregado");
	
	
	if(rta == 0){%> <div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>Ya existe un cliente con ese DNI en el sistema.</p> </div> <%}
	else{
		if(rta == -3){%><div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>Las contraseñas no son iguales.</p> </div><%}
		else{
			if(rta == -2){%><div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>Complete todos los campos.</p> </div><%}
			else
			{
				if(rta==-1){%><div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>Ya existe un cliente con ese usuario en el sistema.</p> </div>  <%}
				else{%><div class="alert alert-success" role="alert"><p>El cliente ha sido agregado con éxito al sistema.</p></div> <%}	
			}
		}		
	}		
}%>


</div>
<div class = "pieDePagina"></div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>