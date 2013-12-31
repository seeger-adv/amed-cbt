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

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.ChapterController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.NavigationHelper;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

@WebFilter(urlPatterns = "/Quiz/*")
public class StateFilter
		implements Filter {

	private int count;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {

		count = 0;
		VariableMap map = VariableMap.getMappingFromRequest(req);
		map.put(Constants.STATUS_PARAM, renderProgress(req));

		chain.doFilter(req, resp);
	}

	private String renderProgress(ServletRequest req) {
		StringBuilder sb = new StringBuilder();
		sb.append("Question:\n");

		int id = PathUtil.getFirstUrlSegmentAsId((HttpServletRequest) req);
		Chapter chapter = ChapterController.getChapter(id);

		for (Lesson lesson : chapter.getLessons()) {
			for (Question question : lesson.getQuestions()) {
				sb.append(renderQuestion(sb, question));
			}
		}
		sb.append("<a href=\"\">skip</a>");
		return sb.toString();
	}

	private String renderQuestion(StringBuilder sb, Question question) {
		String htmlClass = getQuestionState(question);
		String link = String.format("<a href=\"%s\">%s</a>", NavigationHelper.getQuizLink(question), ++count);
		return String.format("<div class=\"%s\" >%s</div>\n", htmlClass, link);
	}

	private String getQuestionState(Question question) {
		return "bla";
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
