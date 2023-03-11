package logica.datatypes;

import java.time.LocalDate;

public class DataUsuario {

	protected String nickname;
	protected String nombre;
	protected String apellido;
	protected String email;
	protected LocalDate nacimiento;
	protected String imgSrc;

	protected DataUsuario(String nickname, String nombre, String apellido, String email, LocalDate nacimiento, String imgSrc) {
		setNickname(nickname);
		setNombre(nombre);
		setApellido(apellido);
		setEmail(email);
		setNacimiento(nacimiento);
		setImgSrc(imgSrc);
	}

	protected String getNickname() {
		return nickname;
	}

	protected String getNombre() {
		return nombre;
	}

	protected String getApellido() {
		return apellido;
	}

	protected String getEmail() {
		return email;
	}

	protected LocalDate getNacimiento() {
		return nacimiento;
	}

	protected void setNickname(String nickname) {
		this.nickname = nickname;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected void setApellido(String apellido) {
		this.apellido = apellido;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}
	
	protected void setImgSrc(String foto) {
		this.imgSrc = foto;
	}
	
	protected String getImgSrc() {
		return this.imgSrc;
	}

	/*
	 * @Override public String toString() { return "DataUsuario {nickname=" +
	 * nickname + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" +
	 * email + ", nacimiento=" + nacimiento + "}"; }
	 */

}
