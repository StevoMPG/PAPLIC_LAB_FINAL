package logica;

import datatypes.DtFechaHora;

public class compraCuponera {
	private DtFechaHora fechaCompra;
	private Cuponera c;
	private Socio s;
	
	compraCuponera(DtFechaHora compra,Cuponera c,Socio s){
		this.c = c;
		this.s = s;
		fechaCompra = new DtFechaHora(compra);
	}
	
	public DtFechaHora getFechaCompra() {
		DtFechaHora r = new DtFechaHora(fechaCompra);
		return r;
	}
	public int cantidadClases(ActividadDeportiva act) {
		return c.cantidadClases(act);
	}

	public Socio getSocio() {
		return s;
	}
	
	public Cuponera getCuponera() {
		return c;
	}
}
