package logica;
import java.util.Date;
public abstract class InfoBasicaUser {

	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private Date fechaNac;

	public InfoBasicaUser(String nickname, String nombre, String apellido, String correo, Date fechaNac) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fechaNac = fechaNac;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
    
	
	public boolean SonIguales( InfoBasicaUser i ) {
		
		if(
		(this.apellido == i.getApellido())&
		(this.correo == i.getCorreo())&
		(this.fechaNac == i.getFechaNac())&
		(this.nickname == i.getNickname())&
		(this.nombre == i.getNombre())){
			return true;
		}
		
		else {
			return false;
			}
	}
    
}
