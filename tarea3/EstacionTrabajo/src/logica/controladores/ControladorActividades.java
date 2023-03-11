package logica.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;
import excepciones.EntidadRepetidaException;
import excepciones.NoExisteEntidadException;
import excepciones.NoExistePaqueteException;
import excepciones.PaqueteCompradoException;
import logica.EntityManagerSingleton;
import logica.EstadoActividad;
import logica.clases.ActividadTuristica;
import logica.clases.Categoria;
import logica.clases.Departamento;
import logica.clases.Inscripcion;
import logica.clases.PaqueteActividades;
import logica.clases.Proveedor;
import logica.clases.SalidaTuristica;
import logica.clases.Turista;
import logica.clasesPersistencia.ActividadTuristicaP;
import logica.clasesPersistencia.InscripcionP;
import logica.clasesPersistencia.ProveedorP;
import logica.clasesPersistencia.SalidaTuristicaP;
import logica.clasesPersistencia.TuristaP;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataActividadTuristicaP;
import logica.datatypes.DataDepartamento;
import logica.datatypes.DataInscripcionP;
import logica.datatypes.DataPaqueteActividades;
import logica.datatypes.DataProveedorP;
import logica.datatypes.DataSalidaTuristica;
import logica.datatypes.DataSalidaTuristicaP;
import logica.datatypes.DataTuristaP;
import logica.interfaces.IControladorActividades;
import logica.manejadores.ManejadorActividades;
import logica.manejadores.ManejadorCategorias;
import logica.manejadores.ManejadorDepartamentos;
import logica.manejadores.ManejadorInscripciones;
import logica.manejadores.ManejadorPaquetes;
import logica.manejadores.ManejadorUsuario;
import jakarta.persistence.*;

public class ControladorActividades implements IControladorActividades {

	public void crearActividad(String departamento, String descripcion, String nombre, int duracion, double costo,
			String ciudad, String proveedor, List<String> nomaCategorias) throws ActividadRepetidaException {
		// se crea en estado agregada
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		ManejadorCategorias maCategorias = ManejadorCategorias.getinstance();
		if (maActividades.existeActividad(nombre)) {
			throw new ActividadRepetidaException("La actividad " + nombre + " ya esta registrada");
		} else {
			Departamento dpto = maDepartamentos.obtenerDepartamento(departamento);
			List<Categoria> categorias = new ArrayList<Categoria>();
			for (String nomCat : nomaCategorias) {
				Categoria categoria = maCategorias.obtenerCategoria(nomCat);
				categorias.add(categoria);
			}
			ActividadTuristica actividad = new ActividadTuristica(dpto, nombre, proveedor, descripcion, duracion, costo,
					ciudad, LocalDate.now(), categorias, null, null, 0);

			maUsuario.obtenerProveedor(proveedor).setActividad(actividad);
			maActividades.addActividad(actividad);
			dpto.setActividad(actividad);
		}
	}

	public void crearActividad(String departamento, String descripcion, String nombre, int duracion, double costo,
			String ciudad, String proveedor, List<String> nomaCategorias, String imgSrc, String video,
			int cantFavoritos) throws ActividadRepetidaException {
		// se crea en estado agregada
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		ManejadorCategorias maCategorias = ManejadorCategorias.getinstance();
		if (maActividades.existeActividad(nombre)) {
			throw new ActividadRepetidaException("La actividad " + nombre + " ya esta registrada");
		} else {
			Departamento dpto = maDepartamentos.obtenerDepartamento(departamento);
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			for (String nomCat : nomaCategorias) {
				Categoria categoria = maCategorias.obtenerCategoria(nomCat);
				categorias.add(categoria);
			}
			ActividadTuristica actividad = new ActividadTuristica(dpto, nombre, proveedor, descripcion, duracion, costo,
					ciudad, LocalDate.now(), categorias, imgSrc, video, cantFavoritos);
			maUsuario.obtenerProveedor(proveedor).setActividad(actividad);
			maActividades.addActividad(actividad);
			dpto.setActividad(actividad);
		}
	}

