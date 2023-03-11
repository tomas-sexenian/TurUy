package logica.clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Proveedor extends Usuario {

	private String descripcion;
	private String link;
	private Map<String, ActividadTuristica> actividades;

	public Proveedor(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String descripcion, String link, String password) {
		super(nickname, nombre, apellido, email, nacimiento, password);
		this.descripcion = descripcion;
		this.link = link;
		this.actividades = new HashMap<String, ActividadTuristica>();
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

	public LocalDate getNacimiento() {
		return nacimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getLink() {
		return link;
	}

	public String getPassword() {
		return password;
	}

	public Map<String, ActividadTuristica> getActividades() {
		return this.actividades;
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

	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setLInk(String link) {
		this.link = link;
	}

	public void setActividad(ActividadTuristica actividad) {
		String nombre = actividad.getNombre();
		actividades.put(nombre, actividad);
	}

}
