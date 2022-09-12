package logica.persistencia.Entidades;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import datatypes.DtFechaHora;
import datatypes.DtcompraClase;
import datatypes.tipoRegistro;


/**
 * Entity implementation class for Entity: Registros
 *
 */


@Entity
@Table(name = "REGISTROS")
public class Registros implements Serializable {
    private static final long serialVersionUID = 1L;
    //@EmbeddedId
    //private RegistrosId primaryKey;
    
    //@Embeddable
    //private class RegistrosId {
	@Id
	@ManyToOne
    @JoinColumn(name = "ID_SOCIO")
    private Socios socio;
    
	@Id
	@ManyToOne
    @JoinColumn(name = "ID_CLASE")
    private Clases clase;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_REGISTRO")
    private Calendar fechaRegistro;
    
    @Column(name = "COSTO")
    private Float costo;
    

    
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

	public Socios getSocio() {
		return socio;
	}

	public void setSocio(Socios socio) {
		this.socio = socio;
	}

	public Clases getClase() {
		return clase;
	}

	public void setClase(Clases clase) {
		this.clase = clase;
	}
    
   

    @Override
    public String toString() {
        return "Registros[Socio = " + socio.getNickname() +
        		", Clase = " + clase.getNombre() +
        		", FechaRegistro = " + new DtFechaHora(fechaRegistro) +
        		", Costo = " + costo + 
                "]";
    }

    public DtcompraClase toDtReciboClase() {
    	DtcompraClase res = new DtcompraClase(clase.getNombre(), socio.getNickname(), "", 
    			new DtFechaHora(fechaRegistro), tipoRegistro.general, costo);
    	return res;
    }
}