package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.ActividadTuristica;

public final class ManejadorActividades {
	private Map<String, ActividadTuristica> nombreActividad;
	private Map<String, Integer> contador;

	private static ManejadorActividades instancia = null;

	private ManejadorActividades() {
		nombreActividad = new HashMap<String, ActividadTuristica>();
		contador = new HashMap<String, Integer>();
	}

	public static ManejadorActividades getinstance() {
		if (instancia == null)
			instancia = new ManejadorActividades();
		return instancia;
	}

	/*
	 * Agrega la actividad al sistema. Si ya existia una actividad de igual nombre,
	 * la operacion no tiene efecto
	 */
	public void addActividad(ActividadTuristica act) {
		if (!existeActividad(act.getNombre()))
			nombreActividad.put(act.getNombre(), act);
	}

	/*
	 * Devuelve la actividad indicada. Si la actividad no existe, devuelve null
	 */
	public ActividadTuristica obtenerActividad(String nombre) {
		if (nombreActividad.get(nombre) != null) {
			return nombreActividad.get(nombre);
		} else {
			return null;
		}
	}

	/*
	 * Devuelve todas las actividades del sistema
	 */
	public Map<String, ActividadTuristica> getActividades() {
		return nombreActividad;
	}

	/*
	 * Devuelve true si en el sistema existe una actividad cuyo nombre es el enviado
	 * por parametro
	 */
	public boolean existeActividad(String nombre) {
		for (ActividadTuristica act : nombreActividad.values()) {
			if (act.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Remueve a la actividad del sistema, si la actividad no existe la operacion no
	 * tiene efecto
	 */
	public void removerActividad(String nombre) {
		nombreActividad.remove(nombre);
	}

	public int updateContador(String actividad) {
		int actual = 1;
		if (contador.containsKey(actividad)) {
			actual = contador.get(actividad);
			actual++;
		}
		contador.put(actividad, actual);
		return actual;
	}

	public Map<String, Integer> getContador() {
		return contador;
	}
}
