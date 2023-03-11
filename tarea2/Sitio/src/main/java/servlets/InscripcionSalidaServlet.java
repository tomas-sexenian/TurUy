package servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.ActividadNoExisteException;
import excepciones.InscripcionRepetidaException;
import excepciones.NoExisteEntidadException;
import excepciones.NoHayCapacidadException;
import logica.Fabrica;
import logica.interfaces.IControladorSalidas;

@WebServlet("/inscripcionSalida")
public class InscripcionSalidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!request.getSession().getAttribute("tipo_usuario").equals("Turista")) {

			request.getRequestDispatcher("/htmls/index.jsp").forward(request, response);

		} else {

			Fabrica fabrica = Fabrica.getInstance();
			IControladorSalidas iControladorSalidas = fabrica.getIControladorSalidas();

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
					iControladorSalidas.inscribirTuristaSalida(user, nombreSalida, cantTuristas, LocalDate.now());
					request.setAttribute("alert", "La inscripcion fue registrada.");
				} catch (NoExisteEntidadException e) {
					// IGNORE
				} catch (InscripcionRepetidaException e) {
					request.setAttribute("alert", "Ya existe una inscripcion del turista para esta salida.");
				} catch (NoHayCapacidadException e) {
					request.setAttribute("alert",
							"No se pudo realizar la inscripcion, no hay capacidad suficiente en el paquete.");
				}
			} else { // paga con paquete
				String paquete = request.getParameter("paquete");
				try {
					iControladorSalidas.inscribirTuristaSalidaPaquete(user, nombreSalida, paquete, cantTuristas,
							LocalDate.now());
					request.setAttribute("alert", "La inscripcion fue registrada.");
				} catch (NoExisteEntidadException | ActividadNoExisteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InscripcionRepetidaException e) {
					request.setAttribute("alert", "Ya existe una inscripcion del turista para esta salida.");
				} catch (NoHayCapacidadException e) {
					request.setAttribute("alert",
							"No se pudo realizar la inscripcion, no hay capacidad suficiente en el paquete.");
				}
			}
			request.getRequestDispatcher("consultaSalida?salida=" + nombreSalida).forward(request, response);
		}
	}

}
