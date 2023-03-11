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

import dao.ApplicationDao;
import servidor.DataActividadTuristica;
import servidor.DataSalidaTuristica;

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
		ApplicationDao dao = new ApplicationDao();
		DataActividadTuristica actividad = dao.obtenerActividadTuristica(request.getParameter("actividad"));

		String video = "";
		String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|(?:be-nocookie|be)\\.com\\/(?:watch|[\\w]+\\?(?:feature=[\\w]+.[\\w]+\\&)?v=|v\\/|e\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(actividad.getVideo());
		if (matcher.find()) {
			video = "https://www.youtube.com/embed/" + matcher.group(1);
		} else {
			video = null;
		}

		List<DataSalidaTuristica> salidas = actividad.getSalidas();
		request.getSession().setAttribute("salidasAct", salidas);
		request.getSession().setAttribute("actividad", actividad);
		request.getSession().setAttribute("categoriasAct", actividad.getCategorias());
		request.getSession().setAttribute("paquetesAct", actividad.getPaquetes());
		request.getSession().setAttribute("video", video);

		request.getRequestDispatcher("/htmls/consultaActividad.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
