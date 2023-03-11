package logica.datatypes;

public class DataInscripcionP {

	private String fechaInscripcion;
	private int cantTuristas;
	private double costo;
	
	private DataTuristaP turista;
	private DataSalidaTuristicaP salida;

	public DataInscripcionP(String fechaInscripcion, int cantTuristas, DataSalidaTuristicaP salida, double costo) {
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

	public void setSalida(DataSalidaTuristicaP salida) {
		this.salida = salida;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public void setTurista(DataTuristaP turista) {
		this.turista = turista;
	}

	public String getFechaInscripcion() {
		return fechaInscripcion;
	}

	public int getCantTuristas() {
		return cantTuristas;
	}

	public DataSalidaTuristicaP getSalida() {
		return salida;
	}

	public double getCosto() {
		return costo;
	}
	
	public DataTuristaP getTurista() {
		return this.turista;
	}

}
