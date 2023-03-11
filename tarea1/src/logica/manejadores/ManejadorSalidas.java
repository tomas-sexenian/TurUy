package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.SalidaTuristica;

public final class ManejadorSalidas {

	private Map<String, SalidaTuristica> salidaNombre;
	private static ManejadorSalidas instancia = null;

	private ManejadorSalidas() {
		salidaNombre = new HashMap<String, SalidaTuristica>();
	}

	public static ManejadorSalidas getinstance() {
		if (instancia == null)
			instancia = new ManejadorSalidas();
		return instancia;
	}

	/*
	 * Agrega la salida al sistema. Si ya existe una salida de igual nombre, la
	 * operacion no tiene efecto
	 */
	public void addSalida(SalidaTuristica salida) {
		if (!existeSalida(salida.getNombre()))
			salidaNombre.put(salida.getNombre(), salida);
	}

	/*
	 * Devuelve la salida indicada, si la salida no existe en el sistema devuelve
	 * null
	 */
	public SalidaTuristica obtenerSalida(String nombre) {
		if (salidaNombre.get(nombre) != null) {
			return salidaNombre.get(nombre);
		} else {
			return null;
		}
	}

	/*
	 * Devuelve todas las salidas turisticas del sistema
	 */
	public Map<String, SalidaTuristica> getSalidas() {
		return salidaNombre;
	}

	/*
	 * Devuelve true si en el sistema existe una salida cuyo nombre es el enviado
	 * por parametro
	 */
	public boolean existeSalida(String nombre) {

		for (SalidaTuristica sal : salidaNombre.values()) {
			if (sal.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Remueve a la salida del sistema
	 */
	public void removerSalida(String nombre) {
		salidaNombre.remove(nombre);
	}
}
