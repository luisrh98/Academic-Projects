package ies.sotero.hernandez.EjercicioPrimerDia;

import Excepciones.DatosNoCorrectosException;
import Laboral.Empleado;
import Laboral.Nomina;


public class CalculaNominas {
    public static void main(String[] args) throws DatosNoCorrectosException {
        
		try {
			Empleado emp1 = new Empleado("James Cosling", "32000032G", "M", 4, 7);
			Empleado emp2 = new Empleado("Ada Lovelace", "32000031R", "F");
	        
	        CalculaNominas.escribe(emp1, emp2);
	        System.out.println("-------------------------------");
	        emp2.incrAnyos();
	        emp1.setCategoria(9);
	        CalculaNominas.escribe(emp1, emp2);
	        
		} catch (DatosNoCorrectosException e) {
			throw new DatosNoCorrectosException("Datos incorrectos");
		}
		
        
        
    }
    
    public static void escribe(Empleado emp1, Empleado emp2) {
    	System.out.println(emp1.imprime() + ", Sueldo: " + Nomina.sueldo(emp1));
    	System.out.println(emp2.imprime() + ", Sueldo: " + Nomina.sueldo(emp2));
    }
}
