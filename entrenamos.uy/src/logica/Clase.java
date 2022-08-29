package logica;

import datatypes.DtClaseExtra;
import datatypes.DtClase;
import datatypes.DtFechaHora;

import java.util.ArrayList;
import java.util.List;

public class Clase {
	private String nombre;
	private DtFechaHora fechaClase;
	private int minSocios;
	private int maxSocios;
	private String URL;
	private DtFechaHora fechaRegistro;
	private List<compraClase> ListReciboClase;
	private Profesor p;
	private ActividadDeportiva a;
	
	Clase(DtClase d, Profesor p, ActividadDeportiva a){
		this.a = a;
		this.nombre = d.getNombre();
		this.fechaClase = d.getFechaClase();
		this.minSocios = d.getMinSocios();
		this.maxSocios = d.getMaxSocios();
		this.URL = d.getURL();
		this.fechaRegistro = d.getFechaRegistro();
		this.p = p;
		this.ListReciboClase = new ArrayList<compraClase>();
	}
	
	public String getNombre() {
		String s = nombre;
		return s;
	}
	
	public DtFechaHora getFechaClase() {
		DtFechaHora ret = new DtFechaHora(fechaClase);
		return ret;
	}
	
	public int getMinSocios() {
		return minSocios;
	}
	
	public int getMaxSocios() {
		return maxSocios;
	}
	
	public Profesor getProfesor() {
		return p;
	}
	
	public String getURL() {
		return URL;
	}
	
	public DtFechaHora getFechaRegistro() {
		DtFechaHora ret = new DtFechaHora(fechaRegistro);
		return ret;
	}
	
	public DtClaseExtra getDt() {
		List<String> SoloNombres = new ArrayList<>();
		List<String> ListNombres = new ArrayList<>();
		for(compraClase x: ListReciboClase) {
			ListNombres.add(x.getNickCorreoSocio());
			SoloNombres.add(x.getNick());
		}
		DtClaseExtra x = new DtClaseExtra(nombre, p.getNickname(), p.getCorreo(), minSocios, maxSocios, URL, this.getFechaClase(),
				this.getFechaRegistro(), ListNombres, SoloNombres);
		return x;
	}
	
	public boolean hayLugar() {
		return ListReciboClase.size() < maxSocios;
	}
	
	public boolean tieneActividadDeportiva(ActividadDeportiva actDep) {
		return actDep == a;
	}
	
	public void addRecibo(compraClase rc) {
		ListReciboClase.add(rc);
	}
	
	public boolean tieneActividadDeportiva(String aa) {
		return a.getNombre().equals(aa);
	}
	
	public ActividadDeportiva getAD() {
		return a;
	}
	
}