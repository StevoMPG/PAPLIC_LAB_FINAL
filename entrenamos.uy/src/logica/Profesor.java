package logica;

import datatypes.DtProfesor;
import datatypes.DtProfesorExtra;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;


public class Profesor extends Usuario {
	
	private String descripcion, biografia, website;
	private Institucion instituto;
	private Map<String, Clase> misClases;
	
	
	public Profesor(DtProfesor datos) {
		super(datos.getNickname(), datos.getNombre(), datos.getApellido(), datos.getEmail(), datos.getFechaNacimiento());
		this.setDescripcion(datos.getDescripcion());
		this.setBiografia(datos.getBiografia());
		this.setWebsite(datos.getLink());
		instituto = null;
		misClases = new HashMap<>();
	}
	
	public boolean esSocio() {
		return false;
	}
	
	private void setDescripcion(String desc) {
		this.descripcion = desc;
	}
	
	private void setBiografia(String bio) {
		this.biografia = bio;
	}
	
	private void setWebsite(String web) {
		this.website = web;
	}
	
	public void setInstitucion(Institucion insti) {
		this.instituto = insti;
	}
	
	// Devuelve true si la Clase 'cl' se añade con exito al conjunto de Clases asociadas al Profesor.
	public boolean addClase(Clase cl) {
		if (misClases.containsKey(cl.getNombre())) {
			return false;
		} else {
			misClases.put(cl.getNombre(), cl);
			return true;
		}
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getBiografia() {
		return biografia;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public Institucion getInstitucion() {
		return instituto;
	}
	
	public Map<String, Clase> getClasesDictadas() {
		return misClases;
	}
	
	public DtProfesorExtra getDtExt() {
    	Set<String> clasesDictadas = new HashSet<>(misClases.keySet());
    	Map<String,Set<String>> x = new HashMap<>();
    	for(String aa: instituto.getMiTrabajo(this)) {
    		Set<String> y = new HashSet<>();
    		x.put(aa,y);
    		for(String c: clasesDictadas) {
    			if(misClases.get(c).tieneActividadDeportiva(aa)) {
    				y.add(c);
    			}
    		}
    	}
    	DtProfesorExtra datosExt = new DtProfesorExtra(nickname,nombre,apellido,correo,fechaNacimiento,instituto.getNombre(),
    			descripcion,biografia,website,x);
    	return datosExt;
    }
    
    public void editarDatos(DtProfesor datos) {
    	super.editarDatos(datos);
    	this.setDescripcion(datos.getDescripcion());
    	this.setBiografia(datos.getBiografia());
    	this.setWebsite(datos.getLink());
    }
}