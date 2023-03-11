package logica.clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PaqueteActividades {

	private String nombre;
	private String descripcion;
	private int validez;
	private double descuento;
	private Map<String, ActividadTuristica> actividades;
	private LocalDate fechaAlta;
	private boolean comprado;

	public PaqueteActividades(String nombre, String descripcion, int validez, double descuento, LocalDate fechaAlta) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validez = validez;
		this.descuento = descuento;
		this.actividades = new HashMap<String, ActividadTuristica>();
		this.fechaAlta = fechaAlta;
		this.comprado = false;
	}

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

	public int getValidez() {
		return validez;
	}

	public void setValidez(int validez) {
		this.validez = validez;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public Map<String, ActividadTuristica> getActividades() {
		return actividades;
	}

	public void setActividad(ActividadTuristica actividad) {
		this.actividades.put(actividad.getNombre(), actividad);
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setComprado() {
		this.comprado = true;
	}

	public boolean getComprado() {
		return comprado;
	}
}
