package de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode;

import javax.servlet.http.HttpServletRequest;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;

/**
 * http://jsfiddle.net/3F2gM/3/
 * 
 * @author seegerj
 */
public class AddressingQuiz extends QuizRenderer {

	@Override
	public Answer getAnswer(HttpServletRequest request) {
		return null;
	}

	@Override
	protected boolean supportsShuffledAnswers() {
		return false;
	}

	@Override
	protected void renderAnswer(StringBuilder builder, Answer answer) {

	}
}
