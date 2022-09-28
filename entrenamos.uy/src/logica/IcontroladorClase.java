package logica;

import java.util.Set;

import datatypes.DtClase;
import datatypes.DtClaseExtra;
import datatypes.DtFechaHora;
import datatypes.DtcompraClase;
import datatypes.tipoRegistro;

import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;
import excepciones.UsuarioNoExisteException;


public interface IcontroladorClase {


	public Set<String> obtenerUsuarios();
	
	public Set<String> obtenerInstituciones();
	
	//Dada una Actividad Deportiva te devuelve el nombre de la Institucion asociada
	public String obtenerInstitucionActDep(String actDep) ;
	
	public Set<String> obtenerActividades(String ins) throws InstitucionException ;
	public Set<String> obtenerActividadesAprobadas(String ins) throws InstitucionException;
	
	public Set<String> obtenerProfesores(String ins) throws InstitucionException ;
	
	public Set<String> obtenerClases(String ins,  String actDep) throws InstitucionException ;
	
	public DtClaseExtra seleccionarClase(String  ins,  String actDep,  String clase) throws InstitucionException,  ClaseException, 
			ActividadDeportivaException;
	
	
	public int ingresarDatosClase(String ins,  String actDep,  DtClase datos) throws  InstitucionException,  FechaInvalidaException, 
			ClaseException,  UsuarioNoExisteException,  ActividadDeportivaException;
	
	public void inscribirSocio(String ins,  String actDep,  String clase,  String socio,  tipoRegistro tipoRegistro,  DtFechaHora fechaReg, String cuponera) 
			throws  ClaseException,  FechaInvalidaException,  NoExisteCuponeraException,  InstitucionException,  
			UsuarioNoExisteException,  ActividadDeportivaException;

	public Set<String> obtenerSocios();

	public Set<String> getCuponerasSocioClase(String nombreSocio, String nombreInst, String nombreAd, String nombreClase);
	
	public Set<String> getCuponerasDisponibles(String nombreSocio,  String nombreInst,  String nombreAd) 
			throws UsuarioNoExisteException,  InstitucionException,  ActividadDeportivaException;
	
	public Set<DtcompraClase> bringTheRegistersPls(String nombreClase) throws ClaseException;
}
