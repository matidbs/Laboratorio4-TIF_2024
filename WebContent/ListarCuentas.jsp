<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar Cuentas</title>

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
	<jsp:include page="/css/StyleSheet.css"></jsp:include>
</style>

<!-- Paginación Table -->

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#listarCuentas').DataTable();
	});
</script>

</head>
<body>

<div class = "encabezado"> <h4 class = "text-center text-white"> <u>LISTAR CUENTAS</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<div class = "parteDerecha">

<%
ArrayList<Cuenta> listaCuentas = null;

if(request.getAttribute("listaCuentas") != null){
	listaCuentas = 	(ArrayList<Cuenta>)request.getAttribute("listaCuentas");
}

if(request.getAttribute("cuentaPorDni") != null){
	listaCuentas = 	(ArrayList<Cuenta>)request.getAttribute("cuentaPorDni");
}

if(request.getAttribute("cuentaPorNro") != null){
	listaCuentas = 	(ArrayList<Cuenta>)request.getAttribute("cuentaPorNro");
}

%>

<table class = "font-weight-bold">
<form method="post" action="ServletListarCuentas">
<tr>
<td>FILTRAR POR:
</tr>
<tr>
<td>NUMERO DE CUENTA (activas o inactivas):</td><td><input type = "text" name = "txtNroCuenta" required></td>
<td><input type = "submit" name = "btnBuscarCuenta" value = "Buscar" class = "form-control"></td>
</tr>
</form>
<form method="post" action="ServletListarCuentas">
<tr>
<td>DNI (solo activas):</td><td><input type = "text" required pattern="^\d{8}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');" title ="DNI: 8 dígitos sin espacios ni puntos." name = "txtBuscarDni" maxlength = "8"></td>
<td><input type = "submit" name = "btnBuscarDni" value = "Buscar" class = "form-control"></td>
</tr>
</form>
<form method="post" action="ServletListarCuentas">
<tr>
<td><input type = "submit" name = "btnMostrarTodos" value = "Mostrar todas las cuentas" class = "form-control"></td>
<td><input type = "submit" name = "btnMostrarTodosInactivos" value = "Mostrar todas las cuentas Inactivas" class = "form-control"></td>
</tr>
</form>
</table>

<div class = "table-responsive">
<table id = "listarCuentas" class = "table table-hover text-center" border = "1">

<thead>
<tr>
	<!-- El nombre del cliente los traemos con un inner join -->
	<th>NÚMERO DE CUENTA</th><th>DNI</th><th style="min-width:170px">FECHA DE CREACIÓN</th><th>TIPO DE CUENTA</th><th>CBU</th><th>SALDO</th>
</thead>


<tbody>
<%
if(listaCuentas != null){
for (Cuenta c: listaCuentas){	%>

<tr>	
	<td><%=c.getNroCuenta_CU() %> <input type="hidden" name="nroCuentaHidden" value="<%= c.getNroCuenta_CU() %>" /></td>
	<td><%=c.getDniCliente_CU() %></td>
	<td><%=c.getFechaCreacion_CU() %></td>
	<td><%=c.getTipoCuenta_CU().getDescripcion_Tcu() %></td>
	<td><%=c.getCBU_CU() %></td>
	<td><%=c.getSaldo_CU() %></td>

</tr>
<% }} %>
</tbody>

</table>
</div>

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
		$('#listarCuentas').DataTable();
	});
</script>
</body>
</html>