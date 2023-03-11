package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplicationDao;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataPaqueteActividades;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataSalidaTuristica;

/**
 * Servlet implementation class homeServlet
 */
@WebServlet("/home")
public class homeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {

		System.out.println("in init method");
		// DatosPrueba.cargarDatos();
		ApplicationDao dao = new ApplicationDao();
		List<String> departamentos = dao.obtenerDepartamentos();
		getServletContext().setAttribute("departamentos", departamentos);

		List<String> categorias = dao.obtenerCategorias();
		getServletContext().setAttribute("categorias", categorias);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ApplicationDao dao = new ApplicationDao();

		List<DataActividadTuristica> actividades = dao.obtenerActividades("", "");
		req.getSession().setAttribute("actividades", actividades);

		List<DataPaqueteActividades> paquetes = dao.obtenerPaquetes();
		req.getSession().setAttribute("paquetes", paquetes);

		List<DataSalidaTuristica> salidas = dao.obtenerSalidas();
		req.getSession().setAttribute("salidas", salidas);

		List<DataProveedor> proveedores = dao.obtenerProveedores();
		req.getSession().setAttribute("proveedores", proveedores);

		// forward the control to the index.jsp
		req.getRequestDispatcher("/htmls/index.jsp").forward(req, resp);
	}

}
