<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solicitudes de Prestamos</title>

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

<div class = "encabezado"> 
	<h4 class = "text-center text-white"> 
		<u>SOLICITUDES DE PRESTAMOS</u> 
	</h4> 
</div>

<div class = "parteIzquierda">
	<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
	<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<div class = "parteDerecha">
<a href="ServletPrestamos?Param=1">Cargar Solicitudes</a>
	<table class = "font-weight-bold">
		<tr>
			<td><u>FILTRAR PRESTAMOS POR CUENTAS DE CLIENTE</u></td>
		</tr>
		<tr>
		<form method="GET" action="ServletPrestamos">
			<td>INGRESE DNI:
	 			<input type = "text" pattern="^\d{8}$" title ="DNI: 8 dígitos sin espacios ni puntos." name = "txtFiltrarCuentasPrestamos" maxlength = "8">
	 		</td>
			<td>
				<input type = "submit" name ="btnCargarCuentas" value = "Cargar cuentas" class = "btn" 
				style ="margin-left: 1em">
			</td>
		</form>
	</tr>
	<tr>
		<form method="GET" action="ServletPrestamos">
		<td>
			CUENTAS:
			<select id = "filtrarCuentas" name="filtrarCuentas">
				<option disabled = "disabled" value="disabled">---Seleccionar---</option>
			<%
				ArrayList<Cuenta> listaCuentas = null;
				
				if(request.getAttribute("listaCuentas") != null) {
					listaCuentas = (ArrayList<Cuenta>)request.getAttribute("listaCuentas");
					for(Cuenta c : listaCuentas){
			 %>
	 			<option value="<%= c.getNroCuenta_CU() %>"><%= c.getNroCuenta_CU() %></option>
			 <% }} %>
			</select>
		</td>
		<td>
			<input type="submit" name="btnFiltrarCuentas" value="Filtrar por cuenta" class="btn" style="margin-left: 1em">
		</td>
		</form>
	</tr>
	<tr>
		<td>
			<u>FILTRAR POR MONTO TOTAL A PAGAR</u>
		</td>
	</tr>
	<tr>
		<td>
		<form method="GET" action="ServletPrestamos">
			SUPERIOR A
			<select id = "montoSuperior" name="montoSuperior">
				<option>5000</option>
				<option>10000</option>
				<option>15000</option>
				<option>20000</option>
				<option>25000</option>
				<option>30000</option>
			</select>
			E INFERIOR A
			<select id = "montoInferior" name="montoInferior">
				<option>15000</option>
				<option>25000</option>
				<option>35000</option>
				<option>45000</option>
				<option>55000</option>
				<option>65000</option>
			</select>
		</td>
		
		<td>
			<input type="submit" name="btnFiltrarMontos" value="Filtrar por monto" class="btn" style="margin-left: 1em">
		</td>
		</form>
		<form method="GET" action="ServletPrestamos">
		<td>
			<input type="submit" name="btnMostrarTodos" value="Mostrar todos" class="btn" style=" margin-left: 1em">
		</td>
		</form>
	</tr>
</table>
<%
	boolean existeDni = true;
	if(request.getAttribute("existeDni") != null){
		existeDni = (boolean)request.getAttribute("existeDni");
	}
	
	if(!existeDni){
 %>
<div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>No existe el DNI ingresado en el sistema.</p> </div>
<% } 
	boolean filtrarMonto = true;
	if(request.getAttribute("filtrarMonto") != null){
		filtrarMonto = (boolean)request.getAttribute("filtrarMonto");
	}
	
   	if(!filtrarMonto){
%>
<div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>El monto superior no puede ser mayor al inferior.</p> </div>
<% }

	boolean cuentaSeleccionada = true;
	if(request.getAttribute("cuentaSeleccionada") != null){
		cuentaSeleccionada = (boolean)request.getAttribute("cuentaSeleccionada");
	}
	
	if(!cuentaSeleccionada){
 %>
<div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>Seleccione una cuenta.</p> </div>

<% } %>



<div>
<% 
if(request.getAttribute("prestamoAceptado") != null){%>
	<div class="alert alert-success" role="alert"><p class = "font-weight-bold text-center"> Prestamos Autorizado con exito, para mas detalles ingrese a Gestion de Prestamos > Prestamos Autorizados .</p></div>
			<%}
			else
			{
				if(request.getAttribute("prestamoRechazado") != null){%><div class="alert alert-success" role="alert"><p class = "font-weight-bold text-center"> Prestamos rechazado.</p></div> <%}
				
			}%>
</div>
<div class = "table-responsive">
	<table id = "solicitudesPrestamos" class = "table table-hover text-center" border = "1">
		<thead>
		<tr>
			<th>N° de Prestamo:</th>
			<th>DNI del Cliente solicitante:</th>
			<th>N° de Cuenta solicitante:</th>
			<th>Monto solicitado:</th>
			<th>Cuotas solicitadas:</th>
			<th style="min-width:170px">Fecha de alta:</th>
			<th>Cuotas pagas:</th>
			<th style="min-width:170px">Fecha finalización:</th>
			<th>Monto Total a Pagar:</th>
			<th colspan="2" style="min-width:210px">Aceptar/Rechazar</th>
		</tr>
		</thead>
		<tbody>
		<!-- CARGA TODAS LAS SOLICITUDES EN LA TABLA -->
		<%
		ArrayList<Prestamo> listaSolicitudes = null;
		if(request.getAttribute("listaSolicitudes") != null){
			listaSolicitudes = (ArrayList<Prestamo>)request.getAttribute("listaSolicitudes");
			for(Prestamo p : listaSolicitudes){
		 %>
		<tr>
			<form method="POST" action="ServletPrestamos">
				<td><%= p.getNroPrestamo_P() %> <input type="hidden" name="nroPrestamo" value="<%= p.getNroPrestamo_P() %>"></td>
				<td><%= p.getDniCliente_P() %></td>
				<td><%= p.getNroCuentaSolicitante_P() %><input type="hidden" name="nroCuentaSolicitante" value="<%= p.getNroCuentaSolicitante_P() %>"></td>
				<td><%= p.getMontoSolicitado_P() %><input type="hidden" name="montoSolicitado" value="<%= p.getMontoSolicitado_P() %>"></td>
				<td><%= p.getCantidadCuotas_P() %><input type="hidden" name="cantidadCuotas" value="<%= p.getCantidadCuotas_P() %>"></td>
				<td><%= p.getFechaAltaPrestamo_P() %></td>
				<td><%= p.getCuotasPagas_P() %></td>
				<td><%= p.getFechaFinalizacion_P() %></td>
				<td><%= p.getMontoTotalAPagar_P() %><input type="hidden" name="montoTotalAPagar" value="<%= p.getMontoTotalAPagar_P() %>"></td>
				<td>
					<input type = "submit" name = "btnAceptar" value = "Aceptar" class = "btn">
				</td>
				<td>
					<input type = "submit"  name = "btnRechazar" value = "Rechazar" class = "btn">
				</td>
			</form>
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
		$('#solicitudesPrestamos').DataTable();
	});
</script>
</body>
</html>