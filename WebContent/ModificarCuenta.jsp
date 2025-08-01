<%@page import="entidad.tipoDeCuenta"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Cuenta</title>

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
<jsp:include page="/css/StyleSheet.css"></jsp:include>
</style>

</head>
<body>

<div class = "encabezado"> <h4 class = "text-center text-white"> <u>MODIFICAR CUENTA</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaAdmin.html"></jsp:include>
</div>

<div class = "parteDerecha font-weight-bold" style="height:600px;">

<form method = "post" action = "ServletModificarCuenta">

<div>
  <table class="text-center" style="margin: 0 auto;">
    <tr>
      <td>Buscar por número de cuenta</td>
      <td style="padding: 0 1rem;">
      	<input type="text" name="txtBuscarNroCuenta" />
      </td>
      <td>
      	<input type="submit" name="btnSeleccionar" value="Seleccionar" />
      </td>
    </tr>
    
    <tr>
      <td colspan=3>
            	<%
	      		ArrayList<Cuenta> listaCuentas = null;
	      		ArrayList<tipoDeCuenta> listaTipoCuenta = null;
	      		tipoDeCuenta tc = new tipoDeCuenta();
	      		
	      		
	      		Cuenta x = new Cuenta();
	      		if(request.getAttribute("cuentaPorNro") != null){
	      			listaCuentas = (ArrayList<Cuenta>)request.getAttribute("cuentaPorNro");
	      			x = listaCuentas.get(0);
	      		}
	      		if(request.getAttribute("TipoDeCuenta") != null){
	      			listaTipoCuenta = (ArrayList<tipoDeCuenta>)request.getAttribute("TipoDeCuenta");
	      			tc = listaTipoCuenta.get(0);
	      		}

        		
        		if(request.getAttribute("txtVacio") != null) {
        				if((boolean)request.getAttribute("txtVacio")){
        		%><div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>Ingrese un número de cuenta.</p> </div>
<% 
        				}
        		}
        		if(request.getAttribute("existeCuenta") != null){
					if(!(boolean)request.getAttribute("existeCuenta")){
				%>	<div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>No existe la cuenta.</p> </div>

				<% }} %>
      </td>
    </tr>
  </table>	
</div>
</form>


<form method = "post" action="ServletModificarCuenta">
<h4 class = "text-center"><u>DATOS DE LA CUENTA</u></h4>
	<table>
	
		<tr>
		<td>DNI:</td>
		<td>
		<input type = "text" pattern="^\d{8}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');" title ="DNI: 8 dígitos sin espacios ni puntos." name = "txtDni" required maxlength = "8"
			 value="<%= x.getDniCliente_CU() != null ? x.getDniCliente_CU() : " " %>"
		>
		</td>
		<td>FECHA DE CREACION:</td>
		<td>
		<% if(listaCuentas != null){ %>
			<p><%= x.getFechaCreacion_CU() %></p>
		<% }%>
		</td>
		</tr>
		
		<tr>
		<td>TIPO DE CUENTA:</td>
		<td>
		<% if (listaTipoCuenta != null) {%>
		<select id="tipoCuenta" name="tipoCuenta">
		        <option value="Ahorro" <%= tc.getCodTipoCuenta_Tcu() == 2 ? "selected" : "" %>>Caja de Ahorro</option>
		        <option value="Corriente" <%= tc.getCodTipoCuenta_Tcu() == 1 ? "selected" : "" %>>Cuenta Corriente</option>
		 </select>
		 <% } else  { %>
		 <select id="tipoCuenta" name="tipoCuenta">
		        <option value="Ahorro" <%= tc.getCodTipoCuenta_Tcu() == 2 ? "selected" : "" %>>Caja de Ahorro</option>
		        <option value="Corriente" <%= tc.getCodTipoCuenta_Tcu() == 1 ? "selected" : "" %>>Cuenta Corriente</option>
		 </select> <% } %>
		</td>
		<td>SALDO:</td><td><input type = "text" name = "txtSaldo"
		title ="Saldo: sin letras y un solo punto." required maxlength = "15" value="<%= x != null ? (double)x.getSaldo_CU() : " " %>"></td>
		</tr>
		<tr>
		<td>ESTADO:</td>
		<% 
		boolean estado = false;
		if(listaCuentas != null) {
			estado = x.isEstado_CU();
		}
		if(estado){
		%>
		<td>Activar <input type = "radio" name="estado" value = "Activar" checked="checked" > Desactivar<input type = "radio" name="estado" value = "Desactivar" requiered ></td>
		<%}else{ %>
		<td>Activar <input type = "radio" name="estado" value = "Activar" > Desactivar<input type = "radio" name="estado" value = "Desactivar" requiered checked="checked"></td>
		<% } %>
	</table>
	<p style = "text-align: center;"> 
	<input type="submit" name="btnAceptar" value="Aceptar">
	</p>
	
	<%if(request.getAttribute("existeDni") != null)
	{
		if((int)(request.getAttribute("existeDni")) == 1)%><div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>El DNI ingresado no existe en el sistema.</p> </div>
<%		
		else{
					if(request.getAttribute("modificacion") != null)
					{
						if((int)(request.getAttribute("modificacion")) == 1)%> <div class="alert alert-success" role="alert"><p>La cuenta ha sido modificado con éxito.</p></div><%
						else%> <div class="alert alert-danger" role="alert"> <h4 class="alert-heading">¡Error!</h4> <p>El cliente con ese DNI ya tiene 3 cuentas asignadas.</p> </div>
<%
								
					}		
			}
	}
	%>
	
	
</form>
</div>

<div class = "pieDePagina"></div>
</html>