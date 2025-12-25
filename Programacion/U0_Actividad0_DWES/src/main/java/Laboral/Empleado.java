package Laboral;

import Excepciones.DatosNoCorrectosException;

/**
 * La clase Empleado representa a un empleado, que es una extensión de la clase Persona.
 * Incluye atributos adicionales como categoría y años de experiencia.
 * Permite la creación de empleados con diferentes niveles de categoría y años trabajados.
 * También incluye métodos para gestionar estos atributos.
 */
public class Empleado extends Persona {

    /**
     * Categoría laboral del empleado, un valor entre 1 y 10.
     */
    private int categoria;
    
    /**
     * Años de experiencia del empleado.
     */
    public int anyos;

    /**
     * Constructor para crear un empleado con categoría 1 y 0 años de experiencia.
     *
     * @param nombre El nombre del empleado.
     * @param dni El DNI del empleado.
     * @param sexo El sexo del empleado.
     */
    public Empleado(String nombre, String dni, String sexo) {
        super(nombre, dni, sexo);
        categoria = 1;
        anyos = 0;
    }

    /**
     * Constructor que permite especificar la categoría y los años de experiencia del empleado.
     * Lanza una excepción si los datos no son correctos.
     *
     * @param nombre El nombre del empleado.
     * @param dni El DNI del empleado.
     * @param sexo El sexo del empleado.
     * @param categoria La categoría del empleado (debe estar entre 1 y 10).
     * @param anyos Los años de experiencia del empleado (deben ser mayor que 0).
     * @throws DatosNoCorrectosException Si la categoría no está entre 1 y 10, o si los años son menores o iguales a 0.
     */
    public Empleado(String nombre, String dni, String sexo, int categoria, int anyos) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        if (categoria > 0 && categoria < 11 && anyos > 0) {
            this.categoria = categoria;
            this.anyos = anyos;
        } else {
            throw new DatosNoCorrectosException();
        }
    }

    /**
     * Establece la categoría laboral del empleado.
     *
     * @param numero El nuevo valor de la categoría del empleado.
     */
    public void setCategoria(int numero) {
        categoria = numero;
    }

    /**
     * Devuelve la categoría laboral del empleado.
     *
     * @return La categoría del empleado.
     */
    public int getCategoria() {
        return categoria;
    }
      

    public int getAnyos() {
		return anyos;
	}

	/**
     * Incrementa en uno el número de años de experiencia del empleado.
     */
    public void incrAnyos() {
        anyos++;
    }

    /**
     * Devuelve una representación en forma de cadena de los datos del empleado.
     * Incluye el nombre, DNI, sexo, categoría y años de experiencia.
     *
     * @return Una cadena con los datos del empleado.
     */
    public String imprime() {
        return nombre + ", " + dni + ", " + sexo + ", " + categoria + ", " + anyos;
    }
}
