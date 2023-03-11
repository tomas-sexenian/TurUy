package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoActividades;
import servidor.DataActividad;
import servidor.DataActividadTuristica;
import servidor.Paquete;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "search", urlPatterns = { "/search" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String searchString = req.getParameter("search").toLowerCase();

		String dpto = req.getParameter("departamento");
		String cat = req.getParameter("categorias");
		String sortBy = req.getParameter("sortBy");

		if (dpto == null) {
			dpto = "";
		}
		if (cat == null) {
			cat = "";
		}
		if (sortBy == null) {
			sortBy = "alfabetico"; // default
		}
		DaoActividades daoActividades = new DaoActividades();
		List<servidor.DataActividadTuristica> actividadesTodas = daoActividades.obtenerActividades(dpto, cat);
		List<servidor.DataPaqueteActividades> paquetesTodos = daoActividades.obtenerPaquetes();
		List<servidor.DataActividad> actividadesFiltradas = new ArrayList<>();
		List<servidor.Paquete> paquetesFiltrados = new ArrayList<>();
		for (servidor.DataActividadTuristica act : actividadesTodas) {
			if (act.getNombre().toLowerCase().contains(searchString)) {
				DataActividad dact = new DataActividad(act.getDepartamento(), act.getNombre(), act.getProveedor(),
						act.getDescripcion(), act.getDuracion(), act.getCosto(), act.getCiudad(), act.getFechaAlta(),
						act.getSalidas(), act.getEstado(), act.getCategorias(), act.getImageSrc(), act.getVideo(),
						act.getCantFavoritos(), act.getPaquetes());
				actividadesFiltradas.add(dact);
			}
		}
		for (servidor.DataPaqueteActividades paq : paquetesTodos) {
			List<String> categoriasPaquete = new ArrayList<>();
			List<String> dptosPaquete = new ArrayList<>();
			for (DataActividadTuristica actividadTuristica : paq.getActividades()) {
				categoriasPaquete.addAll(actividadTuristica.getCategorias());
				dptosPaquete.add(actividadTuristica.getDepartamento().getNombre());
			}
			boolean agregar = false;
			for (String categoriaString : categoriasPaquete) {
				for (String dptoString : dptosPaquete) {
					if (categoriaString.contains(cat) && dptoString.contains(dpto)
							&& paq.getNombre().toLowerCase().contains(searchString)) {
						agregar = true;
						break;
					}
				}
			}
			if (agregar) {
				Paquete paquete = new Paquete(paq.getNombre(), paq.getDescripcion(), paq.getValidez(),
						paq.getDescuento(), paq.getFechaAlta(), paq.getActividades(), paq.isComprado(), paq.getImgSrc(),
						paq.getCosto());

				paquetesFiltrados.add(paquete);
			}

		}
		if (sortBy.equals("alfabetico")) {
			paquetesFiltrados.sort(Comparator.comparing(servidor.Paquete::getNombre));
			actividadesFiltradas.sort(Comparator.comparing(servidor.DataActividad::getNombre));
		}
		if (sortBy.equals("fecha")) {
			paquetesFiltrados.sort(Comparator.comparing(servidor.Paquete::getFechaAlta).reversed());
			actividadesFiltradas.sort(Comparator.comparing(servidor.DataActividad::getFechaAlta).reversed());
		}

		req.getSession().setAttribute("paquetesSearch", paquetesFiltrados);
		req.getSession().setAttribute("actividadesSearch", actividadesFiltradas);
		req.setAttribute("departamento", dpto);
		req.setAttribute("categoriaSelected", cat);
		req.setAttribute("sortBy", sortBy);
		req.setAttribute("search", searchString);
		req.getRequestDispatcher("/htmls/searchResults.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
