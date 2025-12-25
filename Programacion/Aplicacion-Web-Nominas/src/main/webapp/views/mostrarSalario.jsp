<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Salario del Empleado</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Enlace al archivo CSS -->
</head>
<body>
    <h2>Salario del Empleado</h2>
    <c:choose>
        <c:when test="${salario!=0}">
            <p>El salario del empleado con DNI <strong>${dni}</strong> es: <strong>${salario}</strong></p>
        </c:when>
        <c:otherwise>
            <p>No se encontró un empleado con el DNI <strong>${dni}</strong>.</p>
        </c:otherwise>
    </c:choose>
    <a href="index.jsp" class="button">Volver al inicio</a> <!-- Opción para volver -->
</body>
</html>