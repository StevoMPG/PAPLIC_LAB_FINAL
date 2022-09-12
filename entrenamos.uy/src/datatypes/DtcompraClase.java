package datatypes;

public class DtcompraClase {

	private String aClase;
	private String aSocio;
	private String cupons;
	private DtFechaHora fechaInscripcion;
	private tipoRegistro tipo;
	private float costo;
	
	public DtcompraClase(String clase, String socio, String cuponera, DtFechaHora fecha, tipoRegistro tipo, float costo) {
		aClase = clase;
		aSocio = socio;
		cupons = cuponera;
		fechaInscripcion = fecha;
		this.tipo = tipo;
		this.costo = costo;
	}

	public String getaClase() {
		return aClase;
	}

	public void setaClase(String aClase) {
		this.aClase = aClase;
	}

	public String getaSocio() {
		return aSocio;
	}

	public void setaSocio(String aSocio) {
		this.aSocio = aSocio;
	}

	public String getCupons() {
		return cupons;
	}

	public void setCupons(String cupons) {
		this.cupons = cupons;
	}

	public DtFechaHora getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(DtFechaHora fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public tipoRegistro getTipo() {
		return tipo;
	}

	public void setTipo(tipoRegistro tipo) {
		this.tipo = tipo;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
}