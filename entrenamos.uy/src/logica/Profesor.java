package logica;

import datatypes.DtProfesor;


import java.util.Map;
import java.util.HashMap;


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
	
}