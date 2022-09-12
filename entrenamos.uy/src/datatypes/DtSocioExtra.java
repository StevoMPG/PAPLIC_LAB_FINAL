package datatypes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class DtSocioExtra extends DtUsuarioExtra {
	
	private Map<String,Set<String>> x;
	
	public DtSocioExtra(String nickname, String nombre, String apellido, String email, DtFechaHora fechaNacimiento, Map<String,Set<String>> clases, byte[] bs){
		super(nickname, nombre, apellido, email, email, fechaNacimiento, bs);
		this.x = clases;
	}
	
	public Map<String,Set<String>> getAguadeUwu(){
		return x;
	}
	
	public Set<String> getClases(){
		Set<String> y = new HashSet<>();
		for(Entry<String, Set<String>> q: x.entrySet())
			y.addAll(q.getValue());
		return y;
	}
	
	public DtSocio downgrade() {
		return new DtSocio(this.getNickname(), this.getNombre(), this.getApellido(), this.getEmail(), this.getFechaNacimiento(), this.getImagen());
	}
}
	
