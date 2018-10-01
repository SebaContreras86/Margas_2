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
	<jsp:include page="InicioCliente.jsp" flush="true"/>
	
	<h2>Nuestros productos</h2>
	
	<table border="1">
		<tr>
			<th>Descripción</th>
			<th>Stock</th>
			<th>Precio</th>
			<th>Cantidad</th>
		</tr>
		<c:forEach items="${lista_productos}" var="producto">
			<tr>
				<td> <c:out value="${producto.getDescripcion()}"/> </td>
				<td> <c:out value="${producto.getStock()}"/> </td>
				<td> <c:out value="${producto.getPrecio()}"/> </td>
				<td>
					<form action="#">
						<input type="number" name="cantidad" value="0" min="1" max="${producto.getStock()}">
						<input type="hidden" name="id" value="${producto.getId()}">
						<input type="submit" name="agregar" value="Agregar">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<h2>Tu pedido</h2>
	
	<table border="1">
		<tr>
			<th>Descripción</th>
			<th>Cantidad</th>
			<th>Importe</th>
			<th>#</th>
		</tr>
		<c:forEach items="${lineas_de_pedido}" var="linea">
			<tr>
				<td> <c:out value="${linea.getTipoGarrafa().getDescripcion()}"/> </td>
				<td> <c:out value="${linea.getCantidad()}"/> </td>
				<td> <c:out value="${linea.getImporte()}"/> </td>
				<td>
					<form action="ServletPedido" method="post">
						<input type="hidden" name="id_producto" value="${linea.getTipoGarrafa().getId()}"/>
						<input type="submit" name="quitar" value="Quitar">
					</form> 
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2"> TOTAL </td>
			<td> $<c:out value="${ctrl_pedido.getPedido().getTotal()}"/> </td>
			<td>
				<form action="ServletPedido" method="post">
					<input type="submit" name="enviar" value="Enviar pedido">
				</form> 
			</td> 
		</tr>
	</table>
</body>
</html>