package logica.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.EstadoActividad;

public class ActividadTuristica {

	private String nombre;
	private String descripcion;
	private int duracion;
	private double costo;
	private String ciudad;
	private String nombreDepartamento;
	private EstadoActividad estado;
	private LocalDate fechaAlta;
	private LocalDate fechaBaja;
	private Departamento departamento;
	private String nickProveedor;
	private Map<String, SalidaTuristica> salidas;
	private List<Categoria> categorias;
	private String imgSrc;
	private String video;
	private List<PaqueteActividades> paquetes;
	private int cantFavoritos;

	public ActividadTuristica(Departamento departamento, String nombre, String nickP, String descripcion, int duracion,
			double costo, String ciudad, LocalDate fechaAlta, List<Categoria> categorias, String imgSrc, String video,
			int cantFavoritos) {
		super();
		this.departamento = departamento;
		this.nombreDepartamento = departamento.getNombre();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = costo;
		this.ciudad = ciudad;
		this.fechaAlta = fechaAlta;
		this.salidas = new HashMap<String, SalidaTuristica>();
		this.estado = EstadoActividad.AGREGADA;
		this.categorias = categorias;
		this.imgSrc = imgSrc;
		this.video = video;
		this.cantFavoritos = cantFavoritos;
		this.paquetes = new ArrayList<PaqueteActividades>();
		this.nickProveedor = nickP;

	}

	public String getNombreDepartamento() {
		return this.nombreDepartamento;
	}

	public String getNickProveedor() {
		return nickProveedor;
	}

	public void setNickProveedor(String nickProveedor) {
		this.nickProveedor = nickProveedor;
	}

	public List<PaqueteActividades> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<PaqueteActividades> paquetes) {
		this.paquetes = paquetes;
	}

	public void agregarPaquete(PaqueteActividades paq) {
		paquetes.add(paq);
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getNombre() {
		return nombre;
	}

	/*
	 * public void setNombre(String nombre) { this.nombre = nombre; }
	 */

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double real) {
		this.costo = real;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public LocalDate getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Map<String, SalidaTuristica> getSalidas() {
		return this.salidas;
	}

	public void setSalida(SalidaTuristica salida) {
		salidas.put(salida.getNombre(), salida);
	}

	public EstadoActividad getEstado() {
		return estado;
	}

	public void rechazarActividad() {
		estado = EstadoActividad.RECHAZADA;
	}

	public void confirmarActividad() {
		estado = EstadoActividad.CONFIRMADA;
	}

	public void finalizarActividad() {
		estado = EstadoActividad.FINALIZADA;
		fechaBaja = LocalDate.now();
	}

	public List<String> getCategorias() {
		List<String> lista = new ArrayList<String>();
		for (Categoria cat : categorias) {
			lista.add(cat.getNombre());
		}
		return lista;
	}

	public List<String> getNombresCategorias() {
		List<String> nombres = new ArrayList<String>();
		for (Categoria categoria : this.categorias) {
			nombres.add(categoria.getNombre());
		}
		return nombres;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String src) {
		this.imgSrc = src;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getVideo() {
		return this.video;
	}

	public int getCantFavoritos() {
		return this.cantFavoritos;
	}

	public void setCantFavoritos(int cant) {
		this.cantFavoritos = cant;
	}

	public boolean perteneceCategoria(String categoria) {
		for (Categoria cat : this.categorias) {
			if (cat.getNombre().equals(categoria))
				return true;
		}
		return false;
	}

}
