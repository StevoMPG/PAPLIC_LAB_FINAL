package logica;

import datatypes.DtClaseExtra;
import datatypes.DtClase;
import datatypes.DtFechaHora;
import logica.persistencia.DataPersistencia;

import java.util.ArrayList;
import java.util.List;

public class Clase {
	private String nombre;
	private DtFechaHora fechaClase;
	private int minSocios;
	private int maxSocios;
	private String URL;
	private DtFechaHora fechaRegistro;
	private Profesor p;
	private ActividadDeportiva actDep;
	private List<compraClase> listaReciboClase;
	
	Clase(DtClase datoClase, Profesor profe, ActividadDeportiva actDep){
		this.actDep = actDep;
		this.nombre = datoClase.getNombre();
		this.fechaClase = datoClase.getFechaClase();
		this.minSocios = datoClase.getMinSocios();
		this.maxSocios = datoClase.getMaxSocios();
		this.URL = datoClase.getURL();
		this.fechaRegistro = datoClase.getFechaRegistro();
		this.p = profe;
		this.listaReciboClase = new ArrayList<compraClase>();

	}
	
	
	public String getNombre() {
		String res = nombre;
		return res;
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
		for(compraClase x: listaReciboClase) {
			ListNombres.add(x.getNickCorreoSocio());
			SoloNombres.add(x.getNick());
		}
		
		DtClaseExtra claseDatos = new DtClaseExtra(nombre,  p.getNickname(),  p.getCorreo(),  minSocios,  maxSocios,  URL,  this.getFechaClase(), 
				this.getFechaRegistro(),  ListNombres,  SoloNombres);
		return claseDatos;
	}
	
	public boolean hayLugar() {
		return listaReciboClase.size() < maxSocios;
	}
	
	public boolean tieneActividadDeportiva(ActividadDeportiva actDep) {
		return this.actDep == actDep;
	}


	public boolean tieneActividadDeportiva(String activity) {
		return actDep.getNombre().equals(activity);
	}
	

	public ActividadDeportiva getAD() {
		return actDep;
	}
	
	public List<compraClase> getRecibo(){
		return listaReciboClase;
	}
	
	public void addRecibo(compraClase recibo) {
		listaReciboClase.add(recibo);
		DataPersistencia.getInstance().persistirRegistroClase(recibo);
	}

	public void suicidar() {
		for(compraClase rc: listaReciboClase)
			rc.suicidar();
		
	}
	
	
}