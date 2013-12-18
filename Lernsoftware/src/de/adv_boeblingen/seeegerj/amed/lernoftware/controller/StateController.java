package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Chapter;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Lesson;

public interface StateController {
	boolean isCurrentChapter(Chapter chapter);

	boolean isLessonComplete(Lesson lesson);
}
