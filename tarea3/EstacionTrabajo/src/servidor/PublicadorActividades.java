package servidor;

import java.util.ArrayList;
import java.util.Arrays;

import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;
import excepciones.NoExisteEntidadException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataActividadTuristicaP;
import logica.datatypes.DataPaqueteActividades;
import logica.interfaces.IControladorActividades;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorActividades {

	private Endpoint endpoint = null;
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorActividades iControladorActividades = fabrica.getIControladorActividades();

	@WebMethod(exclude = true)
	public void publicar(String host, String port) {
		endpoint = Endpoint.publish("http://" + host + ":" + port + "/publicador", this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public String[] obtenerCategorias() {
		return iControladorActividades.obtenerCategorias()
				.toArray(new String[iControladorActividades.obtenerCategorias().size()]);
	}

	@WebMethod
	public DataActividadTuristica[] obtenerActividadesConfirmadas() throws ActividadNoExisteException {
		ArrayList<DataActividadTuristica> list = iControladorActividades.obtenerActividadesConfirmadas();
		return list.toArray(new DataActividadTuristica[list.size()]);
	}

	@WebMethod
	public DataActividadTuristica[] obtenerActividadesConfirmadasDepartamento(String dep)
			throws ActividadNoExisteException {
		ArrayList<DataActividadTuristica> list = iControladorActividades.obtenerActividadesConfirmadas(dep);
		return list.toArray(new DataActividadTuristica[list.size()]);
	}

	@WebMethod
	public DataActividadTuristica[] obtenerActividadesConfirmadasDepartamentoCategoria(String dep, String cat)
			throws ActividadNoExisteException {
		ArrayList<DataActividadTuristica> list = iControladorActividades.obtenerActividadesConfirmadas(dep, cat);
		return list.toArray(new DataActividadTuristica[list.size()]);
	}

	@WebMethod
	public DataActividadTuristica consultarActividad(String nombre) throws ActividadNoExisteException {
		return iControladorActividades.consultarActividad(nombre);
	}

	@WebMethod
	public DataPaqueteActividades[] obtenerPaquetes() throws NoExisteEntidadException {
		return iControladorActividades.obtenerPaquetes()
				.toArray(new DataPaqueteActividades[iControladorActividades.obtenerPaquetes().size()]);
	}

	@WebMethod
	public DataPaqueteActividades consultarPaquete(String nombre) throws NoExisteEntidadException {
		return iControladorActividades.consultarPaquete(nombre);
	}

	@WebMethod
	public void crearActividad(String dpto, String descripcion, String nombre, int duracion, double costo,
			String ciudad, String prov, String[] categorias, String newFileNameAct, String urlVideo)
			throws ActividadRepetidaException {
		iControladorActividades.crearActividad(dpto, descripcion, nombre, duracion, costo, ciudad, prov,
				Arrays.asList(categorias), newFileNameAct, urlVideo, 0);
	}

	@WebMethod
	public void updateVisitsActividad(String nombre) {
		iControladorActividades.updateVisitsActividad(nombre);
	}

	@WebMethod
	public void finalizarActividad(String nombre) throws ActividadNoExisteException {
		iControladorActividades.finalizarActividad(nombre);
	}

	@WebMethod
	public DataActividadTuristica[] obtenerActividadesNoFinalizadas(String nickname) {
		return iControladorActividades.obtenerProveedorActividadesNoFinalizadas(nickname)
				.toArray(new DataActividadTuristica[iControladorActividades
						.obtenerProveedorActividadesNoFinalizadas(nickname).size()]);
	}

	@WebMethod
	public DataActividadTuristicaP[] obtenerActividadesFinalizadas(String nickname, String nombreActividad) {
		return iControladorActividades.obtenerProveedorActividadesFinalizadas(nickname, nombreActividad)
				.toArray(new DataActividadTuristicaP[iControladorActividades
						.obtenerProveedorActividadesFinalizadas(nickname, nombreActividad).size()]);
	}

}
