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

	public DataActividadTuristica(DataDepartamento departamento, String nombre, String descripcion, int duracion,
			double real, String ciudad, LocalDate fechaAlta, Map<String, DataSalidaTuristica> salidas,
			EstadoActividad estado, List<String> categorias) {
		super();
		this.departamento = departamento;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = real;
		this.ciudad = ciudad;
		this.fechaAlta = fechaAlta;
		this.salidas = salidas;
		this.estado = estado;
		this.categorias = categorias;
	}

	public DataActividadTuristica(DataDepartamento departamento, String nombre, String descripcion, int duracion,
			double real, String ciudad, LocalDate fechaAlta, EstadoActividad estado, List<String> categorias) {
		super();
		this.departamento = departamento;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = real;
		this.ciudad = ciudad;
		this.fechaAlta = fechaAlta;
		this.salidas = new HashMap<String, DataSalidaTuristica>();
		this.estado = estado;
		this.categorias = categorias;
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

	public double getReal() {
		return costo;
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

	/*
	 * public DataSalidaTuristica obtenerSalida (String nombre) { return
	 * this.salidas.get(nombre); }
	 */

	@Override
	public String toString() {
		return nombre + " ($" + costo + ")";
	}

}
