package logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;
import excepciones.EntidadRepetidaException;
import excepciones.NoExisteEntidadException;
import excepciones.NoExistePaqueteException;
import excepciones.PaqueteCompradoException;
import logica.EstadoActividad;
import logica.clases.ActividadTuristica;
import logica.clases.Categoria;
import logica.clases.Departamento;
import logica.clases.PaqueteActividades;
import logica.clases.SalidaTuristica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataDepartamento;
import logica.datatypes.DataPaqueteActividades;
import logica.datatypes.DataSalidaTuristica;
import logica.interfaces.IControladorActividades;
import logica.manejadores.ManejadorActividades;
import logica.manejadores.ManejadorCategorias;
import logica.manejadores.ManejadorDepartamentos;
import logica.manejadores.ManejadorPaquetes;
import logica.manejadores.ManejadorUsuario;

public class ControladorActividades implements IControladorActividades {

	/*
	 * public void crearActividad(String departamento, String nombre, String
	 * descripcion, int duracion, double costo, String ciudad, LocalDate fechaAlta)
	 * throws ActividadRepetidaException{ ManejadorActividades ma =
	 * ManejadorActividades.getinstance(); ManejadorDepartamentos md =
	 * ManejadorDepartamentos.getinstance(); if (ma.existeActividad(nombre)) { throw
	 * new ActividadRepetidaException("La actividad " + nombre +
	 * " ya esta registrada"); } else { Departamento d =
	 * md.obtenerDepartamento(departamento); ActividadTuristica a = new
	 * ActividadTuristica(d,nombre,descripcion,duracion,costo,ciudad,fechaAlta);
	 * ma.addActividad(a); d.setActividad(a); } }
	 */

	public void crearActividad(String departamento, String descripcion, String nombre, int duracion, double costo,
			String ciudad, LocalDate fechaAlta, String proveedor, List<String> nomCategorias)
			throws ActividadRepetidaException {
		// se crea en estado agregada
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		ManejadorDepartamentos mDepartamentos = ManejadorDepartamentos.getinstance();
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		ManejadorCategorias mCategorias = ManejadorCategorias.getinstance();
		if (mActividades.existeActividad(nombre)) {
			throw new ActividadRepetidaException("La actividad " + nombre + " ya esta registrada");
		} else {
			Departamento dpto = mDepartamentos.obtenerDepartamento(departamento);
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			for (String nomCat : nomCategorias) {
				Categoria categoria = mCategorias.obtenerCategoria(nomCat);
				categorias.add(categoria);
			}
			ActividadTuristica actividad = new ActividadTuristica(dpto, nombre, descripcion, duracion, costo, ciudad,
					fechaAlta, categorias);
			mUsuario.obtenerProveedor(proveedor).setActividad(actividad);
			mActividades.addActividad(actividad);
			dpto.setActividad(actividad);
		}
	}

	public void aceptarActividad(String nombreActividad) throws ActividadNoExisteException {
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		if (!mActividades.existeActividad(nombreActividad)) {
			throw new ActividadNoExisteException("La actividad " + nombreActividad + " no existe");
		} else {
			ActividadTuristica actividad = mActividades.obtenerActividad(nombreActividad);
			actividad.confirmarActividad();
		}
	}

	public void rechazarActividad(String nombreActividad) throws ActividadNoExisteException {
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		if (!mActividades.existeActividad(nombreActividad)) {
			throw new ActividadNoExisteException("La actividad " + nombreActividad + " no existe");
		} else {
			ActividadTuristica actividad = mActividades.obtenerActividad(nombreActividad);
			actividad.rechazarActividad();
		}
	}

	public void modificarActividad(String departamento, String nombre, int duracion, double costo, String ciudad,
			LocalDate fechaAlta) throws ActividadNoExisteException {
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		ManejadorDepartamentos mDepartamentos = ManejadorDepartamentos.getinstance();
		if (!mActividades.existeActividad(nombre)) {
			throw new ActividadNoExisteException("La actividad " + nombre + " no existe");
		} else {
			ActividadTuristica actividad = mActividades.obtenerActividad(nombre);
			actividad.setDepartamento(mDepartamentos.obtenerDepartamento(departamento));
			actividad.setDuracion(duracion);
			actividad.setCosto(costo);
			actividad.setCiudad(ciudad);
			actividad.setFechaAlta(fechaAlta);
		}
	}

