package logica.controladores;

import java.time.LocalDate;
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
		ManejadorDepartamentos mDepartamentos = ManejadorDepartamentos.getinstance();
		Departamento depto = mDepartamentos.obtenerDepartamento(departamento);
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
					actividad.getFechaAlta(), actividad.getEstado(), actividad.getNombresCategorias());
			das[cont] = dataAct;
			cont++;
		}
		return das;
	}

	public void crearSalida(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			LocalDate fechaSalida, int horaSalida, String actividad)
			throws SalidaRepetidaException, ActividadNoExisteException {
		ManejadorSalidas mSalidas = ManejadorSalidas.getinstance();
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		if (mSalidas.obtenerSalida(nombre) != null) {
			throw new SalidaRepetidaException("La salida " + nombre + " ya esta registrada");
		} else {
			ActividadTuristica actividadTuristica = mActividades.obtenerActividad(actividad);
			if (actividadTuristica == null) {
				throw new ActividadNoExisteException("La actividad no existe");
			}
			SalidaTuristica salida = new SalidaTuristica(nombre, cantMaxTuristas, fechaAlta, lugarSalida, fechaSalida,
					horaSalida, actividadTuristica);
			mSalidas.addSalida(salida);
			actividadTuristica.setSalida(salida);
		}
	}

	public void modificarSalida(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			LocalDate fechaSalida, int horaSalida) throws SalidaNoExisteException {
		ManejadorSalidas mSalida = ManejadorSalidas.getinstance();
		SalidaTuristica salida = mSalida.obtenerSalida(nombre);
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
		ManejadorSalidas mSalidas = ManejadorSalidas.getinstance();
		SalidaTuristica salida = mSalidas.obtenerSalida(nombre);
		if (salida == null) {
			throw new SalidaNoExisteException("La salida " + nombre + " no existe");
		} else {
			DataSalidaTuristica dataSalida = new DataSalidaTuristica(salida.getNombre(), salida.getCantMaxTuristas(),
					salida.getFechaAlta(), salida.getLugarSalida(), salida.getFechaSalida(), salida.getHoraSalida(),
					salida.getActividad().getNombre(), salida.getLugaresDisponibles());
			return dataSalida;
		}
	}

	public DataSalidaTuristica[] getSalidas() throws SalidaNoExisteException {
		ManejadorSalidas mSalidas = ManejadorSalidas.getinstance();
		if (mSalidas.getSalidas() != null) {
			DataSalidaTuristica[] dss = new DataSalidaTuristica[mSalidas.getSalidas().size()];
			int cont = 0;
			for (SalidaTuristica salida : mSalidas.getSalidas().values()) {
				DataSalidaTuristica dataSalidaTuristica = new DataSalidaTuristica(salida.getNombre(),
						salida.getCantMaxTuristas(), salida.getFechaAlta(), salida.getLugarSalida(),
						salida.getFechaSalida(), salida.getHoraSalida(), salida.getActividad().getNombre(),
						salida.getLugaresDisponibles());
				dss[cont] = dataSalidaTuristica;
				cont++;
			}
			return dss;
		} else {
			throw new SalidaNoExisteException("No hay salidas en el sistema");
		}
	}

	public List<DataSalidaTuristica> getSalidasVigentes(String actividad) throws SalidaNoExisteException {
		ManejadorSalidas mSalidas = ManejadorSalidas.getinstance();
		if (mSalidas.getSalidas() != null) {
			List<DataSalidaTuristica> salidas = new ArrayList<DataSalidaTuristica>();
			for (SalidaTuristica salida : mSalidas.getSalidas().values()) {
				// System.out.println(LocalDate.now().toString());
				// System.out.println(salida.getFechaSalida().toString());
				if (salida.getFechaSalida().isAfter(LocalDate.now()) && salida.getActividad().getNombre() == actividad
						&& salida.getLugaresDisponibles() > 0) {
					DataSalidaTuristica dataSalida = new DataSalidaTuristica(salida.getNombre(),
							salida.getCantMaxTuristas(), salida.getFechaAlta(), salida.getLugarSalida(),
							salida.getFechaSalida(), salida.getHoraSalida(), salida.getActividad().getNombre(),
							salida.getLugaresDisponibles());
					salidas.add(dataSalida);
				}
			}
			return salidas;
		} else {
			throw new SalidaNoExisteException("No hay salidas en el sistema");
		}
	}

	public void inscribirTuristaSalida(String nickname, String nombreSalida, int cantTuristas, LocalDate fecha)
			throws NoExisteEntidadException, InscripcionRepetidaException, NoHayCapacidadException {
		ManejadorSalidas mSal = ManejadorSalidas.getinstance();
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		ManejadorInscripciones mInscripciones = ManejadorInscripciones.getinstance();
		SalidaTuristica salida = mSal.obtenerSalida(nombreSalida);
		if (!mSal.existeSalida(nombreSalida) || !mUsuario.existeTuristaNick(nickname)) {
			throw new NoExisteEntidadException("Seleccione una salida y un usuario que sean validos");
		} else if (mInscripciones.existeInscripcion(nickname, nombreSalida)) {
			throw new InscripcionRepetidaException("Ya existe una inscripcion para ese turista a esa salida");
		} else if (salida.getLugaresDisponibles() < cantTuristas) {
			throw new NoHayCapacidadException("La cantidad de turistas supera la capacidad disponible para la salida");
		} else {
			Turista turista = mUsuario.obtenerTurista(nickname);
			Inscripcion inscripcion = new Inscripcion(turista, salida, fecha, cantTuristas);
			mInscripciones.addInscripcion(inscripcion);
			turista.setInscripcion(inscripcion);
			salida.decrementarLugaresDisponibles(cantTuristas);
		}
	}

	public void inscribirTuristaSalidaPaquete(String nickname, String nombreSalida, String nomPaquete, int cantTuristas,
			LocalDate fecha) throws NoExisteEntidadException, InscripcionRepetidaException, NoHayCapacidadException,
			ActividadNoExisteException {
		ManejadorSalidas mSal = ManejadorSalidas.getinstance();
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		ManejadorInscripciones mInscripciones = ManejadorInscripciones.getinstance();
		SalidaTuristica salida = mSal.obtenerSalida(nombreSalida);
		Turista turista = mUsuario.obtenerTurista(nickname);
		Compra compra = turista.obtenerCompra(nomPaquete);
		if (!mSal.existeSalida(nombreSalida) || !mUsuario.existeTuristaNick(nickname)) {
			throw new NoExisteEntidadException("Seleccione una salida, un paquete y un usuario que sean validos");
		} else if (mInscripciones.existeInscripcion(nickname, nombreSalida)) {
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
			mInscripciones.addInscripcion(inscripcion);
			turista.setInscripcion(inscripcion);
			salida.decrementarLugaresDisponibles(cantTuristas);
		}
	}
}
