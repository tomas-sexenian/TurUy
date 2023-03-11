package logica.datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataTurista {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private String nacimiento;
	private String imgSrc;
	private String nacionalidad;
	private List<DataInscripcion> inscripciones;
	private List<DataCompra> compras;
	private Map<String, DataActividadTuristica> actFavoritas;

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

	public DataTurista(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidad, List<DataInscripcion> inscripciones, List<DataCompra> compras, String imgSrc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String nacimientoString = nacimiento.format(formatter);
		setNacimiento(nacimientoString);
		setNickname(nickname);
		setNombre(nombre);
		setApellido(apellido);
		setEmail(email);
		setImgSrc(imgSrc);
		setNacionalidad(nacionalidad);
		setCompras(compras);
		setInscripciones(inscripciones);
		this.actFavoritas = new HashMap<String, DataActividadTuristica>();
	}

	public DataTurista(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidad, String imgSrc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String nacimientoString = nacimiento.format(formatter);
		setNacimiento(nacimientoString);
		setNickname(nickname);
		setNombre(nombre);
		setApellido(apellido);
		setEmail(email);
		setImgSrc(imgSrc);
		setNacionalidad(nacionalidad);
		setCompras(new LinkedList<DataCompra>());
		setInscripciones(new LinkedList<DataInscripcion>());
		this.actFavoritas = new HashMap<String, DataActividadTuristica>();
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setInscripciones(List<DataInscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public void setCompras(List<DataCompra> compras) {
		this.compras = compras;
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

	public void setImgSrc(String foto) {
		this.imgSrc = foto;
	}

	public String getImgSrc() {
		return this.imgSrc;
	}

	public Map<String, DataActividadTuristica> getactFavoritas() {
		return actFavoritas;
	}

	public void setActFavorita(DataActividadTuristica actv) {
		this.actFavoritas.put(actv.getNombre(), actv);
	}

}
