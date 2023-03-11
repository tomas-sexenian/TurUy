package presentacion;

public final class FabricaInternalFrames {

	private static FabricaInternalFrames instancia;
	private ConsultarActividad consultarActividad;
	private ConsultarSalidas consultarSalidas;
	private ConsultarPaqueteActividades consultarPaqueteActividades;
	private ConsultarUsuario consultarUsuario;

	private FabricaInternalFrames() {
		consultarSalidas = new ConsultarSalidas();
		consultarActividad = new ConsultarActividad();
		consultarPaqueteActividades = new ConsultarPaqueteActividades();
		consultarUsuario = new ConsultarUsuario();
	};

	public static FabricaInternalFrames getInstance() {

		if (instancia == null) {
			instancia = new FabricaInternalFrames();
		}
		return instancia;
	}

	public ConsultarActividad getConsultarActividad() {
		return consultarActividad;
	}

	public ConsultarSalidas getConsultarSalidas() {
		return consultarSalidas;
	}

	public ConsultarPaqueteActividades getConsultarPaqueteActividades() {
		return consultarPaqueteActividades;
	}

	public ConsultarUsuario getConsultarUsuario() {
		return consultarUsuario;
	}

}
