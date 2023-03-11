package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.Proveedor;
import logica.clases.Turista;

public final class ManejadorUsuario {
	private Map<String, Turista> turistaNickname;
	private Map<String, Proveedor> proveedorNickname;
	private static ManejadorUsuario instancia = null;

	private ManejadorUsuario() {
		turistaNickname = new HashMap<String, Turista>();
		proveedorNickname = new HashMap<String, Proveedor>();
	}

	public static ManejadorUsuario getinstance() {
		if (instancia == null)
			instancia = new ManejadorUsuario();
		return instancia;
	}

	/*
	 * Agrega el turista al sistema. Si ya existe un turista de igual nombre, la
	 * operacion no tiene efecto
	 */
	public void addTurista(Turista turista) {
		if (!existeTuristaNick(turista.getNombre()))
			turistaNickname.put(turista.getNickname(), turista);
	}

	/*
	 * Agrega el proveedor al sistema. SI ya existe un proveedor de igual nombre, la
	 * operacion no tiene efecto
	 */
	public void addProveedor(Proveedor proveedor) {
		if (!existeProveedorNick(proveedor.getNombre()))
			proveedorNickname.put(proveedor.getNickname(), proveedor);
	}

	/*
	 * Devuelve el turista indicado en caso de que exista, en caso contrario
	 * devuelve null
	 */
	public Turista obtenerTurista(String nickname) {
		if (turistaNickname.get(nickname) != null) {
			return turistaNickname.get(nickname);
		}
		return null;

	}

	/*
	 * Devuelve el proveedor indicado en caso de que exista, en caso contrario
	 * devuelve null
	 */
	public Proveedor obtenerProveedor(String nickname) {
		if (proveedorNickname.get(nickname) != null)
			return proveedorNickname.get(nickname);
		return null;

	}

	/*
	 * Devuelve todos los turistas del sistema.
	 */
	public Map<String, Turista> getTuristas() {
		return turistaNickname;
	}

	/*
	 * Devuelve todos los proveedores del sistema.
	 */
	public Map<String, Proveedor> getProveedores() {
		return proveedorNickname;
	}

	/*
	 * Devuelve true si en el sistema existe un turista con el nickname enviado por
	 * parametro
	 */
	public boolean existeTuristaNick(String nickname) {
		for (Turista tur : turistaNickname.values()) {
			if (tur.getNickname().toLowerCase().equals(nickname.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Devuelve true si en el sistema existe un proveedor con el nickname enviado
	 * por parametro
	 */
	public boolean existeProveedorNick(String nickname) {
		for (Proveedor p : proveedorNickname.values()) {
			if (p.getNickname().toLowerCase().equals(nickname.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Devuelve true si en el sistema existe un turista con el nickname enviado por
	 * parametro
	 */
	public boolean existeTuristaEmail(String email) {
		for (Turista tur : turistaNickname.values()) {
			if (tur.getEmail().toLowerCase().equals(email.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Devuelve true si en el sistema existe un proveedor con el nickname enviado
	 * por parametro
	 */
	public boolean existeProveedorEmail(String email) {
		for (Proveedor proveedor : proveedorNickname.values()) {
			if (proveedor.getEmail().toLowerCase().equals(email.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Remueve al turista del sistema Si el turista no existe, la operacion no tiene
	 * efecto.
	 */
	public void removerTurista(String nickname) {
		this.turistaNickname.remove(nickname);
	}

	/*
	 * Remueve al proveedor del sistema. Si el proveedor no existe, la operacion no
	 * tiene efecto.
	 */
	public void removerProveedor(String nickname) {
		this.proveedorNickname.remove(nickname);
	}

	public String getProveedorActividad(String nombre) {
		for (Proveedor proveedor : proveedorNickname.values()) {
			if (proveedor.tieneActividad(nombre)) {
				return proveedor.getNickname();
			}
		}
		return "";
	}

}