	public void crearActividad(String departamento, String descripcion, String nombre, int duracion, double costo,
			String ciudad, LocalDate fechaAlta, String proveedor, List<String> nomaCategorias, String imgSrc,
			String video, int cantFavoritos) throws ActividadRepetidaException {
		// se crea en estado agregada
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		ManejadorCategorias maCategorias = ManejadorCategorias.getinstance();
		if (maActividades.existeActividad(nombre)) {
			throw new ActividadRepetidaException("La actividad " + nombre + " ya esta registrada");
		} else {
			Departamento dpto = maDepartamentos.obtenerDepartamento(departamento);
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			for (String nomCat : nomaCategorias) {
				Categoria categoria = maCategorias.obtenerCategoria(nomCat);
				categorias.add(categoria);
			}
			ActividadTuristica actividad = new ActividadTuristica(dpto, nombre, proveedor, descripcion, duracion, costo,
					ciudad, fechaAlta, categorias, imgSrc, video, cantFavoritos);
			maUsuario.obtenerProveedor(proveedor).setActividad(actividad);
			maActividades.addActividad(actividad);
			dpto.setActividad(actividad);
		}
	}

	public void crearActividad(String departamento, String descripcion, String nombre, int duracion, double costo,
			String ciudad, LocalDate fechaAlta, String proveedor, List<String> nomaCategorias)
			throws ActividadRepetidaException {
		// se crea en estado agregada
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		ManejadorCategorias maCategorias = ManejadorCategorias.getinstance();
		if (maActividades.existeActividad(nombre)) {
			throw new ActividadRepetidaException("La actividad " + nombre + " ya esta registrada");
		} else {
			Departamento dpto = maDepartamentos.obtenerDepartamento(departamento);
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			for (String nomCat : nomaCategorias) {
				Categoria categoria = maCategorias.obtenerCategoria(nomCat);
				categorias.add(categoria);
			}
			ActividadTuristica actividad = new ActividadTuristica(dpto, nombre, proveedor, descripcion, duracion, costo,
					ciudad, fechaAlta, categorias, null, null, 0);
			maUsuario.obtenerProveedor(proveedor).setActividad(actividad);
			maActividades.addActividad(actividad);
			dpto.setActividad(actividad);
		}
	}

	public void aceptarActividad(String nombreActividad) throws ActividadNoExisteException {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (!maActividades.existeActividad(nombreActividad)) {
			throw new ActividadNoExisteException("La actividad " + nombreActividad + " no existe");
		} else {
			ActividadTuristica actividad = maActividades.obtenerActividad(nombreActividad);
			actividad.confirmarActividad();
		}
	}

	public void rechazarActividad(String nombreActividad) throws ActividadNoExisteException {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (!maActividades.existeActividad(nombreActividad)) {
			throw new ActividadNoExisteException("La actividad " + nombreActividad + " no existe");
		} else {
			ActividadTuristica actividad = maActividades.obtenerActividad(nombreActividad);
			actividad.rechazarActividad();
		}
	}

	public void modificarActividad(String departamento, String nombre, int duracion, double costo, String ciudad,
			LocalDate fechaAlta) throws ActividadNoExisteException {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		if (!maActividades.existeActividad(nombre)) {
			throw new ActividadNoExisteException("La actividad " + nombre + " no existe");
		} else {
			ActividadTuristica actividad = maActividades.obtenerActividad(nombre);
			actividad.setDepartamento(maDepartamentos.obtenerDepartamento(departamento));
			actividad.setDuracion(duracion);
			actividad.setCosto(costo);
			actividad.setCiudad(ciudad);
			actividad.setFechaAlta(fechaAlta);
		}
	}

