package logica.manejadores;

import java.util.HashMap;
import java.util.Map;
import logica.clases.Departamento;

public final class ManejadorDepartamentos {
	private Map<String, Departamento> nombreDepartamento;
	private static ManejadorDepartamentos instancia = null;

	private ManejadorDepartamentos() {
		nombreDepartamento = new HashMap<String, Departamento>();
	}

	public static ManejadorDepartamentos getinstance() {
		if (instancia == null)
			instancia = new ManejadorDepartamentos();
		return instancia;
	}

	/*
	 * Agrega el departamento al sistema. Si ya existe un departamento de igual
	 * nombre, la operacion no tiene efecto
	 */
	public void addDepartamento(Departamento dpto) {
		if (!existeDepartamento(dpto.getNombre()))
			nombreDepartamento.put(dpto.getNombre(), dpto);
	}

	/*
	 * Devuelve el departamento indicado. Si el departamento no existe, devuelve
	 * null
	 */
	public Departamento obtenerDepartamento(String nombre) {
		if (nombreDepartamento.get(nombre) != null) {
			return nombreDepartamento.get(nombre);
		} else {
			return null;
		}
	}

	/*
	 * Devuelve todos los departamentos del sistema
	 */
	public Map<String, Departamento> getDepartamentos() {
		return nombreDepartamento;
	}

	/*
	 * Devuelve true si y solo si el departamento indicado se encuentra en el
	 * sistema
	 */
	public boolean existeDepartamento(String nombre) {
		for (Departamento dep : nombreDepartamento.values()) {
			if (dep.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Elimina el departamento del sistema, si el departamento no existe la
	 * operacion no tiene efecto.
	 */
	public void removerDepartamento(String nombre) {
		nombreDepartamento.remove(nombre);
	}
}
