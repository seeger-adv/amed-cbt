package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.adv_boeblingen.seeegerj.amed.lernoftware.controller.DatabaseController.DatabaseRunnable;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Chapter;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.Constants;

public class ChapterController {
	public static List<Chapter> getChapters() {
		return DatabaseController
				.runTransaction(new DatabaseRunnable<List<Chapter>>() {
					@Override
					public List<Chapter> run(EntityManager manager,
							EntityTransaction transaction) {
						String queryString = String.format(
								Constants.BASIC_QUERY, Chapter.class.getName());

						Query query = manager.createQuery(queryString);

						@SuppressWarnings("unchecked")
						List<Chapter> all = query.getResultList();
						if (all == null) {
							return new ArrayList<>();
						}
						return all;
					}
				});
	}

	public static boolean isChapterComplete(Chapter chapter) {
		// TODO Auto-generated method stub
		return false;
	}
}
