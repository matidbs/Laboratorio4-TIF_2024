<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Información personal</title>

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

<div class = "encabezado"> <h4 class = "text-center text-white bg-success"> <u>INFORMACIÓN PERSONAL</u> </h4> </div>

<div class = "parteIzquierda">
<h5 class = "text-center">Usuario: <%=session.getAttribute("sesion")%></h5>
<jsp:include page = "PLANTILLA_ParteIzquierdaCliente.html"></jsp:include>
</div>


<div class = "parteDerecha">


 <% 
		ArrayList <Cliente> tablaListaDatos = null;
		if(request.getAttribute("ListaDatos") != null)
			{
			tablaListaDatos = (ArrayList<Cliente>)request.getAttribute("ListaDatos"); 
			}
		
		int cantidadDeCuentas=0;
		if(request.getAttribute("CantidadCuentas") != null)
		{
			cantidadDeCuentas = (int)request.getAttribute("CantidadCuentas"); 
		}
	%>


<h4 class = "font-weight-bold text-center"><u>Mis datos:</u></h4>
<h5 class = "font-weight-bold text-left">Para el usuario: <%=session.getAttribute("sesion")%> <a href="ServletCLIENTE_MenuCliente?Datos=1"> Cargar Datos</a></h5>

<br>

<div class = "table-responsive">
		<table id = "infoCliente" class = "table table-hover text-center" border = "1">
		
		<thead>
		<tr>
			<th>DNI:</th><th>CUIL:</th><th>NOMBRE:</th><th>APELLIDO:</th><th>SEXO:</th><th>NACIONALIDAD:</th><th>PROVINCIA:</th><th>LOCALIDAD:</th>
			<th>DIRECCION:</th><th>EMAIL:</th><th>TELEFONO:</th><th>FECHA DE NACIMIENTO:</th><th>Cant. cuentas:</th>
		</tr>
		</thead>
		
		<tbody>
		<%		  
				  	if (tablaListaDatos != null)
				  	{	%>						  		
				  			<%for(Cliente x : tablaListaDatos) {
				  			%>								
									<tr>		
										<td><%=x.getDni_Cl()%></td> 
										<td><%=x.getCuil_Cl()%></td>	
										<td><%=x.getNombre_Cl()%></td>	
										<td><%=x.getApellido_Cl()%></td>							
											<% if(x.getSexo_Cl().equals("M"))
											{%>
												<td>Masculino</td><%}
												else
											{%>
												<td>Femenino</td><%}%>
										<td><%=x.getCodNacionalidad_Cl().getDescripcion_N()%></td>	
										<td><%=x.getCodProv_Cl().getDescripcion_P()%></td>
										<td><%=x.getCodLoc_Cl().getDescripcion_L()%></td>	
										<td><%=x.getDireccion_Cl()%></td>
										<td><%=x.getCorreoElectronico_Cl()%></td>	
										<td><%=x.getTelefono_Cl()%></td>	
										<td><%=x.getFechaNacimiento_Cl()%></td>	
										<td><%=cantidadDeCuentas%></td>			   						
									</tr>		
										 
				  			<%}
				  	}%>
			</tbody>
		</table>
</div>

<br>

<h6 class = "font-weight-bold text-left"><font class = "text-danger font-weight-bold">*</font>En el caso que usted desee modificar sus datos, por favor, comuniquese con un administrador.</h6>
<h6 class = "font-weight-bold text-left"><font class = "text-danger font-weight-bold">*</font>Para visualizar movimientos en sus cuentas, por favor, dirijase al menú principal.</h6>
<h6 class = "font-weight-bold text-left"><font class = "text-danger font-weight-bold">*</font>El sistema unicamente enumera la cantidad de cuentas activas.</h6>
	

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

