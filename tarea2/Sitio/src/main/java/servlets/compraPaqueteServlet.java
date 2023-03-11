package servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.PaqueteCompradoException;
import logica.Fabrica;
import logica.interfaces.IControladorUsuario;

/**
 * Servlet implementation class compraPaqueteServlet
 */
@WebServlet("/compraPaquete")
public class compraPaqueteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public compraPaqueteServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// chequear si el usuario ya compro el paquete, ya se que el usuario es un
		// turista
		String usuario = (String) request.getSession().getAttribute("usuario_logueado");

		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icu = fabrica.getIControladorUsuario();
		String nombrePaquete = request.getParameter("nombrePaquete");
		LocalDate hoy = LocalDate.now();
		int cantTuristas = Integer.parseInt(request.getParameter("cantidadTuristas"));
		try {
			icu.compraTuristaPaquete(usuario, nombrePaquete, hoy, cantTuristas);
		} catch (PaqueteCompradoException e) {
			request.setAttribute("alert", "El paquete ya fue comprado por este usuario");
		}

		request.getRequestDispatcher("/htmls/consultaPaquete.jsp").forward(request, response);

	}
}
