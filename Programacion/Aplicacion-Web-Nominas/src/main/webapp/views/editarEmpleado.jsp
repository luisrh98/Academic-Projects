<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Empleado</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Enlace al archivo CSS -->
</head>
<body>
    <h2>Editar Empleado</h2>
    <form action="empresa?action=actualizar" method="post">
        <input type="hidden" name="dni" value="${empleado.dni}" />

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${empleado.nombre}" required /><br/>

        <label for="sexo">Sexo:</label>
    	<select id="sexo" name="sexo" required>
        	<option value="">Seleccione...</option>
        	<option value="M">Masculino</option>
        	<option value="F">Femenino</option>
    	</select><br><br>

        <label for="categoria">Categoría:</label>
        <input type="number" id="categoria" name="categoria" value="${empleado.categoria}" required /><br/>

        <label for="anyos">Años de experiencia:</label>
        <input type="number" id="anyos" name="anyos" value="${empleado.anyos}" required /><br/>

        <button type="submit">Actualizar</button>
    </form>
</body>
</html>