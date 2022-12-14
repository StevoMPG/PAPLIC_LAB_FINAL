
package webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WSClaseControllerService", targetNamespace = "http://webServices/", wsdlLocation = "http://localhost:9129/entrenamosuy/claseController?wsdl")
public class WSClaseControllerService
    extends Service
{

    private final static URL WSCLASECONTROLLERSERVICE_WSDL_LOCATION;
    private final static WebServiceException WSCLASECONTROLLERSERVICE_EXCEPTION;
    private final static QName WSCLASECONTROLLERSERVICE_QNAME = new QName("http://webServices/", "WSClaseControllerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9129/entrenamosuy/claseController?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSCLASECONTROLLERSERVICE_WSDL_LOCATION = url;
        WSCLASECONTROLLERSERVICE_EXCEPTION = e;
    }

    public WSClaseControllerService() {
        super(__getWsdlLocation(), WSCLASECONTROLLERSERVICE_QNAME);
    }

    public WSClaseControllerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSCLASECONTROLLERSERVICE_QNAME, features);
    }

    public WSClaseControllerService(URL wsdlLocation) {
        super(wsdlLocation, WSCLASECONTROLLERSERVICE_QNAME);
    }

    public WSClaseControllerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSCLASECONTROLLERSERVICE_QNAME, features);
    }

    public WSClaseControllerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSClaseControllerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WSClaseController
     */
    @WebEndpoint(name = "WSClaseControllerPort")
    public WSClaseController getWSClaseControllerPort() {
        return super.getPort(new QName("http://webServices/", "WSClaseControllerPort"), WSClaseController.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSClaseController
     */
    @WebEndpoint(name = "WSClaseControllerPort")
    public WSClaseController getWSClaseControllerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webServices/", "WSClaseControllerPort"), WSClaseController.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSCLASECONTROLLERSERVICE_EXCEPTION!= null) {
            throw WSCLASECONTROLLERSERVICE_EXCEPTION;
        }
        return WSCLASECONTROLLERSERVICE_WSDL_LOCATION;
    }

}
