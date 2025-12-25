<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buscar Salario</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Enlace al archivo CSS -->
</head>
<body>
    <h2>Buscar Salario de un Empleado</h2>
    <form action="empresa" method="post">
        <input type="hidden" name="action" value="buscarSalario">
        <label for="dni">DNI del Empleado:</label>
        <input type="text" name="dni" id="dni" required>
        <button type="submit">Buscar Salario</button>
    </form>
    <a href="index.jsp" class="button">Volver</a> <!-- Opción para volver -->
</body>
</html>