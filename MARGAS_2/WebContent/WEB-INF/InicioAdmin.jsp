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
	<h1>Administrador: <c:out value="${usuario.getNombre()}"/> </h1>
	
	<nav>
		<ul>
			<li> <a href="#"> Inicio </a> </li>
			<li> <a href="#"> Registrar usuario</a> </li>
			<li> <a href="#"> ABM Clientes </a> </li>
			<li> <a href="#"> Ver pedidos pendientes </a> </li>
			<li> <a href="#"> Contactar proveedor </a> </li>
			<li> <a href="Logout"> Cerrar sesi�n </a> </li>
		</ul>
	</nav>
</body>
</html>