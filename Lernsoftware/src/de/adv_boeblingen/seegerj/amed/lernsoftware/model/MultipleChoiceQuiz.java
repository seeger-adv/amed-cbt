package de.adv_boeblingen.seegerj.amed.lernsoftware.model;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;

public class MultipleChoiceQuiz
		implements QuizRenderer {

	@Override
	public boolean isCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void renderAnswer(StringBuilder builder, Answer answer) {
		String answerLabel = answer.getUniqueLabel();
		String questionLabel = answer.getQuestion().getUniqueLabel();
		String label = String.format(Constants.Markup.LABEL, answerLabel, answer.getAnswer());
		String checkbox = String.format(Constants.Markup.RADIO, questionLabel, answerLabel);
		builder.append(checkbox).append(label).append(Constants.Markup.BREAK);
	}

	@Override
	public boolean supportsShuffledAnswers() {
		return true;
	}
}