	public DataActividadTuristica consultarActividad(String nombre) throws ActividadNoExisteException {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		ActividadTuristica actividad = maActividades.obtenerActividad(nombre);
		if (actividad == null) {
			throw new ActividadNoExisteException("No existe la actividad");
		}
		DataDepartamento datadpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
				actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
		List<DataSalidaTuristica> salidas = new ArrayList<>();
		List<DataPaqueteActividades> paquetes = new ArrayList<DataPaqueteActividades>();
		for (PaqueteActividades paq : actividad.getPaquetes()) {
			try {
				paquetes.add(consultarPaquete(paq.getNombre()));
			} catch (NoExisteEntidadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (var entry : actividad.getSalidas().entrySet()) {
			SalidaTuristica salida = entry.getValue();

			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
			String fechaSalida = salida.getFechaSalida().format(formatters);
			DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(), salida.getCantMaxTuristas(),
					salida.getFechaAlta(), salida.getLugarSalida(), fechaSalida, salida.getHoraSalida(),
					salida.getActividad().getNombre(), salida.getLugaresDisponibles(), salida.getImgSrc());
			salidas.add(dsalida);
		}

		DataActividadTuristica dact = new DataActividadTuristica(datadpto, actividad.getNombre(),
				actividad.getNickProveedor(), actividad.getDescripcion(), actividad.getDuracion(), actividad.getCosto(),
				actividad.getCiudad(), actividad.getFechaAlta(), salidas, actividad.getEstado(),
				actividad.getNombresCategorias(), actividad.getImgSrc(), actividad.getVideo(),
				actividad.getCantFavoritos(), paquetes);
		return dact;
	}

	public List<DataActividadTuristica> obtenerActividades() throws ActividadNoExisteException {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (maActividades.getActividades().size() > 0) {
			List<DataActividadTuristica> das = new ArrayList<DataActividadTuristica>();
			for (ActividadTuristica actividad : maActividades.getActividades().values()) {
				// pquetes
				List<DataPaqueteActividades> paquetes = new ArrayList<DataPaqueteActividades>();
				for (PaqueteActividades paq : actividad.getPaquetes()) {
					try {
						paquetes.add(consultarPaquete(paq.getNombre()));
					} catch (NoExisteEntidadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// salidas
				List<DataSalidaTuristica> salidas = new ArrayList<>();
				for (var entry : actividad.getSalidas().entrySet()) {
					SalidaTuristica salida = entry.getValue();

					DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
					String fechaSalida = salida.getFechaSalida().format(formatters);
					DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(),
							salida.getCantMaxTuristas(), salida.getFechaAlta(), salida.getLugarSalida(), fechaSalida,
							salida.getHoraSalida(), salida.getActividad().getNombre(), salida.getLugaresDisponibles(),
							salida.getImgSrc());
					salidas.add(dsalida);
				}
				DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
						actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
				DataActividadTuristica dact = new DataActividadTuristica(dpto, actividad.getNombre(),
						actividad.getNickProveedor(), actividad.getDescripcion(), actividad.getDuracion(),
						actividad.getCosto(), actividad.getCiudad(), actividad.getFechaAlta(), salidas,
						actividad.getEstado(), actividad.getNombresCategorias(), actividad.getImgSrc(),
						actividad.getVideo(), actividad.getCantFavoritos(), paquetes);
				das.add(dact);
			}
			return das;
		} else {
			throw new ActividadNoExisteException("No hay actividades en el sistema");
		}
	}

	public ArrayList<DataActividadTuristica> obtenerActividadesConfirmadas() throws ActividadNoExisteException {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (maActividades.getActividades().size() > 0) {
			ArrayList<DataActividadTuristica> das = new ArrayList<DataActividadTuristica>();
			for (ActividadTuristica actividad : maActividades.getActividades().values()) {
				if (actividad.getEstado().equals(EstadoActividad.CONFIRMADA)) {
					// pquetes
					List<DataPaqueteActividades> paquetes = new ArrayList<DataPaqueteActividades>();
					for (PaqueteActividades paq : actividad.getPaquetes()) {
						try {
							paquetes.add(consultarPaquete(paq.getNombre()));
						} catch (NoExisteEntidadException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// salidas
					List<DataSalidaTuristica> salidas = new ArrayList<>();
					for (var entry : actividad.getSalidas().entrySet()) {
						SalidaTuristica salida = entry.getValue();

						DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
						String fechaSalida = salida.getFechaSalida().format(formatters);
						DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(),
								salida.getCantMaxTuristas(), salida.getFechaAlta(), salida.getLugarSalida(),
								fechaSalida, salida.getHoraSalida(), salida.getActividad().getNombre(),
								salida.getLugaresDisponibles(), salida.getImgSrc());
						salidas.add(dsalida);
					}
					DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
							actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
					DataActividadTuristica dact = new DataActividadTuristica(dpto, actividad.getNombre(),
							actividad.getNickProveedor(), actividad.getDescripcion(), actividad.getDuracion(),
							actividad.getCosto(), actividad.getCiudad(), actividad.getFechaAlta(), salidas,
							actividad.getEstado(), actividad.getNombresCategorias(), actividad.getImgSrc(),
							actividad.getVideo(), actividad.getCantFavoritos(), paquetes);
					das.add(dact);
				}
			}
			return das;
		} else {
			throw new ActividadNoExisteException("No hay actividades en el sistema");
		}
	}

	@Override
	public List<DataActividadTuristica> obtenerActividades(String departamento) throws ActividadNoExisteException {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (maActividades.getActividades().size() > 0) {
			List<DataActividadTuristica> das = new ArrayList<DataActividadTuristica>();
			for (ActividadTuristica actividad : maActividades.getActividades().values()) {
				if (actividad.getDepartamento().getNombre().equals(departamento)) {
					// pquetes
					List<DataPaqueteActividades> paquetes = new ArrayList<DataPaqueteActividades>();
					for (PaqueteActividades paq : actividad.getPaquetes()) {
						try {
							paquetes.add(consultarPaquete(paq.getNombre()));
						} catch (NoExisteEntidadException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// salidas
					List<DataSalidaTuristica> salidas = new ArrayList<>();
					for (var entry : actividad.getSalidas().entrySet()) {
						SalidaTuristica salida = entry.getValue();

						DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
						String fechaSalida = salida.getFechaSalida().format(formatters);
						DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(),
								salida.getCantMaxTuristas(), salida.getFechaAlta(), salida.getLugarSalida(),
								fechaSalida, salida.getHoraSalida(), salida.getActividad().getNombre(),
								salida.getLugaresDisponibles(), salida.getImgSrc());
						salidas.add(dsalida);
					}
					DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
							actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
					DataActividadTuristica dact = new DataActividadTuristica(dpto, actividad.getNombre(),
							actividad.getNickProveedor(), actividad.getDescripcion(), actividad.getDuracion(),
							actividad.getCosto(), actividad.getCiudad(), actividad.getFechaAlta(), salidas,
							actividad.getEstado(), actividad.getNombresCategorias(), actividad.getImgSrc(),
							actividad.getVideo(), actividad.getCantFavoritos(), paquetes);
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
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (maActividades.getActividades().size() > 0) {
			ArrayList<DataActividadTuristica> das = new ArrayList<DataActividadTuristica>();
			for (ActividadTuristica actividad : maActividades.getActividades().values()) {
				if (actividad.getDepartamento().getNombre().equals(departamento)
						&& actividad.getEstado().equals(EstadoActividad.CONFIRMADA)) {
					// pquetes
					List<DataPaqueteActividades> paquetes = new ArrayList<DataPaqueteActividades>();
					for (PaqueteActividades paq : actividad.getPaquetes()) {
						try {
							paquetes.add(consultarPaquete(paq.getNombre()));
						} catch (NoExisteEntidadException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// salidas
					List<DataSalidaTuristica> salidas = new ArrayList<>();
					for (var entry : actividad.getSalidas().entrySet()) {
						SalidaTuristica salida = entry.getValue();

						DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
						String fechaSalida = salida.getFechaSalida().format(formatters);
						DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(),
								salida.getCantMaxTuristas(), salida.getFechaAlta(), salida.getLugarSalida(),
								fechaSalida, salida.getHoraSalida(), salida.getActividad().getNombre(),
								salida.getLugaresDisponibles(), salida.getImgSrc());
						salidas.add(dsalida);
					}
					DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
							actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
					DataActividadTuristica dact = new DataActividadTuristica(dpto, actividad.getNombre(),
							actividad.getNickProveedor(), actividad.getDescripcion(), actividad.getDuracion(),
							actividad.getCosto(), actividad.getCiudad(), actividad.getFechaAlta(), salidas,
							actividad.getEstado(), actividad.getNombresCategorias(), actividad.getImgSrc(),
							actividad.getVideo(), actividad.getCantFavoritos(), paquetes);
					das.add(dact);
				}
			}
			return das;
		} else {
			throw new ActividadNoExisteException("No hay actividades en el sistema");
		}
	}

	@Override
	public ArrayList<DataActividadTuristica> obtenerActividadesConfirmadas(String departamento, String categoria)
			throws ActividadNoExisteException {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (maActividades.getActividades().size() > 0) {
			ArrayList<DataActividadTuristica> das = new ArrayList<DataActividadTuristica>();
			for (ActividadTuristica actividad : maActividades.getActividades().values()) {

				if ((departamento.equals("") || actividad.getDepartamento().getNombre().equals(departamento))
						&& (categoria.equals("") || actividad.perteneceCategoria(categoria))
						&& actividad.getEstado().equals(EstadoActividad.CONFIRMADA)) {
					// pquetes
					List<DataPaqueteActividades> paquetes = new ArrayList<DataPaqueteActividades>();
					for (PaqueteActividades paq : actividad.getPaquetes()) {
						try {
							paquetes.add(consultarPaquete(paq.getNombre()));
						} catch (NoExisteEntidadException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// salidas
					List<DataSalidaTuristica> salidas = new ArrayList<>();
					for (var entry : actividad.getSalidas().entrySet()) {
						SalidaTuristica salida = entry.getValue();

						DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
						String fechaSalida = salida.getFechaSalida().format(formatters);
						DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(),
								salida.getCantMaxTuristas(), salida.getFechaAlta(), salida.getLugarSalida(),
								fechaSalida, salida.getHoraSalida(), salida.getActividad().getNombre(),
								salida.getLugaresDisponibles(), salida.getImgSrc());
						salidas.add(dsalida);
					}
					DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
							actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
					DataActividadTuristica dact = new DataActividadTuristica(dpto, actividad.getNombre(),
							actividad.getNickProveedor(), actividad.getDescripcion(), actividad.getDuracion(),
							actividad.getCosto(), actividad.getCiudad(), actividad.getFechaAlta(), salidas,
							actividad.getEstado(), actividad.getNombresCategorias(), actividad.getImgSrc(),
							actividad.getVideo(), actividad.getCantFavoritos(), paquetes);
					das.add(dact);
				}
			}
			return das;
		} else {
			throw new ActividadNoExisteException("No hay actividades en el sistema");
		}
	}

	public ArrayList<String> obtenerCategorias() {
		ManejadorCategorias maCategorias = ManejadorCategorias.getinstance();
		Map<String, Categoria> categorias = maCategorias.getCategorias();
		ArrayList<String> nombres = new ArrayList<String>();
		for (Categoria cat : categorias.values()) {
			nombres.add(cat.getNombre());
		}
		return nombres;
	}

	public void agregarCategoria(String nombre) throws EntidadRepetidaException {
		Categoria categoria = new Categoria(nombre);
		ManejadorCategorias maCategorias = ManejadorCategorias.getinstance();
		if (!maCategorias.existeCategoria(nombre)) {
			maCategorias.addCategoria(categoria);
		} else {
			throw new EntidadRepetidaException("Ya existe la categoria en el sistema");
		}
	}

	public void crearPaquete(String nombre, String descripcion, int validez, double descuento, LocalDate fecha)
			throws EntidadRepetidaException {
		ManejadorPaquetes maPaquetes = ManejadorPaquetes.getinstance();
		if (!maPaquetes.existePaquete(nombre)) {
			maPaquetes.addPaquete(new PaqueteActividades(nombre, descripcion, validez, descuento, fecha, ""));
		} else {
			throw new EntidadRepetidaException("Ya existe un paquete de nombre " + nombre);
		}
	}

	public void crearPaquete(String nombre, String descripcion, int validez, double descuento, LocalDate fecha,
			String imgSrc) throws EntidadRepetidaException {
		ManejadorPaquetes maPaquetes = ManejadorPaquetes.getinstance();
		if (!maPaquetes.existePaquete(nombre)) {
			maPaquetes.addPaquete(new PaqueteActividades(nombre, descripcion, validez, descuento, fecha, imgSrc));
		} else {
			throw new EntidadRepetidaException("Ya existe un paquete de nombre " + nombre);
		}
	}

	public void modificarPaquete(String nombre, String descripcion, int validez, double descuento)
			throws NoExistePaqueteException {
		ManejadorPaquetes maPaquetes = ManejadorPaquetes.getinstance();
		if (!maPaquetes.existePaquete(nombre)) {
			throw new NoExistePaqueteException("No existe un paquete de nombre " + nombre);
		} else {
			PaqueteActividades paquete = maPaquetes.obtenerPaquete(nombre);
			paquete.setDescripcion(descripcion);
			paquete.setValidez(validez);
			paquete.setDescuento(descuento);
		}
	}

	public DataPaqueteActividades consultarPaquete(String nombre) throws NoExisteEntidadException {
		ManejadorPaquetes maPaquetes = ManejadorPaquetes.getinstance();
		if (!maPaquetes.existePaquete(nombre)) {
			throw new NoExisteEntidadException("No existe un paquete de nombre " + nombre);
		} else {
			PaqueteActividades paquete = maPaquetes.obtenerPaquete(nombre);
			LinkedList<DataActividadTuristica> das = new LinkedList<DataActividadTuristica>();
			for (ActividadTuristica actividad : paquete.getActividades().values()) {
				DataDepartamento dataDpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
						actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
				DataActividadTuristica dataAct = new DataActividadTuristica(dataDpto, actividad.getNombre(),
						actividad.getNickProveedor(), actividad.getDescripcion(), actividad.getDuracion(),
						actividad.getCosto(), actividad.getCiudad(), actividad.getFechaAlta(), actividad.getEstado(),
						actividad.getNombresCategorias(), actividad.getImgSrc(), actividad.getVideo(),
						actividad.getCantFavoritos());
				das.add(dataAct);
			}
			DataPaqueteActividades dataPaquete = new DataPaqueteActividades(paquete.getNombre(),
					paquete.getDescripcion(), paquete.getValidez(), paquete.getDescuento(), paquete.getFechaAlta(), das,
					paquete.getComprado(), paquete.getImgSrc(), paquete.getCosto());

			return dataPaquete;
		}
	}

	public ArrayList<DataPaqueteActividades> obtenerPaquetes() throws NoExisteEntidadException {
		ManejadorPaquetes maPaquetes = ManejadorPaquetes.getinstance();
		if (maPaquetes.getPaquetes().isEmpty()) {
			throw new NoExisteEntidadException("No hay paquetes en el sistema");
		} else {
			ArrayList<DataPaqueteActividades> dps = new ArrayList<DataPaqueteActividades>();
			for (PaqueteActividades p : maPaquetes.getPaquetes().values()) {
				dps.add(consultarPaquete(p.getNombre()));
			}
			return dps;
		}
	}

	public List<DataPaqueteActividades> obtenerPaquetes(String actividad) throws NoExisteEntidadException {
		ManejadorPaquetes maPaquetes = ManejadorPaquetes.getinstance();
		if (maPaquetes.getPaquetes().isEmpty()) {
			throw new NoExisteEntidadException("No hay paquetes en el sistema");
		} else {
			ArrayList<DataPaqueteActividades> dps = new ArrayList<DataPaqueteActividades>();
			for (PaqueteActividades p : maPaquetes.getPaquetes().values()) {
				if (p.getActividades().containsKey(actividad)) {
					dps.add(consultarPaquete(p.getNombre()));
				}
			}
			return dps;
		}
	}

	public void agregarActividadPaquete(String paquete, String actividad) throws NoExistePaqueteException,
			ActividadNoExisteException, ActividadRepetidaException, PaqueteCompradoException {
		ManejadorPaquetes maPaquetes = ManejadorPaquetes.getinstance();
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (!maPaquetes.existePaquete(paquete)) {
			throw new NoExistePaqueteException("No existe el paquete");
		} else if (!maActividades.existeActividad(actividad)) {
			throw new ActividadNoExisteException("No existe la actividad");
		} else {
			PaqueteActividades paqueteActividades = maPaquetes.obtenerPaquete(paquete);
			Map<String, ActividadTuristica> acts = paqueteActividades.getActividades();
			if (paqueteActividades.getComprado()) {
				throw new PaqueteCompradoException("El paquete ya fue comprado");
			} else if (acts.containsKey(actividad)) { // existe la actvidad en ese paquete
				throw new ActividadRepetidaException("Esa actividad ya fue agregada al paquete");
			} else {
				paqueteActividades.setActividad(maActividades.obtenerActividad(actividad));
				maActividades.obtenerActividad(actividad).agregarPaquete(paqueteActividades);
			}
		}
	}

	public String obtenerProveedorActividad(String nombre) throws ActividadNoExisteException {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (!maActividades.existeActividad(nombre)) {
			throw new ActividadNoExisteException("No existe la actividad");
		} else {
			ManejadorUsuario maUsuarios = ManejadorUsuario.getinstance();
			return maUsuarios.getProveedorActividad(nombre);
		}
	}

	public void finalizarActividad(String nombreActividad) throws ActividadNoExisteException {
		// Acá tenemos que empezar a ver como persistir las cosas TODO
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		if (!maActividades.existeActividad(nombreActividad)) {
			throw new ActividadNoExisteException("La actividad " + nombreActividad + " no existe");
		} else {
			List<TuristaP> turistas_usados = new ArrayList<TuristaP>();
			EntityManagerSingleton emsing = EntityManagerSingleton.getInstance();
			ActividadTuristica actividad = maActividades.obtenerActividad(nombreActividad);
			actividad.finalizarActividad();
			// se crean las clases a persistir
			ActividadTuristicaP activ = new ActividadTuristicaP(actividad.getNombre(), actividad.getDescripcion(),
					actividad.getDuracion(), actividad.getCosto(), actividad.getCiudad(),
					actividad.getDepartamento().getNombre(), actividad.getFechaAlta(), actividad.getFechaBaja());
			ManejadorInscripciones mInscripciones = ManejadorInscripciones.getinstance();
			List<Inscripcion> inscriptions = mInscripciones.obtenerInscripciones();
			List<SalidaTuristicaP> salidas = new ArrayList<SalidaTuristicaP>();
			for (SalidaTuristica sal : actividad.getSalidas().values()) {
				SalidaTuristicaP salida = new SalidaTuristicaP(sal.getNombre(), sal.getFechaSalida(),
						sal.getHoraSalida(), sal.getCantMaxTuristas(), sal.getLugarSalida(), sal.getFechaAlta());
				for (Inscripcion ins : inscriptions) {
					if (ins.getSalida().getNombre().equals(sal.getNombre())) {
						Turista tur = ins.getTurista();
						TuristaP turis = emsing.obtenerTurista(tur.getNickname());
						if (turis == null) {
							// necesito chequear si el nickname del turista es de alguno que ya tengo
							// guardado
							boolean guardado = false;
							for (TuristaP turista : turistas_usados) {
								if (turista.getNickname().equals(tur.getNickname())) {
									guardado = true;
									// si esta ya lo seteo
									turis = turista;
								}
							}
							if (!(guardado)) {
								// solo si no esta creo uno
								turis = new TuristaP(tur.getNickname(), tur.getNombre(), tur.getApellido(),
										tur.getEmail(), tur.getNacimiento(), tur.getNacionalidad(), "turista");
								// lo guardo
								turistas_usados.add(turis);
								emsing.addTurista(turis);
							}
						}
						InscripcionP inscrip = new InscripcionP(turis, ins.getFechaInscripcion(), ins.getCantTuristas(),
								ins.calcularCosto(), salida);
						turis.setInscripcion(inscrip);
						salida.setInscripciones(inscrip);
					}
				}
				salidas.add(salida);

				salida.setActividadTuristicaP(activ);
			}
			activ.setSalida(salidas);

			ManejadorUsuario maUsuarios = ManejadorUsuario.getinstance();
			Proveedor p = maUsuarios.obtenerProveedor(actividad.getNickProveedor());
			ProveedorP prov = emsing.obtenerProveedor(p.getNickname());
			if (prov == null) {
				prov = new ProveedorP(p.getNickname(), p.getNombre(), p.getApellido(), p.getEmail(), p.getNacimiento(),
						p.getDescripcion(), p.getLink(), "proveedor");
				emsing.addProveedor(prov);
			}
			activ.setProv(prov);
			// conjunto de inscripcionoes de la salida ya quedan linkeados a la salida

			EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("turismo");
			EntityManager em = emFactory.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.merge(activ);
			tx.commit();
			em.close();
			emFactory.close();

		}
	}

	public int updateVisitsActividad(String nombre) {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		return maActividades.updateContador(nombre);
	}

	public Map<String, Integer> getContadorActividades() {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		return maActividades.getContador();
	}

	public ArrayList<DataActividadTuristica> obtenerProveedorActividadesNoFinalizadas(String nickname) {
		ManejadorActividades maActividades = ManejadorActividades.getinstance();
		ArrayList<DataActividadTuristica> das = new ArrayList<DataActividadTuristica>();
		for (ActividadTuristica actividad : maActividades.getActividades().values()) {
			if (!actividad.getEstado().equals(EstadoActividad.FINALIZADA)
					&& actividad.getNickProveedor().equals(nickname)) {
				// pquetes
				List<DataPaqueteActividades> paquetes = new ArrayList<DataPaqueteActividades>();
				for (PaqueteActividades paq : actividad.getPaquetes()) {
					try {
						paquetes.add(consultarPaquete(paq.getNombre()));
					} catch (NoExisteEntidadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// salidas
				List<DataSalidaTuristica> salidas = new ArrayList<>();
				for (var entry : actividad.getSalidas().entrySet()) {
					SalidaTuristica salida = entry.getValue();

					DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
					String fechaSalida = salida.getFechaSalida().format(formatters);
					DataSalidaTuristica dsalida = new DataSalidaTuristica(salida.getNombre(),
							salida.getCantMaxTuristas(), salida.getFechaAlta(), salida.getLugarSalida(), fechaSalida,
							salida.getHoraSalida(), salida.getActividad().getNombre(), salida.getLugaresDisponibles(),
							salida.getImgSrc());
					salidas.add(dsalida);
				}
				DataDepartamento dpto = new DataDepartamento(actividad.getDepartamento().getNombre(),
						actividad.getDepartamento().getDescripcion(), actividad.getDepartamento().getLink());
				DataActividadTuristica dact = new DataActividadTuristica(dpto, actividad.getNombre(),
						actividad.getNickProveedor(), actividad.getDescripcion(), actividad.getDuracion(),
						actividad.getCosto(), actividad.getCiudad(), actividad.getFechaAlta(), salidas,
						actividad.getEstado(), actividad.getNombresCategorias(), actividad.getImgSrc(),
						actividad.getVideo(), actividad.getCantFavoritos(), paquetes);
				das.add(dact);
			}
		}
		return das;
	}

	public ArrayList<DataActividadTuristicaP> obtenerProveedorActividadesFinalizadas(String nickname,
			String nombreActividad) {
		ArrayList<DataActividadTuristicaP> resultado = new ArrayList<DataActividadTuristicaP>();
		EntityManagerSingleton ems = EntityManagerSingleton.getInstance();
		EntityManagerFactory emFactory = ems.getFactory();
		EntityManager em = emFactory.createEntityManager();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		TypedQuery<ActividadTuristicaP> query = em.createQuery(
				"SELECT a FROM ActividadTuristicaP a WHERE a.prov.nickname = :name", ActividadTuristicaP.class);
		List<ActividadTuristicaP> acts = query.setParameter("name", nickname).getResultList();
		for (ActividadTuristicaP activs : acts) {
			if (nombreActividad.equals("") || nombreActividad.equals(activs.getNombre())) {
				ArrayList<DataSalidaTuristicaP> salidas = new ArrayList<DataSalidaTuristicaP>();
				for (SalidaTuristicaP sals : activs.getSalidas()) {
					DataSalidaTuristicaP salid = new DataSalidaTuristicaP(sals.getNombre(), sals.getFechaSalida(),
							sals.getHoraSalida(), sals.getCantMaxTuristas(), sals.getLugarSalida(),
							sals.getFechaAlta());
					salidas.add(salid);
				}
				DataProveedorP dtProv = new DataProveedorP(activs.getProv().getNickname(), activs.getProv().getNombre(),
						activs.getProv().getApellido(), activs.getProv().getEmail(), activs.getProv().getNacimiento(),
						activs.getProv().getDescripcion(), activs.getProv().getLink(),
						activs.getProv().getTipoUsuario());
				DataActividadTuristicaP dataAct = new DataActividadTuristicaP(activs.getNombre(),
						activs.getDescripcion(), activs.getDuracion(), activs.getCosto(), activs.getCiudad(),
						activs.getNombreDepartamento(), activs.getEstado(), activs.getFechaAlta(), salidas,
						activs.getFechaBaja(), dtProv);
				// ya quedo armado el data actividad
				resultado.add(dataAct);

			}
		}
		em.close();
		return resultado;
	}

}
