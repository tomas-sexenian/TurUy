package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplicationDao;
import servidor.DataSalidaTuristica;

/**
 * Servlet implementation class consultaSalidaServlet
 */
@WebServlet(name = "consultaSalida", urlPatterns = { "/consultaSalida" })
public class consultaSalidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApplicationDao dao = new ApplicationDao();
		DataSalidaTuristica salida = dao.obtenerSalida(request.getParameter("salida"));

		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
		LocalDate fechaSalida = LocalDate.parse(salida.getFechaSalida(), formatters);
		if (fechaSalida.isAfter(LocalDate.now())) {
			request.getSession().setAttribute("vigente", "yes");
		} else {
			request.getSession().removeAttribute("vigente");
		}

		request.getSession().setAttribute("salida", salida);
		request.getRequestDispatcher("/htmls/consultaSalida.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
