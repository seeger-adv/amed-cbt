package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Response;

public interface StateController {
	boolean isCurrentChapter(Chapter chapter);

	boolean isLessonComplete(Lesson lesson);

	Response getResponse(Question question);

	void answerQuestion(Answer givenAnswer);
}
