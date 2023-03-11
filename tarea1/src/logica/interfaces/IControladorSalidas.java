package logica.interfaces;

import java.time.LocalDate;
import java.util.List;

import excepciones.ActividadNoExisteException;
import excepciones.DepartamentoNoExisteException;
import excepciones.InscripcionRepetidaException;
import excepciones.NoExisteEntidadException;
import excepciones.NoHayCapacidadException;
import excepciones.SalidaNoExisteException;
import excepciones.SalidaRepetidaException;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataSalidaTuristica;

public interface IControladorSalidas {

	public abstract void crearSalida(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			LocalDate fechaSalida, int horaSalida, String actividad)
			throws SalidaRepetidaException, ActividadNoExisteException;

	public abstract DataActividadTuristica[] obtenerActividadesDepartamento(String departamento)
			throws DepartamentoNoExisteException;

	public abstract void modificarSalida(String nombre, int cantMaxTuristas, LocalDate fechaAlta, String lugarSalida,
			LocalDate fechaSalida, int horaSalida) throws SalidaNoExisteException;

	public abstract DataSalidaTuristica consultarSalida(String nombre)
			throws SalidaNoExisteException, ActividadNoExisteException;

	public abstract DataSalidaTuristica[] getSalidas() throws SalidaNoExisteException;

	public abstract List<DataSalidaTuristica> getSalidasVigentes(String actividad) throws SalidaNoExisteException;

	public void inscribirTuristaSalida(String nickname, String nombreSalida, int cantTuristas, LocalDate fecha)
			throws NoExisteEntidadException, InscripcionRepetidaException, NoHayCapacidadException;
}
