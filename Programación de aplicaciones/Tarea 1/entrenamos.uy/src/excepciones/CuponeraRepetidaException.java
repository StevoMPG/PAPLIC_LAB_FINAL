package excepciones;

/**
 * Excepción utilizada para indicar  que la clase seleccionada esta llena.
 */

@SuppressWarnings("serial")
public class CuponeraRepetidaException extends Exception {
	
    public CuponeraRepetidaException(String string) {
        super(string);
    }
}