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

<form class = "form-login">
 <h2 style = text-align:center; > Atencion.</h2>
<table>
<tr>
<td>
<p class = "font-weight-bold"> <u>¿Estas seguro que deseas dar de baja al cliente: [Nombre]?</u></p>
</td>
</tr>
<tr>
<td><input type = "submit" name = "btnConfirmar" value = "Confirmar" class = "btn"></td>
<td><input type = "submit" name = "btnCancelar" value = "Cancelar" class = "btn"></td>
</tr>
</table>
</form>
</body>
</html>