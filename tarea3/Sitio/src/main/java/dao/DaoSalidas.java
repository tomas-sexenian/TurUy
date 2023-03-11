package dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import servidor.ActividadNoExisteException_Exception;
import servidor.DataActividadTuristica;
import servidor.InscripcionRepetidaException_Exception;
import servidor.NoExisteEntidadException_Exception;
import servidor.NoHayCapacidadException_Exception;
import servidor.PaqueteVencidoException_Exception;
import servidor.PublicadorSalidas;
import servidor.PublicadorSalidasService;
import servidor.SalidaNoExisteException_Exception;
import servidor.SalidaRepetidaException_Exception;

public class DaoSalidas {

	private PublicadorSalidasService publicadorSalidasService;
	private PublicadorSalidas publicadorSalidas;

	public DaoSalidas() {
		publicadorSalidasService = new PublicadorSalidasService();
		publicadorSalidas = publicadorSalidasService.getPublicadorSalidasPort();
	}

	public void crearSalida(String nombre, int cantMaxTuristas, String lugarSalida, LocalDate fechaSalida,
			String horaSalida, String actividad, String newFileNameSalida)
			throws ActividadNoExisteException_Exception, SalidaRepetidaException_Exception {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String fechaSalidaString = fechaSalida.format(formatters);
		publicadorSalidas.crearSalida(nombre, cantMaxTuristas, lugarSalida, fechaSalidaString, horaSalida, actividad,
				newFileNameSalida);
	}

	public List<servidor.DataSalidaTuristica> obtenerSalidas(String actividad) {
		List<servidor.DataSalidaTuristica> salidas = new ArrayList<servidor.DataSalidaTuristica>();
		try {
			for (servidor.DataSalidaTuristica salidaTuristica : publicadorSalidas.getSalidas().getItem()) {
				if (salidaTuristica.getActividad().equals(actividad)) {
					salidas.add(salidaTuristica);
				}
			}
		} catch (SalidaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salidas;
	}

	// solo da las salidas de actividades confirmadas
	public List<servidor.DataSalidaTuristica> obtenerSalidas() {
		DaoActividades daoActividades = new DaoActividades();
		List<DataActividadTuristica> actividades = daoActividades.obtenerActividades("", "");
		List<servidor.DataSalidaTuristica> salidas = new ArrayList<>();
		for (DataActividadTuristica dact : actividades) {
			List<servidor.DataSalidaTuristica> salidasAct = dact.getSalidas();
			for (servidor.DataSalidaTuristica dtsal : salidasAct) {
				salidas.add(dtsal);
			}
		}
		return salidas;
	}

	public List<servidor.DataSalidaTuristica> obtenerSalidasVigentes(String actividad) {
		List<servidor.DataSalidaTuristica> salidas = new ArrayList<>();
		try {
			salidas = publicadorSalidas.getSalidasVigentes(actividad).getItem();
		} catch (SalidaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salidas;
	}

	public servidor.DataSalidaTuristica obtenerSalida(String nombre) {
		try {
			return publicadorSalidas.consultarSalida(nombre);
		} catch (SalidaNoExisteException_Exception | ActividadNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateVisitsSalida(String nombre) {
		publicadorSalidas.updateVisitsSalida(nombre);
	}

	public void inscribirTuristaSalida(String nickname, String nombreSalida, int cantTuristas)
			throws InscripcionRepetidaException_Exception, NoExisteEntidadException_Exception,
			NoHayCapacidadException_Exception {
		publicadorSalidas.inscribirTuristaSalida(nickname, nombreSalida, cantTuristas);
	}

	public void inscribirTuristaSalidaPaquete(String nickname, String nombreSalida, String nomPaquete, int cantTuristas)
			throws ActividadNoExisteException_Exception, InscripcionRepetidaException_Exception,
			NoExisteEntidadException_Exception, NoHayCapacidadException_Exception, PaqueteVencidoException_Exception {
		publicadorSalidas.inscribirTuristaSalidaPaquete(nickname, nombreSalida, nomPaquete, cantTuristas);
	}
}
