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
import javax.servlet.http.HttpSession;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.ChapterController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.StateController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.NavigationHelper;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Response;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;
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
		map.put(Constants.STATUS_PARAM, renderProgress((HttpServletRequest) req));

		chain.doFilter(req, resp);
	}

	private String renderProgress(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		sb.append("Question:\n");

		int id = PathUtil.getFirstUrlSegmentAsId(req);
		Chapter chapter = ChapterController.getChapter(id);

		for (Lesson lesson : chapter.getLessons()) {
			for (Question question : lesson.getQuestions()) {
				sb.append(renderQuestion(req, sb, question));
			}
		}
		// String nextQuestion = QuestionController.
		sb.append("<a href=\"\">skip</a>");
		return sb.toString();
	}

	private String renderQuestion(HttpServletRequest req, StringBuilder sb, Question question) {
		Boolean questionState = getQuestionState(req, question);
		String content;
		String htmlClass = null;
		if (questionState == null) {
			content = String.format("<a href=\"%s\">%s</a>", NavigationHelper.getQuizLink(question), ++count);
		} else {
			if (questionState) {
				htmlClass = "correct";
			} else {
				htmlClass = "wrong";
			}
			content = Integer.toString(++count);
		}
		return String.format("<div class=\"%s\" >%s</div>\n", htmlClass, content);
	}

	private Boolean getQuestionState(HttpServletRequest req, Question question) {
		HttpSession httpSession = req.getSession();
		Session session = (Session) httpSession.getAttribute(Constants.SESSION_PARAM);
		StateController state = session.getStateController();

		Response response = state.getResponse(question);
		if (response == null) {
			// not answered yet
			return null;
		}

		Answer givenAnswer = response.getGivenAnswer();
		Answer correctAnswer = question.getCorrectAnswer();
		return givenAnswer.equals(correctAnswer);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
