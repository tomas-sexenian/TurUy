package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public AuthenticationFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httprequest = (HttpServletRequest) request;
		if (!httprequest.getRequestURI().startsWith("/SitioMovil/Login")
				&& !httprequest.getRequestURI().equals("/SitioMovil/")
				&& !httprequest.getRequestURI().startsWith("/SitioMovil/lib/")
				&& !httprequest.getRequestURI().startsWith("/SitioMovil/img/")
				&& !httprequest.getRequestURI().startsWith("/SitioMovil/css/")) {
			HttpSession session = httprequest.getSession();
			String tipoString = (String) session.getAttribute("tipo_usuario");
			if (tipoString == null || !tipoString.equals("Turista")) {
				request.getRequestDispatcher("Login").forward(request, response);
				return;
			}
		}

		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
