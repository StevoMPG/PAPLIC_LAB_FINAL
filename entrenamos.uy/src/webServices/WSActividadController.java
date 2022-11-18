package webServices;

import java.util.Properties;
import java.util.Set;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import datatypesWS.DtActividadWS;
import datatypesWS.DtClaseWS;
import datatypesWS.DtInstitucionWS;
import excepciones.ActividadDeportivaException;
import excepciones.ClaseException;
import excepciones.InstitucionException;
import logica.IcontroladorActividadDeportiva;
import logica.Fabrica;
import main.Main;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSActividadController {

	private Endpoint endpoint = null;
	private Fabrica factory = Fabrica.getInstance();
	private IcontroladorActividadDeportiva IADC = factory.obtenerIcontroladorActDeportiva();
	
	public WSActividadController(){}
	
    @WebMethod(exclude = true)
    public void publicar(){
    	Properties prp = Main.config;
    	endpoint = Endpoint.publish("http://"+prp.getProperty("hostIP")+":"+prp.getProperty("hostPort")+prp.getProperty("actividadController_ServiceName"), this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
    	return endpoint;
    }
    
	public String[] obtenerInstituciones() {
		return IADC.obtenerInstituciones().toArray(new String[0]);
	}
	
	public Boolean ingresarDatosActividadDep(String nombreInsti,  DtActividadWS datosAD) throws InstitucionException, 
			ActividadDeportivaException{
		return IADC.ingresarDatosActividadDep(nombreInsti, datosAD.adapt());
	}
	
	public String[] obtenerActividades(String ins) throws InstitucionException{
		return IADC.obtenerActividades(ins).toArray(new String[0]);
	}
	
	public  String[] obtenerDeltaInstituciones(String nombreCup,  String ins) throws InstitucionException{
		return IADC.obtenerDeltaInstituciones(nombreCup, ins).toArray(new String[0]);
	}
	
	
	
	public DtClaseWS seleccionarClase(String  ins,  String actDep,  String clase) throws InstitucionException, 
			ActividadDeportivaException,  ClaseException{
		return new DtClaseWS(IADC.seleccionarClase(ins, actDep, clase));
	}
	
	public String[] obtenerSocios() {
		return IADC.obtenerSocios().toArray(new String[0]);
	}
	
	public DtActividadWS getActDepExt(String ins,  String actDep) throws InstitucionException,  
			ActividadDeportivaException{
		return new DtActividadWS(IADC.getActDepExt(ins, actDep));
	}
	/* OPERACION DESACTIVADA
	public int altaInstitucion(String nombre,  String descripcion,  String URL);
	*/
	
	public DtInstitucionWS obtenerDatosInstitucion(String inst) throws InstitucionException{
		return new DtInstitucionWS(IADC.obtenerDatosInstitucion(inst));
	}
	
	/* OPERACION DESACTIVADA
	public void aprobarActividad(String actividadDeportiva,  TEstado estado);
	*/
	public void finalizarActividad(String actividadDeportiva) {
		IADC.finalizarActividad(actividadDeportiva);
	}
	
	public String[] obtenerActDepIngresadas() {
		return IADC.obtenerActDepIngresadas().toArray(new String[0]);
	}
	
	/* OPERACION DESACTIVADA
	public void ingresarCatergoria(DtCategoria datos) throws CategoriaException;
	 */
	
	public String[] obtenerCategorias() {
		return IADC.obtenerCategorias().toArray(new String[0]);
	}

	public DtActividadWS buscarActDep(String nombreActDep) throws ActividadDeportivaException{
		return new DtActividadWS(IADC.buscarActDep(nombreActDep));
	}
	
	public String[] buscarActividades(String coincidencia){
		return IADC.buscarActividades(coincidencia).toArray(new String[0]);
	}
}
