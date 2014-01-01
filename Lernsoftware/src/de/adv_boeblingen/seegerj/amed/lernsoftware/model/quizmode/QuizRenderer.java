package de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;

public abstract class QuizRenderer {
	public abstract Answer getAnswer(HttpServletRequest request);

	protected abstract boolean supportsShuffledAnswers();

	public void renderAnswers(StringBuilder builder, Question question) {
		ArrayList<Answer> answers = new ArrayList<Answer>(question.getAnswers());
		if (supportsShuffledAnswers()) {
			Collections.shuffle(answers);
		}

		for (Answer answer : answers) {
			renderAnswer(builder, answer);
		}
	}

	protected abstract void renderAnswer(StringBuilder builder, Answer answer);
}
