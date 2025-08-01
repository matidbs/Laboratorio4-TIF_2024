<%@page import="entidad.tipoDeMovimiento"%>
<%@page import="entidad.Movimiento"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movimientos</title>

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
	
	<% 
		int numCuenta = 0;
		ArrayList <Movimiento> tablaListaMovimientos = null;
		if(request.getAttribute("Movimientos") != null)
			{
				tablaListaMovimientos = (ArrayList<Movimiento>)request.getAttribute("Movimientos"); 
			}
		ArrayList<tipoDeMovimiento> listaTipo = null;
		if(request.getAttribute("ListaTipos") != null){
			listaTipo = (ArrayList<tipoDeMovimiento>)request.getAttribute("ListaTipos");
			numCuenta = (int)request.getAttribute("numCuenta");
		}
		
		
	%>


<div class="w-auto p-3 h-auto">
<form method="post" action="ServletCLIENTE_Movimientos">
<input type="hidden" value="<%= numCuenta %>" name="numCuenta">
<table class="font-weight-bold">
	<tr>
		<td>FILTRAR POR TIPO DE MOVIMIENTO:</td> 
		<td></td>
		<td> <select name=seleccionarTipo>
								<%
								if(listaTipo!=null){
									for(tipoDeMovimiento t : listaTipo){
										%><option value="<%= t.getCodTipoMovimiento_TM() %>"><%= t.getDescripcion_TM() %></option><%
									}
								}
								%>
							   </select>	
						  </td>
		<td></td>
		<td> <input type="submit" class="btn" value="Filtrar" name="btnFiltrar"> </td>
		<td></td>
		<td> <input type="submit" class="btn" value="Mostrar todos" name="btnMostrarTodos"> </td>

	</tr>	
</table>
</form>
</div>				  

<div class="table-responsive">
	<table id="listarMovimientos" class = "table table-hover text-center" border = "1">
		<thead>
		<!-- Columnas -->
			<tr>
				<th>Numero de movimiento</th><th>Numero de cuenta</th><th>Detalle</th><th>Importe</th><th>Fecha</th>
			</tr>
		</thead>
		
		<!-- Filas -->
		<%		  
		  	if (tablaListaMovimientos != null)
		  	{
		  			for(Movimiento x : tablaListaMovimientos) 
		  			{%>
							<tbody>
							<tr>		
								<td><%=x.getNroMovimiento_M()%></td> 
								<td><%=x.getCuenta_M().getNroCuenta_CU()%></td>	
								<td><%=x.getDetalle_M()%></td>	
								<td><%=x.getImporte_M()%></td>							
								<td><%=x.getFecha_M()%></td>	   						
							</tr>		
							</tbody>	 
		  			<%}
		  	}%>	
	
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
		$('#listarMovimientos').DataTable();
	});
</script>
</body>
</html>

