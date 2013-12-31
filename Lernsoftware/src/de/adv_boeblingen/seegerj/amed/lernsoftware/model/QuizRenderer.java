package de.adv_boeblingen.seegerj.amed.lernsoftware.model;

public interface QuizRenderer {
	boolean isCorrect();

	void renderAnswer(StringBuilder builder, Answer answer);
}
