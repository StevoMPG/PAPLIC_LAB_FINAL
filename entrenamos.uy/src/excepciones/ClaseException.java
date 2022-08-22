package excepciones;

/**
 * Excepción utilizada para indicar  que la clase seleccionada esta llena.
 */

@SuppressWarnings("serial")
public class ClaseException extends Exception {

    public ClaseException(String string) {
        super(string);
    }
}