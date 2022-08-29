package logica;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;


import datatypes.DtSocio;
import datatypes.DtSocioExtra;


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
}
