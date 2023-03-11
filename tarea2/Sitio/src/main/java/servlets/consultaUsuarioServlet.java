package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApplicationDao;
import logica.datatypes.DataActividadTuristica;
import logica.datatypes.DataCompra;
import logica.datatypes.DataInscripcion;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataSalidaTuristica;
import logica.datatypes.DataTurista;

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
		
		// TODO Auto-generated method stub
		ApplicationDao dao = new ApplicationDao();
		String usuario = request.getParameter("usuario");
		String tipoUsuarios = request.getParameter("tipo");
		if (usuario == null) {
			List<DataTurista> turistas = new ArrayList<>();
			List<DataProveedor> proveedores = new ArrayList<>();
			if (tipoUsuarios == null || tipoUsuarios.equals("Turistas y Proveedores")){
				turistas = dao.obtenerTuristas();
				proveedores = dao.obtenerProveedores();
				tipoUsuarios = "Turistas y Proveedores";
			}else if (tipoUsuarios.equals("Turistas")) {
				turistas = dao.obtenerTuristas();
			}else {
				proveedores = dao.obtenerProveedores();
			}
			request.setAttribute("turistas", turistas);
			request.setAttribute("tipo", tipoUsuarios);

			request.setAttribute("proveedores", proveedores);
			request.getRequestDispatcher("/htmls/usuarios.jsp").forward(request, response);
		}
		
		if (dao.obtenerTurista(request.getParameter("usuario")) != null) {
			// agregar al request  (dtturista, Lista<DtInscripción> y Lista<DtPaquete>) y redireccionar al consultaTurista.jsp
			DataTurista turista = dao.obtenerTurista(request.getParameter("usuario"));
			List<DataInscripcion> inscripciones = dao.obternerInscripcionesTurista(request.getParameter("usuario"));
			List<DataCompra> compras = dao.obtenerComprasTurista(request.getParameter("usuario"));
			
			request.setAttribute("turista", turista);
			request.setAttribute("inscripciones", inscripciones);
			request.setAttribute("compras", compras);
			request.getRequestDispatcher("/htmls/consultaTurista.jsp").forward(request, response);
		
		} else if (dao.obtenerProveedor(request.getParameter("usuario")) != null) {
			// agregar al request ( DtProveedor, Lista<DtActividad> y Lista<DtSalidas>) y redireccionar al consultaProveedor.jsp
			DataProveedor proveedor = dao.obtenerProveedor(request.getParameter("usuario"));
			List<DataActividadTuristica> actividades = dao.obternerActividadesProveedor(request.getParameter("usuario"));
			List<DataSalidaTuristica> salidas = dao.obtenerSalidasProveedor(request.getParameter("usuario"));
			
			request.setAttribute("proveedor", proveedor);
			request.setAttribute("actividades", actividades);
			request.setAttribute("salidas", salidas);
			request.getRequestDispatcher("/htmls/consultaProveedor.jsp").forward(request, response);
		} else {
			// error usuario no encontrado
		}
	}

}
