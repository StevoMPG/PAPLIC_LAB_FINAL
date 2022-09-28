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
import excepciones.ClaseException;
import datatypes.DtFechaHora;
import datatypes.tipoRegistro;

public class Socio extends Usuario {
	
	private List<compraCuponera> compraCuponeras;
	
	private List<compraClase> compraClases;
	
	
	public Socio(DtSocio datos) {
		super(datos.getNickname(),   datos.getNombre(),   datos.getApellido(),   datos.getEmail(),   datos.getContrasenia(),   datos.getFechaNacimiento(),   datos.getImagen());
		compraCuponeras = new LinkedList<>();
		compraClases = new LinkedList<>();
	}

	public boolean esSocio() {
		return true;
	}
	
	public void addcompraCuponera(compraCuponera rCup) {
		compraCuponeras.add(rCup);
	}
	
	public void addcompraClase(compraClase rCl) {
		compraClases.add(rCl);
	}
	
	public DtSocio getDt() {
		DtSocio datos = new DtSocio(this.getNickname(),   this.getNombre(),   this.getApellido(),   this.getCorreo(),   this.getContrasenia(),   this.getFecha(),   this.getImagen());
		return datos;
	}
	
	public DtSocioExtra getDtExt() {
    	Map<String,  Set<String>> clasesDeActividadesAceptadas = new HashMap<>();
    	for (compraClase compraClase: compraClases) {
    		String nombreAD = compraClase.getClase().getAD().getNombre();
    		if (!clasesDeActividadesAceptadas.containsKey(nombreAD)) {
    			Set<String> nombreClases = new HashSet<>();
    			clasesDeActividadesAceptadas.put(nombreAD,  nombreClases);
    			for (compraClase rc2: compraClases) {
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
    	
    	Map<String,  Set<String>> clasesDeActividadesFinalizadas =new HashMap<>();// DataPersistencia.getInstance().obtenerActividadxClasesSocio(getNickname());
    	/* Viejo codigo de cuando las actividades finalizadas estaban en memoria (F)
    	for (compraClase compraClase: compraClases) {  
    		String nombreAD = compraClase.getClase().getAD().getNombre();
    		if (!clasesDeActividadesFinalizadas.containsKey(nombreAD)) {
    			Set<String> nombreClases = new HashSet<>();
    			clasesDeActividadesFinalizadas.put(nombreAD,  nombreClases);
    			for (compraClase rc2: compraClases) {
    				if (rc2.getClase().getAD().getNombre().equals(nombreAD) && rc2.getClase().getAD().getEstado().equals(TEstado.finalizada))
    					nombreClases.add(rc2.getClase().getNombre());
    			}
    		}
    	}
    	*/
    	Map<String,  Set<String>> clasesDeActividadesFinalizadasLimpiarSetsVacios = new HashMap<>();
    	for(Entry<String, Set<String>> x : clasesDeActividadesFinalizadas.entrySet()) {
    		if(x.getValue().size()>0)
    			clasesDeActividadesFinalizadasLimpiarSetsVacios.put(x.getKey(), x.getValue());
    	}
    	clasesDeActividadesFinalizadas = clasesDeActividadesFinalizadasLimpiarSetsVacios;
    	
    	Set<String> cupis = new HashSet<>();
    	for (compraCuponera recibo : compraCuponeras) {
    		cupis.add(recibo.getCuponera().getNombre());
    	}
    	
    	DtSocioExtra datosExt = new DtSocioExtra(this.getNickname(),   this.getNombre(),   this.getApellido(),   this.getCorreo(),
    			this.getContrasenia(),   this.getFecha(),   clasesDeActividadesAceptadas,   this.getImagen(),  this.getSeguidos().keySet(),
    			this.getSeguidores().keySet(), cupis,clasesDeActividadesFinalizadas);
    	return datosExt;
    }
	
	
	
	
	
	
	public List<compraCuponera> getReciboCuponera() {
		return compraCuponeras;
	}
	
	public List<compraClase> getReciboClase() {
		return compraClases;
	}
	
 void inscribirSocio(ActividadDeportiva actDep,   Clase clase,   tipoRegistro tipoCuponera,   DtFechaHora reg,  Cuponera cupi) throws NoExisteCuponeraException,   
	ClaseException {
		boolean noEstaInsc = true;
		for (compraClase res: compraClases) {
			if (res.getNombreClase() == clase.getNombre()) {
				noEstaInsc = false;
			}
		} 
		if (noEstaInsc) {
			if (tipoCuponera.equals(tipoRegistro.general)) {
				compraClase nuevoRecibo = new compraClase(reg,   tipoRegistro.general,   actDep.getCosto(),   clase,   this,   null);
				compraClases.add(nuevoRecibo);
				clase.addRecibo(nuevoRecibo);	
			} else {
				int iteradorMagico=0;
				for (compraClase reciboCl: compraClases)
					if (reciboCl.esTipoCuponera() && reciboCl.getCuponera()==cupi)
						iteradorMagico++;
				if (iteradorMagico>=cupi.cantidadClases(actDep))
					throw new NoExisteCuponeraException("La cuponera seleccionada no es válida.");
				compraClase nuevoRecibo = new compraClase(reg,  tipoRegistro.cuponera,  actDep.getCosto(),  clase,  this,  cupi);
				compraClases.add(nuevoRecibo);
				clase.addRecibo(nuevoRecibo);
			}
		} else {
			throw new ClaseException("Este Usuario ya esta inscripto a esta Clase.");
		}
	}
	
	public void remClase(compraClase rec) {
		compraClases.remove(rec);
	}
}
