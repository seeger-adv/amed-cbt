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
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Chapter;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Lesson;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.VariableMap;

@WebFilter(urlPatterns = "/Lesson/*")
public class NavigationFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		VariableMap map = VariableMap.getMappingFromRequest(req);
		map.put("navigation", renderNavigation(req));

		chain.doFilter(req, resp);
	}

	private String renderNavigation(ServletRequest req) {
		StringBuilder renderedNavigation = new StringBuilder();

		for (Chapter chapter : NavigationController.getNavigation()) {
			renderChapter(chapter, renderedNavigation);
		}

		return renderedNavigation.toString();
	}

	private void renderChapter(Chapter chapter, StringBuilder navigation) {
		navigation.append("<ul>");
		navigation.append(String.format("<li>%s</li>", chapter.getTitle()));
		for (Lesson lesson : chapter.getLessons()) {
			if (lesson == null) {
				continue;
			}
			renderLesson(navigation, lesson, false);
		}
		navigation.append("</ul>");
	}

	private void renderLesson(StringBuilder navigation, Lesson lesson,
			boolean isCurrent) {
		navigation.append("<ul>");
		navigation.append("<li>");
		navigation.append(lesson.getTitle());
		navigation.append("</li>");
		navigation.append("</ul>");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
