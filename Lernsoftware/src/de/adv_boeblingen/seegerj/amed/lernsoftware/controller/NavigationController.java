package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.UriBuilder;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

public class NavigationController {
	public static List<Chapter> getNavigation() {
		return ChapterController.getChapters();
	}

	public static String getNavLink(Lesson lesson) {
		UriBuilder builder = PathUtil.getBaseUriBuilder();
		builder.appendPathElement("Lesson");
		builder.appendPathElement(Integer.toString(lesson.getId()));
		return builder.toString();
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
		HttpServletRequest requestCast = (HttpServletRequest) req;
		HttpSession httpSession = requestCast.getSession();
		Session session = (Session) httpSession.getAttribute(Constants.SESSION_PARAM);
		return session.getStateController();
	}

	public static String getQuizLink(Chapter chapter) {
		UriBuilder builder = PathUtil.getBaseUriBuilder();
		builder.appendPathElement("Quiz");
		builder.appendPathElement(Integer.toString(chapter.getId()));
		return builder.toString();
	}

	public static String getQuizLink(Question question) {
		UriBuilder builder = PathUtil.getBaseUriBuilder();
		builder.appendPathElement("Quiz");
		builder.appendPathElement(Integer.toString(question.getLesson().getChapter().getId()));
		builder.appendPathElement("Question");
		builder.appendPathElement(Integer.toString(question.getId()));
		return builder.toString();
	}
}
