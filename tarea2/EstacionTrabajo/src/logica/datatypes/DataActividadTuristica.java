package logica.datatypes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.EstadoActividad;

public class DataActividadTuristica {

	private DataDepartamento departamento;
	private String nombre;
	private String descripcion;
	private int duracion;
	private double costo;
	private String ciudad;
	private LocalDate fechaAlta;
	private Map<String, DataSalidaTuristica> salidas;
	private EstadoActividad estado;
	private List<String> categorias;
	private String imageSrc;
	private List<DataPaqueteActividades> paquetes;

	public DataActividadTuristica(DataDepartamento departamento, String nombre, String descripcion, int duracion,
			double costo, String ciudad, LocalDate fechaAlta, Map<String, DataSalidaTuristica> salidas,
			EstadoActividad estado, List<String> categorias, String imgSrc, List<DataPaqueteActividades> paquetes) {
		super();
		setDepartamento(departamento);
		setNombre(nombre);
		setDescripcion(descripcion);
		setDuracion(duracion);
		setCosto(costo);
		setCiudad(ciudad);
		this.fechaAlta = fechaAlta;
		setSalidas(salidas);
		this.estado = estado;
		setCategorias(categorias);
		setImageSrc(imgSrc);
		setPaquetes(paquetes);
	}

	public DataActividadTuristica(DataDepartamento departamento, String nombre, String descripcion, int duracion,
			double real, String ciudad, LocalDate fechaAlta, EstadoActividad estado, List<String> categorias,
			String imgSrc) {
		super();
		setDepartamento(departamento);
		setNombre(nombre);
		setDescripcion(descripcion);
		setDuracion(duracion);
		setCosto(real);
		setCiudad(ciudad);
		this.fechaAlta = fechaAlta;
		this.salidas = new HashMap<String, DataSalidaTuristica>();
		this.estado = estado;
		setCategorias(categorias);
		setImageSrc(imgSrc);
	}

	public DataActividadTuristica(DataDepartamento dpto, String nombre, String descripcion, int duracion,
			double costo, String ciudad, LocalDate fechaAlta, Map<String, DataSalidaTuristica> salidas,
			EstadoActividad estado, List<String> nombresCategorias, String imgSrc) {
		setDepartamento(departamento);
		setNombre(nombre);
		setDescripcion(descripcion);
		setDuracion(duracion);
		setCosto(costo);
		setCiudad(ciudad);
		this.fechaAlta = fechaAlta;
		this.salidas = salidas;
		this.estado = estado;
		setCategorias(nombresCategorias);
		setImageSrc(imgSrc);
	}

	public List<DataPaqueteActividades> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<DataPaqueteActividades> paquetes) {
		this.paquetes = paquetes;
	}

	public DataDepartamento getDepartamento() {
		return departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public Map<String, DataSalidaTuristica> getSalidas() {
		return salidas;
	}

	public EstadoActividad getEstadoActividad() {
		return estado;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public void setDepartamento(DataDepartamento departamento) {
		this.departamento = departamento;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setSalidas(Map<String, DataSalidaTuristica> salidas) {
		this.salidas = salidas;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

	/*
	 * public DataSalidaTuristica obtenerSalida (String nombre) { return
	 * this.salidas.get(nombre); }
	 */

	@Override
	public String toString() {
		return nombre + " ($" + costo + ")";
	}

}
