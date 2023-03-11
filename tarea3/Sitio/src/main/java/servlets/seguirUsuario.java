package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuarios;

/**
 * Servlet implementation class seguirUsuario
 */
@WebServlet("/seguirUsuario")
public class seguirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public seguirUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nicknameUsr = (String) request.getSession().getAttribute("usuario_logueado");
		String nicknameASeguirUsr = (String) request.getSession().getAttribute("seguirUsuarios");
		//String nicknameASeguirUsr = "lachiqui";
		DaoUsuarios dao = new DaoUsuarios();
		PrintWriter out = response.getWriter();
		int cant = dao.cantSeguidores(nicknameASeguirUsr);
		cant++;
		dao.seguirUsr(nicknameUsr, nicknameASeguirUsr);
		out.println(cant);
		}

}
