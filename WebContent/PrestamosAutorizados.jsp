<%@page import="entidad.DetallePrestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prestamos Autorizados</title>

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

<div class = "encabezado"> <h4 class = "text-center text-white"> <u>PRESTAMOS AUTORIZADOS</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<%
ArrayList <DetallePrestamo> tabla = null;
	if(request.getAttribute("listaAutorizadosSinFiltros") != null) tabla = (ArrayList <DetallePrestamo>)request.getAttribute("listaAutorizadosSinFiltros");
	if(request.getAttribute("listaAutorizadosFiltrado") != null) tabla = (ArrayList <DetallePrestamo>)request.getAttribute("listaAutorizadosFiltrado");
%>



<div class = "parteDerecha">
<a href="ServletDetallePrestamos?autorizados=1">Cargar Prestamos Autorizados</a>
<form method="post" action="ServletDetallePrestamos">
<table class = "font-weight-bold" >
<tr><td><u>FILTRAR PRESTAMOS POR DNI DEL CLIENTE</u></td></tr>
<tr>
	<td>DNI:<input type = "text" pattern="^\d{8}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');" title ="DNI: 8 dígitos sin espacios ni puntos." name = "txtFiltrarDni" maxlength = "8"></td>
	<td><input type = "submit" name = "btnFiltrarDni" value = "Filtrar por DNI" class = "btn" style = " margin-left: 1em "></td>
</tr>
</table>
</form>


<form method="post" action="ServletDetallePrestamos">
<table class = "font-weight-bold"  >
<tr ><td style="width:100%"><u>FILTRAR PRESTAMOS POR NOMBRE Y APELLIDO DEL CLIENTE</u></td></tr>
<tr style="width:100%">
	<td >NOMBRE:<input type = "text" pattern="^([a-zA-Z]+\s)*[a-zA-Z]+$"  title ="Nombre: solo letras y espacios." name = "txtFiltrarNombre" maxlength = "20"></td>
	<td >APELLIDO:<input type = "text" pattern="^([a-zA-Z]+\s)*[a-zA-Z]+$"  title ="Apellido: solo letras y espacios." name = "txtFiltrarApellido" maxlength = "20"></td>	
	<td><input type = "submit" name = "btnFiltrarNombreyApellido" value = "Filtrar por Cliente" class = "btn" style = " margin-left: 1em "></td>
</tr>
</table>
</form>

<form method="post" action="ServletDetallePrestamos">
<table class = "font-weight-bold" >
<tr><td><u>FILTRAR PRESTAMOS POR MONTO DE CUOTA</u></td></tr>
<tr>
	<td>
		MONTO MAYOR A : <input type = "text" size="7" pattern="^\d+(\.\d+)?$" title = "solo numeros y punto" name = "txtFiltrarMontoMIN" maxlength = "8"> 
		MENOR A : <input type = "text" size="7" pattern="^\d+(\.\d+)?$" title = "solo numeros y punto" name = "txtFiltrarMontoMAX" maxlength = "8">
	</td>
	<td ><input type = "submit" name = "btnFiltrarMonto" value = "Filtrar por Monto" class = "btn" style = " margin-left: 1em "></td>
</tr>
</table>
</form>

<form method="post" action="ServletDetallePrestamos">
<table class = "font-weight-bold" >
<tr><td><u>FILTRAR PRESTAMOS POR FECHAS</u></td></tr>
<tr>
	<td>
		FECHA MAYOR A : <input type = "date" size="10" name = "txtFiltroFechaMin" pattern="\d{4}-\d{2}-\d{2}" placeholder = "AAAA-mm-dd" title = "AAAA-mm-dd" maxlength = "10"> 
		MENOR A : <input type = "date" size="10" name = "txtFiltroFechaMax" pattern="\d{4}-\d{2}-\d{2}" placeholder = "AAAA-mm-dd" title = "AAAA-mm-dd" maxlength = "10"> 
	</td>
	<td ><input type = "submit" name = "btnFiltrarfECHA" value = "Filtrar por Fecha" class = "btn" style = " margin-left: 1em "></td>
</tr>
</table>
</form>

<form method="post" action="ServletDetallePrestamos">
<table class = "font-weight-bold" >
<tr>
	<td><input type = "submit" name = "btnMostrarTodos" value = "Mostrar todos" class = "btn" style = " margin-left: 1em "></td>
</tr>
</table><br>
</form>
<% 
if(request.getAttribute("errorFiltradoMontos") != null){
	String mensaje = request.getAttribute("errorFiltradoMontos").toString();
	%> <div class="alert alert-danger" role="alert">
    <h4 class="alert-heading">¡Error!</h4>
    <p><%=mensaje%></p>
</div> <% 
}
%>
<div class = "table-responsive">

<table id = "PrestamosAutorizados" class = "table table-hover text-center" border = "1">

<thead>
<tr>
	<!-- El nombre del cliente lo vemos con el Numero de Cuenta haciendo un inner join-->
	<th>Numero de Prestamo:</th><th>Numero De Cuota</th><th>Monto de la Cuota</th><th>Abonado con Cuenta</th><th>Cliente:</th><th>Fecha de Pago</th><th>Estado</th>
</tr>
</thead>

<tbody>
<%
		  
		  	if (tabla != null)
		  	{%>		
		  		<script>		  		
				$('#PrestamosAutorizados').DataTable().fnDestroy();
				</script>
				
				<%for(DetallePrestamo x : tabla) 
		  			{%>
				<tr>		
					<td><%=x.getNroPrestamo_DP()%></td> 
					<td><%=x.getNroCuota_DP() %></td>	
					<td><%=x.getMontoCuota_DP() %></td>
					<td><%=x.getNroCuentaPago_DP() %></td>
					<td><%=x.getNombreCliente()%></td>
					<td><%=x.getFechaPagoCuota_DP() != null ? x.getFechaPagoCuota_DP() : "-"%></td>	
					<%if(x.isEstado_DP())%><td>Pagado</td><%
					else%><td>Pago pendiente</td>
				</tr>	 
		  			<%}	
		}
%>
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
		$('#PrestamosAutorizados').DataTable( {
			pageLength : 5,
		    lengthMenu: [[5, 10, 20, -1], [5, 10, 20, 'Todos']]
			      } );
	});
</script>
</body>
</html>