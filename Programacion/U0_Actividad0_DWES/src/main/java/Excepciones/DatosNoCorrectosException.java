package Excepciones;

/**
 * La clase DatosNoCorrectosException es una excepción personalizada que se lanza
 * cuando los datos proporcionados no son correctos.
 * Extiende la clase {@link Exception}, lo que permite manejar errores específicos
 * relacionados con datos no válidos.
 */
public class DatosNoCorrectosException extends Exception {

    /**
     * Constructor por defecto de la excepción que llama al constructor de la clase {@link Exception}.
     * Se utiliza cuando no se proporciona un mensaje de error específico.
     */
    public DatosNoCorrectosException() {
        super();
    }

    /**
     * Constructor que acepta un mensaje específico para describir el error.
     * Este mensaje puede ser recuperado más tarde utilizando el método {@link Throwable#getMessage()}.
     *
     * @param mensaje El mensaje que describe la razón del error.
     */
    public DatosNoCorrectosException(String mensaje) {
        super(mensaje);
    }
}