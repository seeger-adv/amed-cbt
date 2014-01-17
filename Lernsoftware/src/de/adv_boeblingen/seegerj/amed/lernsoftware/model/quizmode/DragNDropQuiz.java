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
		String droppableMarkup = Constants.Markup.DROPPABLE;
		String hiddenField = Constants.Markup.HIDDEN_FIELD;
		String qIdentifier = "[{]([q][0-9][a][0-9])[}]";
		for (Answer answer : question.getAnswers()) {
			String uniqueLabel = answer.getUniqueLabel();
			String dropTarget = String.format(droppableMarkup, uniqueLabel);
			String hiddenLabel = answer.getHiddenLabel();
			String dropField = String.format(hiddenField, hiddenLabel);
			q = q.replaceFirst(qIdentifier, dropTarget);
			builder.append(dropField);
		}
		builder.append(q);
		builder.append(Constants.Markup.BREAK);
	}

	@Override
	protected void renderAnswer(StringBuilder builder, Answer answer) {
		String label = answer.getHiddenLabel();
		String answerText = answer.getAnswer();
		String draggableMarkup = Constants.Markup.DRAGGABLE;
		String renderedAnswer = String.format(draggableMarkup, label, answerText);
		builder.append(renderedAnswer);
	}
}
