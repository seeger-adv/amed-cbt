package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Answer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Response;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.User;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil.DatabaseRunnable;

public class StateControllerImpl
		implements StateController {
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

	@Override
	public Response getResponse(final Question question) {
		return DatabaseUtil.runQuery(new DatabaseRunnable<Response>() {
			@Override
			public Response run(EntityManager manager, EntityTransaction transaction) {
				Response query = new Response();
				query.setUser(mUser);
				query.setQuestion(question);
				return manager.find(Response.class, query);
			}
		});
	}

	@Override
	public void answerQuestion(final Answer givenAnswer) {
		DatabaseUtil.runTransaction(new DatabaseRunnable<Void>() {
			@Override
			public Void run(EntityManager manager, EntityTransaction transaction) {
				Response response = new Response();
				response.setGivenAnswer(givenAnswer);
				response.setQuestion(givenAnswer.getQuestion());
				response.setUser(mUser);
				response.setTimestamp(new Date().getTime());
				manager.persist(response);
				return null;
			}
		});
	}
}
