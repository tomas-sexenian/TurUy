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

@WebServlet("/actividades")
public class actividadesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ApplicationDao dao = new ApplicationDao();

		String dpto = request.getParameter("departamento");
		String cat = request.getParameter("categorias");

		if (dpto == null) {
			dpto = "";
		}
		if (cat == null) {
			cat = "";
		}

		List<DataActividadTuristica> actividades = dao.obtenerActividades(dpto, cat);
		request.getSession().setAttribute("actividades", actividades);
		request.getSession().setAttribute("departamento", dpto);
		request.getSession().setAttribute("categoriaSelected", cat);
		request.getRequestDispatcher("/htmls/actividades.jsp").forward(request, response);
	}

}
