package datatypes;

import java.util.Set;

public class DtActividadDeportivaExtra extends DtActividadDeportiva{
	
	/**
	 * 
	 */

	private Set<String> clases;
	private Set<String> cup;

	
	public DtActividadDeportivaExtra (String nombre,   String desc,   int durmin,   float costo,   DtFechaHora fechaReg,   Set<String> cat,   Set<String> clases,   Set<String> cuponeras,   tipoEstado state, String creador ){
		super(nombre,desc,durmin,costo,fechaReg,  cat,   state, creador);
		this.clases = clases;
		cup = cuponeras;
	}
	
	public Set<String> getClases(){
		return clases;
	}
	public Set<String> getCuponeras(){
		return cup;
	}
}