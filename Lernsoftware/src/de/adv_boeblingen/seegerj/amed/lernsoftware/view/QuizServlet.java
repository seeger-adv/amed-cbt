package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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
		sb.append(String.format(Constants.Markup.HEADLINE1, "Quiz for Chapter " + id));
		sb.append(Constants.Markup.FORM_START);

		HttpServletRequest httpRequest = req;
		HttpSession httpSession = httpRequest.getSession();
		Session session = (Session) httpSession.getAttribute(Constants.SESSION_PARAM);
		StateController state = session.getStateController();

		int questionId = PathUtil.getCurrentQuestion(req);
		Question question = QuestionController.getQuestion(questionId);
		if (question == null) {
			question = QuestionController.getFirstQuestionForChapter(chapter);
		}
		sb.append(renderQuestion(state, question));

		sb.append(Constants.Markup.FORM_END);
		return sb.toString();
	}

	private String renderQuestion(StateController state, Question question) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format(Constants.Markup.PAR, question.getQuestion()));
		for (Answer answer : question.getAnswers()) {
			renderAnswer(builder, answer);
		}
		return builder.toString();
	}

	private void renderAnswer(StringBuilder builder, Answer answer) {
		String answerLabel = answer.getUniqueLabel();
		String questionLabel = answer.getQuestion().getUniqueLabel();
		String label = String.format(Constants.Markup.LABEL, answerLabel, answer.getAnswer());
		String checkbox = String.format(Constants.Markup.RADIO, questionLabel, answerLabel);
		builder.append(checkbox).append(label).append(Constants.Markup.BREAK);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
}
