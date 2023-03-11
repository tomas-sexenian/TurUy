package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoActividades;

/**
 * Servlet implementation class paquetesServlet
 */
@WebServlet(name = "paquetes", urlPatterns = { "/paquetes" })
public class paquetesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DaoActividades daoActividades = new DaoActividades();

		List<servidor.DataPaqueteActividades> paquetes = daoActividades.obtenerPaquetes();
		req.getSession().setAttribute("paquetes", paquetes);
		req.getRequestDispatcher("/htmls/paquetes.jsp").forward(req, resp);

	}

}
