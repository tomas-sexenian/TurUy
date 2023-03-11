package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoActividades;
import dao.DaoSalidas;
import dao.DaoUsuarios;
import servidor.DataActividadTuristica;

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
		DaoUsuarios dao = new DaoUsuarios();
		DaoActividades daoActividades = new DaoActividades();
		DaoSalidas daoSalidas = new DaoSalidas();

		List<DataActividadTuristica> actividades = daoActividades.obtenerActividades("", "");
		request.getSession().setAttribute("actividades", actividades);

		List<servidor.DataPaqueteActividades> paquetes = daoActividades.obtenerPaquetes();
		request.getSession().setAttribute("paquetes", paquetes);

		List<servidor.DataSalidaTuristica> salidas = daoSalidas.obtenerSalidas();
		request.getSession().setAttribute("salidas", salidas);

		List<servidor.DataProveedor> proveedores = dao.obtenerProveedores();
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
