package logica;

import java.util.ArrayList;
import java.util.List;

import datatypes.DtFechaHora;
import datatypes.DtClasesCuponeras;
import datatypes.DtCuponera;

public class Cuponera {
	private String nombre,descripcion;
	private DtFechaHora fechaInicio,fechaFin,fechaAlta;
	private float descuento,costo;
	
	private List<ClasesCuponera> cp;
	private List<compraCuponera> rc;
	
	Cuponera(String nombre, String descripcion, int descuento, DtFechaHora fechaInicio, DtFechaHora fechaFin, DtFechaHora fechaAlta){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = (float)descuento;
		this.fechaInicio = new DtFechaHora(fechaInicio);
		this.fechaFin = new DtFechaHora(fechaFin);
		this.fechaAlta = new DtFechaHora(fechaAlta);
		this.cp = new ArrayList<>();
		this.rc = new ArrayList<>();
		costo = 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public DtFechaHora getFechaInicio() {
		DtFechaHora d = new DtFechaHora(fechaInicio);
		return d;
	}
	
	public DtFechaHora getFechaFin() {
		DtFechaHora d = new DtFechaHora(fechaFin);
		return d;
	}
	
	public DtFechaHora getFechaAlta() {
		DtFechaHora d = new DtFechaHora(fechaAlta);
		return d;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public List<String> getNombresActDep(){
		List<String> nomnom = new ArrayList<>();
		for(ClasesCuponera cc: cp) {
			nomnom.add(cc.getNombreActDep());
		}
		return nomnom;
	}
	
	
	public int cantidadClases(ActividadDeportiva actDep) {
		for(ClasesCuponera cc: cp) {
			if(cc.getNombreActDep() == actDep.getNombre())
				return cc.getCantidadClases();
		}
		return 0;
	}
	public boolean tieneActividadDeportiva(ActividadDeportiva n) {
		for(ClasesCuponera cc: cp) {
			if(cc.getNombreActDep() == n.getNombre())
				return true;
		}
		return false;
	}
	public DtCuponera getDt() {
		List<DtClasesCuponeras> r = new ArrayList<>();
		for(ClasesCuponera cc: cp) {
			DtClasesCuponeras rr = new DtClasesCuponeras(cc.getNombreActDep(),cc.getCantidadClases());
			r.add(rr);
		}
		DtCuponera x = new DtCuponera(getNombre(), getDescripcion(), getDescuento(), getCosto(), getFechaInicio(),
				getFechaFin(), getFechaAlta(), r);
		return x;
	}

	public List<compraCuponera> getRc() {
		return rc;
	}
}
