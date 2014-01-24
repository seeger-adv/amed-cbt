package de.adv_boeblingen.seegerj.amed.lernsoftware.misc;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

public class NavigationHelper {
	public static String getQuizLink(Chapter chapter) {
		UriBuilder builder = PathUtil.getBaseUriBuilder();
		builder.appendPathElement("Quiz");
		if (chapter == null) {
			return null;
		}
		int chapterId = chapter.getId();
		builder.appendPathElement(Integer.toString(chapterId));
		return builder.toString();
	}

	public static String getQuizLink(Question question) {
		UriBuilder builder = PathUtil.getBaseUriBuilder();
		builder.appendPathElement("Quiz");
		if (question == null) {
			return null;
		}
		Lesson lesson = question.getLesson();
		Chapter chapter = lesson.getChapter();
		int chapterId = chapter.getId();
		builder.appendPathElement(Integer.toString(chapterId));
		builder.appendPathElement("Question");
		int questionId = question.getId();
		builder.appendPathElement(Integer.toString(questionId));
		return builder.toString();
	}

	public static String getNavLink(Lesson lesson) {
		UriBuilder builder = PathUtil.getBaseUriBuilder();
		builder.appendPathElement("Lesson");
		int lessonId = lesson.getId();
		builder.appendPathElement(Integer.toString(lessonId));
		return builder.toString();
	}

	public static String getStatLink() {
		UriBuilder builder = PathUtil.getBaseUriBuilder();
		builder.appendPathElement("Stats");
		return builder.toString();
	}
}
