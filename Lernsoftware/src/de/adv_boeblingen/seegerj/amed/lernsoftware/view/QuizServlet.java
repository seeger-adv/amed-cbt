package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.ChapterController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.QuestionController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.User;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

@WebServlet("/Quiz/*")
@SuppressWarnings("serial")
public class QuizServlet
		extends HttpServlet {

	public QuizServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		VariableMap map = VariableMap.getMappingFromRequest(req);
		map.put(Constants.CONTENT_PARAM, renderQuiz(req));

		new TemplateRenderer(req, "/_template.jtpl").printOutput(writer);
	}

	private String renderQuiz(HttpServletRequest req) {
		int id = PathUtil.retrieveLessonId(req);
		Chapter chapter = ChapterController.getChapter(id);

		StringBuilder sb = new StringBuilder();
		sb.append("<h1>Quiz for Chapter ").append(id).append("</h1>").append("<form action=\"\" method=\"post\">");

		Session session = null;
		User user = getUserFromSession();
		Set<Question> unansweredQuestions = QuestionController.getUnansweredQuestions(user, chapter);

		for (Question question : unansweredQuestions) {
			String renderedQuestion = renderQuestion(question);
			sb.append(renderedQuestion);
		}

		sb.append("</form>");
		return sb.toString();
	}

	private String renderQuestion(Question question) {
		StringBuilder builder = new StringBuilder();
		builder.append("<p>").append(question.getQuestion()).append("</p>");
		for (Answer answer : question.getAnswers()) {
			builder.append(answer.getAnswer());
		}
		return builder.toString();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
}
