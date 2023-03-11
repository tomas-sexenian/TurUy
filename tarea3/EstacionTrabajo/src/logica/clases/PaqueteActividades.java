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
	private String imgSrc;

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public double getCosto() {
		double total = 0;
		for (var act : this.actividades.entrySet()) {
			total += act.getValue().getCosto();
		}
		return total * (1 - this.descuento);
	}

	public PaqueteActividades(String nombre, String descripcion, int validez, double descuento, LocalDate fechaAlta,
			String imgSrc) {
		super();
		setNombre(nombre);
		setDescripcion(descripcion);
		setValidez(validez);
		setDescuento(descuento);
		this.actividades = new HashMap<String, ActividadTuristica>();
		this.fechaAlta = fechaAlta;
		this.comprado = false;
		setImgSrc(imgSrc);
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

	public double getPrecio() {
		double precio = 0;
		for (ActividadTuristica acts : actividades.values()) {
			precio = precio + acts.getCosto();
		}
		precio = precio * ((100 - descuento) / 100);
		return precio;
	}
}
