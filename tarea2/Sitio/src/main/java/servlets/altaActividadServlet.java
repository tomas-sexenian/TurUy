package servlets;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;

import excepciones.ActividadRepetidaException;
import logica.Fabrica;
import logica.interfaces.IControladorActividades;

@WebServlet("/altaActividad")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class altaActividadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!request.getSession().getAttribute("tipo_usuario").equals("Proveedor")) {

			request.getRequestDispatcher("/htmls/index.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("/htmls/altaActividad.jsp").forward(request, response);
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
		String newFileNameAct = (String) req.getSession().getAttribute("newFileName");
		if (fileName != null && fileName != "" && !hayImagenGuardada) {

			// changes name to random string
			newFileNameAct = RandomStringUtils.randomAlphanumeric(8);
			req.getSession().setAttribute("newFileName", newFileNameAct);

			// constructs the directory path to store upload file
			// this path is relative to application's directory
			String uploadPath = getServletContext().getRealPath("") + File.separator + fileDir;

			// creates the directory if it does not exist
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// saves the image in the server
			filePart.write(uploadDir + File.separator + newFileNameAct);
		}

		String prov = (String) req.getSession().getAttribute("usuario_logueado");
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		String ciudad = req.getParameter("ciudad");
		String[] cats = req.getParameterValues("categoriasMultiple");
		List<String> categorias = Arrays.asList(cats);
		String dpto = req.getParameter("fdept");
		int duracion = Integer.parseInt(req.getParameter("duracion"));
		double costo = Double.parseDouble(req.getParameter("costo"));

		try {
			Fabrica fabrica = Fabrica.getInstance();
			IControladorActividades ica = fabrica.getIControladorActividades();
			ica.crearActividad(dpto, descripcion, nombre, duracion, costo, ciudad, prov, categorias, newFileNameAct);
			req.setAttribute("alert", "Se agrego la actividad correctamente.");
		} catch (ActividadRepetidaException e) {
			req.setAttribute("alert", "Error: ya existe una actividad con ese nombre.");
			req.setAttribute("error", "yes");
			req.setAttribute("descripcionAltaAct", descripcion);
			req.setAttribute("ciudadAltaAct", ciudad);
			req.setAttribute("categoriasAltaAct", cats);
			req.setAttribute("departamentoAltaAct", dpto);
			req.setAttribute("duracionAltaAct", duracion);
			req.setAttribute("costoAltaAct", costo);
			req.getSession().setAttribute("filePart", filePart);
			req.setAttribute("imgAltaAct", fileName);

		}
		req.getRequestDispatcher("/htmls/altaActividad.jsp").forward(req, resp);
	}

}
