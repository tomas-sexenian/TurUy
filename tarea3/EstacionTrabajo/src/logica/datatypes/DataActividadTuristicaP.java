package logica.datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import logica.EstadoActividad;

public class DataActividadTuristicaP {

	private String nombre;
	private String descripcion;
	private int duracion;
	private double costo;
	private String ciudad;
	private String nombreDepartamento;
	private EstadoActividad estado;
	private String fechaAlta;
	private String fechaBaja;

	private List<DataSalidaTuristicaP> salidas;

	private DataProveedorP proveedor;

	public DataActividadTuristicaP(String nombre, String descripcion, int duracion, double costo, String ciudad,
			String nombreDepartamento, EstadoActividad estado, LocalDate fechaAlta, List<DataSalidaTuristicaP> salidas,
			LocalDate fechaBaja, DataProveedorP prov) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String fechaAltaString = fechaAlta.format(formatter);
		setNombre(nombre);
		setDescripcion(descripcion);
		setDuracion(duracion);
		setCosto(costo);
		setCiudad(ciudad);
		this.fechaAlta = fechaAltaString;
		this.estado = estado;
		this.proveedor = prov;
		setNombreDepartamento(nombreDepartamento);
		setSalidas(salidas);
		this.fechaBaja = fechaBaja.format(formatter);
	}

	public String getNombreDepartamento() {
		return this.nombreDepartamento;
	}

	public void setNombreDepartamento(String nombre) {
		this.nombreDepartamento = nombre;
	}

	public DataProveedorP getProveedor() {
		return proveedor;
	}

	public void setProveedor(DataProveedorP proveedor) {
		this.proveedor = proveedor;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public List<DataSalidaTuristicaP> getSalidas() {
		return salidas;
	}

	public EstadoActividad getEstadoActividad() {
		return estado;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setSalidas(List<DataSalidaTuristicaP> salidas) {
		this.salidas = salidas;
	}

	public EstadoActividad getEstado() {
		return estado;
	}

	public void setEstado(EstadoActividad estado) {
		this.estado = estado;
	}

	/*
	 * public DataSalidaTuristica obtenerSalida (String nombre) { return
	 * this.salidas.get(nombre); }
	 */

	@Override
	public String toString() {
		return nombre + " ($" + costo + ")";
	}

}
