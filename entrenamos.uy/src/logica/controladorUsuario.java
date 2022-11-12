package logica;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import datatypes.DtUsuario;
import datatypes.DtUsuarioExtra;
import datatypes.tipoEstado;
import datatypes.DtFechaHora;
import datatypes.DtProfesor;
import datatypes.DtSocio;
import datatypes.DtProfesorExtra;
import datatypes.DtSocioExtra;
import excepciones.ClaseException;
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
	
	
	public DtUsuarioExtra seleccionarUsuario(String userNick) throws UsuarioNoExisteException {
		manejadorUsuario handlerUsuario = manejadorUsuario .getInstance();
		Usuario user = handlerUsuario.findUsuario(userNick);
	
		if(userNick.endsWith("\uEAEA")){
			DtUsuarioExtra resu =  DataPersistencia.getInstance().getUsuario(userNick.replace("\uEAEA", ""));
			if(resu instanceof DtSocioExtra) {
				((DtSocioExtra) resu).setClasesDeActividadesFinalizadas(DataPersistencia.getInstance().obtenerActividadxClasesSocio(userNick.replace("\uEAEA", "")));
				return resu;
			}
			else if(resu instanceof DtProfesorExtra) {
				Set<String> f = DataPersistencia.getInstance().obtenerActividades(userNick.replace("\uEAEA", ""));
				Map<String,tipoEstado> ff = new HashMap<>();
				for(String fq:f) {
					ff.put(fq, tipoEstado.finalizada);
				}
				((DtProfesorExtra) resu).setHistoralActDepIngresadas(ff);
				return resu;
			}
		} else {
			user = handlerUsuario.findUsuario(userNick);
		}
		if (user instanceof Socio) {
			DtSocioExtra dtExt = ((Socio) user).getDtExt();
			return dtExt;
		} else {
			DtProfesorExtra dtExt = ((Profesor) user).getDtExt();
			return dtExt; 
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
	
	private manejadorInstitucion getHI() {
		return manejadorInstitucion.getInstance();
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
	
	
	public boolean verificarIdentidadEmail(String email,  String pass) {
		if (getHU().existeCorreo(email)) {
			try {
				return getHU().findUsuarioByEmail(email).getContrasenia() == pass;
			} catch (UsuarioNoExisteException e) {
				e.printStackTrace();
			}
		}
		return false;	
	}
	
	public boolean verificarIdentidadNickname(String nick,  String pass) {
		if (getHU().existeNick(nick)) {
			try {
				return getHU().findUsuario(nick).getContrasenia() == pass;
			} catch (UsuarioNoExisteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void seguir(String seguidor,  String seguido) throws UsuarioNoExisteException {
		manejadorUsuario handlerUsuario = manejadorUsuario.getInstance();
		Usuario seguidorU = handlerUsuario.findUsuario(seguidor);
		Usuario seguidoU = handlerUsuario.findUsuario(seguido);
		seguidorU.agregarSeguido(seguidoU);
		seguidoU.agregarSeguidor(seguidorU);
		DataPersistencia.getInstance().persistirSeguidores(seguidorU.getNickname(), seguidoU.getNickname());
	}
	
	public void dejarDeSeguir(String user1,  String user2) throws UsuarioNoExisteException {
		getHU().findUsuario(user1).removerSeguido(getHU().findUsuario(user2));
		getHU().findUsuario(user2).removerSeguidor(getHU().findUsuario(user1));
		//DataPersistencia.getInstance().persistirDESSeguidores(user1, user2);
	}
	
	@Override
	public void favoritearActividad(String nick, String ins, String actDep) throws UsuarioNoExisteException, InstitucionException{
		((Socio) getHU().findUsuario(nick)).changeFavoritos(getHI().findInstitucion(ins).findActividad(actDep));
		getHI().findInstitucion(ins).findActividad(actDep).changeFavoritos(((Socio) getHU().findUsuario(nick)));
	}

	@Override
	public void valorarProfesor(String nickSocio, String ins, String actDep, String cla, int valor) throws UsuarioNoExisteException, ClaseException, InstitucionException {
		((Socio) getHU().findUsuario(nickSocio)).valorarProfesor(getHI().findInstitucion(ins).findActividad(actDep).findClase(cla), valor);
		DataPersistencia.getInstance().persistirValoraciones((Socio) getHU().findUsuario(nickSocio),getHI().findInstitucion(ins).findActividad(actDep).findClase(cla), valor);
	} 
	
}


