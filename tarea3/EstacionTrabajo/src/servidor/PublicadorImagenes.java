/**
 * 
 */
package servidor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorImagenes {

	private Endpoint endpoint = null;
	private String imagesDirPath;

	// Constructor
	public PublicadorImagenes() {
		
	}

	// Operaciones las cuales quiero publicar

	@WebMethod(exclude = true)
	public void publicar(String host, String port, String pathImagenes) {
		this.imagesDirPath = pathImagenes;
		endpoint = Endpoint.publish("http://" + host + ":" + port + "/publicador", this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public byte[] getFile(@WebParam(name = "fileName") String name) throws IOException {
		byte[] byteArray = null;
		try {
			File img = new File(imagesDirPath + name);
			FileInputStream streamer = new FileInputStream(img);
			byteArray = new byte[streamer.available()];
			streamer.read(byteArray);
			streamer.close();
		} catch (IOException e) {
			throw e;
		}
		return byteArray;
	}

	@WebMethod
	public void uploadFile(@WebParam(name = "fileName") byte[] byteArray, String name) throws IOException {
		File uploadDir = new File(imagesDirPath + name);

		try (FileOutputStream stream = new FileOutputStream(uploadDir)) {
			stream.write(byteArray);
		}
	}
}
