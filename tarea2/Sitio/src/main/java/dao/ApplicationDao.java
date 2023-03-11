package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import excepciones.ActividadNoExisteException;
import excepciones.DepartamentoNoExisteException;
import excepciones.NoExisteEntidadException;
import excepciones.SalidaNoExisteException;
import excepciones.UsuarioNoExisteException;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataCompra;
import logica.datatypes.DataDepartamento;
import logica.datatypes.DataInscripcion;
import logica.datatypes.DataPaqueteActividades;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataSalidaTuristica;
import logica.datatypes.DataTurista;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorDepartamentos;
import logica.interfaces.IControladorSalidas;
import logica.interfaces.IControladorUsuario;

public class ApplicationDao {

	public List<String> obtenerDepartamentos() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorDepartamentos icd = fabrica.getIControladorDepartamentos();
		List<String> listaDepartamentos = new ArrayList<String>();
		try {
			DataDepartamento[] departamentos = icd.obtenerDepartamentos();
			for (DataDepartamento depto : departamentos) {
				listaDepartamentos.add(depto.toString());
			}

		} catch (DepartamentoNoExisteException e) {
			// TODO Auto-generated catch block
		}
		return listaDepartamentos.stream().sorted().collect(Collectors.toList());
	}

	public List<String> obtenerCategorias() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades iControladorActividades = fabrica.getIControladorActividades();
		List<String> categorias = iControladorActividades.obtenerCategorias();
		return categorias;
	}

	public List<DataActividadTuristica> obtenerActividades(String departamento, String categoria) {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades iControladorActividades = fabrica.getIControladorActividades();
		List<DataActividadTuristica> actividades = new ArrayList<DataActividadTuristica>();
		try {
			if (departamento.equals("") && categoria.equals("")) {

				actividades = iControladorActividades.obtenerActividadesConfirmadas();
			} else if (categoria.equals("") && !departamento.equals("")) {
				actividades = iControladorActividades.obtenerActividadesConfirmadas(departamento);
			} else {
				actividades = iControladorActividades.obtenerActividadesConfirmadas(departamento, categoria);
			}

		} catch (ActividadNoExisteException e) {
			// TODO Auto-generated catch block
		}
		return actividades;
	}

	public DataActividadTuristica obtenerActividadTuristica(String nombre) {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades iControladorActividades = fabrica.getIControladorActividades();
		try {
			DataActividadTuristica actividad = iControladorActividades.consultarActividad(nombre);
			return actividad;
		} catch (ActividadNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<DataPaqueteActividades> obtenerPaquetes() {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades iControladorActividades = fabrica.getIControladorActividades();
		List<DataPaqueteActividades> paquetes = new ArrayList<DataPaqueteActividades>();
		try {
			paquetes = iControladorActividades.obtenerPaquetes();
		} catch (NoExisteEntidadException e) {
			// TODO Auto-generated catch block
		}
		return paquetes;
	}

	public DataPaqueteActividades obtenerPaquete(String nombre) {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades iControladorActividades = fabrica.getIControladorActividades();
		DataPaqueteActividades paquete;
		try {
			paquete = iControladorActividades.consultarPaquete(nombre);
			return paquete;

		} catch (NoExisteEntidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<DataSalidaTuristica> obtenerSalidas(String actividad) {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorSalidas iControladorSalidas = fabrica.getIControladorSalidas();
		List<DataSalidaTuristica> salidas = new ArrayList<DataSalidaTuristica>();
		try {
			salidas = iControladorSalidas.getSalidas(actividad);

		} catch (SalidaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salidas;
	}

	public List<DataSalidaTuristica> obtenerSalidas() {
		List<DataActividadTuristica> actividades = obtenerActividades("", "");
		List<DataSalidaTuristica> salidas = new ArrayList<>();
		for (DataActividadTuristica dact : actividades) {
			Map<String, DataSalidaTuristica> mapSal = dact.getSalidas();
			for (DataSalidaTuristica dtsal : mapSal.values()) {
				salidas.add(dtsal);
			}
		}
		return salidas;
	}

	public List<DataSalidaTuristica> obtenerSalidasVigentes(String actividad) {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorSalidas iControladorSalidas = fabrica.getIControladorSalidas();
		List<DataSalidaTuristica> salidas = new ArrayList<>();
		try {
			salidas = iControladorSalidas.getSalidasVigentes(actividad);
		} catch (SalidaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salidas;
	}

	public DataSalidaTuristica obtenerSalida(String nombre) {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorSalidas iControladorSalidas = fabrica.getIControladorSalidas();
		try {
			return iControladorSalidas.consultarSalida(nombre);
		} catch (SalidaNoExisteException | ActividadNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<DataProveedor> obtenerProveedores() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario iControladorUsuario = fabrica.getIControladorUsuario();
		try {
			List<DataProveedor> proveedores = new ArrayList<DataProveedor>(
					Arrays.asList(iControladorUsuario.getProveedores()));
			return proveedores;
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public DataProveedor obtenerProveedor(String nickname) {
		List<DataProveedor> proveedores = this.obtenerProveedores();
		for (DataProveedor dp : proveedores) {
			if (dp.getNickname().equals(nickname)) {
				return dp;
			}
		}
		return null;
	}

	public List<DataActividadTuristica> obternerActividadesProveedor(String username) {
		Fabrica fabrica = Fabrica.getInstance();

		IControladorUsuario iControladorUsuario = fabrica.getIControladorUsuario();
		List<DataActividadTuristica> actividades = new ArrayList<>();
		try {
			DataProveedor proveedor = iControladorUsuario.verInfoProveedor(username);
			actividades = Arrays.asList(proveedor.getActividades());
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actividades;
	}

	public List<DataSalidaTuristica> obtenerSalidasProveedor(String username) {
		// Fabrica fabrica = Fabrica.getInstance();

		// IControladorUsuario iControladorUsuario = fabrica.getIControladorUsuario();
		List<DataSalidaTuristica> salidas = new ArrayList<>();
		List<DataActividadTuristica> actividades = this.obternerActividadesProveedor(username);
		for (DataActividadTuristica dat : actividades) {
			List<DataSalidaTuristica> sals = new ArrayList<DataSalidaTuristica>(dat.getSalidas().values());
			for (DataSalidaTuristica dst : sals) {
				salidas.add(dst);
			}
		}
		return salidas;
	}

	public List<DataTurista> obtenerTuristas() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario iControladorUsuario = fabrica.getIControladorUsuario();
		try {
			List<DataTurista> turistas = new ArrayList<DataTurista>(Arrays.asList(iControladorUsuario.getTuristas()));
			return turistas;
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public DataTurista obtenerTurista(String nickname) {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario iControladorUsuario = fabrica.getIControladorUsuario();
		try {
			DataTurista dtu = iControladorUsuario.verInfoTurista(nickname);
			return dtu;
		} catch (UsuarioNoExisteException e) {
			return null;
		}
	}

	public List<DataPaqueteActividades> obternerPaquetesTurista(String username) {
		Fabrica fabrica = Fabrica.getInstance();

		IControladorUsuario iControladorUsuario = fabrica.getIControladorUsuario();
		List<DataPaqueteActividades> paquetes = new ArrayList<>();
		try {
			DataTurista turista = iControladorUsuario.verInfoTurista(username);
			List<DataCompra> compras = turista.getCompras();
			for (DataCompra compra : compras) {
				paquetes.add(compra.getPaquete());
			}
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paquetes;
	}

	public List<DataPaqueteActividades> obternerPaquetesVigentesTurista(String username) {
		Fabrica fabrica = Fabrica.getInstance();

		IControladorUsuario iControladorUsuario = fabrica.getIControladorUsuario();
		List<DataPaqueteActividades> paquetes = new ArrayList<>();
		try {
			DataTurista turista = iControladorUsuario.verInfoTurista(username);
			List<DataCompra> compras = turista.getCompras();
			for (DataCompra compra : compras) {
				if (compra.getValidez().isAfter(LocalDate.now())) {
					paquetes.add(compra.getPaquete());
				}
			}
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paquetes;
	}

	public List<DataCompra> obtenerComprasTurista(String username) {
		Fabrica fabrica = Fabrica.getInstance();

		IControladorUsuario iControladorUsuario = fabrica.getIControladorUsuario();
		List<DataCompra> compras = new ArrayList<>();
		try {
			DataTurista turista = iControladorUsuario.verInfoTurista(username);
			compras = turista.getCompras();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return compras;
	}

	public List<DataInscripcion> obternerInscripcionesTurista(String username) {
		Fabrica fabrica = Fabrica.getInstance();

		IControladorUsuario iControladorUsuario = fabrica.getIControladorUsuario();
		List<DataInscripcion> res = new ArrayList<>();
		try {
			DataTurista turista = iControladorUsuario.verInfoTurista(username);
			res = turista.getInscripciones();

		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
