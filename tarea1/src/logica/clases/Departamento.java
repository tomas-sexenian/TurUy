package logica.clases;

import java.util.HashMap;
import java.util.Map;

public class Departamento {
	private String nombre;
	private String descripcion;
	private String link;
	private Map<String, ActividadTuristica> actividades;

	public Departamento(String nombre, String descripcion, String link) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.link = link;
		this.actividades = new HashMap<String, ActividadTuristica>();
	}

	/*
	 * public Departamento() { super(); this.nombre = new String(); this.descripcion
	 * = new String(); this.link = new String(); }
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setActividad(ActividadTuristica actividad) {
		this.actividades.put(actividad.getNombre(), actividad);
	}

	public Map<String, ActividadTuristica> getActividades() {
		return this.actividades;
	}

	/*
	 * @Override public String toString() { return "Departamento {nombre=" + nombre
	 * + ", descripcion=" + descripcion + ", link=" + link + "}"; }
	 */
}
