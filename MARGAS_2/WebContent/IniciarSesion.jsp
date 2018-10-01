<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>

<body>
	<h1>Iniciar sesión</h1>
	
	<form action="login" method="post">
		<input type="text" name="usuario" autofocus placeholder="Usuario" required>
		<input type="password" name="password" placeholder="Contraseña" required>
		<input type="submit" value="Ingresar">
	</form>
	<a href="CrearCuenta.jsp"> Crear Cuenta </a>
	
	<c:out value="${mensaje}"/>
</body>

</html>