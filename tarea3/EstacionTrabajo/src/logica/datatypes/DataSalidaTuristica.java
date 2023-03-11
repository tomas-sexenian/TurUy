package logica.datatypes;

import java.time.LocalDate;

public class DataSalidaTuristica {

	private String nombre;
	private int cantMaxTuristas;
	private LocalDate fechaAlta;
	private String lugarSalida;
	private String fechaSalida;
	private String horaSalida;
	private String actividad;
	private int lugaresDisponibles;
	private String imgSrc;

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public int getLugaresDisponibles() {
		return lugaresDisponibles;
	}

	public void setLugaresDisponibles(int lugaresDisponibles) {
		this.lugaresDisponibles = lugaresDisponibles;
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

	public void setLugarSalida(String lugarSalida) {
		this.lugarSalida = lugarSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public DataSalidaTuristica(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			String fechaSalida, String horaSalida, String actividad, int disponibles, String imgSrc) {
		super();
		setNombre(nombre);
		setCantMaxTuristas(cantMaxTuristas);
		setFechaAlta(fechaAlta);
		setLugarSalida(lugarSalida);
		setFechaSalida(fechaSalida);
		setHoraSalida(horaSalida);
		setActividad(actividad);
		setLugaresDisponibles(disponibles);
		setImgSrc(imgSrc);
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

	public String getFechaSalida() {
		return fechaSalida;
	}

	public String getActividad() {
		return actividad;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	@Override
	public String toString() {
		return nombre + " (" + lugaresDisponibles + ")";
	}

}
