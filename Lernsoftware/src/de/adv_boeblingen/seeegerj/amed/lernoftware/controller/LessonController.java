package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seeegerj.amed.lernoftware.controller.DatabaseController.DatabaseRunnable;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Lesson;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.GetAllQuery;

public class LessonController {
	public static Lesson getLesson(final Integer id) {
		return DatabaseController.runQuery(new DatabaseRunnable<Lesson>() {
			@Override
			public Lesson run(EntityManager manager,
					EntityTransaction transaction) {
				return manager.find(Lesson.class, id);
			}
		});
	}

	public static List<Lesson> getLessons() {
		return DatabaseController.runQuery(new GetAllQuery<>(Lesson.class));
	}

}
