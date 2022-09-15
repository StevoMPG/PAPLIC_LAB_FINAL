package logica.persistencia.Entidades;

import java.util.HashMap;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import datatypes.DtFechaHora;
import datatypes.DtProfesorExtra;
import datatypes.DtUsuarioExtra;



/**
 * Entity implementation class for Entity: Profesores
 *
 */


@Entity
@Table(name = "PROFESORES")
public class Profesores extends Usuarios {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "BIOGRAFIA")
    private String biografia;
    
    @Column(name = "WEB")
    private String link;
    
    @Column(name = "INSTITUCION")
    private String institucion;
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }
    
    
    @Override
    public DtUsuarioExtra toDtUsuarioExt() {
		DtProfesorExtra res = new DtProfesorExtra(nickname,nombre,apellido,email,new DtFechaHora(fechaNacimiento), institucion, 
				descripcion,  biografia, link, new HashMap<String, Set<String>>(), null);
		return res;
    }
}