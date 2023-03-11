package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoUsuarios;
import servidor.DataProveedor;
import servidor.DataTurista;
import servidor.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("htmls/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String from = (String) request.getSession().getAttribute("from");

		HttpSession objSesion = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DaoUsuarios dao = new DaoUsuarios();
		if (username.contains("@")) {
			// busco el nick asociado a ese mail
			username = dao.obtenerNickname(username);
		}
		try {
			DataTurista datat = dao.obtenerTurista(username);
			if (datat != null) {
				if (dao.obtenerPassword(username).equals(password)) {
					request.getSession().setAttribute("usuario_logueado", username);
					request.getSession().setAttribute("nombre_usuario", datat.getNombre() + " " + datat.getApellido());
					request.getSession().setAttribute("tipo_usuario", "Turista");
					request.getSession().setAttribute("foto_usuario", datat.getImgSrc());
					objSesion.setAttribute("sesion_activa", true);
					request.getRequestDispatcher(from).forward(request, response);
					return;

				} else {
					// contrasenia incorrecta
					objSesion.setAttribute("sesion_activa", false);
					request.setAttribute("alert", "Error: email o contraseña incorrecta.");
					request.getRequestDispatcher(from).forward(request, response);
					return;
				}
			}
		} catch (UsuarioNoExisteException_Exception e) {
			e.printStackTrace();
		}
		try {
			DataProveedor datat = dao.obtenerProveedor(username);

			// es proveedor
			// chequeo contrasenia
			if (dao.obtenerPassword(username).equals(password)) {
				request.getSession().setAttribute("usuario_logueado", username);
				request.getSession().setAttribute("tipo_usuario", "Proveedor");
				request.getSession().setAttribute("nombre_usuario", datat.getNombre() + " " + datat.getApellido());
				request.getSession().setAttribute("foto_usuario", datat.getImgSrc());
				objSesion.setAttribute("sesion_activa", true);
			} else {
				// contrasenia incorrecta
				objSesion.setAttribute("sesion_activa", false);
				request.setAttribute("alert", "Error: email o contraseña incorrecta.");
			}
		} catch (UsuarioNoExisteException_Exception e1) {
			// no esta en la base de datos
			objSesion.setAttribute("sesion_activa", false);
			request.setAttribute("alert", "Error: email o contraseña incorrecta.");
		}

		// forward the control to prev jsp
		request.getRequestDispatcher(from).forward(request, response);

	}

}
