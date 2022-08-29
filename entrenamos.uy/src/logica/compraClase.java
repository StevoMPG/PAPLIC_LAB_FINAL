package logica;

import datatypes.DtFechaHora;
import datatypes.tipoRegistro;

public class compraClase {
	private DtFechaHora fechaInscripcion;
	private tipoRegistro tipo;
	private float costo;
	private Clase aClase;
	private Socio aSocio;
	private Cuponera cup;
	

	
	compraClase(DtFechaHora f,tipoRegistro t, float cc, Clase c, Socio s, Cuponera cupon){
		aSocio = s;
		aClase = c;
		fechaInscripcion = f;
		tipo = t;
		costo = cc;
		cup = cupon;
	}
	
	public DtFechaHora getFechaInscripcion() {
		DtFechaHora ret = new DtFechaHora(fechaInscripcion);
		return ret;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public boolean esTipoCuponera() {
		return tipo.equals(tipoRegistro.cuponera);
	}
	
	public String getNombreClase() {
		return aClase.getNombre();
	}
	
	public boolean tieneActividadDeportiva(ActividadDeportiva actDep) {
		return aClase.tieneActividadDeportiva(actDep);
	}
	
	public String getNickCorreoSocio() {
		return aSocio.getNickname() + " <" + aSocio.getCorreo() + "> - " 
				+ fechaInscripcion.toFecha() + " (" + tipo.toString() + ")";
	}
	
	public String getNick() {
		return aSocio.getNickname();
	}
	
	public Cuponera getCuponera() {
		return cup;
	}

	public Clase getClase() {
		return aClase;
	}
}