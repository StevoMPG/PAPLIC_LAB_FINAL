package datatypes;

public class DtSocio extends DtUsuario {

	public DtSocio(String nickname, String nombre, String apellido, String email, DtFechaHora fechaNacimiento, byte[] bs) {
		super(nickname, nombre, apellido, email, fechaNacimiento, bs);
	}
}
