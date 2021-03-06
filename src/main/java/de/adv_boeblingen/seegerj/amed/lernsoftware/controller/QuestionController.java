package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode.AddressingQuiz;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode.DragNDropQuiz;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode.DropdownQuiz;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode.MultipleChoiceQuiz;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode.QuizRenderer;
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
		} else if (question.getType().equals(Question.DROPDOWN)) {
			return new DropdownQuiz();
		} else if (question.getType().equals(Question.DRAGNDROP)) {
			return new DragNDropQuiz();
		} else if (question.getType().equals(Question.ADDRESSING)) {
			return new AddressingQuiz();
		}
		return null;
	}

	public static Set<Question> getNextQuestions(Question question) {
		Set<Question> matchingQuestions = new HashSet<Question>();

		Lesson lesson = question.getLesson();
		Chapter chapter = lesson.getChapter();
		for (Lesson currentLesson : chapter.getLessons()) {
			for (Question currentQuestion : currentLesson.getQuestions()) {
				if (currentQuestion.equals(question)) {
					continue;
				}

				matchingQuestions.add(currentQuestion);
			}
		}
		return matchingQuestions;
	}
}
