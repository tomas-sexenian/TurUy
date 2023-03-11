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
import excepciones.EmailRepetidoException;
import excepciones.EntidadRepetidaException;
import excepciones.NickameRepetidoException;
import excepciones.NoExisteEntidadException;
import excepciones.NoExistePaqueteException;
import excepciones.PaqueteCompradoException;
import logica.DatosPrueba;
import logica.EstadoActividad;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataCompra;
import logica.datatypes.DataPaqueteActividades;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorUsuario;
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
					new ArrayList<String>(Arrays.asList("Gastronomia")), "", null, 0);
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
		String nomAct = "Un nombre 5";
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
			// assertTrue(icActividades.obtenerActividades("Rocha").size() == 2);
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
			icActividades.crearActividad("Rocha", "", "Nombre 1", 1, 100, "", "washington",
					new ArrayList<String>(Arrays.asList("Gastronomia")));
			icActividades.aceptarActividad("Nombre 1");
		} catch (ActividadRepetidaException e) {
			// ignored
		} catch (ActividadNoExisteException e) {
			// TODO Auto-generated catch block
		}
		try {
			assertTrue(icActividades.obtenerActividadesConfirmadas("Rocha", "Gastronomia").size() > 0);
		} catch (ActividadNoExisteException e) {
			// TODO Auto-generated catch block
		}
	}

	@Test
	void testVideoActividad() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();

		String dpto = "Montevideo";
		String nomAct = "Un nombre 3";
		String desc = "Descripcion de actividad";
		int duracion = 2;
		double costo = 40.5;
		String ciudad = "Montevideo";
		LocalDate fecha = LocalDate.of(2022, 8, 1);
		String prov = "eldiez";

		try {
			icActividades.crearActividad(dpto, desc, nomAct, duracion, costo, ciudad, fecha, prov,
					new ArrayList<String>(Arrays.asList("Gastronomia")), "", "video", 0);
			boolean videoOK = icActividades.consultarActividad("Un nombre 3").getVideo().equals("video");
			if (!videoOK) {
				fail("El video seteado para la actividad no coincide con el obtenido");
			}
		} catch (ActividadRepetidaException | ActividadNoExisteException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testVIsitas() {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		String dpto = "Montevideo";
		String nomAct = "Un nombre 6";
		String desc = "Descripcion de actividad";
		int duracion = 2;
		double costo = 40.5;
		String ciudad = "Montevideo";
		LocalDate fecha = LocalDate.of(2022, 8, 1);
		String prov = "eldiez";

		try {
			icActividades.crearActividad(dpto, desc, nomAct, duracion, costo, ciudad, fecha, prov,
					new ArrayList<String>(Arrays.asList("Gastronomia")), "", "video", 0);
			int cant = icActividades.updateVisitsActividad(nomAct);
			assertEquals(cant, 1);

		} catch (ActividadRepetidaException err) {
			err.printStackTrace();
		}
	}

	@Test
	void testFavoritearActividad() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icActividades = fabrica.getIControladorActividades();
		IControladorUsuario icUsuarios = fabrica.getIControladorUsuario();

		String dpto = "Montevideo";
		String nomAct = "Un nombre 4";
		String desc = "Descripcion de actividad";
		int duracion = 2;
		double costo = 40.5;
		String ciudad = "Montevideo";
		LocalDate fecha = LocalDate.of(2022, 8, 1);
		String prov = "eldiez";

		try {
			icActividades.crearActividad(dpto, desc, nomAct, duracion, costo, ciudad, fecha, prov,
					new ArrayList<String>(Arrays.asList("Gastronomia")), "", "", 0);
			icUsuarios.marcarFavorita("lachiqui", "Un nombre 4");
			boolean favoritaOK = icActividades.consultarActividad("Un nombre 4").getCantFavoritos() == 1;
			icUsuarios.desmarcarFavorita("lachiqui", "Un nombre 4");
			boolean desfavoritaOK = icActividades.consultarActividad("Un nombre 4").getCantFavoritos() == 0;
			if (!(favoritaOK || desfavoritaOK)) {
				fail("Se creo una actividad con 0 favoritos, se la favoriteo y desfavoriteo y su total de favoritos no volvio a 0");
			}
		} catch (ActividadRepetidaException | ActividadNoExisteException e) {
			e.printStackTrace();
		}
	}

	@Test
	void compraPaquete() {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuarios = fabrica.getIControladorUsuario();

		String nick = "nickname";
		String email = "email";
		String nombre = "nombre";
		String apellido = "apellido";
		String nacionalidad = "nac";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {

			icUsuarios.crearTurista(nick, nombre, apellido, email, fechaNac, nacionalidad, password, "");
			icUsuarios.compraTuristaPaquete(nick, "Disfrutar Rocha", 1);
			List<DataCompra> compras = icUsuarios.obtenerComprasTurista(nick);
			assertTrue(compras.size() == 1);

		} catch (EmailRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PaqueteCompradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
