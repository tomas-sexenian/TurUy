package logica.clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import excepciones.ActividadNoExisteException;

public class Compra {

	private int cantTuristas;
	private LocalDate fechaCompra;
	private PaqueteActividades paquete;
	private LocalDate vigencia;
	private Map<String, Integer> disponiblesActividad; // la clave es el nombre de la actividad, el valor es la cantidad
														// de inscripciones disponibles para esa actividad

	public Compra(int cantTuristas, LocalDate fechaCompra, PaqueteActividades paquete) {
		this.cantTuristas = cantTuristas;
		this.fechaCompra = fechaCompra;
		this.paquete = paquete;
		disponiblesActividad = new HashMap<String, Integer>();
		for (ActividadTuristica actividad : paquete.getActividades().values()) {
			disponiblesActividad.put(actividad.getNombre(), cantTuristas);
		}
		vigencia = calcularVencimiento();
	}

	public int getCantTuristas() {
		return cantTuristas;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public PaqueteActividades getPaquete() {
		return paquete;
	}

	public double calcularCostoTotal() {
		double res = 0;
		for (ActividadTuristica a : paquete.getActividades().values())
			res = res + a.getCosto();
		return ((res * cantTuristas) * (100 - paquete.getDescuento())) / 100;
	}

	public LocalDate calcularVencimiento() {
		return fechaCompra.plusDays(paquete.getValidez());
	}

	public void decrementarInscripcionesDisponibles(String nomActividad, int cant) throws ActividadNoExisteException {
		if (disponiblesActividad.get(nomActividad) == null) {
			throw new ActividadNoExisteException("La actividad no es parte del paquete");
		} else {
			int anterior = disponiblesActividad.get(nomActividad);
			disponiblesActividad.put(nomActividad, anterior - cant);
		}
	}

	public LocalDate getVigencia() {
		return vigencia;
	}

	public int getDisponibilidad(String nomActividad) {
		return disponiblesActividad.get(nomActividad);
	}

}
