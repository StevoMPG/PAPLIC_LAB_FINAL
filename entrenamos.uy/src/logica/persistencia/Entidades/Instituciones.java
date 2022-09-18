package logica.persistencia.Entidades;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;


/**
 * Entity implementation class for Entity: Clases
 *
 */

@Entity
@Table(name = "INSTITUCIONES")
public class Instituciones implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NOMBRE_INSTITUCION",
    		nullable = false,
    		unique = true)
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    
    @Column(name = "URL")
    private String url;
    
    
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
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
		this.url = url;
	}  
}