package datatypes;

import java.util.Set;

public class DtUsuarioExtra extends DtUsuario{
	
	public DtUsuarioExtra(String nickname,  String nombre,  String apellido,  String email,  String contrasenia,  DtFechaHora fechaNacimiento,  byte[] imagen) {
		super(nickname,  nombre,  apellido,  email, fechaNacimiento, imagen);
	}

}