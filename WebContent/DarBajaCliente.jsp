<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cliente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dar de Baja Cliente</title>

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
	<jsp:include page="/css/StyleSheet.css"></jsp:include>
</style>

</head>
<body>

<div class = "encabezado"> <h4 class = "text-center text-white"> <u>DAR DE BAJA CLIENTE</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<div class = "parteDerecha font-weight-bold">

<a href = "ServletDarBajaCliente?Param=1">Cargar tabla</a>

<%

ArrayList<Cliente> listaC = null; //Botón 'Cargar tabla'
if(request.getAttribute("listaClientes") != null){
	listaC = (ArrayList<Cliente>)request.getAttribute("listaClientes");
}

ArrayList<Cliente> desplegableDni = null; //Array que se usa para cargar el desplegable (cliente filtrado por dni)
if(request.getAttribute("desplegableDni") != null){
	desplegableDni = (ArrayList<Cliente>)request.getAttribute("desplegableDni");
}

ArrayList<Cliente> seleccionadoDNI = null; //Array que tiene cliente seleccionado del desplegable (para volver a cargar la tabla con ese cliente)
if(request.getAttribute("seleccionadoDNI") != null){
	seleccionadoDNI = (ArrayList<Cliente>)request.getAttribute("seleccionadoDNI");
}

ArrayList<Cliente> filtrarClientesXnombre = null;
if(request.getAttribute("clientesFiltradosXnombre") != null){ 
	filtrarClientesXnombre = (ArrayList<Cliente>)request.getAttribute("clientesFiltradosXnombre");
}

ArrayList<Cliente> filtrarClientesXapellido = null;
if(request.getAttribute("clientesFiltradosXapellido") != null){ 
	filtrarClientesXapellido = (ArrayList<Cliente>)request.getAttribute("clientesFiltradosXapellido");
}

ArrayList<Cliente> filtrarClienteXnombre_y_apellido = null;
if(request.getAttribute("clienteFiltradoXnombre_y_apellido") != null){ 
	filtrarClienteXnombre_y_apellido = (ArrayList<Cliente>)request.getAttribute("clienteFiltradoXnombre_y_apellido");
}

//Por si se intentó dar de baja desde modificar cliente, se trae el Dni y lo carga en el value del input text para dni
Cliente cli = null;
if(request.getAttribute("DniDarBaja") != null){
	cli = (Cliente)request.getAttribute("DniDarBaja");
}

%>

<form method = "get" action = "ServletDarBajaCliente">
<table>
<tr><td> <br></td></tr>
<tr>
<tr>
<td>
<u><i>Realizar búsqueda por:</i></u>
</td>
</tr>
<tr><td> <br></td></tr>
<tr>
<td>DNI:</td><td><input type="text" <%if(cli != null){ %>value="<%= cli.getDni_Cl() %>"<% }else{%> value = "" <% }%> pattern="^\d{8}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');" title="DNI: 8 dígitos sin espacios ni puntos." name="txtDni" maxlength = "8"></td>
<td><input type="submit" value = "Buscar" name="btnBuscarDni" style="margin:0 1rem;" /></td>
<td>
<select name="resultadosDNI">
<option disabled="disabled">--Buscar--</option>
<%
	if(desplegableDni != null){
	for(Cliente c : desplegableDni){	
	
%>
		<option value = <%= c.getDni_Cl() %>><%= c.getNombre_Cl() + " " + c.getApellido_Cl() %> - <%= c.getDni_Cl() %></option>

<%
}
	}
%>
</select>
</td>
<td><input type="submit" value="Seleccionar" name="btnDesplegableDni" style="margin:0 1rem;"></td>
</tr>
<tr><td> <br></td></tr>
<tr>
<td>NOMBRE:</td><td><input type="text" name="txtNombre"></td>
</tr>
<tr>
<td>APELLIDO:</td><td><input type="text" name="txtApellido"></td>
<td><input type="submit" value="Buscar" name="btnBuscarNombreApellido" style="margin:0 1rem;" /></td>
<td>
<select name="resultadosNombre">
<option disabled="disabled">--Buscar--</option>

