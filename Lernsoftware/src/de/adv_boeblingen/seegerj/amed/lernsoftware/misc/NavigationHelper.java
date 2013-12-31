package de.adv_boeblingen.seegerj.amed.lernsoftware.misc;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

public class NavigationHelper {
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

	public static String getNavLink(Lesson lesson) {
		UriBuilder builder = PathUtil.getBaseUriBuilder();
		builder.appendPathElement("Lesson");
		builder.appendPathElement(Integer.toString(lesson.getId()));
		return builder.toString();
	}
}
