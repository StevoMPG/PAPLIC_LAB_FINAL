package logica;


import java.util.HashMap;
import java.util.Map;

import datatypes.DtFechaHora;
import datatypes.DtUsuario;



public abstract class Usuario {
	

    private String nickname,  nombre,  apellido,  correo,  contrasenia;
    
    private DtFechaHora fechaNacimiento;
    
    private byte[] imagen;
    
    private Map<String,  Usuario> seguidos;
    private Map<String,  Usuario> seguidores;
    
    protected Usuario(String nick,  String nombre,  String apellido,  String correo,  String contrasenia,  DtFechaHora fecha,  byte[] imagen) {
        this.setNickname(nick);
    	this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.contrasenia = contrasenia;
        this.setFecha(fecha);
        this.setImagen(imagen);
        seguidos = new HashMap<>();
        seguidores = new HashMap<>();
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
    
    private void setImagen(byte[] imagen) {
    	this.imagen = imagen;
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
    
    public String getContrasenia() {
    	return contrasenia;
    }
    
    public DtFechaHora getFecha() {
    	return fechaNacimiento;
    }
    
    public byte[] getImagen() {
    	return imagen;
    }
    
    public Map<String,  Usuario> getSeguidos() {
    	return seguidos;
    }
    
    public Map<String,  Usuario> getSeguidores() {
    	return seguidores;
    }
    
    public boolean sigue(String elCosmeFulanito) {
    	return seguidos.containsKey(elCosmeFulanito);
    }
    
    public boolean esSeguido(String elCosmeFulanito) {
    	return seguidores.containsKey(elCosmeFulanito);
    }
    
    public abstract boolean esSocio();

    public void editarDatos(DtUsuario datos) {
    	this.setNombre(datos.getNombre());
    	this.setApellido(datos.getApellido());
    	this.contrasenia = datos.getContrasenia();
    	this.setFecha(datos.getFechaNacimiento());
    	this.setImagen(datos.getImagen());
    }
    
    public void agregarSeguidor(Usuario u) {
    	seguidores.put(u.getNickname(),  u);
    }
    
    public void agregarSeguido(Usuario u) {
    	seguidos.put(u.getNickname(), u);
    }
    
    public void removerSeguidor(Usuario u) {
    	seguidores.remove(u.getNickname());
    }
    
    public void removerSeguido(Usuario u) {
    	seguidos.remove(u.getNickname());
    }
}