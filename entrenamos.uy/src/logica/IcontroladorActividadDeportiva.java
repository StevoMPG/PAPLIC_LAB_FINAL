package logica;

import java.util.Set;

import excepciones.ActividadDeportivaException;
import excepciones.CategoriaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;

import datatypes.DtActividadDeportiva;
import datatypes.DtClaseExtra;
import datatypes.DtActividadDeportivaExtra;
import datatypes.DtCategoria;
import datatypes.DtInstitucion;
import datatypes.tipoEstado;

public interface IcontroladorActividadDeportiva {

	
public Set<String> obtenerInstituciones(); 
	
	public Boolean ingresarDatosActividadDep(String nombreInsti,  DtActividadDeportiva datosAD) throws InstitucionException, 
			ActividadDeportivaException;
	
	public Set<String> obtenerActividades(String ins) throws InstitucionException ;
	
	public Set<String> obtenerDeltaInstituciones(String nombreCup,  String ins) throws InstitucionException ;
	
	public DtClaseExtra seleccionarClase(String  ins,  String actDep,  String clase) throws InstitucionException, 
			ActividadDeportivaException,  ClaseException;
	
	public Set<String> obtenerSocios();
	
	public DtActividadDeportivaExtra getActDepExt(String ins,  String actDep) throws InstitucionException,  
			ActividadDeportivaException;	
	
	public int altaInstitucion(String nombre,  String descripcion,  String URL);
	
	public DtInstitucion obtenerDatosInstitucion(String inst) throws InstitucionException;
	
	public void aprobarActividad(String actividadDeportiva,  tipoEstado estado);
	
	
	public Set<String> obtenerActDepIngresadas();
	
	public void ingresarCatergoria(DtCategoria datos) throws CategoriaException;

	public Set<String> obtenerCategorias();
	
	public DtActividadDeportivaExtra buscarActDep(String nombreActDep) throws ActividadDeportivaException;
	
	public void finalizarActividad(String actividadDeportiva);
	
	public Set<String> buscarActividades(String coincidencia);

}

