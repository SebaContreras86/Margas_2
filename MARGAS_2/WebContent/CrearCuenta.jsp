<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Crear Cuenta</h1>
	
	<form action="ServletCrearCuenta" method="post">
		<input type="text" name="usuario" required placeholder="Nombre de usuario">
		<input type="password" name="password" required placeholder="Contraseña">
		<input type="text" name="nombre" required placeholder="Nombre">
		<input type="text" name="apellido" required placeholder="Apellido">
		<input type="text" name="dni" required placeholder="DNI">
		<input type="text" name="telefono" required placeholder="Teléfono">
		<input type="text" name="direccion" required placeholder="Dirección">
		<input type="email" name="email" required placeholder="E-Mail">
		<input type="submit" value="Crear cuenta">
	</form>
</body>
</html>