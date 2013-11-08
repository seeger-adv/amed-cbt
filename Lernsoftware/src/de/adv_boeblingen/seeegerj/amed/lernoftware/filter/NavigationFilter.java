package de.adv_boeblingen.seeegerj.amed.lernoftware.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import de.adv_boeblingen.seeegerj.amed.lernoftware.controller.NavigationController;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Lesson;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.VariableMap;

@WebFilter(urlPatterns="/Lesson/*")
public class NavigationFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		VariableMap map = VariableMap.getMappingFromRequest(req);
		map.put("navigation", renderNavigation());

		chain.doFilter(req, resp);
	}

	private String renderNavigation() {
		StringBuilder navigation = new StringBuilder();
		for(Lesson lesson: NavigationController.getNavigation()) {
			navigation.append("<ul>");
			navigation.append(String.format("<li>%s</li>", lesson.getTitle()));
			navigation.append("</ul>");
		}
		return navigation.toString();
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
