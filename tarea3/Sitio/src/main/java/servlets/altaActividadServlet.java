package servlets;

import java.io.IOException;
import java.io.InputStream;
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

import dao.DaoActividades;
import servidor.ActividadRepetidaException_Exception;
import servidor.IOException_Exception;
import servidor.PublicadorImagenes;
import servidor.PublicadorImagenesService;

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

		Part filePart = (Part) req.getSession().getAttribute("filePart");
		if (filePart == null) {
			filePart = req.getPart("img");
		}

		String fileName = filePart.getSubmittedFileName();
		if (fileName != null && fileName != "") {
			req.getSession().setAttribute("imgAltaAct", fileName);

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

		String prov = (String) req.getSession().getAttribute("usuario_logueado");
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		String ciudad = req.getParameter("ciudad");
		String[] cats = req.getParameterValues("categoriasMultiple");
		List<String> categorias = Arrays.asList(cats);
		String dpto = req.getParameter("fdept");
		int duracion = Integer.parseInt(req.getParameter("duracion"));
		double costo = Double.parseDouble(req.getParameter("costo"));
		String videoURL = req.getParameter("video");

		try {
			if (videoURL == null) {
				videoURL = "";
			}

			DaoActividades daoActividades = new DaoActividades();
			daoActividades.crearActividad(dpto, descripcion, nombre, duracion, costo, ciudad, prov, categorias,
					fileName, videoURL);
			req.getSession().removeAttribute("filePart");
			req.getSession().removeAttribute("imgAltaAct");
			req.setAttribute("alert", "Se agrego la actividad correctamente.");
		} catch (ActividadRepetidaException_Exception e) {
			req.setAttribute("alert", "Error: ya existe una actividad con ese nombre.");
			req.setAttribute("error", "yes");
			req.setAttribute("descripcionAltaAct", descripcion);
			req.setAttribute("ciudadAltaAct", ciudad);
			req.setAttribute("categoriasAltaAct", cats);
			req.setAttribute("departamentoAltaAct", dpto);
			req.setAttribute("duracionAltaAct", duracion);
			req.setAttribute("costoAltaAct", costo);
			req.setAttribute("vidAltaAct", videoURL);
			req.getSession().setAttribute("filePart", filePart);
		}
		req.getRequestDispatcher("/htmls/altaActividad.jsp").forward(req, resp);
	}

}
