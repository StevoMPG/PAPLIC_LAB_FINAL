
package excepciones;

/**
 * Excepción utilizada para indicar  que la clase seleccionada esta llena.
 */

@SuppressWarnings("serial")
public class ActividadDeportivaException extends Exception {
	
    public ActividadDeportivaException(String string) {
        super(string);
    }
}