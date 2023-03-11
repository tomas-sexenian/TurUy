package dao;

import java.util.ArrayList;
import java.util.List;

import net.java.dev.jaxb.array.StringArray;
import servidor.ActividadNoExisteException_Exception;
import servidor.ActividadRepetidaException_Exception;
import servidor.DataActividadTuristica;
import servidor.DataActividadTuristicaP;
import servidor.NoExisteEntidadException_Exception;
import servidor.PublicadorActividades;
import servidor.PublicadorActividadesService;

public class DaoActividades {

	private PublicadorActividadesService publicadorActividadesService;
	private PublicadorActividades publicadorActividades;

	public DaoActividades() {
		publicadorActividadesService = new PublicadorActividadesService(); //HOLA
		publicadorActividades = publicadorActividadesService.getPublicadorActividadesPort();
	}

	public void crearActividad(String dpto, String descripcion, String nombre, int duracion, double costo,
			String ciudad, String prov, List<String> categorias, String newFileNameAct, String videoURL)
			throws ActividadRepetidaException_Exception {
		StringArray array = new StringArray();
		array.setItem(categorias);
		publicadorActividades.crearActividad(dpto, descripcion, nombre, duracion, costo, ciudad, prov, array,
				newFileNameAct, videoURL);

	}

	public List<String> obtenerCategorias() {
		List<String> categorias = publicadorActividades.obtenerCategorias().getItem();
		return categorias;
	}

	public List<DataActividadTuristica> obtenerActividades(String departamento, String categoria) {
		List<DataActividadTuristica> actividades = new ArrayList<>();
		try {
			if (departamento.equals("") && categoria.equals("")) {
				actividades = publicadorActividades.obtenerActividadesConfirmadas().getItem();//HOLA
			} else if (categoria.equals("") && !departamento.equals("")) {
				actividades = publicadorActividades.obtenerActividadesConfirmadasDepartamento(departamento).getItem();
			} else {
				actividades = publicadorActividades
						.obtenerActividadesConfirmadasDepartamentoCategoria(departamento, categoria).getItem();
			}

		} catch (ActividadNoExisteException_Exception e) {
			// TODO Auto-generated catch block
		}
		return actividades;
	}

	public DataActividadTuristica obtenerActividadTuristica(String nombre) {
		try {
			servidor.DataActividadTuristica actividad = publicadorActividades.consultarActividad(nombre);
			return actividad;
		} catch (ActividadNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<servidor.DataPaqueteActividades> obtenerPaquetes() {
		List<servidor.DataPaqueteActividades> paquetes = new ArrayList<servidor.DataPaqueteActividades>();
		try {
			paquetes = publicadorActividades.obtenerPaquetes().getItem();
		} catch (NoExisteEntidadException_Exception e) {
			// TODO Auto-generated catch block
		}
		return paquetes;
	}

	public servidor.DataPaqueteActividades obtenerPaquete(String nombre) {
		servidor.DataPaqueteActividades paquete;
		try {
			paquete = publicadorActividades.consultarPaquete(nombre);
			return paquete;

		} catch (NoExisteEntidadException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateVisitsActividad(String nombre) {
		publicadorActividades.updateVisitsActividad(nombre);
	}

	public void finalizarActividad(String nombre) throws ActividadNoExisteException_Exception {
		publicadorActividades.finalizarActividad(nombre);
	}
	
	public List<servidor.DataActividadTuristica> obtenerActividadesNoFinalizadas(String nickname){
		List<servidor.DataActividadTuristica> activ = new ArrayList<servidor.DataActividadTuristica>();
		activ = publicadorActividades.obtenerActividadesNoFinalizadas(nickname).getItem();
		return activ;
	}
	
	public List<servidor.DataActividadTuristicaP> obtenerActividadesFinalizadas(String nickname, String nombreActividad){
		List<DataActividadTuristicaP> activ = new ArrayList<servidor.DataActividadTuristicaP>();
		activ = publicadorActividades.obtenerActividadesFinalizadas(nickname, nombreActividad).getItem();
		return activ;
	}
}
