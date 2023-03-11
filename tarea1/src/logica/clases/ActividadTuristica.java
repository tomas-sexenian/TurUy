package logica.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.EstadoActividad;

public class ActividadTuristica {
	private Departamento departamento;
	private String nombre;
	private String descripcion;
	private int duracion;
	private double costo;
	private String ciudad;
	private LocalDate fechaAlta;
	private Map<String, SalidaTuristica> salidas;
	private EstadoActividad estado;
	private List<Categoria> categorias;

	public ActividadTuristica(Departamento departamento, String nombre, String descripcion, int duracion, double costo,
			String ciudad, LocalDate fechaAlta, List<Categoria> categorias) {
		super();
		this.departamento = departamento;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = costo;
		this.ciudad = ciudad;
		this.fechaAlta = fechaAlta;
		this.salidas = new HashMap<String, SalidaTuristica>();
		this.estado = EstadoActividad.AGREGADA;
		this.categorias = categorias;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getNombre() {
		return nombre;
	}

	/*
	 * public void setNombre(String nombre) { this.nombre = nombre; }
	 */

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double real) {
		this.costo = real;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Map<String, SalidaTuristica> getSalidas() {
		return this.salidas;
	}

	public void setSalida(SalidaTuristica salida) {
		salidas.put(salida.getNombre(), salida);
	}

	public EstadoActividad getEstado() {
		return estado;
	}

	public void rechazarActividad() {
		estado = EstadoActividad.RECHAZADA;
	}

	public void confirmarActividad() {
		estado = EstadoActividad.CONFIRMADA;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public List<String> getNombresCategorias() {
		List<String> nombres = new ArrayList<String>();
		for (Categoria categoria : categorias) {
			nombres.add(categoria.getNombre());
		}
		return nombres;
	}

	/*
	 * @Override public String toString() { return
	 * "ActividadTuristica {departamento=" + departamento + ", nombre=" + nombre +
	 * ", Descripcion=" + descripcion + ", duracion=" + duracion + ", real=" + costo
	 * + ", ciudad=" + ciudad + ", fechaAlta=" + fechaAlta.toString() + ", salidas="
	 * + salidas.toString() + "}"; }
	 */

}
