package logica;

import java.util.HashSet;
import java.util.Set;

import datatypes.DtCuponera;
import datatypes.DtFechaHora;
import excepciones.ActividadDeportivaException;
import excepciones.CuponeraInmutableException;
import excepciones.CuponeraRepetidaException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;

public class controladorCuponera implements IcontroladorCuponera {

	private static controladorCuponera instance = null;

	private controladorCuponera() {}
	
	public static controladorCuponera getInstance(){
		if ( instance == null )
			instance = new controladorCuponera();
		return instance;
	}
	
	public int ingresarCuponera(String nombre,  String descripcion,  DtFechaHora inicio,  DtFechaHora fin,  
			int descuento,  DtFechaHora alta, String imagen) throws CuponeraRepetidaException,  FechaInvalidaException {
	if (!alta.esMenor(inicio)) {
		throw new FechaInvalidaException("La fecha de alta debe ser anterior a la de inicio.");
	}
	if (!inicio.esMenor(fin)) {
		throw new FechaInvalidaException("La fecha de inicio debe ser anterior a la de finalizacion.");
	}
	if (getHC().addCuponera(nombre,  descripcion,  inicio,  fin,  descuento,  alta) == 0) {
		getHC().getCup(nombre).setImg(imagen);
	}
	return 0;
}

	
	public Set<String> getNombreCuponeras(){
		return getHC().getNombreCuponeras();
	}

	public void agregarActividadCuponera(String nombreCuponera,  String institucion,  String actividadDeportiva,  int cantidadClases) 
			throws InstitucionException,  ActividadDeportivaException,  CuponeraInmutableException{
		getHC().getCup(nombreCuponera).addActDep(getHI().findInstitucion(institucion).getActDep(actividadDeportiva),  cantidadClases);
	}
		
		
	public DtCuponera seleccionarCuponera(String n) throws NoExisteCuponeraException {
		manejadorCuponera hu = manejadorCuponera.getInstance();
		Cuponera c = hu.getCup(n);
		if (c == null) {
			throw new NoExisteCuponeraException("La cuponera seleccionada no existe en el sistema.");
		}
		return c.getDt();
	}
	
	private manejadorInstitucion getHI() {
		return manejadorInstitucion.getInstance();
	}
	private manejadorCuponera getHC() {
		return manejadorCuponera.getInstance();
	}
	
	public Set<String> getNombreCuponerasSinRecibos(){
		Set<String> res = new HashSet<>();
		for (String x: getHC().getNombreCuponeras()) {
			if (getHC().getCup(x).getRc().size()==0)
				res.add(x);

		}
		return res;
	}
}

