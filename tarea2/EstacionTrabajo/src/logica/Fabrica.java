package logica;

import logica.controladores.ControladorActividades;
import logica.controladores.ControladorDepartamentos;
import logica.controladores.ControladorSalidas;
import logica.controladores.ControladorUsuario;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorDepartamentos;
import logica.interfaces.IControladorSalidas;
import logica.interfaces.IControladorUsuario;

public final class Fabrica {

	private static Fabrica instancia;

	private Fabrica() {
	}

	public static Fabrica getInstance() {
		if (instancia == null) {
			instancia = new Fabrica();
		}
		return instancia;
	}

	public IControladorUsuario getIControladorUsuario() {
		return new ControladorUsuario();
	}

	public IControladorActividades getIControladorActividades() {
		return new ControladorActividades();
	}

	public IControladorDepartamentos getIControladorDepartamentos() {
		return new ControladorDepartamentos();
	}

	public IControladorSalidas getIControladorSalidas() {
		return new ControladorSalidas();
	}

}
