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
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.Constants;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.VariableMap;

@WebFilter(urlPatterns = "/Lesson/*")
public class NavigationFilter implements Filter {
	StringBuilder renderedNavigation;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		VariableMap map = VariableMap.getMappingFromRequest(req);
		Lesson currentLesson = NavigationController.getCurrentLesson(req);
		map.put(Constants.NAVIGATION_PARAM, renderNavigation(currentLesson));

		chain.doFilter(req, resp);
	}

	private String renderNavigation(Lesson currentLesson) {
		this.renderedNavigation = new StringBuilder();

		for (Chapter chapter : NavigationController.getNavigation()) {
			renderChapter(chapter, currentLesson);
		}

		return this.renderedNavigation.toString();
	}

	private void renderChapter(Chapter chapter, Lesson currentLesson) {
		this.renderedNavigation.append("<ul>").append(
				String.format("<li>%s</li>", chapter.getTitle()));

		for (Lesson lesson : chapter.getLessons()) {
			if (lesson == null) {
				continue;
			}

			boolean isCurrent = NavigationController.isCurrent(lesson,
					currentLesson);
			renderLesson(lesson, isCurrent);
		}
		this.renderedNavigation.append("</ul>");
	}

	private void renderLesson(Lesson lesson, boolean isCurrent) {
		this.renderedNavigation.append("<ul>").append("<li>");
		if (isCurrent) {
			this.renderedNavigation.append(lesson.getTitle());
		} else {
			this.renderedNavigation
					.append(String.format("<a href=\"%s\">%s</a>",
							NavigationController.getNavLink(lesson),
							lesson.getTitle()));
		}

		this.renderedNavigation.append("</li>").append("</ul>");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
