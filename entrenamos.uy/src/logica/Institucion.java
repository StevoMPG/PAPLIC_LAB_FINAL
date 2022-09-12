package logica;

import datatypes.DtActividadDeportiva;
import datatypes.DtInstitucion;

import excepciones.UsuarioNoExisteException;
import excepciones.ActividadDeportivaException;

import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;

public class Institucion {
	
    private String nombre;
    private String URL;
    private String descripcion;
    private Map<String, ActividadDeportiva> actsDeps;
    private Set<Profesor> profesores;
	private Logger log;
	
    public Institucion(String nombre,   String descripcion,   String url) {
        this.nombre = nombre;
        this.URL = url;
        this.descripcion = descripcion;
        this.actsDeps = new HashMap<>();
        this.profesores = new HashSet<>();
        
		log = Logger.getLogger(manejadorInstitucion.class.getName());
		log.setLevel(Level.INFO);
		Handler handler = new ConsoleHandler();
		log.addHandler(handler);
    }


    public String getNombre() {
    	return nombre;
    }
    public String getURL() {
    	return URL;
    }
    public String getDescripcion() {
    	return this.descripcion;
    }
    public Map<String,   ActividadDeportiva> getActsDeps(){ 
    	return this.actsDeps;
    }
    public Set<Profesor> getProfesores() {
    	return profesores;
    }  
    public ActividadDeportiva findActividad(String actDepNombre) {
    	return actsDeps.get(actDepNombre);
    }

    public void addProfesor(Profesor profe) {
    	profesores.add(profe);
    	log.info("Institucion "+nombre+" event: "+" new prof "+profe.getNickname());
    }
    

    public int addActividadDeportiva(DtActividadDeportiva datosAD, Profesor creador) {
        ActividadDeportiva actDep = new ActividadDeportiva(datosAD, creador);
        if (actsDeps.containsKey(datosAD.getNombre()))
        	return 1;
		actsDeps.put(datosAD.getNombre(),   actDep);
    	log.info("Institucion "+nombre+" event: "+" new actDep "+actDep.getNombre());
		return 0;
    }


    public Boolean existeActDep(String nombreActDep) {
        return actsDeps.containsKey(nombreActDep);
    }

    public Set<String> obtenerNombresActDep() {
		return actsDeps.keySet();
    }


    public ActividadDeportiva getActDep(String nombreActDep) throws ActividadDeportivaException {
    	ActividadDeportiva res = actsDeps.get(nombreActDep);
    	if (res == null) {
    		throw new ActividadDeportivaException("La Actividad Deportiva no pertenece a esta Institucion.");
    	}
    	return res;
    }


    public Set<String> getMiTrabajo(Profesor profe) {
        Set<String> nombreActsDepsProfe = new HashSet<>();
		for(Map.Entry<String, ActividadDeportiva> x: actsDeps.entrySet())
			if(x.getValue().participaProfesor(profe))
				nombreActsDepsProfe.add(x.getKey());
        return nombreActsDepsProfe;
    }

	public Profesor getProfesor(String nick) throws UsuarioNoExisteException {
		Profesor res = null;
		for(Profesor x: profesores)
			if(x.getNickname().equals(nick)) {
				res = x;
			}
		if (res == null) {
			throw new UsuarioNoExisteException("El Profesor seleccionado no pertenece a esta Institucion.");
		}
		return res;
	}
	
	public DtInstitucion obtenerDatos() {
		return new DtInstitucion(nombre,   getDescripcion(),   getURL());
	}
}
