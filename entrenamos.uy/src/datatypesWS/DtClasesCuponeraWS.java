package datatypesWS;

import datatypes.DtClasesCuponeras;

public class DtClasesCuponeraWS {
	private String nombreActividad;
	private int cantidadClases;
	public DtClasesCuponeraWS() { }
	public DtClasesCuponeraWS(DtClasesCuponeras dtcc) {
		setNombreActividad(dtcc.getNombreActividad());
		setCantidadClases(dtcc.getCantidadClases());
	}
	
	public DtClasesCuponeras adapt() {
		return new DtClasesCuponeras(nombreActividad,cantidadClases);
	}
	public int getCantidadClases() {
		return cantidadClases;
	}
	public void setCantidadClases(int cantidadClases) {
		this.cantidadClases = cantidadClases;
	}
	public String getNombreActividad() {
		return nombreActividad;
	}
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

}
