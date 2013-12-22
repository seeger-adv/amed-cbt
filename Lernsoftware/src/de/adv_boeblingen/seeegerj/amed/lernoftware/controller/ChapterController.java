package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import java.util.List;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Chapter;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Lesson;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.User;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.GetAllQuery;

public class ChapterController {
	public static List<Chapter> getChapters() {
		return DatabaseController.runQuery(new GetAllQuery<Chapter>(Chapter.class));
	}

	public static boolean isChapterComplete(User user, Chapter chapter) {
		boolean isComplete = false;

		for (Lesson lesson : chapter.getLessons()) {
			isComplete |= LessonController.isComplete(lesson);
		}

		return isComplete;
	}
}
