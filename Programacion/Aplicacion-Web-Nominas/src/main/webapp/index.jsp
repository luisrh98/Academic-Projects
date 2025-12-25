<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menú de Opciones</title>
<link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Enlace al archivo CSS -->
</head>
<body>
  <h1>Menu de Empleados</h1>
  <table border="1">
  <tr>
    <td><a href="empresa?action=listar">Listar todos los empleados</a></td>
  </tr>
  <tr>
    <td><a href="empresa?action=buscarSalario">Buscar salario por DNI</a></td>
  </tr>
  <tr>
    <td><a href="empresa?action=modificar">Modificar datos de un empleado</a></td>
  </tr>
</table>
</body>
</html>