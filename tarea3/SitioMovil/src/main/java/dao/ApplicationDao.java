package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import servidor.ActividadNoExisteException_Exception;
import servidor.DataActividadTuristica;
import servidor.DataDepartamento;
import servidor.DataSalidaTuristica;
import servidor.DataTurista;
import servidor.DepartamentoNoExisteException_Exception;
import servidor.PublicadorActividades;
import servidor.PublicadorActividadesService;
import servidor.PublicadorDepartamentos;
import servidor.PublicadorDepartamentosService;
import servidor.PublicadorSalidas;
import servidor.PublicadorSalidasService;
import servidor.PublicadorUsuario;
import servidor.PublicadorUsuarioService;
import servidor.SalidaNoExisteException_Exception;
import servidor.UsuarioNoExisteException_Exception;

public class ApplicationDao {

	private PublicadorActividadesService publicadorActividadesService;
	private PublicadorActividades publicadorActividades;
	private PublicadorSalidasService publicadorSalidasService;
	private PublicadorSalidas publicadorSalidas;
	private PublicadorDepartamentosService publicadorDepartamentosService;
	private PublicadorDepartamentos publicadorDepartamentos;
	private PublicadorUsuarioService publicadorUsuarioService;
	private PublicadorUsuario publicadorUsuario;

	public ApplicationDao() {
		publicadorActividadesService = new PublicadorActividadesService();
		publicadorActividades = publicadorActividadesService.getPublicadorActividadesPort();
		publicadorSalidasService = new PublicadorSalidasService();
		publicadorSalidas = publicadorSalidasService.getPublicadorSalidasPort();
		publicadorUsuarioService = new PublicadorUsuarioService();
		publicadorUsuario = publicadorUsuarioService.getPublicadorUsuarioPort();
		publicadorDepartamentosService = new PublicadorDepartamentosService();
		publicadorDepartamentos = publicadorDepartamentosService.getPublicadorDepartamentosPort();
	}

	public List<String> obtenerDepartamentos() {
		List<String> listaDepartamentos = new ArrayList<String>();
		try {
			List<DataDepartamento> departamentos = publicadorDepartamentos.obtenerDepartamentos().getItem();
			for (DataDepartamento depto : departamentos) {
				listaDepartamentos.add(depto.getNombre());
			}
		} catch (DepartamentoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
		}
		return listaDepartamentos.stream().sorted().collect(Collectors.toList());
	}

	public List<String> obtenerCategorias() {
		List<String> categorias = publicadorActividades.obtenerCategorias().getItem();
		return categorias;
	}

	public List<DataActividadTuristica> obtenerActividades(String departamento, String categoria) {
		List<DataActividadTuristica> actividades = new ArrayList<>();
		try {
			if (departamento.equals("") && categoria.equals("")) {
				actividades = publicadorActividades.obtenerActividadesConfirmadas().getItem();
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
			DataActividadTuristica actividad = publicadorActividades.consultarActividad(nombre);
			return actividad;
		} catch (ActividadNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<DataSalidaTuristica> obtenerSalidas(String actividad) {
		List<DataSalidaTuristica> salidas = new ArrayList<DataSalidaTuristica>();
		try {
			for (DataSalidaTuristica salidaTuristica : publicadorSalidas.getSalidas().getItem()) {
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

	public List<DataSalidaTuristica> obtenerSalidas() {
		List<DataActividadTuristica> actividades = obtenerActividades("", "");
		List<DataSalidaTuristica> salidas = new ArrayList<>();
		for (DataActividadTuristica dact : actividades) {
			List<DataSalidaTuristica> salidasAct = dact.getSalidas();
			for (DataSalidaTuristica dtsal : salidasAct) {
				salidas.add(dtsal);
			}
		}
		return salidas;
	}

	public List<DataSalidaTuristica> obtenerSalidasVigentes(String actividad) {
		List<DataSalidaTuristica> salidas = new ArrayList<>();
		try {
			salidas = publicadorSalidas.getSalidasVigentes(actividad).getItem();
		} catch (SalidaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salidas;
	}

	public DataSalidaTuristica obtenerSalida(String nombre) {
		try {
			return publicadorSalidas.consultarSalida(nombre);
		} catch (SalidaNoExisteException_Exception | ActividadNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public DataTurista obtenerTurista(String nickname) throws UsuarioNoExisteException_Exception {
		if (nickname == null) {
			nickname = "";
		}
		try {
			return publicadorUsuario.verInfoTurista(nickname);
		} catch (Exception e) {
			// ignore
		}
		return null;

	}

	public void updateVisitsActividad(String nombre) {
		publicadorActividades.updateVisitsActividad(nombre);
	}

	public void updateVisitsSalida(String nombre) {
		publicadorSalidas.updateVisitsSalida(nombre);
	}

	public String obtenerPassword(String nickname) {
		return publicadorUsuario.obtenerPassword(nickname);
	}

	public String obtenerNickname(String username) {
		return publicadorUsuario.obtenerNickname(username);
	}

}
