package logica.datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataSalidaTuristicaP {

	private String nombre;
	private String fechaSalida;
	private String horaSalida;
	private int cantMaxTuristas;
	private String lugarSalida;
	private String fechaAlta;

	public DataSalidaTuristicaP(String nombre, LocalDate fechaSalida, String horaSalida, int cantMaxTuristas,
			String lugarSalida, LocalDate fechaAlta) {
		super();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String fechaAltaString = fechaAlta.format(formatter);
		String fechaSalidaString = fechaSalida.format(formatter);
		setNombre(nombre);
		setCantMaxTuristas(cantMaxTuristas);
		setFechaAlta(fechaAltaString);
		setLugarSalida(lugarSalida);
		setFechaSalida(fechaSalidaString);
		setHoraSalida(horaSalida);
	}

	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCantMaxTuristas(int cantMaxTuristas) {
		this.cantMaxTuristas = cantMaxTuristas;
	}

	public void setFechaAlta(String fechaAlta) {
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

	public String getNombre() {
		return nombre;
	}

	public int getCantMaxTuristas() {
		return cantMaxTuristas;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public String getLugarSalida() {
		return lugarSalida;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public String getHoraSalida() {
		return horaSalida;
	}


}
