package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.MultipleChoiceQuiz;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.QuizRenderer;
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

	public static Question getFirstQuestionForChapter(Chapter chapter) {
		for (Lesson lesson : chapter.getLessons()) {
			for (Question question : lesson.getQuestions()) {
				return question;
			}
		}
		return null;
	}

	public static QuizRenderer getQuiz(Question question) {
		if (question.getType().equals(Question.MULTIPLE_CHOICE)) {
			return new MultipleChoiceQuiz();
		}
		return null;
	}
}
