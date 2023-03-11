package logica.datatypes;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class DataTurista extends DataUsuario {
	private String nacionalidad;
	private List<DataInscripcion> inscripciones;
	private List<DataCompra> compras;

	public DataTurista(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidad, List<DataInscripcion> inscripciones, List<DataCompra> compras) {
		super(nickname, nombre, apellido, email, nacimiento);
		// TODO Auto-generated constructor stub
		this.nacionalidad = nacionalidad;
		this.inscripciones = inscripciones;
		this.compras = compras;
	}

	public DataTurista(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidad) {
		super(nickname, nombre, apellido, email, nacimiento);
		// TODO Auto-generated constructor stub
		this.nacionalidad = nacionalidad;
		this.compras = new LinkedList<DataCompra>();
		this.inscripciones = new LinkedList<DataInscripcion>();
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	@Override
	public String getNickname() {
		// TODO Auto-generated method stub
		return nickname;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public String getApellido() {
		// TODO Auto-generated method stub
		return apellido;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public LocalDate getNacimiento() {
		// TODO Auto-generated method stub
		return nacimiento;
	}

	public List<DataInscripcion> getInscripciones() {
		return inscripciones;
	}

	public List<DataCompra> getCompras() {
		return compras;
	}

	@Override
	public String toString() {
		return this.nickname + " - " + this.nombre + " " + this.apellido;
	}

}
