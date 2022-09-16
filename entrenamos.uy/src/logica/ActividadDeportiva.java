package logica;

import datatypes.DtClaseExtra;
import datatypes.DtClase;
import datatypes.DtActividadDeportiva;
import datatypes.DtFechaHora;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import logica.persistencia.DataPersistencia;
import datatypes.DtActividadDeportivaExtra;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



public class ActividadDeportiva {
	

	private String nombre;

	private String descripcion;

	private int duracionMinutos;

	private float costo;

	private DtFechaHora fechaRegistro;
	
	Institucion ins;
	
	private Map <String, ClasesCuponera> clCuponera;
	private Map <String, Clase> clases;  // Nombre de clase y clase
	
	private Logger log;

	public ActividadDeportiva(DtActividadDeportiva datosActDep) {
		nombre=datosActDep.getNombre();
		descripcion=datosActDep.getDescripcion();
		duracionMinutos=datosActDep.getDuracionMinutos();
		costo=datosActDep.getCosto();
		fechaRegistro = new DtFechaHora(datosActDep.getFechaRegistro());
		crearHandler();
	}
	private void crearHandler() {
		
		clases = new HashMap<>();
		clCuponera= new HashMap<>();
		log = Logger.getLogger(manejadorInstitucion.class.getName());
		log.setLevel(Level.INFO);
		Handler handler = new ConsoleHandler();
		log.addHandler(handler);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int addClasesCup(ClasesCuponera claseCup) {
		if (clCuponera.containsKey(claseCup.getNombreCuponera()))
			return 1;
		clCuponera.put(claseCup.getNombreCuponera(),  claseCup);
		log.info("ActDep "+nombre+" event: "+" new cup added "+claseCup.getNombreCuponera());
		return 0;
	}
	
	
	public int addClase(DtClase clase,  Profesor profe, String nombreInsti) throws InstitucionException {
		Institucion inst = getHI().findInstitucion(nombreInsti);
		if (clases.containsKey(clase.getNombre()))
			return 1;
		Clase aula = new Clase(clase,  profe,  this);
		clases.put(clase.getNombre(),  aula);
		profe.addClase(aula);
		DataPersistencia.getInstance().persistirClase(getClases().get(clase.getNombre()), inst);
		log.info("ActDep "+nombre+" event: "+" new clase "+clase.getNombre());
		return 0;
	}
	
	private static manejadorInstitucion getHI() {
		return  manejadorInstitucion.getInstance();
	}

	public DtActividadDeportiva getDt(){
		DtActividadDeportiva x = new DtActividadDeportiva(nombre, descripcion, duracionMinutos, costo, fechaRegistro);
		return x;
	}
	
	public DtClaseExtra getClaseDatos(String c) {
		return clases.get(c).getDt();
	}
	
	public Set<String> getNombreClases(){
		return clases.keySet();		
	}
	
	public Set<DtClase> getDatosClases() {
		Set<DtClase> resultado = new HashSet<>();
		for(Map.Entry<String, Clase> x: clases.entrySet())
			resultado.add(x.getValue().getDt());
		return resultado;
	}
	

	public Clase findClase(String clase) throws ClaseException {
		Clase res = clases.get(clase);
		if (res == null) {
			throw new ClaseException("La Clase seleccionada no pertenece a esta Actividad Deportiva.");
		}
		return res;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public int getDuracionMinutos() {
		return duracionMinutos;
	}
	public float getCosto() {
		return costo;
	}
	public DtFechaHora getFechaRegistro() {
		return fechaRegistro;
	}
	
	public boolean participaProfesor(Profesor profe) {
		for (Map.Entry<String,  Clase> x: clases.entrySet())
			if (x.getValue().getProfesor()==profe)
				return true;
        return false;
	}
	public DtActividadDeportivaExtra getDtExt() {
		Set<String> nombresClases = new HashSet<>(clases.keySet());
		Set<String> nombresClasesCuponeras = new HashSet<>(clCuponera.keySet());
		DtActividadDeportivaExtra actDep = new DtActividadDeportivaExtra(getNombre(),  getDescripcion(),  getDuracionMinutos(),  
				getCosto(),  getFechaRegistro(),  nombresClases,  nombresClasesCuponeras);
		return actDep;
	}

	public Map<String, Clase> getClases(){
		return clases;
	}
	
	public Map<String,  ClasesCuponera> getClaseCuponeras() {
		return clCuponera;
	}
	
	public Institucion getINS() {
		return ins;
	}

}