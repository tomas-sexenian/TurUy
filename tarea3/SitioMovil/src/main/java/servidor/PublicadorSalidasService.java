
package servidor;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;
import servlets.ConfigListener;

/**
 * This class was generated by the XML-WS Tools. XML-WS Tools 4.0.0 Generated
 * source version: 3.0
 * 
 */
@WebServiceClient(name = "PublicadorSalidasService", targetNamespace = "http://servidor/")
public class PublicadorSalidasService extends Service {

	private final static URL PUBLICADORSALIDASSERVICE_WSDL_LOCATION;
	private final static WebServiceException PUBLICADORSALIDASSERVICE_EXCEPTION;
	private final static QName PUBLICADORSALIDASSERVICE_QNAME = new QName("http://servidor/",
			"PublicadorSalidasService");

	static {
		URL url = null;
		WebServiceException e = null;
		try {
			url = new URL(ConfigListener.cfg.getProperty("salidasControllerURL"));
		} catch (MalformedURLException ex) {
			e = new WebServiceException(ex);
		}
		PUBLICADORSALIDASSERVICE_WSDL_LOCATION = url;
		PUBLICADORSALIDASSERVICE_EXCEPTION = e;
	}

	public PublicadorSalidasService() {
		super(__getWsdlLocation(), PUBLICADORSALIDASSERVICE_QNAME);
	}

	public PublicadorSalidasService(WebServiceFeature... features) {
		super(__getWsdlLocation(), PUBLICADORSALIDASSERVICE_QNAME, features);
	}

	public PublicadorSalidasService(URL wsdlLocation) {
		super(wsdlLocation, PUBLICADORSALIDASSERVICE_QNAME);
	}

	public PublicadorSalidasService(URL wsdlLocation, WebServiceFeature... features) {
		super(wsdlLocation, PUBLICADORSALIDASSERVICE_QNAME, features);
	}

	public PublicadorSalidasService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public PublicadorSalidasService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
		super(wsdlLocation, serviceName, features);
	}

	/**
	 * 
	 * @return returns PublicadorSalidas
	 */
	@WebEndpoint(name = "PublicadorSalidasPort")
	public PublicadorSalidas getPublicadorSalidasPort() {
		return super.getPort(new QName("http://servidor/", "PublicadorSalidasPort"), PublicadorSalidas.class);
	}

	/**
	 * 
	 * @param features A list of {@link jakarta.xml.ws.WebServiceFeature} to
	 *                 configure on the proxy. Supported features not in the
	 *                 <code>features</code> parameter will have their default
	 *                 values.
	 * @return returns PublicadorSalidas
	 */
	@WebEndpoint(name = "PublicadorSalidasPort")
	public PublicadorSalidas getPublicadorSalidasPort(WebServiceFeature... features) {
		return super.getPort(new QName("http://servidor/", "PublicadorSalidasPort"), PublicadorSalidas.class, features);
	}

	private static URL __getWsdlLocation() {
		if (PUBLICADORSALIDASSERVICE_EXCEPTION != null) {
			throw PUBLICADORSALIDASSERVICE_EXCEPTION;
		}
		return PUBLICADORSALIDASSERVICE_WSDL_LOCATION;
	}

}