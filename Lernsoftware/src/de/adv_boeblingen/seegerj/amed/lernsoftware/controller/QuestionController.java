package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil.DatabaseRunnable;

public class QuestionController {
	public static List<Question> getQuestions() {
		return new ArrayList<Question>();
	}

	public static Question getQuestion(final int questionId) {
		return DatabaseUtil.runQuery(new DatabaseRunnable<Question>() {
			@Override
			public Question run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(Question.class, questionId);
			}
		});
	}
}
