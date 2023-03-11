package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoActividades;
import servidor.ActividadNoExisteException_Exception;

/**
 * Servlet implementation class finalizarActividadServlet
 */
@WebServlet("/finalizarActividad")
public class finalizarActividadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public finalizarActividadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// hay que finalizar la actividad!
		String nombre = request.getParameter("nombreActividad");
		try {
			DaoActividades daoActividades = new DaoActividades();
			daoActividades.finalizarActividad(nombre);
		} catch (ActividadNoExisteException_Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("consultaActividadFinalizada?actividad=" + nombre).forward(request, response);
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
