package logica.persistencia.Entidades;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.DiscriminatorType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import datatypes.DtFechaHora;
import datatypes.DtUsuarioExtra;
import logica.persistencia.Datatypes.TipoUsuario;



/**
 * Entity implementation class for Entity: Usuarios
 *
 */

@MappedSuperclass
@Table(name = "USUARIOS")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO_USUARIO",
discriminatorType = DiscriminatorType.INTEGER)
public abstract class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "NICKNAME",
    		nullable = false,
    		unique = true)
    protected String nickname;
    
    @Column(name = "EMAIL",
    		nullable = false,
    		unique = true)
    protected String email;
    
    @Column(name = "NOMBRE")
    protected String nombre;
    
    @Column(name = "APELLIDO")
    protected String apellido;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_NACIMIENTO")
    protected Calendar fechaNacimiento;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO")
    protected TipoUsuario tipoUsuario;
    
 /*   @Column(name = "IMAGEN")
    protected  byte[] imagen;
    
    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    */
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    

    public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	

    
    @Override
    public String toString() {
    	return "Usuarios[nickname =  " + nickname +
        		", email = " + email +
        		", nombre = " + nombre +
        		", apellido = " + apellido +
                ", fechaNacimiento = " + new DtFechaHora(fechaNacimiento) +
                "]";
    }
    
    public abstract DtUsuarioExtra toDtUsuarioExt();
}