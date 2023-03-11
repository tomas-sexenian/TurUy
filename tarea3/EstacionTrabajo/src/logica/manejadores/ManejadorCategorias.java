package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.Categoria;

public final class ManejadorCategorias {

	private Map<String, Categoria> nombreCategoria;
	private static ManejadorCategorias instancia = null;

	private ManejadorCategorias() {
		nombreCategoria = new HashMap<String, Categoria>();
	}

	public static ManejadorCategorias getinstance() {
		if (instancia == null)
			instancia = new ManejadorCategorias();
		return instancia;
	}

	/*
	 * Agrega el departamento al sistema. Si ya existe un departamento de igual
	 * nombre, la operacion no tiene efecto
	 */
	public void addCategoria(Categoria cat) {
		if (!existeCategoria(cat.getNombre())) {
			nombreCategoria.put(cat.getNombre(), cat);
		}
	}

	/*
	 * Devuelve el departamento indicado. Si el departamento no existe, devuelve
	 * null
	 */
	public Categoria obtenerCategoria(String nombre) {
		if (nombreCategoria.get(nombre) != null) {
			return nombreCategoria.get(nombre);
		} else {
			return null;
		}
	}

	/*
	 * Devuelve todos los departamentos del sistema
	 */
	public Map<String, Categoria> getCategorias() {
		return nombreCategoria;
	}

	/*
	 * Devuelve true si y solo si el departamento indicado se encuentra en el
	 * sistema
	 */
	public boolean existeCategoria(String nombre) {
		for (Categoria cat : nombreCategoria.values()) {
			if (cat.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Elimina el departamento del sistema, si el departamento no existe la
	 * operacion no tiene efecto.
	 */
	public void removerCategoria(String nombre) {
		nombreCategoria.remove(nombre);
	}
}
