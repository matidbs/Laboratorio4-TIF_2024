<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entidad.tipoDeCuenta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Cuenta</title>

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
	<jsp:include page="/css/StyleSheet.css"></jsp:include>
</style>

</head>
<body>
<div class = "encabezado"> <h4 class = "text-center text-white"> <u>AGREGAR CUENTA</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<div class = "parteDerecha font-weight-bold">

<a href="ServletAgregarCuenta?Param=1">Cargar descolgables</a>

<%
ArrayList<tipoDeCuenta> listaTipoCuenta = null;
if(request.getAttribute("TipoDeCuenta") != null){
	listaTipoCuenta = (ArrayList <tipoDeCuenta>)request.getAttribute("TipoDeCuenta");
}
%>

<form method = "post" action = "ServletAgregarCuenta">

<h4 class = "text-center"><u>DATOS DE LA CUENTA</u></h4>
<table>

<tr>
<td>INGRESE DNI DEL CLIENTE:</td><td><input type = "text" pattern="^\d{8}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');" title ="DNI: 8 dígitos sin espacios ni puntos." name = "txtDni" required placeholder = "Inserte DNI de 8 dígitos" maxlength = "8"></td>




</tr>

<tr> 
<td>SELECCIONE TIPO DE CUENTA:</td><td> <select name = "tipoCuenta" required>
<% 
  					if(listaTipoCuenta != null)
  					for(tipoDeCuenta x : listaTipoCuenta)  {
  				%>		
  						<option value="<%= x.getCodTipoCuenta_Tcu() %>"><%= x.getDescripcion_Tcu() %></option>
				<% } %>
</select>
</td>
</tr>

<tr>
<td> 
<input type="submit" name = "btnAgregar" value="Agregar" style="width: 120px; "/>
</td>
</tr>

</table>

<%
if(request.getAttribute("NoExiste") != null){

	int rta = (int)(request.getAttribute("NoExiste"));
	if(rta == 1)
			{%>
				<div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>El cliente no existe.</p> </div>
			<%}
}
else
	{
		if(request.getAttribute("datoscuenta") != null)
		{
			Cuenta aux = (Cuenta)request.getAttribute("datoscuenta");
			if(aux.getDniCliente_CU() != "-1")
			{
				%>	
				<div class="alert alert-success" role="alert">
				    <h4 class="alert-heading">¡Cuenta asignada con éxito!</h4>
				    <hr>
				    <h5><u>Detalles de la cuenta:</u></h5>
				    <ul>
				        <li><strong>Número de cuenta:</strong> <%= aux.getNroCuenta_CU() %></li>
				        <li><strong>Número de CBU:</strong> <%= aux.getCBU_CU() %></li>
				        <li><strong>DNI del cliente:</strong> <%= aux.getDniCliente_CU() %></li>
				        <li><strong>Tipo de cuenta:</strong> <%= aux.getTipoCuenta_CU().getDescripcion_Tcu() %></li>
				        <li><strong>Saldo:</strong> $<%= aux.getSaldo_CU() %></li>
				    </ul>
				</div> 	
				<%
			}
			else
			{%>
			<div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>El cliente con ese DNI ya tiene 3 cuentas asignadas.</p> </div>
			<%}
		}
	}%>

</form>
	

</div>

<div class = "pieDePagina"></div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>