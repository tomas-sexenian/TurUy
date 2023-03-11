package logica.clases;

import java.time.LocalDate;

public abstract class Usuario {

	protected String nickname;
	protected String nombre;
	protected String apellido;
	protected String email;
	protected LocalDate nacimiento;
	protected String password;
	protected String imgSrc;

	protected Usuario(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String password, String imgSrc) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nacimiento = nacimiento;
		this.password = password;
		this.imgSrc = imgSrc;
	}

	public abstract String getNombre();

	public abstract String getNickname();

	public abstract String getApellido();

	public abstract String getEmail();

	public abstract LocalDate getNacimiento();

	public abstract String getImgSrc();

}
