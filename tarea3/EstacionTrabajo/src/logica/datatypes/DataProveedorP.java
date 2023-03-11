package logica.datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataProveedorP {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private String nacimiento;
	private String tipoUsuario;
	private String descripcion;
	private String link;

	public DataProveedorP(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String descripcion, String link, String tipoUsuario) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String nacimientoString = nacimiento.format(formatter);
		setNacimiento(nacimientoString);
		setNickname(nickname);
		setNombre(nombre);
		setApellido(apellido);
		setEmail(email);
		setDescripcion(descripcion);
		setLink(link);
		this.tipoUsuario = tipoUsuario;
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
	
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setLink(String link) {
		this.link = link;
	}



	public String getDescripcion() {
		return descripcion;
	}

	public String getLink() {
		return link;
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
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public String toString() {
		return this.nickname + " - " + this.nombre + " " + this.apellido;
	}
}
