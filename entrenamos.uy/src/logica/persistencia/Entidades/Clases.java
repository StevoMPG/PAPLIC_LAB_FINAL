package logica.persistencia.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import datatypes.DtClaseExtra;
import datatypes.DtFechaHora;


/**
 * Entity implementation class for Entity: Clases
 *
 */

@Entity
@Table(name = "CLASES")
public class Clases implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NOMBRE_CLASE",
    		nullable = false,
    		unique = true)
    private String nombre;
    
    @Column(name = "NICKNAME_PROFESOR")
    private String nicknameProfesor;
    
	@Column(name = "FECHA_HORA_INICIO")
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaInicio;
	
    @Column(name = "SOCIOS_MINIMOS")
    private Integer sociosMinimos;
    
    @Column(name = "SOCIOS_MAXIMOS")
    private Integer sociosMaximos;
    
    @Column(name = "URL")
    private String url;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_ALTA")
    private Calendar fechaAlta;
    
    @Column(name = "ACTIVIDAD")
    private String actividad;
    
    @Column(name = "INSTITUCION")
    private String institucion;
    
    public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	
    public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	
	
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getnicknameProfesor() {
		return nicknameProfesor;
	}

	public void setnicknameProfesor(String nicknameProfesor) {
		this.nicknameProfesor = nicknameProfesor;
	}
	
	
    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public Integer getSociosMaximos() {
        return sociosMaximos;
    }

    public void setSociosMaximos(Integer sociosMaximos) {
        this.sociosMaximos = sociosMaximos;
    }
    
    public Integer getSociosMinimos() {
        return sociosMaximos;
    }

    public void setSociosMinimos(Integer sociosMinimos) {
        this.sociosMinimos = sociosMinimos;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
		this.url = url;
	}
    
    public Calendar getFechaAlta() {
        return fechaInicio;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    

    @Override
    public String toString() {
    	DtFechaHora fechaIni = new DtFechaHora(fechaInicio);
    	
    	String fechaAlt = fechaAlta.get(Calendar.DAY_OF_MONTH) + "/" + String.valueOf(fechaAlta.get(Calendar.MONTH) + 1) + 
    			"/" + fechaAlta.get(Calendar.YEAR);
        return "Clases[ nombre = " + nombre +
        		", nicknameProfesor = " + nicknameProfesor +
        		", fechaInicio = " + fechaIni +
        		", sociosMinimos = " + sociosMinimos +
        		", sociosMaximos = " + sociosMaximos +
                ", url = " + url +
                ", fechaAlta = " + fechaAlt +
                ", institucion = " + institucion +
                ", actividad = " + actividad +
                "]";
    }
    
    public DtClaseExtra toDtClaseExt() {
    	List<String> nickAlumnos = new ArrayList<>();
    	
    	DtClaseExtra res = new DtClaseExtra(nombre, "", nicknameProfesor, sociosMinimos, sociosMaximos, url, new DtFechaHora(fechaInicio),
    			new DtFechaHora(fechaAlta), nickAlumnos, nickAlumnos);
    	return res;
    }   
}