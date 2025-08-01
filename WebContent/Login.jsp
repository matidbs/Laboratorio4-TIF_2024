<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">


<style>
        <jsp:include page="/css/StyleSheet.css"></jsp:include>
         body {
            background-color: #f8f9fa;
            padding-top: 50px;
        }
</style>


</head>
<body>
<div>

		<%
        if(request.getAttribute("verificar") != null)
        {
        	{%><p class = "font-weight-bold text-center text-danger"> Usuario o Contraseña inválidos.</p><%}
        }else
        {
            if(request.getAttribute("inactivo") != null)
            {
            	{%><p class = "font-weight-bold text-center text-danger"> Usuario inactivo.</p><%}
            }        
            
        }        
        %>

        <form class="form-login"  method="get" action="ServletLogin">
            <h2 style = text-align:center; >Iniciar Sesión</h2>
            <table border="0">
            	<tr>
            		<td>Usuario:</td>
					<td colspan="2">
					<!-- Hay que mirar en la tabla Usuarios y Clientes dde la DB-->
						<input type="text" name="txtUsuario" class="form-control" required>
					</td>
				</tr>
            	<tr>
            		<td>Contraseña:</td>
					<td colspan="2">
                		<input type="password"  name = "txtContrasena" class="form-control" required>
					</td>
				</tr>				
            </table>
            <div class="text-center">
            	<input  type="submit" name="btnIngresar" value="Ingresar"/>
            </div>
            
        </form>
        
        
       
    </div>

</body>
</html>