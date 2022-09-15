package logica;


import java.util.HashSet;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Set;

import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;
import logica.persistencia.DataPersistencia;
import datatypes.DtActividadDeportiva;
import datatypes.DtActividadDeportivaExtra;
import datatypes.DtClaseExtra;
import datatypes.DtInstitucion;


public class controladorActividadDeportiva  implements IcontroladorActividadDeportiva {

	private static controladorActividadDeportiva instance = null;
	
	private controladorActividadDeportiva() {
		

	}
	

	public static controladorActividadDeportiva getInstance(){
		if(instance == null)
			instance = new controladorActividadDeportiva();
		return instance;
	}	
	
	public Set<String> obtenerInstituciones(){
		return getHI().obtenerInstituciones();
	}		
	public Set<String> obtenerActividades(String ins) throws InstitucionException {
		return getHI().findInstitucion(ins).obtenerNombresActDep();
	}
	
	
	public Boolean ingresarDatosActividadDep(String nombreInsti,  DtActividadDeportiva datosAD) throws InstitucionException, ActividadDeportivaException{
		Institucion inst = getHI().findInstitucion(nombreInsti);
		for (String x : getHI().obtenerInstituciones()) {
			if (getHI().findInstitucion(x).existeActDep(datosAD.getNombre())) {
				throw new ActividadDeportivaException("La Actividad Deportiva ya existe en el Sistema.");
			}
		}

		
		if (!inst.existeActDep(datosAD.getNombre())) {
			
			
			inst.addActividadDeportiva(datosAD);
			return true;
		}
		return false;
	}
	
	public Set<String> seleccionarInstitucion(String ins) throws InstitucionException {
		return getHI().findInstitucion(ins).obtenerNombresActDep();
	}
	
	public Set<String> obtenerDeltaInstituciones(String nombreCup,  String ins) throws InstitucionException {
		Set<String> actividadesDeportivas = new HashSet<>();
		for (String y: getHI().findInstitucion(ins).getActsDeps().keySet()) {
			actividadesDeportivas.add(y);
			for (String z: getHC().getCup(nombreCup).getNombresActDep()) {
				if (y.equals(z)) {
					actividadesDeportivas.remove(y);
					break;
				}
			}
		}
		Set<String> res = new HashSet<>();
		res.addAll(actividadesDeportivas);
		return res;
	}
	
	public DtClaseExtra seleccionarClase(String inst, String actDep, String clase) throws InstitucionException, 
			ActividadDeportivaException, ClaseException {
		return getHI().findInstitucion(inst).getActDep(actDep).findClase(clase).getDt();
	}
	
	public Set<String> obtenerSocios(){
		return getHU().obtenerNicknameSocios(); 
	}
	
	public DtActividadDeportivaExtra getActDepExt(String ins, String actDep) throws InstitucionException, 
			ActividadDeportivaException {
		return getHI().findInstitucion(ins).getActDep(actDep).getDtExt();
	}
	
	private static manejadorInstitucion getHI() {
		return  manejadorInstitucion.getInstance();
	}
	private static manejadorUsuario getHU() {
		return  manejadorUsuario.getInstance();
	}
	private static manejadorCuponera getHC() {
		return  manejadorCuponera.getInstance();
	}
	
	public int altaInstitucion(String nombre, String descripcion, String URL) {
		if(!getHI().existeInstitucion(nombre)){
			Institucion i = new Institucion(nombre,descripcion,URL);
			getHI().addInstitucion(i);
			return 0;
		}
		return 1;
	}
	
	public DtInstitucion obtenerDatosInstitucion(String inst) throws InstitucionException {
		Institucion instit = getHI().findInstitucion(inst);
		return instit.obtenerDatos();
	}


}