	public DataActividadTuristica consultarActividad(String nombre) throws ActividadNoExisteException {
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		ActividadTuristica actvividad = mActividades.obtenerActividad(nombre);
		DataDepartamento datadpto = new DataDepartamento(actvividad.getDepartamento().getNombre(),
				actvividad.getDepartamento().getDescripcion(), actvividad.getDepartamento().getLink());
		HashMap<String, DataSalidaTuristica> salidas = new HashMap<String, DataSalidaTuristica>();
		for (var entry : actvividad.getSalidas().entrySet()) {
			SalidaTuristica salida = entry.getValue();
			DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(), salida.getCantMaxTuristas(),
					salida.getFechaAlta(), salida.getLugarSalida(), salida.getFechaSalida(), salida.getHoraSalida(),
					salida.getActividad().getNombre(), salida.getLugaresDisponibles());
			salidas.put(salida.getNombre(), dsalida);
		}
		DataActividadTuristica dact = new DataActividadTuristica(datadpto, actvividad.getNombre(),
				actvividad.getDescripcion(), actvividad.getDuracion(), actvividad.getCosto(), actvividad.getCiudad(),
				actvividad.getFechaAlta(), salidas, actvividad.getEstado(), actvividad.getNombresCategorias());
		return dact;
	}

	public List<DataActividadTuristica> obtenerActividades() throws ActividadNoExisteException {
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		if (mActividades.getActividades().size() > 0) {
			ArrayList<DataActividadTuristica> das = new ArrayList<DataActividadTuristica>();
			for (ActividadTuristica actividad : mActividades.getActividades().values()) {
				DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
						actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
				DataActividadTuristica dact = new DataActividadTuristica(dpto, actividad.getNombre(),
						actividad.getDescripcion(), actividad.getDuracion(), actividad.getCosto(),
						actividad.getCiudad(), actividad.getFechaAlta(), actividad.getEstado(),
						actividad.getNombresCategorias());
				das.add(dact);
			}
			return das;
		} else {
			throw new ActividadNoExisteException("No hay actividades en el sistema");
		}
	}

	public List<DataActividadTuristica> obtenerActividadesConfirmadas() throws ActividadNoExisteException {
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		if (mActividades.getActividades().size() > 0) {
			ArrayList<DataActividadTuristica> das = new ArrayList<DataActividadTuristica>();
			for (ActividadTuristica actividad : mActividades.getActividades().values()) {
				if (actividad.getEstado().equals(EstadoActividad.CONFIRMADA)) {
					DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
							actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
					DataActividadTuristica dact = new DataActividadTuristica(dpto, actividad.getNombre(),
							actividad.getDescripcion(), actividad.getDuracion(), actividad.getCosto(),
							actividad.getCiudad(), actividad.getFechaAlta(), actividad.getEstado(),
							actividad.getNombresCategorias());
					das.add(dact);
				}
			}
			return das;
		} else {
			throw new ActividadNoExisteException("No hay actividades en el sistema");
		}
	}

	@Override
	public ArrayList<DataActividadTuristica> obtenerActividades(String departamento) throws ActividadNoExisteException {
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		if (mActividades.getActividades().size() > 0) {
			ArrayList<DataActividadTuristica> das = new ArrayList<DataActividadTuristica>();
			for (ActividadTuristica actividad : mActividades.getActividades().values()) {
				if (actividad.getDepartamento().getNombre().equals(departamento)) {
					DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
							actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
					DataActividadTuristica dact = new DataActividadTuristica(dpto, actividad.getNombre(),
							actividad.getDescripcion(), actividad.getDuracion(), actividad.getCosto(),
							actividad.getCiudad(), actividad.getFechaAlta(), actividad.getEstado(),
							actividad.getNombresCategorias());
					das.add(dact);
				}
			}
			return das;
		} else {
			throw new ActividadNoExisteException("No hay actividades en el sistema");
		}
	}

	@Override
	public ArrayList<DataActividadTuristica> obtenerActividadesConfirmadas(String departamento)
			throws ActividadNoExisteException {
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		if (mActividades.getActividades().size() > 0) {
			ArrayList<DataActividadTuristica> das = new ArrayList<DataActividadTuristica>();
			for (ActividadTuristica actividad : mActividades.getActividades().values()) {
				if (actividad.getDepartamento().getNombre().equals(departamento)
						&& actividad.getEstado().equals(EstadoActividad.CONFIRMADA)) {
					DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
							actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
					DataActividadTuristica dact = new DataActividadTuristica(dpto, actividad.getNombre(),
							actividad.getDescripcion(), actividad.getDuracion(), actividad.getCosto(),
							actividad.getCiudad(), actividad.getFechaAlta(), actividad.getEstado(),
							actividad.getNombresCategorias());
					das.add(dact);
				}
			}
			return das;
		} else {
			throw new ActividadNoExisteException("No hay actividades en el sistema");
		}
	}

	public List<String> obtenerCategorias() {
		ManejadorCategorias mCategorias = ManejadorCategorias.getinstance();
		Map<String, Categoria> categorias = mCategorias.getCategorias();
		ArrayList<String> nombres = new ArrayList<String>();
		for (Categoria cat : categorias.values()) {
			nombres.add(cat.getNombre());
		}
		return nombres;
	}

	public void agregarCategoria(String nombre) throws EntidadRepetidaException {
		Categoria categoria = new Categoria(nombre);
		ManejadorCategorias mCategorias = ManejadorCategorias.getinstance();
		if (!mCategorias.existeCategoria(nombre)) {
			mCategorias.addCategoria(categoria);
		} else {
			throw new EntidadRepetidaException("Ya existe la categoria en el sistema");
		}
	}

	/*
	 * public void crearPaquete(String nombre, String descripcion, int validez,
	 * double descuento, String[] actividades) throws EntidadRepetidaException{
	 * ManejadorPaquetes mp = ManejadorPaquetes.getinstance(); ManejadorActividades
	 * ma = ManejadorActividades.getinstance(); if (!mp.existePaquete(nombre)) {
	 * PaqueteActividades p = new
	 * PaqueteActividades(nombre,descripcion,validez,descuento); for(int i = 0; i <
	 * actividades.length;i++) p.setActividad(ma.obtenerActividad(actividades[i]));
	 * mp.addPaquete(p); } else { throw new
	 * EntidadRepetidaException("Ya existe un paquete de nombre " + nombre); } }
	 */

	public void crearPaquete(String nombre, String descripcion, int validez, double descuento, LocalDate fecha)
			throws EntidadRepetidaException {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		if (!mPaquetes.existePaquete(nombre)) {
			mPaquetes.addPaquete(new PaqueteActividades(nombre, descripcion, validez, descuento, fecha));
		} else {
			throw new EntidadRepetidaException("Ya existe un paquete de nombre " + nombre);
		}
	}

	public void modificarPaquete(String nombre, String descripcion, int validez, double descuento)
			throws NoExistePaqueteException {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		if (!mPaquetes.existePaquete(nombre)) {
			throw new NoExistePaqueteException("No existe un paquete de nombre " + nombre);
		} else {
			PaqueteActividades paquete = mPaquetes.obtenerPaquete(nombre);
			paquete.setDescripcion(descripcion);
			paquete.setValidez(validez);
			paquete.setDescuento(descuento);
		}
	}

	public DataPaqueteActividades consultarPaquete(String nombre) throws NoExisteEntidadException {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		if (!mPaquetes.existePaquete(nombre)) {
			throw new NoExisteEntidadException("No existe un paquete de nombre " + nombre);
		} else {
			PaqueteActividades paquete = mPaquetes.obtenerPaquete(nombre);
			LinkedList<DataActividadTuristica> das = new LinkedList<DataActividadTuristica>();
			for (ActividadTuristica actividad : paquete.getActividades().values()) {
				DataDepartamento dataDpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
						actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
				DataActividadTuristica dataAct = new DataActividadTuristica(dataDpto, actividad.getNombre(),
						actividad.getDescripcion(), actividad.getDuracion(), actividad.getCosto(),
						actividad.getCiudad(), actividad.getFechaAlta(), actividad.getEstado(),
						actividad.getNombresCategorias());
				das.add(dataAct);
			}
			DataPaqueteActividades dataPaquete = new DataPaqueteActividades(paquete.getNombre(),
					paquete.getDescripcion(), paquete.getValidez(), paquete.getDescuento(), paquete.getFechaAlta(), das,
					paquete.getComprado());
			return dataPaquete;
		}
	}

	public List<DataPaqueteActividades> obtenerPaquetes() throws NoExisteEntidadException {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		if (mPaquetes.getPaquetes().isEmpty()) {
			throw new NoExisteEntidadException("No hay paquetes en el sistema");
		} else {
			ArrayList<DataPaqueteActividades> dps = new ArrayList<DataPaqueteActividades>();
			for (PaqueteActividades p : mPaquetes.getPaquetes().values()) {
				dps.add(consultarPaquete(p.getNombre()));
			}
			return dps;
		}
	}

	public List<DataPaqueteActividades> obtenerPaquetes(String actividad) throws NoExisteEntidadException {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		if (mPaquetes.getPaquetes().isEmpty()) {
			throw new NoExisteEntidadException("No hay paquetes en el sistema");
		} else {
			ArrayList<DataPaqueteActividades> dps = new ArrayList<DataPaqueteActividades>();
			for (PaqueteActividades p : mPaquetes.getPaquetes().values()) {
				if (p.getActividades().containsKey(actividad)) {
					dps.add(consultarPaquete(p.getNombre()));
				}
			}
			return dps;
		}
	}

	public void agregarActividadPaquete(String paquete, String actividad) throws NoExistePaqueteException,
			ActividadNoExisteException, ActividadRepetidaException, PaqueteCompradoException {
		ManejadorPaquetes mPaquetes = ManejadorPaquetes.getinstance();
		ManejadorActividades mActividades = ManejadorActividades.getinstance();
		if (!mPaquetes.existePaquete(paquete)) {
			throw new NoExistePaqueteException("No existe el paquete");
		} else if (!mActividades.existeActividad(actividad)) {
			throw new ActividadNoExisteException("No existe la actividad");
		} else {
			PaqueteActividades paqueteActividades = mPaquetes.obtenerPaquete(paquete);
			Map<String, ActividadTuristica> acts = paqueteActividades.getActividades();
			if (paqueteActividades.getComprado()) {
				throw new PaqueteCompradoException("El paquete ya fue comprado");
			} else if (acts.containsKey(actividad)) { // existe la actvidad en ese paquete
				throw new ActividadRepetidaException("Esa actividad ya fue agregada al paquete");
			} else {
				paqueteActividades.setActividad(mActividades.obtenerActividad(actividad));
			}
		}
	}

}
