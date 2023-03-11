package logica.datatypes;

import java.time.LocalDate;

public class DataInscripcion {
	private LocalDate fechaInscripcion;
	private int cantTuristas;
	private DataSalidaTuristica salida;
	private double costo;
	
//	public DataInscripcion(LocalDate fechaInscripcion, int cantTuristas, DataSalidaTuristica salida) {
//		super();
//		this.fechaInscripcion = fechaInscripcion;
//		this.cantTuristas = cantTuristas;
//		this.salida = salida;
//	}
	
	public DataInscripcion(LocalDate fechaInscripcion, int cantTuristas, DataSalidaTuristica salida, double costo) {
		super();
		this.fechaInscripcion = fechaInscripcion;
		this.cantTuristas = cantTuristas;
		this.salida = salida;
		this.costo = costo;
	}

	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}

	public int getCantTuristas() {
		return cantTuristas;
	}

	public DataSalidaTuristica getSalida() {
		return salida;
	}
	
	public double getCosto() {
		return costo;
	}
	/*
	@Override
	public String toString() {
		return "DataInscripcion {fechaInscripcion=" + fechaInscripcion + ", cantTuristas=" + cantTuristas + ", salida="
				+ salida.toString() + "}";
	}*/

}
