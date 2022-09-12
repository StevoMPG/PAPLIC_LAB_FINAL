package datatypes;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DtProfesorExtra extends DtUsuarioExtra{

	private Map<String,Set<String>> x;
	private String nombreInstitucion,  descripcion,  biografia,  link;
	
	public DtProfesorExtra (String nickname, String nombre, String apellido, String email, DtFechaHora fechaNacimiento, String nombreInstitucion, String descripcion, String biografia, String link, Map<String,Set<String>> actxClase, byte[] bs) {
		super(nickname, nombre, apellido, email, link, fechaNacimiento, bs); 
		x = actxClase;
		this.nombreInstitucion = nombreInstitucion;
		this.descripcion = descripcion;
		this.biografia = biografia;
		this.link = link;
	}
	
	public Set<String> getActividadesDepAsociadas(){
		return x.keySet();
	}
	
	public Set<String> getClasesDictadas(){
		Set<String> y = new HashSet<>();
		for(Entry<String, Set<String>> q: x.entrySet())
			y.addAll(q.getValue());
		return y;
	}
	
	public Map<String,Set<String>> getClasesxActividades(){
		return x;
	}
	
	public String getNombreInstitucion() {
		return this.nombreInstitucion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getBiografia() {
		return this.biografia;
	}

	public String getLink() {
		return this.link;
	}
	
	public DtProfesor downgrade() {
		return new DtProfesor(this.getNickname(), this.getNombre(), this.getApellido(), this.getEmail(), this.getFechaNacimiento(), this.getNombreInstitucion(), this.getDescripcion(),
				this.getBiografia(), this.getLink(), this.getImagen());
	}

}
