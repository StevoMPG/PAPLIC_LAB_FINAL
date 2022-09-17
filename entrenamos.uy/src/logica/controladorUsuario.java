package logica;

import java.util.Set;
import datatypes.DtUsuario;
import datatypes.DtUsuarioExtra;
import datatypes.DtFechaHora;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtProfesorExtra;
import datatypes.DtSocioExtra;
import excepciones.CuponeraNoExisteException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;
import logica.persistencia.DataPersistencia;

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
	
	public int ingresarDatosUsuario(DtUsuario datoUser) throws InstitucionException {
		manejadorUsuario handlerUsuario = manejadorUsuario.getInstance();
		if (handlerUsuario.existeNick(datoUser.getNickname()) || handlerUsuario.existeCorreo(datoUser.getEmail())) {
			return 1;
		} else {
			if (datoUser instanceof DtSocioExtra)
				datoUser = ((DtSocioExtra) datoUser).downgrade();
			else if (datoUser instanceof DtProfesorExtra)
				datoUser = ((DtProfesorExtra) datoUser).downgrade();
			if (datoUser instanceof DtSocio) {
				Socio newSocio = new Socio((DtSocio) datoUser);
				handlerUsuario.addUser(newSocio);
			} else {
				Profesor newProfesor = new Profesor((DtProfesor) datoUser);
				handlerUsuario.addUser(newProfesor);
				manejadorInstitucion handlerInstitucion = manejadorInstitucion.getInstance();
				Institucion instituto = handlerInstitucion.findInstitucion(((DtProfesor) datoUser).getNombreInstitucion());
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

	public void editarDatosBasicos(String userNick,  DtUsuario datoUser) throws UsuarioNoExisteException {
		manejadorUsuario handlerUsuario = manejadorUsuario.getInstance();
		Usuario user = handlerUsuario.findUsuario(userNick);
		if (user instanceof Profesor) {
			if(datoUser instanceof DtProfesorExtra)
				((Profesor) user).editarDatos(((DtProfesorExtra) datoUser).downgrade());
			else
				((Profesor) user).editarDatos((DtProfesor) datoUser);
			DataPersistencia.getInstance().persistirProfesorMod((Profesor) user);
		} else {
			user.editarDatos(datoUser);
			DataPersistencia.getInstance().persistirSocioMod(user);
		}
	}
	
	public void comprarCuponera(String cuponera,  String socio,  DtFechaHora fechaCompra) throws UsuarioNoExisteException,  CuponeraNoExisteException{
		compraCuponera compraClase = new compraCuponera(fechaCompra,  getHC().getCup(cuponera),  (Socio) getHU().findUsuario(socio));
		((Socio) getHU().findUsuario(socio)).addcompraCuponera(compraClase);
		getHC().getCup(cuponera).addRecibo(compraClase);
		((Socio) getHU().findUsuario(socio)).addcompraCuponera(compraClase);
	}
	
	private manejadorUsuario getHU() {
		return manejadorUsuario.getInstance();
	}
	private manejadorCuponera getHC() {
		return manejadorCuponera.getInstance();
	}
	

	
	public DtUsuarioExtra seleccionarUsuarioEmail(String userEmail) throws UsuarioNoExisteException{
		for (String nombreUsuariox: getHU().getNicknameUsuarios()){
			try {
				DtUsuarioExtra datoU = (DtUsuarioExtra) seleccionarUsuario(nombreUsuariox);
				if (datoU.getEmail().equals(userEmail))
					return datoU;
			}
			catch (UsuarioNoExisteException ignore) {
				;
			}
		}
		throw new UsuarioNoExisteException(userEmail);
	}
}


