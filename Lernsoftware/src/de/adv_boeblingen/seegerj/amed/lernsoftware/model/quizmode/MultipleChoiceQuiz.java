package de.adv_boeblingen.seegerj.amed.lernsoftware.model.quizmode;

import javax.servlet.http.HttpServletRequest;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.ChapterController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

public class MultipleChoiceQuiz
		extends QuizRenderer {

	@Override
	public Answer getAnswer(HttpServletRequest req) {
		int id = PathUtil.getFirstUrlSegmentAsId(req);
		Chapter chapter = ChapterController.getChapter(id);

		for (Lesson lesson : chapter.getLessons()) {
			for (Question question : lesson.getQuestions()) {
				String answerParameter = req.getParameter(question.getUniqueLabel());
				for (Answer answer : question.getAnswers()) {
					String answerLabel = answer.getUniqueLabel();
					if (answerLabel.equals(answerParameter)) {
						return answer;
					}
				}
			}
		}
		return null;
	}

	@Override
	public void renderAnswer(StringBuilder builder, Answer answer) {
		String answerLabel = answer.getUniqueLabel();
		String questionLabel = answer.getQuestion().getUniqueLabel();
		String label = String.format(Constants.Markup.LABEL, answerLabel, answer.getAnswer());
		String checkbox = String.format(Constants.Markup.RADIO, questionLabel, answerLabel, answerLabel);
		builder.append(checkbox).append(label).append(Constants.Markup.BREAK);
	}

	@Override
	public boolean supportsShuffledAnswers() {
		return true;
	}
}
