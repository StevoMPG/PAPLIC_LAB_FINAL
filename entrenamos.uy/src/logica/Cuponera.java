package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datatypes.DtFechaHora;
import excepciones.CuponeraInmutableException;
import logica.persistencia.DataPersistencia;
import datatypes.DtClasesCuponeras;
import datatypes.DtCuponera;

public class Cuponera {
	
	private String nombre,  descripcion,  img;
	private DtFechaHora fechaInicio,  fechaFin,  fechaAlta;
	private float descuento,  costo;
	
	private List<ClasesCuponera> clasesCuphead;
	private List<compraCuponera> recibosCuponardos;
	private Set<Categoria> categorias;
	
	Cuponera(String nombre,  String descripcion,  int descuento,  DtFechaHora fechaInicio,  DtFechaHora fechaFin,  DtFechaHora fechaAlta){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = (float) descuento;
		this.fechaInicio = new DtFechaHora(fechaInicio);
		this.fechaFin = new DtFechaHora(fechaFin);
		this.fechaAlta = new DtFechaHora(fechaAlta);
		
		clasesCuphead = new ArrayList<>();
		recibosCuponardos = new ArrayList<>();
		categorias = new HashSet<>();
		costo = 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public DtFechaHora getFechaInicio() {
		DtFechaHora fecha = new DtFechaHora(fechaInicio);
		return fecha;
	}
	
	public DtFechaHora getFechaFin() {
		DtFechaHora fecha = new DtFechaHora(fechaFin);
		return fecha;
	}
	
	public DtFechaHora getFechaAlta() {
		DtFechaHora fecha = new DtFechaHora(fechaAlta);
		return fecha;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public List<String> getNombresActDep(){
		List<String> nomnom = new ArrayList<>();
		for (ClasesCuponera cc: clasesCuphead) {
			nomnom.add(cc.getNombreActDep());
		}
		return nomnom;
	}
	
	public void addActDep(ActividadDeportiva act,  int num) throws CuponeraInmutableException{
		if (recibosCuponardos.size()>0)
			throw new CuponeraInmutableException("No es posible modificar la cuponera dado que ya hay socios que la compraron.");
		ClasesCuponera claCup = new ClasesCuponera(num,  this,  act);
		clasesCuphead.add(claCup);
		categorias.addAll(act.getCategorias());
		act.addClasesCup(claCup);
		costo = costo + (1 - descuento/100)*act.getCosto()*num;
		DataPersistencia.getInstance().persistirActividadesCuponeras(claCup);
	}
	
	public int cantidadClases(ActividadDeportiva actDep) {
		for (ClasesCuponera cc: clasesCuphead) {
			if (cc.getNombreActDep() == actDep.getNombre())
				return cc.getCantidadClases();
		}
		return 0;
	}
	public boolean tieneActividadDeportiva(ActividadDeportiva actDep) {
		for (ClasesCuponera cc: clasesCuphead) {
			if (cc.getNombreActDep() == actDep.getNombre())
				return true;
		}
		return false;
	}
	public DtCuponera getDt() {
		List<DtClasesCuponeras> datosClases = new ArrayList<>();
		List<String> nombresCat = new ArrayList<>();
		for (ClasesCuponera cc: clasesCuphead) {
			DtClasesCuponeras datosClaseCuponera = new DtClasesCuponeras(cc.getNombreActDep(),  cc.getCantidadClases());
			datosClases.add(datosClaseCuponera);
		}
		for (Categoria c: categorias) {
			nombresCat.add(c.getNombre());
		}
		DtCuponera datosCup = new DtCuponera(getNombre(),  getDescripcion(),  getDescuento(),  getCosto(),  getFechaInicio(), 
				getFechaFin(),  getFechaAlta(),  datosClases,  nombresCat,  getImg());
		return datosCup;
	}
	public void addRecibo(compraCuponera reciboCup) {
		recibosCuponardos.add(reciboCup);
	}
	public List<compraCuponera> getRc() {
		return recibosCuponardos;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void estafar(ClasesCuponera cl) {
		clasesCuphead.remove(cl);
		costo = costo - (1 - descuento/100)*cl.getAd().getCosto()*cl.getCantidadClases();
	}
}

