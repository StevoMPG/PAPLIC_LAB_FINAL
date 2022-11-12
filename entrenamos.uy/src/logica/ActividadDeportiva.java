package logica;

import datatypes.DtClaseExtra;
import datatypes.DtClase;
import datatypes.DtActividadDeportiva;
import datatypes.DtFechaHora;
import datatypes.tipoEstado;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import logica.persistencia.DataPersistencia;
import datatypes.DtActividadDeportivaExtra;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ActividadDeportiva {
	

	private Map<String,  Clase> clases;  // Nombre de clase y clase
	private Map<String,  ClasesCuponera> clCuponera;
	private Map<String,  Categoria> cats;
	private Set<Socio> favoritos;
	private String nombre;
	private String descripcion;
	private int duracionMinutos;
	private float costo;
	private DtFechaHora fechaRegistro;
	private Logger log;
	private tipoEstado estado;
	private Profesor creador;
	private String imgName;
	
	public ActividadDeportiva(DtActividadDeportiva datosActDep,  Map<String,  Categoria> cat,  Profesor profe) {
		nombre=datosActDep.getNombre();
		descripcion=datosActDep.getDescripcion();
		duracionMinutos=datosActDep.getDuracionMinutos();
		costo=datosActDep.getCosto();
		fechaRegistro = new DtFechaHora(datosActDep.getFechaRegistro());
		cats = cat;
		creador = profe;
		imgName = datosActDep.getImgName();
		estado = datosActDep.getEstado(); 
		crearHandler();
	}
	private void crearHandler() {
		clases = new HashMap<>();
		clCuponera= new HashMap<>();
		favoritos = new HashSet<>();
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

	public Set<String> getNombreClases(){
		return clases.keySet();		
	}
	public Map<String, Clase> getClases(){
		return clases;
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
		Set<String> nombresClasesCuponeras = new HashSet<>(clCuponera.keySet());
		DtActividadDeportivaExtra actDep = new DtActividadDeportivaExtra(getNombre(),  getDescripcion(),  getDuracionMinutos(),  
				getCosto(),  getFechaRegistro() ,  cats.keySet(),  getNombreClases(),  nombresClasesCuponeras,  estado,  creador.getNickname(),  imgName,favoritos.size());
		return actDep;
	}
	public tipoEstado getEstado() {
		return estado;
	}
	
	public boolean setEstado(tipoEstado nuevoEstado) {
		if (estado==tipoEstado.ingresada && (nuevoEstado==tipoEstado.aceptada || nuevoEstado==tipoEstado.rechazada)) {
			estado = nuevoEstado;
			return true;
		}
		else if (estado==tipoEstado.aceptada && nuevoEstado==tipoEstado.finalizada) {
			estado = nuevoEstado;
			return true;
		}
		else 
			return false;
	}
	
	public Set<Categoria> getCategorias() {
		Set<Categoria> res = new HashSet<>();
		res.addAll(cats.values());
		return res;
	}
	public Map<String,  ClasesCuponera> getClaseCuponeras() {
		return clCuponera;
	}
	public Set<Socio> getFavoritos() {
		return favoritos;
	}
	public void setFavoritos(Set<Socio> favoritos) {
		this.favoritos = favoritos;
	}
	public void changeFavoritos(Socio user) {
		if (favoritos.contains(user))
			favoritos.remove(user);
		else
			favoritos.add(user);
	}
	public void suicidar() {
		// TODO Auto-generated method stub
		creador.remActDep(this);
		for(Entry<String, Clase> x: clases.entrySet())
			x.getValue().suicidar();
		for(Entry<String, ClasesCuponera> x : clCuponera.entrySet())
			x.getValue().estafar();
	}
	public Profesor getCreador() {
		return creador;
	}
}
