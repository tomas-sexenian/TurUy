package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoActividades;
import dao.DaoDepartamentos;
import dao.DaoSalidas;
import dao.DaoUsuarios;
import servidor.DataActividadTuristica;

/**
 * Servlet implementation class homeServlet
 */
@WebServlet("/home")
public class homeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {

		System.out.println("in init method");
		DaoDepartamentos daoDepartamentos = new DaoDepartamentos();
		List<String> departamentos = daoDepartamentos.obtenerDepartamentos();
		getServletContext().setAttribute("departamentos", departamentos);

		DaoActividades daoActividades = new DaoActividades();
		List<String> categorias = daoActividades.obtenerCategorias();
		getServletContext().setAttribute("categorias", categorias);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DaoUsuarios dao = new DaoUsuarios();
		DaoActividades daoActividades = new DaoActividades();
		DaoSalidas daoSalidas = new DaoSalidas();
		List<DataActividadTuristica> actividades = daoActividades.obtenerActividades("", "");

		req.getSession().setAttribute("actividades", actividades);

		List<servidor.DataPaqueteActividades> paquetes = daoActividades.obtenerPaquetes();
		req.getSession().setAttribute("paquetes", paquetes);

		List<servidor.DataSalidaTuristica> salidas = daoSalidas.obtenerSalidas();
		req.getSession().setAttribute("salidas", salidas);

		List<servidor.DataProveedor> proveedores = dao.obtenerProveedores();
		req.getSession().setAttribute("proveedores", proveedores);

		// forward the control to the index.jsp
		req.getRequestDispatcher("/htmls/index.jsp").forward(req, resp);
	}

}
