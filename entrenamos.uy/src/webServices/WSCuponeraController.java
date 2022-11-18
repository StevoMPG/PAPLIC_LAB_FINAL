package webServices;

import java.util.Properties;
import java.util.Set;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import datatypesWS.DtCuponeraWS;
import excepciones.NoExisteCuponeraException;
import logica.IcontroladorCuponera;
import logica.Fabrica;
import main.Main;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSCuponeraController {

	private Endpoint endpoint = null;
	private Fabrica factory = Fabrica.getInstance();
	private IcontroladorCuponera ICC = factory.obtenerIcontroladorCuponera();

	public WSCuponeraController(){}
	
    @WebMethod(exclude = true)
    public void publicar(){
    	Properties prp = Main.config;
    	endpoint = Endpoint.publish("http://"+prp.getProperty("hostIP")+":"+prp.getProperty("hostPort")+prp.getProperty("cuponeraController_ServiceName"), this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
    	return endpoint;
    }	
    /*  OPERACION DESACTIVADA
	public int ingresarCuponera(String nombre,  String descripcion,  DtFecha inicio,  DtFecha fin,  
			int descuento,  DtFecha alta, String imagen) throws CuponeraRepetidaException,  FechaInvalidaException{
		return ICC.ingresarCuponera(nombre, descripcion, inicio, fin, descuento, alta, imagen);
	}
	*/
	public String [] getNombreCuponeras() {
		return ICC.getNombreCuponeras().toArray(new String[0]);
	}
	/* OPERACION DESACTIVADA
	public void agregarActividadCuponera(String nombre,  String instituto,  String actividadDeportiva,  int cantidadClases) 
			throws InstitucionException,  ActividadDeportivaException,  CuponeraInmutableException{
		ICC.agregarActividadCuponera(nombre, instituto, actividadDeportiva, cantidadClases);
	}
	*/
	
	public DtCuponeraWS seleccionarCuponera(String nombre) throws NoExisteCuponeraException{
		return new DtCuponeraWS(ICC.seleccionarCuponera(nombre));
	}
	
	public String [] getNombreCuponerasSinRecibos() {
		return ICC.getNombreCuponerasSinRecibos().toArray(new String[0]);
	}
	
	public String [] buscarCuponeras(String coincidencia) {
		return ICC.buscarCuponeras(coincidencia).toArray(new String[0]);
	}
}
