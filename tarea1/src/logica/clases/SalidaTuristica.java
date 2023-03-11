package logica.clases;

import java.time.*;

public class SalidaTuristica {
	
	private String nombre;
	private int cantMaxTuristas;
	private LocalDate fechaAlta;
	private String lugarSalida;
	private LocalDate fechaSalida;
	private int horaSalida;
	private ActividadTuristica actividad;
	private int lugaresDisponibles;

	public SalidaTuristica(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida, LocalDate fechaSalida, int horaSalida, ActividadTuristica actividad) {
		this.nombre = nombre;
		this.cantMaxTuristas = cantMaxTuristas;
		this.fechaAlta = fechaAlta;
		this.lugarSalida = lugarSalida;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
		this.actividad = actividad;
		this.lugaresDisponibles = cantMaxTuristas;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getCantMaxTuristas() {
		return this.cantMaxTuristas;
	}
	
	public LocalDate getFechaAlta() {
		return this.fechaAlta;
	}
	
	public String lugarSalida() {
		return this.lugarSalida;
	}

	public String getLugarSalida() {
		return lugarSalida;
	}

	public void setLugarSalida(String lugarSalida) {
		this.lugarSalida = lugarSalida;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCantMaxTuristas(int cantMaxTuristas) {
		this.cantMaxTuristas = cantMaxTuristas;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public ActividadTuristica getActividad() {
		return actividad;
	}

	public void setActividad(ActividadTuristica actividad) {
		this.actividad = actividad;
	}
	public int getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(int horaSalida) {
		this.horaSalida = horaSalida;
	}
	
	public void decrementarLugaresDisponibles(int cant) {
		this.lugaresDisponibles -= cant;
	}
	
	public int getLugaresDisponibles() {
		return this.lugaresDisponibles;
	}

	/*@Override
	public String toString() {
		return "SalidaTuristica {nombre=" + nombre + ", cantMaxTuristas=" + cantMaxTuristas + ", fechaAlta=" + fechaAlta.toString()
				+ ", lugarSalida=" + lugarSalida + ", fechaSalida=" + fechaSalida.toString() + ", actividad=" + actividad.toString() + "}";
	}*/
	
}
