<%@page import="entidad.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis prestamos</title>

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

<div class = "encabezado"> <h4 class = "text-center text-white bg-success"> <u>PRESTAMOS</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaCliente.html"></jsp:include>
</div>



<div class = "parteDerecha">
<a href="ServletCLIENTE_MisPrestamos?Param=1">Cargar tabla</a>

<% 
ArrayList<Prestamo> listaPrestamos = null;
if(request.getAttribute("ListaPrestamos") != null){
	listaPrestamos = (ArrayList<Prestamo>)request.getAttribute("ListaPrestamos");
}
%>


		<h4 class = "text-center"><u>MIS PRESTAMOS</u></h4>
		
		
<div class="table-responsive">
<table id = "solicitudesPrestamos" class = "table table-hover text-center" border="1">

<thead>
<tr>
	<th>N° de Prestamo:</th><th>N° de Cuenta solicitante:</th><th>Monto solicitado:</th><th>Cuotas solicitadas:</th>
	<th style="min-width:170px">Fecha de alta:</th><th>Cuotas pagas:</th><th style="min-width:170px">Fecha finalización:</th><th>Monto total a pagar:</th><th style="min-width:150px">Estado:</th>
</tr>
</thead>

<tbody>
<%
if(listaPrestamos != null){
	for(Prestamo p : listaPrestamos){
%>
<tr>
	<td><%= p.getNroPrestamo_P() %></td>
	<td><%= p.getNroCuentaSolicitante_P() %></td>
	<td> $<%= p.getMontoSolicitado_P() %> </td>
	<td><%= p.getCantidadCuotas_P() %></td>
	<td> <%= p.getFechaAltaPrestamo_P() %></td>
	<td><%= p.getCuotasPagas_P() %></td>
	<td><%= p.getFechaFinalizacion_P()%></td>
	<td> $<%= p.getMontoTotalAPagar_P() %></td>
	<% if(!p.getFechaAltaPrestamo_P().equals("-") || !p.getFechaFinalizacion_P().equals("-")){
		if(p.isEstado_P()){
			 %><td>Aceptado</td><%	
		}else{ %><td>Rechazado</td><%
		}
	}else{
		%><td>Revisión</td><%
	}
	%>
</tr>
<%} }%>
</tbody>

</table>
</div>
</div>

<div class = "pieDePagina bg-success"></div>


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

