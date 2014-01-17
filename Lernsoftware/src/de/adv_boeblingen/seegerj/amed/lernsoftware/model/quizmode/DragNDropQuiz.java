package de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode;

import javax.servlet.http.HttpServletRequest;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Response;

public class DragNDropQuiz extends QuizRenderer {

	@Override
	public Answer getAnswer(HttpServletRequest request) {
		return null;
	}

	@Override
	protected boolean supportsShuffledAnswers() {
		return false;
	}

	@Override
	protected void renderQuestion(StringBuilder builder, Question question) {
		/*
		 * http://www.html5rocks.com/de/tutorials/dnd/basics/
		 */
		String q = question.getQuestion();
		for (Answer answer : question.getAnswers()) {
			String dropTarget = String.format(Constants.Markup.DROPPABLE, answer.getUniqueLabel());
			String dropField = String.format(Constants.Markup.HIDDEN_FIELD, answer.getHiddenLabel());
			q = q.replaceFirst("[{]([q][0-9][a][0-9])[}]", dropTarget);
			builder.append(dropField);
		}
		builder.append(q);
		builder.append(Constants.Markup.BREAK);
	}

	@Override
	protected void renderAnswer(StringBuilder builder, Answer answer) {
		String renderedAnswer = String.format(Constants.Markup.DRAGGABLE, answer.getHiddenLabel(), answer.getAnswer());
		builder.append(renderedAnswer);
	}
}
