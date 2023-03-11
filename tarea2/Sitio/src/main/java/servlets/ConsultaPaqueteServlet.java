package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplicationDao;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataPaqueteActividades;

@WebServlet(name = "ConsultaPaquete", urlPatterns = { "/ConsultaPaquete" })
public class ConsultaPaqueteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ApplicationDao dao = new ApplicationDao();
		DataPaqueteActividades paquete = dao.obtenerPaquete(request.getParameter("paquete"));
		List<DataActividadTuristica> actividades = paquete.getActividades();

		Set<String> temp = new LinkedHashSet<>();
		for (DataActividadTuristica act : actividades) {
			temp.addAll(act.getCategorias());
		}
		List<String> categorias = new ArrayList<String>(temp);
		double costo = paquete.getPrecio();

		request.getSession().setAttribute("categoriasPaq", categorias);
		request.getSession().setAttribute("actividadesPaq", actividades);
		request.getSession().setAttribute("paquete", paquete);
		request.getSession().setAttribute("costo", costo);
		request.getRequestDispatcher("/htmls/consultaPaquete.jsp").forward(request, response);
	}

}
