package excepciones;

/**
 * Excepción utilizada para indicar  que la clase seleccionada esta llena.
 */

@SuppressWarnings("serial")
public class NoExisteCuponeraException extends Exception {

    public NoExisteCuponeraException(String string) {
        super(string);
    }
}