package logica.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import excepciones.ActividadNoExisteException;
import excepciones.DepartamentoNoExisteException;
import excepciones.InscripcionRepetidaException;
import excepciones.NoExisteEntidadException;
import excepciones.NoHayCapacidadException;
import excepciones.SalidaNoExisteException;
import excepciones.SalidaRepetidaException;
import logica.clases.ActividadTuristica;
import logica.clases.Compra;
import logica.clases.Departamento;
import logica.clases.Inscripcion;
import logica.clases.SalidaTuristica;
import logica.clases.Turista;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataDepartamento;
import logica.datatypes.DataSalidaTuristica;
import logica.interfaces.IControladorSalidas;
import logica.manejadores.ManejadorActividades;
import logica.manejadores.ManejadorDepartamentos;
import logica.manejadores.ManejadorInscripciones;
import logica.manejadores.ManejadorSalidas;
import logica.manejadores.ManejadorUsuario;

public class ControladorSalidas implements IControladorSalidas {

	public DataActividadTuristica[] obtenerActividadesDepartamento(String departamento)
			throws DepartamentoNoExisteException {
		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		Departamento depto = maDepartamentos.obtenerDepartamento(departamento);
		if (depto == null) {
			throw new DepartamentoNoExisteException("No existe el departamento en el sistema");
		}
		DataActividadTuristica[] das = new DataActividadTuristica[depto.getActividades().size()];
		int cont = 0;
		for (ActividadTuristica actividad : depto.getActividades().values()) {
			DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
					actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
			DataActividadTuristica dataAct = new DataActividadTuristica(dpto, actividad.getNombre(),
					actividad.getDescripcion(), actividad.getDuracion(), actividad.getCosto(), actividad.getCiudad(),
					actividad.getFechaAlta(), actividad.getEstado(), actividad.getNombresCategorias(),
					actividad.getImgSrc());
			das[cont] = dataAct;
			cont++;
		}
		return das;
	}

	public void crearSalida(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			LocalDate fechaSalida, String horaSalida, String actividad)
			throws SalidaRepetidaException, ActividadNoExisteException {
		ManejadorSalidas maSalidas = ManejadorSalidas.getinstance();
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (maSalidas.existeSalida(nombre)) {
			throw new SalidaRepetidaException("La salida " + nombre + " ya esta registrada");
		} else {
			ActividadTuristica actividadTuristica = maActividades.obtenerActividad(actividad);
			if (actividadTuristica == null) {
				throw new ActividadNoExisteException("La actividad no existe");
			}
			SalidaTuristica salida = new SalidaTuristica(nombre, cantMaxTuristas, fechaAlta, lugarSalida, fechaSalida,
					horaSalida, actividadTuristica);
			maSalidas.addSalida(salida);
			actividadTuristica.setSalida(salida);
		}
	}

	public void crearSalida(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			LocalDate fechaSalida, String horaSalida, String actividad, String imgSrc)
			throws SalidaRepetidaException, ActividadNoExisteException {
		ManejadorSalidas maSalidas = ManejadorSalidas.getinstance();
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (maSalidas.existeSalida(nombre)) {
			throw new SalidaRepetidaException("La salida " + nombre + " ya esta registrada");
		} else {
			ActividadTuristica actividadTuristica = maActividades.obtenerActividad(actividad);
			if (actividadTuristica == null) {
				throw new ActividadNoExisteException("La actividad no existe");
			}
			SalidaTuristica salida = new SalidaTuristica(nombre, cantMaxTuristas, fechaAlta, lugarSalida, fechaSalida,
					horaSalida, actividadTuristica, imgSrc);
			maSalidas.addSalida(salida);
			actividadTuristica.setSalida(salida);
		}
	}

	public void crearSalida(String nombre, int cantMaxTuristas, String lugarSalida, LocalDate fechaSalida,
			String horaSalida, String actividad, String imgSrc)
			throws SalidaRepetidaException, ActividadNoExisteException {
		ManejadorSalidas maSalidas = ManejadorSalidas.getinstance();
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (maSalidas.existeSalida(nombre)) {
			throw new SalidaRepetidaException("La salida " + nombre + " ya esta registrada");
		} else {
			ActividadTuristica actividadTuristica = maActividades.obtenerActividad(actividad);
			if (actividadTuristica == null) {
				throw new ActividadNoExisteException("La actividad no existe");
			}
			LocalDate fechaAlta = LocalDate.now();
			SalidaTuristica salida = new SalidaTuristica(nombre, cantMaxTuristas, fechaAlta, lugarSalida, fechaSalida,
					horaSalida, actividadTuristica, imgSrc);
			maSalidas.addSalida(salida);
			actividadTuristica.setSalida(salida);
		}
	}

