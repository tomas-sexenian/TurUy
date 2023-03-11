package logica.manejadores;

import java.util.HashMap;
import java.util.Map;
import logica.clases.PaqueteActividades;

public final class ManejadorPaquetes {
	private Map<String, PaqueteActividades> nombrePaquete;
	private static ManejadorPaquetes instancia = null;

	private ManejadorPaquetes() {
		nombrePaquete = new HashMap<String, PaqueteActividades>();
	}

	public static ManejadorPaquetes getinstance() {
		if (instancia == null)
			instancia = new ManejadorPaquetes();
		return instancia;
	}

	/*
	 * Agrega el paquete al sistema. Si ya existia un paquete de igual nombre, la
	 * operacion no tiene efecto
	 */
	public void addPaquete(PaqueteActividades paquete) {
		if (!this.existePaquete(paquete.getNombre()))
			nombrePaquete.put(paquete.getNombre(), paquete);
	}

	/*
	 * Devuelve el paquete indicado. Si el paquete no existe, devuelve null
	 */
	public PaqueteActividades obtenerPaquete(String nombre) {
		if (nombrePaquete.get(nombre) != null) {
			return nombrePaquete.get(nombre);
		} else {
			return null;
		}
	}

	/*
	 * Devuelve todas los paquetes del sistema
	 */
	public Map<String, PaqueteActividades> getPaquetes() {
		return nombrePaquete;
	}

	/*
	 * Devuelve true si en el sistema existe un paquete cuyo nombre es el enviado
	 * por parametro
	 */
	public boolean existePaquete(String nombre) {
		for (PaqueteActividades p : nombrePaquete.values()) {
			if (p.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Remueve a el paquete del sistema, si el paquete no existe entonces la
	 * operacion no tiene efecto.
	 */
	public void removerPaquete(String nombre) {
		nombrePaquete.remove(nombre);
	}
}
