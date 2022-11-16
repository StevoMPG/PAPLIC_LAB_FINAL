package logica;

import datatypes.DtClaseExtra;
import datatypes.DtClase;
import datatypes.DtFechaHora;
import datatypes.DtPremio;
import logica.persistencia.DataPersistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Clase {
	private String nombre;
	private DtFechaHora fechaClase;
	private int minSocios;
	private int maxSocios;
	private String url;
	private DtFechaHora fechaRegistro;
	private List<compraClase> listacompraClase;
	private Profesor vasilev;
	private ActividadDeportiva actDep;
	private String imgName, urlVideo;
	private Map<String, Calificacion> calificaciones;
	private Premio prize = null;
	
	Clase(DtClase datoClase,  Profesor profe,  ActividadDeportiva actDep){
		this.actDep = actDep;
		this.nombre = datoClase.getNombre();
		this.fechaClase = datoClase.getFechaClase();
		this.minSocios = datoClase.getMinSocios();
		this.maxSocios = datoClase.getMaxSocios();
		this.url = datoClase.getURL();
		this.fechaRegistro = datoClase.getFechaRegistro();
		this.vasilev = profe;
		this.listacompraClase = new ArrayList<compraClase>();
		this.calificaciones = new HashMap<>();
		this.urlVideo = datoClase.getUrlVideo();
		this.imgName = datoClase.getImgName();
		if (datoClase.getPremio() != null)
			prize = new Premio(this, datoClase.getPremio().getDescripcion(), datoClase.getPremio().getCantidad());
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
		return vasilev;
	}
	
	public String getURL() {
		return url;
	}
	
	public DtFechaHora getFechaRegistro() {
		DtFechaHora ret = new DtFechaHora(fechaRegistro);
		return ret;
	}
	
	public DtClaseExtra getDt() {
		List<String> SoloNombres = new ArrayList<>();
		List<String> ListNombres = new ArrayList<>();
		for (compraClase x: listacompraClase) {
			ListNombres.add(x.getNickCorreoSocio());
			SoloNombres.add(x.getNick());
		}
		Map<String, Integer> calif = new HashMap<>();
		for(Entry<String, Calificacion> x: calificaciones.entrySet()) {
			calif.put(x.getKey(), x.getValue().getValor());
		}
		DtPremio dorado = null;
		if(getPrize()!=null)
			dorado = getPrize().getDt();
		DtClaseExtra claseDatos = new DtClaseExtra(nombre,  vasilev.getNickname(),  vasilev.getCorreo(),  minSocios,  maxSocios,  url,  this.getFechaClase(), 
				this.getFechaRegistro(),  ListNombres,  SoloNombres,  imgName,
				getUrlVideo(),dorado,calif);
		return claseDatos;
	}
	
	public boolean hayLugar() {
		return listacompraClase.size() < maxSocios;
	}
	
	public boolean tieneActividadDeportiva(ActividadDeportiva actDep) {
		return this.actDep == actDep;
	}
	public List<compraClase> getRecibo(){
		return listacompraClase;
	}
	
	public void addRecibo(compraClase recibo, ActividadDeportiva act) {
		listacompraClase.add(recibo);
		DataPersistencia.getInstance().persistirRegistroClase(recibo, act);
	}
	public void addCalifiacion(String socioNick, Calificacion calif) {
		calificaciones.put(socioNick, calif);
	}
	
	public boolean tieneActividadDeportiva(String activity) {
		return actDep.getNombre().equals(activity);
	}
	
	public ActividadDeportiva getAD() {
		return actDep;
	}

	public String getUrlVideo() {
		return urlVideo;
	}
	public Map<String, Calificacion> getCalificaciones(){
		return calificaciones;
	}
	
	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public Premio getPrize() {
		return prize;
	}

	public void suicidar() {
		for(compraClase rc: listacompraClase)
			rc.suicidar();
		
	}
	public void setPremio(Premio premio) {
		prize = premio;
	}
	
}