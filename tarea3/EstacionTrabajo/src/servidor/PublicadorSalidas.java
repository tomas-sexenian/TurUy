package servidor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import excepciones.ActividadNoExisteException;
import excepciones.InscripcionRepetidaException;
import excepciones.NoExisteEntidadException;
import excepciones.NoHayCapacidadException;
import excepciones.PaqueteVencidoException;
import excepciones.SalidaNoExisteException;
import excepciones.SalidaRepetidaException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.datatypes.DataSalidaTuristica;
import logica.interfaces.IControladorSalidas;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorSalidas {

	private Endpoint endpoint = null;
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorSalidas iControladorSalidas = fabrica.getIControladorSalidas();

	@WebMethod(exclude = true)
	public void publicar(String host, String port) {
		endpoint = Endpoint.publish("http://" + host + ":" + port + "/publicador", this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public DataSalidaTuristica[] getSalidas() throws SalidaNoExisteException {
		return iControladorSalidas.getSalidas();
	}

	@WebMethod
	public DataSalidaTuristica[] getSalidasVigentes(String actividad) throws SalidaNoExisteException {
		return iControladorSalidas.getSalidasVigentes(actividad);
	}

	@WebMethod
	public DataSalidaTuristica consultarSalida(String nombre)
			throws SalidaNoExisteException, ActividadNoExisteException {
		return iControladorSalidas.consultarSalida(nombre);
	}

	@WebMethod
	public void crearSalida(String nombre, int cantMaxTuristas, String lugarSalida, String fechaSalidaString,
			String horaSalida, String actividad, String newFileNameSalida)
			throws SalidaRepetidaException, ActividadNoExisteException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		LocalDate fechaSalida = LocalDate.parse(fechaSalidaString, formatter);
		iControladorSalidas.crearSalida(nombre, cantMaxTuristas, lugarSalida, fechaSalida, horaSalida, actividad,
				newFileNameSalida);
	}

	@WebMethod
	public void updateVisitsSalida(String nombre) {
		iControladorSalidas.updateVisitsSalida(nombre);
	}

	@WebMethod
	public void inscribirTuristaSalidaPaquete(String nickname, String nombreSalida, String nomPaquete, int cantTuristas)
			throws NoExisteEntidadException, InscripcionRepetidaException, NoHayCapacidadException,
			ActividadNoExisteException, PaqueteVencidoException {
		iControladorSalidas.inscribirTuristaSalidaPaquete(nickname, nombreSalida, nomPaquete, cantTuristas);
	}

	@WebMethod
	public void inscribirTuristaSalida(String nickname, String nombreSalida, int cantTuristas)
			throws NoExisteEntidadException, InscripcionRepetidaException, NoHayCapacidadException {
		iControladorSalidas.inscribirTuristaSalida(nickname, nombreSalida, cantTuristas);
	}

}
