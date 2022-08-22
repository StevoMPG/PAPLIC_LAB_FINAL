package excepciones;

/**
 * Excepción utilizada para indicar  que la clase seleccionada esta llena.
 */

@SuppressWarnings("serial")
public class UsuarioNoExisteException extends Exception {

    public UsuarioNoExisteException(String string) {
        super(string);
    }
}