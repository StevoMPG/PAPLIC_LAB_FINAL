package datatypes;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class DtPremio {
	private String descripcion;
	private int cantidad;
	private List<String> ganadores = null;
	private DtFechaHora fechaSorteo = null;
	public DtPremio(String descripcionm, int cantidadd, List<String> ganadoress, DtFechaHora fechaSorteoo){
		descripcion = descripcionm;
		cantidad = cantidadd;
		ganadores = ganadoress;
		fechaSorteo = fechaSorteoo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public List<String> getGanadores() {
		return ganadores;
	}

	public DtFechaHora getFechaSorteo() {
		return fechaSorteo;
	}

	public void setFechaSorteo(DtFechaHora fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}
}
