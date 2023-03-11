package logica.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import logica.EstadoActividad;
import logica.manejadores.ManejadorUsuario;

public class Turista extends Usuario {
	private String nacionalidad;
	private List<Compra> compras;
	private List<Inscripcion> inscripciones;
	private Map<String, ActividadTuristica> actFavoritas;

	public Turista() {

	}

	public Turista(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidad, String password, String imgSrc) {
		super(nickname, nombre, apellido, email, nacimiento, password, imgSrc);
		this.nacionalidad = nacionalidad;
		this.compras = new LinkedList<Compra>();
		this.inscripciones = new LinkedList<Inscripcion>();
		this.actFavoritas = new HashMap<String, ActividadTuristica>();
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

	// --

	public Map<String, Proveedor> getSeguidoresProv() {
		return this.seguidoresProv;
	}

	public void setSeguidorProv(Proveedor prov) {
		this.seguidoresProv.put(prov.nickname, prov);
		prov.seguidosTur.put(this.nickname, this);
	}

	// --

	public Map<String, Turista> getSeguidoresTur() {
		return this.seguidoresTur;
	}

	public void setSeguidorTur(Turista tur) {
		if (!tur.nickname.equals(this.nickname)) {
			this.seguidoresTur.put(tur.nickname, tur);
			tur.seguidosTur.put(this.nickname, this);
		}
	}

	// --

	public Map<String, Proveedor> getSeguidosProv() {
		return this.seguidosProv;
	}

	public void setSeguidoProv(Proveedor prov) {
		this.seguidosProv.put(prov.nickname, prov);
		prov.seguidoresTur.put(this.nickname, this);
	}

	// --

	public Map<String, Turista> getSeguidosTur() {
		return this.seguidosTur;
	}

	public void setSeguidoTur(Turista tur) {
		if (!tur.nickname.equals(this.nickname)) {
			this.seguidosTur.put(tur.nickname, tur);
			tur.seguidoresTur.put(this.nickname, this);
		}
	}

	// --

	public void dejarDeSeguirUsr(String nickname) {
		if (this.seguidosProv.containsKey(nickname)) {
			this.seguidosProv.remove(nickname);
			ManejadorUsuario maUsr = ManejadorUsuario.getinstance();
			maUsr.obtenerProveedor(nickname).seguidoresProv.remove(this.nickname);
			maUsr.obtenerProveedor(nickname).seguidoresTur.remove(this.nickname);
		} else if (this.seguidosTur.containsKey(nickname)) {
			this.seguidosTur.remove(nickname);
			ManejadorUsuario maUsr = ManejadorUsuario.getinstance();
			maUsr.obtenerTurista(nickname).seguidoresTur.remove(this.nickname);
			maUsr.obtenerTurista(nickname).seguidoresProv.remove(this.nickname);
		}
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

	public void setActFavorita(ActividadTuristica act) {
		if (act.getEstado() != EstadoActividad.FINALIZADA && act.getEstado() != EstadoActividad.RECHAZADA) {
			this.actFavoritas.put(act.getNombre(), act);
			act.setCantFavoritos(act.getCantFavoritos() + 1);
		}
	}

	public Map<String, ActividadTuristica> getactFavoritas() {
		return this.actFavoritas;
	}

	public void eliminarFavorito(ActividadTuristica act) {
		if (this.actFavoritas.remove(act.getNombre()) != null) {
			act.setCantFavoritos(act.getCantFavoritos() - 1);
		}
	}
}
