package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.DatabaseController.DatabaseRunnable;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.User;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.GetAllQuery;

public class LessonController {
	public static Lesson getLesson(final Integer id) {
		return DatabaseController.runQuery(new DatabaseRunnable<Lesson>() {
			@Override
			public Lesson run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(Lesson.class, id);
			}
		});
	}

	public static List<Lesson> getLessons() {
		return DatabaseController.runQuery(new GetAllQuery<Lesson>(Lesson.class));
	}

	public static boolean isComplete(User user, Lesson lesson) {
		return false;
	}
}
