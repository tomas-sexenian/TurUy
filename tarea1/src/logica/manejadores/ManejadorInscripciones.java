package logica.manejadores;

import java.util.LinkedList;
import java.util.List;

import logica.clases.Inscripcion;
import logica.clases.Turista;

public final class ManejadorInscripciones {
	private List<Inscripcion> inscripciones;
	private static ManejadorInscripciones instancia = null;

	private ManejadorInscripciones() {
		this.inscripciones = new LinkedList<Inscripcion>();
	}

	public static ManejadorInscripciones getinstance() {
		if (instancia == null)
			instancia = new ManejadorInscripciones();
		return instancia;
	}

	/*
	 * Agrega la inscripcion al sistema
	 */
	public void addInscripcion(Inscripcion insc) {
		inscripciones.add(insc);
	}

	public boolean existeInscripcion(String nickname, String nombreSalida) {
		for (Inscripcion insc : inscripciones) {
			if (insc.getSalida().getNombre() == nombreSalida && insc.getTurista().getNickname() == nickname) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Devuelve todas las inscripciones del sistema
	 */
	public List<Inscripcion> obtenerInscripciones() {
		return inscripciones;
	}

	/*
	 * Devuelve todas las inscripciones del turista indicado
	 */
	public List<Inscripcion> obtenerInscripcionesTurista(String nickname) {
		ManejadorUsuario mUsuario = ManejadorUsuario.getinstance();
		Turista turista = mUsuario.obtenerTurista(nickname);
		return turista.getInscripciones();
	}

}
