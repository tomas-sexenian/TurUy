package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ApplicationDao;
import servidor.DataActividadTuristica;
import servidor.DataSalidaTuristica;
import servidor.DataTurista;
import servidor.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

		System.out.println("in init method");
		ApplicationDao dao = new ApplicationDao();
		List<String> departamentos = dao.obtenerDepartamentos();
		getServletContext().setAttribute("departamentos", departamentos);

		List<String> categorias = dao.obtenerCategorias();
		getServletContext().setAttribute("categorias", categorias);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationDao dao = new ApplicationDao();

		List<DataActividadTuristica> actividades = dao.obtenerActividades("", "");
		request.getSession().setAttribute("actividades", actividades);
		List<DataSalidaTuristica> salidas = dao.obtenerSalidas();
		request.getSession().setAttribute("salidas", salidas);
		request.getRequestDispatcher("/htmls/login.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationDao dao = new ApplicationDao();

		List<DataActividadTuristica> actividades = dao.obtenerActividades("", "");
		request.getSession().setAttribute("actividades", actividades);
		List<DataSalidaTuristica> salidas = dao.obtenerSalidas();
		request.getSession().setAttribute("salidas", salidas);
		HttpSession objSesion = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username.contains("@")) {
			// busco el nick asociado a ese mail
			username = dao.obtenerNickname(username);
		}
		try {
			DataTurista datat = dao.obtenerTurista(username);
			// es turista
			// chequeo contrasenia
			if (dao.obtenerPassword(username).equals(password)) {
				request.getSession().setAttribute("usuario_logueado", username);
				request.getSession().setAttribute("nombre_usuario", datat.getNombre() + " " + datat.getApellido());
				request.getSession().setAttribute("tipo_usuario", "Turista");
				request.getSession().setAttribute("foto_usuario", datat.getImgSrc());
				objSesion.setAttribute("sesion_activa", true);
				request.getRequestDispatcher("/htmls/index.jsp").forward(request, response);
			} else {
				// contrasenia incorrecta
				objSesion.setAttribute("sesion_activa", false);
				request.setAttribute("alert", "Error: email o contraseña incorrecta.");
				request.getRequestDispatcher("/htmls/login.jsp").forward(request, response);
			}

		} catch (UsuarioNoExisteException_Exception e) {
			// no era un turista, por lo tanto no puede entrar
			objSesion.setAttribute("sesion_activa", false);
			request.setAttribute("alert", "Error: email o contraseña incorrecta.");
			request.getRequestDispatcher("/htmls/login.jsp").forward(request, response);
		}

		// forward the control to prev jsp

	}

}
