package logica.datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataTuristaP {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private String nacimiento;
	private String tipoUsuario;
	private String nacionalidad;
	private List<DataInscripcionP> inscripciones;

	public DataTuristaP(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String tipoUsuario, String nacionalidad) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String nacimientoString = nacimiento.format(formatter);
		setNacimiento(nacimientoString);
		setNickname(nickname);
		setNombre(nombre);
		setApellido(apellido);
		setEmail(email);
		this.tipoUsuario = tipoUsuario;
		setNacionalidad(nacionalidad);
		inscripciones = new ArrayList<DataInscripcionP>();
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

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setInscripciones(DataInscripcionP inscripcion) {
		this.inscripciones.add(inscripcion);
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getNickname() {
		// TODO Auto-generated method stub
		return nickname;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	public String getApellido() {
		// TODO Auto-generated method stub
		return apellido;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	public String getNacimiento() {
		// TODO Auto-generated method stub
		return nacimiento;
	}
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public List<DataInscripcionP> getInscripciones() {
		return inscripciones;
	}
}
