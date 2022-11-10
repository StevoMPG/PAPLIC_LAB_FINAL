
package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;
import net.java.dev.jaxb.array.StringArray;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WSActividadController", targetNamespace = "http://webServices/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    net.java.dev.jaxb.array.ObjectFactory.class,
    webservices.ObjectFactory.class
})
public interface WSActividadController {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     * @throws ActividadDeportivaException_Exception
     * @throws InstitucionException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/ingresarDatosActividadDepRequest", output = "http://webServices/WSActividadController/ingresarDatosActividadDepResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSActividadController/ingresarDatosActividadDep/Fault/InstitucionException"),
        @FaultAction(className = ActividadDeportivaException_Exception.class, value = "http://webServices/WSActividadController/ingresarDatosActividadDep/Fault/ActividadDeportivaException")
    })
    public boolean ingresarDatosActividadDep(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        DtActividadWS arg1)
        throws ActividadDeportivaException_Exception, InstitucionException_Exception
    ;

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://webServices/WSActividadController/finalizarActividadRequest", output = "http://webServices/WSActividadController/finalizarActividadResponse")
    public void finalizarActividad(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws InstitucionException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/obtenerActividadesRequest", output = "http://webServices/WSActividadController/obtenerActividadesResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSActividadController/obtenerActividades/Fault/InstitucionException")
    })
    public StringArray obtenerActividades(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws InstitucionException_Exception
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/obtenerInstitucionesRequest", output = "http://webServices/WSActividadController/obtenerInstitucionesResponse")
    public StringArray obtenerInstituciones();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws InstitucionException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/obtenerDeltaInstitucionesRequest", output = "http://webServices/WSActividadController/obtenerDeltaInstitucionesResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSActividadController/obtenerDeltaInstituciones/Fault/InstitucionException")
    })
    public StringArray obtenerDeltaInstituciones(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws InstitucionException_Exception
    ;

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns webservices.DtClaseWS
     * @throws ActividadDeportivaException_Exception
     * @throws ClaseException_Exception
     * @throws InstitucionException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/seleccionarClaseRequest", output = "http://webServices/WSActividadController/seleccionarClaseResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSActividadController/seleccionarClase/Fault/InstitucionException"),
        @FaultAction(className = ActividadDeportivaException_Exception.class, value = "http://webServices/WSActividadController/seleccionarClase/Fault/ActividadDeportivaException"),
        @FaultAction(className = ClaseException_Exception.class, value = "http://webServices/WSActividadController/seleccionarClase/Fault/ClaseException")
    })
    public DtClaseWS seleccionarClase(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2)
        throws ActividadDeportivaException_Exception, ClaseException_Exception, InstitucionException_Exception
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/obtenerSociosRequest", output = "http://webServices/WSActividadController/obtenerSociosResponse")
    public StringArray obtenerSocios();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns webservices.DtActividadWS
     * @throws ActividadDeportivaException_Exception
     * @throws InstitucionException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/getActDepExtRequest", output = "http://webServices/WSActividadController/getActDepExtResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSActividadController/getActDepExt/Fault/InstitucionException"),
        @FaultAction(className = ActividadDeportivaException_Exception.class, value = "http://webServices/WSActividadController/getActDepExt/Fault/ActividadDeportivaException")
    })
    public DtActividadWS getActDepExt(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws ActividadDeportivaException_Exception, InstitucionException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.DtInstitucionWS
     * @throws InstitucionException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/obtenerDatosInstitucionRequest", output = "http://webServices/WSActividadController/obtenerDatosInstitucionResponse", fault = {
        @FaultAction(className = InstitucionException_Exception.class, value = "http://webServices/WSActividadController/obtenerDatosInstitucion/Fault/InstitucionException")
    })
    public DtInstitucionWS obtenerDatosInstitucion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws InstitucionException_Exception
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/obtenerActDepIngresadasRequest", output = "http://webServices/WSActividadController/obtenerActDepIngresadasResponse")
    public StringArray obtenerActDepIngresadas();

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/obtenerCategoriasRequest", output = "http://webServices/WSActividadController/obtenerCategoriasResponse")
    public StringArray obtenerCategorias();

    /**
     * 
     * @param arg0
     * @return
     *     returns webservices.DtActividadWS
     * @throws ActividadDeportivaException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webServices/WSActividadController/buscarActDepRequest", output = "http://webServices/WSActividadController/buscarActDepResponse", fault = {
        @FaultAction(className = ActividadDeportivaException_Exception.class, value = "http://webServices/WSActividadController/buscarActDep/Fault/ActividadDeportivaException")
    })
    public DtActividadWS buscarActDep(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadDeportivaException_Exception
    ;

}
