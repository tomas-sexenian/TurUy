
package servlets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;

import dao.ApplicationDao;
import excepciones.UsuarioNoExisteException;
import logica.Fabrica;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataTurista;
import logica.interfaces.IControladorUsuario;

@WebServlet("/modificarUsuario")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class modificarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!(request.getSession().getAttribute("tipo_usuario").equals("Proveedor")
				|| request.getSession().getAttribute("tipo_usuario").equals("Turista"))) {

			// forward the control to the index.jsp
			request.setAttribute("alert", "Error: No puede modificar un usuario que no esté logueado");
			request.setAttribute("error", "yes");
			request.getRequestDispatcher("htmls/index.jsp").forward(request, response);
		} else {
			ApplicationDao dao = new ApplicationDao();
			String nickname = request.getParameter("usuario");
			request.setAttribute("nickname", nickname);
			DataTurista turista = dao.obtenerTurista(nickname);
			if (turista != null) {
				request.setAttribute("nacionalidad", turista.getNacionalidad());

				request.setAttribute("nombre", turista.getNombre());
				request.setAttribute("apellido", turista.getApellido());
				request.setAttribute("correo", turista.getEmail());
				request.setAttribute("img", turista.getImgSrc());
				request.setAttribute("nickname", turista.getNickname());
				request.setAttribute("fecha", turista.getNacimiento().toString());
			} else {
				DataProveedor proveedor = dao.obtenerProveedor(nickname);
				request.setAttribute("descripcion", proveedor.getDescripcion());
				request.setAttribute("link", proveedor.getLink());

				request.setAttribute("nombre", proveedor.getNombre());
				request.setAttribute("apellido", proveedor.getApellido());
				request.setAttribute("correo", proveedor.getEmail());
				request.setAttribute("img", proveedor.getImgSrc());
				request.setAttribute("nickname", proveedor.getNickname());
				request.setAttribute("fecha", proveedor.getNacimiento().toString());
			}
			if (request.getSession().getAttribute("tipo_usuario").equals("Proveedor")) {
				request.getRequestDispatcher("htmls/modificarProveedor.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("htmls/modificarTurista.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nickname = req.getParameter("nickname");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String correo = req.getParameter("correo");
		LocalDate fecha = LocalDate.parse(req.getParameter("fecha"));
		String descripcion = req.getParameter("descripcion");
		String link = req.getParameter("link");
		String nacionalidad = req.getParameter("nacionalidad");
		String contraseña = req.getParameter("contraseña");
		String confContraseña = req.getParameter("confcontraseña");

		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icu = fabrica.getIControladorUsuario();
		boolean success = false;
		if (contraseña != null && contraseña != "") {
			if (!contraseña.equals(confContraseña)) {
				req.setAttribute("error", "yes");
				req.setAttribute("alert", "Las contraseñas no coinciden");
				req.setAttribute("nickname", nickname);
				req.setAttribute("nombre", nombre);
				req.setAttribute("apellido", apellido);
				req.setAttribute("correo", correo);
				req.setAttribute("descripcion", descripcion);
				req.setAttribute("link", link);
				req.setAttribute("nacionalidad", nacionalidad);
				req.setAttribute("fecha", fecha);
				if (req.getSession().getAttribute("tipo_usuario").equals("Proveedor")) {
					req.getRequestDispatcher("htmls/modificarProveedor.jsp").forward(req, resp);
				} else {
					req.getRequestDispatcher("htmls/modificarTurista.jsp").forward(req, resp);
				}
				return;
			}
		}

		try {
			icu.modificarProveedor(nickname, nombre, apellido, correo, fecha, descripcion, link);
			success = true;
		} catch (UsuarioNoExisteException e) {
			success = false;
		}
		if (!success) {
			try {
				icu.modificarTurista(nickname, nombre, apellido, correo, fecha, nacionalidad);
				success = true;
			} catch (UsuarioNoExisteException e) {
				req.setAttribute("alert", "Error: No existe un usuario con nickname " + nickname);
				req.setAttribute("error", "yes");
				return;
			}
		}

		// upload image
		String fileDir = getServletContext().getInitParameter("filedir");
		Part filePart = (Part) req.getSession().getAttribute("filePart");
		boolean hayImagenGuardada = true;
		if (filePart == null) {
			filePart = req.getPart("img");
			hayImagenGuardada = false;
		}
		String fileName = filePart.getSubmittedFileName();
		String newFileName = (String) req.getSession().getAttribute("newFileName");
		if (fileName != null && fileName != "" && !hayImagenGuardada) {

			// changes name to random string
			newFileName = RandomStringUtils.randomAlphanumeric(8);
			req.getSession().setAttribute("newFileName", newFileName);

			// constructs the directory path to store upload file
			// this path is relative to application's directory
			String uploadPath = getServletContext().getRealPath("") + File.separator + fileDir;

			// creates the directory if it does not exist
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// saves the image in the server
			filePart.write(uploadDir + File.separator + newFileName);
		}
		if (success) {
			try { // No es necesario porque ya se maneja arriba la posible excepción pero es
					// necesario para que compile.
				if (fileName != null && fileName != "") {
					icu.modificarImagen(nickname, newFileName);
				}
				if (contraseña != null && contraseña != "") {
					icu.modificarContrasena(nickname, contraseña);
				}

				resp.sendRedirect("/Sitio/consultaUsuario?usuario=" + nickname);
			} catch (UsuarioNoExisteException e) {
				e.printStackTrace();
			}
		}
	}

}
