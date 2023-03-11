package logica.clasesPersistencia;

import java.time.LocalDate;
import java.util.List;
import logica.EstadoActividad;
import jakarta.persistence.*;

@Entity
@Table(name = "actividades_turisticas_finalizadas")
public class ActividadTuristicaP {

	@Id 
	@GeneratedValue
	private long id;

	@Column(unique=true, nullable = false)
	private String nombre;
	@Column(nullable = false, length = 2000)
	private String descripcion;
	@Column(nullable = false)
	private int duracion;
	@Column(nullable = false)
	private double costo;
	@Column(nullable = false)
	private String ciudad;
	@Column(nullable = false, name = "nombre_departamento")
	private String nombreDepartamento;
	@Column(nullable = false)
	private EstadoActividad estado;
	@Column(nullable = false, name = "fecha_alta")
	private LocalDate fechaAlta;
	@Column(nullable = false, name = "fecha_baja")
	private LocalDate fechaBaja;
	// private String id_proveedor;
	

	@OneToMany(mappedBy = "actividad",cascade=CascadeType.PERSIST)
	private List<SalidaTuristicaP> salidas;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "Id_proveedor")
	private ProveedorP prov;
	
	public ActividadTuristicaP() {

	}

	public ActividadTuristicaP(String nombre, String descripcion, int duracion, double costo, 
			String ciudad, String nombreDepartamento, LocalDate fechaAlta, LocalDate fechaBaja) {
		super();
		this.nombreDepartamento = nombreDepartamento;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = costo;
		this.ciudad = ciudad;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.estado = EstadoActividad.FINALIZADA;
	}

	public String getNombreDepartamento() {
		return this.nombreDepartamento;
	}
	
	public void setNombreDepartamento(String nombre) {
		this.nombreDepartamento = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) { 
		this.nombre = nombre; 
	}
	

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
	
	public void setFechaBaja(LocalDate fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public List<SalidaTuristicaP> getSalidas() {
		return this.salidas;
	}

	public void setSalida(List<SalidaTuristicaP> salidas) {
		this.salidas = salidas;
	}

	public EstadoActividad getEstado() {
		return estado;
	}

	public ProveedorP getProv() {
		return this.prov;
	}
	
	public void setProv(ProveedorP prov) {
		this.prov = prov;
	}
}
