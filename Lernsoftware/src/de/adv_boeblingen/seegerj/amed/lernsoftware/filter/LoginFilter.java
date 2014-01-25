package de.adv_boeblingen.seegerj.amed.lernsoftware.filter;

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
import javax.servlet.http.HttpSession;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.UserController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.UriBuilder;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

@WebFilter(urlPatterns = { "/Lesson/*", "/Quiz/*", "/Stats" })
public class LoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpSession httpSession = httpRequest.getSession();
		Session session = (Session) httpSession.getAttribute(Constants.SESSION_PARAM);
		if (!UserController.isValidSession(session)) {
			UriBuilder uriBuilder = PathUtil.getBaseUriBuilder();
			uriBuilder.appendPathElement("login");
			HttpServletResponse responseCast = (HttpServletResponse) resp;
			responseCast.sendRedirect(uriBuilder.toString());
		} else {
			VariableMap map = VariableMap.getMappingFromRequest(req);
			map.put(Constants.USERNAME_PARAM, session.getUser().getUsername());
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
