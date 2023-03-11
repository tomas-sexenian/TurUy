
package servlets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.RandomStringUtils;

import dao.ApplicationDao;
import excepciones.ActividadNoExisteException;
import excepciones.SalidaRepetidaException;
import logica.Fabrica;
import logica.datatypes.DataActividadTuristica;
import logica.interfaces.IControladorSalidas;

@WebServlet("/altaSalida")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class altaSalidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!request.getSession().getAttribute("tipo_usuario").equals("Proveedor")) {

			// forward the control to the index.jsp
			request.getRequestDispatcher("htmls/index.jsp").forward(request, response);

		} else {
			request.getSession().setAttribute("deptoSelected", "");
			request.getRequestDispatcher("/htmls/altaSalida.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ApplicationDao dao = new ApplicationDao();
		String departamento = req.getParameter("fdept");
		if (departamento != null) {
			List<DataActividadTuristica> actividades = dao.obtenerActividades(departamento, "");
			req.setAttribute("actividades", actividades);
			req.getSession().setAttribute("deptoSelected", departamento);
			req.getRequestDispatcher("/htmls/altaSalida.jsp").forward(req, resp);
			return;
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
		String newFileNameSalida = (String) req.getSession().getAttribute("newFileName");
		if (fileName != null && fileName != "" && !hayImagenGuardada) {

			// changes name to random string
			newFileNameSalida = RandomStringUtils.randomAlphanumeric(8);
			req.getSession().setAttribute("newFileName", newFileNameSalida);

			// constructs the directory path to store upload file
			// this path is relative to application's directory
			String uploadPath = getServletContext().getRealPath("") + File.separator + fileDir;

			// creates the directory if it does not exist
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// saves the image in the server
			filePart.write(uploadDir + File.separator + newFileNameSalida);
		}

		String nombre = req.getParameter("nombre");
		int cantMaxTuristas = Integer.parseInt(req.getParameter("cTuristas"));
		String lugarSalida = req.getParameter("lugar");
		LocalDate fechaSalida = LocalDate.parse(req.getParameter("fecha"));
		String horaSalida = req.getParameter("hora");
		String actividad = req.getParameter("actividad");
		try {
			Fabrica fabrica = Fabrica.getInstance();
			IControladorSalidas ics = fabrica.getIControladorSalidas();
			ics.crearSalida(nombre, cantMaxTuristas, lugarSalida, fechaSalida, horaSalida, actividad,
					newFileNameSalida);
			req.setAttribute("alert", "Se agrego la salida correctamente.");
			req.getSession().setAttribute("deptoSelected", "");

		} catch (SalidaRepetidaException e) {
			req.setAttribute("alert", "Error: ya existe una salida con ese nombre.");
			req.setAttribute("error", "yes");
			String deptoAltaSalida = (String) req.getSession().getAttribute("deptoSelected");
			req.setAttribute("deptoAltaSalida", deptoAltaSalida);

			List<DataActividadTuristica> actividades = dao.obtenerActividades(deptoAltaSalida, "");
			req.setAttribute("actividades", actividades);
			req.setAttribute("cantMax", cantMaxTuristas);
			req.setAttribute("fechaAltaSalida", fechaSalida);
			req.setAttribute("horaAltaSalida", horaSalida);
			req.setAttribute("actividadAltaSalida", actividad);
			req.setAttribute("lugarAltaSalida", lugarSalida);
			req.setAttribute("imgAltaSalida", fileName);
			req.getSession().setAttribute("filePart", filePart);

		} catch (ActividadNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("/htmls/altaSalida.jsp").forward(req, resp);
	}

}
