package logica.persistencia.Entidades;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import datatypes.DtActividadDeportivaExtra;
import datatypes.DtFechaHora;



/**
 * Entity implementation class for Entity: ActividadesDeportivas
 *
 */


@Entity
@Table(name = "ACTIVIDADES_DEPORTIVAS")
public class ActividadesDeportivas implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NOMBRE_ACTIVIDAD",
    		nullable = false,
    		unique = true)
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    
    @Column(name = "INSTITUCION")
    private String institucion;
    
    @Column(name = "DURACION")
    private Integer duracion;
    
    @Column(name = "COSTO")
    private Float costo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_ALTA")
    private Calendar fechaAlta;
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getInsitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    
    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }
    
    public Calendar getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
	
    @Override
    public String toString() {
    	String fechaAlt = fechaAlta.get(Calendar.DAY_OF_MONTH) + "/" + fechaAlta.get(Calendar.MONTH) + 
    			"/" + fechaAlta.get(Calendar.YEAR);
        return "ActividadesDeportivas [nombre = " + nombre +
        		", desripcion = " + descripcion +
        		", institucion = " + institucion +
        		", duracion = " + duracion +
        		", costo = " + costo +
                ", fechaAlt = " + fechaAlt +
                "]";
    }
    
    public DtActividadDeportivaExtra toDtActividadDeportivaExt() {
    	Set<String> nombreClases = new HashSet<>();
    	DtActividadDeportivaExtra res = new DtActividadDeportivaExtra(nombre, descripcion, duracion, costo,
    			new DtFechaHora(fechaAlta), new HashSet<>(), nombreClases);
    	return res;
    }
}