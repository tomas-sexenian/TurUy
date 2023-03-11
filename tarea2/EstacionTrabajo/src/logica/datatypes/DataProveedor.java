package logica.datatypes;

import java.time.LocalDate;

public class DataProveedor extends DataUsuario {
	private String descripcion;
	private String link;
	private DataActividadTuristica[] actividades;
	
	
	public DataProveedor(String nickname, String nombre, String apellido, String email, LocalDate nacimiento, String descripcion, String link, DataActividadTuristica[] actividades, String imgSrc) {
		super(nickname, nombre, apellido, email, nacimiento, imgSrc);
		// TODO Auto-generated constructor stub
		this.descripcion = descripcion;
		this.link = link;
		this.actividades = actividades;
	}
	public DataProveedor(String nickname, String nombre, String apellido, String email, LocalDate nacimiento, String descripcion, String link, String imgSrc) {
		super(nickname, nombre, apellido, email, nacimiento, imgSrc);
		// TODO Auto-generated constructor stub
		this.descripcion = descripcion;
		this.link = link;
		this.actividades = new DataActividadTuristica[0];
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

	@Override
	public String getNickname() {
		return nickname;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getApellido() {
		return apellido;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public LocalDate getNacimiento() {
		return nacimiento;
	}

	@Override
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
