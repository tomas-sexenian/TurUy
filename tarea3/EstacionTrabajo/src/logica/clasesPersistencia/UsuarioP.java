package logica.clasesPersistencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public abstract class UsuarioP {
	@Id 
	@GeneratedValue
	protected long id;
	@Column(unique=true, nullable = false)
	protected String nickname;
	@Column(nullable = false)
	protected String nombre;
	@Column(nullable = false)
	protected String apellido;
	@Column(unique=true, nullable = false)
	protected String email;
	@Column(nullable = false)
	protected LocalDate nacimiento;
	@Column(nullable = false)
	protected String tipoUsuario;

	public UsuarioP() {

	}

	protected UsuarioP(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String tipoUsuario) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nacimiento = nacimiento;
		this.tipoUsuario = tipoUsuario;
	}

	public abstract String getNombre();

	public abstract String getNickname();

	public abstract String getApellido();

	public abstract String getEmail();

	public abstract LocalDate getNacimiento();
	
	public abstract String getTipoUsuario();
	
	public abstract void setNombre(String n);

	public abstract void setNickname(String n);

	public abstract void setApellido(String n);

	public abstract void setEmail(String n);

	public abstract void setNacimiento(LocalDate n);
	
	public abstract void setTipoUsuario(String n);

}
