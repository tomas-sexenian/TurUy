package logica.clases;

import java.time.LocalDate;

public class Inscripcion {
	private Turista turista;
	private LocalDate fechaInscripcion;
	private int cantTuristas;
	private SalidaTuristica salida;
	private double descuento;

	public Inscripcion(Turista turista, SalidaTuristica salida, LocalDate fechaInscripcion, int cantTuristas) {
		this.salida = salida;
		this.turista = turista;
		this.fechaInscripcion = fechaInscripcion;
		this.cantTuristas = cantTuristas;
		this.descuento = 0;
	}
	

	public Inscripcion(Turista turista, SalidaTuristica salida, LocalDate fechaInscripcion, int cantTuristas,
			double descuento) {
		this.salida = salida;
		this.turista = turista;
		this.fechaInscripcion = fechaInscripcion;
		this.cantTuristas = cantTuristas;
		this.descuento = descuento;
	}

	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}

	public Turista getTurista() {
		return turista;
	}

	public int getCantTuristas() {
		return cantTuristas;
	}

	public SalidaTuristica getSalida() {
		return salida;
	}

	public double calcularCosto() {
		return salida.getActividad().getCosto() * cantTuristas * ((100 - descuento) / 100);
	}

}
