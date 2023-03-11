package logica.datatypes;

import java.time.LocalDate;

public class DataCompra {
	private int cantTuristas;
	private LocalDate fechaCompra;
	private double costo;
	private DataPaqueteActividades paquete;
	private LocalDate validez;

	public DataCompra(int cantTuristas, LocalDate fechaCompra, double costo, DataPaqueteActividades paquete,
			LocalDate validez) {
		this.cantTuristas = cantTuristas;
		this.fechaCompra = fechaCompra;
		this.costo = costo;
		this.paquete = paquete;
		this.validez = validez;
	}

	
	public int getCantTuristas() {
		return cantTuristas;
	}
	

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public double getCosto() {
		return costo;
	}

	public LocalDate getValidez() {
		return validez;
	}

	public DataPaqueteActividades getPaquete() {
		return paquete;
	}

	@Override
	public String toString() {
		return "DataCompra {cantTuristas=" + cantTuristas + ", fechaCompra=" + fechaCompra.toString() + ", costo="
				+ costo + ", validez=" + validez.toString() + ", paquete=" + paquete.toString() + "}";
	}
	
}
