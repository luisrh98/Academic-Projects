package Laboral;

/**
 * La clase Persona representa una persona con nombre, DNI y sexo.
 * Es la clase base para otras clases como Empleado.
 */
public class Persona {
    
    /**
     * El nombre de la persona.
     */
    public String nombre;
    
    /**
     * El DNI de la persona.
     */
    public String dni;
    
    /**
     * El sexo de la persona ('M' para masculino, 'F' para femenino).
     */
    public String sexo;

    /**
     * Constructor que inicializa la persona con nombre, DNI y sexo.
     *
     * @param nombre El nombre de la persona.
     * @param dni El DNI de la persona.
     * @param sexo El sexo de la persona.
     */
    public Persona(String nombre, String dni, String sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    /**
     * Constructor que inicializa la persona solo con el nombre y sexo.
     * El DNI se puede establecer más tarde usando el método {@link #setDni(String)}.
     *
     * @param nombre El nombre de la persona.
     * @param sexo El sexo de la persona.
     */
    public Persona(String nombre, String sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Establece el DNI de la persona.
     *
     * @param dni El DNI de la persona.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Devuelve una representación en forma de cadena del nombre y DNI de la persona.
     *
     * @return Una cadena que contiene el nombre y el DNI de la persona.
     */
    public String imprime() {
        return nombre + ", " + dni;
    }

    /**
     * Devuelve el DNI de la persona.
     *
     * @return El DNI de la persona.
     */
    public String getDni() {
        return dni;
    }
}