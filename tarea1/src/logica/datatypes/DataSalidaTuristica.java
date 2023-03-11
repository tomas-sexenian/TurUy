package logica.datatypes;

import java.time.LocalDate;


public class DataSalidaTuristica {
	
	private String nombre;
	private int cantMaxTuristas;
	private LocalDate fechaAlta;
	private String lugarSalida;
	private LocalDate fechaSalida;
	private int horaSalida;
	private String actividad;
	private int lugaresDisponibles;
	

	public DataSalidaTuristica(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			LocalDate fechaSalida, int horaSalida, String actividad, int disponibles) {
		super();
		this.nombre = nombre;
		this.cantMaxTuristas = cantMaxTuristas;
		this.fechaAlta = fechaAlta;
		this.lugarSalida = lugarSalida;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
		this.actividad = actividad;
		this.lugaresDisponibles = disponibles;
	}

	
	public String getNombre() {
		return nombre;
	}

	public int getCantMaxTuristas() {
		return cantMaxTuristas;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public String getLugarSalida() {
		return lugarSalida;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public String getActividad() {
		return actividad;
	}

	public int getHoraSalida() {
		return horaSalida;
	}

	@Override
	public String toString() {
		return nombre + " (" + lugaresDisponibles  + ")";
	}
	
}
