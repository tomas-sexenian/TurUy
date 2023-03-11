package logica.clasesPersistencia;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inscripciones")
public class InscripcionP {
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, name = "fecha_inscripcion")
	private LocalDate fechaInscripcion;
	@Column(nullable = false, name = "cantidad_turistas")
	private int cantTuristas;
	@Column(nullable = false)
	private double costo;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Id_turista", nullable = false)
	private TuristaP turista;

	@ManyToOne()
	@JoinColumn(name = "Id_salida", nullable = false)
	private SalidaTuristicaP salida;

	public InscripcionP() {

	}

	public InscripcionP(TuristaP turista, LocalDate fechaInscripcion, int cantTuristas, double costo,
			SalidaTuristicaP salida) {
		this.salida = salida;
		this.fechaInscripcion = fechaInscripcion;
		this.cantTuristas = cantTuristas;
		this.costo = costo;
		this.turista = turista;
	}

	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}

	public TuristaP getTurista() {
		return turista;
	}

	public int getCantTuristas() {
		return cantTuristas;
	}

	public double getCosto() {
		return this.costo;
	}

	public SalidaTuristicaP getSalida() {
		return this.salida;
	}

	public void setFechaInscripcion(LocalDate fecha) {
		this.fechaInscripcion = fecha;
	}

	public void setSalida(SalidaTuristicaP salida) {
		this.salida = salida;
	}

	public void setTurista(TuristaP p) {
		this.turista = p;
	}

}
