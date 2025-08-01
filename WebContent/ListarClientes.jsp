<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cliente"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.Period"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar Clientes</title>

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<style>

<jsp:include page="/css/StyleSheet.css"></jsp:include>
</style>
</head>
<body>

<div class = "encabezado"> <h4 class = "text-center text-white"> <u>LISTAR CLIENTES</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<div class = "parteDerecha">

<a href="ServletListarClientes?Param=1">Cargar tabla</a>
<%

ArrayList<Cliente> listaC = null;
if(request.getAttribute("listaClientes") != null){
	listaC = (ArrayList<Cliente>)request.getAttribute("listaClientes");
}

if(request.getAttribute("clienteFiltradoXdni") != null){ 
	listaC = (ArrayList<Cliente>)request.getAttribute("clienteFiltradoXdni");
}

if(request.getAttribute("clientesFiltradosXedad") != null){ 
	listaC = (ArrayList<Cliente>)request.getAttribute("clientesFiltradosXedad");
}

if(request.getAttribute("clientesFiltradosXnombre") != null){ 
	listaC = (ArrayList<Cliente>)request.getAttribute("clientesFiltradosXnombre");
}

if(request.getAttribute("clientesFiltradosXapellido") != null){ 
	listaC = (ArrayList<Cliente>)request.getAttribute("clientesFiltradosXapellido");
}

if(request.getAttribute("clienteFiltradoXnombre_y_apellido") != null){ 
	listaC = (ArrayList<Cliente>)request.getAttribute("clienteFiltradoXnombre_y_apellido");
}


%>

<form method = "get" action = "ServletListarClientes">
<table class = "font-weight-bold">
<tr>
<td>FILTRAR POR DNI:</td><td><input type = "text" pattern="^\d{8}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');" title ="DNI: 8 dígitos sin espacios ni puntos." name = "txtFiltrarDni" maxlength = "8"></td>
<td><input type = "submit" name = "btnDni" value = "Filtrar por DNI" class = "form-control"></td>
</tr>
<tr>
<td>FILTRAR POR EDAD:</td><td><input type = "number" name = "txtEdad" min = "18" max = "80"></td>
<td><input type = "submit" name = "btnEdad" value = "Filtrar por Edad" class = "form-control"></td>
</tr>
<tr>
<td>FILTRAR POR NOMBRE:</td><td><input type = "text" name = "txtNombre"></td>
<td>FILTRAR POR APELLIDO:</td><td><input type = "text" name = "txtApellido"></td>
<td><input type = "submit" name = "btnBuscar" value = "Buscar" class = "form-control" style = "width: 170px"></td>
</tr>
</table>
</form>


<div class = "table-responsive">
<table id = "listarClientes" class = "table table-hover text-center" border = "1">

<thead>
<tr>
	<th>DNI</th><th>CUIL</th><th>NOMBRE</th><th>APELLIDO</th><th>SEXO</th><th>NACIONALIDAD</th><th>PROVINCIA</th><th>LOCALIDAD</th>
	<th>DIRECCION</th><th>EMAIL</th><th>TELEFONO</th><th>FECHA DE NACIMIENTO</th><th>EDAD</th><th>USUARIO</th><th>CONTRASEÑA</th>
</tr>
</thead>
	<tbody>

	<%
	if(listaC != null){
		for(Cliente c : listaC){
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		 	LocalDate dt = LocalDate.parse(c.getFechaNacimiento_Cl(), dtf);
		 	int edad = Period.between(dt, LocalDate.now()).getYears();
	%>
	
	<tr>
		<td><%=c.getDni_Cl() %></td>
		<td><%=c.getCuil_Cl() %></td>
		<td><%=c.getNombre_Cl() %></td>
		<td><%=c.getApellido_Cl() %></td>
		<% if(c.getSexo_Cl().equals("M")){%>
		<td>Masculino</td><%}else{%>
		<td>Femenino</td><%}%>
		<td><%=c.getCodNacionalidad_Cl().getDescripcion_N() %></td>
		<td><%=c.getCodProv_Cl().getDescripcion_P() %></td>
		<td><%=c.getCodLoc_Cl().getDescripcion_L() %></td>
		<td><%=c.getDireccion_Cl() %></td>
		<td><%=c.getCorreoElectronico_Cl() %></td>
		<td><%=c.getTelefono_Cl() %></td>
		<td><%=c.getFechaNacimiento_Cl() %></td>
		<td><%=edad%></td>
		<td><%=c.getUser_contra_Cl().getNombreUser_U()%></td>
		<td><%=c.getUser_contra_Cl().getContra_U() %></td>
	</tr>
	
	<%
	}
		}
	%>
	</tbody>

</table>

</div>

<%if(request.getAttribute("noExisteDni") != null){ %>
	<p class = "font-weight-bold text-danger text-center"><u>No existe un cliente con ese DNI en el sistema.</u></p>
<% }%>
</div>


<div class = "pieDePagina"></div>

<!-- Para la paginación hay que cambiar la libreria jquery-3.3.1.SLIM.min.js a jquery-3.3.1.min.js , sin el "slim"-->
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<!-- Paginación Table -->	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#listarClientes').DataTable({
			pageLength : 5,
		    lengthMenu: [[5, 10, 20, -1], [5, 10, 20, 'Todos']]
			
		});
	});
</script>
</body>
</html>