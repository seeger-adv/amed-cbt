package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		// return ChapterController.isChapterComplete(this.mUser, chapter);
		return false;
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
				CriteriaBuilder builder = manager.getCriteriaBuilder();
				CriteriaQuery<Response> criteria = builder.createQuery(Response.class);
				Root<Response> response = criteria.from(Response.class);

				Path<Object> userPath = response.get("mUser").get("mUsername");
				Predicate userPredicate = builder.equal(userPath, mUser.getUsername());
				Path<Integer> questionPath = response.get("mQuestion").get("mId");
				Predicate questionPredicate = builder.equal(questionPath, question.getId());
				criteria.select(response).where(builder.and(userPredicate, questionPredicate));

				TypedQuery<Response> typedQuery = manager.createQuery(criteria);
				List<Response> resultList = typedQuery.getResultList();
				if (resultList != null && resultList.size() > 0) {
					return resultList.get(0);
				}
				return null;
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

	@Override
	public boolean isChapterComplete(Chapter chapter) {
		return ChapterController.isChapterComplete(this.mUser, chapter);
	}
}
