package servlets;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoActividades;
import dao.DaoSalidas;
import dao.DaoUsuarios;
import servidor.DataActividadTuristica;
import servidor.DataPaqueteActividades;
import servidor.EstadoActividad;

/**
 * Servlet implementation class consultaActividadServlet
 */
@WebServlet(name = "consultaActividad", urlPatterns = { "/consultaActividad" })
public class consultaActividadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DaoSalidas daoSalidas = new DaoSalidas();
		DaoActividades daoActividades = new DaoActividades();
		DaoUsuarios daoUsuarios = new DaoUsuarios();
		String proveedor = "";
		DataActividadTuristica actividad = daoActividades.obtenerActividadTuristica(request.getParameter("actividad"));

		String video = "";
		String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|(?:be-nocookie|be)\\.com\\/(?:watch|[\\w]+\\?(?:feature=[\\w]+.[\\w]+\\&)?v=|v\\/|e\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(actividad.getVideo());
		if (matcher.find()) {
			video = "https://www.youtube.com/embed/" + matcher.group(1);
		} else {
			video = null;
		}

		if (actividad.getEstado().equals(EstadoActividad.CONFIRMADA)) {
			List<servidor.DataSalidaTuristica> salVig = daoSalidas
					.obtenerSalidasVigentes(request.getParameter("actividad"));
			if (salVig.isEmpty()) {
				boolean enPaquete = false;
				List<DataPaqueteActividades> paquetes = daoActividades.obtenerPaquetes();
				for (DataPaqueteActividades paq : paquetes) {
					for (DataActividadTuristica act : paq.getActividades()) {
						if (act.getNombre().equals(actividad.getNombre())) {
							enPaquete = true;
							break;
						}
					}
				}
				if (!enPaquete) {
					proveedor = actividad.getProveedor();
				}

			}
		}

		boolean esFavorita = false;
		String usr = (String) request.getSession().getAttribute("usuario_logueado");
		if (!(usr == null || usr.equals("")) && request.getSession().getAttribute("tipo_usuario").equals("Turista")) {
			esFavorita = daoUsuarios.esFavorita(usr, actividad.getNombre());
		}

		List<servidor.DataSalidaTuristica> salidas = actividad.getSalidas();
		request.getSession().setAttribute("salidasAct", salidas);
		request.getSession().setAttribute("actividad", actividad);
		request.getSession().setAttribute("video", video);
		request.getSession().setAttribute("categoriasAct", actividad.getCategorias());
		request.getSession().setAttribute("paquetesAct", actividad.getPaquetes());
		request.getSession().setAttribute("proveedor", proveedor);
		request.getSession().setAttribute("esFavorita", esFavorita);
		request.getSession().setAttribute("finalizada", false);
		request.getRequestDispatcher("/htmls/consultaActividad.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
