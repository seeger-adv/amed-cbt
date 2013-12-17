package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import java.util.List;

import javax.servlet.ServletRequest;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Chapter;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Lesson;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.Constants;

public class NavigationController {
	public static List<Chapter> getNavigation() {
		return ChapterController.getChapters();
	}

	public static String getNavLink(Lesson lesson) {
		return Integer.toString(lesson.getId());
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
		boolean equalsChapter = lesson.getChapter().equals(
				currentLesson.getChapter());
		return !equalsLesson && equalsChapter;
	}
}
