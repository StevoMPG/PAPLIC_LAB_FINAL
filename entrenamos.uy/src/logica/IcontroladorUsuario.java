package logica;

import java.util.Set;

import datatypes.DtUsuario;
import excepciones.InstitucionException;
import excepciones.UsuarioNoExisteException;

public interface IcontroladorUsuario {

	public Set<String> obtenerInstituciones();
	public Set<String> obtenerUsuario();
	
	public int ingresarDatosUsuario(DtUsuario datoUsuario) throws InstitucionException;
	public DtUsuario seleccionarUsuario (String usuarioNickname) throws UsuarioNoExisteException;
	
	
}
