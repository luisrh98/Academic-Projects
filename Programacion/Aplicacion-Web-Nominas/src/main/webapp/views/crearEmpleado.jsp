<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Empleado</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Enlace al archivo CSS -->
</head>
<body>
    <h2>Crear Empleado</h2>
    <form action="empresa" method="post">
        <input type="hidden" name="action" value="agregar"> <!-- Especifica la acción a realizar -->
        
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br><br>

        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" required><br><br>

        <label for="sexo">Sexo:</label>
    	<select id="sexo" name="sexo" required>
        	<option value="">Seleccione...</option>
        	<option value="M">Masculino</option>
        	<option value="F">Femenino</option>
    	</select><br><br>

        <label for="categoria">Categoría:</label>
        <input type="number" id="categoria" name="categoria" ><br><br>

        <label for="anyos">Años de experiencia:</label>
        <input type="number" id="anyos" name="anyos"><br><br>

        <!-- Botón para crear el empleado -->
        <button type="submit">Crear Empleado</button>
    </form>

    <a href="index.jsp" class="button">Volver al inicio</a> <!-- Opción para volver -->
</body>
</html>