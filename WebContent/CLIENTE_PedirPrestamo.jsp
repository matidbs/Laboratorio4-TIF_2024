<%@page import="com.sun.xml.internal.fastinfoset.algorithm.IntEncodingAlgorithm"%>
<%@page import="entidad.Prestamo"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pedir Prestamos</title>

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
ArrayList<Cuenta> listaCuentas = null;
if(request.getAttribute("cuentasCliente") != null){
	listaCuentas = (ArrayList <Cuenta>)request.getAttribute("cuentasCliente");
}

Prestamo p = null;
if(request.getAttribute("Prestamo") != null){
	p = (Prestamo)request.getAttribute("Prestamo");
}

%>

<a href="ServletCLIENTE_PedirPrestamo?Param=1">Cargar cuentas</a>

<form method="post" action="ServletCLIENTE_PedirPrestamo">
	<h4 class = "text-center"><u>PEDIR PRESTAMO</u></h4>
<h5>Costo Financiero Total (CFT): 100% </h5>

    <table class="container text-center">
        <tr>
            <td><b>CUENTA <font color="red">*</font></b></td>
            <td><b>IMPORTE <font color="red">*</font></b></td>
			<td><b>CUOTAS <font color="red">*</font></b></td>
        </tr>
        <tr>
            <td class="margin: 0 auto;">
            	<select name="cuentasCliente" required>
                    <option disabled selected>Seleccionar cuenta</option>
                    <% 
  					if(listaCuentas != null)
  					for(Cuenta x : listaCuentas)  {
  				%>		
  						<option value="<%= x.getNroCuenta_CU() %>"><%= x.getNroCuenta_CU()%> - <%=x.getTipoCuenta_CU().getDescripcion_Tcu()%></option>
				<% } %>
                </select>
            </td>
            <td>
             <input type = "text" name = "txtImporte" pattern="^\d+(\.\d+)?$" required title ="Saldo: sin letras y un solo punto." required maxlength = "15">
            </td>
            <td>
            	<select name="cuotasPrestamo" required>
                    <option disabled selected>Cantidad de cuotas</option>
                    <option value = "3">3</option>
                    <option value = "6">6</option>
                    <option value = "9">9</option>
                    <option value = "12">12</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="3" class="text-center">
                <input name="btnSolicitar" type="submit" class="btn btn-success" value="Solicitar Prestamo">
            </td>
        </tr>
    </table>
</form>
<%

if(request.getAttribute("Prestamo") != null){	
	float montoCuota;
	if(p != null){
			montoCuota = p.getMontoTotalAPagar_P() / p.getCantidadCuotas_P();
		%>	
		<div class="alert alert-success" role="alert">
    <h4 class="alert-heading">¡Préstamo solicitado con éxito!</h4>
    <p>Podrá conocer el estado del mismo en <strong>Mis Préstamos</strong>.</p>
    <hr>
    <h5><u>Detalles del Préstamo:</u></h5>
    <ul>
        <li><strong>Número de Préstamo:</strong> <%= p.getNroPrestamo_P() %></li>
        <li><strong>Cuenta Solicitante:</strong> <%= p.getNroCuentaSolicitante_P() %></li>
        <li><strong>Monto Solicitado:</strong> $<%= p.getMontoSolicitado_P() %></li>
        <li><strong>Monto Total a Pagar:</strong> $<%= p.getMontoTotalAPagar_P() %></li>
        <li><strong>Cuotas Solicitadas:</strong> <%= p.getCantidadCuotas_P() %> meses</li>
        <li><strong>Importe de cada Cuota:</strong> $<%= montoCuota %></li>
    </ul>
</div>		
		<% 	
		}
}
	%>

<% 
if(request.getAttribute("NoCompleto") != null){
	%> <div class="alert alert-danger" role="alert">
    <h4 class="alert-heading">¡Error!</h4>
    <p>Por favor, complete todos los campos para continuar.</p>
</div> <% 
}

%>
		
</div>


<div class = "pieDePagina bg-success"></div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
