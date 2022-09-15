package logica;

import datatypes.DtFechaHora;
import datatypes.DtcompraClase;
import datatypes.tipoRegistro;

public class compraClase {
	private DtFechaHora fechaInscripcion;
	private tipoRegistro tipo;
	private float costo;
	private Clase aClase;
	private Socio aSocio;
	private Cuponera cup;
	

	
	compraClase(DtFechaHora fecha,  tipoRegistro tipoRegistro,  float precio,  Clase aula,  Socio individuoAsociadoAlClub,  Cuponera cupon){
		aSocio = individuoAsociadoAlClub;
		aClase = aula;
		fechaInscripcion = fecha;
		tipo = tipoRegistro;
		costo = precio;
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
	public Socio getSocio() {
		return aSocio;
	}
	
	public DtcompraClase getDt() {
		String cuponera = (cup == null) ? new String() : cup.getNombre();
		return new DtcompraClase(aClase.getNombre(), aSocio.getNickname(), cuponera, fechaInscripcion,
				tipo, costo);
	}
}