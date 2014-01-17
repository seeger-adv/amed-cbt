package de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Response;

public abstract class QuizRenderer {
	public abstract Response getResponse(HttpServletRequest request);

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

	protected void renderQuestion(StringBuilder builder, Question question) {
		builder.append(String.format(Constants.Markup.PAR,
				question.getQuestion()));
	}

	public void render(StringBuilder builder, Question question) {
		builder.append(Constants.Markup.FORM_START);

		renderQuestion(builder, question);
		renderAnswers(builder, question);

		builder.append(Constants.Markup.BREAK);
		builder.append(Constants.Markup.SUBMIT);
		builder.append(Constants.Markup.FORM_END);
	}
}
