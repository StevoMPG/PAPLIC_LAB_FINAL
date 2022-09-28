package datatypes;

public class DtCategoria {
	
	private String nombre;
	
	public DtCategoria(String nombre) {
		this.setNombre(nombre);
	}
	public DtCategoria() { }
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}