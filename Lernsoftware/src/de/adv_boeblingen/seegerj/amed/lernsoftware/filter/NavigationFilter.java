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

@WebFilter(urlPatterns = { "/Quiz/*", "/Lesson/*", "/Stats" })
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
		renderedNavigation = new StringBuilder();

		renderedNavigation.append("<ul>");
		for (Chapter chapter : ChapterController.getChapters()) {
			renderChapter(chapter, stateController);
		}

		createEntry("Stats", NavigationHelper.getStatLink(), null);

		renderedNavigation.append("</ul>");

		return this.renderedNavigation.toString();
	}

	private void renderChapter(Chapter chapter, StateController stateController) {
		renderedNavigation.append(String.format("<li>%s</li>", chapter.getTitle()));
		boolean isCurrent = stateController.isCurrentChapter(chapter);
		boolean isChapterComplete = true;

		renderedNavigation.append("<ul>");
		for (Lesson lesson : chapter.getLessons()) {
			boolean isLessonComplete = stateController.isLessonComplete(lesson);
			isChapterComplete &= isLessonComplete;
			renderLesson(lesson, stateController, isCurrent, isLessonComplete);
		}

		String htmlClass = null;
		if (isChapterComplete) {
			htmlClass = "done";
		}
		createEntry("Quiz", NavigationHelper.getQuizLink(chapter), htmlClass);
		this.renderedNavigation.append("</ul>");
	}

	private void renderLesson(Lesson lesson, StateController stateController, boolean isCurrent, boolean isComplete) {
		String label = lesson.getTitle();
		String link = NavigationHelper.getNavLink(lesson);

		String htmlClass = null;
		if (isComplete) {
			htmlClass = "done";
		}
		createEntry(label, link, htmlClass);
	}

	private void createEntry(String label, String link, String htmlClass) {
		String inner;
		if (link == null) {
			inner = label;
		} else {
			inner = String.format("<a href=\"%s\">%s</a>", link, label);
		}

		htmlClass = " class=\"" + htmlClass + "\"";
		renderedNavigation.append(String.format("<li%s>%s</li>", htmlClass, inner));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
