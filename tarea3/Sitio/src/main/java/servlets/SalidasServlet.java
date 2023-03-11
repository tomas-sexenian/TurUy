package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoActividades;
import dao.DaoSalidas;
import servidor.DataActividadTuristica;

/**
 * Servlet implementation class consultaSalidasServlet
 */
@WebServlet(name = "salidas", urlPatterns = { "/salidas" })
public class SalidasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DaoActividades daoActividades = new DaoActividades();
		DaoSalidas daoSalidas = new DaoSalidas();
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");

		String actividad = request.getParameter("actividad");
		List<servidor.DataSalidaTuristica> salidas = new ArrayList<servidor.DataSalidaTuristica>();

		String departamento = request.getParameter("departamento");
		if (departamento == null) {
			departamento = "";
		}
		String categoria = request.getParameter("categoria");
		if (categoria == null) {
			categoria = "";
		}
		String vigenteString = request.getParameter("soloVigentes");
		boolean soloVigentes = vigenteString != null && vigenteString.equals("on");
		if (soloVigentes) {
			request.getSession().setAttribute("soloVigentes", "on");
		} else {
			request.getSession().setAttribute("soloVigentes", "off");
		}

		List<String> actividadesLista = new ArrayList<>();
		List<DataActividadTuristica> actividadesD = daoActividades.obtenerActividades(departamento, categoria);
		for (DataActividadTuristica dact : actividadesD) {
			actividadesLista.add(dact.getNombre());
		}
		if (actividad != null && !actividad.equals("")) {
			if (soloVigentes) {
				salidas = daoSalidas.obtenerSalidasVigentes(actividad);
			} else {
				salidas = daoSalidas.obtenerSalidas(actividad);
			}

			request.getSession().setAttribute("actividad", actividad);
			request.getSession().setAttribute("departamento", departamento);
			request.getSession().setAttribute("categoria", categoria);
		} else {
			for (DataActividadTuristica dact : actividadesD) {
				List<servidor.DataSalidaTuristica> salidasAct = dact.getSalidas();
				for (servidor.DataSalidaTuristica dtsal : salidasAct) {
					if (soloVigentes) {
						LocalDate fechaSalida = LocalDate.parse(dtsal.getFechaSalida(), formatters);
						if (fechaSalida.isAfter(LocalDate.now())) {
							salidas.add(dtsal);
						}
					} else {
						salidas.add(dtsal);
					}

				}
			}
			request.getSession().setAttribute("actividad", actividad);
			request.getSession().setAttribute("departamento", departamento);
			request.getSession().setAttribute("categoria", categoria);

		}

		request.getSession().setAttribute("actividades", actividadesLista);
		request.getSession().setAttribute("salidas", salidas);
		request.getRequestDispatcher("/htmls/salidas.jsp").forward(request, response);
	}

}
