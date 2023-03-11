package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import excepciones.EmailRepetidoException;
import excepciones.NickameRepetidoException;
import excepciones.UsuarioNoExisteException;
import logica.Fabrica;
import logica.clases.Proveedor;
import logica.clases.Turista;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataInscripcion;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataTurista;
import logica.interfaces.IControladorUsuario;
import logica.manejadores.ManejadorUsuario;

class ControladorUsuarioTest {

	@AfterEach
	void setUpAfterClass() throws Exception {

		ManejadorUsuario maUsuario = ManejadorUsuario.getinstance();
		for (Turista t : maUsuario.getTuristas().values()) {
			maUsuario.removerTurista(t.getNickname());
		}
		for (Proveedor p : maUsuario.getProveedores().values()) {
			maUsuario.removerProveedor(p.getNickname());
		}

	}

	@Test
	void testCrearTuristaNickRepetido() {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		String nick = "nickname";
		String email = "email";
		String email2 = "email2";
		String nombre = "nombre";
		String apellido = "apellido";
		String nacionalidad = "uruguay";
		String password = "pwd";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		try {
			icUsuario.crearTurista(nick, nombre, apellido, email, fechaNac, nacionalidad, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(NickameRepetidoException.class, () -> {
			icUsuario.crearTurista(nick, nombre, apellido, email2, fechaNac, nacionalidad, password, "");
		});
	}

	@Test
	void testCrearTuristaEmailRepetido() {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		String nick2 = "nickname2";
		String nick3 = "nickname3";

		String email3 = "email3";
		String nombre = "nombre";
		String apellido = "apellido";
		String nacionalidad = "uruguay";
		String password = "pwd";

		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		try {
			icUsuario.crearTurista(nick2, nombre, apellido, email3, fechaNac, nacionalidad, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(EmailRepetidoException.class, () -> {
			icUsuario.crearTurista(nick3, nombre, apellido, email3, fechaNac, nacionalidad, password, "");
		});
	}

	@Test
	void testCrearProveedorNickRepetido() {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		String nick = "nickname4";
		String email4 = "email4";
		String email5 = "email5";
		String nombre = "nombre";
		String apellido = "apellido";
		String descripcion = "desc";
		String link = "link";
		String password = "pwd";

		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		try {
			icUsuario.crearProveedor(nick, nombre, apellido, email4, fechaNac, descripcion, link, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(NickameRepetidoException.class, () -> {
			icUsuario.crearProveedor(nick, nombre, apellido, email5, fechaNac, descripcion, link, password, "");
		});
	}

	@Test
	void testCrearProveedorEmailRepetido() {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		String nick5 = "nickname5";
		String nick6 = "nickname6";

		String email6 = "email6";
		String nombre = "nombre";
		String apellido = "apellido";
		String descripcion = "desc";
		String link = "link";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {
			icUsuario.crearProveedor(nick5, nombre, apellido, email6, fechaNac, descripcion, link, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(EmailRepetidoException.class, () -> {
			icUsuario.crearProveedor(nick6, nombre, apellido, email6, fechaNac, descripcion, link, password, "");
		});
	}

	@Test
	void testModificarTurista() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		String nick = "nickname7";
		String email = "email7";
		String nombre = "nombre";
		String nuevoNombre = "nuevoNombre";
		String apellido = "apellido";
		String nuevoApellido = "nuevoApellido";
		String nacionalidad = "uruguay";
		String nuevaNacionalidad = "argentina";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		LocalDate nuevafechaNac = LocalDate.of(2022, 7, 1);
		String password = "pwd";

		try {
			icUsuario.crearTurista(nick, nombre, apellido, email, fechaNac, nacionalidad, password, "");
			icUsuario.modificarTurista(nick, nuevoNombre, nuevoApellido, email, nuevafechaNac, nuevaNacionalidad);
			DataTurista turista = icUsuario.verInfoTurista(nick);
			assertEquals(turista.getNombre(), nuevoNombre);
			assertEquals(turista.getApellido(), nuevoApellido);
			assertEquals(turista.getNacimiento(), nuevafechaNac);
			assertEquals(turista.getNacionalidad(), nuevaNacionalidad);
			assertEquals(turista.getNickname(), nick);
			assertEquals(turista.getEmail(), email);
			assertTrue(turista.getInscripciones().isEmpty());
			assertTrue(turista.getCompras().isEmpty());

		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testModificarProveedor() {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		String nick = "nickname8";
		String email = "email8";
		String nombre = "nombre";
		String nuevoNombre = "nuevoNombre";
		String apellido = "apellido";
		String nuevoApellido = "nuevoApellido";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		LocalDate nuevafechaNac = LocalDate.of(2022, 7, 1);
		String descripcion = "desc";
		String nuevaDesc = "nuevaDesc";
		String link = "link";
		String nuevoLink = "nuevoLink";
		String password = "pwd";

		try {
			icUsuario.crearProveedor(nick, nombre, apellido, email, fechaNac, descripcion, link, password, "");
			icUsuario.modificarProveedor(nick, nuevoNombre, nuevoApellido, email, nuevafechaNac, nuevaDesc, nuevoLink);
			DataProveedor proveedor = icUsuario.verInfoProveedor(nick);
			assertEquals(proveedor.getNombre(), nuevoNombre);
			assertEquals(proveedor.getApellido(), nuevoApellido);
			assertEquals(proveedor.getNacimiento(), nuevafechaNac);
			assertEquals(proveedor.getDescripcion(), nuevaDesc);
			assertEquals(proveedor.getLink(), nuevoLink);
			assertEquals(proveedor.getEmail(), email);
			assertEquals(proveedor.getNickname(), nick);
			assertTrue(proveedor.getActividades().length == 0);
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testVerInfoTuristas() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		assertThrows(UsuarioNoExisteException.class, () -> {
			icUsuario.verInfoTurista("algo");
		});
	}

	@Test
	void testVerInfoProveedor() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		assertThrows(UsuarioNoExisteException.class, () -> {
			icUsuario.verInfoProveedor("algo");
		});
	}

	@Test
	void testGetTuristas() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		try {
			DataTurista[] turistas = icUsuario.getTuristas();
			assertTrue(turistas.length == 0);
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		String nick = "nickname";
		String email = "email";
		String nombre = "nombre";
		String apellido = "apellido";
		String nacionalidad = "uruguay";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {
			icUsuario.crearTurista(nick, nombre, apellido, email, fechaNac, nacionalidad, password, "");
			DataTurista[] turistas = icUsuario.getTuristas();
			assertTrue(turistas.length == 1);
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testGetProveedores() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		try {
			DataProveedor[] proveedors = icUsuario.getProveedores();
			assertTrue(proveedors.length == 0);
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		String nick = "nickname";
		String email = "email";
		String nombre = "nombre";
		String apellido = "apellido";
		String descripcion = "desc";
		String link = "link";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {
			icUsuario.crearProveedor(nick, nombre, apellido, email, fechaNac, descripcion, link, password, "");
			DataProveedor[] proveedors = icUsuario.getProveedores();
			assertTrue(proveedors.length == 1);
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerSalidasTuristaNoExisteTurista() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		assertThrows(UsuarioNoExisteException.class, () -> {
			icUsuario.obtenerInscripcionesTurista("alguien");
		});
	}

	@Test
	void testObtenerSalidasTuristaVacia() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();

		String nick = "nickname";
		String email = "email";
		String nombre = "nombre";
		String apellido = "apellido";
		String nacionalidad = "uruguay";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {
			icUsuario.crearTurista(nick, nombre, apellido, email, fechaNac, nacionalidad, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		DataInscripcion[] inscripcions;
		try {
			inscripcions = icUsuario.obtenerInscripcionesTurista(nick);
			assertTrue(inscripcions.length == 0);

		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerActividadesProveedorNoExiteProveedor() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();
		DataActividadTuristica[] actividadTuristicas = icUsuario.obtenerActividadesProveedor("alguien");
		assertTrue(actividadTuristicas.length == 0);
	}

	@Test
	void testObtenerActividadesProveedorVacia() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();

		String nick = "nickname";
		String email = "email";
		String nombre = "nombre";
		String apellido = "apellido";
		String descripcion = "desc";
		String link = "link";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {
			icUsuario.crearProveedor(nick, nombre, apellido, email, fechaNac, descripcion, link, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		DataActividadTuristica[] actividadTuristicas = icUsuario.obtenerActividadesProveedor(nick);
		assertTrue(actividadTuristicas.length == 0);
	}

	@Test
	void testObtenerLink() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();

		String nick = "nickname";
		String email = "email";
		String nombre = "nombre";
		String apellido = "apellido";
		String descripcion = "desc";
		String link = "link";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {
			icUsuario.crearProveedor(nick, nombre, apellido, email, fechaNac, descripcion, link, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		String linkP = icUsuario.obtenerLink(nick);
		assertTrue(linkP.equals(""));
	}

	@Test
	void testObtenerPasswordProv() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();

		String nick = "nickname";
		String email = "email";
		String nombre = "nombre";
		String apellido = "apellido";
		String descripcion = "desc";
		String link = "link";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {
			icUsuario.crearProveedor(nick, nombre, apellido, email, fechaNac, descripcion, link, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		String linkP = icUsuario.obtenerPassword(nick);
		assertTrue(linkP.equals("pwd"));
	}

	@Test
	void testObtenerPasswordTur() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();

		String nick = "nickname";
		String email = "email";
		String nombre = "nombre";
		String apellido = "apellido";
		String nacionalidad = "nac";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {
			icUsuario.crearTurista(nick, nombre, apellido, email, fechaNac, nacionalidad, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		String linkP = icUsuario.obtenerPassword(nick);
		assertTrue(linkP.equals("pwd"));
	}

	@Test
	void testObtenerNickProv() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();

		String nick = "nickname";
		String email = "email";
		String nombre = "nombre";
		String apellido = "apellido";
		String descripcion = "desc";
		String link = "link";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {
			icUsuario.crearProveedor(nick, nombre, apellido, email, fechaNac, descripcion, link, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		String linkP = icUsuario.obtenerNickname(email);
		assertTrue(linkP.equals("nickname"));
	}

	@Test
	void testObtenerNickTur() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icUsuario = fabrica.getIControladorUsuario();

		String nick = "nickname";
		String email = "email";
		String nombre = "nombre";
		String apellido = "apellido";
		String nacionalidad = "nac";
		LocalDate fechaNac = LocalDate.of(2022, 8, 1);
		String password = "pwd";

		try {
			icUsuario.crearTurista(nick, nombre, apellido, email, fechaNac, nacionalidad, password, "");
		} catch (EmailRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (NickameRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

	}

}
