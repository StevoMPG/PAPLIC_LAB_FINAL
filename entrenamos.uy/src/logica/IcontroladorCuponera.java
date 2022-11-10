package logica;

import java.util.Set;

import datatypes.DtCuponera;
import datatypes.DtFechaHora;
import excepciones.ActividadDeportivaException;
import excepciones.CuponeraInmutableException;
import excepciones.CuponeraRepetidaException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;

public interface IcontroladorCuponera {

	
	public int ingresarCuponera(String nombre, String descripcion, DtFechaHora inicio, DtFechaHora fin, 
			int descuento, DtFechaHora alta, String img) throws CuponeraRepetidaException, FechaInvalidaException;
	
	public Set<String> getNombreCuponeras();
	
	public void agregarActividadCuponera(String nombre,  String instituto,  String actividadDeportiva,  int cantidadClases) 
			throws InstitucionException,  ActividadDeportivaException,  CuponeraInmutableException;
	
	public DtCuponera seleccionarCuponera(String n) throws NoExisteCuponeraException;
	
	public Set<String> getNombreCuponerasSinRecibos();
}


