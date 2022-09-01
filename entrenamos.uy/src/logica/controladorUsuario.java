package logica;

import java.util.Set;
import datatypes.DtUsuario;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtProfesorExtra;
import datatypes.DtSocioExtra;

import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;

public class controladorUsuario implements IcontroladorUsuario {
	
	private static controladorUsuario instancia = null;
	
	public static controladorUsuario getInstance() {
		if (instancia == null) {
			instancia = new controladorUsuario();
		}
		return instancia;
	}
	
	public Set<String> obtenerUsuario(){
		manejadorUsuario ou = manejadorUsuario.getInstance();
		return ou.getNicknameUsuarios();
	}
	
	public Set<String> obtenerInstituciones(){
		manejadorInstitucion oi = manejadorInstitucion.getInstance();
		return oi.obtenerInstituciones();
	}
	
	public int ingresarDatosUsuario(DtUsuario datoUsuario) throws InstitucionException {
		
		manejadorUsuario ou = manejadorUsuario.getInstance();
		if(ou.existeNick(datoUsuario.getNickname()) || ou.existeCorreo(datoUsuario.getEmail())) {
			return 1;
		}else{
			if (datoUsuario instanceof DtSocio) {
				Socio newSocio = new Socio(((DtSocio)datoUsuario));
				ou.addUser(newSocio);
			}else {
				Profesor newProfesor = new Profesor(((DtProfesor)datoUsuario));
				ou.addUser(newProfesor);
				manejadorInstitucion oi = manejadorInstitucion.getInstance();
				Institucion instituto = oi.findInstitucion(((DtProfesor)datoUsuario).getNombreInstitucion());
				newProfesor.setInstitucion(instituto);
				instituto.addProfesor(newProfesor);
			}
			return 0;
		}
	}
	
	public DtUsuario seleccionarUsuario (String usuarioNickname) throws UsuarioNoExisteException {
		
		manejadorUsuario ou = manejadorUsuario.getInstance();
		Usuario user = ou.findUsuario(usuarioNickname);
		if (user instanceof Socio) {
			DtSocioExtra dtExtra = ((Socio)user).getDtExt();
			return dtExtra;
		}else {
			DtProfesorExtra dtExtra = ((Profesor)user).getDtExt();
			return dtExtra;
		}
	}

	public void editarDatosBasicos(String userNick, DtUsuario datoUser) throws UsuarioNoExisteException {
		manejadorUsuario hu = manejadorUsuario.getInstance();
		Usuario user = hu.findUsuario(userNick);
		if (user instanceof Profesor) {
			((Profesor)user).editarDatos(((DtProfesor)datoUser));
		} else {
			user.editarDatos(datoUser);
		}
	}
}


