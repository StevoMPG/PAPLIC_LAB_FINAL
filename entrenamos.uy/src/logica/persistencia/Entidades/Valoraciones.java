package logica.persistencia.Entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VALORACIONES")
public class Valoraciones implements Serializable {
    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "SOCIO")
    private String nombreSoc;
    
    @Column(name = "CLASE")
    private String nombreClase;
        
    @Column(name = "PROFESOR")
    private String nombreProf;
    
    @Column(name = "VALORACION")
    private int valoracion;
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getNombreSoc() {
        return nombreSoc;
    }

    public void setNombreSoc(String nombreSoc) {
        this.nombreSoc = nombreSoc;
    }
    
    
    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }
    
    public String getNombreProf() {
        return nombreProf;
    }

    public void setNombreProf(String nombreProf) {
        this.nombreProf = nombreProf;
    }
    
    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

}