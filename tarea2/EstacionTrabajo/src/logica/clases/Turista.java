package logica.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Turista extends Usuario {
	private String nacionalidad;
	private List<Compra> compras;
	private List<Inscripcion> inscripciones;

	public Turista(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidad, String password, String imgSrc) {
		super(nickname, nombre, apellido, email, nacimiento, password, imgSrc);
		this.nacionalidad = nacionalidad;
		this.compras = new LinkedList<Compra>();
		this.inscripciones = new LinkedList<Inscripcion>();
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getPassword() {
		return password;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public List<Inscripcion> getInscripciones() {
		return this.inscripciones;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setCompra(Compra compra) {
		this.compras.add(compra);
	}

	public void setInscripcion(Inscripcion insc) {
		this.inscripciones.add(insc);
	}

	public boolean comproPaquete(String nombrePaq) {
		for (Compra compra : compras) {
			if (compra.getPaquete().getNombre().equals(nombrePaq)) {
				return true;
			}
		}
		return false;
	}

	public Compra obtenerCompra(String nomPaquete) {
		for (Compra compra : compras) {
			if (compra.getPaquete().getNombre().equals(nomPaquete)) {
				return compra;
			}
		}
		return null;
	}

	public List<Compra> obtenerCompras(String nomActividad) {
		List<Compra> comprasActividad = new ArrayList<Compra>();
		for (Compra compra : compras) {
			if (compra.getPaquete().getActividades().containsKey(nomActividad)) {
				comprasActividad.add(compra);
			}
		}
		return comprasActividad;
	}
	
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	public String getImgSrc() {
		return this.imgSrc;
	}

}
