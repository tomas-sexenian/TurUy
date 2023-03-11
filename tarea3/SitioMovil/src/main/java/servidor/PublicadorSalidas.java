
package servidor;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "PublicadorSalidas", targetNamespace = "http://servidor/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PublicadorSalidas {


    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataSalidaTuristica
     * @throws ActividadNoExisteException_Exception
     * @throws SalidaNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/PublicadorSalidas/consultarSalidaRequest", output = "http://servidor/PublicadorSalidas/consultarSalidaResponse", fault = {
        @FaultAction(className = SalidaNoExisteException_Exception.class, value = "http://servidor/PublicadorSalidas/consultarSalida/Fault/SalidaNoExisteException"),
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://servidor/PublicadorSalidas/consultarSalida/Fault/ActividadNoExisteException")
    })
    public DataSalidaTuristica consultarSalida(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ActividadNoExisteException_Exception, SalidaNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataSalidaTuristicaArray
     * @throws SalidaNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/PublicadorSalidas/getSalidasVigentesRequest", output = "http://servidor/PublicadorSalidas/getSalidasVigentesResponse", fault = {
        @FaultAction(className = SalidaNoExisteException_Exception.class, value = "http://servidor/PublicadorSalidas/getSalidasVigentes/Fault/SalidaNoExisteException")
    })
    public DataSalidaTuristicaArray getSalidasVigentes(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws SalidaNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws InscripcionRepetidaException_Exception
     * @throws NoExisteEntidadException_Exception
     * @throws NoHayCapacidadException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorSalidas/inscribirTuristaSalidaRequest", output = "http://servidor/PublicadorSalidas/inscribirTuristaSalidaResponse", fault = {
        @FaultAction(className = NoExisteEntidadException_Exception.class, value = "http://servidor/PublicadorSalidas/inscribirTuristaSalida/Fault/NoExisteEntidadException"),
        @FaultAction(className = InscripcionRepetidaException_Exception.class, value = "http://servidor/PublicadorSalidas/inscribirTuristaSalida/Fault/InscripcionRepetidaException"),
        @FaultAction(className = NoHayCapacidadException_Exception.class, value = "http://servidor/PublicadorSalidas/inscribirTuristaSalida/Fault/NoHayCapacidadException")
    })
    public void inscribirTuristaSalida(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        int arg2)
        throws InscripcionRepetidaException_Exception, NoExisteEntidadException_Exception, NoHayCapacidadException_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @throws ActividadNoExisteException_Exception
     * @throws InscripcionRepetidaException_Exception
     * @throws NoExisteEntidadException_Exception
     * @throws NoHayCapacidadException_Exception
     * @throws PaqueteVencidoException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorSalidas/inscribirTuristaSalidaPaqueteRequest", output = "http://servidor/PublicadorSalidas/inscribirTuristaSalidaPaqueteResponse", fault = {
        @FaultAction(className = NoExisteEntidadException_Exception.class, value = "http://servidor/PublicadorSalidas/inscribirTuristaSalidaPaquete/Fault/NoExisteEntidadException"),
        @FaultAction(className = InscripcionRepetidaException_Exception.class, value = "http://servidor/PublicadorSalidas/inscribirTuristaSalidaPaquete/Fault/InscripcionRepetidaException"),
        @FaultAction(className = NoHayCapacidadException_Exception.class, value = "http://servidor/PublicadorSalidas/inscribirTuristaSalidaPaquete/Fault/NoHayCapacidadException"),
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://servidor/PublicadorSalidas/inscribirTuristaSalidaPaquete/Fault/ActividadNoExisteException"),
        @FaultAction(className = PaqueteVencidoException_Exception.class, value = "http://servidor/PublicadorSalidas/inscribirTuristaSalidaPaquete/Fault/PaqueteVencidoException")
    })
    public void inscribirTuristaSalidaPaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        int arg3)
        throws ActividadNoExisteException_Exception, InscripcionRepetidaException_Exception, NoExisteEntidadException_Exception, NoHayCapacidadException_Exception, PaqueteVencidoException_Exception
    ;

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorSalidas/updateVisitsSalidaRequest", output = "http://servidor/PublicadorSalidas/updateVisitsSalidaResponse")
    public void updateVisitsSalida(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @throws ActividadNoExisteException_Exception
     * @throws SalidaRepetidaException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorSalidas/crearSalidaRequest", output = "http://servidor/PublicadorSalidas/crearSalidaResponse", fault = {
        @FaultAction(className = SalidaRepetidaException_Exception.class, value = "http://servidor/PublicadorSalidas/crearSalida/Fault/SalidaRepetidaException"),
        @FaultAction(className = ActividadNoExisteException_Exception.class, value = "http://servidor/PublicadorSalidas/crearSalida/Fault/ActividadNoExisteException")
    })
    public void crearSalida(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        int arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6)
        throws ActividadNoExisteException_Exception, SalidaRepetidaException_Exception
    ;

    /**
     * 
     * @return
     *     returns servidor.DataSalidaTuristicaArray
     * @throws SalidaNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/PublicadorSalidas/getSalidasRequest", output = "http://servidor/PublicadorSalidas/getSalidasResponse", fault = {
        @FaultAction(className = SalidaNoExisteException_Exception.class, value = "http://servidor/PublicadorSalidas/getSalidas/Fault/SalidaNoExisteException")
    })
    public DataSalidaTuristicaArray getSalidas()
        throws SalidaNoExisteException_Exception
    ;

}