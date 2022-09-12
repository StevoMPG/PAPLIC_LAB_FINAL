package logica;

import datatypes.DtFechaHora;
import datatypes.DtUsuario;

public abstract class Usuario {

    protected String nickname, nombre, apellido, correo;
    
    protected DtFechaHora fechaNacimiento;

    private byte[] imagen;
    
    public Usuario(String nick, String nombre, String apellido, String correo, DtFechaHora fecha,  byte[] imagen) {
        this.setNickname(nick);
    	this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setFecha(fecha);
        this.setImagen(imagen);
    }
    

    
    private void setNickname(String nick) {
    	this.nickname = nick;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void setApellido(String apellido) {
        this.apellido = apellido;
    }

    private void setCorreo(String mail) {
        this.correo = mail;
    }
    
    private void setFecha(DtFechaHora fecha) {
    	this.fechaNacimiento = fecha;
    }
    
    public String getNickname() {
    	return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }
    
    public DtFechaHora getFecha() {
    	return fechaNacimiento;
    }
    
    
    private void setImagen(byte[] imagen) {
    	this.imagen = imagen;
    }
    
    public byte[] getImagen() {
    	return imagen;
    }
    
    
    public abstract boolean esSocio();

    public void editarDatos(DtUsuario datos) {
    	this.setNombre(datos.getNombre());
    	this.setApellido(datos.getApellido());
    	this.setFecha(datos.getFechaNacimiento());
    }
}