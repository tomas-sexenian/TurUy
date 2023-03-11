
package servlets;

import java.io.IOException;
import java.io.InputStream;
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

import dao.DaoActividades;
import dao.DaoSalidas;
import servidor.ActividadNoExisteException_Exception;
import servidor.DataActividadTuristica;
import servidor.IOException_Exception;
import servidor.PublicadorImagenes;
import servidor.PublicadorImagenesService;
import servidor.SalidaRepetidaException_Exception;

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
		DaoActividades daoActividades = new DaoActividades();
		String departamento = req.getParameter("fdept");
		if (departamento != null) {
			List<DataActividadTuristica> actividades = daoActividades.obtenerActividades(departamento, "");
			req.setAttribute("actividades", actividades);
			req.getSession().setAttribute("deptoSelected", departamento);
			req.getRequestDispatcher("/htmls/altaSalida.jsp").forward(req, resp);
			return;
		}

		// upload image
		Part filePart = (Part) req.getSession().getAttribute("filePart");
		if (filePart == null) {
			filePart = req.getPart("img");
		}
		String fileName = filePart.getSubmittedFileName();
		if (fileName != null && fileName != "") {
			req.getSession().setAttribute("imgAltaSalida", fileName);
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
		}

		String nombre = req.getParameter("nombre");
		int cantMaxTuristas = Integer.parseInt(req.getParameter("cTuristas"));
		String lugarSalida = req.getParameter("lugar");
		LocalDate fechaSalida = LocalDate.parse(req.getParameter("fecha"));
		String horaSalida = req.getParameter("hora");
		String actividad = req.getParameter("actividad");
		try {
			DaoSalidas daoSalidas = new DaoSalidas();

			daoSalidas.crearSalida(nombre, cantMaxTuristas, lugarSalida, fechaSalida, horaSalida, actividad, fileName);
			req.setAttribute("alert", "Se agrego la salida correctamente.");
			req.getSession().removeAttribute("filePart");
			req.getSession().removeAttribute("imgAltaSalida");
			req.getSession().setAttribute("deptoSelected", "");

		} catch (SalidaRepetidaException_Exception e) {
			req.setAttribute("alert", "Error: ya existe una salida con ese nombre.");
			req.setAttribute("error", "yes");
			String deptoAltaSalida = (String) req.getSession().getAttribute("deptoSelected");
			req.setAttribute("deptoAltaSalida", deptoAltaSalida);
			List<DataActividadTuristica> actividades = daoActividades.obtenerActividades(deptoAltaSalida, "");
			req.setAttribute("actividades", actividades);
			req.setAttribute("cantMax", cantMaxTuristas);
			req.setAttribute("fechaAltaSalida", fechaSalida);
			req.setAttribute("horaAltaSalida", horaSalida);
			req.setAttribute("actividadAltaSalida", actividad);
			req.setAttribute("lugarAltaSalida", lugarSalida);
			req.getSession().setAttribute("filePart", filePart);

		} catch (ActividadNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("/htmls/altaSalida.jsp").forward(req, resp);
	}

}
