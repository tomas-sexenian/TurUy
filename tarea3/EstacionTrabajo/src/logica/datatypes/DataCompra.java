package logica.datatypes;

public class DataCompra {
	private int cantTuristas;
	private String fechaCompra;
	private double costo;
	private DataPaqueteActividades paquete;
	private String validez;
	private boolean isValid;

	public DataCompra(int cantTuristas, String fechaCompra, double costo, DataPaqueteActividades paquete,
			String validez, boolean isValid) {
		setCantTuristas(cantTuristas);
		setFechaCompra(fechaCompra);
		setCosto(costo);
		setPaquete(paquete);
		setValidez(validez);
		setValid(isValid);

	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public void setCantTuristas(int cantTuristas) {
		this.cantTuristas = cantTuristas;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setPaquete(DataPaqueteActividades paquete) {
		this.paquete = paquete;
	}

	public void setValidez(String validez) {
		this.validez = validez;
	}

	public int getCantTuristas() {
		return cantTuristas;
	}

	public String getFechaCompra() {
		return fechaCompra;
	}

	public double getCosto() {
		return costo;
	}

	public String getValidez() {
		return validez;
	}

	public DataPaqueteActividades getPaquete() {
		return paquete;
	}

}