	public void modificarSalida(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			LocalDate fechaSalida, String horaSalida) throws SalidaNoExisteException {
		ManejadorSalidas maSalidas = ManejadorSalidas.getinstance();
		SalidaTuristica salida = maSalidas.obtenerSalida(nombre);
		if (salida == null) {
			throw new SalidaNoExisteException("La salida " + nombre + " no existe");
		} else {
			salida.setCantMaxTuristas(cantMaxTuristas);
			salida.setFechaAlta(fechaAlta);
			salida.setLugarSalida(lugarSalida);
			salida.setFechaSalida(fechaSalida);
			salida.setHoraSalida(horaSalida);
		}
	}

	public DataSalidaTuristica consultarSalida(String nombre)
			throws SalidaNoExisteException, ActividadNoExisteException {
		ManejadorSalidas maSalidas = ManejadorSalidas.getinstance();
		SalidaTuristica salida = maSalidas.obtenerSalida(nombre);
		if (salida == null) {
			throw new SalidaNoExisteException("La salida " + nombre + " no existe");
		} else {
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
			String fechaSalida = salida.getFechaSalida().format(formatters);
			DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(), salida.getCantMaxTuristas(),
					salida.getFechaAlta(), salida.getLugarSalida(), fechaSalida, salida.getHoraSalida(),
					salida.getActividad().getNombre(), salida.getLugaresDisponibles(), salida.getImgSrc());
			return dsalida;
		}
	}

	public DataSalidaTuristica[] getSalidas() throws SalidaNoExisteException {
		ManejadorSalidas maSalidas = ManejadorSalidas.getinstance();
		if (maSalidas.getSalidas() != null) {
			DataSalidaTuristica[] dss = new DataSalidaTuristica[maSalidas.getSalidas().size()];
			int cont = 0;
			for (SalidaTuristica salida : maSalidas.getSalidas().values()) {
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
				String fechaSalida = salida.getFechaSalida().format(formatters);
				DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(), salida.getCantMaxTuristas(),
						salida.getFechaAlta(), salida.getLugarSalida(), fechaSalida, salida.getHoraSalida(),
						salida.getActividad().getNombre(), salida.getLugaresDisponibles(), salida.getImgSrc());
				dss[cont] = dsalida;
				cont++;
			}
			return dss;
		} else {
			throw new SalidaNoExisteException("No hay salidas en el sistema");
		}
	}

	public List<DataSalidaTuristica> getSalidas(String actividad) throws SalidaNoExisteException {
		ManejadorSalidas maSalidas = ManejadorSalidas.getinstance();
		if (maSalidas.getSalidas() != null) {
			List<DataSalidaTuristica> salidas = new ArrayList<DataSalidaTuristica>();
			for (SalidaTuristica salida : maSalidas.getSalidas().values()) {
				// System.out.println(LocalDate.now().toString());
				// System.out.println(salida.getFechaSalida().toString());
				if (salida.getActividad().getNombre().equals(actividad)) {
					DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
					String fechaSalida = salida.getFechaSalida().format(formatters);
					DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(),
							salida.getCantMaxTuristas(), salida.getFechaAlta(), salida.getLugarSalida(), fechaSalida,
							salida.getHoraSalida(), salida.getActividad().getNombre(), salida.getLugaresDisponibles(),
							salida.getImgSrc());
					salidas.add(dsalida);
				}
			}
			return salidas;
		} else {
			throw new SalidaNoExisteException("No hay salidas en el sistema");
		}
	}

	public List<DataSalidaTuristica> getSalidasVigentes(String actividad) throws SalidaNoExisteException {
		ManejadorSalidas maSalidas = ManejadorSalidas.getinstance();
		if (maSalidas.getSalidas() != null) {
			List<DataSalidaTuristica> salidas = new ArrayList<DataSalidaTuristica>();
			for (SalidaTuristica salida : maSalidas.getSalidas().values()) {
				// System.out.println(LocalDate.now().toString());
				// System.out.println(salida.getFechaSalida().toString());
				if (salida.getFechaSalida().isAfter(LocalDate.now())
						&& salida.getActividad().getNombre().equals(actividad) && salida.getLugaresDisponibles() > 0) {
					DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
					String fechaSalida = salida.getFechaSalida().format(formatters);
					DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(),
							salida.getCantMaxTuristas(), salida.getFechaAlta(), salida.getLugarSalida(), fechaSalida,
							salida.getHoraSalida(), salida.getActividad().getNombre(), salida.getLugaresDisponibles(),
							salida.getImgSrc());
					salidas.add(dsalida);
				}
			}
			return salidas;
		} else {
			throw new SalidaNoExisteException("No hay salidas en el sistema");
		}
	}

	public void inscribirTuristaSalida(String nickname, String nombreSalida, int cantTuristas, LocalDate fecha)
			throws NoExisteEntidadException, InscripcionRepetidaException, NoHayCapacidadException {
		ManejadorSalidas maSal = ManejadorSalidas.getinstance();
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		ManejadorInscripciones maInscripciones = ManejadorInscripciones.getinstance();
		SalidaTuristica salida = maSal.obtenerSalida(nombreSalida);
		if (!maSal.existeSalida(nombreSalida) || !maUsuario.existeTuristaNick(nickname)) {
			throw new NoExisteEntidadException("Seleccione una salida y un usuario que sean validos");
		} else if (maInscripciones.existeInscripcion(nickname, nombreSalida)) {
			throw new InscripcionRepetidaException("Ya existe una inscripcion para ese turista a esa salida");
		} else if (salida.getLugaresDisponibles() < cantTuristas) {
			throw new NoHayCapacidadException("La cantidad de turistas supera la capacidad disponible para la salida");
		} else {
			Turista turista = maUsuario.obtenerTurista(nickname);
			Inscripcion inscripcion = new Inscripcion(turista, salida, fecha, cantTuristas);
			maInscripciones.addInscripcion(inscripcion);
			turista.setInscripcion(inscripcion);
			salida.decrementarLugaresDisponibles(cantTuristas);
		}
	}

	public void inscribirTuristaSalidaPaquete(String nickname, String nombreSalida, String nomPaquete, int cantTuristas,
			LocalDate fecha) throws NoExisteEntidadException, InscripcionRepetidaException, NoHayCapacidadException,
			ActividadNoExisteException {
		ManejadorSalidas maSal = ManejadorSalidas.getinstance();
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		ManejadorInscripciones maInscripciones = ManejadorInscripciones.getinstance();
		SalidaTuristica salida = maSal.obtenerSalida(nombreSalida);
		Turista turista = maUsuario.obtenerTurista(nickname);
		Compra compra = turista.obtenerCompra(nomPaquete);
		if (!maSal.existeSalida(nombreSalida) || !maUsuario.existeTuristaNick(nickname)) {
			throw new NoExisteEntidadException("Seleccione una salida, un paquete y un usuario que sean validos");
		} else if (maInscripciones.existeInscripcion(nickname, nombreSalida)) {
			throw new InscripcionRepetidaException("Ya existe una inscripcion para ese turista a esa salida");
		} else if (salida.getLugaresDisponibles() < cantTuristas) {
			throw new NoHayCapacidadException("La cantidad de turistas supera la capacidad disponible para la salida");
		} else if (compra.getDisponibilidad(salida.getActividad().getNombre()) < cantTuristas) {
			throw new NoHayCapacidadException(
					"La cantidad de turistas supera las inscripciones disponibles de la compra seleccionada");
		} else {
			compra.decrementarInscripcionesDisponibles(salida.getActividad().getNombre(), cantTuristas);
			Inscripcion inscripcion = new Inscripcion(turista, salida, fecha, cantTuristas,
					compra.getPaquete().getDescuento());
			maInscripciones.addInscripcion(inscripcion);
			turista.setInscripcion(inscripcion);
			salida.decrementarLugaresDisponibles(cantTuristas);
		}
	}
}
