package logica;

import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

import excepciones.UsuarioNoExisteException;
import logica.persistencia.DataPersistencia;

public class manejadorUsuario {

	private static manejadorUsuario instancia = null;
	
	private Logger log;
	private Map<String, Usuario> usuarios;
	private Set<String> correos;
	
	private manejadorUsuario() {
		usuarios = new HashMap<>();
		correos = new HashSet<>();
		log = Logger.getLogger(manejadorInstitucion.class.getName());
		log.setLevel(Level.INFO);
		Handler handler = new ConsoleHandler();
		log.addHandler(handler);
	}
	
	public static manejadorUsuario getInstance() {
		if (instancia == null) {
			instancia = new manejadorUsuario(); 
		}
		return instancia;
	}
	
	//Retorna true si la accion de añadir el Usuario se realiza de forma exitosa, False en otro caso (ya existe un usuario
	//con ese nickname o correo)
	public boolean addUser(Usuario user) {
		if (existeNick(user.getNickname()) || existeCorreo(user.getCorreo())) {
			return false;
		} else {
			usuarios.put(user.getNickname(), user);
			correos.add(user.getCorreo());
			if (user.esSocio()) {
			DataPersistencia.getInstance().persistirSocio(user);
			}
			log.info("Usuario "+user.getNombre()+"registered, total: "+usuarios.size());
			return true;
		}
	}
	
	
	
	public Usuario findUsuario(String userNick) throws UsuarioNoExisteException {
		Usuario res = usuarios.get(userNick);
		if (res == null) {
			throw new UsuarioNoExisteException("Usuario no registrado en el sistema.");
		}
		return res;
	}
	
	public boolean existeNick(String userNick) {
		return usuarios.containsKey(userNick);
	}
	
	public boolean existeCorreo(String userCorreo) {
		return correos.contains(userCorreo);
	}
	
	public Set<String> getNicknameUsuarios() {
		return usuarios.keySet();
	}
	
	public Set<String> obtenerNicknameSocios() {
		Set<String> resultado = new HashSet<>();
		Collection<Usuario> userCollection = usuarios.values();
		Iterator<Usuario> itUser = userCollection.iterator();
		while (itUser.hasNext()) {
			Usuario userAux = itUser.next();
			if (userAux.esSocio()) {
				resultado.add(userAux.getNickname());
			}
		}
		return resultado;
	}
}