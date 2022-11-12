package logica;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;


import datatypes.DtSocio;
import datatypes.DtSocioExtra;
import datatypes.tipoEstado;
import excepciones.NoExisteCuponeraException;
import excepciones.UsuarioNoExisteException;
import logica.persistencia.DataPersistencia;
import excepciones.ClaseException;
import datatypes.DtFechaHora;
import datatypes.DtPremio;
import datatypes.tipoRegistro;

public class Socio extends Usuario {
	
	private List<compraCuponera> reciboCuponeras;
	private List<compraClase> reciboClases;
	private List<ActividadDeportiva> favoritos;
	
	private Map<String, Calificacion> calificaciones;
	private Map<String, Premio> premios;
	
	public Socio(DtSocio datos) {
		super(datos.getNickname(),   datos.getNombre(),   datos.getApellido(),   datos.getEmail(),   datos.getContrasenia(),   datos.getFechaNacimiento(),   datos.getImagen());
		reciboCuponeras = new LinkedList<>();
		reciboClases = new LinkedList<>();
		favoritos = new LinkedList<>();
		premios = new HashMap<>();
		calificaciones = new HashMap<>();
	}
	
	public void addcompraCuponera(compraCuponera rCup) {
		reciboCuponeras.add(rCup);
	}
	
	public void addcompraClase(compraClase rCl) {
		reciboClases.add(rCl);
	}
	
	public DtSocio getDt() {
		DtSocio datos = new DtSocio(this.getNickname(),   this.getNombre(),   this.getApellido(),   this.getCorreo(),   this.getContrasenia(),   this.getFecha(),   this.getImagen());
		return datos;
	}

	public DtSocioExtra getDtExt() {
    	Map<String,  Set<String>> clasesDeActividadesAceptadas = new HashMap<>();
    	for (compraClase compraClase: reciboClases) {
    		String nombreAD = compraClase.getClase().getAD().getNombre();
    		if (!clasesDeActividadesAceptadas.containsKey(nombreAD)) {
    			Set<String> nombreClases = new HashSet<>();
    			clasesDeActividadesAceptadas.put(nombreAD,  nombreClases);
    			for (compraClase rc2: reciboClases) {
    				if (rc2.getClase().getAD().getNombre().equals(nombreAD) && rc2.getClase().getAD().getEstado().equals(tipoEstado.aceptada))
    					nombreClases.add(rc2.getClase().getNombre());
    			}
    		}
    	}
    	Map<String,  Set<String>> clasesDeActividadesAceptadasLimpiarSetsVacios = new HashMap<>();
    	for(Entry<String, Set<String>> x : clasesDeActividadesAceptadas.entrySet()) {
    		if(x.getValue().size()>0)
    			clasesDeActividadesAceptadasLimpiarSetsVacios.put(x.getKey(), x.getValue());
    	}
    	clasesDeActividadesAceptadas = clasesDeActividadesAceptadasLimpiarSetsVacios;
    	
    	
//    	Map<String,  Set<String>> clasesDeActividadesFinalizadas =new HashMap<>();
    	Map<String,  Set<String>> clasesDeActividadesFinalizadas = DataPersistencia.getInstance().obtenerActividadxClasesSocio(getNickname());
//    	 Viejo codigo de cuando las actividades finalizadas estaban en memoria (F)
    	for (compraClase compraClase: reciboClases) {  
    		String nombreAD = compraClase.getClase().getAD().getNombre();
    		if (!clasesDeActividadesFinalizadas.containsKey(nombreAD)) {
    			Set<String> nombreClases = new HashSet<>();
    			clasesDeActividadesFinalizadas.put(nombreAD,  nombreClases);
    			for (compraClase rc2: reciboClases) {
    				if (rc2.getClase().getAD().getNombre().equals(nombreAD) && rc2.getClase().getAD().getEstado().equals(tipoEstado.finalizada))
    					nombreClases.add(rc2.getClase().getNombre());
    			}
    		}
    	}
    	Map<String,  Set<String>> clasesDeActividadesFinalizadasLimpiarSetsVacios = new HashMap<>();
    	for(Entry<String, Set<String>> x : clasesDeActividadesFinalizadas.entrySet()) {
    		if(x.getValue().size()>0)
    			clasesDeActividadesFinalizadasLimpiarSetsVacios.put(x.getKey(), x.getValue());
    	}
    	clasesDeActividadesFinalizadas = clasesDeActividadesFinalizadasLimpiarSetsVacios;
    	
    	Set<String> cupis = new HashSet<>();
    	for (compraCuponera recibo : reciboCuponeras) {
    		cupis.add(recibo.getCuponera().getNombre());
    	}
    	Set<String> favs = new HashSet<>();
    	for (ActividadDeportiva x : favoritos) {
    		favs.add(x.getNombre());
    	}
    	Map<String, DtPremio> prem = new HashMap<>();
    	for (Entry<String, Premio> x : premios.entrySet()) {
    		prem.put(x.getKey(), x.getValue().getDt());
    	}
    	
    	DtSocioExtra datosExt = new DtSocioExtra(this.getNickname(),   this.getNombre(),   this.getApellido(),   this.getCorreo(),
    			this.getContrasenia(),   this.getFecha(),   clasesDeActividadesAceptadas,   this.getImagen(),  this.getSeguidos().keySet(),
    			this.getSeguidores().keySet(), cupis, favs, prem,clasesDeActividadesFinalizadas);
    	return datosExt;
    }
	
