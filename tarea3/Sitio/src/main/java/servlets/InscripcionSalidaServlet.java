package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import dao.DaoSalidas;
import dao.DaoUsuarios;
import servidor.ActividadNoExisteException_Exception;
import servidor.DataInscripcion;
import servidor.DataTurista;
import servidor.InscripcionRepetidaException_Exception;
import servidor.NoExisteEntidadException_Exception;
import servidor.NoHayCapacidadException_Exception;
import servidor.PaqueteVencidoException_Exception;
import servidor.UsuarioNoExisteException_Exception;

@WebServlet("/inscripcionSalida")
public class InscripcionSalidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!request.getSession().getAttribute("tipo_usuario").equals("Turista")) {

			request.getRequestDispatcher("/htmls/index.jsp").forward(request, response);

		} else {

			DaoSalidas dao = new DaoSalidas();
			int cantTuristas = Integer.parseInt(request.getParameter("cTuristas"));
			Object temp = request.getParameter("formaPago");
			int formaPago = 0;
			if (temp != null) {
				formaPago = Integer.parseInt((String) temp);
			}

			String user = (String) request.getSession().getAttribute("usuario_logueado");
			String nombreSalida = (String) request.getSession().getAttribute("nombreSalida");
			if (formaPago == 0) {
				try {
					dao.inscribirTuristaSalida(user, nombreSalida, cantTuristas);
					request.setAttribute("alert", "La inscripcion fue registrada.");
				} catch (NoExisteEntidadException_Exception e) {
					// IGNORE
				} catch (InscripcionRepetidaException_Exception e) {
					request.setAttribute("alert", "Ya existe una inscripcion del turista para esta salida.");
				} catch (NoHayCapacidadException_Exception e) {
					request.setAttribute("alert",
							"No se pudo realizar la inscripcion, no hay capacidad suficiente en el paquete.");
				}
			} else { // paga con paquete
				String paquete = request.getParameter("paquete");
				try {
					dao.inscribirTuristaSalidaPaquete(user, nombreSalida, paquete, cantTuristas);
					request.setAttribute("alert", "La inscripcion fue registrada.");
				} catch (NoExisteEntidadException_Exception | ActividadNoExisteException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InscripcionRepetidaException_Exception e) {
					request.setAttribute("alert", "Ya existe una inscripcion del turista para esta salida.");
				} catch (NoHayCapacidadException_Exception e) {
					request.setAttribute("alert",
							"No se pudo realizar la inscripcion, no hay capacidad suficiente en el paquete.");
				} catch (PaqueteVencidoException_Exception e) {
					request.setAttribute("alert", "No se pudo realizar la inscripcion, el paquete no esta vigente.");
				}
			}
			request.getRequestDispatcher("consultaSalida?salida=" + nombreSalida).forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DaoUsuarios dao = new DaoUsuarios();

			String usuario = request.getParameter("usuario");
			String salida = request.getParameter("salida");

			DataTurista turista = dao.obtenerTurista(usuario);
			List<DataInscripcion> inscripciones = dao.obternerInscripcionesTurista(request.getParameter("usuario"));
			DataInscripcion inscripcion = null;
			for (DataInscripcion insc : inscripciones) {
				if (insc.getSalida().getNombre().equals(salida)) {
					inscripcion = insc;
					break;
				}
			}

			ConverterProperties props = new ConverterProperties();
			props.setBaseUri("./img");
//            

			String html = "<h1>Inscripción a salida turistica: <br>" + inscripcion.getSalida().getNombre() + "</h1>";
			html += "<p>Actividad: " + inscripcion.getSalida().getActividad() + "</p>";
			html += "<p>Turista: " + turista.getNombre() + " " + turista.getApellido() + "</p>";
			html += "<p>Fecha de inscripción: " + inscripcion.getFechaInscripcion().toString() + "</p>";
			html += "<p>Cantidad de turistas: " + inscripcion.getCantTuristas() + "</p>";
			html += "<p>Costo: $" + inscripcion.getCosto() + "</p>";

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
			// Document doc = new Document(pdfDoc);
			Document doc = HtmlConverter.convertToDocument(html, new PdfWriter(baos), props);
			doc.close();

			// setting some response headers
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// setting the content type
			response.setContentType("application/pdf");
			// the contentlength
			response.setContentLength(baos.size());
			// write ByteArrayOutputStream to the ServletOutputStream
			OutputStream ost = response.getOutputStream();
			baos.writeTo(ost);
			ost.flush();
			ost.close();
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
