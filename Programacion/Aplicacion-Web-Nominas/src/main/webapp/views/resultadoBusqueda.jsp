<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.aprendec.model.Empleado" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Resultados de la Búsqueda</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Enlace al archivo CSS -->
</head>
<body>
    <h2>Resultados de la Búsqueda</h2>

    <c:choose>
        <c:when test="${not empty empleados}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>DNI</th>
                        <th>Sexo</th>
                        <th>Categoría</th>
                        <th>Años experiencia</th>
                        <th>Editar</th> <!-- Nueva columna para las acciones -->
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Iterar sobre los empleados encontrados -->
                    <c:forEach var="empleado" items="${empleados}">
                        <tr>
                            <td>${empleado.nombre}</td>
                            <td>${empleado.dni}</td>
                            <td>${empleado.sexo}</td>
                            <td>${empleado.categoria}</td>
                            <td>${empleado.anyos}</td>
                            <td>
                                <!-- Botón para editar, pasa el DNI del empleado como parámetro -->
    							<form action="empresa" method="get"> <!-- Cambia a 'empleados' sin la acción aquí -->
    								<input type="hidden" name="action" value="editar" /> <!-- Agrega esto -->
    								<input type="hidden" name="dni" value="${empleado.dni}">
    								<button type="submit">Editar</button>
								</form>
                                
                            </td>
                            <td>
                            	<!-- Botón para eliminar -->
                                <form action="empresa?action=eliminar" method="post">
    								<input type="hidden" name="dni" value="${empleado.dni}">
    								<button type="submit">Eliminar</button>
								</form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>No se encontraron empleados con los criterios de búsqueda especificados.</p>
        </c:otherwise>
    </c:choose>

    <!-- Botón para crear nuevo empleado -->
    <a href="empresa?action=crearEmpleado" class="button">Crear nuevo empleado</a> <!-- Enlace al formulario de creación -->

</body>
</html>