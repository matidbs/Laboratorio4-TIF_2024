<%@page import="entidad.Provincia"%>
<%@page import="entidad.Mes"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Reporte"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte de Cuentas</title>

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

<div class = "encabezado"> <h4 class = "text-center text-white"> <u>REPORTE DE CUENTAS</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<div class = "parteDerecha">

<a href="ServletReportes?Param=1">Cargar Filtros y Tabla</a>

<% 
	ArrayList <Provincia> listaProvincias = null;
	ArrayList <Mes> listaMeses = null;
	if(request.getAttribute("Provincias") != null)
	{
		listaProvincias = (ArrayList <Provincia>)request.getAttribute("Provincias"); //casteo
	}	
	if(request.getAttribute("Meses") != null)
	{
		listaMeses = (ArrayList <Mes>)request.getAttribute("Meses"); //casteo
	}
	
	%>

	<br>
	<u><b>CANTIDAD DE CUENTAS EXISTENTES:</b></u><br>
	<u><i>Filtre la cantidad de cuentas creadas por provincia. O filtre por provincia y localidad especifica.</i></u>

	<table class = "font-weight-bold" >
		<tr><td><u>FILTRAR POR PROVINCIA :</u></td>


		<form method="post" action="ServletReportes"> 
			<td>	
				<select name="provincias">
  				<% 
  					if(listaProvincias != null)
  					for(Provincia x : listaProvincias)  {
  				%>		
  						<option value="<%= x.getCodProv_P() %>"><%= x.getDescripcion_P() %></option>
				<% } %>
  				</select>
			</td>
			<td style="min-width:120px">
			<input type = "submit" name = "btnFiltrarProvincia" value = "Filtrar" class = "btn" style = " margin-left: 1em ">
		</form>
			</td>
	
	
	<td><u>FILTRAR POR LOCALIDAD:</u></td>
	<% 
	ArrayList <Localidad> listaLocalidades = null;
	if(request.getAttribute("FiltroLocalidades") != null)
	{
		listaLocalidades = (ArrayList <Localidad>)request.getAttribute("FiltroLocalidades"); //casteo
	}
	
	%>
	<form method="post" action="ServletReportes"> 
	<td>
	<select name= "localidades">
	<%if(listaLocalidades != null)
	  {  
	  for(Localidad x : listaLocalidades) //recorro con un for
		{%>
  			<option value=<%=x.getCodLoc_L()%>><%=x.getDescripcion_L()%> </option>
  						<%}%>			
		<%} %> 
	</select>
	</td>
	<td>
	
	<input type = "submit" name = "btnFiltrarLocalidad" value = "Filtrar" class = "btn" style = " margin-left: 1em ">
</form>
	</td>
	</tr>
	</table>

<br>


<u><i>Filtre la cantidad de cuentas creadas en un año especifico. Tambien puede filtrar por año y mes especifico</i></u>

<table class = "font-weight-bold" >
	<form method="post" action="ServletReportes">
	<tr>
		<td><u>FILTRAR POR AÑO:</u></td>
		
		<%if(request.getAttribute("AnioFiltrado") != null)
		{%>
			<td><input type = "text" pattern="(19[89][0-9]|20[0-2][0-4]|2024)"  title ="Ingrese una fecha correcta" name = "txtFiltrarAnio" 
			 value = "<%=request.getAttribute("AnioFiltrado").toString()%>"maxlength = "4"></td>
		<td style="min-width:120px">
		<%}else
		{%>
			<td><input type = "text" pattern="(19[89][0-9]|20[0-2][0-4]|2024)"  title ="Ingrese una fecha correcta" name = "txtFiltrarAnio"  maxlength = "4"></td>
			<td style="min-width:120px">
		<%}
		%>		
		<input type = "submit" name = "btnFiltrarAnio" value = "Filtrar" class = "btn" style = " margin-left: 1em ">	
		</td>
	
		<td><u>FILTRAR POR MES:</u></td>
		<td>
		<select name = "meses">
		<%if(listaMeses != null)
		  {  
		  for(Mes x : listaMeses) //recorro con un for
			{%>
	  			<option value=<%=x.getCodMes_M()%>><%=x.getDescripcion_M()%> </option>
	  						<%}%>			
			<%} %> 
		</select>
		</td>
		
		<td>		
		<input type = "submit" name = "btnFiltrarMes" value = "Filtrar" class = "btn" style = " margin-left: 1em ">	
		</td>
	</tr>
	</form>
