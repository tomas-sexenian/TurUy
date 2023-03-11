package test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import excepciones.DepartamentoNoExisteException;
import excepciones.DepartamentoRepetidoException;
import logica.Fabrica;
import logica.clases.Departamento;
import logica.datatypes.DataDepartamento;
import logica.interfaces.IControladorDepartamentos;
import logica.manejadores.ManejadorDepartamentos;

class ControladorDepartamentosTest {
	
	@AfterEach
	void setUpAfterClass() throws Exception {

		ManejadorDepartamentos maDepartamentos = ManejadorDepartamentos.getinstance();
		for (Departamento d : maDepartamentos.getDepartamentos().values()) {
			maDepartamentos.removerDepartamento(d.getNombre());
		}
	}

	@Test
	void testCrearDepartamentoOk() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorDepartamentos icd = fabrica.getIControladorDepartamentos();
		String nombre = "MyDepto";
		String descripcion = "Division Turismo de la Intendencia";
		String link = "https://www.imcanelones.gub.uy/es";
		try {
			icd.crearDepartamento(nombre, descripcion, link);
			DataDepartamento departamento = icd.consultarDepartamento(nombre);
			assertEquals(departamento.getNombre(), nombre);
			assertEquals(departamento.getDescripcion(), descripcion);
			assertEquals(departamento.getLink(), link);
		} catch (DepartamentoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (DepartamentoNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testCrearDepartamentoFail() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorDepartamentos icd = fabrica.getIControladorDepartamentos();
		String nombre = "MyOtroDepto";
		String descripcion = "Division Turismo de la Intendencia";
		String link = "https://www.imcanelones.gub.uy/es";
		try {
			icd.crearDepartamento(nombre, descripcion, link);
		} catch (DepartamentoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} 
		assertThrows(DepartamentoRepetidoException.class, () -> {
			icd.crearDepartamento(nombre, descripcion, link); });
	}
	
	@Test
	void testConsultarDepartamento() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorDepartamentos icd = fabrica.getIControladorDepartamentos();
		assertThrows(DepartamentoNoExisteException.class, () -> {
			icd.consultarDepartamento("algo"); });
	}

	@Test
	void testModificarDepartamentoFail() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorDepartamentos icd = fabrica.getIControladorDepartamentos();
		assertThrows(DepartamentoNoExisteException.class, () -> {
			icd.modificarDepartamento("algo", "desc", "link"); });

	}
	
	@Test
	void testModificarDepartamentoOk() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorDepartamentos icd = fabrica.getIControladorDepartamentos();
		String nombre = "MyTercerDepto";
		String descripcion = "Mi descripcion";
		String link = "https://www.link.gub.uy/es";
		try {
			icd.crearDepartamento(nombre, "Division Turismo de la Intendencia", "https://www.imcanelones.gub.uy/es");
			icd.modificarDepartamento(nombre, descripcion, link);
			DataDepartamento departamento = icd.consultarDepartamento(nombre);
			assertEquals(departamento.getNombre(), nombre);
			assertEquals(departamento.getDescripcion(), descripcion);
			assertEquals(departamento.getLink(), link);
		} catch (DepartamentoNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (DepartamentoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerDepartamentos() {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorDepartamentos icd = fabrica.getIControladorDepartamentos();
		String nombre = "MyCuartoDepto";
		String descripcion = "Division Turismo de la Intendencia";
		String link = "https://www.imcanelones.gub.uy/es";
		try {
			icd.crearDepartamento(nombre, descripcion, link);
			DataDepartamento[] dds = icd.obtenerDepartamentos();
			assertTrue(dds.length > 0);
		} catch (DepartamentoNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (DepartamentoRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
