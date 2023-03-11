package logica.clasesPersistencia;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "salidas")
public class SalidaTuristicaP {
	@Id 
	@GeneratedValue
	private long id;
	@Column(unique=true, nullable = false)
	private String nombre;
	@Column(name="fecha", nullable = false)
	private LocalDate fechaSalida;
	@Column(name="hora", nullable = false)
	private String horaSalida;
	@Column(name="turistas_max", nullable = false)
	private int cantMaxTuristas;
	@Column(name="lugar", nullable = false)
	private String lugarSalida;
	@Column(name="fecha_alta", nullable = false)
	private LocalDate fechaAlta;

	@OneToMany(mappedBy = "salida", cascade=CascadeType.PERSIST)
	private List<InscripcionP> inscripciones;
	
	@ManyToOne()
	@JoinColumn(name = "Id_actividad")
	private ActividadTuristicaP actividad;

	public SalidaTuristicaP() {

	}


	public SalidaTuristicaP(String nombre, LocalDate fechaSalida, String horaSalida, int cantMaxTuristas,
			String lugarSalida, LocalDate fechaAlta) {
		setNombre(nombre);
		setCantMaxTuristas(cantMaxTuristas);
		setFechaAlta(fechaAlta);
		setLugarSalida(lugarSalida);
		setFechaSalida(fechaSalida);
		setHoraSalida(horaSalida);
		this.inscripciones = new ArrayList<InscripcionP>();
	}
	
	public void setActividadTuristicaP(ActividadTuristicaP act) {
		this.actividad = act;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getCantMaxTuristas() {
		return this.cantMaxTuristas;
	}

	public LocalDate getFechaAlta() {
		return this.fechaAlta;
	}

	public String getLugarSalida() {
		return lugarSalida;
	}

	public void setLugarSalida(String lugarSalida) {
		this.lugarSalida = lugarSalida;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCantMaxTuristas(int cantMaxTuristas) {
		this.cantMaxTuristas = cantMaxTuristas;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	
	public List<InscripcionP> getInscripciones(){
		return this.inscripciones;
	}
	
	public void setInscripciones(InscripcionP inscripcion) {
		this.inscripciones.add(inscripcion);
	}

}
