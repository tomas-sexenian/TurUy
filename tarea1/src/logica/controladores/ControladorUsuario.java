package logica.controladores;

import java.time.LocalDate;
import java.util.LinkedList;

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
			String nacionalidadTurista, String password) throws EmailRepetidoException, NickameRepetidoException {
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		if (mUsuario.existeProveedorNick(nickname) || mUsuario.existeTuristaNick(nickname)) {
			throw new NickameRepetidoException("El nickname " + nickname + " ya esta en uso");
		} else if (mUsuario.existeProveedorEmail(email) || mUsuario.existeTuristaEmail(email)) {
			throw new EmailRepetidoException("El email " + email + " ya esta en uso");
		} else {
			Turista turista = new Turista(nickname, nombre, apellido, email, nacimiento, nacionalidadTurista, password);
			mUsuario.addTurista(turista);
		}
	}

	public void crearProveedor(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String descripcionProveedor, String linkProveedor, String password)
			throws EmailRepetidoException, NickameRepetidoException {
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		if (mUsuario.existeProveedorNick(nickname) || mUsuario.existeTuristaNick(nickname)) {
			throw new NickameRepetidoException("El nickname " + nickname + " ya esta en uso");
		} else if (mUsuario.existeProveedorEmail(email) || mUsuario.existeTuristaEmail(email)) {
			throw new EmailRepetidoException("El email " + email + " ya esta en uso");
		} else {
			Proveedor proveedor = new Proveedor(nickname, nombre, apellido, email, nacimiento, descripcionProveedor,
					linkProveedor, password);
			mUsuario.addProveedor(proveedor);
		}
	}

	public DataTurista verInfoTurista(String nickname) throws UsuarioNoExisteException {
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		Turista turista = mUsuario.obtenerTurista(nickname);
		if (turista != null) {

			LinkedList<DataInscripcion> dataInscripciones = new LinkedList<DataInscripcion>();
			for (Inscripcion inscripcion : turista.getInscripciones()) {
				SalidaTuristica salida = inscripcion.getSalida();
				DataSalidaTuristica datasal = new DataSalidaTuristica(salida.getNombre(), salida.getCantMaxTuristas(),
						salida.getFechaAlta(), salida.getLugarSalida(), salida.getFechaSalida(), salida.getHoraSalida(),
						salida.getActividad().getNombre(), salida.getLugaresDisponibles());
				dataInscripciones.add(
						new DataInscripcion(inscripcion.getFechaInscripcion(), inscripcion.getCantTuristas(), datasal));
			}

			LinkedList<DataCompra> dataCompras = new LinkedList<DataCompra>();
			for (Compra compra : turista.getCompras()) {
				PaqueteActividades paquete = compra.getPaquete();
				DataPaqueteActividades dpaq = new DataPaqueteActividades(paquete.getNombre(), paquete.getDescripcion(),
						paquete.getValidez(), paquete.getDescuento(), paquete.getFechaAlta(), null,
						paquete.getComprado());
				dataCompras.add(new DataCompra(compra.getCantTuristas(), compra.getFechaCompra(),
						compra.calcularCostoTotal(), dpaq, compra.getVigencia()));
			}
			return new DataTurista(turista.getNickname(), turista.getNombre(), turista.getApellido(),
					turista.getEmail(), turista.getNacimiento(), turista.getNacionalidad(), dataInscripciones,
					dataCompras);
		} else
			throw new UsuarioNoExisteException("El turista " + nickname + " no existe");

	}

	public DataProveedor verInfoProveedor(String nickname) throws UsuarioNoExisteException {
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		Proveedor proveedor = mUsuario.obtenerProveedor(nickname);
		if (proveedor != null)
			return new DataProveedor(proveedor.getNickname(), proveedor.getNombre(), proveedor.getApellido(),
					proveedor.getEmail(), proveedor.getNacimiento(), proveedor.getDescripcion(), proveedor.getLink(),
					obtenerActividadesProveedor(nickname));
		else
			throw new UsuarioNoExisteException("El turista " + nickname + " no existe");
	}

	public void modificarTurista(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidadTurista) throws UsuarioNoExisteException {
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		Turista turista = mUsuario.obtenerTurista(nickname);
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
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		Proveedor proveedor = mUsuario.obtenerProveedor(nickname);
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

	public DataTurista[] getTuristas() throws UsuarioNoExisteException {
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		if (mUsuario.getTuristas() == null) {
			throw new UsuarioNoExisteException("No hay turistas en el sistema");
		} else {
			int cont = 0;
			DataTurista[] dts = new DataTurista[mUsuario.getTuristas().size()];
			for (Turista t : mUsuario.getTuristas().values()) {
				DataTurista dtur = new DataTurista(t.getNickname(), t.getNombre(), t.getApellido(), t.getEmail(),
						t.getNacimiento(), t.getNacionalidad());
				dts[cont] = dtur;
				cont++;
			}
			return dts;
		}
	}

	public DataProveedor[] getProveedores() throws UsuarioNoExisteException {
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		if (mUsuario.getProveedores() == null) {
			throw new UsuarioNoExisteException("No hay proveedores en el sistema");
		} else {
			int cont = 0;
			DataProveedor[] dps = new DataProveedor[mUsuario.getProveedores().size()];
			for (Proveedor proveedor : mUsuario.getProveedores().values()) {
				DataProveedor dproveedor = new DataProveedor(proveedor.getNickname(), proveedor.getNombre(),
						proveedor.getApellido(), proveedor.getEmail(), proveedor.getNacimiento(),
						proveedor.getDescripcion(), proveedor.getLink());
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
				DataSalidaTuristica dataSal = new DataSalidaTuristica(salida.getNombre(), salida.getCantMaxTuristas(),
						salida.getFechaAlta(), salida.getLugarSalida(), salida.getFechaSalida(), salida.getHoraSalida(),
						salida.getActividad().getNombre(), salida.getLugaresDisponibles());
				dinsc[cont] = new DataInscripcion(ins.getFechaInscripcion(), ins.getCantTuristas(), dataSal);
				cont++;
			}
			return dinsc;
		}
	}

	public DataActividadTuristica[] obtenerActividadesProveedor(String nickname) {
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		if (!mUsuario.existeProveedorNick(nickname)) {
			return new DataActividadTuristica[0];
		} else {
			Proveedor proveedor = mUsuario.obtenerProveedor(nickname);
			DataActividadTuristica[] dact = new DataActividadTuristica[proveedor.getActividades().size()];
			int cont = 0;
			for (var entry : proveedor.getActividades().entrySet()) {
				DataDepartamento dpto = new DataDepartamento(entry.getValue().getDepartamento().getNombre(),
						entry.getValue().getDepartamento().getDescripcion(),
						entry.getValue().getDepartamento().getLink());
				dact[cont] = new DataActividadTuristica(dpto, entry.getValue().getNombre(),
						entry.getValue().getDescripcion(), entry.getValue().getDuracion(), entry.getValue().getCosto(),
						entry.getValue().getCiudad(), entry.getValue().getFechaAlta(), entry.getValue().getEstado(),
						entry.getValue().getNombresCategorias());
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
}