</table>
	<br>
<table class = "font-weight-bold" >	
	<u><i>Filtre las cuentas por Saldos</i></u>
	
	<form method="post" action="ServletReportes">
	<tr>
		<td><u>SALDOS: </u></td>
		<td>
		<select name = "operador">
	  			<option value= "=">IGUALES </option>	  	
	  			<option value= ">">SUPERIORES </option>
	  			<option value= "<">INFERIORES </option>	 	 				
		</select>
		</td>
		<td style="min-width:20px"><u> A: </u></td style="min-width:20px">
		<td><input type = "text" pattern="^\d+(\.\d+)?$"  title ="Ingrese solo numeros y un punto" name = "txtSaldo"  maxlength = "8"></td>
		<td><input type = "submit" name = "btnFiltrarSaldo" value = "Filtrar" class = "btn" style = " margin-left: 1em ">	</td>
	</tr>	
	
	</form>
</table>	
	
<table class = "font-weight-bold" >
	<form method="post" action="ServletReportes">
	<tr><td><u>LIMPIAR FILTROS:</u></td>
	<td><input type = "submit" name = "btnMostrarTodos" value = "Mostrar todos" class = "btn" style = " margin-left: 1em "></td>
	</form>
	</tr>	
</table>


	<br>
<%	
	int cantidadCuentasExistentes= 0;
	if(request.getAttribute("CantidadRegistros") != null)
	{
		cantidadCuentasExistentes = (int)request.getAttribute("CantidadRegistros") ;
	}
	
%>
<u><b>RESULTADO:</b></u> <%=cantidadCuentasExistentes%>
<br><br>



<%	
	ArrayList <Reporte> tabla = null;
	if(request.getAttribute("ReportesSinFiltro") != null) tabla = (ArrayList <Reporte>)request.getAttribute("ReportesSinFiltro");
	else
		if (request.getAttribute("ReportesFiltroProvincia") != null) tabla = (ArrayList<Reporte>)request.getAttribute("ReportesFiltroProvincia");
		else
			if (request.getAttribute("ReportesFiltroProvinciaLocalidad") != null) tabla = (ArrayList <Reporte>)request.getAttribute("ReportesFiltroProvinciaLocalidad");
			else
				if (request.getAttribute("ReportesFiltroAnio") != null) tabla = (ArrayList<Reporte>)request.getAttribute("ReportesFiltroAnio");
				else
					if(request.getAttribute("ReportesFiltroAnioMes") != null)tabla  =  (ArrayList<Reporte>)request.getAttribute("ReportesFiltroAnioMes");
					else tabla  =  (ArrayList<Reporte>)request.getAttribute("ReportesFiltroSaldo");
%>

<div class = "table-responsive">
	<table id = "ReporteCuentas" class="container text-center" border = "1">
	
	<thead>
	<tr>
		<th>Número de Cuenta:</th><th>DNI del cliente</th><th>Nombre y Apellido</th><th>Provincia</th><th>Localidad</th><th>Fecha creación cuenta</th><th>Tipo de Cuenta</th><th>CBU</th><th>Saldo</th><th>Estado</th>
	</tr>
	</thead>
	
	<tbody>
		  <%
		  
		  	if (tabla != null)
		  	{		  	
		  			for(Reporte x : tabla) 
		  			{%>
				<tr>		
					<td><%=x.getNumeroDeCuenta() %></td> 
					<td><%=x.getDniDelCliente() %></td>	
					<td><%=x.getApellidoYNombre() %></td>
					<td><%=x.getProvincia() %></td>
					<td><%=x.getLocalidad() %></td>
					<td><%=x.getFechaDeCreacion() %></td>
					<td><%=x.getTipoDeCuenta() %></td>
					<td><%=x.getCbu() %></td>
					<td><%=x.getSaldo() %></td>
					<%if(x.isEstado())
					{%>
					<td>Activo</td>
					<%}
					else{%>
					<td>Inactivo</td>	
				</tr>			 
		  	<%			}
					}
		  	}%>			
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
		$('#ReporteCuentas').DataTable({
		pageLength : 5,
	    lengthMenu: [[5, 10, 20, -1], [5, 10, 20, 'Todos']]});
	});
</script>
</body>
</html>