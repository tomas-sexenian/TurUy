package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplicationDao;
import logica.DatosPrueba;

/**
 * Servlet implementation class cargarDatosServlet
 */
@WebServlet("/cargarDatos")
public class cargarDatosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Override
	public void init() throws ServletException {
		DatosPrueba.cargarDatos();
		ApplicationDao dao = new ApplicationDao();
		List<String> departamentos = dao.obtenerDepartamentos();
		getServletContext().setAttribute("departamentos", departamentos);

		List<String> categorias = dao.obtenerCategorias();
		getServletContext().setAttribute("categorias", categorias);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// forward the control to the index.jsp
		request.getServletContext().setAttribute("datosCargados", "true");
		request.getRequestDispatcher("home").forward(request, response);
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
