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
	
	public IcontroladorActividadDeportiva obtenerIcontroladorActDeportiva() {
		IcontroladorActividadDeportiva iact = controladorActividadDeportiva.getInstance();
		return iact;
	}
	
	
	
	public IcontroladorClase obtenerIcontroladorDictadoClase(){
    	IcontroladorClase idic = controladorClase.getInstance();
    	return idic;
     }
    
    public IcontroladorCuponera obtenerIcontroladorCuponera() {
    	IcontroladorCuponera idep = controladorCuponera.getInstance();
    	return idep;
     }
    
    public ILogger getILogger() {
    	return manejadorLogs.getInstance();
    }
		
	
}

