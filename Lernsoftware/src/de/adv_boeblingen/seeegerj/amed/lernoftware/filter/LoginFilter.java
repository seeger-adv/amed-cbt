package de.adv_boeblingen.seeegerj.amed.lernoftware.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seeegerj.amed.lernoftware.controller.UserController;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Session;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.VariableMap;

@WebFilter(urlPatterns = "/Lesson/*")
public class LoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		Session session = (Session) ((HttpServletRequest) req).getSession()
				.getAttribute("session");
		if (!UserController.isValidSession(session)) {
			((HttpServletResponse) resp).sendRedirect("/Lernsoftware/login");
		} else {
			VariableMap map = VariableMap.getMappingFromRequest(req);
			map.put("username", session.getUser().getUsername());
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
