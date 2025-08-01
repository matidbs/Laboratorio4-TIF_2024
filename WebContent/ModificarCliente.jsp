<%@page import="java.security.acl.AclNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="entidad.Localidad"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Cliente</title>

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
	<jsp:include page="/css/StyleSheet.css"></jsp:include>
</style>

</head>
<body>

<div class = "encabezado"> <h4 class = "text-center text-white"> <u>MODIFICAR CLIENTE</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<div class = "parteDerecha font-weight-bold" style="height:600px;">

<%

Cliente c = new Cliente();
ArrayList<Cliente> filtrarClienteXdni = null;
if(request.getAttribute("clienteFiltradoXdni") != null){ 
	filtrarClienteXdni = (ArrayList<Cliente>)request.getAttribute("clienteFiltradoXdni");
	c = filtrarClienteXdni.get(0);
}

ArrayList <Provincia> provincias = null;
if(request.getAttribute("Provincias") != null)
{	
	provincias = (ArrayList <Provincia>)request.getAttribute("Provincias");
}	

ArrayList <Nacionalidad> nacionalidades = null;
if(request.getAttribute("Nacionalidades") != null)
{	
	nacionalidades = (ArrayList <Nacionalidad>)request.getAttribute("Nacionalidades");
}	

ArrayList <Localidad> localidades = null;
if(request.getAttribute("Localidades") != null)
{	
	localidades = (ArrayList <Localidad>)request.getAttribute("Localidades");
}

Localidad LF = new Localidad();
ArrayList <Localidad> localidadesF = null;
if(request.getAttribute("FiltroLocalidades") != null)
{	
	localidadesF = (ArrayList <Localidad>)request.getAttribute("FiltroLocalidades");
	LF = localidadesF.get(0);
}

%>

<form method = "get" action = "ServletModificarCliente">

<div>
  <table class="text-center" style="margin: 0 auto;">
    <tr>
      <td>Buscar cliente por DNI</td>
      <td style="padding: 0 1rem;">
      	<input type = "text" pattern="^\d{8}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');" title ="DNI: 8 dígitos sin espacios ni puntos." name = "txtBuscarDni" maxlength = "8">
      	<% session.setAttribute("DNI", c.getDni_Cl()); %>
      </td>
      <td>
      	<input type="submit" name="btnSeleccionar" value="Seleccionar" />
      </td>
    </tr>
    
    <tr>
      <td colspan=3>
       <%if(request.getAttribute("noExisteDni") != null){ %>
	<div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>No hay ningún cliente con ese DNI.</p> </div>
<% }%>
      </td>
    </tr>
  </table>	
</div>

</form>

<h4 class = "text-center"><u>DATOS DEL CLIENTE</u></h4>

<form method = "get" action = "ServletModificarCliente">
<table>

<tr>
<td>DNI:</td><td><input type = "text" name = "txtDni"  readOnly value = "<%= c.getDni_Cl() != null ? c.getDni_Cl() : "" %>"></td>
<td>CUIL:</td><td> <input type = "text" pattern="^\d{11}$" value = "<%= c.getCuil_Cl() != null ? c.getCuil_Cl() : "" %>" oninput="this.value = this.value.replace(/[^0-9]/g, '');" title ="CUIL: 11 dígitos si espacios ni guiones" name = "txtCuil" required maxlength = "11"></td>
</tr>

<tr>
<td>NOMBRE:</td><td><input type = "text" name = "txtNombre" required maxlength = "15" value = "<%= c.getNombre_Cl() != null ? c.getNombre_Cl() : "" %>"></td>
<td>APELLIDO:</td><td><input type = "text" name = "txtApellido" required maxlength = "15" value = "<%= c.getApellido_Cl() != null ? c.getApellido_Cl() : "" %>"></td>
</tr>
<tr>
<td>SEXO:</td><td><select name = "sexo">

<%
if(filtrarClienteXdni != null){
if(c.getSexo_Cl().equals("M")){
%>
<option selected value = "M">Masculino</option>
<option value = "F">Femenino</option>

<% }else{%>
<option value = "M">Masculino</option>
<option selected value = "F">Femenino</option>
<% }}%>

</select></td>
</tr>

<tr>
<td>NACIONALIDAD:</td><td><select name = "nacionalidad">
<%
					if(nacionalidades != null)
  					for(Nacionalidad x : nacionalidades)  {
  						if(c.getCodNacionalidad_Cl().getCodNacionalidad_N() == x.getCodNacionalidad_N()){
  				%>		
  						<option selected value="<%= x.getCodNacionalidad_N() %>"><%= x.getDescripcion_N() %></option>
				<%}else{%>
						<option value="<%= x.getCodNacionalidad_N() %>"><%= x.getDescripcion_N() %></option>
					<% }%>
					
					<%}%>
				
