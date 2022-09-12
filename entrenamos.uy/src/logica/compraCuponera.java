package logica;

import datatypes.DtFechaHora;

public class compraCuponera {
	private DtFechaHora fechaCompra;
	private Cuponera cuponera;
	private Socio socio;
	
	compraCuponera(DtFechaHora compra,  Cuponera cupi,  Socio socio){
		this.cuponera = cupi;
		this.socio = socio;
		fechaCompra = new DtFechaHora(compra);
	}
	
	public DtFechaHora getFechaCompra() {
		DtFechaHora fecha = new DtFechaHora(fechaCompra);
		return fecha;
	}
	public int cantidadClases(ActividadDeportiva act) {
		return cuponera.cantidadClases(act);
	}

	public Socio getSocio() {
		return socio;
	}
	
	public Cuponera getCuponera() {
		return cuponera;
	}
}
