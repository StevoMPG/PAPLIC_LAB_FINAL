package logica.persistencia.Entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACT_FAVORITAS_SOCIOS")
public class Favoritas implements Serializable {
    private static final long serialVersionUID = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "SOCIO")
    private String nombreSoc;
    
    @Column(name = "ACTIVIDAD")
    private String nombreAct;

    
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
    
    
    public String getNombreAct() {
        return nombreAct;
    }

    public void setNombreAct(String nombreAct) {
        this.nombreAct = nombreAct;
    }

}