package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuarios;
import servidor.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class altaUsuarioDinamico
 */
@WebServlet("/altaUsuarioDinamico")
public class altaUsuarioDinamico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public altaUsuarioDinamico() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		DaoUsuarios dao = new DaoUsuarios();
		String nickname = request.getParameter("nickname");
		if (nickname != null && nickname.length() > 0) {
			try {
				if (dao.obtenerTurista(nickname) != null) {
					out.println("El usuario ya existe");

				} else {
					if (dao.obtenerProveedor(nickname) != null) {
						out.println("El usuario ya existe");

					} else {
						out.println("El usuario no existe");
					}
				}
			} catch (UsuarioNoExisteException_Exception e) {
				try {
					if (dao.obtenerProveedor(nickname) != null) {
						out.println("El usuario ya existe");

					} else {
						out.println("El usuario no existe");
					}
					// System.out.println("taken");
				} catch (UsuarioNoExisteException_Exception e1) {
					// System.out.println("not_taken");
					out.println("El usuario no existe");
				}
			}
		} else {
			out.println("");
		}
		String correo = request.getParameter("correo");

		if (correo != null && correo.length() > 0) {
			if (dao.existeEmail(correo)) {
				out.println("El email ya esta registrado");
			} else {
				out.println("El email no esta en uso");
			}
		} else {
			out.println("");
		}
	}

}
