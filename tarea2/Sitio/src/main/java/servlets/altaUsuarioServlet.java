
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

import excepciones.EmailRepetidoException;
import excepciones.NickameRepetidoException;
import excepciones.PasswordsNoCoincidenException;
import logica.Fabrica;
import logica.interfaces.IControladorUsuario;

@WebServlet("/altaUsuario")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class altaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (null != request.getSession().getAttribute("usuario_logueado")) {

			// forward the control to the index.jsp
			request.setAttribute("alert", "No se puede dar de alta estando logueado");
			request.setAttribute("error", "yes");
			request.getRequestDispatcher("htmls/index.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("/htmls/altaUsuario.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// upload image
		String fileDir = getServletContext().getInitParameter("filedir");
		Part filePart = (Part) req.getSession().getAttribute("filePart");
		boolean hayImagenGuardada = true;
		if (filePart == null) {
			filePart = req.getPart("img");
			hayImagenGuardada = false;
		}
		String fileName = filePart.getSubmittedFileName();
		String newFileNameUsuario = (String) req.getSession().getAttribute("newFileNameUsuario");
		if (fileName != null && fileName != "" && !hayImagenGuardada) {

			// changes name to random string
			newFileNameUsuario = RandomStringUtils.randomAlphanumeric(8);
			req.getSession().setAttribute("newFileName", newFileNameUsuario);

			// constructs the directory path to store upload file
			// this path is relative to application's directory
			String uploadPath = getServletContext().getRealPath("") + File.separator + fileDir;

			// creates the directory if it does not exist
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// saves the image in the server
			filePart.write(uploadDir + File.separator + newFileNameUsuario);
		}
		String contraseña = req.getParameter("contraseña");
		String confContraseña = req.getParameter("confcontraseña");

		String nickname = req.getParameter("nickname");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String correo = req.getParameter("correo");
		LocalDate fecha = LocalDate.parse(req.getParameter("fecha"));
		String tipo = req.getParameter("tipo");

		String descripcion = req.getParameter("descripcion");
		String link = req.getParameter("link");
		String nacionalidad = req.getParameter("nacionalidad");
		;

		try {
			if (!contraseña.equals(confContraseña)) {
				throw new PasswordsNoCoincidenException();
			}
			Fabrica fabrica = Fabrica.getInstance();
			IControladorUsuario icu = fabrica.getIControladorUsuario();
			if (tipo.equals("turista")) {
				// crearTurista(String nickname, String nombre, String apellido, String email,
				// LocalDate nacimiento, String nacionalidadTuristar, String password, String
				// imgSrc)
				icu.crearTurista(nickname, nombre, apellido, correo, fecha, nacionalidad, contraseña,
						newFileNameUsuario);

			} else {
				// crearProveedor(String nickname, String nombre, String apellido, String email,
				// LocalDate nacimiento, String descripcionProveedor, String linkProveedor,
				// String password, String imgSrc)
				icu.crearProveedor(nickname, nombre, apellido, correo, fecha, descripcion, link, contraseña,
						newFileNameUsuario);
			}
			req.setAttribute("alert", "Se registró el usuario correctamente.");
		} catch (PasswordsNoCoincidenException e) {
			req.setAttribute("alert", e.getMessage());
			req.setAttribute("error", "yes");

			req.setAttribute("nicknameIngresado", nickname);
			req.setAttribute("nombreIngresado", nombre);
			req.setAttribute("apellidoIngresado", apellido);
			req.setAttribute("contraseñaIngresado", contraseña);
			req.setAttribute("correoIngresado", correo);
			req.setAttribute("fechaIngresado", fecha.toString());
			req.setAttribute("tipoIngresado", tipo);
			req.setAttribute("descripcionIngresado", descripcion);
			req.setAttribute("linkIngresado", link);
			req.setAttribute("nacionalidadIngresado", nacionalidad);
			req.getSession().setAttribute("filePartIngresado", filePart);
		} catch (NickameRepetidoException e) {
			req.setAttribute("alert", "Error: ya existe un usuario con ese nickname.");
			req.setAttribute("error", "yes");

			req.setAttribute("nicknameIngresado", nickname);
			req.setAttribute("nombreIngresado", nombre);
			req.setAttribute("apellidoIngresado", apellido);
			req.setAttribute("contraseñaIngresado", contraseña);
			req.setAttribute("correoIngresado", correo);
			req.setAttribute("fechaIngresado", fecha.toString());
			req.setAttribute("tipoIngresado", tipo);
			req.setAttribute("descripcionIngresado", descripcion);
			req.setAttribute("linkIngresado", link);
			req.setAttribute("nacionalidadIngresado", nacionalidad);
			req.getSession().setAttribute("filePartIngresado", filePart);

		} catch (EmailRepetidoException e) {
			req.setAttribute("alert", "Error: ya existe un usuario con ese correo.");
			req.setAttribute("error", "yes");

			req.setAttribute("nicknameIngresado", nickname);
			req.setAttribute("nombreIngresado", nombre);
			req.setAttribute("apellidoIngresado", apellido);
			req.setAttribute("contraseñaIngresado", contraseña);
			req.setAttribute("correoIngresado", correo);
			req.setAttribute("fechaIngresado", fecha.toString());
			req.setAttribute("tipoIngresado", tipo);
			req.setAttribute("descripcionIngresado", descripcion);
			req.setAttribute("linkIngresado", link);
			req.setAttribute("nacionalidadIngresado", nacionalidad);
			req.getSession().setAttribute("filePartIngresado", filePart);
		}
		req.getRequestDispatcher("/htmls/altaUsuario.jsp").forward(req, resp);
	}

}
