<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Portal Clientes</title>

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

<div class = "encabezado"> <h4 class = "text-center text-white bg-success"> <u>PORTAL CLIENTE</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaCliente.html"></jsp:include>
</div>

<div class = "parteDerecha">
	
	<h4 class = "text-center">Cuentas</h4> 
	<h5 class = "text-center"><a href="ServletCLIENTE_MenuCliente?Param=1">Cargar Cuentas</a></h5>
	 
	 <% 
		ArrayList <Cuenta> tablaListaCuentas = null;
		if(request.getAttribute("ListaCuentas") != null)
			{
				tablaListaCuentas = (ArrayList<Cuenta>)request.getAttribute("ListaCuentas"); 
			}
	%>
	
	 <table class="container">
  	<tr>
    	<th>Numero de Cuenta</th>
    	<th>CBU</th>
    	<th>Saldo</th>
    	<th>Movimientos</th>
  	</tr>
  	<tr>
  		<%		  
		  	if (tablaListaCuentas != null)
		  	{
		  			for(Cuenta x : tablaListaCuentas) 
		  			{%>
				<tbody>
				<tr>	
				<form method = "post" action = "ServletCLIENTE_Movimientos">	
					<td><%=x.getNroCuenta_CU() %><input type="hidden" name = "nroCuenta" value="<%=x.getNroCuenta_CU() %>" ></td> 
					<td><%=x.getCBU_CU() %></td>	
					<td><%=x.getSaldo_CU()%></td>
					
					<td><input type="submit" name= "btnVerMovimientos" value="Ver" class="form-control text-center my-2" style="width: 120px; "/></td>   
				</form>	
				</tr>		
				</tbody>	 
		  			<%}
		  	}%>	
	</table> 

	
</div>








<div class = "pieDePagina bg-success"></div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
