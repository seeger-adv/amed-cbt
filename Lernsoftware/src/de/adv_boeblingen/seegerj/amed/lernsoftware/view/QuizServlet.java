package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.QuestionController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.StateController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;
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
		StringBuilder sb = new StringBuilder();
		int id = PathUtil.retrieveLessonId(req);
		sb.append("<h1>Quiz for Chapter ").append(id).append("</h1>").append("<form action=\"\" method=\"post\">");

		HttpServletRequest httpRequest = req;
		HttpSession httpSession = httpRequest.getSession();
		Session session = (Session) httpSession.getAttribute(Constants.SESSION_PARAM);
		StateController state = session.getStateController();

		int questionId = PathUtil.getCurrentQuestion(req);
		Question question = QuestionController.getQuestion(questionId);
		sb.append(renderQuestion(state, question));

		sb.append("</form>");
		return sb.toString();
	}

	private String renderQuestion(StateController state, Question question) {
		if (question == null) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		builder.append("<p>").append(question.getQuestion()).append("</p>");
		for (Answer answer : question.getAnswers()) {
			renderAnswer(builder, answer);
		}
		return builder.toString();
	}

	private void renderAnswer(StringBuilder builder, Answer answer) {
		String label = String.format("<label for=\"%s\">%s</label>", answer.getUniqueLabel(), answer.getAnswer());
		String checkbox = String.format("<input type=\"radio\" name=\"%s\" value=\"%s\">", answer.getQuestion()
				.getUniqueLabel(), answer.getUniqueLabel());
		builder.append(checkbox).append(label).append("<br>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
}
