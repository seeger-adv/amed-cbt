package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Chapter;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Lesson;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.User;

public class StateControllerImpl implements StateController {
	private final User mUser;

	public StateControllerImpl(User mUser) {
		super();
		this.mUser = mUser;
	}

	@Override
	public boolean isCurrentChapter(Chapter chapter) {
		return ChapterController.isChapterComplete(this.mUser, chapter);
	}

	@Override
	public boolean isLessonComplete(Lesson lesson) {
		return LessonController.isComplete(this.mUser, lesson);
	}
}
