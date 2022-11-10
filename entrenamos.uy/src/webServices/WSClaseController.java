package webServices;

import java.util.Properties;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import datatypes.tipoRegistro;
import datatypesWS.DtClaseWS;
import datatypesWS.DtFechaWS;
import datatypesWS.TRegWS;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.FechaInvalidaException;
import excepciones.InstitucionException;
import excepciones.NoExisteCuponeraException;
import excepciones.UsuarioNoExisteException;
import logica.IcontroladorClase;
import logica.Fabrica;
import main.Main;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSClaseController {

	private Endpoint endpoint = null;
	private Fabrica factory = Fabrica.getInstance();
	private IcontroladorClase IDCC = factory.obtenerIcontroladorDictadoClase();
	
	public WSClaseController(){}
	
    @WebMethod(exclude = true)
    public void publicar(){
    	Properties prp = Main.config;
    	endpoint = Endpoint.publish("http://"+prp.getProperty("hostIP")+":"+prp.getProperty("hostPort")+prp.getProperty("claseController_ServiceName"), this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
    	return endpoint;
    }	
    
	public String[] obtenerUsuarios() {
		return IDCC.obtenerUsuarios().toArray(new String[0]);
	}
	
	public String[] obtenerInstituciones() {
		return IDCC.obtenerInstituciones().toArray(new String[0]);
	}
	
	//Dada una Actividad Deportiva te devuelve el nombre de la Institucion asociada
	public String obtenerInstitucionActDep(String actDep) {
		return IDCC.obtenerInstitucionActDep(actDep);
	}
	
	public String[] obtenerActividades(String ins) throws InstitucionException{
		return IDCC.obtenerActividades(ins).toArray(new String[0]);
	}
	public String[] obtenerActividadesAprobadas(String ins) throws InstitucionException{
		return IDCC.obtenerActividadesAprobadas(ins).toArray(new String[0]);
	}
	
	public String[] obtenerProfesores(String ins) throws InstitucionException{
		return IDCC.obtenerProfesores(ins).toArray(new String[0]);
	}
	
	public String[] obtenerClases(String ins,  String actDep) throws InstitucionException{
		return IDCC.obtenerClases(ins,actDep).toArray(new String[0]);
	}
	
	public DtClaseWS seleccionarClase(String  ins,  String actDep,  String clase) throws InstitucionException,  ClaseException, 
			ActividadDeportivaException{
		return new DtClaseWS(IDCC.seleccionarClase(ins, actDep, clase));
	}
	
	public DtClaseWS buscarClase(String nombreClase) throws ClaseException{
		return new DtClaseWS(IDCC.buscarClase(nombreClase));
	}
	
	public int ingresarDatosClase(String ins,  String actDep,  DtClaseWS datos) throws  InstitucionException,  FechaInvalidaException, 
			ClaseException,  UsuarioNoExisteException,  ActividadDeportivaException{
		return IDCC.ingresarDatosClase(ins, actDep, datos.adapt());
	}
	

	public void inscribirSocio(String ins,  String actDep,  String clase,  String socio,  TRegWS tipoRegistro,  DtFechaWS fechaReg, String cuponera) 
			throws  ClaseException,  FechaInvalidaException,  NoExisteCuponeraException,  InstitucionException,  
			UsuarioNoExisteException,  ActividadDeportivaException{
		cuponera = ((cuponera=="") ? null : cuponera);
	//	IDCC.inscribirSocio(ins, actDep, clase, socio, tipoRegistro.values()[tipoRegistro.ordinal()], fechaReg.adapt(), cuponera);
	}

	public String[] obtenerSocios() {
		return IDCC.obtenerSocios().toArray(new String[0]);
	}

	public String[] getCuponerasSocioClase(String nombreSocio, String nombreInst, String nombreAd, String nombreClase) {
		return IDCC.getCuponerasSocioClase(nombreSocio, nombreInst, nombreAd, nombreClase).toArray(new String[0]);
	}
	
	public String[] getCuponerasDisponibles(String nombreSocio,  String nombreInst,  String nombreAd) 
			throws UsuarioNoExisteException,  InstitucionException,  ActividadDeportivaException{
		return IDCC.getCuponerasDisponibles(nombreSocio, nombreInst, nombreAd).toArray(new String[0]);
	}

	public String[] sortearPremios(String  ins,  String actDep,  String clase) throws InstitucionException,  ClaseException, 
	ActividadDeportivaException{
		return IDCC.sortearPremios(ins, actDep, clase).toArray(new String[0]);
	}
}
