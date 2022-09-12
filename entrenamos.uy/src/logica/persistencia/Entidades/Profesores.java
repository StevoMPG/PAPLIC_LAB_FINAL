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
    
    @Column(name = "LINK")
    private String link;
    
    
    @Override
    public DtUsuarioExtra toDtUsuarioExt() {
		DtProfesorExtra res = new DtProfesorExtra(nickname,nombre,apellido,email,new DtFechaHora(fechaNacimiento), "", 
				"", "", "", new HashMap<String, Set<String>>(), null);
		return res;
    }
}