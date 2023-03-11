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
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		List<DataActividadTuristica> actividadTuristicas = cActividades.obtenerActividades();
		ManejadorActividades manejadorActividades = ManejadorActividades.getinstance();
		for (DataActividadTuristica dataActividadTuristica : actividadTuristicas) {
			manejadorActividades.removerActividad(dataActividadTuristica.getNombre());
		}
	}

	@Test
	void testCrearActividadOk() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		String dpto = "Montevideo";
		String nomAct = "Un nombre";
		String desc = "Descripcion de actividad";
		int duracion = 2;
		double costo = 40.5;
		String ciudad = "Montevideo";
		LocalDate fecha = LocalDate.of(2022, 8, 1);
		String prov = "eldiez";
		try {
			cActividades.crearActividad(dpto, desc, nomAct, duracion, costo, ciudad, fecha, prov,
					new ArrayList<String>(Arrays.asList("Categoria de prueba")));
		} catch (ActividadRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		try {
			DataActividadTuristica da = cActividades.consultarActividad(nomAct);
			assertEquals(dpto, da.getDepartamento().getNombre());
			assertEquals(nomAct, da.getNombre());
			assertEquals(desc, da.getDescripcion());
			assertEquals(duracion, da.getDuracion());
			assertEquals(costo, da.getReal());
			assertEquals(ciudad, da.getCiudad());
			assertEquals(fecha, da.getFechaAlta());

		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testCrearActividadRepetida() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		assertThrows(ActividadRepetidaException.class, () -> {
			cActividades.crearActividad("Rocha", "Descripcion", "Degusta", 3, 50, "Rocha", LocalDate.of(2022, 1, 20),
					"washington", new ArrayList<String>(Arrays.asList("Categoria de prueba")));
		});
	}

	@Test
	void testModificarActividadOk() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		String dpto = "Montevideo";
		String nomAct = "Degusta";
		int duracion = 2;
		double costo = 40.5;
		String ciudad = "Montevideo";
		LocalDate fecha = LocalDate.of(2022, 8, 1);
		try {
			cActividades.consultarActividad(nomAct).toString();
			cActividades.modificarActividad(dpto, nomAct, duracion, costo, ciudad, fecha);
		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		try {
			DataActividadTuristica da = cActividades.consultarActividad(nomAct);
			assertEquals(dpto, da.getDepartamento().getNombre());
			assertEquals(nomAct, da.getNombre());
			assertEquals(duracion, da.getDuracion());
			assertEquals(costo, da.getReal());
			assertEquals(ciudad, da.getCiudad());
			assertEquals(fecha, da.getFechaAlta());

		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

	}

	@Test
	void testObtenerActividades() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		try {
			assertTrue(cActividades.obtenerActividades().size() > 0);
		} catch (ActividadNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerActividadesDpto() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		try {
			assertTrue(cActividades.obtenerActividades("Artigas").size() == 0);
			assertTrue(cActividades.obtenerActividades("Rocha").size() == 2);
		} catch (ActividadNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testCrearPaquete() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		assertThrows(EntidadRepetidaException.class, () -> {
			cActividades.crearPaquete("Disfrutar Rocha", "Descripcion", 60, 20, LocalDate.of(2022, 8, 19));
		});
	}

	@Test
	void testModificarPaquete() {
		String nom = "Disfrutar Rocha";
		String desc = "Descripcion";
		int validez = 90;
		double descuento = 10;
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		try {
			cActividades.modificarPaquete(nom, desc, validez, descuento);
		} catch (NoExistePaqueteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		try {
			DataPaqueteActividades dp = cActividades.consultarPaquete("Disfrutar Rocha");
			assertEquals(dp.getNombre(), nom);
			assertEquals(dp.getDescripcion(), desc);
			assertEquals(dp.getValidez(), validez);
			assertEquals(dp.getDescuento(), descuento);
		} catch (NoExisteEntidadException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerPaquetes() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		try {
			assertTrue(cActividades.obtenerPaquetes().size() > 0);
		} catch (NoExisteEntidadException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerPaquetesActividad() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		try {
			assertTrue(cActividades.obtenerPaquetes("Degusta").size() > 0);
		} catch (NoExisteEntidadException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testAgregarActividadPaqueteFaltaPaquete() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		assertThrows(NoExistePaqueteException.class, () -> {
			cActividades.agregarActividadPaquete("algo", "Degusta");
		});
	}

	@Test
	void testAgregarActividadPaqueteFaltaActividad() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades cActividades = fabrica.getIControladorActividades();
		assertThrows(ActividadNoExisteException.class, () -> {
			cActividades.agregarActividadPaquete("Disfrutar Rocha", "algo");
		});
	}
}
