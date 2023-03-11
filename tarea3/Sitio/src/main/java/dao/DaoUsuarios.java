package dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import servidor.DataActividadTuristica;
import servidor.EmailRepetidoException_Exception;
import servidor.NickameRepetidoException_Exception;
import servidor.PaqueteCompradoException_Exception;
import servidor.PublicadorUsuario;
import servidor.PublicadorUsuarioService;
import servidor.UsuarioNoExisteException_Exception;

public class DaoUsuarios {

	private PublicadorUsuarioService publicadorUsuarioService;
	private PublicadorUsuario publicadorUsuario;

	public DaoUsuarios() {
		publicadorUsuarioService = new PublicadorUsuarioService();
		publicadorUsuario = publicadorUsuarioService.getPublicadorUsuarioPort();
	}

	public List<servidor.DataProveedor> obtenerProveedores() {
		try {
			List<servidor.DataProveedor> proveedores = publicadorUsuario.getProveedores().getItem();
			return proveedores;
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public servidor.DataProveedor obtenerProveedor(String nickname) throws UsuarioNoExisteException_Exception {
		if (nickname == null) {
			nickname = "";
		}
		try {
			servidor.DataProveedor dtu = publicadorUsuario.verInfoProveedor(nickname);
			return dtu;
		} catch (UsuarioNoExisteException_Exception e) {
			// ignore
		}
		return null;
	}

	public List<servidor.DataTurista> obtenerTuristas() {
		try {
			List<servidor.DataTurista> turistas = publicadorUsuario.getTuristas().getItem();
			return turistas;
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public servidor.DataTurista obtenerTurista(String nickname) throws UsuarioNoExisteException_Exception {
		if (nickname == null) {
			nickname = "";
		}
		try {
			return publicadorUsuario.verInfoTurista(nickname);
		} catch (UsuarioNoExisteException_Exception e) {
			// ignore
		}
		return null;

	}

	public List<DataActividadTuristica> obternerActividadesProveedor(String username) {
		List<DataActividadTuristica> actividades = new ArrayList<>();
		try {
			servidor.DataProveedor proveedor = publicadorUsuario.verInfoProveedor(username);
			actividades = proveedor.getActividades();
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actividades;
	}
	
	public List<DataActividadTuristica> obtenerActividadesProveedorFinalizadas(String username) {
		List<DataActividadTuristica> actividades = new ArrayList<>();
		try {
			servidor.DataProveedor proveedor = publicadorUsuario.verInfoProveedor(username);
			actividades = proveedor.getActividades();
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actividades;
	}
	
	public List<DataActividadTuristica> obtenerActividadesProveedorNoFinalizadas(String username) {
		List<DataActividadTuristica> actividades = new ArrayList<>();
		try {
			servidor.DataProveedor proveedor = publicadorUsuario.verInfoProveedor(username);
			actividades = proveedor.getActividades();
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actividades;
	}

	public List<servidor.DataActividadTuristica> obternerActividadesConfirmadasProveedor(String username) {
		List<servidor.DataActividadTuristica> actividades = new ArrayList<>();
		try {
			servidor.DataProveedor proveedor = publicadorUsuario.verInfoProveedor(username);
			List<servidor.DataActividadTuristica> temp = proveedor.getActividades();
			for (servidor.DataActividadTuristica actividadTuristica : temp) {
				if (actividadTuristica.getEstado() == servidor.EstadoActividad.CONFIRMADA) {
					actividades.add(actividadTuristica);
				}
			}
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actividades;
	}

	public List<servidor.DataSalidaTuristica> obtenerSalidasProveedor(String username) {
		List<servidor.DataSalidaTuristica> salidas = new ArrayList<>();
		List<DataActividadTuristica> actividades = this.obternerActividadesProveedor(username);
		for (DataActividadTuristica dat : actividades) {
			List<servidor.DataSalidaTuristica> sals = dat.getSalidas();
			salidas.addAll(sals);
		}
		return salidas;
	}

	public List<servidor.DataPaqueteActividades> obternerPaquetesTurista(String username) {
		List<servidor.DataPaqueteActividades> paquetes = new ArrayList<>();
		try {
			servidor.DataTurista turista = obtenerTurista(username);
			List<servidor.DataCompra> compras = turista.getCompras();
			for (servidor.DataCompra compra : compras) {
				paquetes.add(compra.getPaquete());
			}
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paquetes;
	}

	public List<servidor.DataPaqueteActividades> obternerPaquetesVigentesTurista(String username) {
		List<servidor.DataPaqueteActividades> paquetes = new ArrayList<>();
		try {
			servidor.DataTurista turista = obtenerTurista(username);
			List<servidor.DataCompra> compras = turista.getCompras();
			for (servidor.DataCompra compra : compras) {
				if (compra.isValid()) {
					paquetes.add(compra.getPaquete());
				}
			}
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paquetes;
	}

	public List<servidor.DataCompra> obtenerComprasTurista(String username) {
		List<servidor.DataCompra> compras = new ArrayList<>();
		compras = publicadorUsuario.obtenerComprasTurista(username).getItem();
		return compras;
	}

	public List<servidor.DataInscripcion> obternerInscripcionesTurista(String username) {
		return publicadorUsuario.obtenerInscripcionesTurista(username).getItem();

	}

	public String obtenerPassword(String nickname) {
		return publicadorUsuario.obtenerPassword(nickname);
	}

	public String obtenerNickname(String username) {
		return publicadorUsuario.obtenerNickname(username);
	}

	public void compraTuristaPaquete(String nickname, String nombrePaq, int cantTuristas)
			throws PaqueteCompradoException_Exception {
		publicadorUsuario.compraTuristaPaquete(nickname, nombrePaq, cantTuristas);
	}

	public void crearTurista(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidadTurista, String password, String imgSrc)
			throws EmailRepetidoException_Exception, NickameRepetidoException_Exception {

		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String fechaNacString = nacimiento.format(formatters);
		publicadorUsuario.crearTurista(nickname, nombre, apellido, email, fechaNacString, nacionalidadTurista, password,
				imgSrc);
	}

	public void crearProveedor(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String descripcionProveedor, String linkProveedor, String password, String imgSrc)
			throws EmailRepetidoException_Exception, NickameRepetidoException_Exception {

		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String fechaNacString = nacimiento.format(formatters);
		publicadorUsuario.crearProveedor(nickname, nombre, apellido, email, fechaNacString, descripcionProveedor,
				linkProveedor, password, imgSrc);
	}

	public void modificarImagen(String nickname, String filename) throws UsuarioNoExisteException_Exception {
		publicadorUsuario.modificarImagen(nickname, filename);
	}

	public void modificarContrasena(String nickname, String contraseña) throws UsuarioNoExisteException_Exception {
		publicadorUsuario.modificarContrasena(nickname, contraseña);
	}

	public void modificarProveedor(String nickname, String nombre, String apellido, String email, String nacimiento,
			String descripcionProveedor, String linkProveedor) throws UsuarioNoExisteException_Exception {
		publicadorUsuario.modificarProveedor(nickname, nombre, apellido, email, nacimiento, descripcionProveedor,
				linkProveedor);
	}

	public void modificarTurista(String nickname, String nombre, String apellido, String email, String nacimiento,
			String nacionalidadTurista) throws UsuarioNoExisteException_Exception {
		publicadorUsuario.modificarTurista(nickname, nombre, apellido, email, nacimiento, nacionalidadTurista);
	}

	public boolean existeEmail(String email) {
		return publicadorUsuario.existeEmail(email);
	}

	public void seguirUsr(String nicknameUsr, String nicknameASeguirUsr) {
		publicadorUsuario.seguirUsr(nicknameUsr, nicknameASeguirUsr);
	}

	public void dejarDeSeguirUsr(String nicknameUsr, String nicknameSeguidoUsr) {
		publicadorUsuario.dejarDeSeguirUsr(nicknameUsr, nicknameSeguidoUsr);
	}

	public int cantSeguidores(String nickname) {
		return publicadorUsuario.cantSeguidores(nickname);
	}

	public int cantSeguidos(String nickname) {
		return publicadorUsuario.cantSeguidos(nickname);
	}

	public void marcarFavorita(String nikcnameUsr, String nombreActv) {
		publicadorUsuario.marcarFavorita(nikcnameUsr, nombreActv);
	}

	public void desmarcarFavorita(String nikcnameUsr, String nombreActv) {
		publicadorUsuario.desmarcarFavorita(nikcnameUsr, nombreActv);
	}

	public boolean esFavorita(String nickname, String nombreAct) {
		return publicadorUsuario.esFavorita(nickname, nombreAct);
	}

	public boolean sigueAUsuario(String nick1, String nick2) {
		return publicadorUsuario.sigueAUsuario(nick1, nick2);
	}

	public List<servidor.DataProveedor> getSeguidoresProv(String nickname) {
		List<servidor.DataProveedor> provs = new ArrayList<>();
		provs = publicadorUsuario.getSeguidoresProv(nickname).getItem();
		return provs;
	}

	public List<servidor.DataTurista> getSeguidoresTur(String nickname) {
		List<servidor.DataTurista> turs = new ArrayList<>();
		turs = publicadorUsuario.getSeguidoresTur(nickname).getItem();
		return turs;
	}

	public List<servidor.DataProveedor> getSeguidosProv(String nickname) {
		List<servidor.DataProveedor> provs = new ArrayList<>();
		provs = publicadorUsuario.getSeguidosProv(nickname).getItem();
		return provs;
	}

	public List<servidor.DataTurista> getSeguidosTur(String nickname) {
		List<servidor.DataTurista> turs = new ArrayList<>();
		turs = publicadorUsuario.getSeguidosTur(nickname).getItem();
		return turs;
	}

}
