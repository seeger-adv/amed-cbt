package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.ChapterController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.NavigationController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.QuestionController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.StateController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.NavigationHelper;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.TemplateRenderer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.QuizRenderer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

@WebServlet("/Quiz/*")
@SuppressWarnings("serial")
public class QuizServlet
		extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int chapterId = PathUtil.getFirstUrlSegmentAsId(req);
		int questionId = PathUtil.getCurrentQuestion(req);

		if (redirectIfNoQuestionGiven(chapterId, questionId, resp)) {
			return;
		}

		PrintWriter writer = resp.getWriter();
		VariableMap map = VariableMap.getMappingFromRequest(req);
		map.put(Constants.CONTENT_PARAM, renderQuiz(questionId));
		new TemplateRenderer(req, "/_template.jtpl").printOutput(writer);
	}

	private boolean redirectIfNoQuestionGiven(int chapterId, int questionId, HttpServletResponse resp)
			throws IOException {
		if (questionId == -1) {
			Chapter chapter = ChapterController.getChapter(chapterId);
			Question question = QuestionController.getFirstQuestionForChapter(chapter);
			String link = NavigationHelper.getQuizLink(question);
			link = resp.encodeRedirectURL(link);
			resp.sendRedirect(link);
			return true;
		}
		return false;
	}

	private String renderQuiz(int questionId) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(Constants.Markup.HEADLINE1, "Quiz for Chapter " + questionId));
		sb.append(Constants.Markup.FORM_START);

		Question question = QuestionController.getQuestion(questionId);
		QuizRenderer quiz = QuestionController.getQuiz(question);
		sb.append(String.format(Constants.Markup.PAR, question.getQuestion()));

		ArrayList<Answer> answers = new ArrayList<Answer>(question.getAnswers());

		if (quiz.supportsShuffledAnswers()) {
			Collections.shuffle(answers);
		}

		for (Answer answer : answers) {
			quiz.renderAnswer(sb, answer);
		}

		sb.append(Constants.Markup.SUBMIT);
		sb.append(Constants.Markup.FORM_END);
		return sb.toString();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		StateController state = getSessionFromRequest(req);

		Answer answer = getAnswer(req);
		if (answer != null) {
			state.answerQuestion(answer);
		}

		Question question = answer.getQuestion();
		String next = NavigationController.getNextQuestion(question);
		if (next == null) {
			Lesson lesson = question.getLesson();
			next = NavigationController.getNextLesson(lesson);
		}

		next = res.encodeRedirectURL(next);
		res.sendRedirect(next);
	}

	private StateController getSessionFromRequest(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		Session session = (Session) httpSession.getAttribute(Constants.SESSION_PARAM);
		StateController state = session.getStateController();
		return state;
	}

	private Answer getAnswer(HttpServletRequest req) {
		int id = PathUtil.getFirstUrlSegmentAsId(req);
		Chapter chapter = ChapterController.getChapter(id);

		for (Lesson lesson : chapter.getLessons()) {
			for (Question question : lesson.getQuestions()) {
				String answerParameter = req.getParameter(question.getUniqueLabel());
				for (Answer answer : question.getAnswers()) {
					if (answer.getUniqueLabel().equals(answerParameter)) {
						return answer;
					}
				}
			}
		}
		return null;
	}
}
