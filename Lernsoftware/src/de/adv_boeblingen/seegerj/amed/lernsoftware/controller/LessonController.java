package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.RetrieveAllQuery;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Response;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.User;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil.DatabaseRunnable;

public class LessonController {
	public static Lesson getLesson(final Integer id) {
		return DatabaseUtil.runQuery(new DatabaseRunnable<Lesson>() {
			@Override
			public Lesson run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(Lesson.class, id);
			}
		});
	}

	public static List<Lesson> getLessons() {
		return DatabaseUtil.runQuery(new RetrieveAllQuery<Lesson>(Lesson.class));
	}

	public static boolean isComplete(User user, Lesson lesson) {
		boolean result = true;
		for (Question question : lesson.getQuestions()) {
			StateControllerImpl state = new StateControllerImpl(user);
			Response response = state.getResponse(question);
			result |= (response == null);
		}
		return result;
	}

	public static Lesson getFirstLesson(Chapter chapter) {
		for (Lesson lesson : chapter.getLessons()) {
			return lesson;
		}
		return null;
	}
}
