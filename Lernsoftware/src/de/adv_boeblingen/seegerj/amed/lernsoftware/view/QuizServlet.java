package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.ChapterController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
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

		for (Lesson lesson : chapter.getLessons()) {
			for (Question question : lesson.getQuestions()) {
				String renderedQuestion = renderQuestion(question);
				sb.append(renderedQuestion);
			}
		}

		sb.append("</form>");
		return sb.toString();
	}

	private String renderQuestion(Question question) {
		return "<p>" + question.getQuestion() + "</p>";
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
}
