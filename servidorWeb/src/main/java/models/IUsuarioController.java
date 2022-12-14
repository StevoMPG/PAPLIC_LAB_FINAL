package models;

import java.util.Set;

import datatypes.DtFechaHora;
import datatypes.DtUsuario;
import datatypes.DtUsuarioExtra;
import excepciones.ClaseException;
import excepciones.CuponeraNoExisteException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;


public interface IUsuarioController {
	
	public Set<String> obtenerInstituciones();

	public Set<String> obtenerUsuarios();
	
	public int ingresarDatosUsuario(DtUsuario datoUser) throws InstitucionException;
	
	public DtUsuarioExtra seleccionarUsuario(String userNick) throws UsuarioNoExisteException;
	public DtUsuarioExtra seleccionarUsuarioEmail(String userEmail) throws UsuarioNoExisteException;
	
	public void editarDatosBasicos(String userNick,  DtUsuario datoUser) throws UsuarioNoExisteException;
	
	public void seguir(String seguidor,  String seguido) throws UsuarioNoExisteException ;
	
	public void dejarDeSeguir(String seguidor,  String seguido) throws UsuarioNoExisteException ;

	public void comprarCuponera(String cuponera,  String socio,  DtFechaHora fechaCompra) throws UsuarioNoExisteException,  CuponeraNoExisteException;
	
	public boolean verificarIdentidadEmail(String email,  String pass);
	public boolean verificarIdentidadNickname(String nick,  String pass);
	
	public void favoritearActividad(String nick, String ins, String actDep) throws UsuarioNoExisteException, InstitucionException;
	public void valorarProfesor(String nickSocio, String ins, String actDep, String cla, int valor) throws UsuarioNoExisteException, ClaseException, InstitucionException;
}
