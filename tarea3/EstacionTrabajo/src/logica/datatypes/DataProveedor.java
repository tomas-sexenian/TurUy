package logica.datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataProveedor {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private String nacimiento;
	private String imgSrc;
	private String descripcion;
	private String link;
	private DataActividadTuristica[] actividades;

	public DataProveedor(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String descripcion, String link, DataActividadTuristica[] actividades, String imgSrc) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String nacimientoString = nacimiento.format(formatter);
		setNacimiento(nacimientoString);
		setNickname(nickname);
		setNombre(nombre);
		setApellido(apellido);
		setEmail(email);
		setDescripcion(descripcion);
		setLink(link);
		setActividades(actividades);
		setImgSrc(imgSrc);
	}

	public DataProveedor(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String descripcion, String link, String imgSrc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String fechaNacString = nacimiento.format(formatter);
		setNacimiento(fechaNacString);
		setNickname(nickname);
		setNombre(nombre);
		setApellido(apellido);
		setEmail(email);
		setDescripcion(descripcion);
		setLink(link);
		setActividades(new DataActividadTuristica[0]);
		setImgSrc(imgSrc);
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setActividades(DataActividadTuristica[] actividades) {
		this.actividades = actividades;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getLink() {
		return link;
	}

	public DataActividadTuristica[] getActividades() {
		return actividades;
	}

	public String getNickname() {
		return nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getEmail() {
		return email;
	}

	public String getNacimiento() {
		return nacimiento;
	}

	public String toString() {
		return this.nickname + " - " + this.nombre + " " + this.apellido;
	}

	public void setImgSrc(String foto) {
		this.imgSrc = foto;
	}

	public String getImgSrc() {
		return this.imgSrc;
	}

}
