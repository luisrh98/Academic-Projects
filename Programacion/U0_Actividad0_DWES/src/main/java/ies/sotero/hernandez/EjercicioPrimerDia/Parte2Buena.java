package ies.sotero.hernandez.EjercicioPrimerDia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Excepciones.DatosNoCorrectosException;
import Laboral.Empleado;
import Laboral.Nomina;

public class Parte2Buena {
	// Conexión base de datos
	private static final String DB_URL = "jdbc:mariadb://localhost:3306/nominas"; // Nombre de tu base de datos
	private static final String USER = "root"; // Usuario de la base de datos
	private static final String PASSWORD = "usuario"; // Contraseña

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				BufferedWriter empleados = new BufferedWriter(new FileWriter("empleados.txt"));
				DataOutputStream binario = new DataOutputStream(new FileOutputStream("salarios.dat"));
				Scanner sc = new Scanner(System.in)) {
			
			/*  // Alta de un solo empleado 
			altaEmpleado("Grace Hopper", "32000034", "F", 3, 10, binario, empleados, conn);
			  
			  // Alta de empleados por lotes desde un archivo
			altaEmpleado("empleadosNuevos.txt", binario, empleados, conn);
			  
			  //Modificar empleado
			modificarDatosEmpleado(conn, sc);
			*/
			 
			
			int opcion;
			do {
				mostrarMenu();
				opcion = sc.nextInt();
				sc.nextLine(); // limpiar buffer
				switch (opcion) {
				case 1:
					mostrarInformacionEmpleados(conn);
					break;
				case 2:
					System.out.print("Introduce el DNI del empleado: ");
					String dni = sc.nextLine();
					mostrarSalarioEmpleado(dni, conn);
					break;
				case 3:
					modificarDatosEmpleado(conn, sc);
					break;
				case 4:
					System.out.print("Introduce el DNI del empleado para recalcular el sueldo: ");
					dni = sc.nextLine();
					recalcularYActualizarSueldo(dni, conn);
					break;
				case 5:
					recalcularYActualizarSueldos(conn);
					break;
				case 6:
					realizarCopiaSeguridad(binario, empleados, conn);
					break;
				case 0:
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opción no válida.");
				}
			} while (opcion != 0);

		} catch (SQLException e) {
			System.out.println("Ocurrió un error en la base de datos. Inténtalo de nuevo más tarde.");
			
		} catch (IOException e) {
			System.out.println("Error al procesar el archivo.");
			
		}/* catch (DatosNoCorrectosException e) {
			System.out.println("Los datos del empleado no son correctos.");            //--------->  Solo cuando se creen empleados
			
		}*/

	}

	private static void mostrarMenu() {
		System.out.println("\nMenú:");
		System.out.println("1. Mostrar información de todos los empleados");
		System.out.println("2. Mostrar salario de un empleado por DNI");
		System.out.println("3. Modificar datos de un empleado");
		System.out.println("4. Recalcular y actualizar el sueldo de un empleado");
		System.out.println("5. Recalcular y actualizar los sueldos de todos los empleados");
		System.out.println("6. Realizar copia de seguridad de empleados");
		System.out.println("0. Salir");
		System.out.print("Elige una opción: ");
	}

	// Opción 1: Mostrar información de todos los empleados
	private static void mostrarInformacionEmpleados(Connection conn) throws SQLException {
		String consultaSQL = "SELECT nombre, dni, sexo, categoria, anyos FROM Empleados";
		try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL); ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				System.out.println("Nombre: " + rs.getString("nombre") + ", DNI: " + rs.getString("dni") + ", Sexo: "
						+ rs.getString("sexo") + ", Categoría: " + rs.getInt("categoria") + ", Años: "
						+ rs.getInt("anyos"));
			}
		}
	}

	// Opción 2: Mostrar salario de un empleado por DNI
	private static void mostrarSalarioEmpleado(String dni, Connection conn) throws SQLException {
		String consultaSQL = "SELECT sueldo FROM Nomina WHERE dni = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL)) {
			pstmt.setString(1, dni);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					System.out.println("Salario del empleado con DNI " + dni + ": " + rs.getInt("sueldo"));
				} else {
					System.out.println("Empleado no encontrado.");
				}
			}
		}
	}

	// Opción 3: Submenú para modificar datos del empleado
	private static void modificarDatosEmpleado(Connection conn, Scanner sc) throws SQLException {
		System.out.print("Introduce el DNI del empleado a modificar: ");
		String dni = sc.nextLine();

		System.out.print("Nuevo nombre: ");
		String nuevoNombre = sc.nextLine();
		System.out.print("Nuevo sexo: ");
		String nuevoSexo = sc.nextLine();
		System.out.print("Nueva categoría: ");
		int nuevaCategoria = sc.nextInt();
		System.out.print("Nuevos años de experiencia: ");
		int nuevosAnios = sc.nextInt();
		sc.nextLine(); // limpiar buffer

		String updateSQL = "UPDATE Empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
			pstmt.setString(1, nuevoNombre);
			pstmt.setString(2, nuevoSexo);
			pstmt.setInt(3, nuevaCategoria);
			pstmt.setInt(4, nuevosAnios);
			pstmt.setString(5, dni);

			int filasActualizadas = pstmt.executeUpdate();
			if (filasActualizadas > 0) {
				System.out.println("Empleado actualizado correctamente.");
				recalcularYActualizarSueldo(dni, conn); // Recalcular sueldo
			} else {
				System.out.println("Empleado no encontrado.");
			}
		}
	}

	// Opción 4: Recalcular y actualizar el sueldo de un empleado
	private static void recalcularYActualizarSueldo(String dni, Connection conn) throws SQLException {
		String consultaSQL = "SELECT categoria, anyos FROM Empleados WHERE dni = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL)) {
			pstmt.setString(1, dni);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int categoria = rs.getInt("categoria");
					int anyos = rs.getInt("anyos");

					// Recalcular sueldo
					int nuevoSueldo = calcularSueldo(categoria, anyos);

					// Actualizar sueldo en la base de datos
					String updateSQL = "UPDATE Nomina SET sueldo = ? WHERE dni = ?";
					try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateSQL)) {
						pstmtUpdate.setInt(1, nuevoSueldo);
						pstmtUpdate.setString(2, dni);
						pstmtUpdate.executeUpdate();
						System.out.println("Sueldo recalculado y actualizado para el empleado con DNI " + dni);
					}
				} else {
					System.out.println("Empleado no encontrado.");
				}
			}
		}
	}

	// Opción 5: Recalcular y actualizar sueldos de todos los empleados
	private static void recalcularYActualizarSueldos(Connection conn) throws SQLException {
		String consultaSQL = "SELECT dni, categoria, anyos FROM Empleados";
		try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL); ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				String dni = rs.getString("dni");
				int categoria = rs.getInt("categoria");
				int anyos = rs.getInt("anyos");

				// Recalcular sueldo
				int nuevoSueldo = calcularSueldo(categoria, anyos);

				// Actualizar sueldo en la base de datos
				String updateSQL = "UPDATE Nomina SET sueldo = ? WHERE dni = ?";
				try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateSQL)) {
					pstmtUpdate.setInt(1, nuevoSueldo);
					pstmtUpdate.setString(2, dni);
					pstmtUpdate.executeUpdate();
				}
			}
			System.out.println("Sueldos recalculados y actualizados para todos los empleados.");
		}
	}

	// Opción 6: Copia de seguridad en empleados.txt
	private static void realizarCopiaSeguridad(DataOutputStream binario, BufferedWriter empleados, Connection conn)
			throws SQLException, IOException {
		exportarEmpleados(binario, empleados, conn);
		System.out.println("Copia de seguridad realizada en empleados.txt y salarios.dat.");
	}

	// Método auxiliar para calcular el sueldo basado en categoría y años
	private static int calcularSueldo(int categoria, int anyos) {
		int sueldoBase = Nomina.SUELDO_BASE[categoria - 1];
		return sueldoBase + (5000 * anyos);
	}

	private static void exportarEmpleados(DataOutputStream binario, BufferedWriter empleados, Connection conn) {
		String consultaSQLEmpleados = "SELECT nombre, dni, sexo, categoria, anyos FROM Empleados";
		String consultaSQLNomina = "SELECT dni, sueldo FROM nomina";
		try (PreparedStatement pstmt1 = conn.prepareStatement(consultaSQLEmpleados);
				ResultSet rs1 = pstmt1.executeQuery();
				PreparedStatement pstmt2 = conn.prepareStatement(consultaSQLNomina);
				ResultSet rs2 = pstmt2.executeQuery()) {

			while (rs1.next()) {
				String nombre = rs1.getString("nombre");
				String dni = rs1.getString("dni");
				String sexo = rs1.getString("sexo");
				int categoria = rs1.getInt("categoria");
				int anyos = rs1.getInt("anyos");

				// Escribir datos de empleados en el archivo
				empleados.write(nombre + ", " + dni + ", " + sexo + ", " + categoria + ", " + anyos);
				empleados.newLine();
			}

			// Asegurarse de que los datos se escriban antes de cerrar
			empleados.flush();

			while (rs2.next()) {
				String dni = rs2.getString("dni");
				int sueldo = rs2.getInt("sueldo");

				// Escribir datos de nomina en el archivo .dat
				binario.writeUTF(dni);
				binario.write(sueldo);

			}

		} catch (SQLException | IOException e) {
			System.out.println("Ocurrió un error en la base de datos. Inténtalo de nuevo.");
		}

	}

	// ALTA EMPLEADOS
	// Función para añadir empleados desde el archivo empleadosNuevos.txt
	private static void altaEmpleado(String ficheroEmpleados, DataOutputStream binario, BufferedWriter empleados,
			Connection conn) throws SQLException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(ficheroEmpleados))) {
			String line;

			// Leer el archivo línea por línea
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

					// Verificar que al menos haya 3 campos para crear un empleado
					if (fields.length >= 3) {
						String nombre = fields[0].trim().replace("\"", "");
						String dni = fields[1].trim().replace("\"", "");
						String sexo = fields[2].trim().replace("\"", "");
						Integer categoria = fields.length > 3 && !fields[3].trim().isEmpty()
								? Integer.parseInt(fields[3].trim())
								: null;
						Integer anyos = fields.length > 4 && !fields[4].trim().isEmpty()
								? Integer.parseInt(fields[4].trim())
								: null;

						// Llamar al método para alta de un solo empleado
						altaEmpleado(nombre, dni, sexo, categoria, anyos, binario, empleados, conn);

					} else {
						System.out.println("Datos insuficientes en la línea: " + line);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
			
		}
	}

	private static void altaEmpleado(String nombre, String dni, String sexo, Integer categoria, Integer anyos,
			DataOutputStream binario, BufferedWriter empleados, Connection conn) {
		try {

			if (existeEmpleado(dni, conn)) {
				System.out.println("El empleado con DNI " + dni + " ya existe en la base de datos. No se inserta.");
				return; // Salir de la función si el empleado ya existe
			}

			// Crear el empleado dependiendo de si la categoría y los años están disponibles
			// o no
			Empleado emp;
			if (categoria != null && anyos != null) {
				emp = new Empleado(nombre, dni, sexo, categoria, anyos);
			} else {
				emp = new Empleado(nombre, dni, sexo); // Usa el constructor por defecto si no hay categoría ni años
			}

			// Calcular el sueldo del empleado
			int sueldo = Nomina.sueldo(emp);

			// Imprimir información del empleado
			System.out.println("Empleado creado:");
			System.out.println(emp.imprime() + ", Sueldo: " + sueldo);



			// Preparar las sentencias SQL
			String insertEmpleadoSQL = "INSERT INTO Empleados (nombre, dni, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
			String insertNominaSQL = "INSERT INTO Nomina (dni, sueldo) VALUES (?, ?)";

			try (PreparedStatement pstmtEmpleado = conn.prepareStatement(insertEmpleadoSQL);
					PreparedStatement pstmtNomina = conn.prepareStatement(insertNominaSQL)) {

				// Insertar datos en la tabla Empleados
				pstmtEmpleado.setString(1, emp.nombre);
				pstmtEmpleado.setString(2, emp.getDni());
				pstmtEmpleado.setString(3, emp.sexo);
				pstmtEmpleado.setInt(4, emp.getCategoria());
				pstmtEmpleado.setInt(5, emp.anyos);
				pstmtEmpleado.executeUpdate();

				// Insertar datos en la tabla Nomina
				pstmtNomina.setString(1, emp.getDni());
				pstmtNomina.setInt(2, sueldo);
				pstmtNomina.executeUpdate();

				System.out.println("Empleado y sueldo insertados correctamente en la base de datos.");

			} catch (SQLException e) {
				System.out.println("Ocurrió un error en la base de datos. Inténtalo de nuevo.");
			}

		} catch (DatosNoCorrectosException e) {
			System.out.println("Error al crear o procesar al empleado: " + e.getMessage());
			
		}
		
	}

	private static boolean existeEmpleado(String dni, Connection conn) {
		String consultaSQL = "SELECT dni FROM Empleados WHERE dni = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL)) {
			pstmt.setString(1, dni);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next(); // Retorna true si encuentra el DNI en la base de datos
			}
		} catch (SQLException e) {
			System.out.println("Ocurrió un error en la base de datos. Inténtalo de nuevo.");
		}
		return false;
	}
}
