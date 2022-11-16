package logica.persistencia.Entidades;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Entity implementation class for Entity: Registros
 *
 */


@Entity
@Table(name = "REGISTROS_CLASES")
public class Registros implements Serializable {
    private static final long serialVersionUID = 1L;

   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    
	@Column(name = "NOMBRE_SOCIO")
    private String nsocio;
    
    @Column(name = "NOMBRE_CLASE")
    private String nclase;
    
    @Column(name = "NOMBRE_ACT")
    private String nact;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_REGISTRO")
    private Calendar fechaRegistro;
    
    @Column(name = "COSTO")
    private Float costo;
    
    
    @Column(name = "TIPO_PAGO")
    private boolean pago;
    
    @Column(name = "ESTADO_ACTIVIDAD")
    private String tipoEstado;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

	public String getSocio() {
		return nsocio;
	}

	public void setSocio(String nsocio) {
		this.nsocio = nsocio;
	}

	public String getClase() {
		return nclase;
	}

	public void setClase(String nclase) {
		this.nclase = nclase;
	}
	
	public String getAct() {
		return nact;
	}

	public void setAct(String nact) {
		this.nact = nact;
	}
	
	
	public boolean getTipoPago() {
		return pago;
	}

	public void setTipoPago(boolean pago) {
		this.pago = pago;
	}
	
	public String getEstado() {
	     return tipoEstado;
	}

	public void setEstado(String tipoEstado) {
	     this.tipoEstado = tipoEstado;
	}

}