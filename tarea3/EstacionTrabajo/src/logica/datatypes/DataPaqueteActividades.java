package logica.datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataPaqueteActividades {


	private String nombre;
	private String descripcion;
	private int validez;
	private double descuento;
	private List<DataActividadTuristica> actividades;
	private String fechaAlta;
	private boolean comprado;
	private String imgSrc;
	private double costo;

	public DataPaqueteActividades(String nombre, String descripcion, int validez, double descuento, LocalDate fechaAlta,
			List<DataActividadTuristica> actividades, boolean comprado, String imgSrc, double costo) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String fechaAltaString = fechaAlta.format(formatter);
		setNombre(nombre);
		setDescripcion(descripcion);
		setValidez(validez);
		setDescuento(descuento);
		setActividades(actividades);
		setFechaAlta(fechaAltaString);
		setComprado(comprado);
		setImgSrc(imgSrc);
		setCosto(costo);
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getCosto() {
		double precio = 0;
		for (DataActividadTuristica acts : actividades) {
			precio = precio + acts.getCosto();
		}
		precio = precio * ((100 - descuento) / 100);
		return precio;
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

	public String getFechaAlta() {
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

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setComprado(boolean comprado) {
		this.comprado = comprado;
	}

}