	public List<compraCuponera> getReciboCuponera() {
		return reciboCuponeras;
	}
	
	public List<compraClase> getReciboClase() {
		return reciboClases;
	}
	
	public void inscribirSocio(ActividadDeportiva actDep,   Clase clase,   tipoRegistro tipoCuponera,   DtFechaHora reg,  Cuponera cupi) throws NoExisteCuponeraException,   
			ClaseException {
		boolean noEstaInsc = true;
		for (compraClase res: reciboClases) {
			if (res.getNombreClase() == clase.getNombre()) {
				noEstaInsc = false;
			}
		} 
		if (noEstaInsc) {
			if (tipoCuponera.equals(tipoRegistro.general)) {
				compraClase nuevoRecibo = new compraClase(reg,   tipoRegistro.general,   actDep.getCosto(),   clase,   this,   null);
				reciboClases.add(nuevoRecibo);
				clase.addRecibo(nuevoRecibo, actDep);	
			} else {
				int iteradorMagico=0;
				for (compraClase reciboCl: reciboClases)
					if (reciboCl.esTipoCuponera() && reciboCl.getCuponera()==cupi)
						iteradorMagico++;
				if (iteradorMagico>=cupi.cantidadClases(actDep))
					throw new NoExisteCuponeraException("La cuponera seleccionada no es válida.");
				compraClase nuevoRecibo = new compraClase(reg,  tipoRegistro.cuponera,  actDep.getCosto(),  clase,  this,  cupi);
				reciboClases.add(nuevoRecibo);
				clase.addRecibo(nuevoRecibo, actDep);
			}
		} else {
			throw new ClaseException("Este Usuario ya esta inscripto a esta Clase.");
		}
	}

	public Map<String, Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public Map<String, Premio> getPremios() {
		return premios;
	}
	public void addPremio(Premio premiopremio) {
		premios.put(premiopremio.getClase().getNombre(), premiopremio);
	}
	public List<ActividadDeportiva> getFavoritos() {
		return favoritos;
	}

	public void changeFavoritos(ActividadDeportiva favfav) {
		if (favoritos.contains(favfav))
			favoritos.remove(favfav);
		else
			favoritos.add(favfav);
	}
	
	public void valorarProfesor(Clase clasee, int valor) throws UsuarioNoExisteException {
		Calificacion calif = new Calificacion(clasee, this, valor);
		calificaciones.put(clasee.getProfesor().getNickname(), calif);
		clasee.addCalifiacion(getNickname(), calif);
	}

	public void remClase(compraClase rec) {
		reciboClases.remove(rec);
		favoritos.remove(rec.getClase().getAD());
		calificaciones.remove(rec.getClase().getNombre());
		premios.remove(rec.getClase().getNombre());
	}
	
	public boolean esSocio() {
		return true;
	}
}
