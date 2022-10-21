package logica.persistencia.Entidades;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


/**
 * Entity implementation class for Entity: ActividadesCuponeras
 *
 */


@Entity
@Table(name = "ACTIVIDADES_CUPONERAS")
public class ActividadesCuponeras implements Serializable {
    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NOMBRE_ACTIVIDAD")
    private String nombre;
    
    @Column(name = "NOMBRE_CUPONERA")
    private String nombrec;
    
    @Column(name = "CANT_CLASES")
    private int clases;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getClases() {
        return clases;
    }

    public void setClases(int clases) {
        this.clases = clases;
    }
    
    public String getNombrec() {
        return nombrec;
    }

    public void setNombrec(String nombrec) {
        this.nombrec = nombrec;
    }
  
}
    
    