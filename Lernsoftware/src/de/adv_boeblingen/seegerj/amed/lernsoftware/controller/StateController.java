package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;

public interface StateController {
	boolean isCurrentChapter(Chapter chapter);

	boolean isLessonComplete(Lesson lesson);
}
