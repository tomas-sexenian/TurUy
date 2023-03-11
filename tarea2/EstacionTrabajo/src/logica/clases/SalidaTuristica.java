package logica.clases;

import java.time.LocalDate;

public class SalidaTuristica {

	private String nombre;
	private int cantMaxTuristas;
	private LocalDate fechaAlta;
	private String lugarSalida;
	private LocalDate fechaSalida;
	private String horaSalida;
	private ActividadTuristica actividad;
	private int lugaresDisponibles;
	private String imgSrc;

	public SalidaTuristica(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			LocalDate fechaSalida, String horaSalida, ActividadTuristica actividad) {
		this.nombre = nombre;
		this.cantMaxTuristas = cantMaxTuristas;
		this.fechaAlta = fechaAlta;
		this.lugarSalida = lugarSalida;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
		this.actividad = actividad;
		this.lugaresDisponibles = cantMaxTuristas;
		this.imgSrc = "";
	}

	public SalidaTuristica(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			LocalDate fechaSalida, String horaSalida, ActividadTuristica actividad, String imgSrc) {
		this.nombre = nombre;
		this.cantMaxTuristas = cantMaxTuristas;
		this.fechaAlta = fechaAlta;
		this.lugarSalida = lugarSalida;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
		this.actividad = actividad;
		this.lugaresDisponibles = cantMaxTuristas;
		this.imgSrc = imgSrc;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
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

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public void decrementarLugaresDisponibles(int cant) {
		this.lugaresDisponibles -= cant;
	}

	public int getLugaresDisponibles() {
		return this.lugaresDisponibles;
	}

	/*
	 * @Override public String toString() { return "SalidaTuristica {nombre=" +
	 * nombre + ", cantMaxTuristas=" + cantMaxTuristas + ", fechaAlta=" +
	 * fechaAlta.toString() + ", lugarSalida=" + lugarSalida + ", fechaSalida=" +
	 * fechaSalida.toString() + ", actividad=" + actividad.toString() + "}"; }
	 */

}
