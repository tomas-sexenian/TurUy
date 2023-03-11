package logica.clasesPersistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "turistas")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class TuristaP extends UsuarioP {
	@Column(nullable = false)
	private String nacionalidad;

	@OneToMany(mappedBy = "turista")
	private List<InscripcionP> inscripciones;

	public TuristaP() {

	}

	public TuristaP(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String nacionalidad, String tipoUsuario) {
		super(nickname, nombre, apellido, email, nacimiento, tipoUsuario);
		this.nacionalidad = nacionalidad;
		this.inscripciones = new ArrayList<InscripcionP>();
	}

	public String getNickname() {
		return nickname;
	}

	public List<InscripcionP> getInscripciones() {
		return inscripciones;
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

	public String getTipoUsuario() {
		return this.tipoUsuario;
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

	public void setTipoUsuario(String tipo) {
		this.tipoUsuario = tipo;
	}

	public void setInscripcion(InscripcionP inscripcion) {
		this.inscripciones.add(inscripcion);
	}

	@Override
	public void setNickname(String n) {
		this.nickname = n;

	}

	@Override
	public void setEmail(String n) {
		this.email = n;

	}
}
