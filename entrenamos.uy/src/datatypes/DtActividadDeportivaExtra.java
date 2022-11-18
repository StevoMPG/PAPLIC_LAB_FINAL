package datatypes;

import java.util.Set;

public class DtActividadDeportivaExtra extends DtActividadDeportiva{
	private Set<String> clases;
	private Set<String> cup;
	private int favoritos=0;
	
	public DtActividadDeportivaExtra(String nombre,   String desc,   int durmin,   float costo,   DtFechaHora fechaReg,   Set<String> cat,   Set<String> clases,   Set<String> cuponeras,   tipoEstado state,  String creador){
		super(nombre,   desc,   durmin,   costo,   fechaReg,   cat,   state,   creador);
		this.clases = clases;
		this.cup = cuponeras;
	}
	public DtActividadDeportivaExtra(String nombre,   String desc,   int durmin,   float costo,   DtFechaHora fechaReg,   Set<String> cat,   Set<String> clases,   Set<String> cuponeras,   tipoEstado state,  String creador,  String img){
		super(nombre,   desc,   durmin,   costo,   fechaReg,   cat,   state,   creador,   img);
		this.clases = clases;
		this.cup = cuponeras;
	}
	public DtActividadDeportivaExtra(String nombre,   String desc,   int durmin,   float costo,   DtFechaHora fechaReg,   Set<String> cat,   Set<String> clases,   Set<String> cuponeras,   tipoEstado state,  String creador,  String img, int cantfav){
		super(nombre,   desc,   durmin,   costo,   fechaReg,   cat,   state,   creador,   img);
		this.clases = clases;
		this.cup = cuponeras;
		favoritos = cantfav;
	}
	public Set<String> getClases(){
		return clases;
	}
	public Set<String> getCuponeras(){
		return cup;
	}
	public int getFavoritos() {
		return favoritos;
	}
	public void setFavoritos(int favoritos) {
		this.favoritos = favoritos;
	}
}
