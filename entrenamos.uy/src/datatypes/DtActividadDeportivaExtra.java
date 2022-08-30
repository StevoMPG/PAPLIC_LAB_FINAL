package datatypes;

import java.util.Set;

public class DtActividadDeportivaExtra extends DtActividadDeportiva{
	
	private Set<String> cl;
	private Set<String> cup;
	
	public DtActividadDeportivaExtra(String nombre, String desc, int durmin, float costo, DtFechaHora fechaReg, Set<String> clases, Set<String> cuponeras){
		super(nombre,desc,durmin,costo,fechaReg);
		cl = clases;
		cup = cuponeras;
	}
	
	public Set<String> getClases(){
		return cl;
	}
	
	public Set<String> getCuponeras(){
		return cup;
	}
}