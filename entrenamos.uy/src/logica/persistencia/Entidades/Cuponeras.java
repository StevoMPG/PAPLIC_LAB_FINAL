package logica.persistencia.Entidades;

import java.io.Serializable;

import java.util.Calendar;


import javax.persistence.Column;
import javax.persistence.Entity;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * Entity implementation class for Entity: Cuponeras
 */

@Entity
@Table(name = "CUPONERAS")
public class Cuponeras implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NOMBRE_CUPONERA",
    		nullable = false,
    		unique = true)
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    
    @Column(name = "DESCUENTO")
    private float descuento;
    
	@Column(name = "FECHA_INICIO")
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaInicio;
    
	@Column(name = "FECHA_FIN")
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaFin;
    
	@Column(name = "FECHA_ALTA")
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaAlta;
	
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    } 
    
    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
    
    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public Calendar getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
      
}