
package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;

import dao.DaoUsuarios;
import servidor.EmailRepetidoException_Exception;
import servidor.IOException_Exception;
import servidor.NickameRepetidoException_Exception;
import servidor.PublicadorImagenes;
import servidor.PublicadorImagenesService;

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
		Part filePart = (Part) req.getSession().getAttribute("filePart");
		if (filePart == null) {
			filePart = req.getPart("img");
		}
		String fileName = filePart.getSubmittedFileName();
		if (fileName != null && fileName != "") {
			req.getSession().setAttribute("imgAltaUsuario", fileName);

			fileName = RandomStringUtils.randomAlphanumeric(8);

			InputStream fileStream = filePart.getInputStream();

			byte[] byteArray = new byte[fileStream.available()];
			fileStream.read(byteArray);
			fileStream.close();

			PublicadorImagenesService service = new PublicadorImagenesService();
			PublicadorImagenes port = service.getPublicadorImagenesPort();

			try {
				port.uploadFile(byteArray, fileName);
			} catch (IOException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
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
		req.setAttribute("nombreIngresado", nombre);
		req.setAttribute("apellidoIngresado", apellido);
		req.setAttribute("correoIngresado", correo);
		req.setAttribute("fechaIngresado", fecha.toString());
		req.setAttribute("tipoIngresado", tipo);
		req.setAttribute("descripcionIngresado", descripcion);
		req.setAttribute("linkIngresado", link);
		req.setAttribute("nacionalidadIngresado", nacionalidad);
		req.getSession().setAttribute("filePartIngresado", filePart);
		if (!confContraseña.equals(contraseña)) {
			req.setAttribute("alert", "Error: Las contraseñas no coinciden");
			req.setAttribute("error", "yes");
			req.getSession().setAttribute("filePart", filePart);
			req.getRequestDispatcher("/htmls/altaUsuario.jsp").forward(req, resp);
			return;
		}
		try {
			DaoUsuarios dao = new DaoUsuarios();
			if (tipo.equals("turista")) {
				dao.crearTurista(nickname, nombre, apellido, correo, fecha, nacionalidad, contraseña, fileName);
			} else {
				if (link == null) {
					link = "";
				}
				dao.crearProveedor(nickname, nombre, apellido, correo, fecha, descripcion, link, contraseña, fileName);
			}
			req.getSession().removeAttribute("filePart");
			req.getSession().removeAttribute("imgAltaUsuario");
			req.setAttribute("alert", "Se registró el usuario correctamente.");
		} catch (NickameRepetidoException_Exception e) {
			req.setAttribute("alert", "Error: ya existe un usuario con ese nickname.");
			req.setAttribute("error", "yes");
			req.getSession().setAttribute("filePart", filePart);
		} catch (EmailRepetidoException_Exception e) {
			req.setAttribute("alert", "Error: ya existe un usuario con ese correo.");
			req.setAttribute("error", "yes");
			req.getSession().setAttribute("filePart", filePart);
		}
		req.getRequestDispatcher("/htmls/altaUsuario.jsp").forward(req, resp);
	}

}
