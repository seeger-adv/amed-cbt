package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.RetrieveAllQuery;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.User;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil.DatabaseRunnable;

public class ChapterController {
	public static List<Chapter> getChapters() {
		return DatabaseUtil.runQuery(new RetrieveAllQuery<Chapter>(Chapter.class));
	}

	public static boolean isChapterComplete(User user, Chapter chapter) {
		boolean isComplete = true;

		for (Lesson lesson : chapter.getLessons()) {
			isComplete &= LessonController.isComplete(user, lesson);
		}

		return isComplete;
	}

	public static Chapter getChapter(final int id) {
		return DatabaseUtil.runQuery(new DatabaseRunnable<Chapter>() {
			@Override
			public Chapter run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(Chapter.class, id);
			}
		});
	}

	public static Chapter getNextChapter(Chapter chapter) {
		boolean found = false;
		for (Chapter currentChapter : ChapterController.getChapters()) {
			if (currentChapter.equals(chapter)) {
				found = true;
				continue;
			}

			if (found) {
				return currentChapter;
			}
		}

		return null;
	}
}
