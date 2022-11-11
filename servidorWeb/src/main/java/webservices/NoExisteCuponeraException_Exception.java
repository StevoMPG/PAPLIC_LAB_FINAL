
package webservices;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "NoExisteCuponeraException", targetNamespace = "http://webServices/")
public class NoExisteCuponeraException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private NoExisteCuponeraException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public NoExisteCuponeraException_Exception(String message, NoExisteCuponeraException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public NoExisteCuponeraException_Exception(String message, NoExisteCuponeraException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: webservices.NoExisteCuponeraException
     */
    public NoExisteCuponeraException getFaultInfo() {
        return faultInfo;
    }

}