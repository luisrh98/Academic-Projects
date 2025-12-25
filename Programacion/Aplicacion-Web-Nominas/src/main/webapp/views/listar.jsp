<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Empleados</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Enlace al archivo CSS -->
</head>
<body>
    <h2>Empleados Registrados</h2>
    <table>
        <tr>
            <th>Nombre</th>
            <th>DNI</th>
            <th>Sexo</th>
            <th>Categoría</th>
            <th>Años Trabajados</th>
        </tr>
        <c:forEach var="empleado" items="${listaEmpleados}">
            <tr>
                <td>${empleado.nombre}</td>
                <td>${empleado.dni}</td>
                <td>${empleado.sexo}</td>
                <td>${empleado.categoria}</td>
                <td>${empleado.anyos}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp" class="button">Volver al inicio</a>
    <!-- Botón para crear nuevo empleado -->
    <a href="empresa?action=crearEmpleado" class="button">Crear nuevo empleado</a> <!-- Enlace al formulario de creación -->
</body>
</html> 