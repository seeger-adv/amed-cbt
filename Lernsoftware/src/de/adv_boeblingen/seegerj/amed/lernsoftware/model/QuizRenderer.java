package de.adv_boeblingen.seegerj.amed.lernsoftware.model;

import javax.servlet.http.HttpServletRequest;

public interface QuizRenderer {
	Answer getAnswer(HttpServletRequest request);

	boolean supportsShuffledAnswers();

	void renderAnswer(StringBuilder builder, Answer answer);
}
