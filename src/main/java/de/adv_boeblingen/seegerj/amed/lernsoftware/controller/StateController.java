package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Response;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.User;

public interface StateController {
	boolean isCurrentChapter(Chapter chapter);

	boolean isLessonComplete(Lesson lesson);

	Response getResponse(Question question);

	void answerQuestion(Response response);

	boolean isChapterComplete(Chapter chapter);

	Boolean isUserResponseCorrect(Response response);

	User getUser();

	Question getNextUnansweredQuestion(Question question);
}
