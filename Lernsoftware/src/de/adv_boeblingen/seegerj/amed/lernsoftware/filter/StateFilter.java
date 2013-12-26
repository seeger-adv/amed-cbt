package de.adv_boeblingen.seegerj.amed.lernsoftware.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;

@WebFilter(urlPatterns = "/Quiz/*")
public class StateFilter
		implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {

		VariableMap map = VariableMap.getMappingFromRequest(req);
		map.put(Constants.STATUS_PARAM, renderProgress(req));

		chain.doFilter(req, resp);
	}

	private String renderProgress(ServletRequest req) {
		StringBuilder sb = new StringBuilder();
		sb.append("Question:\n");
		sb.append("<div class=\"bla\" id=\"q1\">1</div>\n");
		sb.append("<div class=\"bla\" id=\"q2\">2</div>\n");
		sb.append("<div class=\"bla\" id=\"q3\">3</div>\n");
		sb.append("<div class=\"bla\" id=\"q4\">4</div>\n");
		sb.append("<a href=\"\">skip</a>");
		return sb.toString();
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
