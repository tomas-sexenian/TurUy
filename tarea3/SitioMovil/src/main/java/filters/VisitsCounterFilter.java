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

import dao.ApplicationDao;

/**
 * Servlet Filter implementation class VisitsCounterFilter
 */
@WebFilter("/VisitsCounterFilter")
public class VisitsCounterFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public VisitsCounterFilter() {
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
		// TODO Auto-generated method stub
		HttpServletRequest httprequest = (HttpServletRequest) request;

		// salida
		if (httprequest.getRequestURI().startsWith("/SitioMovil/consultaSalida")) {
			String salida = request.getParameter("salida");
			ApplicationDao dao = new ApplicationDao();
			dao.updateVisitsSalida(salida);
		}

		if (httprequest.getRequestURI().startsWith("/SitioMovil/consultaActividad")) {
			String actividad = request.getParameter("actividad");
			ApplicationDao dao = new ApplicationDao();
			dao.updateVisitsActividad(actividad);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
