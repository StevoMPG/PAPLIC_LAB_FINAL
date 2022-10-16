package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import datatypes.DtFechaHora;
import excepciones.CuponeraRepetidaException;
import logica.persistencia.DataPersistencia;
import datatypes.DtCuponera;

public class manejadorCuponera {
	

	private static manejadorCuponera instance = null;
	private Map<String,Cuponera> cuponeras;
	private Logger log;
	
	private manejadorCuponera(){
		cuponeras = new HashMap<>();
		
		log = Logger.getLogger(manejadorInstitucion.class.getName());
		log.setLevel(Level.INFO);
		Handler handler = new ConsoleHandler();
		log.addHandler(handler);
	}
	
	public static manejadorCuponera getInstance(){
		if(instance == null)
			instance = new manejadorCuponera();
		return instance;
	}
	
	public boolean exists(String nombreCuponera){
		return cuponeras.containsKey(nombreCuponera);
	}
	
	public void add(String nombreCuponera, Cuponera cuponera){
		cuponeras.put(nombreCuponera, cuponera);
	}

	public int addCuponera(String nombreCuponera, String descripcion, DtFechaHora ini, DtFechaHora fin, 
			int descuento, DtFechaHora fechaAlta) throws CuponeraRepetidaException {
		if( cuponeras.containsKey(nombreCuponera ) )
			throw new CuponeraRepetidaException("La cuponera ingresada ya existe en el sistema.");
		Cuponera nuevaCuponera = new Cuponera(nombreCuponera, descripcion, descuento, ini, fin, fechaAlta);
		cuponeras.put( nombreCuponera, nuevaCuponera );
		//DataPersistencia.getInstance().persistirCuponeras(nuevaCuponera);
		log.info("New Cuponera "+nuevaCuponera.getNombre()+" registered, total: "+cuponeras.size());
		return 0;
	}
	

	public Cuponera getCup(String nombreCuponera){
		return cuponeras.get(nombreCuponera);
	}
	
	public Set<String> getNombreCuponeras() {
		Set<String> r = new HashSet<String>(cuponeras.keySet());
		return r;
	}
	
	public DtCuponera getDtCuponera(String nombreCuponera){
		return getCup(nombreCuponera).getDt();
	}
}