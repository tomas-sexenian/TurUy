package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ActividadNoExisteException;
import excepciones.ActividadRepetidaException;
import excepciones.EntidadRepetidaException;
import excepciones.NoExisteEntidadException;
import excepciones.NoExistePaqueteException;
import logica.DatosPrueba;
import logica.EstadoActividad;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataPaqueteActividades;
import logica.interfaces.IControladorActividades;
import logica.manejadores.ManejadorActividades;

class ControladorActividadesTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatosPrueba.cargarDatos();
	}

	@AfterAll
	static void setUpAfterClass() throws Exception {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		List<DataActividadTuristica> actividadTuristicas = icActividades.obtenerActividades();
		ManejadorActividades manejadorActividades = ManejadorActividades.getinstance();
		for (DataActividadTuristica dataActividadTuristica : actividadTuristicas) {
			manejadorActividades.removerActividad(dataActividadTuristica.getNombre());
		}
	}

	@Test
	void testCrearActividadFechaOk() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		String dpto = "Montevideo";
		String nomAct = "Un nombre";
		String desc = "Descripcion de actividad";
		int duracion = 2;
		double costo = 40.5;
		String ciudad = "Montevideo";
		LocalDate fecha = LocalDate.of(2022, 8, 1);
		String prov = "eldiez";
		try {
			icActividades.crearActividad(dpto, desc, nomAct, duracion, costo, ciudad, fecha, prov,
					new ArrayList<String>(Arrays.asList("Gastronomia")));
		} catch (ActividadRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		try {
			DataActividadTuristica dtActividad = icActividades.consultarActividad(nomAct);
			assertEquals(dpto, dtActividad.getDepartamento().getNombre());
			assertEquals(nomAct, dtActividad.getNombre());
			assertEquals(desc, dtActividad.getDescripcion());
			assertEquals(duracion, dtActividad.getDuracion());
			assertEquals(costo, dtActividad.getCosto());
			assertEquals(ciudad, dtActividad.getCiudad());
			assertEquals(fecha, dtActividad.getFechaAlta());

		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testCrearActividadImgOk() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		String dpto = "Montevideo";
		String nomAct = "Un nombre 2";
		String desc = "Descripcion de actividad";
		int duracion = 2;
		double costo = 40.5;
		String ciudad = "Montevideo";
		String prov = "eldiez";
		try {
			icActividades.crearActividad(dpto, desc, nomAct, duracion, costo, ciudad, prov,
					new ArrayList<String>(Arrays.asList("Gastronomia")), "");
		} catch (ActividadRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		try {
			DataActividadTuristica dtActividad = icActividades.consultarActividad(nomAct);
			assertEquals(dpto, dtActividad.getDepartamento().getNombre());
			assertEquals(nomAct, dtActividad.getNombre());
			assertEquals(desc, dtActividad.getDescripcion());
			assertEquals(duracion, dtActividad.getDuracion());
			assertEquals(costo, dtActividad.getCosto());
			assertEquals(ciudad, dtActividad.getCiudad());
		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testCrearActividadOk() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		String dpto = "Montevideo";
		String nomAct = "Un nombre 3";
		String desc = "Descripcion de actividad";
		int duracion = 2;
		double costo = 40.5;
		String ciudad = "Montevideo";
		LocalDate fecha = LocalDate.now();
		String prov = "eldiez";
		try {
			icActividades.crearActividad(dpto, desc, nomAct, duracion, costo, ciudad, prov,
					new ArrayList<String>(Arrays.asList("Gastronomia")));
		} catch (ActividadRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		try {
			DataActividadTuristica dtActividad = icActividades.consultarActividad(nomAct);
			assertEquals(dpto, dtActividad.getDepartamento().getNombre());
			assertEquals(nomAct, dtActividad.getNombre());
			assertEquals(desc, dtActividad.getDescripcion());
			assertEquals(duracion, dtActividad.getDuracion());
			assertEquals(costo, dtActividad.getCosto());
			assertEquals(ciudad, dtActividad.getCiudad());
			assertEquals(fecha, dtActividad.getFechaAlta());

		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void rechazarActividadOk() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		String nomAct = "Degusta";
		EstadoActividad estado = EstadoActividad.RECHAZADA;
		try {
			icActividades.rechazarActividad(nomAct);
		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		try {
			DataActividadTuristica dtActividad = icActividades.consultarActividad(nomAct);
			assertEquals(estado, dtActividad.getEstadoActividad());
		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void rechazarActividadFail() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		assertThrows(ActividadNoExisteException.class, () -> {
			icActividades.rechazarActividad("No existe");
		});
	}

	@Test
	void aceptarActividadFail() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		assertThrows(ActividadNoExisteException.class, () -> {
			icActividades.aceptarActividad("No existe");
		});
	}

	@Test
	void testCrearActividadRepetida() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		assertThrows(ActividadRepetidaException.class, () -> {
			icActividades.crearActividad("Rocha", "Descripcion", "Degusta", 3, 50, "Rocha", LocalDate.of(2022, 1, 20),
					"washington", new ArrayList<String>(Arrays.asList("Gastronomia")));
		});
	}

	@Test
	void testModificarActividadOk() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		String dpto = "Montevideo";
		String nomAct = "Degusta";
		int duracion = 2;
		double costo = 40.5;
		String ciudad = "Montevideo";
		LocalDate fecha = LocalDate.of(2022, 8, 1);
		try {
			icActividades.consultarActividad(nomAct).toString();
			icActividades.modificarActividad(dpto, nomAct, duracion, costo, ciudad, fecha);
		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		try {
			DataActividadTuristica dtActividad = icActividades.consultarActividad(nomAct);
			assertEquals(dpto, dtActividad.getDepartamento().getNombre());
			assertEquals(nomAct, dtActividad.getNombre());
			assertEquals(duracion, dtActividad.getDuracion());
			assertEquals(costo, dtActividad.getCosto());
			assertEquals(ciudad, dtActividad.getCiudad());
			assertEquals(fecha, dtActividad.getFechaAlta());

		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

	}



	@Test
	void testObtenerActividadesConfirmadas() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		try {
			assertTrue(icActividades.obtenerActividadesConfirmadas().size() > 0);
		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerActividadesConfirmadasDepto() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		try {
			assertTrue(icActividades.obtenerActividadesConfirmadas("Rocha").size() > 0);
		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerActividadesDpto() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		try {
			assertTrue(icActividades.obtenerActividades("Artigas").size() == 0);
			//assertTrue(icActividades.obtenerActividades("Rocha").size() == 2);
		} catch (ActividadNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testCrearPaquete() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		assertThrows(EntidadRepetidaException.class, () -> {
			icActividades.crearPaquete("Disfrutar Rocha", "Descripcion", 60, 20, LocalDate.of(2022, 8, 19));
		});
	}

	@Test
	void testModificarPaquete() {
		String nom = "Disfrutar Rocha";
		String desc = "Descripcion";
		int validez = 90;
		double descuento = 10;
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		try {
			icActividades.modificarPaquete(nom, desc, validez, descuento);
		} catch (NoExistePaqueteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		try {
			DataPaqueteActividades dtPaquete = icActividades.consultarPaquete("Disfrutar Rocha");
			assertEquals(dtPaquete.getNombre(), nom);
			assertEquals(dtPaquete.getDescripcion(), desc);
			assertEquals(dtPaquete.getValidez(), validez);
			assertEquals(dtPaquete.getDescuento(), descuento);
		} catch (NoExisteEntidadException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerPaquetes() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		try {
			assertTrue(icActividades.obtenerPaquetes().size() > 0);
		} catch (NoExisteEntidadException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerPaquetesActividad() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		try {
			assertTrue(icActividades.obtenerPaquetes("Degusta").size() > 0);
		} catch (NoExisteEntidadException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testAgregarActividadPaqueteFaltaPaquete() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		assertThrows(NoExistePaqueteException.class, () -> {
			icActividades.agregarActividadPaquete("algo", "Degusta");
		});
	}

	@Test
	void testAgregarActividadPaqueteFaltaActividad() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		assertThrows(ActividadNoExisteException.class, () -> {
			icActividades.agregarActividadPaquete("Disfrutar Rocha", "algo");
		});
	}
	
	@Test
	void testObtenerActividadesConfirmadasDeptoCat() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		try {
			icActividades.crearActividad("Rocha", "", "Nombre 1", 1, 100, "", "washington", new ArrayList<String>(Arrays.asList("Gastronomia")));
			icActividades.aceptarActividad("Nombre 1");
		}catch(ActividadRepetidaException e) {
			//ignored
		} catch (ActividadNoExisteException e) {
			// TODO Auto-generated catch block
		}
		try {
			assertTrue(icActividades.obtenerActividadesConfirmadas("Rocha", "Gastronomia").size() > 0);
		} catch (ActividadNoExisteException e) {
			// TODO Auto-generated catch block
		}
	}
}


