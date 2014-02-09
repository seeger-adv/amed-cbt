package de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode;

import javax.servlet.http.HttpServletRequest;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Response;

public class DropdownQuiz
		extends QuizRenderer {

	@Override
	public Response getResponse(HttpServletRequest request) {
		return null;
	}

	@Override
	public boolean supportsShuffledAnswers() {
		return true;
	}

	@Override
	public void renderAnswers(StringBuilder builder, Question question) {
		String questionLabel = question.getUniqueLabel();
		builder.append(String.format("<select name=\"%s\">", questionLabel));
		super.renderAnswers(builder, question);
		builder.append("</select>").append(Constants.Markup.BREAK);
	}

	@Override
	public void renderAnswer(StringBuilder builder, Answer answer) {
		String answerLabel = answer.getUniqueLabel();
		builder.append(String.format("<option value=\"%s\">%s</option>", answerLabel, answer.getAnswer()));
	}
}
