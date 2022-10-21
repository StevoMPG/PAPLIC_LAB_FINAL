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
@Table(name = "CATEGORIAS")
public class Categorias implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NOMBRE_CATEGORIA",
    		nullable = false,
    		unique = true)
    private String nombre;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}