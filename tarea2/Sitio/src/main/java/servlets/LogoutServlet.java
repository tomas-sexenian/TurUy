package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ApplicationDao;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataPaqueteActividades;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataSalidaTuristica;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(name = "logout", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession objSesion = request.getSession();
		objSesion.invalidate();
		ApplicationDao dao = new ApplicationDao();

		List<DataActividadTuristica> actividades = dao.obtenerActividades("", "");
		request.getSession().setAttribute("actividades", actividades);

		List<DataPaqueteActividades> paquetes = dao.obtenerPaquetes();
		request.getSession().setAttribute("paquetes", paquetes);

		List<DataSalidaTuristica> salidas = dao.obtenerSalidas();
		request.getSession().setAttribute("salidas", salidas);

		List<DataProveedor> proveedores = dao.obtenerProveedores();
		request.getSession().setAttribute("proveedores", proveedores);
		// forward the control to the index.jsp
		request.getRequestDispatcher("htmls/index.jsp").forward(request, response);
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
