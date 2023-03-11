package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoActividades;
import dao.DaoUsuarios;
import servidor.DataActividadTuristica;
import servidor.DataActividadTuristicaP;
import servidor.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class consultaActividadServlet
 */
@WebServlet(name = "consultaUsuario", urlPatterns = { "/consultaUsuario" })
public class consultaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// nos pasa un nickname y tenemos que ver si es turista, proveedor o no existe.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getSession().setAttribute("seguirUsuarios", request.getParameter("usuario"));
		// TODO Auto-generated method stub
		DaoUsuarios dao = new DaoUsuarios();
		String usuario = request.getParameter("usuario");
		String tipoUsuarios = request.getParameter("tipo");
		if (usuario == null || usuario.equals("")) {
			List<servidor.DataTurista> turistas = new ArrayList<>();
			List<servidor.DataProveedor> proveedores = new ArrayList<>();
			if (tipoUsuarios == null || tipoUsuarios.equals("Turistas y Proveedores")) {
				turistas = dao.obtenerTuristas();
				proveedores = dao.obtenerProveedores();
				tipoUsuarios = "Turistas y Proveedores";
			} else if (tipoUsuarios.equals("Turistas")) {
				turistas = dao.obtenerTuristas();
			} else {
				proveedores = dao.obtenerProveedores();
			}
			request.setAttribute("turistas", turistas);
			request.setAttribute("tipo", tipoUsuarios);

			request.setAttribute("proveedores", proveedores);
			request.getRequestDispatcher("/htmls/usuarios.jsp").forward(request, response);
			return;
		}
		String logueado = (String) request.getSession().getAttribute("usuario_logueado");
		try {
			if (dao.obtenerTurista(request.getParameter("usuario")) != null) {
				// agregar al request (dtturista, Lista<DtInscripción> y Lista<DtPaquete>) y
				// redireccionar al consultaTurista.jsp
				servidor.DataTurista turista = dao.obtenerTurista(request.getParameter("usuario"));
				List<servidor.DataInscripcion> inscripciones = turista.getInscripciones();
				List<servidor.DataCompra> compras = turista.getCompras();
				request.getSession().setAttribute("cantSeguidos", dao.cantSeguidos(turista.getNickname()));
				request.getSession().setAttribute("cantSeguidores", dao.cantSeguidores(turista.getNickname()));
				if (logueado == null) {
					request.getSession().setAttribute("sigue", false);
				} else {
					request.getSession().setAttribute("sigue", dao.sigueAUsuario(logueado, usuario));
				}
				request.getSession().setAttribute("turista", turista);
				request.setAttribute("turista", turista);
				request.getSession().setAttribute("turistaS", turista);
				request.setAttribute("inscripciones", inscripciones);
				request.setAttribute("compras", compras);
				request.getSession().setAttribute("seguidores_prov", dao.getSeguidoresProv(turista.getNickname()));
				request.getSession().setAttribute("seguidores_tur", dao.getSeguidoresTur(turista.getNickname()));
				request.getSession().setAttribute("seguidos_prov", dao.getSeguidosProv(turista.getNickname()));
				request.getSession().setAttribute("seguidos_tur", dao.getSeguidosTur(turista.getNickname()));
				request.getRequestDispatcher("/htmls/consultaTurista.jsp").forward(request, response);
			} else if (dao.obtenerProveedor(usuario) != null
					&& usuario.equals(request.getSession().getAttribute("usuario_logueado"))) {
				// agregar al request ( DtProveedor, Lista<DtActividad> y Lista<DtSalidas>) y
				// redireccionar al consultaProveedor.jsp
				servidor.DataProveedor proveedor = dao.obtenerProveedor(request.getParameter("usuario"));
				// obtener finalizadas y no finalizadas por separado
				DaoActividades daoAct = new DaoActividades();
				List<DataActividadTuristica> actividades = daoAct
						.obtenerActividadesNoFinalizadas(request.getParameter("usuario"));

				List<DataActividadTuristicaP> actividades_finalizadas = daoAct
						.obtenerActividadesFinalizadas(request.getParameter("usuario"), "");

				// List<DataActividadTuristica> actividades = dao
				// .obternerActividadesProveedor(request.getParameter("usuario"));
				List<servidor.DataSalidaTuristica> salidas = dao
						.obtenerSalidasProveedor(request.getParameter("usuario"));
				request.getSession().setAttribute("cantSeguidos", dao.cantSeguidos(proveedor.getNickname()));
				request.getSession().setAttribute("cantSeguidores", dao.cantSeguidores(proveedor.getNickname()));
				request.getSession().setAttribute("proveedor", proveedor);

				if (logueado == null) {
					request.getSession().setAttribute("sigue", false);
				} else {
					request.getSession().setAttribute("sigue", dao.sigueAUsuario(logueado, usuario));
				}
				request.setAttribute("proveedor", proveedor);
				request.setAttribute("actividades", actividades);
				request.setAttribute("actividades_finalizadas", actividades_finalizadas);
				request.setAttribute("salidas", salidas);
				request.getSession().setAttribute("seguidores_prov", dao.getSeguidoresProv(proveedor.getNickname()));
				request.getSession().setAttribute("seguidores_tur", dao.getSeguidoresTur(proveedor.getNickname()));
				request.getSession().setAttribute("seguidos_prov", dao.getSeguidosProv(proveedor.getNickname()));
				request.getSession().setAttribute("seguidos_tur", dao.getSeguidosTur(proveedor.getNickname()));
				request.getRequestDispatcher("/htmls/consultaProveedor.jsp").forward(request, response);

			} else if (dao.obtenerProveedor(request.getParameter("usuario")) != null
					&& !usuario.equals(request.getSession().getAttribute("usuario_logueado"))) {
				// agregar al request ( DtProveedor, Lista<DtActividad> y Lista<DtSalidas>) y
				// redireccionar al consultaProveedor.jsp
				servidor.DataProveedor proveedor = dao.obtenerProveedor(request.getParameter("usuario"));
				List<DataActividadTuristica> actividades = dao.obternerActividadesConfirmadasProveedor(usuario);
				List<servidor.DataSalidaTuristica> salidas = dao
						.obtenerSalidasProveedor(request.getParameter("usuario"));
				request.getSession().setAttribute("cantSeguidos", dao.cantSeguidos(proveedor.getNickname()));
				request.getSession().setAttribute("cantSeguidores", dao.cantSeguidores(proveedor.getNickname()));
				request.getSession().setAttribute("proveedor", proveedor);
				if (logueado == null) {
					request.getSession().setAttribute("sigue", false);
				} else {
					request.getSession().setAttribute("sigue", dao.sigueAUsuario(logueado, usuario));
				}
				request.setAttribute("proveedor", proveedor);
				request.setAttribute("actividades", actividades);
				request.setAttribute("salidas", salidas);
				request.getSession().setAttribute("seguidores_prov", dao.getSeguidoresProv(proveedor.getNickname()));
				request.getSession().setAttribute("seguidores_tur", dao.getSeguidoresTur(proveedor.getNickname()));
				request.getSession().setAttribute("seguidos_prov", dao.getSeguidosProv(proveedor.getNickname()));
				request.getSession().setAttribute("seguidos_tur", dao.getSeguidosTur(proveedor.getNickname()));
				request.getRequestDispatcher("/htmls/consultaProveedor.jsp").forward(request, response);
			} else {
				// error usuario no encontrado
			}
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
