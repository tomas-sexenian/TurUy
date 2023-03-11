package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoActividades;

import servidor.DataActividadTuristicaP;
import servidor.DataSalidaTuristicaP;

/**
 * Servlet implementation class consultaActividadFinalizada
 */
@WebServlet("/consultaActividadFinalizada")
public class consultaActividadFinalizada extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public consultaActividadFinalizada() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DaoActividades daoActividades = new DaoActividades();
		String proveedor = (String) request.getSession().getAttribute("usuario_logueado");
		List<DataActividadTuristicaP> actividades = daoActividades.obtenerActividadesFinalizadas(proveedor,
				request.getParameter("actividad"));
		DataActividadTuristicaP actividad = actividades.get(0);
		List<DataSalidaTuristicaP> salidas = actividad.getSalidas();

		request.getSession().setAttribute("salidasAct", salidas);
		request.getSession().setAttribute("actividad", actividad);
		request.getSession().setAttribute("finalizada", true);
		request.getRequestDispatcher("/htmls/consultaActividad.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
