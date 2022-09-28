package datatypes;

import java.util.List;

public class DtCuponera {

	private String nombre,descripcion;
	private float descuento,costo;
	private DtFechaHora fechaInicio,fechaFin,fechaAlta;
	private List<DtClasesCuponeras> contenido;
	private List<String> categorias;
	
	public DtCuponera(String nom, String descr, float desc, float cc, DtFechaHora fi, DtFechaHora ff, DtFechaHora fa, List<DtClasesCuponeras> v,  List<String> cat){
		this.nombre = nom;
		this.descripcion = descr;
		this.descuento = desc;
		this.fechaInicio = fi;
		this.fechaFin = ff;
		this.fechaAlta = fa;
		this.costo = cc;
		this.contenido = v;
		categorias=cat;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public float getDescuento() {
		return this.descuento;
	}
	
	public DtFechaHora getFechaInicio() {
		return this.fechaInicio;
	}
	
	public DtFechaHora getFechaFin() {
		return this.fechaFin;
	}
	
	public DtFechaHora getFechaAlta() {
		return this.fechaAlta;
	}
	
	public List<DtClasesCuponeras> getContenido() {
		return this.contenido;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public List<String> getCategorias() {
		return categorias;
	}

	public String toString() {
		String res = new String();
		res += "Nombre: " + this.getNombre() + "\n";
		res += "Descripcion: " + this.getDescripcion() + "\n";
		res += "Descuento: " + this.getDescuento() + "%\n";
		res += "Fecha inicio: " + this.getFechaInicio().toFecha() + "\n";
		res += "Fecha fin: " + this.getFechaFin().toFecha() + "\n";
		res += "Fecha registro: " + this.getFechaAlta().toFecha() + "\n";
		res += "Costo: " + this.getCosto() + " pesos.\n";
		res += "Contenido: " + this.getContenido() + "\n";
		return res;
	}
}