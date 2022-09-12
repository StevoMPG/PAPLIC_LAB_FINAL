package excepciones;

/**
 * Excepción utilizada para indicar que la cuponera no puede agregarsele actividades deportivas.
 */

@SuppressWarnings("serial")
public class CuponeraInmutableException extends Exception{
    public CuponeraInmutableException(String string) {
        super(string);
    }
}