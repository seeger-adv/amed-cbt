package de.adv_boeblingen.seeegerj.amed.lernoftware.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seeegerj.amed.lernoftware.controller.ChapterController;
import de.adv_boeblingen.seeegerj.amed.lernoftware.controller.LessonController;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Chapter;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Lesson;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.Constants;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.PathUtil;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.VariableMap;

@WebServlet("/Lesson/*")
@SuppressWarnings("serial")
public class LessonServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		VariableMap map = VariableMap.getMappingFromRequest(req);
		map.put(Constants.CONTENT_PARAM, renderLesson(req));

		new TemplateRenderer(req, "/_template.jtpl").PrintOutput(writer);
	}

	private String renderLesson(HttpServletRequest req) {
		int lessonId = PathUtil.retrieveLessonId(req);

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

		String content = lesson.getContent();
		if (content != null) {
			return content;
		}

		return "";
	}

	private String renderDefaultLesson() {
		Chapter firstChapter = ChapterController.getChapters().get(0);
		Lesson firstLesson = firstChapter.getLessons().iterator().next();
		return renderLesson(firstLesson);
	}
}
