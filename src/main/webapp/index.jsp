<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Java JSP</title>
</head>
<body>

<h1>Bem vindo a Pagina JSP</h1>

<form action="ServletLogin" method="post">
<input type="hidden" value="<%=request.getParameter("url")%>" name="url">
<table>
<tr>

	<td><label>Login:</label></td>
	<td>
	<input name = "login" type="text">
	</td>
</tr>

<tr>
	<td><label>Senha:</label></td>
	<td>
	<input name = "senha" type="password">
	</td>

</tr>

<tr>
	<td></td>
	<td>
	<input type="submit" value="Enviar">
	</td>

</tr>
</table>


<h4>${msg}</h4>



</form>




</body>
</html>