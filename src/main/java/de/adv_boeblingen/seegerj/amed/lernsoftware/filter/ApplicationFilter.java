package de.adv_boeblingen.seegerj.amed.lernsoftware.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Configuration;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.UriBuilder;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

public class ApplicationFilter
		implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		VariableMap map = VariableMap.getMappingFromRequest(req);
		UriBuilder builder = PathUtil.getBaseUriBuilder();
		map.put(Constants.BASE_URL_PARAM, builder.toString());
		map.put(Constants.TITLE_PARAM, Configuration.APP_TITLE);

		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		String message = (String) session.getAttribute(Constants.MESSAGE_PARAM);
		map.put(Constants.MESSAGE_PARAM, message == null ? "" : message);

		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
