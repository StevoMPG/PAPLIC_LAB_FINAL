package datatypes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class DtSocioExtra extends DtUsuarioExtra {
	
	private Map<String,  Set<String>> clasesDeActividades;
	private Map<String, Set<String>> clasesDeActividadesFinalizadas;
	private Set<String> cuponerasCompradas;
	
	public DtSocioExtra(String nickname, String nombre, String apellido, String email,  String contrasenia, DtFechaHora fechaNacimiento, Map<String,Set<String>> clases, byte[] imagen, Set<String> seguidosNickname,   Set<String> seguidoresNickname,
			  Set<String> cuponeras, Map<String, Set<String>> clasesDeActividadesFinalizadas){
		super(nickname,   nombre,   apellido,   email,   contrasenia,   fechaNacimiento,   imagen,   seguidosNickname,   seguidoresNickname);
		cuponerasCompradas = cuponeras;
		this.clasesDeActividadesFinalizadas = clasesDeActividadesFinalizadas;
		this.clasesDeActividades = clases;
	}
	
	public Map<String,Set<String>> getAguadeUwu(){
		return clasesDeActividades;
	}
	
	public Set<String> getClases(){
		Set<String> y = new HashSet<>();
		for(Entry<String, Set<String>> q: clasesDeActividades.entrySet())
			y.addAll(q.getValue());
		return y;
	}
	
	
	public DtSocio downgrade() {
		return new DtSocio(this.getNickname(), this.getNombre(), this.getApellido(), this.getEmail(), this.getContrasenia(), this.getFechaNacimiento(), this.getImagen());
	}
	
	public Map<String, Set<String>> getClasesDeActividadesFinalizadas() {
		return clasesDeActividadesFinalizadas;
	}
	public void setClasesDeActividadesFinalizadas(Map<String, Set<String>> r) {
		this.clasesDeActividadesFinalizadas = r;
	}
	
	public Set<String> getCuponerasCompradas() {
		return cuponerasCompradas;
	}
}
	
