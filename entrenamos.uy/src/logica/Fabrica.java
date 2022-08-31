package logica;

public class Fabrica {
	
	private static Fabrica instancia = null;
	
	public static Fabrica getInstance() {
		if(instancia == null)
			instancia = new Fabrica();
		return instancia;
	}
	

	public IcontroladorUsuario obtenerIcontroladorUsuario() {
		IcontroladorUsuario icu = controladorUsuario.getInstance();
		return icu;
	}
	

		
	
}

