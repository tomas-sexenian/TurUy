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

	public DataPaqueteActividades(String nombre, String descripcion, int validez, double descuento, LocalDate fecha,
			List<DataActividadTuristica> actividades, boolean comprado) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validez = validez;
		this.descuento = descuento;
		this.actividades = actividades;
		this.fechaAlta = fecha;
		this.comprado = comprado;
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

}
