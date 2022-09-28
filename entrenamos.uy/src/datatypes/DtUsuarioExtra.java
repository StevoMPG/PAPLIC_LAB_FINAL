package datatypes;

import java.util.Set;

public class DtUsuarioExtra extends DtUsuario{
	
	private Set<String> seguidosNickname;
	private Set<String> seguidoresNickname;
	
	public DtUsuarioExtra(String nickname,  String nombre,  String apellido,  String email,  String contrasenia,  DtFechaHora fechaNacimiento,  byte[] imagen,  Set<String> seguidosNickname,  Set<String> seguidoresNickname) {
		super(nickname,  nombre,  apellido,  email,  contrasenia,  fechaNacimiento,  imagen);
		this.seguidosNickname = seguidosNickname;
		this.seguidoresNickname = seguidoresNickname;
	}
	public Set<String> getSeguidosNickname() {
    	return seguidosNickname;
    }

    
    public Set<String> getSeguidoresNickname() {
    	return seguidoresNickname;
    }
}