<%
	if(filtrarClientesXnombre != null){
	for(Cliente c : filtrarClientesXnombre){	
	
%>
		<option value = <%= c.getDni_Cl() %>><%=c.getNombre_Cl() + " " + c.getApellido_Cl() %> - <%= c.getDni_Cl() %></option>

<%
}
	}
%>

<%
	if(filtrarClientesXapellido != null){
	for(Cliente c : filtrarClientesXapellido){	
	
%>
		<option value = <%= c.getDni_Cl() %>><%= c.getNombre_Cl() + " " + c.getApellido_Cl() %> - <%= c.getDni_Cl() %></option>

<%
}
	}
%>

<%
	if(filtrarClienteXnombre_y_apellido != null){
	for(Cliente c : filtrarClienteXnombre_y_apellido){	
	
%>
		<option value = <%= c.getDni_Cl() %>><%= c.getNombre_Cl() + " " + c.getApellido_Cl() %> - <%= c.getDni_Cl() %></option>

<%
}
	}
%>

</select>
</td>
<td><input type="submit" value="Seleccionar" name="btnDesplegableNomApe" style="margin:0 1rem;"></td>
</tr>
</table>

</form>

<table id = "listarResultados" class = "table table-hover text-center" border = "1" style="margin-top:2rem;">
<thead>
<tr>
	<th>DNI</th><th>CUIL</th><th>NOMBRE</th><th>APELLIDO</th><th>USUARIO</th><th></th>
</tr>
</thead>

<%if(listaC != null){
	for(Cliente c: listaC){
		String nomApe = c.getNombre_Cl() + " " + c.getApellido_Cl();
%>

<tbody>

<tr>
	<form method = "get" action = "ConfirmarBajaCliente.jsp">
	<td><%= c.getDni_Cl()%> <input type = "hidden" name = "hiddenDni" value = "<%= c.getDni_Cl() %>"> <input type = "hidden" name = "hiddenNomApe" value = "<%= nomApe %>"> </td>
	<td><%= c.getCuil_Cl() %></td>
	<td><%= c.getNombre_Cl() %></td>
	<td><%= c.getApellido_Cl() %></td>
	<td><%= c.getUser_contra_Cl().getNombreUser_U() %></td>
	<td>
		<input type="submit" value="Dar de Baja" name="btnDarBaja" />	
	</td>
	</form>

</tr>

</tbody>

<%}
	} %>
	
	<%if(seleccionadoDNI != null){
	for(Cliente c: seleccionadoDNI){
	String nomApe = c.getNombre_Cl() + " " + c.getApellido_Cl();
%>

<tbody>

<tr>
	<form method = "get" action = "ConfirmarBajaCliente.jsp">
	<td><%= c.getDni_Cl() %> <input type = "hidden" name = "hiddenDni" value = "<%= c.getDni_Cl() %>"> <input type = "hidden" name = "hiddenNomApe" value = "<%= nomApe %>"> </td>
	<td><%= c.getCuil_Cl() %></td>
	<td><%= c.getNombre_Cl() %></td>
	<td><%= c.getApellido_Cl() %></td>
	<td><%= c.getUser_contra_Cl().getNombreUser_U() %></td>
	<td>
		<input type="submit" value="Dar de Baja" name="btnDarBaja"  />
	</td>
	</form>
</tr>

</tbody>

<%}
	} %>


</table>

<%
boolean estado = false;
if(request.getAttribute("dadoBaja") != null){
	estado = Boolean.parseBoolean(request.getAttribute("dadoBaja").toString());
}
%>

<%
if(estado){%>
<div class="alert alert-success" role="alert"><p>Cliente dado de baja con éxito</p></div>
<%}%>

</div>

<div class = "pieDePagina"></div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html> 