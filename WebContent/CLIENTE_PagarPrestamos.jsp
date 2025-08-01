<%@page import="entidad.Cuenta"%>
<%@page import="entidad.DetallePrestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagar prestamos</title>

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

<a href="ServletCLIENTE_PagarPrestamos?Param=1">Cargar descolgable y tabla</a>
	<h4 class = "text-center"><u>PAGAR PRESTAMOS</u></h4>
<% 
ArrayList<Cuenta> lista = null;
ArrayList<DetallePrestamo> listadto = null;


if(request.getAttribute("cuentasCliente") != null){
	lista = (ArrayList<Cuenta>)request.getAttribute("cuentasCliente");
}

if(request.getAttribute("dtoPrestamosCliente") != null){
	listadto = (ArrayList<DetallePrestamo>)request.getAttribute("dtoPrestamosCliente");
}
%>
	
<!-- Mensajes aclaratorios  -->
<table class="container">
	<%
		boolean pagoExitoso = false;
		if(request.getAttribute("pagoExitoso") != null){
			pagoExitoso = (boolean)request.getAttribute("pagoExitoso");
		}
	
   		if(pagoExitoso){
	 %>
	<tr>
		<td></td>	
		<td><div class="alert alert-success" role="alert"><p>Se pagó la cuota con éxito</p></div></td>	
		<td></td>	
	</tr>
	<% } 
		boolean cuentaSeleccionada = true;
		if(request.getAttribute("cuentaSeleccionada") != null){
			cuentaSeleccionada = (boolean)request.getAttribute("cuentaSeleccionada");
		}
	
   		if(!cuentaSeleccionada){
	%>
	<tr>
		<td></td>	
		<td><div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>Seleccione una cuenta.</p> </div> </td>	
		<td></td>	
	</tr>
	<% }
		boolean saldoInsuficiente = true;
		if(request.getAttribute("saldoInsuficiente") != null){
			saldoInsuficiente = (boolean)request.getAttribute("saldoInsuficiente");
		}
	
   		if(!saldoInsuficiente){
	 %>
		<tr>
		<td></td>	
		<td><div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>Saldo insuficiente en la cuenta.</p> </div> </td>	
		<td></td>	
	</tr>
	<% } %>
</table>
	


<!-- Tabla de cuotas -->
<div class = "table-responsive">
<table id = "pagarPrestamos" class = "table table-hover text-center" border = "1">

<thead>
<tr>
	<th>Numero de Prestamo:</th><th>Numero De Cuota</th><th>Monto de la Cuota</th><th>Número de Cuenta Solicitante:</th><th>Fecha de Pago</th><th>Seleccionar cuenta de pago</th><th>Cuenta de pago</th><th>Estado</th>
</tr>
</thead>

<tbody>
<%
		if(listadto != null){
			for(DetallePrestamo dp : listadto){
			%>
			<tr>
				<form method="post" action="ServletCLIENTE_PagarPrestamos">
				<td><%=dp.getNroPrestamo_DP()%> <input type="hidden" name="nroPrestamo" value=<%=dp.getNroPrestamo_DP() %>></td>
				<td><%=dp.getNroCuota_DP()%> <input type="hidden" name="nroCuota" value=<%=dp.getNroCuota_DP() %>></td>
				<td><%=dp.getMontoCuota_DP()%> <input type="hidden" name="montoCuota" value=<%=dp.getMontoCuota_DP() %> ></td>
				<td><%=dp.getNrocuentaSolicitante()%></td>
				<td><%=dp.getFechaPagoCuota_DP() != null? dp.getFechaPagoCuota_DP() : "-"%></td>
						<td>
			<select name="seleccionarCuenta" required>
			<option disabled selected value="disabled">Seleccionar</option>
			<% if(lista != null){
				
				for(Cuenta c : lista){
					%><option value="<%= c.getNroCuenta_CU() %>"><%= c.getNroCuenta_CU() %></option> <% 
				}	}%>
			</select>
		</td>
				<td><%=dp.getNroCuentaPago_DP() != 0 ? dp.getNroCuentaPago_DP() : "-" %></td>				
				<%if(dp.isEstado_DP())%><td>Pagado</td><%
				else%><td><input type="submit" name="btnPagar" value ="Pagar" class="btn"></td>
				</form>
			</tr>
			<%}
	 	}%>
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
		$('#pagarPrestamos').DataTable({
			pageLength : 5,
		    lengthMenu: [[5, 10, 20, -1], [5, 10, 20, 'Todos']]});
	});
</script>
</body>
</html>