</select> </td>
</tr>
<tr>
<td>PROVINCIA:</td><td><select name = "provincia">


				<%if(provincias != null)
  					for(Provincia x : provincias)  {
  						if(c.getCodProv_Cl().getCodProv_P() == x.getCodProv_P() || LF.getCodProv_L() == x.getCodProv_P()){
  				%>		
  						<option selected value="<%= x.getCodProv_P() %>"><%= x.getDescripcion_P() %></option>
				<%}else{%>
						<option value="<%= x.getCodProv_P() %>"><%= x.getDescripcion_P() %></option>
					<% }%>
					
					<%}%>
				
  						
</select> </td>
<td>LOCALIDAD:</td><td><select name = "localidad">
<% 
  					if(localidades != null){
  					for(Localidad x : localidades)  {
  						if(c.getCodProv_Cl().getCodProv_P() == x.getCodProv_L()){
  						if(c.getCodLoc_Cl().getCodLoc_L() == x.getCodLoc_L()){
  				%>		
  						<option selected value="<%= x.getCodLoc_L() %>"><%= x.getDescripcion_L() %></option>
				<%}else{%>
						<option value="<%= x.getCodLoc_L() %>"><%= x.getDescripcion_L() %></option>
					<% }%>
					
					<%}}}%>
					
					<% 
  					if(localidadesF != null){
  					for(Localidad x : localidadesF)  {
  						
  						if(c.getCodLoc_Cl().getCodLoc_L() == x.getCodLoc_L()){
  				%>		
  						<option selected value="<%= x.getCodLoc_L() %>"><%= x.getDescripcion_L() %></option>
				<%}else{%>
						<option value="<%= x.getCodLoc_L() %>"><%= x.getDescripcion_L() %></option>
					<% }%>
					
					<%}}%>
				
				
</select>
<tr><td> </td><td><input type = "submit" name = "btnFiltrarProvincia" value = "Filtrar localidades" class = "btn-primary">
</td></tr>
<tr>
</tr>


<tr>
<td>DIRECCION:</td><td><input type = "text" name = "txtDireccion" required value = "<%= c.getDireccion_Cl() != null ? c.getDireccion_Cl() : "" %>"></td>
<td>EMAIL:</td><td><input type = "email" name = "txtEmail" required value = "<%= c.getCorreoElectronico_Cl() != null ? c.getCorreoElectronico_Cl() : "" %>"></td>
</tr>

<tr>
<td>TELEFONO:</td><td><input type = "tel" name = "txtTel" value = "<%= c.getTelefono_Cl() != null ? c.getTelefono_Cl() : "" %>" required maxlength = "10" pattern="^\d{10}$" title = "Teléfono: número de 10 dígitos." oninput="this.value = this.value.replace(/[^0-9]/g, '');"></td>
<td>FECHA DE NACIMIENTO:</td><td><input type = "date" value = "<%= c.getFechaNacimiento_Cl() != null ? c.getFechaNacimiento_Cl() : "" %>" required name = "fechaNac" pattern="\d{4}-\d{2}-\d{2}" title = "Borre 00:00:00.0." placeholder = "AAAA-mm-dd" maxlength="10"></td>
</tr>
<tr>
<td>ESTADO:</td>
<td><select name = "estado">
<%if(filtrarClienteXdni != null){ 
if(c.isEstado_Cl() == true){
%>
<option selected value = "1">Activo</option>
<option value = "0">Inactivo</option>
<%}else{ %>
<option value = "1">Activo</option>
<option selected value = "0">Inactivo</option>
<% }}%>
</select>
</td>
</tr>
</table>

<h4 class = "text-center"><u>MODIFICAR CONTRASEÑA</u></h4>

<p class = "text-center">
<%if(filtrarClienteXdni != null){ %>
Se modificará la contraseña del usuario: <u><%= c.getUser_contra_Cl().getNombreUser_U() %></u><br>
<input type = "hidden" name = "nombreUser" value = "<%= c.getUser_contra_Cl().getNombreUser_U() %>">
<%}else{ %>
Se modificará la contraseña del usuario: <br>
<%} %>
<%if(filtrarClienteXdni !=null){ %>
CONTRASEÑA:<input type = "text" value = "<%= c.getUser_contra_Cl().getContra_U() %>" name = "txtContra" required maxlength = "15">
<%}else{ %>
CONTRASEÑA:<input type = "text" name = "txtContra" required maxlength = "15">
<% }%>

<span style = "margin-left: 5em">
<input type = "submit" name = "btnAceptar" value = "Aceptar"></span>
</p>

</form>

<%
boolean estado = false;
if(request.getAttribute("Modificado") != null){
	estado = Boolean.parseBoolean(request.getAttribute("Modificado").toString());
}
%>

 <%if(request.getAttribute("CUIL") != null){ %>
<div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>Ya existe un cliente con ese CUIL.</p> </div>
<% }%>

<%
if(estado){%>
<div class="alert alert-success" role="alert"><p>El cliente ha sido modificado con éxito</p></div>
<%} %>


</div>

<div class = "pieDePagina"></div>
</html>