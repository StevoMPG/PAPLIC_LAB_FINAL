package logica;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;


import datatypes.DtSocio;
import datatypes.DtSocioExtra;

import excepciones.NoExisteCuponeraException;
import excepciones.ClaseException;
import datatypes.DtFechaHora;
import datatypes.tipoRegistro;

public class Socio extends Usuario {
	
	private List<compraCuponera> compraCuponeras;
	
	private List<compraClase> compraClases;
	
	
	public Socio(DtSocio datos) {
		super(datos.getNickname(), datos.getNombre(), datos.getApellido(), datos.getEmail(), datos.getFechaNacimiento());
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
		DtSocio datos = new DtSocio(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getFecha());
		return datos;
	}
	
    public DtSocioExtra getDtExt() {
    	Map<String,Set<String>> x = new HashMap<>();
    	for(compraClase rc: compraClases) {
    		String z = rc.getClase().getAD().getNombre();
    		if(!x.containsKey(z)) {
    			Set<String> y = new HashSet<>();
    			x.put(z,y);
    			for(compraClase rc2: compraClases) {
    				if(rc2.getClase().getAD().getNombre().equals(z))
    					y.add(rc2.getClase().getNombre());
    			}
    		}
    	}
    	DtSocioExtra datosExt = new DtSocioExtra(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getFecha(), x);
    	return datosExt;
    }
	
	public List<compraCuponera> getReciboCuponera() {
		return compraCuponeras;
	}
	
	public List<compraClase> getReciboClase() {
		return compraClases;
	}
	
	public void inscribirSocio(ActividadDeportiva actDep, Clase cl, tipoRegistro t, DtFechaHora reg) throws NoExisteCuponeraException, ClaseException {
		boolean noEstaInsc = true;
		for (compraClase res: compraClases) {
			if (res.getNombreClase() == cl.getNombre()) {
				noEstaInsc = false;
			}
		} 
		if (noEstaInsc) {
			if(t.equals(tipoRegistro.general)) {
				compraClase nuevoRecibo = new compraClase(reg, tipoRegistro.general, actDep.getCosto(), cl, this, null);
				compraClases.add(nuevoRecibo);
				cl.addRecibo(nuevoRecibo);	
			} else {
				for (compraCuponera y: compraCuponeras) {
					Cuponera cupActual = y.getCuponera();
					if (cupActual.tieneActividadDeportiva(actDep)) {
						int cantidadClases = 0;
						for (compraClase x: compraClases) {
							if ((x.getCuponera() == cupActual) && (x.tieneActividadDeportiva(actDep))) {
								cantidadClases++;
							}
						}
						if (cantidadClases < cupActual.cantidadClases(actDep)) {
							compraClase nuevoRecibo = new compraClase(reg,tipoRegistro.cuponera,actDep.getCosto(),cl,this,cupActual);
							compraClases.add(nuevoRecibo);
							cl.addRecibo(nuevoRecibo);
						}
					}
				}
				throw new NoExisteCuponeraException("Este Usuario no presenta Cuponeras validas para esta Clase.");
			}
		} else {
			throw new ClaseException("Este Usuario ya esta inscripto a esta Clase.");
		}
	}
}
