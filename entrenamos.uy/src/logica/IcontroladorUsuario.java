package logica;

import java.util.Set;

import datatypes.DtFechaHora;
import datatypes.DtUsuario;
import excepciones.CuponeraNoExisteException;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;

public interface IcontroladorUsuario {

	public Set<String> obtenerInstituciones();
	public Set<String> obtenerUsuario();
	
	public int ingresarDatosUsuario(DtUsuario datoUsuario) throws InstitucionException;
	public DtUsuario seleccionarUsuario (String usuarioNickname) throws UsuarioNoExisteException;
	public void editarDatosBasicos(String userNick, DtUsuario datoUser) throws UsuarioNoExisteException;
	public void comprarCuponera(String cuponera,  String socio,  DtFechaHora fechaCompra) throws UsuarioNoExisteException,  CuponeraNoExisteException;
}

