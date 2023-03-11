package servidor;

import excepciones.DepartamentoNoExisteException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.datatypes.DataDepartamento;
import logica.interfaces.IControladorDepartamentos;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorDepartamentos {

	private Endpoint endpoint = null;
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorDepartamentos iControladorDepartamentos = fabrica.getIControladorDepartamentos();

	@WebMethod(exclude = true)
	public void publicar(String host, String port) {
		endpoint = Endpoint.publish("http://" + host + ":" + port + "/publicador", this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public DataDepartamento[] obtenerDepartamentos() throws DepartamentoNoExisteException {
		return iControladorDepartamentos.obtenerDepartamentos();
	}

}
