
package servidor;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "InscripcionRepetidaException", targetNamespace = "http://servidor/")
public class InscripcionRepetidaException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InscripcionRepetidaException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public InscripcionRepetidaException_Exception(String message, InscripcionRepetidaException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public InscripcionRepetidaException_Exception(String message, InscripcionRepetidaException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidor.InscripcionRepetidaException
     */
    public InscripcionRepetidaException getFaultInfo() {
        return faultInfo;
    }

}