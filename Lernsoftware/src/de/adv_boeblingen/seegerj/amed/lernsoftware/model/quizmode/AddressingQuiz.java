package de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode;

import javax.servlet.http.HttpServletRequest;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Response;

/**
 * http://jsfiddle.net/3F2gM/3/
 * 
 * @author seegerj
 */
public class AddressingQuiz extends QuizRenderer {

	@Override
	public Response getResponse(HttpServletRequest request) {
		String given = request.getParameter("adress");

		Response response = new Response();
		response.setGivenValue(given.toString());
		return response;
	}

	@Override
	protected boolean supportsShuffledAnswers() {
		return false;
	}

	@Override
	public void renderAnswers(StringBuilder builder, Question question) {
		renderAnswer(builder, null);
	}

	@Override
	protected void renderAnswer(StringBuilder builder, Answer answer) {
		builder.append(Constants.Markup.ADRESSINGBOX).append(Constants.Markup.BREAK);
	}
}
