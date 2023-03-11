package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuarios;
import servidor.DataActividadTuristica;

/**
 * Servlet implementation class altaUsuarioDinamico
 */
@WebServlet("/desmarcarFavorita")
public class desmarcarFavoritaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public desmarcarFavoritaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DaoUsuarios dao = new DaoUsuarios();
		String nickname = (String) request.getSession().getAttribute("usuario_logueado");
		DataActividadTuristica actividad = (DataActividadTuristica) request.getSession().getAttribute("actividad");
		dao.desmarcarFavorita(nickname, actividad.getNombre());
	}

}
