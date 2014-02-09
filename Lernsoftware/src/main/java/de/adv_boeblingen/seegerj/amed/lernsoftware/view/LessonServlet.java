package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.ChapterController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.LessonController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.TemplateRenderer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

@SuppressWarnings("serial")
public class LessonServlet
extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		VariableMap map = VariableMap.getMappingFromRequest(req);
		map.put(Constants.CONTENT_PARAM, renderLesson(req));

		new TemplateRenderer(req, "/_template.jtpl").printOutput(writer);
	}

	private String renderLesson(HttpServletRequest req) {
		int lessonId = PathUtil.getFirstUrlSegmentAsId(req);

		if (lessonId == -1) {
			return renderDefaultLesson();
		}

		Lesson firstLesson = LessonController.getLesson(lessonId);
		if (firstLesson == null) {
			return renderDefaultLesson();
		}

		return renderLesson(firstLesson);
	}

	private String renderLesson(Lesson lesson) {
		if (lesson == null) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		builder.append(String.format("<h1>%s</h1>", lesson.getTitle()));
		builder.append(lesson.getContent());
		return builder.toString();
	}

	private String renderDefaultLesson() {
		Chapter firstChapter = ChapterController.getChapters().get(0);
		Lesson firstLesson = firstChapter.getLessons().iterator().next();
		return renderLesson(firstLesson);
	}
}
