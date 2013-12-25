package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.DatabaseController.DatabaseRunnable;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.User;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.GetAllQuery;

public class ChapterController {
	public static List<Chapter> getChapters() {
		return DatabaseController.runQuery(new GetAllQuery<Chapter>(Chapter.class));
	}

	public static boolean isChapterComplete(User user, Chapter chapter) {
		boolean isComplete = false;

		for (Lesson lesson : chapter.getLessons()) {
			isComplete |= LessonController.isComplete(user, lesson);
		}

		return isComplete;
	}

	public static Chapter getChapter(final int id) {
		return DatabaseController.runQuery(new DatabaseRunnable<Chapter>() {
			@Override
			public Chapter run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(Chapter.class, id);
			}
		});
	}
}
