package datatypes;

public class DtClasesCuponeras {
	
	private String nombreActividad;
	
	private int cantidadClases;
	
	public DtClasesCuponeras(String s, int t) {
		nombreActividad = s;
		cantidadClases = t;
	}
	public String getNombreActividad() {
		return nombreActividad;
	}
	public int getCantidadClases() {
		return cantidadClases;
	}

}
