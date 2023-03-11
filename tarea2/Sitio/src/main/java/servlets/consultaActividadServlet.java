package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplicationDao;
import excepciones.ActividadNoExisteException;
import excepciones.SalidaNoExisteException;
import logica.EstadoActividad;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataSalidaTuristica;
import logica.interfaces.IControladorActividades;
import logica.interfaces.IControladorSalidas;

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
		Fabrica fabrica = Fabrica.getInstance();
		IControladorActividades icu = fabrica.getIControladorActividades();
		IControladorSalidas ics = fabrica.getIControladorSalidas();
		String proveedor = "";
		DataActividadTuristica actividad = dao.obtenerActividadTuristica(request.getParameter("actividad"));
		if (actividad.getEstadoActividad().equals(EstadoActividad.CONFIRMADA)) {
			try {
				List<DataSalidaTuristica> salVig = ics.getSalidasVigentes(request.getParameter("actividad"));
				if (salVig.isEmpty()) {
					proveedor = icu.obtenerProveedorActividad(request.getParameter("actividad"));
				}
			} catch (SalidaNoExisteException e) {
				// ignore
			} catch (ActividadNoExisteException e1) {
				// ignore
			}
		}
		List<DataSalidaTuristica> salidas = new ArrayList<DataSalidaTuristica>(actividad.getSalidas().values());
		request.getSession().setAttribute("salidasAct", salidas);
		request.getSession().setAttribute("actividad", actividad);
		request.getSession().setAttribute("categoriasAct", actividad.getCategorias());
		request.getSession().setAttribute("paquetesAct", actividad.getPaquetes());
		request.getSession().setAttribute("proveedor", proveedor);
		request.getRequestDispatcher("/htmls/consultaActividad.jsp").forward(request, response);

	}

}
