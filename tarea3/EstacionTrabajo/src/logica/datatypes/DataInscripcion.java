package logica.datatypes;

public class DataInscripcion {

	private String fechaInscripcion;
	private int cantTuristas;
	private DataSalidaTuristica salida;
	private double costo;

	public DataInscripcion(String fechaInscripcion, int cantTuristas, DataSalidaTuristica salida, double costo) {
		setFechaInscripcion(fechaInscripcion);
		setCantTuristas(cantTuristas);
		setSalida(salida);
		setCosto(costo);
	}

	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public void setCantTuristas(int cantTuristas) {
		this.cantTuristas = cantTuristas;
	}

	public void setSalida(DataSalidaTuristica salida) {
		this.salida = salida;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getFechaInscripcion() {
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

}
