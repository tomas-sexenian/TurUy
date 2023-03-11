
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
@WebServiceClient(name = "PublicadorActividadesService", targetNamespace = "http://servidor/")
public class PublicadorActividadesService extends Service {

	private final static URL PUBLICADORACTIVIDADESSERVICE_WSDL_LOCATION;
	private final static WebServiceException PUBLICADORACTIVIDADESSERVICE_EXCEPTION;
	private final static QName PUBLICADORACTIVIDADESSERVICE_QNAME = new QName("http://servidor/",
			"PublicadorActividadesService");

	static {
		URL url = null;
		WebServiceException e = null;
		try {
			url = new URL(ConfigListener.cfg.getProperty("actividadesControllerURL"));
		} catch (MalformedURLException ex) {
			e = new WebServiceException(ex);
		}
		PUBLICADORACTIVIDADESSERVICE_WSDL_LOCATION = url;
		PUBLICADORACTIVIDADESSERVICE_EXCEPTION = e;
	}

	public PublicadorActividadesService() {
		super(__getWsdlLocation(), PUBLICADORACTIVIDADESSERVICE_QNAME);
	}

	public PublicadorActividadesService(WebServiceFeature... features) {
		super(__getWsdlLocation(), PUBLICADORACTIVIDADESSERVICE_QNAME, features);
	}

	public PublicadorActividadesService(URL wsdlLocation) {
		super(wsdlLocation, PUBLICADORACTIVIDADESSERVICE_QNAME);
	}

	public PublicadorActividadesService(URL wsdlLocation, WebServiceFeature... features) {
		super(wsdlLocation, PUBLICADORACTIVIDADESSERVICE_QNAME, features);
	}

	public PublicadorActividadesService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public PublicadorActividadesService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
		super(wsdlLocation, serviceName, features);
	}

	/**
	 * 
	 * @return returns PublicadorActividades
	 */
	@WebEndpoint(name = "PublicadorActividadesPort")
	public PublicadorActividades getPublicadorActividadesPort() {
		return super.getPort(new QName("http://servidor/", "PublicadorActividadesPort"), PublicadorActividades.class);
	}

	/**
	 * 
	 * @param features A list of {@link jakarta.xml.ws.WebServiceFeature} to
	 *                 configure on the proxy. Supported features not in the
	 *                 <code>features</code> parameter will have their default
	 *                 values.
	 * @return returns PublicadorActividades
	 */
	@WebEndpoint(name = "PublicadorActividadesPort")
	public PublicadorActividades getPublicadorActividadesPort(WebServiceFeature... features) {
		return super.getPort(new QName("http://servidor/", "PublicadorActividadesPort"), PublicadorActividades.class,
				features);
	}

	private static URL __getWsdlLocation() {
		if (PUBLICADORACTIVIDADESSERVICE_EXCEPTION != null) {
			throw PUBLICADORACTIVIDADESSERVICE_EXCEPTION;
		}
		return PUBLICADORACTIVIDADESSERVICE_WSDL_LOCATION;
	}

}