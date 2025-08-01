<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>¡ATENCION!</title>

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

<%
int nroCuenta = 0;
if(request.getParameter("btnDarBaja") != null){
nroCuenta = Integer.parseInt(request.getParameter("nroCuentaHidden").toString());
session.setAttribute("Cuenta", nroCuenta);
}
%>

<form class = "form-confirm" method = "post" action = "ServletDarBajaCuenta">

<h2 class = "text-danger text-center"><u>DAR BAJA A CUENTA</u></h2>
<p class = "font-weight-bold text-center">¿Estas seguro que quieres dar de baja la cuenta n°: <u> <%=nroCuenta %></u>?</p>

<div style = "padding-left: 9.5em">
<table>
<tr>
<td class = "text-center">
<input type="submit" name="btnConfirmar" value="Confirmar" class="btn btn-success" style="margin-right: 10px;">
<input type="submit" name="btnCancelar" value="Cancelar" class="btn btn-danger">
</td>
</tr>
</table>
</div>

</form>

</body>
</html>