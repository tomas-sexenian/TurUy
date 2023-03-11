package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.UsuarioNoExisteException;
import logica.Fabrica;
import logica.datatypes.DataProveedor;
import logica.datatypes.DataTurista;
import logica.interfaces.IControladorUsuario;

/**
 * Servlet implementation class altaUsuarioDinamico
 */
@WebServlet("/altaUsuarioDinamico")
public class altaUsuarioDinamico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaUsuarioDinamico() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Fabrica fabrica = Fabrica.getInstance();
		IControladorUsuario icu = fabrica.getIControladorUsuario();
		String nickname = request.getParameter("nickname");
		if (nickname != null && nickname.length() > 0) {
			try {
				DataTurista datat = icu.verInfoTurista(nickname);
				out.println("El usuario ya existe");
			} catch (UsuarioNoExisteException e) {
				try {
					DataProveedor dataP = icu.verInfoProveedor(nickname);
					//System.out.println("taken");
					out.println("El usuario ya existe");
				} catch (UsuarioNoExisteException e1) {
					//System.out.println("not_taken");
					out.println("El usuario no existe");
				}
			}
		} else {
			out.println("");
		}
		String correo = request.getParameter("correo");
		
		if (correo != null && correo.length() > 0) {
			if (icu.existeEmail(correo)) {
				out.println("El email ya esta registrado");
			} else {
				out.println("El email no esta en uso");
			}
		} else {
			out.println("");
		}
	}

}
