package excepciones;

/**
 * Excepción utilizada para indicar  que la clase seleccionada esta llena.
 */

@SuppressWarnings("serial")
public class InstitucionException extends Exception {

    public InstitucionException(String string) {
        super(string);
    }
}