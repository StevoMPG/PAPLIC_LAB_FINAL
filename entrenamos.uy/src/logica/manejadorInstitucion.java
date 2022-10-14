package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import excepciones.InstitucionException;
import logica.persistencia.DataPersistencia;

public class manejadorInstitucion {
	
	private static manejadorInstitucion instance = null;
	private Logger log;
	private Map<String, Institucion> instituciones;
	
	private manejadorInstitucion() {
		instituciones = new HashMap<>();
		
		log = Logger.getLogger(manejadorInstitucion.class.getName());
		log.setLevel(Level.INFO);
		Handler handler = new ConsoleHandler();
		log.addHandler(handler);
	}
	
	public static manejadorInstitucion getInstance() {
		if (instance == null)
			instance = new manejadorInstitucion(); 
		return instance;
	}
	
	public Institucion findInstitucion(String nombreIns) throws InstitucionException {
		Institucion res = instituciones.get(nombreIns);
		if (res != null)
			return instituciones.get(nombreIns);
		else 
			throw new InstitucionException("La institucion no existe en el Sistema.");
	}

	public Set<String> obtenerInstituciones() {
		Set<String> nombreInstituciones = new HashSet<>();
		for(Map.Entry<String, Institucion> x: instituciones.entrySet())
			nombreInstituciones.add(x.getKey());
		return nombreInstituciones; 
	}

	public int addInstitucion(Institucion ins) {
		if (!existeInstitucion(ins.getNombre())) {
			instituciones.put(ins.getNombre(), ins);
			DataPersistencia.getInstance().persistirInstitucion(ins);
			log.info("Institucion "+ins.getNombre()+"registered, total: "+instituciones.size());
			return 0;
		}
		return 1;
	}
	

	public boolean existeInstitucion(String nombre) {
		return instituciones.containsKey(nombre);
	}
}	