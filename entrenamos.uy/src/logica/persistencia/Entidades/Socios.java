package logica.persistencia.Entidades;

import java.util.HashMap;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import datatypes.DtFechaHora;
import datatypes.DtSocioExtra;
import datatypes.DtUsuarioExtra;


/**
 * Entity implementation class for Entity: Socios
 *
 */


@Entity
@Table(name = "SOCIOS")
public class Socios extends Usuarios {
    private static final long serialVersionUID = 1L;
    
    @Override
	public DtUsuarioExtra toDtUsuarioExt() {
		DtSocioExtra res = new DtSocioExtra(nickname, nombre, apellido, email, new DtFechaHora(fechaNacimiento), 
				new HashMap<String, Set<String>>(), null);
		return res;
	}
}