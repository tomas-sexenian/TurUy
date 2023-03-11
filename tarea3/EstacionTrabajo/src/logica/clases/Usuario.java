package logica.clases;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public abstract class Usuario {
	protected String nickname;
	protected String nombre;
	protected String apellido;
	protected String email;
	protected LocalDate nacimiento;
	protected String password;
	protected String imgSrc;
	protected Map<String, Proveedor> seguidoresProv;
	protected Map<String, Turista> seguidoresTur;
	protected Map<String, Proveedor> seguidosProv;
	protected Map<String, Turista> seguidosTur;

	public Usuario() {

	}

	protected Usuario(String nickname, String nombre, String apellido, String email, LocalDate nacimiento,
			String password, String imgSrc) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.nacimiento = nacimiento;
		this.password = password;
		this.imgSrc = imgSrc;
		this.seguidoresProv = new HashMap<String, Proveedor>();
		this.seguidoresTur = new HashMap<String, Turista>();
		this.seguidosProv = new HashMap<String, Proveedor>();
		this.seguidosTur = new HashMap<String, Turista>();
	}

	public abstract String getNombre();

	public abstract String getNickname();

	public abstract String getApellido();

	public abstract String getEmail();

	public abstract LocalDate getNacimiento();

	public abstract String getImgSrc();

	public abstract Map<String, Proveedor> getSeguidoresProv();

	public abstract Map<String, Turista> getSeguidoresTur();

	public abstract Map<String, Proveedor> getSeguidosProv();

	public abstract Map<String, Turista> getSeguidosTur();

}
