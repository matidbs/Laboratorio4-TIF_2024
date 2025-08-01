<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dar de Baja Cuenta</title>

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
	<jsp:include page="/css/StyleSheet.css"></jsp:include>
</style>

</head>
<body>

<div class = "encabezado"> <h4 class = "text-center text-white"> <u>DAR DE BAJA CUENTA</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<div class = "parteDerecha font-weight-bold">

<%
ArrayList<Cuenta> listaCuentas = null;
ArrayList<Cuenta> listaCuentasNroCuenta = null;

if(request.getAttribute("listaCuentasDNI") != null){
	listaCuentas = (ArrayList<Cuenta>)request.getAttribute("listaCuentasDNI");
}

if(request.getAttribute("listaCuentasNroCliente") != null){
	listaCuentas = (ArrayList<Cuenta>)request.getAttribute("listaCuentasNroCliente");
}
%>


<table>
<tr>
<form method = "post" action = "ServletDarBajaCuenta">
<td>BUSCAR POR DNI:</td><td><input type="text" name="txtDni" pattern="^\d{8}$" required oninput="this.value = this.value.replace(/[^0-9]/g, '');" title="DNI: 8 dígitos sin espacios ni puntos." maxlength = "8" ></td>
<td><input type="submit" value="Buscar" name="btnBuscarDni" style="margin:0 1rem;"/></td>
</form>
</tr>
<tr>
<form method = "post" action = "ServletDarBajaCuenta">
<td>BUSCAR POR NÚMERO DE CUENTA:</td><td> <input type="text" name="txtNumeroCuenta" required></td>
<td><input type="submit" value="Buscar" name="btnBuscarNumeroCuenta" style="margin:0 1rem;" /></td>
</form>
</tr>
</table>


<table id = "listarResultados" class = "table table-hover text-center" border = "1" style="margin-top:2rem;">

<thead>
<tr>
	<th>NÚMERO DE CUENTA</th><th>DNI</th><th>FECHA DE CREACIÓN</th><th>TIPO DE CUENTA</th><th>CBU</th><th>SALDO</th><th></th>
</tr>
</thead>

<tbody>
<%
if(listaCuentas != null){
for (Cuenta c: listaCuentas){	%>

<tr>
	<form method= "post" action="ConfirmarBajaCuenta.jsp">
	<!-- Input escondido para llevarme el número cuenta por fila -->
	
	<td><%=c.getNroCuenta_CU() %> <input type="hidden" name="nroCuentaHidden" value="<%= c.getNroCuenta_CU() %>"/></td>
	<td><%=c.getDniCliente_CU() %></td>
	<td><%=c.getFechaCreacion_CU() %></td>
	<td><%=c.getTipoCuenta_CU().getDescripcion_Tcu() %></td>
	<td><%=c.getCBU_CU() %></td>
	<td><%=c.getSaldo_CU() %></td>
		<td>
		<input type="submit" value="Dar de Baja" name="btnDarBaja" />
	</td>
	</form>
</tr>
<% }} %>
</tbody>

</table>

<%
boolean estado = false;

if(request.getAttribute("Elimino") != null){
	estado = Boolean.parseBoolean(request.getAttribute("Elimino").toString());
}

if(estado){
	%>
<p class = "font-weight-bold text-center text-danger">Cuenta dada de baja con éxito.</p>	
	<% 
}
%>

	
</div>

<div class = "pieDePagina"></div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>