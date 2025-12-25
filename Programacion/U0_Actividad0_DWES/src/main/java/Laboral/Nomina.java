package Laboral;

/**
 * La clase Nomina proporciona métodos para calcular el sueldo de un empleado.
 * Utiliza una tabla de sueldos base según la categoría del empleado y
 * ajusta el sueldo según los años de experiencia.
 */
public class Nomina {

    /**
     * Tabla de sueldos base según la categoría del empleado.
     * Cada índice corresponde a una categoría (1-9).
     */
    public static final int[] SUELDO_BASE = {
        50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000
    };

    /**
     * Calcula el sueldo de un empleado en función de su categoría y años de experiencia.
     * 
     * El sueldo base se determina según la categoría del empleado.
     * Además, se suman 5000 unidades monetarias por cada año de experiencia.
     *
     * @param emp El empleado cuyo sueldo se desea calcular.
     * @return El sueldo total del empleado, basado en su categoría y años de experiencia.
     */
    public static int sueldo(Empleado emp) {
        int sueldo = SUELDO_BASE[emp.getCategoria() - 1];
        sueldo += 5000 * emp.anyos;
        return sueldo;
    }
}