<%@page import="entidad.Cuenta"%>
<%@page import="entidad.tipoDeCuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transferencias</title>

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

<div class = "encabezado"> <h4 class = "text-center text-white bg-success"> <u>TRANSFERENCIA</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaCliente.html"></jsp:include>
</div>

<div class = "parteDerecha">
<h5 class = "text-center"><a href="ServletCLIENTE_Transferencia?Param=1">Cargar Cuentas a Debitar</a></h5>
	
	<% 
		ArrayList <Cuenta> tablaListaCuentas = null;
		if(request.getAttribute("ListaCuentas") != null)
			{
				tablaListaCuentas = (ArrayList<Cuenta>)request.getAttribute("ListaCuentas"); 
			}
		
		//Me fijo el atribute de seleccioneCuenta
		if(request.getAttribute("SeleccioneCuenta") != null){
			%>
			<div class="alert alert-danger" role="alert">
			<h4 class="alert-heading">¡Error!</h4>
			<p>Seleccione la cuenta a debitar.</p>
			</div>
			<% 
		}
		
		
		//Al volver del ServletCliente_Transferencia despues de apretar "Transferir"
		
		//me fijo si el cliente está activo
		if(request.getAttribute("verificarCliente") != null)
		{%>
		<div class="alert alert-danger" role="alert">
		<h4 class="alert-heading">¡Error!</h4>
		<p>No existe el cliente destino o se encuentra deshabilitado.</p>
		</div>
		<%}
		else
		{
			//me fijo si el cbu lo pudo encontrar
			if(request.getAttribute("verificarCbu") != null)
				{
						
					if((boolean)request.getAttribute("verificarCbu") == true)
					{
						
						if(request.getAttribute("transferencia")!= null)
						{
							//me fijo el resultado de la transferencia, si dio false es porque no se pudo transferir
							if((boolean)request.getAttribute("transferencia"))
							{%>
								<div class="alert alert-success" role="alert">
								    <p>Transferencia realizada con éxito</p>
								</div>
							<%}else
							{%>
								<div class="alert alert-danger" role="alert">
	    						<h4 class="alert-heading">¡Error!</h4>
	    						<p>Saldo insuficiente para realizar la transferencia.</p>
								</div>
							<%}	
						} 
					}
					else
					{%>
						<div class="alert alert-danger" role="alert">
	    				<h4 class="alert-heading">¡Error!</h4>
	    				<p>No existe la cuenta destino o se encuentra deshabilitada.</p>
						</div>
					<%}
				}					
		}
		%>



<table class="container text-center">
	<form method = "post" action="ServletCLIENTE_Transferencia">
        <tr>
            <td><b>CBU <font color="red">*</font></b></td>
            <td></td>
            <td><b>CUENTA A DEBITAR <font color="red">*</font></b></td>
        </tr>
        
        <tr>
            <td><input type = "text" pattern="^\d{8}$" oninput="this.value = this.value.replace(/[^0-9]/g, '');" name="txtCbu" required></td>
        	<td></td>
            <td>
                <select name="cuentasCliente">
                    <option disabled selected>Seleccionar cuenta</option>
	                    <% 
	  					if(tablaListaCuentas != null)
	  					for(Cuenta x : tablaListaCuentas)  {
	  						
	  					%>		  						
	  						<option value="<%= x.getNroCuenta_CU() %>"><%= x.getNroCuenta_CU()%> - <%=x.getTipoCuenta_CU().getDescripcion_Tcu() %></option>
						<% } %>
                </select>
            </td>
        </tr>
        
        <tr>
            <td><b>IMPORTE <font color="red">*</font></b></td>
        	<td></td>
            <td><b>MOTIVO <font color="red">*</font></b></td>
        </tr>
        
        <tr>
            <td><input type="text" pattern="^\d+(\.\d+)?$" required title ="Valor: sin letras y un solo punto." required maxlength = "15" name="txtImporte" required></td>
        	<td></td>
            <td><input type="text" name="txtMotivo" required></td>
        </tr>
        
        <tr>
        	<td></td>
            <td class="text-center">
                <input type="submit" name= "btnTransferir" class="btn btn-success mw-100" value="Transferir">
            </td>
            <td></td>
        </tr>
   	</form>
</table>
   
	
</div>








<div class = "pieDePagina bg-success"></div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
