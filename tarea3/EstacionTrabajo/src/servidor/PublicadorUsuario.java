package servidor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import excepciones.EmailRepetidoException;
import excepciones.NickameRepetidoException;
import excepciones.PaqueteCompradoException;
import excepciones.UsuarioNoExisteException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Fabrica;
import logica.clases.Proveedor;
import logica.clases.Turista;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataCompra;
import logica.datatypes.DataInscripcion;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataTurista;
import logica.interfaces.IControladorUsuario;
import logica.manejadores.ManejadorUsuario;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorUsuario {

	private Endpoint endpoint = null;
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorUsuario iControladorUsuario = fabrica.getIControladorUsuario();

	@WebMethod(exclude = true)
	public void publicar(String host, String port) {
		endpoint = Endpoint.publish("http://" + host + ":" + port + "/publicador", this);
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public DataProveedor[] getProveedores() throws UsuarioNoExisteException {
		return iControladorUsuario.getProveedores();
	}

	@WebMethod
	public DataProveedor verInfoProveedor(String username) throws UsuarioNoExisteException {
		return iControladorUsuario.verInfoProveedor(username);
	}

	@WebMethod
	public DataTurista[] getTuristas() throws UsuarioNoExisteException {
		return iControladorUsuario.getTuristas();
	}

	@WebMethod
	public DataTurista verInfoTurista(String username) throws UsuarioNoExisteException {
		DataTurista turista = iControladorUsuario.verInfoTurista(username);
		return turista;
	}

	@WebMethod
	public void crearTurista(String username, String nombre, String apellido, String correo, String fecha,
			String nacionalidad, String contraseña, String newFileNameUsuario)
			throws EmailRepetidoException, NickameRepetidoException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		LocalDate fechaNac = LocalDate.parse(fecha, formatter);
		iControladorUsuario.crearTurista(username, nombre, apellido, correo, fechaNac, nacionalidad, contraseña,
				newFileNameUsuario);
	}

	@WebMethod
	public void crearProveedor(String username, String nombre, String apellido, String correo, String fecha,
			String descripcion, String link, String contraseña, String newFileNameUsuario)
			throws EmailRepetidoException, NickameRepetidoException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		LocalDate fechaNac = LocalDate.parse(fecha, formatter);
		iControladorUsuario.crearProveedor(username, nombre, apellido, correo, fechaNac, descripcion, link, contraseña,
				newFileNameUsuario);
	}

	@WebMethod
	public String obtenerPassword(String nick) {
		return iControladorUsuario.obtenerPassword(nick);
	}

	@WebMethod
	public String obtenerNickname(String username) {
		return iControladorUsuario.obtenerNickname(username);
	}

	@WebMethod
	public void compraTuristaPaquete(String nickname, String nombrePaq, int cantTuristas)
			throws PaqueteCompradoException {
		iControladorUsuario.compraTuristaPaquete(nickname, nombrePaq, cantTuristas);
	}

	@WebMethod
	public void modificarProveedor(String nickname, String nombre, String apellido, String email, String nacimiento,
			String descripcionProveedor, String linkProveedor) throws UsuarioNoExisteException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		LocalDate fechaNac = LocalDate.parse(nacimiento, formatter);
		iControladorUsuario.modificarProveedor(nickname, nombre, apellido, email, fechaNac, descripcionProveedor,
				linkProveedor);
	}

	@WebMethod
	public void modificarTurista(String nickname, String nombre, String apellido, String email, String nacimiento,
			String nacionalidadTurista) throws UsuarioNoExisteException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		LocalDate fechaNac = LocalDate.parse(nacimiento, formatter);
		iControladorUsuario.modificarTurista(nickname, nombre, apellido, email, fechaNac, nacionalidadTurista);

	}

	@WebMethod
	public void modificarImagen(String nickname, String filename) throws UsuarioNoExisteException {
		iControladorUsuario.modificarImagen(nickname, filename);
	}

	@WebMethod
	public void modificarContrasena(String nickname, String contraseña) throws UsuarioNoExisteException {
		iControladorUsuario.modificarContrasena(nickname, contraseña);
	}

	@WebMethod
	public boolean existeEmail(String email) {
		return iControladorUsuario.existeEmail(email);
	}

	@WebMethod
	public DataCompra[] obtenerComprasTurista(String nickname) {
		List<DataCompra> comprasList = iControladorUsuario.obtenerComprasTurista(nickname);
		return comprasList.toArray(new DataCompra[comprasList.size()]);
	}

	@WebMethod
	public DataInscripcion[] obtenerInscripcionesTurista(String nickname) {
		List<DataInscripcion> inscList = new ArrayList<>();
		try {
			inscList = iControladorUsuario.obtenerInscripcionesTurista(nickname);
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inscList.toArray(new DataInscripcion[inscList.size()]);
	}

	@WebMethod
	public void desmarcarFavorita(String nicknameUsr, String nombreActv) {
		iControladorUsuario.desmarcarFavorita(nicknameUsr, nombreActv);
	}

	@WebMethod
	public void marcarFavorita(String nicknameUsr, String nombreActv) {
		iControladorUsuario.marcarFavorita(nicknameUsr, nombreActv);
	}

	@WebMethod
	public String[] obtenerFavoritas(String nicknameUsr, String nombreActv) {
		List<String> res = new LinkedList<String>();
		try {
			DataTurista tur = iControladorUsuario.verInfoTurista(nicknameUsr);
			for (DataActividadTuristica item : tur.getactFavoritas().values()) {
				res.add(item.getNombre());
			}
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
		}
		return res.toArray(new String[res.size()]);
	}

	@WebMethod
	public void seguirUsr(String nicknameUsr, String nicknameASeguirUsr) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.existeTuristaNick(nicknameUsr)) {
			// el usuario es un turista
			// turista que va a seguir
			Turista tur = maUsuario.obtenerTurista(nicknameUsr);
			if (maUsuario.existeProveedorNick(nicknameASeguirUsr)) {
				// y quiere seguir un proveedor
				// proveedor que va a ser seguido
				Proveedor prov = maUsuario.obtenerProveedor(nicknameASeguirUsr);
				prov.setSeguidorTur(tur);
				tur.setSeguidoProv(prov);
			} else {
				// y quiere seguir un turista
				// turista que va a ser seguido
				Turista turS = maUsuario.obtenerTurista(nicknameASeguirUsr);
				turS.setSeguidorTur(tur);
				tur.setSeguidoTur(turS);
			}
		} else {
			// el usuario es un proveedor
			Proveedor prov = maUsuario.obtenerProveedor(nicknameUsr);
			if (maUsuario.existeProveedorNick(nicknameASeguirUsr)) {
				// y quiere seguir un proveedor
				Proveedor provS = maUsuario.obtenerProveedor(nicknameASeguirUsr);
				provS.setSeguidorProv(prov);
				prov.setSeguidoProv(provS);
			} else {
				// y quiere seguir un turista
				Turista turS = maUsuario.obtenerTurista(nicknameASeguirUsr);
				turS.setSeguidorProv(prov);
				prov.setSeguidoTur(turS);
			}
		}
	}

	@WebMethod
	public void dejarDeSeguirUsr(String nicknameUsr, String nicknameSeguidoUsr) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.existeTuristaNick(nicknameUsr)) {
			Turista tur = maUsuario.obtenerTurista(nicknameUsr);
			tur.dejarDeSeguirUsr(nicknameSeguidoUsr);

		} else {
			Proveedor prov = maUsuario.obtenerProveedor(nicknameUsr);
			prov.dejarDeSeguirUsr(nicknameSeguidoUsr);
		}

	}

	@WebMethod
	public int cantSeguidores(String nickname) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.existeProveedorNick(nickname)) {
			Proveedor prov = maUsuario.obtenerProveedor(nickname);
			int ret = prov.getSeguidoresProv().size() + prov.getSeguidoresTur().size();
			return ret;
		} else {
			Turista tur = maUsuario.obtenerTurista(nickname);
			int ret = tur.getSeguidoresProv().size() + tur.getSeguidoresTur().size();
			return ret;
		}
	}

	@WebMethod
	public int cantSeguidos(String nickname) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.existeProveedorNick(nickname)) {
			Proveedor prov = maUsuario.obtenerProveedor(nickname);
			int ret = prov.getSeguidosProv().size() + prov.getSeguidosTur().size();
			return ret;
		} else {
			Turista tur = maUsuario.obtenerTurista(nickname);
			int ret = tur.getSeguidosProv().size() + tur.getSeguidosTur().size();
			return ret;
		}
	}

	@WebMethod
	public boolean sigueAUsuario(String nickname, String nicknameS) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.existeProveedorNick(nicknameS)) {
			Proveedor prov = maUsuario.obtenerProveedor(nicknameS);
			// chequeo si dentro de los seguidores de nicknameS esta nickname
			return prov.getSeguidoresProv().containsKey(nickname) || prov.getSeguidoresTur().containsKey(nickname);
		} else {
			Turista tur = maUsuario.obtenerTurista(nicknameS);
			return tur.getSeguidoresProv().containsKey(nickname) || tur.getSeguidoresTur().containsKey(nickname);
		}
	}

	@WebMethod
	public DataProveedor[] getSeguidoresProv(String nickname) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		DataProveedor[] dts;
		if (maUsuario.existeProveedorNick(nickname)) {
			dts = new DataProveedor[maUsuario.obtenerProveedor(nickname).getSeguidoresProv().size()];
			Map<String, Proveedor> iterar = maUsuario.obtenerProveedor(nickname).getSeguidoresProv();
			int cont = 0;
			for (Proveedor p : iterar.values()) {
				DataProveedor data = new DataProveedor(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(),
						p.getNacimiento(), p.getDescripcion(), p.getLink(), p.getImgSrc());
				dts[cont] = data;
				cont++;
			}
		} else {
			dts = new DataProveedor[maUsuario.obtenerTurista(nickname).getSeguidoresProv().size()];
			Map<String, Proveedor> iterar = maUsuario.obtenerTurista(nickname).getSeguidoresProv();
			int cont = 0;
			for (Proveedor p : iterar.values()) {
				DataProveedor data = new DataProveedor(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(),
						p.getNacimiento(), p.getDescripcion(), p.getLink(), p.getImgSrc());
				dts[cont] = data;
				cont++;
			}
		}
		return dts;
	}

	@WebMethod
	public DataTurista[] getSeguidoresTur(String nickname) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		DataTurista[] dts;
		if (maUsuario.existeProveedorNick(nickname)) {
			dts = new DataTurista[maUsuario.obtenerProveedor(nickname).getSeguidoresTur().size()];
			Map<String, Turista> iterar = maUsuario.obtenerProveedor(nickname).getSeguidoresTur();
			int cont = 0;
			for (Turista p : iterar.values()) {
				DataTurista data = new DataTurista(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(),
						p.getNacimiento(), p.getNacionalidad(), p.getImgSrc());
				dts[cont] = data;
				cont++;
			}
		} else {
			dts = new DataTurista[maUsuario.obtenerTurista(nickname).getSeguidoresTur().size()];
			Map<String, Turista> iterar = maUsuario.obtenerTurista(nickname).getSeguidoresTur();
			int cont = 0;
			for (Turista p : iterar.values()) {
				DataTurista data = new DataTurista(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(),
						p.getNacimiento(), p.getNacionalidad(), p.getImgSrc());
				dts[cont] = data;
				cont++;
			}
		}
		return dts;
	}

	@WebMethod
	public DataProveedor[] getSeguidosProv(String nickname) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		DataProveedor[] dts;
		if (maUsuario.existeProveedorNick(nickname)) {
			dts = new DataProveedor[maUsuario.obtenerProveedor(nickname).getSeguidosProv().size()];
			Map<String, Proveedor> iterar = maUsuario.obtenerProveedor(nickname).getSeguidosProv();
			int cont = 0;
			for (Proveedor p : iterar.values()) {
				DataProveedor data = new DataProveedor(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(),
						p.getNacimiento(), p.getDescripcion(), p.getLink(), p.getImgSrc());
				dts[cont] = data;
				cont++;
			}
		} else {
			dts = new DataProveedor[maUsuario.obtenerTurista(nickname).getSeguidosProv().size()];
			Map<String, Proveedor> iterar = maUsuario.obtenerTurista(nickname).getSeguidosProv();
			int cont = 0;
			for (Proveedor p : iterar.values()) {
				DataProveedor data = new DataProveedor(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(),
						p.getNacimiento(), p.getDescripcion(), p.getLink(), p.getImgSrc());
				dts[cont] = data;
				cont++;
			}
		}
		return dts;
	}

	@WebMethod
	public DataTurista[] getSeguidosTur(String nickname) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		DataTurista[] dts;
		if (maUsuario.existeProveedorNick(nickname)) {
			dts = new DataTurista[maUsuario.obtenerProveedor(nickname).getSeguidosTur().size()];
			Map<String, Turista> iterar = maUsuario.obtenerProveedor(nickname).getSeguidosTur();
			int cont = 0;
			for (Turista p : iterar.values()) {
				DataTurista data = new DataTurista(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(),
						p.getNacimiento(), p.getNacionalidad(), p.getImgSrc());
				dts[cont] = data;
				cont++;
			}
		} else {
			dts = new DataTurista[maUsuario.obtenerTurista(nickname).getSeguidosTur().size()];
			Map<String, Turista> iterar = maUsuario.obtenerTurista(nickname).getSeguidosTur();
			int cont = 0;
			for (Turista p : iterar.values()) {
				DataTurista data = new DataTurista(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(),
						p.getNacimiento(), p.getNacionalidad(), p.getImgSrc());
				dts[cont] = data;
				cont++;
			}
		}
		return dts;
	}

	public boolean esFavorita(String nicknameUsr, String nombreAct) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		return maUsuario.obtenerTurista(nicknameUsr).getactFavoritas().containsKey(nombreAct);
	}

}
