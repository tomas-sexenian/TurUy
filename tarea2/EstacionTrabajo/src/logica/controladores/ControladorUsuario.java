package logica.controladores;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import excepciones.EmailRepetidoException;
import excepciones.NickameRepetidoException;
import excepciones.PaqueteCompradoException;
import excepciones.UsuarioNoExisteException;
import logica.clases.Compra;
import logica.clases.Inscripcion;
import logica.clases.PaqueteActividades;
import logica.clases.Proveedor;
import logica.clases.SalidaTuristica;
import logica.clases.Turista;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataCompra;
import logica.datatypes.DataDepartamento;
import logica.datatypes.DataInscripcion;
import logica.datatypes.DataPaqueteActividades;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataSalidaTuristica;
import logica.datatypes.DataTurista;
import logica.interfaces.IControladorUsuario;
import logica.manejadores.ManejadorCompras;
import logica.manejadores.ManejadorPaquetes;
import logica.manejadores.ManejadorUsuario;

public class ControladorUsuario implements IControladorUsuario {

	public void crearTurista(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidadTurista, String password, String imgSrc)
			throws EmailRepetidoException, NickameRepetidoException {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.existeProveedorNick(nickname) || maUsuario.existeTuristaNick(nickname)) {
			throw new NickameRepetidoException("El nickname " + nickname + " ya esta en uso");
		} else if (maUsuario.existeProveedorEmail(email) || maUsuario.existeTuristaEmail(email)) {
			throw new EmailRepetidoException("El email " + email + " ya esta en uso");
		} else {
			Turista turista = new Turista(nickname, nombre, apellido, email, nacimiento, nacionalidadTurista, password,
					imgSrc);
			maUsuario.addTurista(turista);
		}
	}

	public void crearProveedor(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String descripcionProveedor, String linkProveedor, String password, String imgSrc)
			throws EmailRepetidoException, NickameRepetidoException {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.existeProveedorNick(nickname) || maUsuario.existeTuristaNick(nickname)) {
			throw new NickameRepetidoException("El nickname " + nickname + " ya esta en uso");
		} else if (maUsuario.existeProveedorEmail(email) || maUsuario.existeTuristaEmail(email)) {
			throw new EmailRepetidoException("El email " + email + " ya esta en uso");
		} else {
			Proveedor proveedor = new Proveedor(nickname, nombre, apellido, email, nacimiento, descripcionProveedor,
					linkProveedor, password, imgSrc);
			maUsuario.addProveedor(proveedor);
		}
	}

	public DataTurista verInfoTurista(String nickname) throws UsuarioNoExisteException {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		Turista turista = maUsuario.obtenerTurista(nickname);
		if (turista != null) {

			LinkedList<DataInscripcion> dataInscripciones = new LinkedList<DataInscripcion>();
			for (Inscripcion inscripcion : turista.getInscripciones()) {
				SalidaTuristica salida = inscripcion.getSalida();
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
				String fechaSalida = salida.getFechaSalida().format(formatters);
				DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(), salida.getCantMaxTuristas(),
						salida.getFechaAlta(), salida.getLugarSalida(), fechaSalida, salida.getHoraSalida(),
						salida.getActividad().getNombre(), salida.getLugaresDisponibles(), salida.getImgSrc());
				dataInscripciones.add(
						new DataInscripcion(inscripcion.getFechaInscripcion(), inscripcion.getCantTuristas(), dsalida, inscripcion.calcularCosto()));
			}

			LinkedList<DataCompra> dataCompras = new LinkedList<DataCompra>();
			for (Compra compra : turista.getCompras()) {
				PaqueteActividades paquete = compra.getPaquete();
				DataPaqueteActividades dpaq = new DataPaqueteActividades(paquete.getNombre(), paquete.getDescripcion(),
						paquete.getValidez(), paquete.getDescuento(), paquete.getFechaAlta(), null,
						paquete.getComprado(), paquete.getImgSrc(), paquete.getCosto());
				dataCompras.add(new DataCompra(compra.getCantTuristas(), compra.getFechaCompra(),
						compra.calcularCostoTotal(), dpaq, compra.getVigencia()));
			}
			return new DataTurista(turista.getNickname(), turista.getNombre(), turista.getApellido(),
					turista.getEmail(), turista.getNacimiento(), turista.getNacionalidad(), dataInscripciones,
					dataCompras, turista.getImgSrc());
		} else
			throw new UsuarioNoExisteException("El turista " + nickname + " no existe");

	}

	public DataProveedor verInfoProveedor(String nickname) throws UsuarioNoExisteException {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		Proveedor proveedor = maUsuario.obtenerProveedor(nickname);
		if (proveedor != null)
			return new DataProveedor(proveedor.getNickname(), proveedor.getNombre(), proveedor.getApellido(),
					proveedor.getEmail(), proveedor.getNacimiento(), proveedor.getDescripcion(), proveedor.getLink(),
					obtenerActividadesProveedor(nickname), proveedor.getImgSrc());
		else
			throw new UsuarioNoExisteException("El turista " + nickname + " no existe");
	}

	public void modificarTurista(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidadTurista) throws UsuarioNoExisteException {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		Turista turista = maUsuario.obtenerTurista(nickname);
		if (turista == null) {
			throw new UsuarioNoExisteException("El turista " + nickname + " no existe");
		} else {
			turista.setNombre(nombre);
			turista.setApellido(apellido);
			turista.setNacimiento(nacimiento);
			turista.setNacionalidad(nacionalidadTurista);
		}

	}

	public void modificarProveedor(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String descripcionProveedor, String linkProveedor) throws UsuarioNoExisteException {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		Proveedor proveedor = maUsuario.obtenerProveedor(nickname);
		if (proveedor == null) {
			throw new UsuarioNoExisteException("El proveedor " + nickname + " no existe");
		} else {
			proveedor.setNombre(nombre);
			proveedor.setApellido(apellido);
			proveedor.setNacimiento(nacimiento);
			proveedor.setDescripcion(descripcionProveedor);
			proveedor.setLInk(linkProveedor);
		}

	}
	
	public void modificarImagen(String nickname, String filename) throws UsuarioNoExisteException {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		Proveedor proveedor = maUsuario.obtenerProveedor(nickname);
		if (proveedor == null) {
			Turista turista = maUsuario.obtenerTurista(nickname);
			if (turista == null) {
				throw new UsuarioNoExisteException("El usuario " + nickname + " no existe");
			} else {
				turista.setImgSrc(filename);
			}
		} else {
			proveedor.setImgSrc(filename);
		}
	}
	
	public void modificarContrasena(String nickname, String contraseña) throws UsuarioNoExisteException {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		Proveedor proveedor = maUsuario.obtenerProveedor(nickname);
		if (proveedor == null) {
			Turista turista = maUsuario.obtenerTurista(nickname);
			if (turista == null) {
				throw new UsuarioNoExisteException("El usuario " + nickname + " no existe");
			} else {
				turista.setPassword(contraseña);
			}
		} else {
			proveedor.setPassword(contraseña);
		}
	}

	public DataTurista[] getTuristas() throws UsuarioNoExisteException {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.getTuristas() == null) {
			throw new UsuarioNoExisteException("No hay turistas en el sistema");
		} else {
			int cont = 0;
			DataTurista[] dts = new DataTurista[maUsuario.getTuristas().size()];
			for (Turista t : maUsuario.getTuristas().values()) {
				DataTurista dtur = new DataTurista(t.getNickname(), t.getNombre(), t.getApellido(), t.getEmail(),
						t.getNacimiento(), t.getNacionalidad(), t.getImgSrc());
				dts[cont] = dtur;
				cont++;
			}
			return dts;
		}
	}

	public DataProveedor[] getProveedores() throws UsuarioNoExisteException {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.getProveedores() == null) {
			throw new UsuarioNoExisteException("No hay proveedores en el sistema");
		} else {
			int cont = 0;
			DataProveedor[] dps = new DataProveedor[maUsuario.getProveedores().size()];
			for (Proveedor proveedor : maUsuario.getProveedores().values()) {
				DataProveedor dproveedor = new DataProveedor(proveedor.getNickname(), proveedor.getNombre(),
						proveedor.getApellido(), proveedor.getEmail(), proveedor.getNacimiento(),
						proveedor.getDescripcion(), proveedor.getLink(), proveedor.getImgSrc());
				dps[cont] = dproveedor;
				cont++;
			}
			return dps;
		}
	}

	public DataInscripcion[] obtenerInscripcionesTurista(String nickname) throws UsuarioNoExisteException {
		ManejadorUsuario muUsuario = ManejadorUsuario.getinstance();
		if (!muUsuario.existeTuristaNick(nickname)) {
			throw new UsuarioNoExisteException("El usuario no está registrado en el sistema");
		} else {
			Turista turista = muUsuario.obtenerTurista(nickname);
			DataInscripcion[] dinsc = new DataInscripcion[turista.getInscripciones().size()];
			int cont = 0;
			for (var ins : turista.getInscripciones()) {
				SalidaTuristica salida = ins.getSalida();
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
				String fechaSalida = salida.getFechaSalida().format(formatters);
				DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(), salida.getCantMaxTuristas(),
						salida.getFechaAlta(), salida.getLugarSalida(), fechaSalida, salida.getHoraSalida(),
						salida.getActividad().getNombre(), salida.getLugaresDisponibles(), salida.getImgSrc());
				dinsc[cont] = new DataInscripcion(ins.getFechaInscripcion(), ins.getCantTuristas(), dsalida, ins.calcularCosto());
				cont++;
			}
			return dinsc;
		}
	}

	public DataActividadTuristica[] obtenerActividadesProveedor(String nickname) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (!maUsuario.existeProveedorNick(nickname)) {
			return new DataActividadTuristica[0];
		} else {
			Proveedor proveedor = maUsuario.obtenerProveedor(nickname);
			DataActividadTuristica[] dact = new DataActividadTuristica[proveedor.getActividades().size()];
			int cont = 0;
			for (var entry : proveedor.getActividades().entrySet()) {
				DataDepartamento dpto = new DataDepartamento(entry.getValue().getDepartamento().getNombre(),
						entry.getValue().getDepartamento().getDescripcion(),
						entry.getValue().getDepartamento().getLink());
//		DataSalidaTuristica(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
//							String fechaSalida, String horaSalida, String actividad, int disponibles, String imgSrc)
				Map<String, DataSalidaTuristica> salidas = new HashMap<>();
				for	(var salida: entry.getValue().getSalidas().entrySet()) {
					DataSalidaTuristica dst = new DataSalidaTuristica(salida.getValue().getNombre(),
							salida.getValue().getCantMaxTuristas(),
							salida.getValue().getFechaAlta(),
							salida.getValue().getLugarSalida(),
							salida.getValue().getFechaSalida().toString(),
							salida.getValue().getHoraSalida(),
							salida.getValue().getActividad().getNombre(),
							salida.getValue().getLugaresDisponibles(),
							salida.getValue().getImgSrc());
					salidas.put(salida.getValue().getNombre(), dst);
				}


				dact[cont] = new DataActividadTuristica(dpto, entry.getValue().getNombre(),
						entry.getValue().getDescripcion(), entry.getValue().getDuracion(), entry.getValue().getCosto(),
						entry.getValue().getCiudad(), entry.getValue().getFechaAlta(), salidas, entry.getValue().getEstado(),
						entry.getValue().getNombresCategorias(), entry.getValue().getImgSrc());
				cont++;
			}
			return dact;
		}
	}

	public void compraTuristaPaquete(String nickname, String nombrePaq, LocalDate fecha, int cantTuristas)
			throws PaqueteCompradoException {
		PaqueteActividades paquete = ManejadorPaquetes.getinstance().obtenerPaquete(nombrePaq);
		Turista turista = ManejadorUsuario.getinstance().obtenerTurista(nickname);
		if (turista.comproPaquete(nombrePaq)) {
			throw new PaqueteCompradoException("El turista ya compro este paquete");
		} else {
			Compra compra = new Compra(cantTuristas, fecha, paquete);
			turista.setCompra(compra);
			ManejadorCompras.getinstance().addCompra(compra);
			paquete.setComprado();
		}
	}

	public String obtenerLink(String nickname) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.existeProveedorNick(nickname)) {
			Proveedor proveedor = ManejadorUsuario.getinstance().obtenerProveedor(nickname);
			return proveedor.getImgSrc();
		} else {
			return "";
		}

	}

	public String obtenerPassword(String nickname) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.existeProveedorNick(nickname)) {
			Proveedor proveedor = ManejadorUsuario.getinstance().obtenerProveedor(nickname);
			return proveedor.getPassword();
		} else if (maUsuario.existeTuristaNick(nickname)) {
			Turista turista = ManejadorUsuario.getinstance().obtenerTurista(nickname);
			return turista.getPassword();
		} else {
			return "";
		}

	}

	public String obtenerNickname(String email) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		for (Proveedor proveedor : maUsuario.getProveedores().values()) {
			if (proveedor.getEmail().toLowerCase().equals(email.toLowerCase())) {
				return proveedor.getNickname();
			}
		}
		for (Turista turista : maUsuario.getTuristas().values()) {
			if (turista.getEmail().toLowerCase().equals(email.toLowerCase())) {
				return turista.getNickname();
			}
		}
		return"";
	}

	public boolean existeEmail(String email) {
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		if (maUsuario.existeProveedorEmail(email) || maUsuario.existeTuristaEmail(email)) {
			return true;
		} else {
			return false;
		}
	}

}
