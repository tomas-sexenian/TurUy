package servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoActividades;
import dao.DaoUsuarios;
import servidor.DataActividadTuristica;

@WebServlet("/actividades")
public class actividadesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DaoActividades daoActividades = new DaoActividades();
		DaoUsuarios daoUsuarios = new DaoUsuarios();

		String dpto = request.getParameter("departamento");
		String cat = request.getParameter("categorias");

		if (dpto == null) {
			dpto = "";
		}
		if (cat == null) {
			cat = "";
		}
		List<DataActividadTuristica> actividades_fav = new LinkedList<>();
		List<DataActividadTuristica> actividades_nofav = new LinkedList<>();
		List<DataActividadTuristica> actividades = daoActividades.obtenerActividades("", "");
		String usr = (String) request.getSession().getAttribute("usuario_logueado");
		String tipo = (String) request.getSession().getAttribute("tipo_usuario");
		if (usr != null && tipo.equals("Turista")) {
			for (DataActividadTuristica nom : actividades) {
				if (daoUsuarios.esFavorita(usr, nom.getNombre())) {
					actividades_fav.add(nom);
				} else {
					actividades_nofav.add(nom);
				}
			}
		} else {
			actividades_nofav = actividades;
		}

		request.getSession().setAttribute("actividades_fav", actividades_fav);
		request.getSession().setAttribute("actividades", actividades_nofav);
		request.getSession().setAttribute("departamento", dpto);
		request.getSession().setAttribute("categoriaSelected", cat);
		request.getRequestDispatcher("/htmls/actividades.jsp").forward(request, response);
	}

}
