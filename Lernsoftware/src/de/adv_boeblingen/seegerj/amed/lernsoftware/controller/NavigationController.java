package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

public class NavigationController {
	public static List<Chapter> getNavigation() {
		return ChapterController.getChapters();
	}

	public static String getNavLink(Lesson lesson) {
		return PathUtil.buildQuery("Lesson/" + Integer.toString(lesson.getId()));
	}

	public static boolean containsLesson(Chapter chapter, Lesson lesson) {
		for (Lesson l : chapter.getLessons()) {
			if (l.equals(lesson)) {
				return true;
			}
		}
		return false;
	}

	public static Lesson getCurrentLesson(ServletRequest req) {
		String idParam = (String) req.getAttribute(Constants.LESSON_PARAM);

		if (idParam == null) {
			return null;
		}

		int id = Integer.parseInt(idParam);

		return LessonController.getLesson(id);
	}

	public static boolean isCurrent(Lesson lesson, Lesson currentLesson) {
		if (currentLesson == null) {
			return false;
		}

		boolean equalsLesson = lesson.equals(currentLesson);
		boolean equalsChapter = lesson.getChapter().equals(currentLesson.getChapter());
		return !equalsLesson && equalsChapter;
	}

	public static StateController retrieveFromSession(ServletRequest req) {
		Session session = (Session) ((HttpServletRequest) req).getSession().getAttribute(Constants.SESSION_PARAM);
		return session.getStateController();

	}

	public static String getQuizLink(Chapter chapter) {
		return PathUtil.buildQuery("Quiz/" + Integer.toString(chapter.getId()));
	}
}
