package logica.clasesPersistencia;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id_usuario")
@Table(name = "proveedores")
public class ProveedorP extends UsuarioP {

	@Column(nullable = false, length = 2000)
	private String descripcion;
	@Column(nullable = false)
	private String link;

	@OneToMany(mappedBy = "prov")
	private Map<String, ActividadTuristicaP> actividades;

	public ProveedorP(){

	}


	public ProveedorP(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String descripcion, String link, String tipoUsuario) {
		super(nickname, nombre, apellido, email, nacimiento, tipoUsuario);
		this.descripcion = descripcion;
		this.link = link;
		this.actividades = new HashMap<String, ActividadTuristicaP>();
	}

	public String getNickname() {
		return nickname;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Map<String, ActividadTuristicaP> getActividades() {
		return this.actividades;
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

	public String getTipoUsuario() {
		return tipoUsuario;
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

	public void setTipoUsuario(String tipo) {
		this.tipoUsuario = tipo;
	}
	
}
