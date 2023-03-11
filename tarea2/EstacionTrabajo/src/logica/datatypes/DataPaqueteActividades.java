package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

public class DataPaqueteActividades {
	private String nombre;
	private String descripcion;
	private int validez;
	private double descuento;
	private List<DataActividadTuristica> actividades;
	private LocalDate fechaAlta;
	private boolean comprado;
	private String imgSrc;
	private double costo;

	public DataPaqueteActividades(String nombre, String descripcion, int validez, double descuento, LocalDate fecha,
			List<DataActividadTuristica> actividades, boolean comprado, String imgSrc, double costo) {
		super();
		setNombre(nombre);
		setDescripcion(descripcion);
		setValidez(validez);
		setDescuento(descuento);
		setActividades(actividades);
		setFechaAlta(fecha);
		setComprado(comprado);
		setImgSrc(imgSrc);
		setCosto(costo);
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public double getCosto() {
		return this.costo;
	}
	
	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getValidez() {
		return validez;
	}

	public double getDescuento() {
		return descuento;
	}

	public List<DataActividadTuristica> getActividades() {
		return actividades;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public boolean getComprado() {
		return comprado;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setValidez(int validez) {
		this.validez = validez;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public void setActividades(List<DataActividadTuristica> actividades) {
		this.actividades = actividades;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}

	public double getPrecio() {
		double precio = 0;
		for (DataActividadTuristica acts: actividades) {
			precio = precio + acts.getCosto();
		}
		precio = precio * ((100-descuento)/100);
		return precio;
	}
}
