package de.adv_boeblingen.seegerj.amed.lernsoftware.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.ChapterController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.NavigationController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.StateController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.NavigationHelper;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;

@WebFilter(urlPatterns = { "/Quiz/*", "/Lesson/*", "/Stats/*" })
public class NavigationFilter
implements Filter {
	StringBuilder renderedNavigation;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
	ServletException {

		final StateController stateController = NavigationController.retrieveFromSession(req);
		final VariableMap map = VariableMap.getMappingFromRequest(req);
		map.put(Constants.NAVIGATION_PARAM, renderNavigation(stateController));

		chain.doFilter(req, resp);
	}

	private String renderNavigation(StateController stateController) {
		this.renderedNavigation = new StringBuilder();

		for (Chapter chapter : ChapterController.getChapters()) {
			renderChapter(chapter, stateController);
		}

		return this.renderedNavigation.toString();
	}

	private void renderChapter(Chapter chapter, StateController stateController) {
		this.renderedNavigation.append("<ul>").append(String.format("<li>%s</li>", chapter.getTitle()));
		boolean isCurrent = stateController.isCurrentChapter(chapter);

		for (Lesson lesson : chapter.getLessons()) {
			renderLesson(lesson, stateController, isCurrent);
		}

		String htmlClass = null;
		if (stateController.isChapterComplete(chapter)) {
			htmlClass = "done";
		}
		createEntry("Quiz", NavigationHelper.getQuizLink(chapter), htmlClass);

		this.renderedNavigation.append("</ul>");
	}

	private void renderLesson(Lesson lesson, StateController stateController, boolean isCurrent) {
		String label = lesson.getTitle();
		String link = NavigationHelper.getNavLink(lesson);
		createEntry(label, link, null);
	}

	private void createEntry(String label, String link, String htmlClass) {
		this.renderedNavigation.append("<ul>").append("<li");

		if (htmlClass != null) {
			this.renderedNavigation.append(String.format(" class=\"%s\"", htmlClass));
		}

		this.renderedNavigation.append('>');
		if (link == null) {
			this.renderedNavigation.append(label);
		} else {
			this.renderedNavigation.append(String.format("<a href=\"%s\">%s</a>", link, label));
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
