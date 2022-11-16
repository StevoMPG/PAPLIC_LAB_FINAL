package datatypes;

public class DtClasesCuponeras {
	
	private String nombreActividad;
	
	private int cantidadClases;
	
	public DtClasesCuponeras(String nombreAct,  int cantClase) {
		nombreActividad = nombreAct;
		cantidadClases = cantClase;
	}
	
	public String getNombreActividad() {
		return nombreActividad;
	}
	
	public int getCantidadClases() {
		return cantidadClases;
	}

}
